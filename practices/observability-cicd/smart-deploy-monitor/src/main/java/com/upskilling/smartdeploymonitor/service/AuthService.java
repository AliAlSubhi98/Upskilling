package com.upskilling.smartdeploymonitor.service;

import com.upskilling.smartdeploymonitor.dto.AuthRequest;
import com.upskilling.smartdeploymonitor.dto.AuthResponse;
import com.upskilling.smartdeploymonitor.dto.RegisterRequest;
import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        try {
            // Check if user already exists
            if (userService.existsByEmail(request.getEmail())) {
                return new AuthResponse(null, null, null, "User already exists", false);
            }

            // Create new user
            User savedUser = userService.createUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName());
            
            // Generate tokens
            UserDetails userDetails = userService.loadUserByUsername(savedUser.getEmail());
            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            return new AuthResponse(accessToken, refreshToken, 86400000L, "User registered successfully", true);
        } catch (Exception e) {
            return new AuthResponse(null, null, null, "Registration failed: " + e.getMessage(), false);
        }
    }

    public AuthResponse login(AuthRequest request) {
        try {
            // Find user by email
            Optional<User> userOpt = userService.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                return new AuthResponse(null, null, null, "Login failed: User not found", false);
            }

            User user = userOpt.get();

            // Check password
            if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                return new AuthResponse(null, null, null, "Login failed: Invalid password", false);
            }

            // Generate tokens
            UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            return new AuthResponse(accessToken, refreshToken, 86400000L, "Login successful", true);
        } catch (Exception e) {
            return new AuthResponse(null, null, null, "Login failed: " + e.getMessage(), false);
        }
    }

    public AuthResponse refreshToken(String refreshToken) {
        try {
            if (!jwtService.isRefreshToken(refreshToken)) {
                return new AuthResponse(null, null, null, "Invalid refresh token", false);
            }

            String email = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userService.loadUserByUsername(email);
            
            if (jwtService.validateToken(refreshToken, userDetails)) {
                String newAccessToken = jwtService.generateToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(userDetails);
                
                return new AuthResponse(newAccessToken, newRefreshToken, 86400000L, "Token refreshed successfully", true);
            } else {
                return new AuthResponse(null, null, null, "Invalid refresh token", false);
            }
        } catch (Exception e) {
            return new AuthResponse(null, null, null, "Token refresh failed: " + e.getMessage(), false);
        }
    }
}