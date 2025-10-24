package com.upskilling.smartdeploymonitor.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Notification Domain Service - Demonstrates Monolith vs Microservices Patterns
 * 
 * This service encapsulates all notification-related business logic and represents
 * a domain boundary that could be extracted into a microservice.
 * 
 * In a microservices architecture, this would become the Notification Service.
 */
@Service
public class NotificationDomainService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Send email notification
     * Demonstrates domain logic for email notifications
     */
    public void sendEmailNotification(String to, String subject, String body) {
        // Business validation
        validateEmailNotification(to, subject, body);
        
        // Create notification payload
        Map<String, Object> notification = new HashMap<>();
        notification.put("id", UUID.randomUUID().toString());
        notification.put("type", "EMAIL");
        notification.put("to", to);
        notification.put("subject", subject);
        notification.put("body", body);
        notification.put("timestamp", LocalDateTime.now());
        notification.put("status", "PENDING");
        
        // In a microservices architecture, this would call the Notification Service
        // For now, we'll simulate the call
        processNotification(notification);
    }

    /**
     * Send SMS notification
     * Demonstrates domain logic for SMS notifications
     */
    public void sendSmsNotification(String phoneNumber, String message) {
        // Business validation
        validateSmsNotification(phoneNumber, message);
        
        // Create notification payload
        Map<String, Object> notification = new HashMap<>();
        notification.put("id", UUID.randomUUID().toString());
        notification.put("type", "SMS");
        notification.put("phoneNumber", phoneNumber);
        notification.put("message", message);
        notification.put("timestamp", LocalDateTime.now());
        notification.put("status", "PENDING");
        
        // In a microservices architecture, this would call the Notification Service
        processNotification(notification);
    }

    /**
     * Send push notification
     * Demonstrates domain logic for push notifications
     */
    public void sendPushNotification(String userId, String title, String body) {
        // Business validation
        validatePushNotification(userId, title, body);
        
        // Create notification payload
        Map<String, Object> notification = new HashMap<>();
        notification.put("id", UUID.randomUUID().toString());
        notification.put("type", "PUSH");
        notification.put("userId", userId);
        notification.put("title", title);
        notification.put("body", body);
        notification.put("timestamp", LocalDateTime.now());
        notification.put("status", "PENDING");
        
        // In a microservices architecture, this would call the Notification Service
        processNotification(notification);
    }

    /**
     * Process notification (simulates microservice call)
     * Demonstrates inter-service communication patterns
     */
    private void processNotification(Map<String, Object> notification) {
        // In a real microservices architecture, this would be:
        // restTemplate.postForObject("http://notification-service/api/notifications", notification, String.class);
        
        // For now, we'll just log the notification
        System.out.println("Processing notification: " + notification);
        
        // Simulate processing delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Business validation for email notifications
     * Demonstrates domain validation logic
     */
    private void validateEmailNotification(String to, String subject, String body) {
        if (to == null || to.trim().isEmpty()) {
            throw new IllegalArgumentException("Email recipient cannot be null or empty");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be null or empty");
        }
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Email body cannot be null or empty");
        }
        if (!to.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    /**
     * Business validation for SMS notifications
     * Demonstrates domain validation logic
     */
    private void validateSmsNotification(String phoneNumber, String message) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("SMS message cannot be null or empty");
        }
        if (phoneNumber.length() < 10) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    /**
     * Business validation for push notifications
     * Demonstrates domain validation logic
     */
    private void validatePushNotification(String userId, String title, String body) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Push notification title cannot be null or empty");
        }
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Push notification body cannot be null or empty");
        }
    }
}
