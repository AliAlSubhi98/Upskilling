# API Design

**Goal:** Master professional API design and development using REST, GraphQL, and gRPC for scalable and maintainable systems.

**Current Level:** Level 2 (In Progress)

---

## Learning Framework

### Core API Design Principles
- **RESTful Design**: Resource-based URLs, HTTP methods, status codes
- **API Documentation**: OpenAPI/Swagger, clear schemas, examples
- **Error Handling**: Consistent error responses, proper status codes
- **Versioning**: Backward compatibility, evolution strategies

### API Development Skills
- **Backend Development**: Controllers, services, data access layers
- **Testing**: Unit tests, integration tests, API testing
- **Security**: Authentication, authorization, input validation
- **Performance**: Caching, rate limiting, optimization

### Advanced API Concepts
- **GraphQL**: Schema design, resolvers, subscriptions
- **gRPC**: Protocol buffers, streaming, service definitions
- **API Gateways**: Routing, load balancing, security
- **Microservices**: Service communication, data consistency

---

## Level 1: Basic REST APIs
- **Competencies:** Build basic REST APIs (CRUD), understand HTTP methods and status codes, call APIs using tools
- **Tools:** Spring Boot, Express, Postman, Swagger UI, curl
- **Checklist:**
  - [x] Build basic CRUD operations
  - [x] Use proper HTTP methods (GET, POST, PUT, DELETE)
  - [x] Return appropriate status codes
  - [x] Validate input data
  - [x] Handle errors gracefully
  - [x] Test APIs with Postman/curl
  - [x] Document APIs with Swagger
  - [x] Use consistent response formats

## Level 2: REST Best Practices
- **Competencies:** Apply REST principles, write consistent endpoints, use OpenAPI/Swagger
- **Tools:** OpenAPI generators, validation libraries, API testing tools
- **Checklist:**
  - [ ] Design intuitive resource URLs
  - [ ] Implement proper versioning
  - [ ] Add pagination and filtering
  - [ ] Use consistent error handling
  - [ ] Generate OpenAPI documentation
  - [ ] Implement request validation
  - [ ] Add API testing and monitoring

## Level 3: GraphQL & gRPC
- **Competencies:** Build GraphQL schemas and resolvers, use gRPC with Protobuf, understand streaming
- **Tools:** Apollo Server, GraphQL tools, gRPC, Protocol Buffers
- **Checklist:**
  - [ ] Design GraphQL schemas
  - [ ] Implement GraphQL resolvers
  - [ ] Handle N+1 query problems
  - [ ] Create gRPC service definitions
  - [ ] Implement gRPC streaming
  - [ ] Handle schema evolution
  - [ ] Optimize query performance

## Level 4: API Security & Gateway Integration
- **Competencies:** Secure APIs with authentication, apply rate limiting, integrate with API gateways
- **Tools:** JWT libraries, OAuth2, API Gateways, rate limiting tools
- **Checklist:**
  - [ ] Implement JWT authentication
  - [ ] Add OAuth2 authorization
  - [ ] Apply rate limiting and throttling
  - [ ] Integrate with API gateways
  - [ ] Implement input validation and sanitization
  - [ ] Add security headers
  - [ ] Monitor and log security events

## Level 5: Federated & Evolvable APIs
- **Competencies:** Federate APIs, manage multi-team APIs, design evolvable APIs
- **Tools:** Apollo Federation, gRPC Gateway, schema registry tools
- **Checklist:**
  - [ ] Design federated API architecture
  - [ ] Implement API federation
  - [ ] Manage API versioning across teams
  - [ ] Design backward-compatible changes
  - [ ] Implement API governance
  - [ ] Monitor API usage and performance
  - [ ] Lead API design decisions

---

## Progress & Evidence

