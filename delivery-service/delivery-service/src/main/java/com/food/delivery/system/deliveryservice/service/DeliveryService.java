package com.food.delivery.system.deliveryservice.service;

public interface DeliveryService {
    String deliverOrder(String orderId);
    void receiveOrderAssignment();
    void callCustomer();
}
