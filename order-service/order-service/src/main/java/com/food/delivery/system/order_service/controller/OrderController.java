package com.food.delivery.system.order_service.controller;


import com.food.delivery.system.order_service.dto.OrderDto;
import com.food.delivery.system.order_service.dto.OrderFoodItemDto;
import com.food.delivery.system.order_service.model.OrderStatus;
import com.food.delivery.system.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private static final Logger log = LogManager.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestParam String restaurantName, @RequestBody List<OrderFoodItemDto> foodItems) {
        try {
            log.info("Received request to create order for restaurant: {}", restaurantName);
            OrderDto orderDto = orderService.createOrder(restaurantName, foodItems);
            return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating order for restaurant: {}", restaurantName, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderDetails(@PathVariable String orderId) {
        try {
            log.info("Received request to fetch order details for orderId: {}", orderId);
            OrderDto orderDto = orderService.getOrderDetails(orderId);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching order details for orderId: {}", orderId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        try {
            log.info("Received request to update order status for orderId: {}", orderId);
            orderService.updateOrderStatus(orderId, status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error updating order status for orderId: {}", orderId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
