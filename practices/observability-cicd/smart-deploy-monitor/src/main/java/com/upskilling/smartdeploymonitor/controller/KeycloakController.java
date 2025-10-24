package com.upskilling.smartdeploymonitor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/keycloak")
@Tag(name = "Keycloak Authentication", description = "Keycloak authentication endpoints")
public class KeycloakController {

    @GetMapping("/user")
    @Operation(summary = "Get current user info", description = "Returns information about the currently authenticated user")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", authentication.getName());
        userInfo.put("authorities", authentication.getAuthorities());
        userInfo.put("authenticated", authentication.isAuthenticated());
        userInfo.put("principal", authentication.getPrincipal().getClass().getSimpleName());
        
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/status")
    @Operation(summary = "Authentication status", description = "Returns the current authentication status")
    public ResponseEntity<Map<String, Object>> getAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> status = new HashMap<>();
        status.put("authenticated", authentication.isAuthenticated());
        status.put("user", authentication.getName());
        status.put("roles", authentication.getAuthorities());
        
        return ResponseEntity.ok(status);
    }
}
