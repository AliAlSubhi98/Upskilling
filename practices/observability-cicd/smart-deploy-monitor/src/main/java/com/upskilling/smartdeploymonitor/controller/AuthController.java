package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.dto.AuthRequest;
import com.upskilling.smartdeploymonitor.dto.AuthResponse;
import com.upskilling.smartdeploymonitor.dto.RegisterRequest;
import com.upskilling.smartdeploymonitor.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and authorization endpoints")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Get new access token using refresh token")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader("Authorization") String refreshToken) {
        String token = refreshToken.substring(7); // Remove "Bearer " prefix
        AuthResponse response = authService.refreshToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout user (client should discard tokens)")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}