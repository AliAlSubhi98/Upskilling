package com.upskilling.cherrypick;

import java.time.LocalDateTime;

public class Feature {
    private String name;
    private String description;
    private LocalDateTime added;
    
    public Feature(String name, String description, LocalDateTime added) {
        this.name = name;
        this.description = description;
        this.added = added;
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public LocalDateTime getAdded() { 
        return added; 
    }
} 