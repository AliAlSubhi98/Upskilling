package com.upskilling.cherrypick;

import java.time.LocalDateTime;

/**
 * Notification Service Feature
 * This will be cherry-picked to main branch
 */
public class NotificationService {
    private String recipient;
    private String message;
    private NotificationType type;
    private LocalDateTime sentAt;
    private boolean isRead;

    public enum NotificationType {
        EMAIL, SMS, PUSH, IN_APP
    }

    public NotificationService(String recipient, String message, NotificationType type) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.sentAt = LocalDateTime.now();
        this.isRead = false;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public boolean sendNotification() {
        // Simulate sending notification
        System.out.println("Sending " + type + " notification to " + recipient);
        System.out.println("Message: " + message);
        return true;
    }
} 