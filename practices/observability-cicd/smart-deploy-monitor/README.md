# Smart Deploy Monitor

Spring Boot application demonstrating **Observability + CI/CD** integration with comprehensive monitoring and automated deployment pipelines.

## 🎯 Learning Objectives

This project combines two upskilling topics:
- **Observability Level 1:** Logging, metrics, application monitoring
- **CI/CD Level 1:** Automated deployment and testing

## 🚀 Features

### Observability Features
- ✅ **Health Checks:** `/api/actuator/health` endpoint
- ✅ **Metrics:** Prometheus metrics collection
- ✅ **Logging:** Structured logging with different levels
- ✅ **Monitoring:** Performance monitoring and error tracking
- ✅ **Custom Metrics:** Request counters and error counters
- ✅ **Advanced Metrics:** Database queries, cache hits/misses, external API calls

### CI/CD Features
- ✅ **GitHub Actions:** Automated build and test pipeline
- ✅ **Docker Support:** Containerization with Dockerfile
- ✅ **Security Scanning:** Vulnerability checks
- ✅ **Multi-stage Pipeline:** Build → Test → Security → Deploy

## 🛠️ Technology Stack

- **Framework:** Spring Boot 3.5.4
- **Java:** JDK 17
- **Build Tool:** Maven
- **Observability:** Spring Boot Actuator + Micrometer + Prometheus
- **CI/CD:** GitHub Actions
- **Containerization:** Docker
- **Security:** OWASP Dependency Check, Trivy

## 📁 Project Structure

```
smart-deploy-monitor/
├── src/main/java/com/upskilling/smartdeploymonitor/
│   ├── SmartDeployMonitorApplication.java    # Main application class
│   └── controller/
│       └── DemoController.java               # Demo endpoints for observability
├── src/main/resources/
│   ├── application.properties               # Application configuration
│   └── application.yml                      # YAML configuration
├── .github/workflows/
│   ├── simple-ci-cd.yml                     # Basic CI/CD pipeline
│   ├── ci-cd.yml                           # Comprehensive pipeline
│   └── docker-ci-cd.yml                    # Docker-based pipeline
├── Dockerfile                              # Container configuration
├── docker-compose.yml                      # Local deployment
├── pom.xml                                 # Maven dependencies
└── README.md                               # This file
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.9 or higher
- Docker (optional, for containerization)

### Running Locally

#### Option 1: Maven
```bash
cd practices/observability-cicd/smart-deploy-monitor
mvn spring-boot:run
```

#### Option 2: JAR
```bash
mvn clean package
java -jar target/smart-deploy-monitor-1.0.0.jar
```

#### Option 3: Docker
```bash
# Build and run with Docker
docker build -t smart-deploy-monitor .
docker run -p 8080:8080 smart-deploy-monitor

# Or use Docker Compose
docker-compose up
```

## 📊 Observability Endpoints

### Demo Endpoints
```bash
# Health check
curl http://localhost:8080/api/demo/health

# Generate logs (INFO/WARN/ERROR)
curl http://localhost:8080/api/demo/logs

# Performance test (variable latency)
curl http://localhost:8080/api/demo/performance

# Simulate error
curl http://localhost:8080/api/demo/error
```

### Advanced Features Endpoints
```bash
# Get user by ID (with caching)
curl http://localhost:8080/api/advanced/users/user1

# Create new user
curl -X POST http://localhost:8080/api/advanced/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Johnson","email":"alice@example.com"}'

# Call external API (simulated)
curl http://localhost:8080/api/advanced/external-api

# Get cache statistics
curl http://localhost:8080/api/advanced/cache/stats

# Clear cache
curl -X DELETE http://localhost:8080/api/advanced/cache/clear
```

### Actuator Endpoints
```bash
# Health check
curl http://localhost:8080/api/actuator/health

# Metrics
curl http://localhost:8080/api/actuator/metrics

# Prometheus format
curl http://localhost:8080/api/actuator/prometheus
```

## 🔄 CI/CD Pipeline

### GitHub Actions Workflows

1. **Simple CI/CD** (`.github/workflows/simple-ci-cd.yml`)
   - Build and test
   - Security scan
   - Demo deployment

2. **Comprehensive CI/CD** (`.github/workflows/ci-cd.yml`)
   - Multi-stage pipeline
   - Code quality analysis
   - Staging and production deployment
   - Observability setup

3. **Docker CI/CD** (`.github/workflows/docker-ci-cd.yml`)
   - Containerized deployment
   - Vulnerability scanning
   - Docker-based testing

### Pipeline Stages

1. **Build & Test**
   - Maven compilation
   - Unit tests
   - JAR packaging

2. **Security Scan**
   - Dependency vulnerability check
   - Container security scan (Trivy)

3. **Deploy**
   - Staging deployment (develop branch)
   - Production deployment (main branch)
   - Health checks

4. **Observability Setup**
   - Monitoring configuration
   - Metrics collection
   - Alerting setup

## 📈 Monitoring & Metrics

### Custom Metrics
- `demo.requests.total` - Total number of demo requests
- `demo.errors.total` - Total number of demo errors
- `advanced.database.queries` - Number of database queries
- `advanced.cache.hits` - Number of cache hits
- `advanced.cache.misses` - Number of cache misses
- `advanced.external.api.calls` - Number of external API calls

### Built-in Metrics
- JVM metrics (memory, threads, garbage collection)
- HTTP metrics (request counts, response times)
- Application metrics (uptime, startup time)

## 📝 Logging

The application generates logs at different levels:
- **INFO:** Normal application flow
- **WARN:** Warning conditions
- **ERROR:** Error conditions

Logs are written to both console and can be configured for file output.

## 🎓 Learning Progress

### Observability Level 1 Tasks
- [x] Collect logs from a running application
- [x] Use monitoring tools (health checks, metrics)
- [x] Configure basic alerts for application status

### CI/CD Level 1 Tasks
- [x] Basics of CI/CD, GitHub Actions, YAML Pipelines
- [x] Automated Testing, Deployment Pipelines
- [x] Secure CI/CD Pipelines

## 📚 Resources

- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Micrometer Documentation](https://micrometer.io/docs)
- [Prometheus Documentation](https://prometheus.io/docs/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Docker Documentation](https://docs.docker.com/)

## 🤝 Contributing

This is a learning project for upskilling purposes. Feel free to experiment and add features!

## 📄 License

This project is for educational purposes as part of the Upskilling program.
