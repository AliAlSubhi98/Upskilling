package com.upskilling.smartdeploymonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Rate Limiting Configuration
 * Implements rate limiting to prevent abuse and DoS attacks
 */
@Configuration
public class RateLimitingConfig {

    private final RedisTemplate<String, String> redisTemplate;
    
    public RateLimitingConfig(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public OncePerRequestFilter rateLimitingFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String clientIp = getClientIpAddress(request);
                String requestPath = request.getRequestURI();
                
                // Different rate limits for different endpoints
                RateLimitConfig rateLimitConfig = getRateLimitConfig(requestPath);
                
                if (isRateLimited(clientIp, requestPath, rateLimitConfig)) {
                    response.setStatus(429);
                    response.setHeader("Retry-After", String.valueOf(rateLimitConfig.getWindowSeconds()));
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Rate limit exceeded\",\"message\":\"Too many requests. Please try again later.\"}");
                    return;
                }
                
                filterChain.doFilter(request, response);
            }
        };
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

    private RateLimitConfig getRateLimitConfig(String requestPath) {
        // Stricter limits for authentication endpoints
        if (requestPath.contains("/api/auth/")) {
            return new RateLimitConfig(5, 60); // 5 requests per minute
        }
        
        // Moderate limits for API endpoints
        if (requestPath.startsWith("/api/")) {
            return new RateLimitConfig(100, 60); // 100 requests per minute
        }
        
        // Default limits for other endpoints
        return new RateLimitConfig(200, 60); // 200 requests per minute
    }

    private boolean isRateLimited(String clientIp, String requestPath, RateLimitConfig config) {
        String key = "rate_limit:" + clientIp + ":" + requestPath;
        
        try {
            // Get current count
            String currentCountStr = redisTemplate.opsForValue().get(key);
            int currentCount = currentCountStr != null ? Integer.parseInt(currentCountStr) : 0;
            
            if (currentCount >= config.getMaxRequests()) {
                return true;
            }
            
            // Increment counter
            if (currentCount == 0) {
                redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(config.getWindowSeconds()));
            } else {
                redisTemplate.opsForValue().increment(key);
            }
            
            return false;
        } catch (Exception e) {
            // If Redis is not available, allow the request (fail open)
            return false;
        }
    }

    private static class RateLimitConfig {
        private final int maxRequests;
        private final int windowSeconds;
        
        public RateLimitConfig(int maxRequests, int windowSeconds) {
            this.maxRequests = maxRequests;
            this.windowSeconds = windowSeconds;
        }
        
        public int getMaxRequests() {
            return maxRequests;
        }
        
        public int getWindowSeconds() {
            return windowSeconds;
        }
    }
}
