# Smart Deploy Monitor

A Spring Boot application demonstrating CI/CD and Observability concepts.

## Features

- **REST API**: Simple demo endpoints for health checks and application info
- **Observability**: Built-in health checks, metrics, and logging
- **CI/CD**: GitHub Actions pipeline for automated testing and building

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+

### Running the Application

```bash
# Clone and navigate to the project
cd practices/observability-cicd/smart-deploy-monitor

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Available Endpoints

- `GET /demo/health` - Application health check
- `GET /demo/info` - Application information
- `GET /actuator/health` - Spring Boot Actuator health endpoint
- `GET /actuator/metrics` - Application metrics
- `GET /actuator/prometheus` - Prometheus metrics format

### Testing the Application

```bash
# Health check
curl http://localhost:8080/demo/health

# Application info
curl http://localhost:8080/demo/info

# Actuator health
curl http://localhost:8080/actuator/health
```

## CI/CD Pipeline

The project includes a GitHub Actions workflow (`.github/workflows/ci-cd.yml`) that:

1. Runs on push to `main` and `develop` branches
2. Runs on pull requests to `main`
3. Sets up Java 17 environment
4. Caches Maven dependencies
5. Runs tests
6. Builds the application
7. Uploads build artifacts

## Observability Features

- **Logging**: Structured logging with timestamps
- **Health Checks**: Built-in health monitoring
- **Metrics**: Prometheus-compatible metrics endpoint
- **Actuator**: Spring Boot Actuator for monitoring and management

## Learning Objectives

This project demonstrates:

- **CI/CD**: Automated testing and building with GitHub Actions
- **Observability**: Application monitoring, logging, and metrics
- **Spring Boot**: Modern Java web application development
- **Maven**: Dependency management and build automation