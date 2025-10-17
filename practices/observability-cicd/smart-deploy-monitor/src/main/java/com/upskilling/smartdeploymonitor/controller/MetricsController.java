package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.service.MonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MonitoringService monitoringService;

    public MetricsController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/system")
    public Map<String, Object> getSystemMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("timestamp", LocalDateTime.now());
        metrics.put("uptime_seconds", monitoringService.getUptimeSeconds());
        metrics.put("memory_used_mb", monitoringService.getUsedMemoryMB());
        metrics.put("memory_free_mb", monitoringService.getFreeMemoryMB());
        metrics.put("cpu_cores", monitoringService.getCpuCores());
        metrics.put("total_requests", monitoringService.getTotalRequests());
        metrics.put("active_connections", monitoringService.getActiveConnections());
        
        // JVM metrics
        Runtime runtime = Runtime.getRuntime();
        metrics.put("jvm_max_memory_mb", runtime.maxMemory() / 1024.0 / 1024.0);
        metrics.put("jvm_total_memory_mb", runtime.totalMemory() / 1024.0 / 1024.0);
        metrics.put("jvm_available_processors", runtime.availableProcessors());
        
        return metrics;
    }

    @GetMapping("/health/detailed")
    public Map<String, Object> getDetailedHealth() {
        Map<String, Object> health = new HashMap<>();
        health.put("timestamp", LocalDateTime.now());
        health.put("status", "UP");
        
        // System health checks
        Map<String, Object> checks = new HashMap<>();
        
        // Memory check
        double memoryUsagePercent = (monitoringService.getUsedMemoryMB() / 
                (monitoringService.getUsedMemoryMB() + monitoringService.getFreeMemoryMB())) * 100;
        checks.put("memory", Map.of(
                "status", memoryUsagePercent < 90 ? "UP" : "DOWN",
                "usage_percent", String.format("%.2f", memoryUsagePercent),
                "used_mb", monitoringService.getUsedMemoryMB(),
                "free_mb", monitoringService.getFreeMemoryMB()
        ));
        
        // Uptime check
        long uptimeHours = monitoringService.getUptimeSeconds() / 3600;
        checks.put("uptime", Map.of(
                "status", "UP",
                "hours", uptimeHours,
                "seconds", monitoringService.getUptimeSeconds()
        ));
        
        // Connection check
        checks.put("connections", Map.of(
                "status", monitoringService.getActiveConnections() < 100 ? "UP" : "WARN",
                "active", monitoringService.getActiveConnections()
        ));
        
        health.put("checks", checks);
        return health;
    }
}