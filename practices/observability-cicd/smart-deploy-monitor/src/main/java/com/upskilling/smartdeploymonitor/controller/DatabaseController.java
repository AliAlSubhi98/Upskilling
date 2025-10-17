package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.service.CacheService;
import com.upskilling.smartdeploymonitor.service.SearchService;
import com.upskilling.smartdeploymonitor.service.VectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Controller demonstrating Level 1 Database fundamentals:
 * - Redis TTL-based caching
 * - Elasticsearch search/filter operations
 * - Qdrant vector insert/search operations
 */
@RestController
@RequestMapping("/api/database")
@Tag(name = "Database Operations", description = "Level 1 Database fundamentals demonstration")
public class DatabaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private VectorService vectorService;

    // ========== REDIS CACHING OPERATIONS ==========

    @PostMapping("/cache/set")
    @Operation(summary = "Set cache with TTL", description = "Store data in Redis with Time To Live")
    public ResponseEntity<Map<String, Object>> setCache(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(defaultValue = "300") long ttlSeconds) {
        
        cacheService.setWithTTL(key, value, ttlSeconds);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cache set successfully");
        response.put("key", key);
        response.put("ttl", ttlSeconds);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cache/get")
    @Operation(summary = "Get from cache", description = "Retrieve data from Redis cache")
    public ResponseEntity<Map<String, Object>> getCache(@RequestParam String key) {
        Optional<Object> value = cacheService.get(key);
        
        Map<String, Object> response = new HashMap<>();
        if (value.isPresent()) {
            response.put("found", true);
            response.put("value", value.get());
            response.put("ttl", cacheService.getTTL(key));
        } else {
            response.put("found", false);
            response.put("message", "Key not found or expired");
        }
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cache/user")
    @Operation(summary = "Cache user data", description = "Cache user information with TTL")
    public ResponseEntity<Map<String, Object>> cacheUser(
            @RequestParam String userId,
            @RequestParam String userData,
            @RequestParam(defaultValue = "10") long ttlMinutes) {
        
        cacheService.cacheUser(userId, userData, ttlMinutes);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User cached successfully");
        response.put("userId", userId);
        response.put("ttlMinutes", ttlMinutes);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cache/user/{userId}")
    @Operation(summary = "Get cached user", description = "Retrieve cached user data")
    public ResponseEntity<Map<String, Object>> getCachedUser(@PathVariable String userId) {
        Optional<Object> userData = cacheService.getCachedUser(userId);
        
        Map<String, Object> response = new HashMap<>();
        if (userData.isPresent()) {
            response.put("found", true);
            response.put("userData", userData.get());
        } else {
            response.put("found", false);
            response.put("message", "User not found in cache or expired");
        }
        
        return ResponseEntity.ok(response);
    }

    // ========== ELASTICSEARCH OPERATIONS ==========

    @GetMapping("/search/filters")
    @Operation(summary = "Search with filters", description = "Use Elasticsearch filters for exact matches")
    public ResponseEntity<Map<String, Object>> searchWithFilters(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean isActive) {
        
        List<Map<String, Object>> results = searchService.searchUsersWithFilters(email, isActive);
        
        Map<String, Object> response = new HashMap<>();
        response.put("method", "Elasticsearch Filters");
        response.put("description", "Exact matches, cached, faster performance");
        response.put("results", results);
        response.put("count", results.size());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/queries")
    @Operation(summary = "Search with queries", description = "Use Elasticsearch queries for relevance scoring")
    public ResponseEntity<Map<String, Object>> searchWithQueries(@RequestParam String searchText) {
        List<Map<String, Object>> results = searchService.searchUsersWithQueries(searchText);
        
        Map<String, Object> response = new HashMap<>();
        response.put("method", "Elasticsearch Queries");
        response.put("description", "Relevance scoring, full-text search");
        response.put("searchText", searchText);
        response.put("results", results);
        response.put("count", results.size());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/combined")
    @Operation(summary = "Combined search", description = "Combine filters and queries for optimal performance")
    public ResponseEntity<Map<String, Object>> searchCombined(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean isActive) {
        
        List<Map<String, Object>> results = searchService.searchUsersCombined(searchText, role, isActive);
        
        Map<String, Object> response = new HashMap<>();
        response.put("method", "Combined Filters + Queries");
        response.put("description", "Filters for exact matches, queries for relevance");
        response.put("filters", Map.of("role", role, "isActive", isActive));
        response.put("query", searchText);
        response.put("results", results);
        response.put("count", results.size());
        
        return ResponseEntity.ok(response);
    }

    // ========== QDRANT VECTOR OPERATIONS ==========

    @PostMapping("/vector/collection")
    @Operation(summary = "Create vector collection", description = "Create Qdrant collection for vector storage")
    public ResponseEntity<Map<String, Object>> createVectorCollection() {
        boolean success = vectorService.createCollection();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Collection created successfully" : "Failed to create collection");
        response.put("collectionInfo", vectorService.getCollectionInfo());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vector/insert")
    @Operation(summary = "Insert vector", description = "Insert vector data into Qdrant")
    public ResponseEntity<Map<String, Object>> insertVector(
            @RequestParam String pointId,
            @RequestParam List<Float> vector,
            @RequestParam(required = false) String metadata) {
        
        Map<String, Object> payload = new HashMap<>();
        payload.put("metadata", metadata != null ? metadata : "default");
        payload.put("timestamp", System.currentTimeMillis());
        
        boolean success = vectorService.insertVector(pointId, vector, payload);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Vector inserted successfully" : "Failed to insert vector");
        response.put("pointId", pointId);
        response.put("vectorSize", vector.size());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vector/search")
    @Operation(summary = "Search vectors", description = "Search similar vectors in Qdrant")
    public ResponseEntity<Map<String, Object>> searchVectors(
            @RequestParam List<Float> queryVector,
            @RequestParam(defaultValue = "5") int limit) {
        
        List<Map<String, Object>> results = vectorService.searchVectors(queryVector, limit);
        
        Map<String, Object> response = new HashMap<>();
        response.put("method", "Qdrant Vector Search");
        response.put("description", "Similarity search using cosine distance");
        response.put("queryVectorSize", queryVector.size());
        response.put("limit", limit);
        response.put("results", results);
        response.put("count", results.size());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vector/search-filtered")
    @Operation(summary = "Search vectors with filter", description = "Search vectors with metadata filtering")
    public ResponseEntity<Map<String, Object>> searchVectorsWithFilter(
            @RequestParam List<Float> queryVector,
            @RequestParam String filterKey,
            @RequestParam String filterValue,
            @RequestParam(defaultValue = "5") int limit) {
        
        Map<String, Object> filter = new HashMap<>();
        filter.put(filterKey, filterValue);
        
        List<Map<String, Object>> results = vectorService.searchVectorsWithFilter(queryVector, filter, limit);
        
        Map<String, Object> response = new HashMap<>();
        response.put("method", "Qdrant Vector Search with Filter");
        response.put("description", "Similarity search with metadata filtering");
        response.put("queryVectorSize", queryVector.size());
        response.put("filter", filter);
        response.put("limit", limit);
        response.put("results", results);
        response.put("count", results.size());
        
        return ResponseEntity.ok(response);
    }

    // ========== DATABASE STATISTICS ==========

    @GetMapping("/stats")
    @Operation(summary = "Database statistics", description = "Get statistics for all database services")
    public ResponseEntity<Map<String, Object>> getDatabaseStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("redis", cacheService.getCacheStats());
        stats.put("elasticsearch", searchService.getSearchStats());
        stats.put("qdrant", vectorService.getCollectionInfo());
        
        return ResponseEntity.ok(stats);
    }

    // ========== DEMO ENDPOINTS ==========

    @PostMapping("/demo/level1")
    @Operation(summary = "Level 1 Demo", description = "Demonstrate all Level 1 database operations")
    public ResponseEntity<Map<String, Object>> demonstrateLevel1() {
        Map<String, Object> demo = new HashMap<>();
        
        // Redis TTL-based caching demo
        cacheService.setWithTTL("demo:key", "demo:value", 60);
        Optional<Object> cachedValue = cacheService.get("demo:key");
        demo.put("redis", Map.of(
            "cached", cachedValue.isPresent(),
            "value", cachedValue.orElse("not found")
        ));
        
        // Elasticsearch search demo
        List<Map<String, Object>> searchResults = searchService.searchUsersWithFilters("demo@example.com", true);
        demo.put("elasticsearch", Map.of(
            "searchResults", searchResults.size(),
            "status", "operational"
        ));
        
        // Qdrant vector demo
        List<Float> demoVector = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            demoVector.add((float) Math.random());
        }
        boolean vectorInserted = vectorService.insertVector("demo:point", demoVector, Map.of("type", "demo"));
        demo.put("qdrant", Map.of(
            "vectorInserted", vectorInserted,
            "vectorSize", demoVector.size()
        ));
        
        demo.put("level1Complete", true);
        demo.put("message", "All Level 1 database operations demonstrated successfully");
        
        return ResponseEntity.ok(demo);
    }
}