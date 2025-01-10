package com.food.delivery.system.order_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private String id;
    private String name;
    private String address;
}
