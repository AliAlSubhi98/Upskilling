package com.upskilling.smartdeploymonitor.service;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import com.upskilling.smartdeploymonitor.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class UserService implements UserDetailsService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .authorities(getAuthorities(user.getRole()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getIsActive())
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    
    /**
     * Create a new user
     */
    public User createUser(String email, String password, String firstName, String lastName) {
        logger.info("Creating new user with email: {}", email);
        
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(email, hashedPassword, firstName, lastName);
        
        User savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getId());
        
        return savedUser;
    }
    
    /**
     * Create an admin user
     */
    public User createAdminUser(String email, String password, String firstName, String lastName) {
        logger.info("Creating new admin user with email: {}", email);
        
        User user = createUser(email, password, firstName, lastName);
        user.setRole(UserRole.ADMIN);
        
        User savedUser = userRepository.save(user);
        logger.info("Admin user created successfully with ID: {}", savedUser.getId());
        
        return savedUser;
    }
    
    /**
     * Find user by ID
     */
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    
    /**
     * Find user by email
     */
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Get all users
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get all active users
     */
    @Transactional(readOnly = true)
    public List<User> getActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }
    
    /**
     * Get users by role
     */
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }
    
    /**
     * Update user information
     */
    public User updateUser(UUID id, String firstName, String lastName, String email) {
        logger.info("Updating user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        if (email != null && !email.equals(user.getEmail())) {
            if (userRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Email " + email + " is already in use");
            }
            user.setEmail(email);
        }
        
        if (firstName != null) {
            user.setFirstName(firstName);
        }
        
        if (lastName != null) {
            user.setLastName(lastName);
        }
        
        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());
        
        return updatedUser;
    }
    
    /**
     * Change user password
     */
    public void changePassword(UUID id, String newPassword) {
        logger.info("Changing password for user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPasswordHash(hashedPassword);
        
        userRepository.save(user);
        logger.info("Password changed successfully for user with ID: {}", id);
    }
    
    /**
     * Activate user
     */
    public void activateUser(UUID id) {
        logger.info("Activating user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.setIsActive(true);
        userRepository.save(user);
        logger.info("User activated successfully with ID: {}", id);
    }
    
    /**
     * Deactivate user
     */
    public void deactivateUser(UUID id) {
        logger.info("Deactivating user with ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.setIsActive(false);
        userRepository.save(user);
        logger.info("User deactivated successfully with ID: {}", id);
    }
    
    /**
     * Delete user
     */
    public void deleteUser(UUID id) {
        logger.info("Deleting user with ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        
        userRepository.deleteById(id);
        logger.info("User deleted successfully with ID: {}", id);
    }
    
    /**
     * Verify user password
     */
    public boolean verifyPassword(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        return passwordEncoder.matches(password, user.getPasswordHash());
    }
    
    /**
     * Get user statistics
     */
    @Transactional(readOnly = true)
    public UserStatistics getUserStatistics() {
        long totalUsers = userRepository.count();
        long activeUsers = userRepository.countByIsActiveTrue();
        long adminUsers = userRepository.countByRole(UserRole.ADMIN);
        long regularUsers = userRepository.countByRole(UserRole.USER);
        
        return new UserStatistics(totalUsers, activeUsers, adminUsers, regularUsers);
    }
    
    /**
     * Inner class for user statistics
     */
    public static class UserStatistics {
        private final long totalUsers;
        private final long activeUsers;
        private final long adminUsers;
        private final long regularUsers;
        
        public UserStatistics(long totalUsers, long activeUsers, long adminUsers, long regularUsers) {
            this.totalUsers = totalUsers;
            this.activeUsers = activeUsers;
            this.adminUsers = adminUsers;
            this.regularUsers = regularUsers;
        }
        
        // Getters
        public long getTotalUsers() { return totalUsers; }
        public long getActiveUsers() { return activeUsers; }
        public long getAdminUsers() { return adminUsers; }
        public long getRegularUsers() { return regularUsers; }
        public long getInactiveUsers() { return totalUsers - activeUsers; }
    }
    
    /**
     * Get paginated users with filtering and sorting
     */
    public Map<String, Object> getUsersPaginated(int page, int size, String search, String sortBy, String sortDir) {
        logger.info("Getting paginated users - page: {}, size: {}, search: {}, sortBy: {}, sortDir: {}", 
                   page, size, search, sortBy, sortDir);
        
        // Create sort object
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<User> userPage;
        
        // Apply search filter if provided
        if (search != null && !search.trim().isEmpty()) {
            userPage = userRepository.findBySearchTerm(search.trim(), pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }
        
        // Create pagination info
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", userPage.getNumber());
        pagination.put("size", userPage.getSize());
        pagination.put("totalElements", userPage.getTotalElements());
        pagination.put("totalPages", userPage.getTotalPages());
        pagination.put("first", userPage.isFirst());
        pagination.put("last", userPage.isLast());
        pagination.put("numberOfElements", userPage.getNumberOfElements());
        
        // Create response
        Map<String, Object> result = new HashMap<>();
        result.put("users", userPage.getContent());
        result.put("pagination", pagination);
        
        return result;
    }

    /**
     * Get users by IDs for DataLoader (solves N+1 problem)
     */
    public List<User> getUsersByIds(List<UUID> userIds) {
        logger.info("Getting users by IDs: {}", userIds);
        return userRepository.findAllById(userIds);
    }
}