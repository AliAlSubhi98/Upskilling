package com.upskilling.smartdeploymonitor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired(required = false)
    private RedisTemplate<String, String> redisTemplate;

    // In-memory rate limiting as fallback
    private final ConcurrentHashMap<String, RequestCounter> inMemoryCounters = new ConcurrentHashMap<>();
    
    private static final int MAX_REQUESTS = 100; // requests per minute
    private static final Duration WINDOW_SIZE = Duration.ofMinutes(1);
    
    // Request counter for in-memory rate limiting
    private static class RequestCounter {
        private int count;
        private LocalDateTime windowStart;
        
        public RequestCounter() {
            this.count = 1;
            this.windowStart = LocalDateTime.now();
        }
        
        public boolean isExpired() {
            return LocalDateTime.now().isAfter(windowStart.plus(WINDOW_SIZE));
        }
        
        public void increment() {
            this.count++;
        }
        
        public int getCount() {
            return count;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String clientIp = getClientIpAddress(request);
        
        // Try Redis first, fallback to in-memory
        if (redisTemplate != null) {
            if (checkRedisRateLimit(clientIp, response)) {
                return; // Rate limit exceeded
            }
        } else {
            if (checkInMemoryRateLimit(clientIp, response)) {
                return; // Rate limit exceeded
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private boolean checkRedisRateLimit(String clientIp, HttpServletResponse response) throws IOException {
        try {
            String key = "rate_limit:" + clientIp;
            String currentCount = redisTemplate.opsForValue().get(key);
            
            if (currentCount == null) {
                // First request in the window
                redisTemplate.opsForValue().set(key, "1", WINDOW_SIZE);
            } else {
                int count = Integer.parseInt(currentCount);
                if (count >= MAX_REQUESTS) {
                    // Rate limit exceeded
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Rate limit exceeded. Try again later.\"}");
                    return true;
                } else {
                    // Increment counter
                    redisTemplate.opsForValue().increment(key);
                }
            }
        } catch (Exception e) {
            logger.warn("Redis rate limiting failed, falling back to in-memory: " + e.getMessage());
            return checkInMemoryRateLimit(clientIp, response);
        }
        return false;
    }
    
    private boolean checkInMemoryRateLimit(String clientIp, HttpServletResponse response) throws IOException {
        // Clean up expired counters
        inMemoryCounters.entrySet().removeIf(entry -> entry.getValue().isExpired());
        
        RequestCounter counter = inMemoryCounters.computeIfAbsent(clientIp, k -> new RequestCounter());
        
        if (counter.isExpired()) {
            // Reset counter for new window
            counter = new RequestCounter();
            inMemoryCounters.put(clientIp, counter);
        } else {
            counter.increment();
        }
        
        if (counter.getCount() > MAX_REQUESTS) {
            // Rate limit exceeded
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Rate limit exceeded. Try again later.\"}");
            return true;
        }
        
        return false;
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }
}
