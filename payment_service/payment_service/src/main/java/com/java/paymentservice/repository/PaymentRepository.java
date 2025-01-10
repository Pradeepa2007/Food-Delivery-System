package com.java.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.java.paymentservice.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String>{

}
