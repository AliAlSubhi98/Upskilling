package com.upskilling.smartdeploymonitor.controller;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import com.upskilling.smartdeploymonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for managing users in the Smart Deploy Monitor system")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Create a new user
     */
    @Operation(
        summary = "Create a new user",
        description = "Creates a new user account with the provided information. The user will be assigned the USER role by default."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = @ExampleObject(
                    value = "{\"success\": true, \"message\": \"User created successfully\", \"user\": {\"id\": \"uuid\", \"email\": \"john@example.com\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"role\": \"USER\"}}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request - Invalid input or user already exists",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = @ExampleObject(
                    value = "{\"success\": false, \"message\": \"User with email already exists\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        )
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(
            @Parameter(description = "User creation request", required = true)
            @Valid @RequestBody CreateUserRequest request) {
        try {
            logger.info("Creating new user with email: {}", request.getEmail());
            
            User user = userService.createUser(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User created successfully");
            response.put("user", createUserResponse(user));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error creating user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Create an admin user
     */
    @PostMapping("/admin")
    public ResponseEntity<Map<String, Object>> createAdminUser(@RequestBody CreateUserRequest request) {
        try {
            logger.info("Creating new admin user with email: {}", request.getEmail());
            
            User user = userService.createAdminUser(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Admin user created successfully");
            response.put("user", createUserResponse(user));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error creating admin user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Get all users with pagination and filtering
     */
    @Operation(
        summary = "Get all users with pagination",
        description = "Retrieves a paginated list of users with optional filtering and sorting capabilities."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Users retrieved successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = @ExampleObject(
                    value = "{\"success\": true, \"data\": [{\"id\": \"uuid\", \"email\": \"john@example.com\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"role\": \"USER\"}], \"pagination\": {\"page\": 0, \"size\": 10, \"totalElements\": 25, \"totalPages\": 3}}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request - Invalid pagination parameters",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Search term for filtering users", example = "john")
            @RequestParam(required = false) String search,
            @Parameter(description = "Sort field", example = "firstName")
            @RequestParam(defaultValue = "firstName") String sortBy,
            @Parameter(description = "Sort direction", example = "asc")
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        logger.info("Fetching users - page: {}, size: {}, search: {}, sortBy: {}, sortDir: {}", 
                   page, size, search, sortBy, sortDir);
        
        // Validate pagination parameters
        if (page < 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Page number must be non-negative");
            return ResponseEntity.badRequest().body(response);
        }
        
        if (size < 1 || size > 100) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Page size must be between 1 and 100");
            return ResponseEntity.badRequest().body(response);
        }
        
        // Get paginated users
        Map<String, Object> result = userService.getUsersPaginated(page, size, search, sortBy, sortDir);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result.get("users"));
        response.put("pagination", result.get("pagination"));
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get active users only
     */
    @GetMapping("/active")
    public ResponseEntity<Map<String, Object>> getActiveUsers() {
        logger.info("Fetching active users");
        
        List<User> users = userService.getActiveUsers();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", users.size());
        response.put("users", users.stream().map(this::createUserResponse).toList());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
        logger.info("Fetching user with ID: {}", id);
        
        return userService.findById(id)
                .map(user -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("user", createUserResponse(user));
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", false);
                    response.put("message", "User not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }
    
    /**
     * Get user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        logger.info("Fetching user with email: {}", email);
        
        return userService.findByEmail(email)
                .map(user -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("user", createUserResponse(user));
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", false);
                    response.put("message", "User not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }
    
    /**
     * Get users by role
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<Map<String, Object>> getUsersByRole(@PathVariable UserRole role) {
        logger.info("Fetching users with role: {}", role);
        
        List<User> users = userService.getUsersByRole(role);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("count", users.size());
        response.put("role", role);
        response.put("users", users.stream().map(this::createUserResponse).toList());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Update user
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request) {
        try {
            logger.info("Updating user with ID: {}", id);
            
            User user = userService.updateUser(
                id,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User updated successfully");
            response.put("user", createUserResponse(user));
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error updating user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Change user password
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @PathVariable UUID id,
            @RequestBody ChangePasswordRequest request) {
        try {
            logger.info("Changing password for user with ID: {}", id);
            
            userService.changePassword(id, request.getNewPassword());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Password changed successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error changing password: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Activate user
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<Map<String, Object>> activateUser(@PathVariable UUID id) {
        try {
            logger.info("Activating user with ID: {}", id);
            
            userService.activateUser(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User activated successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error activating user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Deactivate user
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateUser(@PathVariable UUID id) {
        try {
            logger.info("Deactivating user with ID: {}", id);
            
            userService.deactivateUser(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User deactivated successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error deactivating user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Delete user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable UUID id) {
        try {
            logger.info("Deleting user with ID: {}", id);
            
            userService.deleteUser(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User deleted successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            logger.error("Error deleting user: {}", e.getMessage());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Get user statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getUserStatistics() {
        logger.info("Fetching user statistics");
        
        UserService.UserStatistics stats = userService.getUserStatistics();
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("statistics", Map.of(
            "totalUsers", stats.getTotalUsers(),
            "activeUsers", stats.getActiveUsers(),
            "inactiveUsers", stats.getInactiveUsers(),
            "adminUsers", stats.getAdminUsers(),
            "regularUsers", stats.getRegularUsers()
        ));
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Verify user password
     */
    @PostMapping("/verify-password")
    public ResponseEntity<Map<String, Object>> verifyPassword(@RequestBody VerifyPasswordRequest request) {
        logger.info("Verifying password for user: {}", request.getEmail());
        
        boolean isValid = userService.verifyPassword(request.getEmail(), request.getPassword());
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("valid", isValid);
        response.put("message", isValid ? "Password is valid" : "Password is invalid");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Helper method to create user response without sensitive data
     */
    private Map<String, Object> createUserResponse(User user) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", user.getId());
        userResponse.put("email", user.getEmail());
        userResponse.put("firstName", user.getFirstName());
        userResponse.put("lastName", user.getLastName());
        userResponse.put("fullName", user.getFullName());
        userResponse.put("role", user.getRole());
        userResponse.put("isActive", user.getIsActive());
        userResponse.put("isAdmin", user.isAdmin());
        userResponse.put("createdAt", user.getCreatedAt());
        userResponse.put("updatedAt", user.getUpdatedAt());
        return userResponse;
    }
    
    // Request DTOs
    @Schema(description = "Request to create a new user")
    public static class CreateUserRequest {
        @Schema(description = "User email address", example = "john.doe@example.com", required = true)
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        private String email;
        
        @Schema(description = "User password", example = "securePassword123", required = true, minLength = 6)
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;
        
        @Schema(description = "User first name", example = "John", required = true)
        @NotBlank(message = "First name is required")
        private String firstName;
        
        @Schema(description = "User last name", example = "Doe", required = true)
        @NotBlank(message = "Last name is required")
        private String lastName;
        
        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }
    
    public static class UpdateUserRequest {
        private String email;
        private String firstName;
        private String lastName;
        
        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }
    
    public static class ChangePasswordRequest {
        private String newPassword;
        
        // Getters and setters
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
    
    public static class VerifyPasswordRequest {
        private String email;
        private String password;
        
        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}