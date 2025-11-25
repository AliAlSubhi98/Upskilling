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

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private static final int MAX_REQUESTS = 100; // requests per minute
    private static final Duration WINDOW_SIZE = Duration.ofMinutes(1);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // Skip rate limiting for Prometheus metrics endpoint
        String requestPath = request.getRequestURI();
        if (requestPath != null && requestPath.startsWith("/actuator/prometheus")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String clientIp = getClientIpAddress(request);
        
        // Redis-based rate limiting only
        if (checkRedisRateLimit(clientIp, response)) {
            return; // Rate limit exceeded
        }
        
        filterChain.doFilter(request, response);
    }
    
    private boolean checkRedisRateLimit(String clientIp, HttpServletResponse response) throws IOException {
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
