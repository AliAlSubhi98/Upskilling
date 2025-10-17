# API Design

**Goal:** Design and implement well-structured, documented, and maintainable REST APIs.

**Current Level:** Level 1

---

## Level 1: REST API Fundamentals
- **Competencies:** RESTful Design, HTTP Methods, Status Codes, API Documentation
- **Tools:** OpenAPI/Swagger, Spring Boot, Validation, Postman/curl
- **Checklist:**
  - [x] Design RESTful API endpoints following HTTP standards
  - [x] Implement proper HTTP status codes and error handling
  - [x] Add comprehensive API documentation with OpenAPI/Swagger
  - [x] Implement request validation and response formatting

## Level 2: Advanced API Design
- **Competencies:** API Versioning, Rate Limiting, Authentication, Caching
- **Tools:** API Gateway, JWT, Redis, API Versioning Strategies
- **Checklist:**
  - [ ] Implement API versioning strategy (URL, Header, Content-Type)
  - [ ] Add rate limiting and throttling to API endpoints
  - [ ] Implement JWT-based authentication for API security
  - [ ] Add caching strategies for improved performance

## Level 3: API Architecture & Governance
- **Competencies:** Microservices APIs, API Gateway, Service Mesh, API Governance
- **Tools:** Kong, Istio, API Management Platforms, GraphQL
- **Checklist:**
  - [ ] Design APIs for microservices architecture
  - [ ] Implement API Gateway with routing and load balancing
  - [ ] Set up API governance and monitoring
  - [ ] Design GraphQL APIs for flexible data querying

## Level 4: API Security & Performance
- **Competencies:** OAuth2, API Security, Performance Optimization, Load Testing
- **Tools:** OAuth2, JWT, API Security Tools, Load Testing Tools
- **Checklist:**
  - [ ] Implement OAuth2 and advanced authentication
  - [ ] Add comprehensive API security measures
  - [ ] Optimize API performance and response times
  - [ ] Conduct load testing and performance analysis

## Level 5: API Strategy & Leadership
- **Competencies:** API Strategy, Developer Experience, API Monetization, Team Leadership
- **Tools:** API Management Platforms, Developer Portals, Analytics
- **Checklist:**
  - [ ] Develop comprehensive API strategy and roadmap
  - [ ] Create exceptional developer experience and documentation
  - [ ] Implement API monetization and business models
  - [ ] Lead API development teams and establish best practices

---

