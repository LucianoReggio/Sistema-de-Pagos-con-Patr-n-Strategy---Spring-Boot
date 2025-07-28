package com.example.demo.strategy;


import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {
    private static final Logger logger = LoggerFactory.getLogger(PayPalPaymentStrategy.class);

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        logger.info("Procesando pago con PayPal por ${}", request.getAmount());

        String email = request.getPaymentDetails().get("email");
        String password = request.getPaymentDetails().get("password");

        if (email == null || password == null) {
            return new PaymentResult(false, null, "Credenciales de PayPal requeridas");
        }

        // Simular autenticación y procesamiento PayPal
        try {
            Thread.sleep(800);
            String transactionId = "PP_" + System.currentTimeMillis();
            return new PaymentResult(true, transactionId, "Pago PayPal completado");
        } catch (InterruptedException e) {
            return new PaymentResult(false, null, "Error en PayPal");
        }
    }

    @Override
    public boolean supports(PaymentType paymentType) {
        return PaymentType.PAYPAL.equals(paymentType);
    }
}