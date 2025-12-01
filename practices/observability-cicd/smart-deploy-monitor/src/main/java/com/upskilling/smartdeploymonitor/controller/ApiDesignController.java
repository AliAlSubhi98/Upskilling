package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * API Design Controller - Demonstrates Basic API Design Patterns
 * 
 * Shows essential API design principles:
 * - Consistent response format
 * - Proper HTTP status codes
 * - Request validation
 * - Error handling
 */
@RestController
@RequestMapping("/api/design")
@Tag(name = "API Design", description = "Basic API design patterns demonstration")
public class ApiDesignController {

    /**
     * GET endpoint - Demonstrates proper GET response
     */
    @GetMapping("/info")
    @Operation(summary = "Get API info", description = "Returns basic API information")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Smart Deploy Monitor API");
        info.put("version", "1.0.0");
        info.put("status", "operational");
        
        return ResponseEntity.ok(ApiResponse.success("API information retrieved", info));
    }

    /**
     * POST endpoint - Demonstrates request validation and response
     */
    @PostMapping("/contact")
    @Operation(summary = "Send contact message", description = "Accepts and processes contact form data")
    public ResponseEntity<ApiResponse<String>> sendContact(@Valid @RequestBody ContactRequest request) {
        // Simulate processing
        String response = String.format("Message from %s (%s): %s", 
            request.getName(), request.getEmail(), request.getMessage());
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Contact message received", response));
    }

    /**
     * PUT endpoint - Demonstrates update operation
     */
    @PutMapping("/settings/{id}")
    @Operation(summary = "Update settings", description = "Updates application settings")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateSettings(
            @PathVariable String id,
            @Valid @RequestBody SettingsRequest request) {
        
        Map<String, Object> settings = new HashMap<>();
        settings.put("id", id);
        settings.put("theme", request.getTheme());
        settings.put("notifications", request.isNotifications());
        settings.put("updated", System.currentTimeMillis());
        
        return ResponseEntity.ok(ApiResponse.success("Settings updated", settings));
    }

    /**
     * DELETE endpoint - Demonstrates delete operation
     */
    @DeleteMapping("/data/{id}")
    @Operation(summary = "Delete data", description = "Deletes specified data")
    public ResponseEntity<ApiResponse<String>> deleteData(@PathVariable String id) {
        // Simulate deletion
        return ResponseEntity.ok(ApiResponse.success("Data deleted successfully", "ID: " + id));
    }

    /**
     * Error handling - Demonstrates proper error responses
     */
    @GetMapping("/error-demo")
    @Operation(summary = "Error demonstration", description = "Shows how errors are handled")
    public ResponseEntity<ApiResponse<String>> errorDemo() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error("This is a demonstration of error handling"));
    }

    // Request DTOs
    public static class ContactRequest {
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        private String name;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;
        
        @NotBlank(message = "Message is required")
        @Size(min = 10, max = 500, message = "Message must be between 10 and 500 characters")
        private String message;

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    public static class SettingsRequest {
        @NotBlank(message = "Theme is required")
        private String theme;
        private boolean notifications;

        // Getters and Setters
        public String getTheme() { return theme; }
        public void setTheme(String theme) { this.theme = theme; }
        public boolean isNotifications() { return notifications; }
        public void setNotifications(boolean notifications) { this.notifications = notifications; }
    }
}
