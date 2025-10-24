package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.service.domain.UserDomainService;
import com.upskilling.smartdeploymonitor.service.domain.NotificationDomainService;
import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * API Gateway Controller - Demonstrates Monolith vs Microservices Patterns
 * 
 * This controller acts as a centralized entry point that routes requests
 * to appropriate domain services, simulating an API Gateway pattern.
 * 
 * In a microservices architecture, this would be replaced by an actual API Gateway
 * that routes requests to different microservices.
 */
@RestController
@RequestMapping("/api/gateway")
@Tag(name = "API Gateway", description = "Centralized routing and service orchestration")
public class ApiGatewayController {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private NotificationDomainService notificationDomainService;

    /**
     * Route user creation request to User Domain Service
     * Demonstrates API Gateway routing patterns
     */
    @PostMapping("/users")
    @Operation(summary = "Create user via gateway", description = "Routes user creation to User Domain Service")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String password = request.get("password");
            String firstName = request.get("firstName");
            String lastName = request.get("lastName");
            String roleStr = request.get("role");
            
            UserRole role = roleStr != null ? UserRole.valueOf(roleStr.toUpperCase()) : UserRole.USER;
            
            User user = userDomainService.createUser(email, password, firstName, lastName, role);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User created successfully via gateway");
            response.put("userId", user.getId());
            response.put("email", user.getEmail());
            response.put("service", "User Domain Service");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error creating user: " + e.getMessage());
            response.put("service", "User Domain Service");
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Route user listing request to User Domain Service
     * Demonstrates API Gateway routing patterns
     */
    @GetMapping("/users")
    @Operation(summary = "Get users via gateway", description = "Routes user listing to User Domain Service")
    public ResponseEntity<Map<String, Object>> getUsers() {
        try {
            List<User> users = userDomainService.getActiveUsers();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Users retrieved successfully via gateway");
            response.put("users", users);
            response.put("count", users.size());
            response.put("service", "User Domain Service");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error retrieving users: " + e.getMessage());
            response.put("service", "User Domain Service");
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Route notification request to Notification Domain Service
     * Demonstrates API Gateway routing patterns
     */
    @PostMapping("/notifications/email")
    @Operation(summary = "Send email via gateway", description = "Routes email notification to Notification Domain Service")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, String> request) {
        try {
            String to = request.get("to");
            String subject = request.get("subject");
            String body = request.get("body");
            
            notificationDomainService.sendEmailNotification(to, subject, body);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Email notification sent successfully via gateway");
            response.put("service", "Notification Domain Service");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error sending email: " + e.getMessage());
            response.put("service", "Notification Domain Service");
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Route SMS notification request to Notification Domain Service
     * Demonstrates API Gateway routing patterns
     */
    @PostMapping("/notifications/sms")
    @Operation(summary = "Send SMS via gateway", description = "Routes SMS notification to Notification Domain Service")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody Map<String, String> request) {
        try {
            String phoneNumber = request.get("phoneNumber");
            String message = request.get("message");
            
            notificationDomainService.sendSmsNotification(phoneNumber, message);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "SMS notification sent successfully via gateway");
            response.put("service", "Notification Domain Service");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error sending SMS: " + e.getMessage());
            response.put("service", "Notification Domain Service");
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get service status for all domain services
     * Demonstrates API Gateway monitoring capabilities
     */
    @GetMapping("/status")
    @Operation(summary = "Get gateway status", description = "Returns status of all domain services")
    public ResponseEntity<Map<String, Object>> getGatewayStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("gateway", "UP");
        response.put("timestamp", System.currentTimeMillis());
        
        Map<String, String> services = new HashMap<>();
        services.put("User Domain Service", "UP");
        services.put("Notification Domain Service", "UP");
        services.put("Authentication Service", "UP");
        
        response.put("services", services);
        response.put("message", "All domain services are operational");
        
        return ResponseEntity.ok(response);
    }
}
