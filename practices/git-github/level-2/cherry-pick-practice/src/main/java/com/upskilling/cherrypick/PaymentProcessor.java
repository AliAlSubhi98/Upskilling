package com.upskilling.cherrypick;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment Processing Feature
 * This will be cherry-picked to main branch
 */
public class PaymentProcessor {
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime processedAt;
    private PaymentStatus status;

    public enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }

    public PaymentProcessor(String transactionId, BigDecimal amount, String currency) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.processedAt = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public boolean processPayment() {
        // Simulate payment processing
        this.status = PaymentStatus.PROCESSING;
        // In real app, this would call payment gateway
        this.status = PaymentStatus.COMPLETED;
        return true;
    }
} 