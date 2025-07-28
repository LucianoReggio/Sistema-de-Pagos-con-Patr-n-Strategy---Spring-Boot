package com.example.demo.strategy;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BankTransferPaymentStrategy implements PaymentStrategy {
    private static final Logger logger = LoggerFactory.getLogger(BankTransferPaymentStrategy.class);

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        logger.info("Procesando transferencia bancaria por ${}", request.getAmount());

        String accountNumber = request.getPaymentDetails().get("accountNumber");
        String routingNumber = request.getPaymentDetails().get("routingNumber");

        if (accountNumber == null || routingNumber == null) {
            return new PaymentResult(false, null, "Datos bancarios incompletos");
        }

        // Simular transferencia bancaria (más lenta)
        try {
            Thread.sleep(2000);
            String transactionId = "BT_" + System.currentTimeMillis();
            return new PaymentResult(true, transactionId, "Transferencia bancaria iniciada");
        } catch (InterruptedException e) {
            return new PaymentResult(false, null, "Error en transferencia");
        }
    }

    @Override
    public boolean supports(PaymentType paymentType) {
        return PaymentType.BANK_TRANSFER.equals(paymentType);
    }
}