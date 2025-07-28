package com.example.demo.strategy;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// 4. Implementaciones concretas del Strategy
@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {
    private static final Logger logger = LoggerFactory.getLogger(CreditCardPaymentStrategy.class);

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        logger.info("Procesando pago con tarjeta de crédito por ${}", request.getAmount());

        // Validar datos de tarjeta
        String cardNumber = request.getPaymentDetails().get("cardNumber");
        String cvv = request.getPaymentDetails().get("cvv");
        String expiryDate = request.getPaymentDetails().get("expiryDate");

        if (cardNumber == null || cvv == null || expiryDate == null) {
            return new PaymentResult(false, null, "Datos de tarjeta incompletos");
        }

        // Simular procesamiento
        try {
            Thread.sleep(1000); // Simular llamada a API externa
            String transactionId = "CC_" + System.currentTimeMillis();
            return new PaymentResult(true, transactionId, "Pago procesado exitosamente");
        } catch (InterruptedException e) {
            return new PaymentResult(false, null, "Error en el procesamiento");
        }
    }

    @Override
    public boolean supports(PaymentType paymentType) {
        return PaymentType.CREDIT_CARD.equals(paymentType);
    }
}
