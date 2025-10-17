package com.upskilling.smartdeploymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Service for Redis TTL-based caching operations
 * Demonstrates Level 1 Database fundamentals: Redis commands and TTL-based caching
 */
@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Store data in Redis with TTL (Time To Live)
     * @param key Cache key
     * @param value Value to cache
     * @param ttlSeconds TTL in seconds
     */
    public void setWithTTL(String key, Object value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    /**
     * Get data from Redis cache
     * @param key Cache key
     * @return Cached value or null if not found/expired
     */
    public Optional<Object> get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value);
    }

    /**
     * Check if key exists in cache
     * @param key Cache key
     * @return true if key exists and not expired
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * Get TTL for a key
     * @param key Cache key
     * @return TTL in seconds, -1 if key doesn't exist, -2 if key has no expiration
     */
    public long getTTL(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * Delete key from cache
     * @param key Cache key
     * @return true if key was deleted
     */
    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * Cache user data with TTL
     * @param userId User ID
     * @param userData User data to cache
     * @param ttlMinutes TTL in minutes
     */
    public void cacheUser(String userId, Object userData, long ttlMinutes) {
        String key = "user:" + userId;
        setWithTTL(key, userData, ttlMinutes * 60);
    }

    /**
     * Get cached user data
     * @param userId User ID
     * @return Cached user data or empty if not found/expired
     */
    public Optional<Object> getCachedUser(String userId) {
        String key = "user:" + userId;
        return get(key);
    }

    /**
     * Cache API response with TTL
     * @param endpoint API endpoint
     * @param response Response data
     * @param ttlMinutes TTL in minutes
     */
    public void cacheApiResponse(String endpoint, Object response, long ttlMinutes) {
        String key = "api:" + endpoint;
        setWithTTL(key, response, ttlMinutes * 60);
    }

    /**
     * Get cached API response
     * @param endpoint API endpoint
     * @return Cached response or empty if not found/expired
     */
    public Optional<Object> getCachedApiResponse(String endpoint) {
        String key = "api:" + endpoint;
        return get(key);
    }

    /**
     * Clear all cache entries
     */
    public void clearAll() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    /**
     * Get cache statistics
     * @return Cache statistics information
     */
    public String getCacheStats() {
        return String.format("Cache service active. Redis connection: %s", 
            redisTemplate.getConnectionFactory() != null ? "Connected" : "Disconnected");
    }
}