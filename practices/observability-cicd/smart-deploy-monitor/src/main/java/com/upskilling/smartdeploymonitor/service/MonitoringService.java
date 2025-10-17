package com.upskilling.smartdeploymonitor.service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);
    
    private final MeterRegistry meterRegistry;
    private final AtomicLong systemUptime = new AtomicLong(0);
    private final AtomicLong totalRequests = new AtomicLong(0);
    private final AtomicLong activeConnections = new AtomicLong(0);
    
    private long startTime;

    public MonitoringService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.startTime = System.currentTimeMillis();
    }

    @PostConstruct
    public void initializeMetrics() {
        // Register custom metrics using AtomicLong approach
        Gauge.builder("application.requests.total", totalRequests, AtomicLong::get)
                .description("Total application requests")
                .register(meterRegistry);
        
        Gauge.builder("application.connections.active", activeConnections, AtomicLong::get)
                .description("Active connections")
                .register(meterRegistry);
        
        Gauge.builder("system.uptime.seconds", systemUptime, AtomicLong::get)
                .description("System uptime in seconds")
                .register(meterRegistry);
        
        logger.info("Monitoring service initialized with custom metrics");
    }

    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void updateSystemMetrics() {
        systemUptime.set((System.currentTimeMillis() - startTime) / 1000);
        
        // Simulate active connections (random between 5-50)
        activeConnections.set((long) (Math.random() * 45 + 5));
        
        logger.debug("System metrics updated - Uptime: {}s, Memory: {}MB, Active connections: {}", 
                getUptimeSeconds(), getUsedMemoryMB(), activeConnections.get());
    }

    public void incrementRequestCount() {
        totalRequests.incrementAndGet();
    }

    public long getUptimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public double getUsedMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024.0 / 1024.0;
    }

    public double getFreeMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.freeMemory() / 1024.0 / 1024.0;
    }

    public int getCpuCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public long getTotalRequests() {
        return totalRequests.get();
    }

    public long getActiveConnections() {
        return activeConnections.get();
    }

    @Scheduled(fixedRate = 60000) // Every minute
    public void logSystemStatus() {
        logger.info("System Status - Uptime: {}s, Memory: {}/{}MB, CPU Cores: {}, Requests: {}, Connections: {}", 
                getUptimeSeconds(),
                String.format("%.2f", getUsedMemoryMB()),
                String.format("%.2f", getUsedMemoryMB() + getFreeMemoryMB()),
                getCpuCores(),
                getTotalRequests(),
                getActiveConnections());
    }
}