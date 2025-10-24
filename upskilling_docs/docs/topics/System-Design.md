# System Design

**Goal:** Master the design of scalable, reliable, and maintainable systems.

**Current Level:** Level 1

---

## Level 1: System Design Fundamentals
- **Competencies:**
  - Client-server architecture
  - Monolith vs microservices
  - Basic API design
  - Load balancing
  - Caching
  - Database selection
- **Key Tools & Practices:**
  - API documentation tools (Swagger, OpenAPI)
  - Infrastructure diagrams
  - HTTP clients (Postman, cURL)
  - Resource estimation
  - Vertical vs. horizontal scaling
- **Checklist:**
  - [x] Design a URL shortener service
  - [x] Design a notification system
  - [x] Implement a rate limiter
  - [x] Map out data flow diagrams for existing services

## Level 2: Architecture Patterns & Styles
- **Competencies:**
  - Microservices vs. SOA
  - Layered architecture
  - Event-driven architecture
  - Hexagonal/clean/onion architectures
  - BFF pattern
  - CQRS basics
  - Shared-nothing architecture
- **Key Tools & Practices:**
  - API gateways
  - Diagram tools (C4 model)
  - Event modeling
  - Contract testing
  - API versioning strategies
  - Interface segregation
  - Bounded contexts
- **Checklist:**
  - [ ] Design a system using hexagonal architecture
  - [ ] Implement a BFF for mobile and web clients
  - [ ] Create an event-driven workflow
  - [ ] Redesign a monolith toward service boundaries

## Level 3: Distributed Systems
- **Competencies:**
  - Distributed transactions
  - CQRS & Event Sourcing
  - Saga pattern
  - Advanced message processing
  - Consistent hashing
  - Distributed caching
  - Idempotency
- **Key Tools & Practices:**
  - Kafka for event streaming
  - Redis/Memcached for distributed caching
  - Distributed tracing (Jaeger, Zipkin)
  - Optimistic concurrency control
  - Backward/forward compatibility
  - Data partitioning strategies
- **Checklist:**
  - [ ] Design a payment system with Saga pattern
  - [ ] Implement event sourcing for auditing
  - [ ] Design a distributed file storage system
  - [ ] Create a real-time analytics pipeline

## Level 4: Large-Scale System Design
- **Competencies:**
  - Sharding strategies
  - Replication patterns
  - Content delivery
  - Geospatial systems
  - Rate limiting at scale
  - Search architecture
  - Streaming
- **Key Tools & Practices:**
  - Database sharding techniques
  - CDN optimization
  - Vector/similarity search
  - Bloom filters
  - Geospatial indexes
  - Adaptive throttling
  - Distributed consensus (Raft, Paxos)
- **Checklist:**
  - [ ] Design a social media feed system
  - [ ] Create a recommendation engine
  - [ ] Design a large-scale chat application
  - [ ] Implement a distributed search engine

## Level 5: Enterprise Architecture
- **Competencies:**
  - Multi-region architectures
  - Global data consistency
  - Cross-region traffic
  - Disaster recovery
  - Custom distributed algorithms
  - Platform design
  - Migration strategies
- **Key Tools & Practices:**
  - Multi-region database solutions
  - Global load balancing
  - Chaos engineering
  - Site reliability engineering
  - Cost optimization
  - Data sovereignty patterns
  - Service mesh at scale
- **Checklist:**
  - [ ] Design a global e-commerce platform
  - [ ] Implement multi-region active-active architecture
  - [ ] Create an internal service deployment platform
  - [ ] Design zero-downtime system migration

---

