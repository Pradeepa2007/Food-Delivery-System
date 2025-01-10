package com.food.delivery.system.order_service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
	private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    
	@Override
	public String toString() {
		return "FoodItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
