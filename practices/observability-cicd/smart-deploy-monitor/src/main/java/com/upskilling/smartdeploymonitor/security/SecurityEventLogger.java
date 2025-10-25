package com.upskilling.smartdeploymonitor.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SecurityEventLogger {

    private static final Logger logger = LoggerFactory.getLogger(SecurityEventLogger.class);
    private static final Logger securityLogger = LoggerFactory.getLogger("SECURITY");
    
    // Track failed login attempts
    private final Map<String, Integer> failedAttempts = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> lastAttempt = new ConcurrentHashMap<>();

    public void logLoginAttempt(String username, String clientIp, boolean success) {
        if (success) {
            securityLogger.info("LOGIN_SUCCESS: User '{}' logged in from IP '{}'", username, clientIp);
            // Clear failed attempts on successful login
            failedAttempts.remove(username);
            lastAttempt.remove(username);
        } else {
            int attempts = failedAttempts.getOrDefault(username, 0) + 1;
            failedAttempts.put(username, attempts);
            lastAttempt.put(username, LocalDateTime.now());
            
            securityLogger.warn("LOGIN_FAILED: User '{}' failed login attempt #{} from IP '{}'", 
                username, attempts, clientIp);
            
            // Alert on multiple failed attempts
            if (attempts >= 3) {
                securityLogger.error("SECURITY_ALERT: Multiple failed login attempts for user '{}' from IP '{}'", 
                    username, clientIp);
            }
        }
    }

    public void logSecurityEvent(String eventType, String details, String clientIp) {
        securityLogger.warn("SECURITY_EVENT: {} - {} from IP '{}'", eventType, details, clientIp);
    }

    public void logRateLimitExceeded(String clientIp, String endpoint) {
        securityLogger.warn("RATE_LIMIT_EXCEEDED: IP '{}' exceeded rate limit for endpoint '{}'", 
            clientIp, endpoint);
    }

    public void logSuspiciousActivity(String activity, String clientIp, String userAgent) {
        securityLogger.error("SUSPICIOUS_ACTIVITY: {} from IP '{}' with User-Agent '{}'", 
            activity, clientIp, userAgent);
    }

    public void logAdminAccess(String adminUser, String action, String clientIp) {
        securityLogger.info("ADMIN_ACCESS: Admin '{}' performed '{}' from IP '{}'", 
            adminUser, action, clientIp);
    }

    public void logDataAccess(String user, String resource, String action, String clientIp) {
        securityLogger.info("DATA_ACCESS: User '{}' {} '{}' from IP '{}'", 
            user, action, resource, clientIp);
    }

    public void logError(String error, String clientIp, String userAgent) {
        securityLogger.error("HTTP_ERROR: {} from IP '{}' with User-Agent '{}'", 
            error, clientIp, userAgent);
    }

    public boolean isAccountLocked(String username) {
        Integer attempts = failedAttempts.get(username);
        if (attempts == null || attempts < 5) {
            return false;
        }
        
        LocalDateTime last = lastAttempt.get(username);
        if (last == null) {
            return false;
        }
        
        // Lock account for 15 minutes after 5 failed attempts
        return last.isAfter(LocalDateTime.now().minusMinutes(15));
    }

    public void resetFailedAttempts(String username) {
        failedAttempts.remove(username);
        lastAttempt.remove(username);
    }
}