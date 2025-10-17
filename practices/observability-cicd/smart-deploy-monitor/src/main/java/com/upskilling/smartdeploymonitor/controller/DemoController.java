package com.upskilling.smartdeploymonitor.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    private final Counter requestCounter;
    private final Counter errorCounter;
    private final Timer responseTimer;
    private final Random random = new Random();

    public DemoController(MeterRegistry meterRegistry) {
        this.requestCounter = Counter.builder("demo.requests.total")
                .description("Total number of demo requests")
                .register(meterRegistry);
        this.errorCounter = Counter.builder("demo.errors.total")
                .description("Total number of demo errors")
                .register(meterRegistry);
        this.responseTimer = Timer.builder("demo.response.time")
                .description("Response time for demo endpoints")
                .register(meterRegistry);
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Timer.Sample sample = Timer.start();
        try {
            logger.info("Health check requested at {}", LocalDateTime.now());
            requestCounter.increment();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "UP");
            response.put("timestamp", LocalDateTime.now());
            response.put("message", "Smart Deploy Monitor is running!");
            response.put("uptime", System.currentTimeMillis());
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Timer.Sample sample = Timer.start();
        try {
            logger.info("Info endpoint requested");
            requestCounter.increment();
            
            Map<String, Object> response = new HashMap<>();
            response.put("application", "Smart Deploy Monitor");
            response.put("version", "1.0.0");
            response.put("description", "Spring Boot application demonstrating CI/CD and Observability");
            response.put("timestamp", LocalDateTime.now());
            response.put("java.version", System.getProperty("java.version"));
            response.put("os.name", System.getProperty("os.name"));
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }

    @GetMapping("/logs")
    public Map<String, Object> generateLogs() {
        Timer.Sample sample = Timer.start();
        try {
            requestCounter.increment();
            int value = random.nextInt(100);
            
            if (value < 10) {
                logger.error("Error value generated: {}", value);
                errorCounter.increment();
            } else if (value < 25) {
                logger.warn("Warning value generated: {}", value);
            } else {
                logger.info("Info value generated: {}", value);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("random_value", value);
            response.put("log_level", value < 10 ? "ERROR" : value < 25 ? "WARN" : "INFO");
            response.put("timestamp", LocalDateTime.now());
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }

    @GetMapping("/performance")
    public Map<String, Object> performance() throws InterruptedException {
        Timer.Sample sample = Timer.start();
        try {
            requestCounter.increment();
            long start = System.currentTimeMillis();
            
            // Simulate some processing time
            Thread.sleep(random.nextInt(1000) + 100); // 100-1100 ms
            
            long duration = System.currentTimeMillis() - start;
            logger.info("Performance test completed in {} ms", duration);
            
            Map<String, Object> response = new HashMap<>();
            response.put("duration_ms", duration);
            response.put("timestamp", LocalDateTime.now());
            response.put("memory_used_mb", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }

    @GetMapping("/error")
    public Map<String, Object> simulateError() {
        Timer.Sample sample = Timer.start();
        try {
            logger.error("Simulated error endpoint hit at {}", LocalDateTime.now());
            errorCounter.increment();
            requestCounter.increment();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error simulated successfully");
            response.put("timestamp", LocalDateTime.now());
            response.put("error_code", "DEMO_ERROR_001");
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }

    @PostMapping("/data")
    public Map<String, Object> processData(@RequestBody Map<String, Object> data) {
        Timer.Sample sample = Timer.start();
        try {
            logger.info("Processing data: {}", data);
            requestCounter.increment();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "processed");
            response.put("received_data", data);
            response.put("processed_at", LocalDateTime.now());
            response.put("data_size", data.toString().length());
            return response;
        } finally {
            sample.stop(responseTimer);
        }
    }
}