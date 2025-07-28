package com.example.demo.strategy;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;

public interface PaymentStrategy {
    PaymentResult processPayment(PaymentRequest request);
    boolean supports(PaymentType paymentType);
}