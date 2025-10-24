package com.upskilling.smartdeploymonitor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// Using simple health check without actuator dependencies

import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller - Demonstrates Client-Server Architecture
 * 
 * This controller provides health check endpoints that clients can use to:
 * - Monitor service availability
 * - Perform load balancer health checks
 * - Implement circuit breaker patterns
 * - Monitor system dependencies
 */
@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "System health and status endpoints")
public class HealthController {

    // Simple health check without external dependencies

    /**
     * Basic health check endpoint
     * Returns simple UP/DOWN status for load balancers
     */
    @GetMapping("/ping")
    @Operation(summary = "Basic health check", description = "Returns simple UP/DOWN status")
    public ResponseEntity<Map<String, String>> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        response.put("service", "smart-deploy-monitor");
        return ResponseEntity.ok(response);
    }

    /**
     * Detailed health check endpoint
     * Returns comprehensive health information including dependencies
     */
    @GetMapping("/detailed")
    @Operation(summary = "Detailed health check", description = "Returns comprehensive health information")
    public ResponseEntity<Map<String, Object>> detailedHealth() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        health.put("service", "smart-deploy-monitor");
        health.put("version", "1.0.0");
        health.put("uptime", System.currentTimeMillis());
        return ResponseEntity.ok(health);
    }

    /**
     * Readiness check endpoint
     * Indicates if the service is ready to accept traffic
     */
    @GetMapping("/ready")
    @Operation(summary = "Readiness check", description = "Indicates if service is ready to accept traffic")
    public ResponseEntity<Map<String, Object>> readiness() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "READY");
        response.put("ready", true);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    /**
     * Liveness check endpoint
     * Indicates if the service is alive and should not be restarted
     */
    @GetMapping("/live")
    @Operation(summary = "Liveness check", description = "Indicates if service is alive")
    public ResponseEntity<Map<String, Object>> liveness() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ALIVE");
        response.put("alive", true);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}
