# Smart Deploy Monitor

    Spring Boot application demonstrating basic observability (logging, metrics, health checks) and ready for CI/CD pipelines.

## Requirements
- Java 17+
- Maven 3.9+

## Running
```bash
mvn spring-boot:run
```

Or build a jar:
```bash
mvn clean package
java -jar target/smart-deploy-monitor-1.0.0.jar
```

## Endpoints
- `/api/demo/health` – custom health JSON
- `/api/demo/logs` – generate logs
- `/api/actuator/health` – Spring Boot actuator health
- `/api/actuator/metrics` – built-in metrics
- `/api/actuator/prometheus` – Prometheus scrape
