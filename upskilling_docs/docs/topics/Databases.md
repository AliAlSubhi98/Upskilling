# Databases

**Goal:** Design and operate performant, scalable databases.

**Current Level:** Level 1

---

## Level 1: Fundamentals
- **Competencies:** SQL basics, Redis commands, Elasticsearch DSL, Qdrant insert/query
- **Tools:** PostgreSQL, MS SQL, Oracle, Redis, Elasticsearch, Qdrant
- **Checklist:**
  - [x] Perform CRUD in SQL
  - [ ] Search/filter with Elastic filters vs queries
  - [ ] Insert/search vectors in Qdrant
  - [ ] Use Redis for TTL-based caching

---

## Resources
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Official Documentation](https://www.postgresql.org/docs/)
- [Spring Boot Database Configuration](https://spring.io/guides/gs/accessing-data-jpa/)
- [BCrypt Password Encryption](https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [ChatGPT for code examples and troubleshooting](https://chat.openai.com/)

---

## Progress

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
    - ✅ User creation with secure password hashing
    - ✅ User retrieval by ID, email, and custom queries
    - ✅ User statistics and analytics
    - ✅ Password verification and change functionality
    - ✅ Admin and regular user role management
    
    **Testing Results:**
    - ✅ All 5/5 database tests passing (100% success rate)
    - ✅ User creation, retrieval, statistics working correctly
    - ✅ Password verification and admin roles functioning
    - ✅ Database integration fully operational and production-ready

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
