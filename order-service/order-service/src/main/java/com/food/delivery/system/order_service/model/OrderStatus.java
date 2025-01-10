package com.food.delivery.system.order_service.model;

import lombok.ToString;

@ToString
public enum OrderStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
