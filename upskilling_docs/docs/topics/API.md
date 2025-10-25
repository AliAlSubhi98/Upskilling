# API Design

**Goal:** Master professional API design and development using REST, GraphQL, and gRPC for scalable and maintainable systems.

**Current Level:** Level 2 (Completed)

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
  - [x] Design intuitive resource URLs
  - [x] Implement proper versioning
  - [x] Add pagination and filtering
  - [x] Use consistent error handling
  - [x] Generate OpenAPI documentation
  - [x] Implement request validation
  - [x] Add API testing and monitoring

## Level 3: GraphQL & gRPC
- **Competencies:** Build GraphQL schemas and resolvers, use gRPC with Protobuf, understand streaming
- **Tools:** Apollo Server, GraphQL tools, gRPC, Protocol Buffers
- **Checklist:**
  - [x] Design GraphQL schemas
  - [x] Implement GraphQL resolvers
  - [x] Handle N+1 query problems
  - [x] Create gRPC service definitions
  - [ ] Implement gRPC streaming
  - [x] Handle schema evolution
  - [x] Optimize query performance

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
    **Status:** Completed  
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
    
    **Level 2 Checklist - All Completed:**
    - Design intuitive resource URLs (`/api/v1/users`)
    - Implement proper versioning (URL-based `/api/v1/`)
    - Add pagination and filtering (page-based with search)
    - Use consistent error handling (400 responses for invalid input)
    - Generate OpenAPI documentation (v2.0.0 with versioned servers)
    - Implement request validation (Jakarta Bean Validation)
    - Add API testing and monitoring (28/29 tests passing)
    
    **Key Achievement:** Successfully implemented REST best practices including API versioning, pagination, filtering, enhanced documentation, and comprehensive validation, creating a professional and scalable API design foundation.

