package com.upskilling.smartdeploymonitor.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * REST endpoints to demonstrate logging, metrics & basic observability.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    private final Counter requestCounter;
    private final Counter errorCounter;
    private final Random random = new Random();

    public DemoController(MeterRegistry meterRegistry) {
        this.requestCounter = Counter.builder("demo.requests.total")
                .description("Total number of demo requests")
                .register(meterRegistry);
        this.errorCounter = Counter.builder("demo.errors.total")
                .description("Total number of demo errors")
                .register(meterRegistry);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        logger.info("Health check requested at {}", LocalDateTime.now());
        requestCounter.increment();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logs")
    public ResponseEntity<Map<String, Object>> generateLogs() {
        int value = random.nextInt(100);
        if (value < 10) {
            logger.error("Error value: {}", value);
            errorCounter.increment();
        } else if (value < 25) {
            logger.warn("Warning value: {}", value);
        } else {
            logger.info("Info value: {}", value);
        }
        requestCounter.increment();
        Map<String, Object> response = new HashMap<>();
        response.put("random", value);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/performance")
    public ResponseEntity<Map<String, Object>> performance() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000) + 100); // 100-1100 ms
        long duration = System.currentTimeMillis() - start;
        logger.info("Performance test completed in {} ms", duration);
        requestCounter.increment();
        Map<String, Object> response = new HashMap<>();
        response.put("duration_ms", duration);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> simulateError() {
        logger.error("Simulated error endpoint hit");
        errorCounter.increment();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Error simulated");
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
