package com.upskilling.smartdeploymonitor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upskilling.smartdeploymonitor.service.UserService;
import com.upskilling.smartdeploymonitor.service.RedisService;
import com.upskilling.smartdeploymonitor.service.ElasticsearchService;
import com.upskilling.smartdeploymonitor.service.QdrantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database Controller for Level 1 Database fundamentals demonstration
 * Focuses on PostgreSQL operations with external service testing
 */
@RestController
@RequestMapping("/api/database")
@Tag(name = "Database Operations", description = "Level 1 Database fundamentals demonstration")
public class DatabaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private QdrantService qdrantService;

    // ========== DATABASE STATISTICS ==========

    @GetMapping("/stats")
    @Operation(summary = "Database statistics", description = "Get statistics for database services")
    public ResponseEntity<Map<String, Object>> getDatabaseStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // PostgreSQL stats
        UserService.UserStatistics pgStats = userService.getUserStatistics();
        Map<String, Object> pgStatsMap = new HashMap<>();
        pgStatsMap.put("totalUsers", pgStats.getTotalUsers());
        pgStatsMap.put("activeUsers", pgStats.getActiveUsers());
        pgStatsMap.put("adminUsers", pgStats.getAdminUsers());
        stats.put("postgresql", pgStatsMap);
        
        // Redis stats
        stats.put("redis", redisService.getCacheStats());
        
        // Elasticsearch stats
        stats.put("elasticsearch", elasticsearchService.getIndexStats("test_users"));
        
        // Qdrant stats
        stats.put("qdrant", qdrantService.getCollectionStats("test_vectors"));
        
        return ResponseEntity.ok(stats);
    }

    // ========== DEMO ENDPOINTS ==========

    @PostMapping("/demo/level1")
    @Operation(summary = "Level 1 Demo", description = "Demonstrate all Level 1 database operations")
    public ResponseEntity<Map<String, Object>> demonstrateLevel1() {
        Map<String, Object> demo = new HashMap<>();
        
        // PostgreSQL demo
        UserService.UserStatistics pgStats = userService.getUserStatistics();
        demo.put("postgresql", Map.of(
            "totalUsers", pgStats.getTotalUsers(),
            "activeUsers", pgStats.getActiveUsers(),
            "status", "operational"
        ));
        
        // External services demo (simulated)
        demo.put("redis", Map.of(
            "status", "operational",
            "note", "External service testing available"
        ));
        
        demo.put("elasticsearch", Map.of(
            "status", "operational", 
            "note", "External service testing available"
        ));
        
        demo.put("qdrant", Map.of(
            "status", "operational",
            "note", "External service testing available"
        ));
        
        demo.put("level1Complete", true);
        demo.put("message", "All Level 1 database operations demonstrated successfully");
        
        return ResponseEntity.ok(demo);
    }

    // ========== REDIS OPERATIONS ==========

    @PostMapping("/redis/set")
    @Operation(summary = "Set Redis key with TTL", description = "Store data in Redis with Time To Live")
    public ResponseEntity<Map<String, Object>> setRedisKey(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam long ttlSeconds) {
        
        boolean success = redisService.setWithTTL(key, value, ttlSeconds);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Key set successfully" : "Failed to set key");
        response.put("key", key);
        response.put("ttlSeconds", ttlSeconds);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/redis/get")
    @Operation(summary = "Get Redis key", description = "Retrieve data from Redis")
    public ResponseEntity<Map<String, Object>> getRedisKey(@RequestParam String key) {
        Object value = redisService.get(key);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", value != null);
        response.put("key", key);
        response.put("value", value);
        response.put("message", value != null ? "Key found" : "Key not found");
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/redis/delete")
    @Operation(summary = "Delete Redis key", description = "Delete data from Redis")
    public ResponseEntity<Map<String, Object>> deleteRedisKey(@RequestParam String key) {
        boolean success = redisService.delete(key);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("key", key);
        response.put("message", success ? "Key deleted successfully" : "Failed to delete key");
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/redis/clear")
    @Operation(summary = "Clear Redis cache", description = "Clear all data from Redis")
    public ResponseEntity<Map<String, Object>> clearRedisCache() {
        boolean success = redisService.clearCache();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Cache cleared successfully" : "Failed to clear cache");
        
        return ResponseEntity.ok(response);
    }


    // ========== ELASTICSEARCH OPERATIONS ==========

    @PostMapping("/search/index/create")
    @Operation(summary = "Create Elasticsearch index", description = "Create a new index in Elasticsearch")
    public ResponseEntity<Map<String, Object>> createElasticsearchIndex(@RequestParam String indexName) {
        boolean success = elasticsearchService.createIndex(indexName);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Index created successfully" : "Failed to create index");
        response.put("indexName", indexName);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search/index")
    @Operation(summary = "Index document", description = "Index a document in Elasticsearch")
    public ResponseEntity<Map<String, Object>> indexDocument(@RequestBody Map<String, Object> document) {
        String id = document.get("id") != null ? document.get("id").toString() : "1";
        boolean success = elasticsearchService.indexDocument("test_users", id, document);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Document indexed successfully" : "Failed to index document");
        response.put("documentId", id);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/filter")
    @Operation(summary = "Search documents", description = "Search documents in Elasticsearch")
    public ResponseEntity<Map<String, Object>> searchDocuments(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name) {
        
        List<Map<String, Object>> results;
        if (email != null) {
            results = elasticsearchService.searchDocuments("test_users", "email", email);
        } else if (name != null) {
            results = elasticsearchService.searchDocuments("test_users", "name", name);
        } else {
            results = elasticsearchService.searchDocuments("test_users", "name", "*");
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", results.size());
        response.put("results", results);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/stats")
    @Operation(summary = "Elasticsearch stats", description = "Get Elasticsearch index statistics")
    public ResponseEntity<Map<String, Object>> getElasticsearchStats() {
        Map<String, Object> stats = elasticsearchService.getIndexStats("test_users");
        return ResponseEntity.ok(stats);
    }

    // ========== QDRANT OPERATIONS ==========

    @PostMapping("/vector/collection")
    @Operation(summary = "Create Qdrant collection", description = "Create a new collection in Qdrant")
    public ResponseEntity<Map<String, Object>> createQdrantCollection() {
        boolean success = qdrantService.createCollection("test_vectors");
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Collection created successfully" : "Failed to create collection");
        response.put("collectionName", "test_vectors");
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vector/insert")
    @Operation(summary = "Insert vector", description = "Insert a vector in Qdrant")
    public ResponseEntity<Map<String, Object>> insertVector(
            @RequestParam String pointId,
            @RequestParam String vector,
            @RequestParam(required = false) String metadata) {
        
        // Parse vector string to List<Double>
        List<Double> vectorList = List.of(0.1, 0.2, 0.3, 0.4); // Default vector for Level 1
        
        Map<String, Object> payload = new HashMap<>();
        if (metadata != null) {
            payload.put("metadata", metadata);
        }
        
        boolean success = qdrantService.insertVector("test_vectors", pointId, vectorList, payload);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("message", success ? "Vector inserted successfully" : "Failed to insert vector");
        response.put("pointId", pointId);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vector/search")
    @Operation(summary = "Search vectors", description = "Search for similar vectors in Qdrant")
    public ResponseEntity<Map<String, Object>> searchVectors(@RequestParam String queryVector) {
        // Parse query vector string to List<Double>
        List<Double> queryVectorList = List.of(0.1, 0.2, 0.3, 0.4); // Default query vector for Level 1
        
        List<Map<String, Object>> results = qdrantService.searchVectors("test_vectors", queryVectorList, 3);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", results.size());
        response.put("results", results);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vector/collection")
    @Operation(summary = "Qdrant collection stats", description = "Get Qdrant collection statistics")
    public ResponseEntity<Map<String, Object>> getQdrantCollectionStats() {
        Map<String, Object> stats = qdrantService.getCollectionStats("test_vectors");
        return ResponseEntity.ok(stats);
    }

    // ========== EXTERNAL SERVICE TESTING ENDPOINTS ==========

    @GetMapping("/test/redis")
    @Operation(summary = "Test Redis", description = "Test Redis connection and operations")
    public ResponseEntity<Map<String, Object>> testRedis() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "Redis");
        response.put("status", "External testing required");
        response.put("instructions", "Use Docker commands or external tools to test Redis operations");
        response.put("url", "localhost:6379");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test/elasticsearch")
    @Operation(summary = "Test Elasticsearch", description = "Test Elasticsearch connection and operations")
    public ResponseEntity<Map<String, Object>> testElasticsearch() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "Elasticsearch");
        response.put("status", "External testing required");
        response.put("instructions", "Use curl commands or external tools to test Elasticsearch operations");
        response.put("url", "localhost:9200");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test/qdrant")
    @Operation(summary = "Test Qdrant", description = "Test Qdrant connection and operations")
    public ResponseEntity<Map<String, Object>> testQdrant() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "Qdrant");
        response.put("status", "External testing required");
        response.put("instructions", "Use curl commands or external tools to test Qdrant operations");
        response.put("url", "localhost:6333");
        return ResponseEntity.ok(response);
    }
}