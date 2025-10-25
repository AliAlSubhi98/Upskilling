package com.upskilling.smartdeploymonitor.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class InputValidationFilter extends OncePerRequestFilter {

    // SQL Injection patterns
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i).*((union|select|insert|delete|update|drop|create|alter|exec|execute)\\s+.*)|" +
        "(\\s*(or|and)\\s+\\w+\\s*=\\s*\\w+)|" +
        "(\\s*;\\s*)|" +
        "(\\s*--\\s*)|" +
        "(\\s*/\\*.*\\*/\\s*)"
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
                if (isMaliciousInput(value)) {
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
        if (isMaliciousInput(requestURI)) {
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

    private boolean isMaliciousInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        return SQL_INJECTION_PATTERN.matcher(input).matches() ||
               XSS_PATTERN.matcher(input).matches() ||
               PATH_TRAVERSAL_PATTERN.matcher(input).matches();
    }
}
