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
            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword())); // Hash the password
            user.setRole(UserRole.USER);
            user.setIsActive(true);

            User savedUser = userService.createUser(user.getEmail(), user.getPasswordHash(), user.getFirstName(), user.getLastName());
            
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
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Generate tokens
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
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