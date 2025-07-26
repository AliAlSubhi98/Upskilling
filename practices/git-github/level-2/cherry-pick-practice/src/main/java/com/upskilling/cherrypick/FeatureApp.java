package com.upskilling.cherrypick;

import java.util.*;
import java.time.LocalDateTime;

/**
 * Git Level 2 - Cherry-Pick Demo App
 * Simple Java app to practice selective commit picking
 */
public class FeatureApp {
    private List<Feature> features;
    private String version;
    
    public FeatureApp() {
        this.features = new ArrayList<>(
           /* Arrays.asList(
                new Feature("Feature 1", "Description 1", LocalDateTime.now()),
                new Feature("Feature 2", "Description 2", LocalDateTime.now()),
                new Feature("Feature 3", "Description 3", LocalDateTime.now())
            )*/
        );
        this.version = "1.0.0";
    }
    
    public void addFeature(String name, String description) {
        Feature feature = new Feature(name, description, LocalDateTime.now());
        features.add(feature);
        System.out.println("Added feature: " + name);
    }
    
    public void listFeatures() {
        System.out.println("\n App v" + version + " Features:");
        if (features.isEmpty()) {
            System.out.println("No features added yet.");
        } else {
            for (int i = 0; i < features.size(); i++) {
                Feature f = features.get(i);
                System.out.println((i + 1) + ". " + f.getName() + " - " + f.getDescription());
            }
        }
    }
    
    public void run() {
        System.out.println("Git Level 2 - Cherry-Pick Practice App");
        System.out.println("=====================================");
        listFeatures();
    }
    
    public static void main(String[] args) {
        FeatureApp app = new FeatureApp();
        app.run();
    }
} 