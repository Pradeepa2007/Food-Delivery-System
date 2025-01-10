package com.java.paymentservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.paymentservice.model.Payment;
import com.java.paymentservice.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentServiceController {

	@Autowired
	private PaymentService paymentService;

	private static final Logger log = LogManager.getLogger(PaymentServiceController.class);

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> pay(@RequestBody Payment payment) {
		log.info("Received payment request for order ID: {}", payment.getOrderId());
		try {
			String response = paymentService.processPayment(payment);
			log.info("Payment processed successfully for order ID: {}", payment.getOrderId());
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			log.error("Payment processing failed for order ID: {}", payment.getOrderId(), e);
			return new ResponseEntity<>("Payment processing failed. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