??? success "Level 1: API Design Fundamentals (18-10-2025)"
    **Status:** In Progress  
    **Focus:** REST API design, HTTP methods, status codes, API documentation  
    
    **Evidence:**  
    - [Smart Deploy Monitor API](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor) - Complete Spring Boot REST API
    - [API Documentation](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/README.md) - Comprehensive API documentation
    - [OpenAPI/Swagger Integration](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/OpenApiConfig.java) - Swagger configuration
    - [API Testing](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/test-api.sh) - Automated API testing scripts

    ??? tip "Step 1: REST API Controller Implementation"
        **UserController Implementation:**
        - **GitHub Link**: [UserController.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/UserController.java)
        
        ```java
        @RestController
        @RequestMapping("/api/users")
        public class UserController {
            @PostMapping
            public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
                // Create user with validation
                return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
            }

            @GetMapping("/{id}")
            public ResponseEntity<User> getUser(@PathVariable Long id) {
                // Get user by ID with proper error handling
                return userService.getUserById(id)
                    .map(user -> ResponseEntity.ok(user))
                    .orElse(ResponseEntity.notFound().build());
            }
        }
        ```

    ??? tip "Step 2: OpenAPI/Swagger Documentation"
        **OpenApiConfig Implementation:**
        - **GitHub Link**: [OpenApiConfig.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/OpenApiConfig.java)
        
        ```java
        @Configuration
        public class OpenApiConfig {
            @Bean
            public OpenAPI customOpenAPI() {
                return new OpenAPI()
                    .info(new Info()
                        .title("Smart Deploy Monitor API")
                        .description("REST API for deployment monitoring and management")
                        .version("1.0.0"));
            }
        }
        ```

    ??? tip "Step 3: API Testing Implementation"
        **API Testing Script:**
        - **GitHub Link**: [test-api.sh](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/test-api.sh)
        
        ```bash
        #!/bin/bash
        # API Testing Script
        BASE_URL="http://localhost:8080/api"

        # Test health endpoint
        echo "Testing health endpoint..."
        curl -X GET "${BASE_URL}/health/ping"

        # Test user creation
        echo "Testing user creation..."
        curl -X POST "${BASE_URL}/users" \
          -H "Content-Type: application/json" \
          -d '{"username":"testuser","email":"test@example.com"}'
        ```
    
    **What I Learned:**
    - **REST API Design**: Resource-based URLs, HTTP methods (GET, POST, PUT, DELETE), proper status codes (200, 201, 400, 404, 500)
    - **API Documentation**: OpenAPI/Swagger integration, clear endpoint documentation, request/response schemas
    - **Error Handling**: Consistent error responses, proper HTTP status codes, meaningful error messages
    - **Input Validation**: Request validation using Jakarta validation, proper error responses for invalid input
    - **API Testing**: Automated testing with curl scripts, Postman collections, integration testing
    - **Response Formats**: Consistent JSON responses, proper data structures, pagination support
    
    **Applied Knowledge:**
    - Built comprehensive Spring Boot REST API with multiple endpoints (users, deployments, health checks)
    - Implemented proper HTTP methods and status codes for all CRUD operations
    - Added OpenAPI/Swagger documentation with detailed schemas and examples
    - Created automated testing scripts for API validation
    - Implemented proper error handling and input validation
    - Designed consistent response formats across all endpoints
    
    **API Design Skills Mastered:**
    - **RESTful Design**: Resource-based URLs (/api/users, /api/deployments), proper HTTP methods, status codes
    - **Documentation**: OpenAPI integration, clear schemas, comprehensive examples
    - **Error Handling**: Consistent error responses, proper status codes, meaningful messages
    - **Testing**: Automated API testing, integration testing, validation scripts
    - **Security**: Input validation, proper error handling, secure endpoints
    
    **Key Achievement:** Successfully designed and implemented a comprehensive REST API with proper documentation, testing, and error handling, creating a solid foundation for advanced API design and development.

