package com.food.delivery.system.deliveryservice.controller;

import com.food.delivery.system.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    private static final Logger log = LogManager.getLogger(DeliveryController.class);

    @PostMapping("/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deliverOrder(@PathVariable String orderId) {
        log.info("Received request to deliver order with ID: {}", orderId);
        try {
            String response = deliveryService.deliverOrder(orderId);
            log.info("Delivery process completed for order ID: {}", orderId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Delivery process failed for order ID: {}", orderId, e);
            return new ResponseEntity<>("Delivery process failed. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/call-customer")
    public ResponseEntity<Void> callCustomer() {
        log.info("Received request to call customer");
        try {
            deliveryService.callCustomer();
            log.info("Customer call completed successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to call customer", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
