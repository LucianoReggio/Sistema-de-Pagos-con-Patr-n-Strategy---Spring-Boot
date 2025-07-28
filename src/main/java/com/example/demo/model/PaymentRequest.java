package com.example.demo.model;

import java.util.Map;

public class PaymentRequest {
    private double amount;
    private String currency;
    private PaymentType paymentType;
    private Map<String, String> paymentDetails;

    // Constructores
    public PaymentRequest() {}

    public PaymentRequest(double amount, String currency, PaymentType paymentType, Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.paymentType = paymentType;
        this.paymentDetails = paymentDetails;
    }

    // Getters y Setters
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public PaymentType getPaymentType() { return paymentType; }
    public void setPaymentType(PaymentType paymentType) { this.paymentType = paymentType; }

    public Map<String, String> getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(Map<String, String> paymentDetails) { this.paymentDetails = paymentDetails; }
}