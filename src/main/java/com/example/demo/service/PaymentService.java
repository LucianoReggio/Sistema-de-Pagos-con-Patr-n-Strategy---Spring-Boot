package com.example.demo.service;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import com.example.demo.strategy.PaymentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final List<PaymentStrategy> paymentStrategies;

    // Spring inyecta automáticamente todas las implementaciones
    public PaymentService(List<PaymentStrategy> paymentStrategies) {
        this.paymentStrategies = paymentStrategies;
    }

    public PaymentResult processPayment(PaymentRequest request) {
        logger.info("Iniciando procesamiento de pago tipo: {}", request.getPaymentType());

        PaymentStrategy strategy = findStrategy(request.getPaymentType());
        if (strategy == null) {
            return new PaymentResult(false, null, "Método de pago no soportado: " + request.getPaymentType());
        }

        return strategy.processPayment(request);
    }

    private PaymentStrategy findStrategy(PaymentType paymentType) {
        return paymentStrategies.stream()
                .filter(strategy -> strategy.supports(paymentType))
                .findFirst()
                .orElse(null);
    }

    public List<PaymentType> getSupportedPaymentTypes() {
        return paymentStrategies.stream()
                .flatMap(strategy -> Arrays.stream(PaymentType.values())
                        .filter(strategy::supports))
                .collect(Collectors.toList());
    }
}
