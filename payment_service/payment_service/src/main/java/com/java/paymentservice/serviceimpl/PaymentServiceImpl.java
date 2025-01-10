package com.java.paymentservice.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.java.paymentservice.model.Payment;
import com.java.paymentservice.model.PaymentStatus;
import com.java.paymentservice.repository.PaymentRepository;
import com.java.paymentservice.service.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private final KafkaTemplate<String, List<String>> kafkaTemplate;

    private static final Logger log = LogManager.getLogger(PaymentServiceImpl.class);

    @Override
    public String processPayment(Payment payment) {
        log.info("Processing payment for order ID: {}", payment.getOrderId());
        try {
            // Set payment details
            payment.setTimestamp(LocalDateTime.now());
            payment.setPaymentStatus(PaymentStatus.APPROVED);

            // Save payment to the repository
            payment = paymentRepository.save(payment);
            log.info("Payment saved successfully with ID: {}", payment.getId());

            // Prepare payment info for notification
            List<String> paymentInfo = new ArrayList<>();
            paymentInfo.add(payment.getId());
            paymentInfo.add(payment.getOrderId());
            paymentInfo.add("SUCCESS");

            // Simulate payment processing (e.g., validate credit card or call third-party APIs)
            // TODO: Add actual logic for payment validation and third-party API calls

            // Send payment notification to Kafka
            kafkaTemplate.send("payment-notification-topic", paymentInfo);
            log.info("Payment notification sent to Kafka topic: payment-notification-topic");

            return "Payment was " + payment.getPaymentStatus();
        } catch (Exception e) {
            log.error("Error occurred while processing payment for order ID: {}", payment.getOrderId(), e);
            throw new RuntimeException("Payment processing failed. Please try again later.", e);
        }
    }
}
