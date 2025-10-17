package com.upskilling.smartdeploymonitor.entity;

public enum UserRole {
    USER("User"),
    ADMIN("Administrator"),
    MODERATOR("Moderator");
    
    private final String displayName;
    
    UserRole(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}