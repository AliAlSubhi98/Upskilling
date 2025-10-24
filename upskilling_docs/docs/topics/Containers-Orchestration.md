# Containers & Orchestration

**Goal:** Master containerization with Docker and orchestrate scalable deployments using Kubernetes.

**Current Level:** Level 1

---

## Level 1: Docker Basics
- **Competencies:** Install, Pull, Run, Stop Containers, Volumes, Networks, Docker Compose
- **Commands:** Writing your first Dockerfile, `docker run`, `docker ps`, `docker stop`, `docker rm`, `docker volume`, `docker network`, `docker compose`
- **Checklist:**
  - [x] Run a Docker container with a simple web app
  - [x] Bind a volume to a container to persist data
  - [x] Create a custom Docker network and connect two containers

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
