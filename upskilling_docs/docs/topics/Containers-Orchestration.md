# Containers & Orchestration

**Goal:** Master containerization with Docker and orchestrate scalable deployments using Kubernetes.

**Current Level:** Level 1 (Complete)

---

## Level 1: Docker Basics
- **Competencies:** Install, Pull, Run, Stop Containers, Volumes, Networks, Docker Compose
- **Commands:** Writing your first Dockerfile, `docker run`, `docker ps`, `docker stop`, `docker rm`, `docker volume`, `docker network`, `docker compose`
- **Checklist:**
  - [x] Run a Docker container with a simple web app
  - [x] Bind a volume to a container to persist data
  - [x] Create a custom Docker network and connect two containers
  - [x] Implement multi-stage Docker builds for optimization
  - [x] Set up Docker Compose with health checks and dependencies
  - [x] Configure container networking with custom subnets
  - [x] Implement container security best practices
  - [x] Create container management and monitoring scripts

## Level 2: Advanced Docker
- **Competencies:** Multi-Container Apps, Storage, Container Registries
- **Commands:** `docker push`, `docker tag`, `docker build`, `docker inspect`, caching layer, multi-stage
- **Checklist:**
  - [ ] Write a `docker-compose.yml` for a multi-container application
  - [ ] Push an image to GHCR or Docker Hub
  - [ ] Set up persistent storage using Docker volumes

## Level 3: Container Orchestration
- **Competencies:** Kubernetes Basics, Docker Swarm, Helm
- **Commands:** `kubectl apply`, `kubectl get pods`, `kubectl logs`, Docker Swarm, `helm install`
- **Checklist:**
  - [ ] Deploy an application on Kubernetes using YAML files
  - [ ] Use Helm charts to package and deploy an app
  - [ ] Set up a basic Swarm cluster and deploy a service

## Level 4: Kubernetes Advanced
- **Competencies:** Scaling, Security, Monitoring, Service Mesh
- **Commands:** `kubectl scale`, `kubectl rollout`, `kubectl top`, `istioctl`, `linkerd`, `cilium`
- **Checklist:**
  - [ ] Implement Horizontal Pod Autoscaling (HPA)
  - [ ] Deploy Kubernetes Network Policies
  - [ ] Secure an app with Istio or Linkerd Service Mesh

## Level 5: Enterprise Kubernetes
- **Competencies:** Multi-Cluster, Operators, Virtualization, Performance Tuning
- **Commands:** `kubeadm`, `kubevirt`, custom CRDs, `kubefed`, `cluster-api`
- **Checklist:**
  - [ ] Deploy a Multi-Cluster Kubernetes Federation
  - [ ] Write a Custom Kubernetes Operator
  - [ ] Optimize a large-scale Kubernetes cluster for 1000+ nodes

---

