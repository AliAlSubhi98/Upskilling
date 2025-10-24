package com.upskilling.smartdeploymonitor.service.domain;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import com.upskilling.smartdeploymonitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User Domain Service - Demonstrates Monolith vs Microservices Patterns
 * 
 * This service encapsulates all user-related business logic and represents
 * a domain boundary that could be extracted into a microservice.
 * 
 * In a microservices architecture, this would become the User Service.
 */
@Service
@Transactional
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user with business validation
     * Demonstrates domain logic encapsulation
     */
    public User createUser(String email, String password, String firstName, String lastName, UserRole role) {
        // Business validation
        validateUserCreation(email, firstName, lastName);
        
        // Check if user already exists
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        
        // Create user entity
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setIsActive(true);
        
        return userRepository.save(user);
    }

    /**
     * Update user information with business rules
     * Demonstrates domain logic for user updates
     */
    public User updateUser(UUID userId, String firstName, String lastName, UserRole role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        
        // Business validation
        validateUserUpdate(firstName, lastName);
        
        // Update fields
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        
        return userRepository.save(user);
    }

    /**
     * Deactivate user (soft delete)
     * Demonstrates business logic for user lifecycle
     */
    public void deactivateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        
        user.setIsActive(false);
        userRepository.save(user);
    }

    /**
     * Get all active users
     * Demonstrates domain query methods
     */
    public List<User> getActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }

    /**
     * Get user by email
     * Demonstrates domain lookup methods
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Business validation for user creation
     * Demonstrates domain validation logic
     */
    private void validateUserCreation(String email, String firstName, String lastName) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    /**
     * Business validation for user updates
     * Demonstrates domain validation logic
     */
    private void validateUserUpdate(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
    }
}