## Resources
- [System Design Primer (GitHub)](https://github.com/donnemartin/system-design-primer)
- [Designing Data-Intensive Applications by Martin Kleppmann](https://dataintensive.net/)
- [Awesome Scalability](https://github.com/binhnguyennus/awesome-scalability)

---

## Progress & Evidence

??? success "Level 1: System Design Fundamentals (18-10-2025)"
    **Status:** Completed  
    **Focus:** Architecture patterns, API design, caching, database selection, scalability  
    **Evidence:**  
    - [Smart Deploy Monitor System Architecture](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **Multi-Database Architecture** (Completed 18-10-2025): [PostgreSQL, Redis, Elasticsearch, Qdrant integration](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/docker-compose.yml)
    - **RESTful API Design** (Completed 18-10-2025): [OpenAPI/Swagger documentation with proper HTTP methods](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/ApiDesignController.java)
    - **Caching Strategy** (Completed 18-10-2025): [Redis TTL-based caching for performance optimization](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/resources/application.yml)
    - **Microservices Patterns** (Completed 18-10-2025): [Service layer architecture with dependency injection](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/service/domain/)

    **Comprehensive Implementation Evidence:**

    ??? tip "Step 1: Client-Server Architecture Implementation"
        **HealthController Implementation:**
        - **GitHub Link**: [HealthController.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/HealthController.java)
        ```java
        @RestController
        @RequestMapping("/api/health")
        public class HealthController {
            @GetMapping("/ping")
            public ResponseEntity<Map<String, String>> ping() {
                // Basic health check for load balancers
            }
            
            @GetMapping("/detailed")
            public ResponseEntity<Map<String, Object>> detailedHealth() {
                // Comprehensive health information
            }
        }
        ```
        
        **ClientConfig Implementation:**
        - **GitHub Link**: [ClientConfig.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/ClientConfig.java)
        ```java
        @Configuration
        public class ClientConfig {
            @Bean("clientRestTemplate")
            public RestTemplate clientRestTemplate() {
                // HTTP client with timeout configuration
                factory.setConnectTimeout(5000);
                factory.setReadTimeout(10000);
            }
        }
        ```

    ??? tip "Step 2: Monolith vs Microservices Implementation"
        **UserDomainService - Business Logic Encapsulation:**
        - **GitHub Link**: [UserDomainService.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/service/domain/UserDomainService.java)
        ```java
        @Service
        @Transactional
        public class UserDomainService {
            public Optional<User> findUserById(UUID id) {
                return userRepository.findById(id);
            }
            
            public Optional<User> performComplexUserOperation(UUID userId, boolean newStatus) {
                // Complex business logic with inter-service communication
            }
        }
        ```
        
        **ApiGatewayController - Centralized Routing:**
        - **GitHub Link**: [ApiGatewayController.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/ApiGatewayController.java)
        ```java
        @RestController
        @RequestMapping("/api/gateway")
        public class ApiGatewayController {
            @GetMapping("/users")
            public ResponseEntity<List<User>> getAllUsers() {
                // Route to UserDomainService
            }
            
            @PostMapping("/notifications/email")
            public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, String> request) {
                // Route to NotificationDomainService
            }
        }
        ```
        
        **NotificationDomainService - Domain Boundaries:**
        - **GitHub Link**: [NotificationDomainService.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/service/domain/NotificationDomainService.java)

    ??? tip "Step 3: Basic API Design Patterns Implementation"
        **ApiResponse - Standardized Response Format:**
        - **GitHub Link**: [ApiResponse.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/dto/ApiResponse.java)
        ```java
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public class ApiResponse<T> {
            private boolean success;
            private String message;
            private T data;
            private String error;
            
            public static <T> ApiResponse<T> success(T data) {
                return new ApiResponse<>(true, "Success", data);
            }
        }
        ```
        
        **ApiDesignController - RESTful Endpoints:**
        - **GitHub Link**: [ApiDesignController.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/ApiDesignController.java)
        ```java
        @RestController
        @RequestMapping("/api/design")
        public class ApiDesignController {
            @GetMapping("/info")
            public ResponseEntity<ApiResponse<Map<String, Object>>> getInfo() {
                // GET endpoint with proper response format
            }
            
            @PostMapping("/contact")
            public ResponseEntity<ApiResponse<String>> sendContact(@Valid @RequestBody ContactRequest request) {
                // POST endpoint with validation
            }
        }
        ```

    **Testing Evidence:**

    ??? tip "Health Check Endpoints Testing"
        **Basic Health Check Response:**
        ```json
        {
          "service": "smart-deploy-monitor",
          "status": "UP",
          "timestamp": "1761348363913"
        }
        ```
        
        **Detailed Health Check Response:**
        ```json
        {
          "service": "smart-deploy-monitor",
          "version": "1.0.0",
          "status": "UP",
          "timestamp": 1761348368794,
          "uptime": 1761348368794
        }
        ```

    ??? tip "API Gateway Testing"
        **User Management via Gateway:**
        ```json
        {
          "success": true,
          "service": "User Domain Service",
          "count": 0,
          "message": "Users retrieved successfully via gateway",
          "users": []
        }
        ```
        
        **Email Notification via Gateway:**
        ```json
        {
          "success": true,
          "service": "Notification Domain Service",
          "message": "Email notification sent successfully via gateway"
        }
        ```

    ??? tip "API Design Patterns Testing"
        **GET Endpoint Response:**
        ```json
        {
          "success": true,
          "message": "API information retrieved",
          "data": {
            "name": "Smart Deploy Monitor API",
            "version": "1.0.0",
            "status": "operational"
          }
        }
        ```
        
        **POST Endpoint with Validation:**
        ```json
        {
          "success": true,
          "message": "Contact message received",
          "data": "Message from Ali (ali@example.com): This is a test message for API design"
        }
        ```
        
        **Error Response Format:**
        ```json
        {
          "success": false,
          "error": "This is a demonstration of error handling"
        }
        ```

    **Comprehensive Test Results:**
    - ✅ **Health Endpoints**: All health checks responding correctly (UP, READY, ALIVE status)
    - ✅ **API Gateway**: Successfully routing requests to domain services
    - ✅ **Notification Services**: Email and SMS notifications working with validation
    - ✅ **API Design**: All HTTP methods (GET, POST, PUT, DELETE) working with proper status codes
    - ✅ **Request Validation**: Comprehensive validation with detailed error messages
    - ✅ **Error Handling**: Consistent error response format across all endpoints
    - ✅ **Domain Services**: Clear business logic encapsulation and service boundaries
    - ✅ **Inter-Service Communication**: Simulated notification processing working correctly

    **System Architecture Evidence:**

    ??? tip "Smart Deploy Monitor System Architecture"
        **Multi-Layer Architecture:**
        ```
        ┌─────────────────────────────────────────────────────────────┐
        │                    Client Layer                             │
        │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐        │
        │  │   Web UI    │  │  Mobile App  │  │   API Client│        │
        │  └─────────────┘  └─────────────┘  └─────────────┘        │
        └─────────────────────────────────────────────────────────────┘
                                    │
        ┌─────────────────────────────────────────────────────────────┐
        │                  API Gateway Layer                         │
        │  ┌─────────────────────────────────────────────────────────┐│
        │  │           ApiGatewayController                          ││
        │  │  • Centralized Routing                                 ││
        │  │  • Service Orchestration                               ││
        │  │  • Request/Response Transformation                     ││
        │  └─────────────────────────────────────────────────────────┘│
        └─────────────────────────────────────────────────────────────┘
                                    │
        ┌─────────────────────────────────────────────────────────────┐
        │                 Domain Services Layer                       │
        │  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐│
        │  │ UserDomainService│  │NotificationDomain│  │ HealthController││
        │  │ • User Management│  │ • Email/SMS/Push│  │ • Health Checks││
        │  │ • Business Logic│  │ • Notifications │  │ • System Status││
        │  └─────────────────┘  └─────────────────┘  └─────────────────┘│
        └─────────────────────────────────────────────────────────────┘
                                    │
        ┌─────────────────────────────────────────────────────────────┐
        │                  Data Access Layer                         │
        │  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐│
        │  │   PostgreSQL    │  │     Redis       │  │  Elasticsearch  ││
        │  │ • User Data     │  │ • Caching       │  │ • Search/Filter ││
        │  │ • Transactions │  │ • Sessions      │  │ • Analytics     ││
        │  └─────────────────┘  └─────────────────┘  └─────────────────┘│
        └─────────────────────────────────────────────────────────────┘
        ```

    **Deployment Evidence:**

    ??? tip "Docker Compose Multi-Service Architecture"
        **Services Configuration:**
        - **GitHub Link**: [docker-compose.yml](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/docker-compose.yml)
        ```yaml
        services:
          smart-deploy-monitor:
            build: .
            ports:
              - "8080:8080"
            depends_on:
              - postgres
              - redis
              - elasticsearch
              - qdrant
              - keycloak
          
          postgres:
            image: postgres:15
            environment:
              POSTGRES_DB: smart_deploy_monitor
              POSTGRES_USER: admin
              POSTGRES_PASSWORD: password
          
          redis:
            image: redis:7-alpine
            ports:
              - "6379:6379"
          
          elasticsearch:
            image: elasticsearch:8.11.0
            environment:
              - discovery.type=single-node
              - xpack.security.enabled=false
        ```

    **Performance & Monitoring Evidence:**

    ??? tip "System Monitoring & Observability"
        **Health Check Endpoints:**
        - `/api/health/ping` - Basic health check (UP/DOWN)
        - `/api/health/detailed` - Comprehensive system information
        - `/api/health/ready` - Readiness check for traffic acceptance
        - `/api/health/live` - Liveness check for service monitoring
        
        **Monitoring Metrics:**
        - System uptime tracking
        - Memory usage monitoring
        - CPU core utilization
        - Active connections count
        - Request processing metrics
        
        **Logging Evidence:**
        ```
        2025-10-25 03:24:02 [scheduling-1] INFO  c.u.s.service.MonitoringService - 
        System Status - Uptime: 0s, Memory: 127.95/178.00MB, CPU Cores: 8, 
        Requests: 0, Connections: 23
        ```

    **Security Implementation Evidence:**

    ??? tip "Security Configuration & Authentication"
        **Spring Security Configuration:**
        - **GitHub Link**: [SecurityConfig.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/SecurityConfig.java)
        ```java
        @Configuration
        @EnableWebSecurity
        public class SecurityConfig {
            @Bean
            public SecurityFilterChain filterChain(HttpSecurity http) {
                return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/health/**").permitAll()
                        .requestMatchers("/api/design/**").permitAll()
                        .requestMatchers("/api/gateway/**").permitAll()
                        .anyRequest().authenticated()
                    )
                    .build();
            }
        }
        ```
        
        **JWT Authentication:**
        - **GitHub Link**: [AuthService.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/service/AuthService.java)
        - Token-based authentication implemented
        - Access and refresh token support
        - Secure password hashing with BCrypt
        - Role-based access control (RBAC)

    **What I Learned:**
    - **Architecture Patterns**: Layered architecture with Controller-Service-Repository pattern
    - **API Design**: RESTful principles with proper HTTP methods and status codes
    - **Database Selection**: Right database for right use case (PostgreSQL, Redis, Elasticsearch, Qdrant)
    - **Caching Strategy**: TTL-based caching with Redis for performance optimization
    - **Scalability Patterns**: Horizontal scaling considerations and load balancing
    - **System Integration**: Multi-service architecture with proper separation of concerns
    
    **Applied Knowledge:**
    - Designed multi-database architecture for different data types and access patterns
    - Implemented RESTful API with OpenAPI documentation and validation
    - Applied caching strategies for frequently accessed data
    - Used dependency injection for loose coupling and testability
    - Implemented proper error handling and response formatting
    - Applied microservices patterns within a monolithic application
    
    **Architecture Examples:**
    ```java
    // Layered Architecture Pattern
    @RestController
    public class UserController {
        @Autowired
        private UserService userService;
        
        @PostMapping("/api/users")
        public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDTO userDTO) {
            return ResponseEntity.ok(userService.createUser(userDTO));
        }
    }
    
    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;
        
        @Autowired
        private RedisService redisService;
        
        public UserResponse createUser(UserDTO userDTO) {
            // Business logic with caching
            User user = userRepository.save(convertToEntity(userDTO));
            redisService.setWithTTL("user:" + user.getId(), user, 3600);
            return convertToResponse(user);
        }
    }
    ```
    
    **System Design Principles Applied:**
    - **Single Responsibility**: Each service handles one concern
    - **Dependency Inversion**: Depend on abstractions, not concretions
    - **Open/Closed**: Open for extension, closed for modification
    - **Interface Segregation**: Small, focused interfaces
    - **DRY Principle**: Don't repeat yourself
    
    **Resources Used:**
    - Spring Boot architecture patterns and best practices
    - RESTful API design principles
    - Database selection criteria and patterns
    - Caching strategies and performance optimization
    - System design principles and scalability patterns
    
    **Key Achievement:** Successfully designed and implemented a scalable system architecture with multi-database integration, RESTful APIs, caching strategies, and proper separation of concerns, demonstrating professional system design skills.

??? note "Level 2: Design Patterns"
    **Status:** Planned  
    **Focus:** Architecture patterns, CQRS, BFF, shared-nothing

??? note "Level 3: Distributed Systems"
    **Status:** Planned  
    **Focus:** Distributed systems, saga, event sourcing

??? note "Level 4: Advanced Systems"
    **Status:** Planned  
    **Focus:** Sharding, geospatial, streaming systems

??? note "Level 5: Global Scale"
    **Status:** Planned  
    **Focus:** Multi-region, global consistency, custom algorithms

---

## Personal Notes

??? info "Step 1: Client-Server Architecture (Completed 18-10-2025)"

    **What I Implemented:**
    - **HealthController**: Created comprehensive health check endpoints demonstrating client-server communication patterns
      - `/api/health/ping` - Basic health check for load balancers
      - `/api/health/detailed` - Comprehensive health information
      - `/api/health/ready` - Readiness check for traffic acceptance
      - `/api/health/live` - Liveness check for service monitoring
    - **ClientConfig**: Implemented HTTP client configuration with proper timeout management
      - Connection timeout configuration (5 seconds)
      - Read timeout configuration (10 seconds)
      - Error handling for better client-server communication
      - RestTemplate bean configuration for dependency injection

    **What I Learned:**
    - **Health Check Patterns**: Different types of health checks serve different purposes in client-server architecture
    - **Timeout Management**: Proper timeout configuration prevents hanging connections and improves reliability
    - **Error Handling**: Implementing proper error handling in HTTP clients improves system resilience
    - **RESTful Design**: Health endpoints follow RESTful principles and provide clear client-server contracts

    **Applied Knowledge:**
    - Implemented health check endpoints that clients can use for monitoring and load balancing
    - Configured HTTP clients with appropriate timeouts for production environments
    - Applied dependency injection patterns for client configuration
    - Created clear API contracts for client-server communication

    **Key Achievement:** Successfully implemented client-server architecture patterns with comprehensive health checks and proper HTTP client configuration, demonstrating understanding of client-server communication patterns and system monitoring requirements.

??? info "Step 2: Monolith vs Microservices (Completed 18-10-2025)"

    **What I Implemented:**
    - **UserDomainService**: Created comprehensive domain service for user management
      - Business logic encapsulation for user creation, updates, and validation
      - Domain boundaries that could be extracted into a microservice
      - Business validation and error handling
    - **NotificationDomainService**: Implemented notification domain service
      - Email, SMS, and push notification business logic
      - Domain encapsulation for notification processing
      - Inter-service communication simulation
    - **ApiGatewayController**: Created centralized routing controller
      - Centralized entry point for all domain services
      - API Gateway pattern implementation
      - Service orchestration and routing

    **What I Learned:**
    - **Domain Boundaries**: Clear separation of business logic into domain services
    - **Service Encapsulation**: Each domain service encapsulates its own business logic
    - **API Gateway Pattern**: Centralized routing and service orchestration
    - **Monolith Evolution**: How to structure a monolith for future microservices extraction

    **Applied Knowledge:**
    - Implemented domain services with clear business boundaries
    - Created API Gateway pattern for centralized routing
    - Applied domain-driven design principles
    - Demonstrated how monolith can evolve toward microservices

    **Key Achievement:** Successfully implemented monolith vs microservices patterns with domain services, API Gateway routing, and clear service boundaries, demonstrating understanding of how to structure applications for future microservices evolution.

??? info "Step 3: Basic API Design Patterns (Completed 18-10-2025)"

    **What I Implemented:**
    - **ApiResponse**: Created standardized response format for consistent API responses
      - Success and error response handling
      - Generic type support for different data types
      - Consistent JSON structure across all endpoints
    - **ApiDesignController**: Implemented comprehensive API design patterns
      - GET /api/design/info: API information endpoint with proper response format
      - POST /api/design/contact: Contact form with request validation
      - PUT /api/design/settings/{id}: Settings update with proper HTTP status codes
      - DELETE /api/design/data/{id}: Data deletion with confirmation
      - Error handling demonstration with proper error responses
    - **Request Validation**: Implemented comprehensive input validation
      - @Valid, @NotBlank, @Email, @Size annotations
      - Detailed error messages for validation failures
      - Proper HTTP status codes for different error types

    **What I Learned:**
    - **API Design Principles**: Consistent response format and proper HTTP status codes
    - **Request Validation**: Input validation with detailed error messages
    - **RESTful Design**: Proper use of HTTP methods (GET, POST, PUT, DELETE)
    - **Error Handling**: User-friendly error responses and proper status codes
    - **Response Formatting**: Standardized API response structure

    **Applied Knowledge:**
    - Implemented consistent API response format across all endpoints
    - Applied proper HTTP status codes (200, 201, 400) for different scenarios
    - Created comprehensive request validation with detailed error messages
    - Designed RESTful endpoints following API design best practices
    - Implemented proper error handling with user-friendly messages

    **Key Achievement:** Successfully implemented basic API design patterns with consistent response format, proper HTTP status codes, comprehensive request validation, and RESTful endpoint design, demonstrating understanding of essential API design principles and best practices.
