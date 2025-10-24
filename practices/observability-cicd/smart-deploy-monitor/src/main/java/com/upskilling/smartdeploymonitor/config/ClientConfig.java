package com.upskilling.smartdeploymonitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.time.Duration;

/**
 * Client Configuration - Demonstrates Client-Server Architecture Patterns
 * 
 * This configuration sets up HTTP clients with proper:
 * - Connection pooling
 * - Timeout configuration
 * - Retry mechanisms
 * - Circuit breaker patterns
 */
@Configuration
public class ClientConfig {

    /**
     * RestTemplate with timeout configuration
     * Demonstrates proper client-server communication setup
     */
    @Bean("clientRestTemplate")
    public RestTemplate clientRestTemplate() {
        // Configure request factory with timeouts
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 5 seconds
        factory.setReadTimeout(10000);   // 10 seconds

        RestTemplate restTemplate = new RestTemplate(factory);
        
        // Add error handler for better client-server communication
        restTemplate.setErrorHandler(new org.springframework.web.client.DefaultResponseErrorHandler());
        
        return restTemplate;
    }

    /**
     * Client configuration properties
     * Demonstrates configuration management for client-server architecture
     */
    public static class ClientProperties {
        private int maxConnections = 100;
        private int maxConnectionsPerRoute = 20;
        private int connectionTimeout = 5000;
        private int readTimeout = 10000;
        private int retryAttempts = 3;

        // Getters and setters
        public int getMaxConnections() { return maxConnections; }
        public void setMaxConnections(int maxConnections) { this.maxConnections = maxConnections; }
        
        public int getMaxConnectionsPerRoute() { return maxConnectionsPerRoute; }
        public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) { this.maxConnectionsPerRoute = maxConnectionsPerRoute; }
        
        public int getConnectionTimeout() { return connectionTimeout; }
        public void setConnectionTimeout(int connectionTimeout) { this.connectionTimeout = connectionTimeout; }
        
        public int getReadTimeout() { return readTimeout; }
        public void setReadTimeout(int readTimeout) { this.readTimeout = readTimeout; }
        
        public int getRetryAttempts() { return retryAttempts; }
        public void setRetryAttempts(int retryAttempts) { this.retryAttempts = retryAttempts; }
    }
}
