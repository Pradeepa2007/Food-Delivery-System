package com.food.delivery.system.order_service.model;

import com.food.delivery.system.restaurantservice.model.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private BigDecimal totalAmount;
    private LocalDateTime orderTime;
    private Address address;
    private LocalDateTime expectedDeliveryTime;
    private OrderStatus orderStatus;
    private List<FoodItem> foodItems;
    private String restaurantId;
    private String paymentId;
    private String userId;

    public void setStatus(OrderStatus orderStatus) {
    }

    public void setAddress(Address address) {
    }
}
