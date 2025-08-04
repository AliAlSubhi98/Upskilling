package com.upskilling.smartdeploymonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Entry point for the Smart Deploy Monitor application.
 */
@SpringBootApplication
@EnableScheduling
public class SmartDeployMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDeployMonitorApplication.class, args);
    }
}
