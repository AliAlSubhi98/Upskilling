# API Collections for Smart Deploy Monitor

This directory contains comprehensive Postman collections for testing the Smart Deploy Monitor API, including both REST and GraphQL endpoints.

## Files Overview

### Collections
- **`GraphQL-API-Collection.postman_collection.json`** - Complete GraphQL API testing
- **`REST-API-Collection.postman_collection.json`** - Complete REST API testing
- **`Smart-Deploy-Monitor-Environment.postman_environment.json`** - Environment variables

## Quick Start

### 1. Import Collections
1. Open Postman
2. Click **Import** button
3. Select all three JSON files from this directory
4. Import the environment file as well

### 2. Set Environment
1. In Postman, select **"Smart Deploy Monitor Environment"** from the environment dropdown
2. Verify the `baseUrl` is set to `http://localhost:8080`

### 3. Start the Application
```bash
cd /Users/alialsubhi/Desktop/Upskilling/practices/observability-cicd/smart-deploy-monitor
mvn spring-boot:run
```

## Collection Structure

### GraphQL API Collection
- **GraphQL Queries**
  - Get All Users (Full Data)
  - Get All Users (Mobile - Limited Data)
  - Get User by ID
  - Get Users with Variables
- **GraphQL Mutations**
  - Create User
  - Update User
  - Delete User
- **GraphQL Schema & Introspection**
  - Schema Introspection
  - Get All Types
  - Get Query Type
- **Real-World Scenarios**
  - Mobile App - User List
  - Admin Dashboard - Full User Data
  - User Profile Page
  - Performance Test - Simple Query
- **REST API (For Comparison)**
  - Get All Users (REST)
  - Create User (REST)
- **Health & Monitoring**
  - Health Check
  - GraphQL Playground
  - Swagger UI

### REST API Collection
- **User Management**
  - Get All Users
  - Get Users with Pagination
  - Get Users with Search
  - Create User
  - Get User by ID
  - Update User
  - Delete User
- **API Versioning Tests**
  - V1 API - Get Users
  - V1 API - Create User
- **Filtering & Sorting**
  - Sort by First Name ASC
  - Sort by Created Date DESC
  - Search Users by Email
- **Error Handling**
  - Get Non-existent User
  - Create User with Invalid Data
- **Performance Tests**
  - Large Page Size
  - Multiple Requests (Load Test)

## Real-World Testing Scenarios

### 1. Mobile App Testing
```bash
# Test mobile-optimized queries
POST {{graphqlEndpoint}}
{
  "query": "query { users { edges { node { id firstName lastName } } totalCount } }"
}
```

### 2. Admin Dashboard Testing
```bash
# Test admin dashboard queries
POST {{graphqlEndpoint}}
{
  "query": "query { users { edges { node { id email firstName lastName role isActive createdAt } } pageInfo { hasNextPage } totalCount } }"
}
```

### 3. Performance Testing
```bash
# Test simple queries for performance
POST {{graphqlEndpoint}}
{
  "query": "query { users { totalCount } }"
}
```

## 🔧 Environment Variables

| Variable | Value | Description |
|----------|-------|-------------|
| `baseUrl` | `http://localhost:8080` | Base URL for the application |
| `graphqlEndpoint` | `{{baseUrl}}/graphql` | GraphQL endpoint |
| `graphiqlEndpoint` | `{{baseUrl}}/graphiql` | GraphQL Playground |
| `restApiEndpoint` | `{{baseUrl}}/api/v1` | REST API endpoint |
| `swaggerUiEndpoint` | `{{baseUrl}}/swagger-ui/index.html` | Swagger UI |
| `healthEndpoint` | `{{baseUrl}}/actuator/health` | Health check endpoint |
| `testUserId` | `daff9e3a-1ca4-4734-afc4-b938f6b72daf` | Test user ID |
| `testUserEmail` | `john@blog.com` | Test user email |

## 📈 Testing Workflow

### 1. Health Check
Start by testing the health endpoint to ensure the application is running:
```
GET {{healthEndpoint}}
```

### 2. GraphQL Playground
Open the GraphQL Playground in your browser:
```
GET {{graphiqlEndpoint}}
```

### 3. Create Test Data
Use the REST API to create test users:
```
POST {{restApiEndpoint}}/users
{
  "email": "test@example.com",
  "password": "password123",
  "firstName": "Test",
  "lastName": "User",
  "role": "USER"
}
```

### 4. Test GraphQL Queries
Run the GraphQL queries to test different scenarios:
- Mobile app queries (limited data)
- Admin dashboard queries (full data)
- Performance tests

### 5. Compare REST vs GraphQL
Use the comparison requests to see the differences between REST and GraphQL approaches.

## Key Testing Points

### GraphQL Benefits
- **Flexible Data Fetching**: Request only the fields you need
- **Single Endpoint**: All queries go through `/graphql`
- **Type Safety**: Schema introspection and validation
- **Real-time**: WebSocket subscriptions (when implemented)

### REST Benefits
- **Caching**: HTTP caching works out of the box
- **Simplicity**: Easy to understand and implement
- **Standards**: Follows HTTP standards
- **Tools**: Wide ecosystem support

## Advanced Testing

### 1. Load Testing
Use the performance test requests to simulate multiple concurrent requests.

### 2. Error Handling
Test various error scenarios:
- Invalid user IDs
- Malformed requests
- Validation errors

### 3. Schema Evolution
Use the introspection queries to understand the GraphQL schema structure.

## Notes

- All collections include pre-configured test data
- Environment variables make it easy to switch between different environments
- Collections are organized by functionality for easy navigation
- Each request includes proper headers and body formatting

## 🔗 Related Documentation

- [GraphQL Documentation](http://localhost:8080/graphiql)
- [REST API Documentation](http://localhost:8080/swagger-ui/index.html)
- [Health Check](http://localhost:8080/actuator/health)

---

**Happy Testing!**
