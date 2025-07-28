package com.example.demo;

import com.example.demo.model.PaymentRequest;
import com.example.demo.model.PaymentResult;
import com.example.demo.model.PaymentType;
import com.example.demo.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@SpringBootTest(classes = PaymentApplication.class)
public class PaymentServiceTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	public void testCreditCardPayment() {
		Map<String, String> details = new HashMap<>();
		details.put("cardNumber", "4111111111111111");
		details.put("cvv", "123");
		details.put("expiryDate", "12/25");

		PaymentRequest request = new PaymentRequest(100.0, "USD", PaymentType.CREDIT_CARD, details);
		PaymentResult result = paymentService.processPayment(request);

		assertTrue(result.isSuccess());
		assertNotNull(result.getTransactionId());
		assertTrue(result.getTransactionId().startsWith("CC_"));
	}

	@Test
	public void testPayPalPayment() {
		Map<String, String> details = new HashMap<>();
		details.put("email", "user@example.com");
		details.put("password", "password123");

		PaymentRequest request = new PaymentRequest(50.0, "USD", PaymentType.PAYPAL, details);
		PaymentResult result = paymentService.processPayment(request);

		assertTrue(result.isSuccess());
		assertNotNull(result.getTransactionId());
		assertTrue(result.getTransactionId().startsWith("PP_"));
	}
}