## Resources
- [SpringDoc OpenAPI Documentation](https://springdoc.org/)
- [OpenAPI 3.0 Specification](https://swagger.io/specification/)
- [Spring Boot Validation Documentation](https://spring.io/guides/gs/validating-form-input/)
- [REST API Best Practices](https://restfulapi.net/)
- [Jakarta Bean Validation](https://beanvalidation.org/)
- [ChatGPT for code examples and troubleshooting](https://chat.openai.com/)

---

## Progress

??? success "Level 1: REST API Fundamentals"
    **Status:** Completed  
    **Focus:** RESTful Design, HTTP Methods, Status Codes, API Documentation  
    **Tools Used:** OpenAPI/Swagger, Spring Boot, Bean Validation  
    **Evidence:**  
    - [Smart Deploy Monitor API](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **API Testing Results** (Completed 18-10-2024): All 11/11 API design tests passing (100% success rate)
    - **Swagger UI Integration** (Completed 18-10-2024): Interactive API documentation at `/swagger-ui/index.html`
    - **OpenAPI JSON** (Completed 18-10-2024): Complete API specification at `/v3/api-docs`
    
    **What I Learned:**
    - **OpenAPI/Swagger Integration**: Added comprehensive API documentation with SpringDoc OpenAPI
    - **RESTful Design**: Implemented proper HTTP methods (GET, POST, PUT, DELETE) with correct status codes
    - **Request Validation**: Added Bean Validation annotations (@NotBlank, @Email, @Size) for input validation
    - **API Documentation**: Created detailed OpenAPI annotations with examples, descriptions, and response schemas
    - **Error Handling**: Implemented proper HTTP status codes (200, 201, 400, 404, 500) for different scenarios
    - **Response Formatting**: Ensured consistent JSON response structure across all endpoints
    - **API Testing**: Created comprehensive testing suite for API design principles
    
    **Resources Used:**
    - SpringDoc OpenAPI documentation
    - Spring Boot validation documentation
    - OpenAPI 3.0 specification
    - REST API best practices guides
    - ChatGPT for code examples and troubleshooting
    
    **Applied Knowledge:**
    - Enhanced existing UserController with comprehensive OpenAPI documentation
    - Added request validation using Jakarta Bean Validation
    - Implemented proper HTTP status codes and error handling
    - Created detailed API documentation with examples and schemas
    - Built comprehensive testing suite to validate API design principles
    - Integrated Swagger UI for interactive API documentation
    - Ensured RESTful API design following industry standards
    
    **All Level 1 tasks completed!**

??? todo "Level 2: Advanced API Design"
    **Status:** Planned  
    **Focus:** API Versioning, Rate Limiting, Authentication, Caching

??? todo "Level 3: API Architecture & Governance"
    **Status:** Planned  
    **Focus:** Microservices APIs, API Gateway, Service Mesh, API Governance

??? todo "Level 4: API Security & Performance"
    **Status:** Planned  
    **Focus:** OAuth2, API Security, Performance Optimization, Load Testing

??? todo "Level 5: API Strategy & Leadership"
    **Status:** Planned  
    **Focus:** API Strategy, Developer Experience, API Monetization, Team Leadership

---

## Evidence

??? tip "Level 1 Evidence"
    **Repository:** [Smart Deploy Monitor API](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)  
    **Demonstrations:**
    - **OpenAPI Configuration**: Complete API documentation setup with SpringDoc
    - **UserController Enhancement**: Comprehensive API annotations and validation
    - **API Testing Suite**: Automated testing for all API design principles
    - **Swagger UI Integration**: Interactive API documentation interface
    
    **API Endpoints:**
    - `GET /api/users` - Retrieve all users with proper documentation
    - `POST /api/users` - Create user with validation and error handling
    - `GET /api/users/{id}` - Get user by ID with proper status codes
    - `PUT /api/users/{id}` - Update user with validation
    - `DELETE /api/users/{id}` - Delete user with proper error handling
    
    **Testing Results:**
    - ✅ OpenAPI JSON documentation available
    - ✅ Swagger UI accessible and functional
    - ✅ API validation working (400 status for invalid requests)
    - ✅ Consistent response format across all endpoints
    - ✅ Proper HTTP status codes (200, 201, 400, 404, 500)
    - ✅ RESTful API design following industry standards
    - ✅ Error handling and status codes working correctly

---

## Personal Notes

??? info "Note #1: API Design Implementation (18-10-2024)"
    To achieve **Level 1: REST API Fundamentals**, I implemented:

    **1. OpenAPI/Swagger Integration**
    ```java
    @Configuration
    public class OpenApiConfig {
        @Bean
        public OpenAPI smartDeployMonitorOpenAPI() {
            return new OpenAPI()
                .info(new Info()
                    .title("Smart Deploy Monitor API")
                    .description("Comprehensive Spring Boot application...")
                    .version("1.0.0"));
        }
    }
    ```

    **2. Request Validation**
    ```java
    @Schema(description = "Request to create a new user")
    public static class CreateUserRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        private String email;
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;
    }
    ```

    **3. API Documentation**
    ```java
    @Operation(
        summary = "Create a new user",
        description = "Creates a new user account with the provided information."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request - Invalid input")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody CreateUserRequest request) {
        // Implementation
    }
    ```

    **Key Learning:** The combination of OpenAPI documentation, Bean Validation, and comprehensive testing creates a professional, maintainable API that follows industry standards.