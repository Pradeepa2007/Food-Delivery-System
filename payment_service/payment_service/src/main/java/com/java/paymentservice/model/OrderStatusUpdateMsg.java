package com.java.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class OrderStatusUpdateMsg {
	    private String paymentId;
	    private PaymentStatus paymentStatus;
}
