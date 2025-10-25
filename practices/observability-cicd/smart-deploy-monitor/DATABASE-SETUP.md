# Database Setup Guide - Smart Deploy Monitor

## 🗄️ **Database Integration Complete!**

This guide covers the database setup and functionality added to the Smart Deploy Monitor application.

## 📋 **What We've Implemented**

###   **Database Dependencies**
- **Spring Data JPA** - For database operations
- **PostgreSQL Driver** - Database connectivity
- **Flyway** - Database migrations (optional)
- **Spring Security Crypto** - Password encryption

###   **Database Configuration**
- **PostgreSQL** database connection
- **JPA/Hibernate** configuration
- **Connection pooling** and performance settings
- **SQL logging** for development

###   **Entity Layer**
- **User Entity** with JPA annotations
- **UserRole Enum** for role management
- **UUID primary keys** for better security
- **Audit fields** (created_at, updated_at)

###   **Repository Layer**
- **UserRepository** with Spring Data JPA
- **Custom query methods** for common operations
- **Advanced queries** with @Query annotations
- **Pagination and sorting** support

###   **Service Layer**
- **UserService** with business logic
- **Password encryption** with BCrypt
- **User management** operations
- **Statistics and reporting**

###   **Controller Layer**
- **RESTful API** endpoints
- **User CRUD operations**
- **Password management**
- **User statistics**

## 🚀 **Getting Started**

### **Prerequisites**
- Java 17+
- Maven 3.6+
- PostgreSQL database (local or Docker)

### **Database Setup Options**

#### **Option 1: Docker (Recommended)**
```bash
# Start PostgreSQL with Docker Compose
docker compose up -d postgres

# Check if database is running
docker compose ps
```

#### **Option 2: Local PostgreSQL**
1. Install PostgreSQL locally
2. Create database: `smart_deploy_monitor`
3. Update credentials in `application.yml` if needed

### **Application Configuration**

The database is configured in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/smart_deploy_monitor
    username: postgres
    password: Root@123
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: create-drop  # Creates tables automatically
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
```

## 🧪 **Testing the Database**

### **1. Start the Application**
```bash
mvn spring-boot:run
```

### **2. Run Database Tests**
```bash
# Run all database tests
./test-database.sh --test

# Check application status
./test-database.sh --check
```

### **3. Manual Testing with curl**

#### **Create a User**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

#### **Get All Users**
```bash
curl http://localhost:8080/api/users
```

#### **Get User Statistics**
```bash
curl http://localhost:8080/api/users/statistics
```

#### **Verify Password**
```bash
curl -X POST http://localhost:8080/api/users/verify-password \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

## 📊 **Database Schema**

### **Users Table**
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role VARCHAR(50) DEFAULT 'USER',
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### **User Roles**
- **USER** - Regular user
- **ADMIN** - Administrator
- **MODERATOR** - Moderator

## 🔧 **API Endpoints**

### **User Management**
- `POST /api/users` - Create user
- `POST /api/users/admin` - Create admin user
- `GET /api/users` - Get all users
- `GET /api/users/active` - Get active users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/email/{email}` - Get user by email
- `GET /api/users/role/{role}` - Get users by role
- `PUT /api/users/{id}` - Update user
- `PUT /api/users/{id}/password` - Change password
- `PUT /api/users/{id}/activate` - Activate user
- `PUT /api/users/{id}/deactivate` - Deactivate user
- `DELETE /api/users/{id}` - Delete user

### **Statistics & Verification**
- `GET /api/users/statistics` - Get user statistics
- `POST /api/users/verify-password` - Verify password

## 🛡️ **Security Features**

### **Password Security**
- **BCrypt hashing** for password storage
- **Salt rounds** for enhanced security
- **Password verification** without storing plain text

### **Data Protection**
- **UUID primary keys** instead of sequential IDs
- **No password exposure** in API responses
- **Input validation** and error handling

## 📈 **Performance Features**

### **Database Optimization**
- **Indexes** on frequently queried columns
- **Connection pooling** for better performance
- **Query optimization** with proper JPA annotations

### **Monitoring**
- **SQL logging** for development
- **Query performance** monitoring
- **Database health checks** via Actuator

## 🔍 **Troubleshooting**

### **Common Issues**

#### **Database Connection Failed**
```bash
# Check if PostgreSQL is running
docker compose ps

# Check application logs
tail -f logs/smart-deploy-monitor.log
```

#### **Table Creation Issues**
- Ensure `ddl-auto: create-drop` is set
- Check database permissions
- Verify PostgreSQL version compatibility

#### **Password Encoding Issues**
- Ensure Spring Security Crypto dependency is included
- Check BCryptPasswordEncoder bean configuration

### **Useful Commands**

```bash
# Check database connection
curl http://localhost:8080/actuator/health

# View application logs
tail -f logs/smart-deploy-monitor.log

# Test specific endpoint
curl -v http://localhost:8080/api/users/statistics
```

## 🎯 **Learning Outcomes**

### **What You've Learned**
- **Spring Data JPA** - Object-relational mapping
- **PostgreSQL** - Database setup and configuration
- **Entity Design** - JPA annotations and relationships
- **Repository Pattern** - Data access layer
- **Service Layer** - Business logic implementation
- **REST API Design** - Database-backed endpoints
- **Password Security** - BCrypt encryption
- **Database Testing** - Automated testing strategies

### **Skills Applied**
- **Database Design** - Tables, indexes, relationships
- **Spring Boot Integration** - Database connectivity
- **API Development** - CRUD operations
- **Security Implementation** - Password handling
- **Testing Strategies** - Automated database tests

## 🚀 **Next Steps**

### **Potential Enhancements**
- **User Authentication** - JWT tokens
- **Role-based Access Control** - Spring Security
- **Database Migrations** - Flyway integration
- **Caching** - Redis integration
- **Audit Logging** - Track user actions
- **Data Validation** - Bean validation
- **Pagination** - Large dataset handling

### **Advanced Features**
- **Multi-tenancy** - Multiple organizations
- **Soft Deletes** - Data retention
- **Full-text Search** - User search capabilities
- **Data Export** - CSV/JSON export
- **Backup & Recovery** - Data protection

---

**🎉 Congratulations!** You've successfully integrated a complete database layer into your Spring Boot application with proper security, testing, and documentation!