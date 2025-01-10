package com.food.delivery.system.deliveryservice.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAssignment {
    private String deliveryAgentName;
    private Address deliveryAgentCurrentAddress;
    private Long deliveryAgentPhoneNumber;
    private OrderDetails orderDetails;
    private Long deliveryTime;

    public String getDeliveryAgentName() {
        return deliveryAgentName;
    }

    public void setDeliveryAgentName(String deliveryAgentName) {
        this.deliveryAgentName = deliveryAgentName;
    }

    public Address getDeliveryAgentCurrentAddress() {
        return deliveryAgentCurrentAddress;
    }

    public void setDeliveryAgentCurrentAddress(Address deliveryAgentCurrentAddress) {
        this.deliveryAgentCurrentAddress = deliveryAgentCurrentAddress;
    }

    public Long getDeliveryAgentPhoneNumber() {
        return deliveryAgentPhoneNumber;
    }

    public void setDeliveryAgentPhoneNumber(Long deliveryAgentPhoneNumber) {
        this.deliveryAgentPhoneNumber = deliveryAgentPhoneNumber;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
