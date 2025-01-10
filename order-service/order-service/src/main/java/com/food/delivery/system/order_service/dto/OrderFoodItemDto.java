package com.food.delivery.system.order_service.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderFoodItemDto {
    private String id;
    private String name;
    private BigDecimal price;
    private String restaurantId;
    private Integer quantity;
   
}
