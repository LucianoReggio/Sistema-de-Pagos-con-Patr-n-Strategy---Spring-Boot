package com.example.demo.controller;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import com.example.demo.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResult> processPayment(@RequestBody PaymentRequest request) {
        PaymentResult result = paymentService.processPayment(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/supported-types")
    public ResponseEntity<List<PaymentType>> getSupportedPaymentTypes() {
        return ResponseEntity.ok(paymentService.getSupportedPaymentTypes());
    }
}