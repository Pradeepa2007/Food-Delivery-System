package com.java.paymentservice.service;

import org.springframework.stereotype.Service;

import com.java.paymentservice.model.Payment;


public interface PaymentService {
	
	 String processPayment(Payment payment);

}
