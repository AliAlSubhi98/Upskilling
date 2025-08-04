package com.upskilling.cherrypick;

import java.time.LocalDateTime;

/**
 * User Authentication Feature
 * This will be cherry-picked to main branch
 */
public class UserAuth {
    private String username;
    private String email;
    private LocalDateTime lastLogin;
    private boolean isActive;

    public UserAuth(String username, String email) {
        this.username = username;
        this.email = email;
        this.lastLogin = LocalDateTime.now();
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
} 