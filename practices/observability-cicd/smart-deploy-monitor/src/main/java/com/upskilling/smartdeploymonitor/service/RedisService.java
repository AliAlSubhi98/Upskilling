package com.upskilling.smartdeploymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Redis Service for TTL-based caching operations
 * Demonstrates Level 1 Database fundamentals: Redis TTL-based caching
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Set a key-value pair with TTL (Time To Live)
     * @param key Key to store
     * @param value Value to store
     * @param ttlSeconds TTL in seconds
     * @return true if successful
     */
    public boolean setWithTTL(String key, Object value, long ttlSeconds) {
        try {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
            return true;
        } catch (Exception e) {
            System.err.println("Error setting Redis key: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get a value by key
     * @param key Key to retrieve
     * @return Value or null if not found
     */
    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            System.err.println("Error getting Redis key: " + e.getMessage());
            return null;
        }
    }

    /**
     * Delete a key
     * @param key Key to delete
     * @return true if successful
     */
    public boolean delete(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        } catch (Exception e) {
            System.err.println("Error deleting Redis key: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clear all cache
     * @return true if successful
     */
    public boolean clearCache() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error clearing Redis cache: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get cache statistics
     * @return Map with cache statistics
     */
    public Map<String, Object> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        try {
            Set<String> keys = redisTemplate.keys("*");
            stats.put("totalKeys", keys != null ? keys.size() : 0);
            stats.put("status", "connected");
            stats.put("host", "localhost:6379");
        } catch (Exception e) {
            stats.put("status", "error");
            stats.put("error", e.getMessage());
        }
        return stats;
    }
}