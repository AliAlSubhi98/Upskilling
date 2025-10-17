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
    - **REST API Development**: Created comprehensive UserController with CRUD operations
    - **Password Security**: Implemented BCrypt password encryption for secure user authentication
    - **Database Testing**: Created automated testing scripts for database operations
    - **Redis TTL-based Caching**: Implemented Redis caching with Time To Live for performance optimization
    - **Elasticsearch Search/Filter**: Created search services with filters (exact matches) and queries (relevance scoring)
    - **Qdrant Vector Operations**: Implemented vector insert/search operations for similarity search
    - **Multi-Database Integration**: Successfully integrated PostgreSQL, Redis, Elasticsearch, and Qdrant
    
    **Resources Used:**
    - Spring Data JPA documentation
    - PostgreSQL official documentation
    - Spring Boot database configuration guides
    - BCrypt password encryption tutorials
    - ChatGPT for code examples and troubleshooting
    
    **Applied Knowledge:**
    - Integrated PostgreSQL database into existing Spring Boot application
    - Created complete user management system with secure password handling
    - Implemented comprehensive REST API for user operations
    - Added Docker Compose setup for easy database deployment
    - Created automated testing scripts for database functionality
    - Documented complete database setup and usage guide
    - **Successfully tested all database operations** with 100% test pass rate
    - **Verified user creation, retrieval, statistics, and password verification**
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
    -  Elasticsearch search/filter operations
    -  Qdrant vector insert/search operations
    
    **Testing Results:**
    -  All 5/5 database tests passing (100% success rate)
    -  User creation, retrieval, statistics working correctly
    -  Password verification and admin roles functioning
    -  Database integration fully operational and production-ready
    -  Redis caching operations tested and verified
    -  Elasticsearch search/filter functionality confirmed
    -  Qdrant vector operations successfully implemented
    -  Multi-database integration fully operational

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
