package com.upskilling.smartdeploymonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security Headers Configuration
 * Implements comprehensive security headers for protection against common attacks
 */
@Configuration
public class SecurityHeadersConfig {

    @Bean
    public OncePerRequestFilter securityHeadersFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                // X-Content-Type-Options: Prevent MIME type sniffing
                response.setHeader("X-Content-Type-Options", "nosniff");
                
                // X-Frame-Options: Prevent clickjacking
                response.setHeader("X-Frame-Options", "DENY");
                
                // X-XSS-Protection: Enable XSS filtering
                response.setHeader("X-XSS-Protection", "1; mode=block");
                
                // Strict-Transport-Security: Force HTTPS
                response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
                
                // Content-Security-Policy: Prevent XSS and injection attacks
                response.setHeader("Content-Security-Policy", 
                    "default-src 'self'; " +
                    "script-src 'self' 'unsafe-inline'; " +
                    "style-src 'self' 'unsafe-inline'; " +
                    "img-src 'self' data: https:; " +
                    "font-src 'self' https:; " +
                    "connect-src 'self'; " +
                    "frame-ancestors 'none'; " +
                    "base-uri 'self'; " +
                    "form-action 'self'");
                
                // Referrer-Policy: Control referrer information
                response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
                
                // Permissions-Policy: Control browser features
                response.setHeader("Permissions-Policy", 
                    "geolocation=(), " +
                    "microphone=(), " +
                    "camera=(), " +
                    "payment=(), " +
                    "usb=(), " +
                    "magnetometer=(), " +
                    "gyroscope=(), " +
                    "speaker=()");
                
                // X-Permitted-Cross-Domain-Policies: Control cross-domain policies
                response.setHeader("X-Permitted-Cross-Domain-Policies", "none");
                
                // Cache-Control: Prevent caching of sensitive data
                if (request.getRequestURI().contains("/api/auth/") || 
                    request.getRequestURI().contains("/api/users/")) {
                    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Expires", "0");
                }
                
                filterChain.doFilter(request, response);
            }
        };
    }
}
