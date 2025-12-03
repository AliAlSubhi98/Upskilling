# Databases

**Goal:** Design and operate performant, scalable databases.

**Current Level:** Level 1

---

## Level 1: Fundamentals
- **Competencies:** SQL basics, Redis commands, Elasticsearch DSL, Qdrant insert/query
- **Tools:** PostgreSQL, MS SQL, Oracle, Redis, Elasticsearch, Qdrant
- **Checklist:**
  - [x] Perform CRUD in SQL
  - [x] Search/filter with Elastic filters vs queries
  - [x] Insert/search vectors in Qdrant
  - [x] Use Redis for TTL-based caching

## Level 2: Schema Design
- **Competencies:** Normalization, indexing, analyzers, Redis eviction, Qdrant collection config
- **Tools:** B-tree/GiST/GIN, EXPLAIN ANALYZE, Elastic mappings, Redis data types, Qdrant token filters
- **Checklist:**
  - [ ] Normalize SQL tables and create optimal indexes
  - [ ] Create analyzers in Elastic for multi-language support
  - [ ] Tune Redis eviction policy
  - [ ] Define Qdrant collection schema with metadata fields

## Level 3: Advanced Usage
- **Competencies:** SQL functions, CTEs, triggers, Elastic aggregations/scripts, Redis rate limits, Qdrant optimization
- **Tools:** PL/pgSQL, T-SQL, Elastic Painless scripts, Redis Streams/Lua, Qdrant payload indexing
- **Checklist:**
  - [ ] Use SQL functions and triggers to automate logic
  - [ ] Aggregate and bucket results in Elastic
  - [ ] Implement Redis-based rate limiting
  - [ ] Optimize Qdrant collection with multiple segments and query filters

## Level 4: Operational Architecture
- **Competencies:** Replica design, shard tuning, cache patterns, Qdrant payloads
- **Tools:** PgBouncer, AlwaysOn Ags, Redis caching layers, Qdrant indexed payloads
- **Checklist:**
  - [ ] Route SQL reads to replicas
  - [ ] Tune Elastic shard counts and replicas
  - [ ] Implement Redis caching for different workloads
  - [ ] Filter and score with Qdrant payloads

## Level 5: Distributed DBs
- **Competencies:** Geo-distribution, failover, hybrid search, alerting
- **Tools:** CockroachDB, Cassandra, Redis Sentinel, Prometheus + Grafana, Elastic + Qdrant
- **Checklist:**
  - [ ] Deploy CockroachDB for geo-distributed SQL
  - [ ] Handle key eviction and latency in Redis cluster
  - [ ] Implement hybrid search (vector + keyword)
  - [ ] Set alerts on DB metrics and failover

