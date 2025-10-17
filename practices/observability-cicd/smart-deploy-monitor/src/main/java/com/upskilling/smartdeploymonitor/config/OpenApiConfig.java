package com.upskilling.smartdeploymonitor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartDeployMonitorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Deploy Monitor API")
                        .description("A comprehensive Spring Boot application demonstrating CI/CD, Observability, and Database integration with user management capabilities.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ali Al Subhi")
                                .email("ali@example.com")
                                .url("https://github.com/AliAlSubhi98"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("https://api.smartdeploymonitor.com")
                                .description("Production Server")
                ));
    }
}