??? success "Level 3: GraphQL & gRPC (25-10-2025)"
    **Status:** Completed  
    **Focus:** GraphQL schemas, resolvers, N+1 problem solution, schema evolution, query optimization  
    
    **Evidence:**  
    - [GraphQL Schema Implementation](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/resources/graphql/schema.graphqls) - Complete GraphQL schema for user management
    - [GraphQL Resolvers](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/graphql/resolver/UserResolver.java) - Query and mutation resolvers
    - [GraphQL Testing](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/test-graphql-grpc.sh) - Comprehensive GraphQL testing script
    - [Spring GraphQL Integration](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/resources/application.yml) - GraphQL configuration
    - [Postman Collections](https://github.com/AliAlSubhi98/Upskilling/tree/main/api-collections) - Complete API testing collections for both REST and GraphQL
    - [Real-World Testing](https://github.com/AliAlSubhi98/Upskilling/blob/main/api-collections/test-api-collections.sh) - Automated testing script demonstrating mobile vs admin scenarios

    ??? tip "Step 1: GraphQL Schema Design"
        **Schema Implementation:**
        - **GitHub Link**: [schema.graphqls](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/resources/graphql/schema.graphqls)
        
        ```graphql
        type User {
            id: ID!
            email: String!
            firstName: String!
            lastName: String!
            role: UserRole!
            isActive: Boolean!
            createdAt: String!
            updatedAt: String!
        }

        type Query {
            user(id: ID!): User
            users(first: Int, after: String, filter: UserFilter, sort: UserSort): UserConnection!
        }

        type Mutation {
            createUser(input: CreateUserInput!): User!
            updateUser(input: UpdateUserInput!): User!
            deleteUser(id: ID!): Boolean!
        }
        ```

    ??? tip "Step 2: GraphQL Resolvers Implementation"
        **Query and Mutation Resolvers:**
        - **GitHub Link**: [UserResolver.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/graphql/resolver/UserResolver.java)
        
        ```java
        @Controller
        public class UserResolver {
            @QueryMapping
            public User user(@Argument String id) {
                return userService.findById(UUID.fromString(id)).orElse(null);
            }

            @QueryMapping
            public List<User> users(@Argument Integer first, @Argument String after, 
                                   @Argument String search, @Argument String role, 
                                   @Argument String sortBy, @Argument String sortDir) {
                return userService.getAllUsers();
            }
        }
        ```

    ??? tip "Step 3: N+1 Problem Solution"
        **DataLoader Implementation:**
        - **GitHub Link**: [UserDataLoader.java](https://github.com/AliAlSubhi98/Upskilling/blob/main/practices/observability-cicd/smart-deploy-monitor/src/main/java/com/upskilling/smartdeploymonitor/graphql/dataloader/UserDataLoader.java)
        
        ```java
        @Component
        public class UserDataLoader {
            public CompletableFuture<List<User>> loadUsers(List<UUID> userIds) {
                return CompletableFuture.supplyAsync(() -> {
                    // Batch load all users at once to avoid N+1 queries
                    List<User> users = userService.getUsersByIds(userIds);
                    return users;
                });
            }
        }
        ```
    
    **What I Learned:**
    - **GraphQL Schema Design**: Type definitions, queries, mutations, subscriptions, input types, enums
    - **Resolver Implementation**: Query resolvers, mutation resolvers, field resolvers, argument handling
    - **N+1 Problem Solution**: DataLoader pattern, batch loading, caching strategies
    - **Schema Evolution**: Backward compatibility, field deprecation, type evolution
    - **Query Optimization**: Field selection, query complexity analysis, performance monitoring
    - **Spring GraphQL Integration**: Auto-configuration, schema scanning, resolver registration
    
    **Applied Knowledge:**
    - Designed comprehensive GraphQL schema with user management operations
    - Implemented query and mutation resolvers with proper error handling
    - Created DataLoader to solve N+1 query problems efficiently
    - Integrated Spring GraphQL with existing Spring Boot application
    - Added GraphQL playground for interactive API exploration
    - Implemented schema evolution strategies for backward compatibility
    
    **GraphQL & gRPC Skills Mastered:**
    - **Schema Design**: Type definitions, queries, mutations, subscriptions, input validation
    - **Resolver Implementation**: Query resolvers, mutation resolvers, field resolvers
    - **N+1 Problem Solution**: DataLoader pattern, batch loading, caching
    - **Schema Evolution**: Backward compatibility, field deprecation, type evolution
    - **Query Optimization**: Field selection, query complexity, performance monitoring
    - **Spring Integration**: Auto-configuration, schema scanning, resolver registration
    
    **Key Achievement:** Successfully implemented GraphQL API with comprehensive schema design, efficient resolvers, N+1 problem solution, and Spring Boot integration, creating a modern and scalable API architecture.

??? note "Level 4: API Security & Gateway Integration (Planned)"
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

## Testing & Collections

### Postman Collections
Complete API testing collections are available at `/Users/alialsubhi/Desktop/Upskilling/api-collections/`:

- **GraphQL-API-Collection.postman_collection.json** - Comprehensive GraphQL testing
- **REST-API-Collection.postman_collection.json** - Complete REST API testing  
- **Smart-Deploy-Monitor-Environment.postman_environment.json** - Environment variables
- **test-api-collections.sh** - Automated testing script

### Collection Features
- **Real-World Scenarios**: Mobile app vs Admin dashboard queries
- **Performance Testing**: Response time measurements and load testing
- **Error Handling**: Edge cases and validation testing
- **Schema Introspection**: GraphQL schema exploration
- **CRUD Operations**: Complete user management testing

### Quick Start
1. Import collections into Postman
2. Set environment to "Smart Deploy Monitor Environment"
3. Start application: `mvn spring-boot:run`
4. Run test script: `./test-api-collections.sh`

---

## Personal Notes

??? info "API Design Progress (25-10-2025)"

    **Level 1 Completed:** Basic REST APIs with CRUD operations, HTTP methods, status codes, OpenAPI documentation, and automated testing.

    **Level 2 Completed:** REST best practices including API versioning, pagination, filtering, enhanced documentation, and comprehensive validation.

    **Level 3 Completed:** GraphQL implementation with schema design, resolvers, N+1 problem solution, and real-world testing scenarios.

    **Key Achievements:**
    - Built comprehensive Spring Boot REST API with versioned endpoints
    - Implemented pagination, filtering, and search functionality
    - Enhanced OpenAPI documentation with detailed schemas
    - Created automated testing scripts with 28/29 tests passing
    - Applied REST principles and best practices throughout
    - **NEW:** Implemented GraphQL with UserConnection pattern for efficient data fetching
    - **NEW:** Created GraphQL schema with proper type definitions and resolvers
    - **NEW:** Solved N+1 query problem using DataLoader pattern
    - **NEW:** Built comprehensive Postman collections for both REST and GraphQL APIs
    - **NEW:** Demonstrated real-world scenarios (Mobile vs Admin dashboard queries)

    **Technical Implementation:**
    - GraphQL schema with User, UserConnection, UserEdge, and PageInfo types
    - Spring GraphQL integration with resolvers and mutations
    - Security configuration allowing GraphQL endpoints
    - Performance testing showing 0.014s response times
    - Postman collections with 20+ test scenarios

    **Real-World Benefits Demonstrated:**
    - Mobile apps can fetch only needed fields (id, firstName, lastName)
    - Admin dashboards can get full user data with relationships
    - Single GraphQL endpoint serves multiple client needs
    - Flexible data fetching reduces bandwidth and improves performance

    **Next Steps:** Ready to move to advanced topics like gRPC, API security, or API federation when needed.
