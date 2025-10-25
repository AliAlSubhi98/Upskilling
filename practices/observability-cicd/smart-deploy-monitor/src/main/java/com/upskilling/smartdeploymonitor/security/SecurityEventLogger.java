package com.upskilling.smartdeploymonitor.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Security Event Logger
 * Logs security-related events for monitoring and analysis
 */
@Component
public class SecurityEventLogger extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityEventLogger.class);
    private static final Logger securityLogger = LoggerFactory.getLogger("SECURITY");

    // Suspicious patterns to monitor
    private static final List<String> SUSPICIOUS_PATTERNS = Arrays.asList(
        "union", "select", "insert", "update", "delete", "drop", "create", "alter",
        "script", "javascript", "vbscript", "onload", "onerror", "onclick",
        "../", "..\\", "%2e%2e%2f", "%2e%2e%5c",
        "admin", "root", "administrator", "test", "guest"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        
        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logSecurityEvent(request, response, duration);
        }
    }

    private void logSecurityEvent(HttpServletRequest request, HttpServletResponse response, long duration) {
        String clientIp = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        int statusCode = response.getStatus();
        
        // Check for suspicious activity
        boolean isSuspicious = isSuspiciousRequest(request);
        
        // Log security event
        SecurityEvent event = new SecurityEvent(
            LocalDateTime.now(),
            clientIp,
            userAgent,
            method,
            requestUri,
            statusCode,
            duration,
            isSuspicious
        );
        
        if (isSuspicious) {
            securityLogger.warn("SUSPICIOUS_ACTIVITY: {}", event);
        } else if (statusCode >= 400) {
            securityLogger.info("HTTP_ERROR: {}", event);
        } else {
            securityLogger.debug("REQUEST: {}", event);
        }
        
        // Log authentication events
        if (requestUri.contains("/api/auth/")) {
            logAuthenticationEvent(request, response, event);
        }
        
        // Log admin access
        if (requestUri.contains("/admin") || requestUri.contains("/actuator")) {
            securityLogger.info("ADMIN_ACCESS: {}", event);
        }
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

    private boolean isSuspiciousRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI().toLowerCase();
        String queryString = request.getQueryString();
        String userAgent = request.getHeader("User-Agent");
        
        // Check URI for suspicious patterns
        for (String pattern : SUSPICIOUS_PATTERNS) {
            if (requestUri.contains(pattern.toLowerCase())) {
                return true;
            }
        }
        
        // Check query string
        if (queryString != null) {
            for (String pattern : SUSPICIOUS_PATTERNS) {
                if (queryString.toLowerCase().contains(pattern.toLowerCase())) {
                    return true;
                }
            }
        }
        
        // Check for suspicious user agents
        if (userAgent != null) {
            String lowerUserAgent = userAgent.toLowerCase();
            if (lowerUserAgent.contains("sqlmap") || 
                lowerUserAgent.contains("nikto") || 
                lowerUserAgent.contains("nmap") ||
                lowerUserAgent.contains("scanner")) {
                return true;
            }
        }
        
        return false;
    }

    private void logAuthenticationEvent(HttpServletRequest request, HttpServletResponse response, SecurityEvent event) {
        if (response.getStatus() == 200) {
            securityLogger.info("LOGIN_SUCCESS: {}", event);
        } else if (response.getStatus() == 401) {
            securityLogger.warn("LOGIN_FAILURE: {}", event);
        } else if (response.getStatus() == 429) {
            securityLogger.warn("LOGIN_RATE_LIMITED: {}", event);
        }
    }

    private static class SecurityEvent {
        private final LocalDateTime timestamp;
        private final String clientIp;
        private final String userAgent;
        private final String method;
        private final String requestUri;
        private final int statusCode;
        private final long duration;
        private final boolean suspicious;

        public SecurityEvent(LocalDateTime timestamp, String clientIp, String userAgent, 
                           String method, String requestUri, int statusCode, 
                           long duration, boolean suspicious) {
            this.timestamp = timestamp;
            this.clientIp = clientIp;
            this.userAgent = userAgent;
            this.method = method;
            this.requestUri = requestUri;
            this.statusCode = statusCode;
            this.duration = duration;
            this.suspicious = suspicious;
        }

        @Override
        public String toString() {
            return String.format(
                "SecurityEvent{timestamp=%s, clientIp='%s', userAgent='%s', method='%s', " +
                "requestUri='%s', statusCode=%d, duration=%dms, suspicious=%s}",
                timestamp, clientIp, userAgent, method, requestUri, statusCode, duration, suspicious
            );
        }
    }
}
