package com.food.delivery.system.order_service.dto;

import com.food.delivery.system.order_service.model.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private BigDecimal totalAmount;
    private LocalDateTime orderTime;
    private String address;
    private LocalDateTime expectedDeliveryTime;
    private OrderStatus orderStatus;
    private List<String> foodItems;
    private String restaurantId;
    private String paymentId;
    private String userId;

}
