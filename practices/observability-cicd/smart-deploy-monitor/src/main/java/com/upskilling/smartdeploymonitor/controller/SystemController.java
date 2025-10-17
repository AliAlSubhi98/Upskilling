package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.service.MonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {

    private final MonitoringService monitoringService;

    public SystemController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/status")
    public Map<String, Object> getSystemStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("timestamp", LocalDateTime.now());
        status.put("status", "OPERATIONAL");
        
        // System information
        Map<String, Object> system = new HashMap<>();
        system.put("uptime_seconds", monitoringService.getUptimeSeconds());
        system.put("cpu_cores", monitoringService.getCpuCores());
        system.put("memory_used_mb", monitoringService.getUsedMemoryMB());
        system.put("memory_free_mb", monitoringService.getFreeMemoryMB());
        system.put("total_requests", monitoringService.getTotalRequests());
        system.put("active_connections", monitoringService.getActiveConnections());
        
        status.put("system", system);
        
        // JVM information
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> jvm = new HashMap<>();
        jvm.put("max_memory_mb", runtime.maxMemory() / 1024.0 / 1024.0);
        jvm.put("total_memory_mb", runtime.totalMemory() / 1024.0 / 1024.0);
        jvm.put("free_memory_mb", runtime.freeMemory() / 1024.0 / 1024.0);
        jvm.put("available_processors", runtime.availableProcessors());
        
        status.put("jvm", jvm);
        
        return status;
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

    @GetMapping("/info")
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("timestamp", LocalDateTime.now());
        info.put("application", "Smart Deploy Monitor");
        info.put("version", "1.0.0");
        info.put("description", "Spring Boot application demonstrating CI/CD and Observability");
        
        // Environment information
        Map<String, Object> environment = new HashMap<>();
        environment.put("java_version", System.getProperty("java.version"));
        environment.put("java_vendor", System.getProperty("java.vendor"));
        environment.put("os_name", System.getProperty("os.name"));
        environment.put("os_version", System.getProperty("os.version"));
        environment.put("os_arch", System.getProperty("os.arch"));
        environment.put("user_timezone", System.getProperty("user.timezone"));
        
        info.put("environment", environment);
        
        return info;
    }
}