package com.upskilling.smartdeploymonitor.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Advanced controller demonstrating more complex features
 * - Database simulation
 * - Caching
 * - External API simulation
 * - Advanced metrics
 */
@RestController
@RequestMapping("/advanced")
public class AdvancedController {

    private static final Logger logger = LoggerFactory.getLogger(AdvancedController.class);
    
    // Simulate database
    private final Map<String, User> userDatabase = new ConcurrentHashMap<>();
    
    // Simulate cache
    private final Map<String, String> cache = new ConcurrentHashMap<>();
    
    // Metrics
    private final Counter databaseQueryCounter;
    private final Counter cacheHitCounter;
    private final Counter cacheMissCounter;
    private final Counter externalApiCounter;
    
    private final Random random = new Random();

    public AdvancedController(MeterRegistry meterRegistry) {
        this.databaseQueryCounter = Counter.builder("advanced.database.queries")
                .description("Number of database queries")
                .register(meterRegistry);
        this.cacheHitCounter = Counter.builder("advanced.cache.hits")
                .description("Number of cache hits")
                .register(meterRegistry);
        this.cacheMissCounter = Counter.builder("advanced.cache.misses")
                .description("Number of cache misses")
                .register(meterRegistry);
        this.externalApiCounter = Counter.builder("advanced.external.api.calls")
                .description("Number of external API calls")
                .register(meterRegistry);
        
        // Initialize with some sample data
        userDatabase.put("user1", new User("user1", "Ali Hamood", "AliHamood@example.com"));
        userDatabase.put("user2", new User("user2", "Nidhal Salim", "NidhalSalim@example.com"));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String userId) {
        logger.info("Fetching user: {}", userId);
        
        // Simulate database query
        databaseQueryCounter.increment();
        
        // Simulate cache check
        String cacheKey = "user:" + userId;
        if (cache.containsKey(cacheKey)) {
            cacheHitCounter.increment();
            logger.info("Cache hit for user: {}", userId);
        } else {
            cacheMissCounter.increment();
            logger.info("Cache miss for user: {}", userId);
            // Simulate cache storage
            cache.put(cacheKey, "cached_user_data");
        }
        
        User user = userDatabase.get(userId);
        if (user == null) {
            logger.warn("User not found: {}", userId);
            return ResponseEntity.notFound().build();
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("timestamp", LocalDateTime.now());
        response.put("cacheStatus", cache.containsKey(cacheKey) ? "HIT" : "MISS");
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        logger.info("Creating new user: {}", user.getEmail());
        
        // Simulate database insert
        databaseQueryCounter.increment();
        
        String userId = "user" + (userDatabase.size() + 1);
        user.setId(userId);
        userDatabase.put(userId, user);
        
        // Clear cache for this user
        cache.remove("user:" + userId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("userId", userId);
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/external-api")
    public ResponseEntity<Map<String, Object>> callExternalApi() {
        logger.info("Calling external API");
        
        externalApiCounter.increment();
        
        // Simulate external API call with random response time
        try {
            Thread.sleep(random.nextInt(500) + 100); // 100-600ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate occasional failures
        if (random.nextInt(10) == 0) {
            logger.error("External API call failed");
            return ResponseEntity.status(503).body(Map.of(
                "error", "External service unavailable",
                "timestamp", LocalDateTime.now()
            ));
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("externalData", Map.of(
            "weather", "Sunny",
            "temperature", random.nextInt(30) + 10,
            "currency", "USD",
            "exchangeRate", 1.0 + random.nextDouble() * 0.1
        ));
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cache/stats")
    public ResponseEntity<Map<String, Object>> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("cacheSize", cache.size());
        stats.put("databaseSize", userDatabase.size());
        stats.put("cacheHits", cacheHitCounter.count());
        stats.put("cacheMisses", cacheMissCounter.count());
        stats.put("databaseQueries", databaseQueryCounter.count());
        stats.put("externalApiCalls", externalApiCounter.count());
        stats.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(stats);
    }

    @DeleteMapping("/cache/clear")
    public ResponseEntity<Map<String, Object>> clearCache() {
        int cacheSize = cache.size();
        cache.clear();
        
        logger.info("Cache cleared. Removed {} entries", cacheSize);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cache cleared successfully");
        response.put("clearedEntries", cacheSize);
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    // Inner class to represent User
    public static class User {
        private String id;
        private String name;
        private String email;

        public User() {}

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
} 