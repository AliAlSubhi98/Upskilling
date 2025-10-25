package com.upskilling.smartdeploymonitor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class InputValidationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(InputValidationFilter.class);
    private static final Logger securityLogger = LoggerFactory.getLogger("SECURITY");
    
    @Autowired(required = false)
    private SecurityEventLogger securityEventLogger;

    // Enhanced SQL Injection patterns
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i).*(" +
        // SQL keywords
        "(union|select|insert|delete|update|drop|create|alter|exec|execute|truncate|declare|cast|convert)\\s+.*|" +
        // Boolean logic
        "(\\s*(or|and)\\s+\\w+\\s*=\\s*\\w+)|" +
        "(\\s*(or|and)\\s+\\d+\\s*=\\s*\\d+)|" +
        // Comments and terminators
        "(\\s*;\\s*)|" +
        "(\\s*--\\s*)|" +
        "(\\s*/\\*.*\\*/\\s*)|" +
        // Common injection patterns
        "(\\s*'\\s*or\\s*'\\d+'\\s*=\\s*'\\d+)|" +
        "(\\s*'\\s*or\\s*1\\s*=\\s*1)|" +
        "(\\s*'\\s*or\\s*'\\w+'\\s*=\\s*'\\w+)|" +
        "(\\s*'\\s*union\\s+select)|" +
        "(\\s*'\\s*;\\s*drop\\s+table)|" +
        "(\\s*'\\s*;\\s*delete\\s+from)|" +
        "(\\s*'\\s*;\\s*update\\s+.*\\s+set)|" +
        // Time-based blind SQL injection
        "(\\s*'\\s*;\\s*waitfor\\s+delay)|" +
        "(\\s*'\\s*;\\s*sleep\\s*\\()|" +
        // Function-based injections
        "(\\s*'\\s*;\\s*exec\\s*\\()|" +
        "(\\s*'\\s*;\\s*sp_)|" +
        // Information schema
        "(\\s*'\\s*union\\s+select.*information_schema)|" +
        // Common bypass techniques
        "(\\s*'\\s*or\\s*'\\w+'\\s*like\\s*'\\w+)|" +
        "(\\s*'\\s*or\\s*\\d+\\s*like\\s*\\d+)|" +
        // Null byte injection
        "(\\s*'\\s*or\\s*'\\w+'\\s*=\\s*'\\w+\\x00)|" +
        // Hex encoding
        "(\\s*'\\s*or\\s*'\\w+'\\s*=\\s*0x)|" +
        // Double encoding
        "(\\s*'\\s*or\\s*'\\w+'\\s*=\\s*%27)|" +
        // Case variations
        "(\\s*'\\s*Or\\s*'\\w+'\\s*=\\s*'\\w+)|" +
        "(\\s*'\\s*OR\\s*'\\w+'\\s*=\\s*'\\w+)" +
        ")"
    );

    // XSS patterns
    private static final Pattern XSS_PATTERN = Pattern.compile(
        "(?i).*<script.*>.*</script>|" +
        ".*javascript:.*|" +
        ".*on\\w+\\s*=.*|" +
        ".*<iframe.*>.*</iframe>|" +
        ".*<object.*>.*</object>|" +
        ".*<embed.*>.*</embed>"
    );

    // Path traversal patterns
    private static final Pattern PATH_TRAVERSAL_PATTERN = Pattern.compile(
        ".*\\.\\..*|.*\\.\\./.*|.*\\.\\.\\\\\\.*"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // Check query parameters
        request.getParameterMap().forEach((key, values) -> {
            for (String value : values) {
                String attackType = detectAttackType(value);
                if (attackType != null) {
                    String clientIp = getClientIpAddress(request);
                    String userAgent = request.getHeader("User-Agent");
                    
                    // Log security event
                    securityLogger.warn("SECURITY_ALERT: {} detected in parameter '{}' with value '{}' from IP '{}'", 
                        attackType, key, value, clientIp);
                    
                    if (securityEventLogger != null) {
                        securityEventLogger.logSecurityEvent(attackType, 
                            "Parameter: " + key + " = " + value, clientIp);
                    }
                    
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    try {
                        response.getWriter().write("{\"error\":\"Invalid input detected\"}");
                    } catch (IOException e) {
                        logger.error("Error writing response", e);
                    }
                    return;
                }
            }
        });

        // Check request URI
        String requestURI = request.getRequestURI();
        String attackType = detectAttackType(requestURI);
        if (attackType != null) {
            String clientIp = getClientIpAddress(request);
            String userAgent = request.getHeader("User-Agent");
            
            securityLogger.warn("SECURITY_ALERT: {} detected in URI '{}' from IP '{}'", 
                attackType, requestURI, clientIp);
            
            if (securityEventLogger != null) {
                securityEventLogger.logSecurityEvent(attackType, "URI: " + requestURI, clientIp);
            }
            
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.getWriter().write("{\"error\":\"Invalid request path\"}");
            } catch (IOException e) {
                logger.error("Error writing response", e);
            }
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String detectAttackType(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        if (SQL_INJECTION_PATTERN.matcher(input).matches()) {
            return "SQL_INJECTION";
        }
        if (XSS_PATTERN.matcher(input).matches()) {
            return "XSS";
        }
        if (PATH_TRAVERSAL_PATTERN.matcher(input).matches()) {
            return "PATH_TRAVERSAL";
        }
        
        return null;
    }
    
    private boolean isMaliciousInput(String input) {
        return detectAttackType(input) != null;
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
