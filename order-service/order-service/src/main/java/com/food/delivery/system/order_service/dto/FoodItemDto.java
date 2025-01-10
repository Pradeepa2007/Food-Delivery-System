package com.food.delivery.system.order_service.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemDto {
	    private String id;
	    private String name;
	    private String description;
	    private BigDecimal price;
	    private Integer quantity;
	    private String restaurantId;

   
}