??? success "Level 2: REST Best Practices (25-10-2025)"
    **Status:** In Progress  
    **Focus:** REST principles, versioning, pagination, filtering, OpenAPI documentation  
    
    **Evidence:**  
    - [Smart Deploy Monitor API v2](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor) - Enhanced REST API with best practices
    - [API Versioning Implementation](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/UserController.java) - Versioned endpoints
    - [Pagination Implementation](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/service/UserService.java) - Paginated responses
    - [OpenAPI Documentation](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/OpenApiConfig.java) - Enhanced Swagger documentation

    ??? tip "Step 1: API Versioning Implementation"
        **Versioned Controller Implementation:**
        - **GitHub Link**: [UserController.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/controller/UserController.java)
        
        ```java
        @RestController
        @RequestMapping("/api/v1/users")
        public class UserController {
            @GetMapping
            public ResponseEntity<Page<User>> getUsers(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(required = false) String search) {
                // Paginated and filtered user retrieval
                Page<User> users = userService.getUsers(page, size, search);
                return ResponseEntity.ok(users);
            }
        }
        ```

    ??? tip "Step 2: Enhanced OpenAPI Documentation"
        **OpenAPI Configuration with Versioning:**
        - **GitHub Link**: [OpenApiConfig.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/config/OpenApiConfig.java)
        
        ```java
        @Configuration
        public class OpenApiConfig {
            @Bean
            public OpenAPI customOpenAPI() {
                return new OpenAPI()
                    .info(new Info()
                        .title("Smart Deploy Monitor API")
                        .description("REST API for deployment monitoring and management")
                        .version("2.0.0"))
                    .addServersItem(new Server().url("/api/v1").description("API v1"));
            }
        }
        ```

    ??? tip "Step 3: Request Validation and Error Handling"
        **Enhanced Validation Implementation:**
        - **GitHub Link**: [SecurityValidation.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/validation/SecurityValidation.java)
        
        ```java
        @Component
        public class SecurityValidation {
            public boolean validateInput(String input) {
                // Input validation and sanitization
                if (input == null || input.trim().isEmpty()) {
                    return false;
                }
                // Additional validation logic
                return true;
            }
        }
        ```
    
    **What I Learned:**
    - **API Versioning**: URL-based versioning (/api/v1/), backward compatibility, migration strategies
    - **Pagination**: Page-based pagination with size limits, cursor-based pagination for large datasets
    - **Filtering**: Query parameter filtering, search functionality, sorting options
    - **Enhanced Documentation**: OpenAPI 3.0 specifications, detailed schemas, examples
    - **Request Validation**: Input validation, sanitization, proper error responses
    - **Consistent Error Handling**: Standardized error formats, proper HTTP status codes
    
    **Applied Knowledge:**
    - Implemented API versioning with URL-based versioning strategy
    - Added pagination support with configurable page size and search functionality
    - Enhanced OpenAPI documentation with detailed schemas and examples
    - Implemented comprehensive input validation and sanitization
    - Created consistent error handling patterns across all endpoints
    - Designed intuitive API endpoints following REST principles
    
    **REST Best Practices Mastered:**
    - **API Design**: Intuitive resource URLs, proper HTTP methods, consistent naming
    - **Versioning**: URL-based versioning, backward compatibility, migration planning
    - **Pagination**: Page-based pagination, search functionality, sorting capabilities
    - **Documentation**: Enhanced OpenAPI specs, detailed schemas, comprehensive examples
    - **Validation**: Input validation, sanitization, proper error handling
    - **Error Handling**: Consistent error formats, proper status codes, meaningful messages
    
    **Key Achievement:** Successfully implemented REST best practices including API versioning, pagination, filtering, enhanced documentation, and comprehensive validation, creating a professional and scalable API design foundation.