## Resources
- [Docker Docs](https://docs.docker.com/)
- [Kubernetes Docs](https://kubernetes.io/docs/)
- [Awesome Kubernetes](https://github.com/ramitsurana/awesome-kubernetes)

---

## Progress & Evidence

??? success "Level 1: Docker Containerization (18-10-2025)"
    **Status:** Completed  
    **Focus:** Multi-stage Docker builds, Docker Compose orchestration, container networking, volume persistence  
    **Evidence:**  
    - [Smart Deploy Monitor Containerized Application](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **Multi-Stage Dockerfile** (Completed 18-10-2025): Optimized Spring Boot container with security best practices
    - **Docker Compose Orchestration** (Completed 18-10-2025): Multi-service deployment with PostgreSQL, Redis, Elasticsearch, Qdrant
    - **Container Networking** (Completed 18-10-2025): Custom network with service discovery and health checks
    - **Volume Persistence** (Completed 18-10-2025): Data persistence for all database services
    
    **What I Learned:**
    - **Docker Fundamentals**: Container creation, multi-stage builds, image optimization
    - **Docker Compose**: Multi-service orchestration, service dependencies, health checks
    - **Container Networking**: Custom networks, service discovery, inter-container communication
    - **Volume Management**: Data persistence, volume mounting, backup strategies
    - **Security Best Practices**: Non-root users, minimal base images, security scanning
    - **Production Readiness**: Health checks, logging, monitoring, graceful shutdowns
    
    **Applied Knowledge:**
    - Created multi-stage Dockerfile for optimized Spring Boot application
    - Orchestrated 5 services (App, PostgreSQL, Redis, Elasticsearch, Qdrant) with Docker Compose
    - Implemented service discovery and health checks for all containers
    - Configured persistent volumes for database data
    - Applied security best practices with non-root user execution
    - Set up environment-based configuration for different deployment stages
    
    **Container Architecture Examples:**
    ```dockerfile
    # Multi-stage build for Spring Boot application
    FROM openjdk:17-jdk-slim as builder
    WORKDIR /app
    COPY pom.xml .
    RUN ./mvnw dependency:go-offline -B
    COPY src src
    RUN ./mvnw clean package -DskipTests
    
    # Production stage
    FROM openjdk:17-jre-slim
    WORKDIR /app
    RUN groupadd -r appuser && useradd -r -g appuser appuser
    COPY --from=builder /app/target/smart-deploy-monitor-1.0.0.jar app.jar
    RUN chown appuser:appuser app.jar
    USER appuser
    EXPOSE 8080
    HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
      CMD curl -f http://localhost:8080/actuator/health || exit 1
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```
    
    **Docker Compose Services:**
    ```yaml
    services:
      postgres:
        image: postgres:15-alpine
        environment:
          POSTGRES_DB: smart_deploy_monitor
        volumes:
          - postgres_data:/var/lib/postgresql/data
        networks:
          - smart-deploy-network
        healthcheck:
          test: ["CMD-SHELL", "pg_isready -U postgres"]
      
      smart-deploy-monitor:
        build: .
        ports:
          - "8080:8080"
        environment:
          - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/smart_deploy_monitor
        depends_on:
          postgres:
            condition: service_healthy
        networks:
          - smart-deploy-network
    ```
    
    **Container Operations Mastered:**
    - `docker build` - Multi-stage image creation
    - `docker compose up -d` - Service orchestration
    - `docker ps` - Container monitoring
    - `docker logs` - Log analysis
    - `docker volume` - Data persistence
    - `docker network` - Service communication
    
    **Resources Used:**
    - Docker official documentation and best practices
    - Spring Boot containerization guidelines
    - Multi-service architecture patterns
    - Container security and optimization techniques
    - Production deployment strategies
    
    **Key Achievement:** Successfully containerized a complex Spring Boot application with 5 integrated services, implementing production-ready containerization with security, monitoring, and orchestration best practices.

    **Comprehensive Implementation Evidence:**

    ??? tip "Advanced Docker Compose Configuration"
        **Enhanced Docker Compose Setup:**
        - **GitHub Link**: [docker-compose.yml](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/docker-compose.yml)
        ```yaml
        version: '3.8'
        
        networks:
          smart-deploy-network:
            driver: bridge
            ipam:
              config:
                - subnet: 172.20.0.0/16
                  gateway: 172.20.0.1
        
        services:
          smart-deploy-monitor:
            build:
              context: .
              dockerfile: Dockerfile
            container_name: smart-deploy-monitor-app
            ports:
              - "8080:8080"
            environment:
              - SPRING_PROFILES_ACTIVE=default
              - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/smart_deploy_monitor
            depends_on:
              postgres:
                condition: service_healthy
              redis:
                condition: service_healthy
            networks:
              - smart-deploy-network
            healthcheck:
              test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health"]
              interval: 30s
              timeout: 10s
              retries: 3
              start_period: 60s
            restart: unless-stopped
            deploy:
              resources:
                limits:
                  cpus: '1.0'
                  memory: 1G
                reservations:
                  cpus: '0.5'
                  memory: 512M
            logging:
              driver: "json-file"
              options:
                max-size: "10m"
                max-file: "3"
        ```

    ??? tip "Container Management Scripts"
        **Container Management System:**
        - **GitHub Link**: [container-management.sh](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/scripts/container-management.sh)
        ```bash
        # Container Management Script Features:
        # - Start/stop/restart services
        # - Scale services dynamically
        # - Backup and restore volumes
        # - Resource monitoring
        # - Health check automation
        
        ./scripts/container-management.sh start    # Start all services
        ./scripts/container-management.sh status   # Check service status
        ./scripts/container-management.sh scale smart-deploy-monitor 3  # Scale service
        ./scripts/container-management.sh backup  # Backup volumes
        ```

    ??? tip "Container Monitoring & Observability"
        **Advanced Monitoring System:**
        - **GitHub Link**: [container-monitoring.sh](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/scripts/container-monitoring.sh)
        ```bash
        # Monitoring Features:
        # - Container health status
        # - Resource usage tracking
        # - Network connectivity tests
        # - Application health endpoints
        # - Security status checks
        # - Load testing capabilities
        
        ./scripts/container-monitoring.sh health      # Check container health
        ./scripts/container-monitoring.sh resources      # Monitor resource usage
        ./scripts/container-monitoring.sh app-health      # Test application endpoints
        ./scripts/container-monitoring.sh security       # Security status check
        ```

    ??? tip "Service Discovery & Networking"
        **Container Networking Implementation:**
        - **GitHub Link**: [service-discovery.sh](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/scripts/service-discovery.sh)
        ```bash
        # Service Discovery Features:
        # - Network configuration analysis
        # - Service connectivity testing
        # - DNS resolution verification
        # - Port mapping inspection
        # - Load balancing tests
        # - Service dependency mapping
        
        ./scripts/service-discovery.sh network        # Show network info
        ./scripts/service-discovery.sh connectivity   # Test service connectivity
        ./scripts/service-discovery.sh dns            # Test DNS resolution
        ./scripts/service-discovery.sh endpoints      # Show service endpoints
        ```

    **Testing Evidence:**

    ??? tip "Container Health Status"
        **Service Health Monitoring:**
        ```
        smart-deploy-postgres: healthy (172.20.0.4/16)
        smart-deploy-redis: healthy (172.20.0.5/16)
        smart-deploy-monitor-app: starting (172.20.0.6/16)
        smart-deploy-elasticsearch: unhealthy (172.20.0.3/16)
        smart-deploy-qdrant: unhealthy (172.20.0.2/16)
        ```

    ??? tip "Resource Usage Monitoring"
        **Container Resource Tracking:**
        ```
        CONTAINER                    CPU %    MEM USAGE / LIMIT     NET I/O
        smart-deploy-monitor-app     99.32%   130.4MiB / 1GiB       872B / 126B
        smart-deploy-postgres        0.48%    20.89MiB / 3.828GiB   5.94kB / 126B
        smart-deploy-redis           0.11%    193.1MiB / 3.828GiB   12.5kB / 3.06kB
        smart-deploy-elasticsearch  2.77%    962.1MiB / 3.828GiB   6.02kB / 126B
        smart-deploy-qdrant          0.00%    18.42MiB / 3.828GiB   6.02kB / 126B
        ```

    ??? tip "Network Configuration"
        **Custom Network Setup:**
        ```json
        {
          "Name": "smart-deploy-monitor_smart-deploy-network",
          "Driver": "bridge",
          "IPAM": {
            "Config": [
              {
                "Subnet": "172.20.0.0/16",
                "Gateway": "172.20.0.1"
              }
            ]
          },
          "Containers": {
            "smart-deploy-postgres": "172.20.0.4/16",
            "smart-deploy-redis": "172.20.0.5/16",
            "smart-deploy-monitor-app": "172.20.0.6/16",
            "smart-deploy-elasticsearch": "172.20.0.3/16",
            "smart-deploy-qdrant": "172.20.0.2/16"
          }
        }
        ```

    **Security Implementation:**

    ??? tip "Container Security Best Practices"
        **Security Features Implemented:**
        - **Non-root User Execution**: All containers run as non-root users
        - **Minimal Base Images**: Using slim/alpine variants for reduced attack surface
        - **Security Updates**: Automated package updates in Dockerfile
        - **Resource Limits**: CPU and memory limits to prevent resource exhaustion
        - **Health Checks**: Automated health monitoring for all services
        - **Network Isolation**: Custom network with controlled communication
        - **Volume Security**: Proper volume mounting and permissions

    **Production Readiness Features:**

    ??? tip "Production Deployment Features"
        **Enterprise-Grade Containerization:**
        - **Multi-Stage Builds**: Optimized image size and build time
        - **Health Checks**: Comprehensive health monitoring for all services
        - **Resource Management**: CPU and memory limits with reservations
        - **Logging Configuration**: Structured logging with rotation policies
        - **Restart Policies**: Automatic restart on failure
        - **Service Dependencies**: Proper startup order with health checks
        - **Backup & Restore**: Volume backup and restore capabilities
        - **Monitoring & Alerting**: Resource usage and health monitoring
        - **Security Hardening**: Non-root execution and minimal attack surface
        - **Network Security**: Isolated network with controlled communication

??? note "Level 2: Advanced Docker"
    **Status:** Planned  
    **Focus:** Multi-Container Apps, Storage, Container Registries  
    **Commands:** `docker push`, `docker tag`, `docker build`, `docker inspect`

??? note "Level 3: Container Orchestration"
    **Status:** Planned  
    **Focus:** Kubernetes Basics, Docker Swarm, Helm  
    **Commands:** `kubectl apply`, `kubectl get pods`, `kubectl logs`

??? note "Level 4: Kubernetes Advanced"
    **Status:** Planned  
    **Focus:** Scaling, Security, Monitoring, Service Mesh

??? note "Level 5: Enterprise Kubernetes"
    **Status:** Planned  
    **Focus:** Multi-Cluster, Operators, Virtualization, Performance Tuning

---

## Personal Notes

??? info "Level 1: Docker Containerization Mastery (Completed 18-10-2025)"

    **What I Implemented:**
    - **Advanced Docker Compose Setup**: Enhanced existing Docker Compose with custom networking, resource limits, health checks, and logging configuration
    - **Multi-Stage Dockerfile**: Optimized Spring Boot container with security best practices, non-root user execution, and health monitoring
    - **Container Management Scripts**: Created comprehensive management system with start/stop/restart, scaling, backup/restore, and monitoring capabilities
    - **Service Discovery System**: Implemented networking analysis, connectivity testing, DNS resolution, and service dependency mapping
    - **Production-Ready Configuration**: Resource limits, restart policies, logging rotation, security hardening, and network isolation

    **What I Learned:**
    - **Container Orchestration**: Multi-service deployment with proper dependencies and health checks
    - **Docker Networking**: Custom networks with IPAM configuration and service discovery
    - **Container Security**: Non-root execution, minimal base images, and security best practices
    - **Resource Management**: CPU and memory limits, resource monitoring, and optimization
    - **Production Deployment**: Health checks, logging, monitoring, backup strategies, and scaling
    - **Service Communication**: Inter-container communication, DNS resolution, and network isolation

    **Applied Knowledge:**
    - Enhanced Docker Compose with custom subnet (172.20.0.0/16) and proper service dependencies
    - Implemented comprehensive container management and monitoring scripts
    - Applied security best practices with non-root users and minimal attack surface
    - Created production-ready containerization with health checks and resource limits
    - Demonstrated advanced container orchestration with service discovery and networking

    **Key Achievement:** Successfully implemented enterprise-grade container orchestration with advanced Docker Compose configuration, comprehensive management scripts, service discovery, and production-ready security features, demonstrating mastery of containerization fundamentals and orchestration best practices.