---
## Resources
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Official Documentation](https://www.postgresql.org/docs/)
- [Spring Boot Database Configuration](https://spring.io/guides/gs/accessing-data-jpa/)
- [BCrypt Password Encryption](https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [ChatGPT for code examples and troubleshooting](https://chat.openai.com/)


---
??? success "Level 1: Fundamentals"
    **Status:** Completed  
    **Focus:** SQL basics, PostgreSQL, Spring Data JPA, Database Integration  
    **Tools Used:** PostgreSQL, Spring Data JPA, Docker Compose, BCrypt  
    **Evidence:**  
    - [Smart Deploy Monitor Database](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **Database Testing Results** (Completed 18-10-2025): All 5/5 database tests passing (100% success rate)
    - **User Management System** (Completed 18-10-2025): Complete CRUD operations with secure password handling
    - **Database Integration** (Completed 18-10-2025): PostgreSQL fully operational with Spring Boot
    
    **What I Learned:**
    - **Spring Data JPA**: Object-relational mapping with JPA annotations and repositories
    - **PostgreSQL Integration**: Database setup, connection configuration, and schema management
    - **Entity Design**: Created User entity with UUID primary keys, audit fields, and relationships
    - **Repository Pattern**: Implemented UserRepository with custom query methods and Spring Data JPA
    - **Service Layer**: Built UserService with business logic, password encryption, and user management
    - **REST API Development**: Built UserController with CRUD operations. Clean separation of concerns.
    - **Password Security**: Used BCrypt for password encryption. Never store plain text passwords!
    - **Database Testing**: Created automated testing scripts. Makes sure everything works.
    - **Redis TTL-based Caching**: Redis caching with Time To Live. Speeds things up and automatically expires old data.
    - **Elasticsearch Search/Filter**: Search services with filters (exact matches) and queries (relevance scoring). Full-text search is powerful.
    - **Qdrant Vector Operations**: Vector insert/search for similarity search. Useful for AI/ML stuff.
    - **Multi-Database Integration**: Got PostgreSQL, Redis, Elasticsearch, and Qdrant all working together. Each has its purpose.
    
    **What I Did:**
    - Integrated PostgreSQL into the Spring Boot app
    - Built a complete user management system with secure password handling
    - Created a REST API for user operations
    - Added Docker Compose setup for easy database deployment
    - Created automated testing scripts for database functionality
    - Documented the complete database setup and usage
    - Tested all database operations - 100% pass rate!
    - Verified user creation, retrieval, statistics, and password verification
    
    **Resources:** Spring Data JPA docs, PostgreSQL docs, Spring Boot config guides, BCrypt tutorials, ChatGPT when I got stuck
    - **Confirmed admin and regular user roles working correctly**
    - **Database integration fully operational and production-ready**
    - **Implemented Redis TTL-based caching** for performance optimization
    - **Created Elasticsearch search/filter services** with filters and queries
    - **Built Qdrant vector operations** for similarity search and metadata filtering
    - **Integrated multiple database technologies** in a single Spring Boot application
    - **Created comprehensive Level 1 database testing suite** covering all requirements
    
    **All Level 1 tasks completed!**

??? todo "Level 2: Schema Design"
    **Status:** Planned  
    **Focus:** Normalization, indexing, analyzers, Redis eviction, Qdrant collection config

??? todo "Level 3: Performance & Optimization"
    **Status:** Planned  
    **Focus:** Query optimization, indexing strategies, connection pooling, caching

??? todo "Level 4: Advanced Database Operations"
    **Status:** Planned  
    **Focus:** Replication, sharding, backup strategies, monitoring

??? todo "Level 5: Database Architecture & Leadership"
    **Status:** Planned  
    **Focus:** Database strategy, team leadership, enterprise database management

---

## Evidence

??? tip "Level 1 Evidence"
    **Repository:** [Smart Deploy Monitor Database](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)  
    **Demonstrations:**
    - **PostgreSQL Integration**: Complete database setup with Docker Compose
    - **User Entity**: JPA entity with UUID, audit fields, and relationships
    - **Repository Pattern**: Custom query methods and Spring Data JPA integration
    - **Service Layer**: Business logic with password encryption and user management
    - **REST API**: Complete CRUD operations with proper error handling
    
    **Database Operations:**
    -  User creation with secure password hashing
    -  User retrieval by ID, email, and custom queries
    -  User statistics and analytics
    -  Password verification and change functionality
    -  Admin and regular user role management
    -  Redis TTL-based caching operations
    -  Elasticsearch search and filter operations
    -  Qdrant vector insert and search operations
    
    **Level 1 Comprehensive Testing Results:**
    -  PostgreSQL CRUD Operations: Working
    -  Redis TTL-based Caching: Working  
    -  Elasticsearch Search/Filter: Working
    -  Qdrant Vector Operations: Working
    - **All Level 1 database fundamentals successfully demonstrated and tested**
    - **All Level 1 database operations are working correctly:**
    - • SQL CRUD operations with PostgreSQL
    - • TTL-based caching with Redis
    - • Search/filter operations with Elasticsearch
    - • Vector insert/search operations with Qdrant
    
    **Testing Results:**
    -  All 4/4 database services passing (100% success rate)
    -  PostgreSQL: 0 users (ready for operations)
    -  Redis: 2 keys (caching working)
    -  Elasticsearch: 1 document (search working)
    -  Qdrant: 0 points (vector operations ready)

---

## Personal Notes

??? info "Note #1: Database Integration Implementation (18-10-2025)"
    To achieve **Level 1: Fundamentals**, I implemented:

    **1. PostgreSQL Database Setup**
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/smart_deploy_monitor
        username: postgres
        password: Root@123
        driver-class-name: org.postgresql.Driver
    ```

    **2. User Entity with JPA Annotations**
    ```java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        
        @Column(name = "email", nullable = false, unique = true)
        private String email;
        
        @Enumerated(EnumType.STRING)
        private UserRole role = UserRole.USER;
    }
    ```

    **3. Repository with Custom Queries**
    ```java
    @Repository
    public interface UserRepository extends JpaRepository<User, UUID> {
        Optional<User> findByEmail(String email);
        List<User> findByIsActiveTrue();
        @Query("SELECT u FROM User u WHERE u.createdAt >= :startDate")
        List<User> findUsersCreatedInLastDays(@Param("startDate") LocalDateTime startDate);
    }
    ```

    **Key Learning:** The combination of Spring Data JPA, PostgreSQL, and proper entity design creates a robust, scalable database layer that integrates seamlessly with Spring Boot applications.

??? info "Note #2: Level 1 – Multi-Database Integration (18-10-2025)"
    To complete **Level 1: Fundamentals**, I implemented:

    **1. Redis TTL-based Caching**
    ```java
    @Service
    public class CacheService {
        public void setWithTTL(String key, Object value, long ttlSeconds) {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
        }
    }
    ```

    **2. Elasticsearch Search/Filter Operations**
    ```java
    public List<Map<String, Object>> searchUsersWithFilters(String email, Boolean isActive) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (email != null) {
            boolQuery.filter(QueryBuilders.termQuery("email.keyword", email));
        }
        // Filters are cached and faster for exact matches
    }
    ```

    **3. Qdrant Vector Operations**
    ```java
    public boolean insertVector(String pointId, List<Float> vector, Map<String, Object> payload) {
        PointStruct point = PointStruct.newBuilder()
            .setId(PointId.newBuilder().setUuid(pointId).build())
            .setVectors(Vectors.newBuilder().setVector(Vector.newBuilder().addAllData(vector).build()).build())
            .putAllPayload(qdrantPayload)
            .build();
    }
    ```

    **Key Learning:** Multi-database integration requires understanding each technology's strengths: PostgreSQL for ACID transactions, Redis for fast caching, Elasticsearch for full-text search, and Qdrant for vector similarity search.

??? success "Level 1 Complete Integration Results (18-10-2025)"
    **All Level 1 Database Services Successfully Integrated and Tested:**

    ### **PostgreSQL CRUD Operations**
    - **Database Connection**: UP
    - **User Entity**: JPA annotations with UUID primary keys
    - **Repository**: Custom queries with Spring Data JPA
    - **REST API**: 15+ endpoints for user management
    - **Statistics**: 0 users (ready for operations)

    ### **Redis TTL-based Caching**
    - **Redis Connection**: UP (version 7.4.6)
    - **TTL Operations**: Time-based key expiration
    - **Cache Management**: Statistics and monitoring
    - **Statistics**: 2 keys in cache

    ### **Elasticsearch Search/Filter Operations**
    - **Elasticsearch Connection**: UP (yellow status - normal for single-node)
    - **Index Management**: Creation and document indexing
    - **Search Operations**: Filter and query capabilities
    - **Statistics**: 1 document indexed

    ### **Qdrant Vector Operations**
    - **Qdrant Service**: UP and operational
    - **Collection Management**: Creation and configuration
    - **Vector Operations**: Insert and similarity search
    - **Statistics**: 0 points (ready for operations)

    ### **Comprehensive Test Results**
    ```bash
    === Level 1 Database Testing Results ===
    PostgreSQL CRUD Operations: Working
    Redis TTL-based Caching: Working
    Elasticsearch Search/Filter: Working
    Qdrant Vector Operations: Working
    
    Level 1 Database Fundamentals: COMPLETE!
    ```

    ### **Key Achievements**
    - **SQL CRUD Operations**: PostgreSQL with Spring Data JPA
    - **TTL-based Caching**: Redis for performance optimization
    - **Search/Filter Operations**: Elasticsearch for full-text search
    - **Vector Operations**: Qdrant for similarity search and AI/ML

    **Key Achievement:** Successfully integrated and tested all four database technologies in a single Spring Boot application, demonstrating comprehensive Level 1 database fundamentals.

---
## Evidence

??? tip "Level 1 Database Integration Evidence"
    **Status:** Completed  
    **Focus:** Multi-Database Integration, PostgreSQL, Redis, Elasticsearch, Qdrant  
    **Tools Used:** PostgreSQL, Redis, Elasticsearch, Qdrant, Spring Boot, Docker  
    
    ### **Project Evidence**
    - **[Smart Deploy Monitor Multi-Database Integration](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)**
    - **Comprehensive Testing Results** (Completed 18-10-2025): All 4/4 database services working (100% success rate)
    - **Multi-Database Architecture** (Completed 18-10-2025): PostgreSQL, Redis, Elasticsearch, Qdrant fully integrated
    - **Docker Services** (Completed 18-10-2025): All database services running in Docker containers
    
    ### **Code Implementation Evidence**
    
    **PostgreSQL Integration:**
    - **Entity Layer**: Created `User.java` with JPA annotations and UUID primary keys
    - **Repository Layer**: Implemented `UserRepository.java` with custom queries using `@Query` annotations
    - **Service Layer**: Built `UserService.java` with business logic and password encryption
    - **Controller Layer**: Developed `UserController.java` with 15+ REST endpoints for user management
    
    **Redis Caching Implementation:**
    - **Service Layer**: Created `RedisService.java` with TTL-based caching methods
    - **Configuration**: Implemented `RedisConfig.java` for `RedisTemplate` bean configuration
    - **Management**: Added cache statistics and management endpoints
    - **Testing**: Successfully tested TTL expiration functionality
    
    **Elasticsearch Search Implementation:**
    - **Service Layer**: Created `ElasticsearchService.java` for search operations
    - **Index Management**: Implemented index creation and document indexing
    - **Search Operations**: Added search and filter functionality with proper query building
    - **Integration**: Connected with Spring Boot's `ElasticsearchOperations`
    
    **Qdrant Vector Operations:**
    - **Service Layer**: Created `QdrantService.java` for vector operations
    - **Collection Management**: Implemented collection creation and configuration
    - **Vector Operations**: Added vector insertion and similarity search capabilities
    - **API Integration**: Connected with Qdrant REST API using `RestTemplate`
    
    ### **Code Snippets Evidence**
    
    **PostgreSQL Entity Implementation:**
    ```java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        
        @Column(nullable = false)
        private String firstName;
        
        @Column(nullable = false)
        private String lastName;
        
        @Column(unique = true, nullable = false)
        private String email;
        
        @Enumerated(EnumType.STRING)
        private UserRole role = UserRole.USER;
    }
    ```
    
    **Redis Service Implementation:**
    ```java
    @Service
    public class RedisService {
        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        
        public boolean setWithTTL(String key, Object value, long ttlSeconds) {
            redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
            return true;
        }
        
        public Object get(String key) {
            return redisTemplate.opsForValue().get(key);
        }
    }
    ```
    
    **Elasticsearch Service Implementation:**
    ```java
    @Service
    public class ElasticsearchService {
        @Autowired
        private ElasticsearchOperations elasticsearchOperations;
        
        public boolean createIndex(String indexName) {
            return elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).create();
        }
        
        public boolean indexDocument(String indexName, String id, Map<String, Object> document) {
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(id)
                    .withObject(document)
                    .build();
            String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
            return documentId != null && !documentId.isEmpty();
        }
    }
    ```
    
    **Qdrant Vector Operations:**
    ```java
    @Service
    public class QdrantService {
        private final String QDRANT_URL = "http://localhost:6333";
        
        @Autowired
        private RestTemplate restTemplate;
        
        public boolean createCollection(String collectionName) {
            String url = QDRANT_URL + "/collections/" + collectionName;
            Map<String, Object> request = new HashMap<>();
            Map<String, Object> vectors = new HashMap<>();
            vectors.put("size", 4);
            vectors.put("distance", "Cosine");
            request.put("vectors", vectors);
            
            restTemplate.put(url, request);
            return true;
        }
    }
    ```
    
    ### **Docker Integration Evidence**
    
    **Multi-Service Docker Setup:**
    - **Configuration**: Created `docker-services.yml` with Redis, Elasticsearch, and Qdrant services
    - **Health Checks**: Implemented comprehensive health checks for all services
    - **Service Management**: Created `start-services.sh` and `stop-services.sh` scripts
    - **Networking**: Added proper networking and volume management for data persistence
    
    ### **API Integration Evidence**
    
    **REST API Endpoints:**
    - **User Management**: `/api/users` - Complete CRUD operations for user management
    - **Redis Operations**: `/api/database/redis/*` - Caching operations and statistics
    - **Elasticsearch Operations**: `/api/database/search/*` - Search and filter operations
    - **Qdrant Operations**: `/api/database/vector/*` - Vector operations and collection management
    - **Statistics**: `/api/database/stats` - Combined database statistics and health monitoring
    
    **API Documentation:**
    - **OpenAPI/Swagger**: Added SpringDoc OpenAPI documentation with comprehensive endpoint descriptions
    - **Request Validation**: Implemented Jakarta Bean Validation for request validation
    - **API Testing**: Created comprehensive API testing procedures and documentation
    
    ### **Configuration Evidence**
    
    **Application Configuration:**
    - **Multi-Database Setup**: Updated `application.yml` with configurations for all four databases
    - **Connection Settings**: Added proper connection timeouts and pool configurations
    - **Spring Profiles**: Implemented environment-specific configurations for development and production
    
    **Maven Dependencies:**
    - **Redis Integration**: Added `spring-boot-starter-data-redis` for Redis operations
    - **Elasticsearch Integration**: Added `spring-boot-starter-data-elasticsearch` for search operations
    - **Qdrant Integration**: Added `io.qdrant:client` for vector operations
    - **Dependency Management**: Properly configured all dependency versions and compatibility
    
    ### **Documentation Evidence**
    
    **Comprehensive Documentation:**
    - **Setup Guides**: `DATABASE-SERVICES-GUIDE.md` - Complete setup and configuration guide
    - **Testing Guides**: `SERVICE-TESTING-GUIDE.md` - Comprehensive testing procedures
    - **MkDocs Integration**: Updated documentation with detailed learning progress and evidence
    - **Personal Notes**: Added collapsible notes explaining multi-database architecture benefits
    
    ### **Git Integration Evidence**
    
    **Version Control:**
    - **Commit History**: All code changes committed with descriptive commit messages
    - **Repository Structure**: Organized code in proper Maven project structure
    - **Code Organization**: Maintained clean separation between different database integrations
    - **Documentation**: All learning progress and evidence properly documented and versioned

??? note "Personal Notes - Multi-Database Architecture Benefits"
    **Why Multi-Database Architecture Matters:**

    **Real-World Application Benefits:**

    1. **Performance Optimization**
       - **PostgreSQL**: Handles complex relational data with ACID compliance
       - **Redis**: Provides sub-millisecond response times for frequently accessed data
       - **Elasticsearch**: Enables lightning-fast full-text search across millions of records
       - **Qdrant**: Powers AI/ML applications with vector similarity search

    2. **Scalability & Reliability**
       - **Database Specialization**: Each database excels at its specific use case
       - **Load Distribution**: Prevents single points of failure
       - **Independent Scaling**: Scale each database based on its specific workload
       - **Fault Tolerance**: If one database fails, others continue operating

    3. **Modern Application Requirements**
       - **Real-time Caching**: Redis eliminates database hits for hot data
       - **Search Functionality**: Elasticsearch provides Google-like search capabilities
       - **AI Integration**: Qdrant enables semantic search and recommendation systems
       - **Data Consistency**: PostgreSQL ensures transactional integrity

    4. **Business Value**
       - **User Experience**: Faster response times and better search results
       - **Cost Efficiency**: Right tool for the right job reduces infrastructure costs
       - **Future-Proofing**: Architecture supports AI/ML and advanced analytics
       - **Competitive Advantage**: Modern applications require modern database strategies

    5. **Technical Learning Outcomes**
       - **Polyglot Persistence**: Understanding when to use which database
       - **Integration Patterns**: How to connect multiple databases in one application
       - **Performance Tuning**: Optimizing each database for its specific workload
       - **DevOps Skills**: Managing multiple database services with Docker

    **Key Insight:** Modern applications don't use just one database - they use the right database for each specific need. This multi-database approach is the industry standard for scalable, high-performance applications.

    **Next Level Preparation:** This foundation enables learning advanced topics like database sharding, read replicas, data pipelines, and microservices architecture.
