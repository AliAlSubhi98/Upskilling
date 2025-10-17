package com.upskilling.smartdeploymonitor.repository;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    /**
     * Find user by email address
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find user by email address (case insensitive)
     */
    Optional<User> findByEmailIgnoreCase(String email);
    
    /**
     * Check if user exists by email
     */
    boolean existsByEmail(String email);
    
    /**
     * Find all active users
     */
    List<User> findByIsActiveTrue();
    
    /**
     * Find all users by role
     */
    List<User> findByRole(UserRole role);
    
    /**
     * Find all active users by role
     */
    List<User> findByRoleAndIsActiveTrue(UserRole role);
    
    /**
     * Find users by first name containing (case insensitive)
     */
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    
    /**
     * Find users by last name containing (case insensitive)
     */
    List<User> findByLastNameContainingIgnoreCase(String lastName);
    
    /**
     * Count users by role
     */
    long countByRole(UserRole role);
    
    /**
     * Count active users
     */
    long countByIsActiveTrue();
    
    /**
     * Custom query to find users created in the last N days
     */
    @Query("SELECT u FROM User u WHERE u.createdAt >= CURRENT_DATE - :days")
    List<User> findUsersCreatedInLastDays(@Param("days") int days);
    
    /**
     * Custom query to find users by email domain
     */
    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain%")
    List<User> findUsersByEmailDomain(@Param("domain") String domain);
    
    /**
     * Custom query to find admin users
     */
    @Query("SELECT u FROM User u WHERE u.role = 'ADMIN' AND u.isActive = true")
    List<User> findActiveAdmins();
}