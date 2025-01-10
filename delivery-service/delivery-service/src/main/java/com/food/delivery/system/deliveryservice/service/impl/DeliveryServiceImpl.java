package com.food.delivery.system.deliveryservice.service.impl;


import com.food.delivery.system.deliveryservice.model.DeliveryAgent;
import com.food.delivery.system.deliveryservice.model.DeliveryAssignment;
import com.food.delivery.system.deliveryservice.model.OrderDetails;
import com.food.delivery.system.deliveryservice.model.OrderStatus;
import com.food.delivery.system.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final Logger log = LogManager.getLogger(DeliveryServiceImpl.class);

    @Override
    public String deliverOrder(String orderId) {
        log.info("Initiating delivery for order ID: {}", orderId);
        try {
            // Update order status to DELIVERED
            webClientBuilder.build().put()
                    .uri("http://order-service/api/order/status",
                            uriBuilder -> uriBuilder
                                    .queryParam("orderId", orderId)
                                    .build())
                    .bodyValue(OrderStatus.DELIVERED)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            log.info("Order status updated to DELIVERED for order ID: {}", orderId);
            return "Order with ID: " + orderId + " delivered";
        } catch (Exception e) {
            log.error("Failed to deliver order ID: {}", orderId, e);
            throw new RuntimeException("Delivery process failed for order ID: " + orderId, e);
        }
    }

    @Override
    public void receiveOrderAssignment() {
        log.info("Receiving order assignment request");
        try {
            createNewAssignment(new OrderDetails());
            log.info("Order assignment successfully created");
        } catch (Exception e) {
            log.error("Failed to create order assignment", e);
            throw new RuntimeException("Order assignment creation failed", e);
        }
    }

    private void createNewAssignment(OrderDetails orderDetails) {
        log.info("Fetching delivery agents for new assignment");
        try {
            // Fetch delivery agents
            DeliveryAgent[] deliveryAgents = webClientBuilder.build().get()
                    .uri("http://customer-service/api/user/delivery", UriBuilder::build)
                    .retrieve()
                    .bodyToMono(DeliveryAgent[].class)
                    .block();

            if (deliveryAgents == null || deliveryAgents.length == 0) {
                log.warn("No available delivery agents found");
                throw new RuntimeException("No available delivery agents");
            }

            // Create DeliveryAssignment using the first available agent
            DeliveryAssignment deliveryAssignment = new DeliveryAssignment();
            deliveryAssignment.setDeliveryAgentName(deliveryAgents[0].getName());
            deliveryAssignment.setDeliveryAgentCurrentAddress(deliveryAgents[0].getAddress());
            deliveryAssignment.setDeliveryAgentPhoneNumber(deliveryAgents[0].getPhoneNumber());
            deliveryAssignment.setOrderDetails(orderDetails);

            log.info("Delivery assignment created successfully with agent: {}", deliveryAgents[0].getName());
        } catch (Exception e) {
            log.error("Error while creating delivery assignment", e);
            throw new RuntimeException("Error creating delivery assignment", e);
        }
    }

    @Override
    public void callCustomer() {
        log.info("Calling customer for delivery update");
        // Implement logic for calling customer if needed
    }
}
