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
    - **Multi-Database Architecture** (Completed 18-10-2025): PostgreSQL, Redis, Elasticsearch, Qdrant integration
    - **RESTful API Design** (Completed 18-10-2025): OpenAPI/Swagger documentation with proper HTTP methods
    - **Caching Strategy** (Completed 18-10-2025): Redis TTL-based caching for performance optimization
    - **Microservices Patterns** (Completed 18-10-2025): Service layer architecture with dependency injection
    
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