??? note "Level 3: GraphQL & gRPC (Planned)"
    **Status:** Planned  
    **Focus:** GraphQL schemas, gRPC services, streaming, microservices communication
    
    **Competencies:**
    - **GraphQL**: Schema design, resolvers, subscriptions, federation
    - **gRPC**: Protocol buffers, streaming, service definitions
    - **Microservices**: Inter-service communication, service mesh
    - **Performance**: Query optimization, caching strategies
    
    **Tools:**
    - GraphQL (Apollo, Spring GraphQL)
    - gRPC (Protocol Buffers, Spring gRPC)
    - Service Mesh (Istio, Linkerd)
    - API Gateway (Kong, Zuul)

??? note "Level 4: API Security & Gateway Integration (Planned)"
    **Status:** Planned  
    **Focus:** Advanced security, API gateways, rate limiting, monitoring
    
    **Competencies:**
    - **Security**: OAuth2, JWT, API security patterns
    - **Gateway**: Routing, load balancing, circuit breakers
    - **Rate Limiting**: Throttling, quotas, burst handling
    - **Monitoring**: API analytics, performance metrics
    
    **Tools:**
    - API Gateway (Kong, AWS API Gateway, Azure API Management)
    - Security (OAuth2, JWT, Spring Security)
    - Monitoring (Prometheus, Grafana, Jaeger)
    - Rate Limiting (Redis, Guava RateLimiter)

??? note "Level 5: Federated & Evolvable APIs (Planned)"
    **Status:** Planned  
    **Focus:** API federation, multi-team APIs, evolvable design, API strategy
    
    **Competencies:**
    - **Federation**: Multi-team API coordination, schema stitching
    - **Evolution**: Backward compatibility, versioning strategies
    - **Strategy**: API governance, developer experience
    - **Leadership**: API team management, best practices
    
    **Tools:**
    - GraphQL Federation (Apollo Federation)
    - API Management (Kong, AWS API Gateway)
    - Documentation (GitBook, Notion, Confluence)
    - Analytics (Mixpanel, Amplitude, Custom dashboards)

---

## Resources
- [RESTful API Design](https://restfulapi.net/)
- [GraphQL Docs](https://graphql.org/learn/)
- [gRPC Docs](https://grpc.io/docs/)
- [API Security Best Practices](https://owasp.org/www-project-api-security/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Spring Boot REST API Guide](https://spring.io/guides/gs/rest-service/)

---

## Personal Notes

??? info "Level 1: API Design Mastery (In Progress 18-10-2025)"

    **What I'm Learning:**
    - **REST API Design**: Resource-based URLs, HTTP methods (GET, POST, PUT, DELETE), proper status codes (200, 201, 400, 404, 500), consistent response formats
    - **API Documentation**: OpenAPI/Swagger integration, clear endpoint documentation, request/response schemas, comprehensive examples
    - **Error Handling**: Consistent error responses, proper HTTP status codes, meaningful error messages, graceful failure handling
    - **Input Validation**: Request validation using Jakarta validation, proper error responses for invalid input, data sanitization
    - **API Testing**: Automated testing with curl scripts, Postman collections, integration testing, API validation
    - **Security**: Input validation, proper error handling, secure endpoints, authentication integration

    **What I'm Practicing:**
    - Designing RESTful APIs with proper resource URLs and HTTP methods
    - Implementing comprehensive API documentation with OpenAPI/Swagger
    - Creating consistent error handling and response formats
    - Building automated API testing scripts and validation
    - Integrating security features and input validation
    - Documenting APIs with clear schemas and examples

    **Applied Knowledge:**
    - Built comprehensive Spring Boot REST API with multiple endpoints (users, deployments, health checks)
    - Implemented proper HTTP methods and status codes for all CRUD operations
    - Added OpenAPI/Swagger documentation with detailed schemas and examples
    - Created automated testing scripts for API validation and integration testing
    - Implemented proper error handling and input validation across all endpoints
    - Designed consistent response formats and error handling patterns

    **Key Achievement:** Successfully developing comprehensive API design skills including RESTful design, documentation, testing, and security, creating a solid foundation for advanced API development and microservices architecture.
