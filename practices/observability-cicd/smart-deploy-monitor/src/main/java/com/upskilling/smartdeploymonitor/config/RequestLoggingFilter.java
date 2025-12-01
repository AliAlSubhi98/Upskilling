package com.upskilling.smartdeploymonitor.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Centralized HTTP request/response logging filter
 * Logs all HTTP requests automatically without needing logger statements in controllers
 */
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    // Paths to exclude from logging (actuator endpoints, static resources, etc.)
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/actuator/prometheus",
        "/actuator/metrics",
        "/actuator/health"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // Skip logging for excluded paths
        if (EXCLUDED_PATHS.stream().anyMatch(requestPath::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        long startTime = System.currentTimeMillis();

        // Wrap request/response to allow reading body multiple times
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequest(wrappedRequest, wrappedResponse, duration);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequest(HttpServletRequest request, HttpServletResponse response, long duration) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullPath = queryString == null ? uri : uri + "?" + queryString;
        int status = response.getStatus();
        String clientIp = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");

        // Log request details
        logger.info("HTTP {} {} - Status: {} - Duration: {}ms - IP: {} - User-Agent: {}",
            method, fullPath, status, duration, clientIp, userAgent);

        // Optionally log request/response body for debugging (be careful with sensitive data)
        // String requestBody = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
        // String responseBody = new String(response.getContentAsByteArray(), request.getCharacterEncoding());
        // logger.debug("Request Body: {}", requestBody);
        // logger.debug("Response Body: {}", responseBody);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(".")) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}