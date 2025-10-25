# API Documentation Template

## Overview
Brief description of the API, its purpose, and main functionality.

## Base URL
```
https://api.example.com/v1
```

## Authentication
Describe how to authenticate with the API.

## Endpoints

### GET /users
Retrieve a list of users with optional filtering and pagination.

**Parameters:**
- `page` (optional, integer): Page number (default: 0)
- `size` (optional, integer): Number of items per page (default: 10)
- `role` (optional, string): Filter by user role
- `status` (optional, string): Filter by user status

**Response:**
```json
{
  "content": [
    {
      "id": "uuid",
      "email": "ahmed.hassan@example.com",
      "firstName": "أحمد",
      "lastName": "حسن",
      "role": "USER",
      "isActive": true,
      "createdAt": "2025-10-26T10:00:00Z"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 100,
    "totalPages": 10
  }
}
```

**Status Codes:**
- `200 OK`: Successfully retrieved users
- `400 Bad Request`: Invalid parameters
- `401 Unauthorized`: Authentication required
- `500 Internal Server Error`: Server error

### POST /users
Create a new user account.

**Request Body:**
```json
{
  "email": "fatima.ahmed@example.com",
  "password": "securePassword123",
  "firstName": "فاطمة",
  "lastName": "أحمد",
  "role": "USER"
}
```

**Response:**
```json
{
  "id": "uuid",
  "email": "fatima.ahmed@example.com",
  "firstName": "فاطمة",
  "lastName": "أحمد",
  "role": "USER",
  "isActive": true,
  "createdAt": "2025-10-26T10:00:00Z"
}
```

**Status Codes:**
- `201 Created`: User created successfully
- `400 Bad Request`: Invalid input data
- `409 Conflict`: Email already exists
- `500 Internal Server Error`: Server error

### GET /users/{id}
Retrieve a specific user by ID.

**Parameters:**
- `id` (required, string): User UUID

**Response:**
```json
    {
      "id": "uuid",
      "email": "omar.khalid@example.com",
      "firstName": "عمر",
      "lastName": "خالد",
      "role": "USER",
      "isActive": true,
      "createdAt": "2025-10-26T10:00:00Z",
      "updatedAt": "2025-10-26T15:30:00Z"
    }
```

**Status Codes:**
- `200 OK`: User found
- `404 Not Found`: User not found
- `401 Unauthorized`: Authentication required

### PUT /users/{id}
Update an existing user.

**Parameters:**
- `id` (required, string): User UUID

**Request Body:**
```json
{
  "firstName": "سارة",
  "lastName": "محمد",
  "phoneNumber": "+966501234567"
}
```

**Response:**
```json
{
  "id": "uuid",
  "email": "sara.mohammed@example.com",
  "firstName": "سارة",
  "lastName": "محمد",
  "phoneNumber": "+966501234567",
  "role": "USER",
  "isActive": true,
  "createdAt": "2025-10-26T10:00:00Z",
  "updatedAt": "2025-10-26T16:00:00Z"
}
```

**Status Codes:**
- `200 OK`: User updated successfully
- `400 Bad Request`: Invalid input data
- `404 Not Found`: User not found
- `401 Unauthorized`: Authentication required

### DELETE /users/{id}
Deactivate a user account.

**Parameters:**
- `id` (required, string): User UUID

**Response:**
```json
{
  "message": "User deactivated successfully",
  "deactivatedAt": "2025-10-26T16:00:00Z"
}
```

**Status Codes:**
- `200 OK`: User deactivated successfully
- `404 Not Found`: User not found
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Insufficient permissions

## Error Handling

### Error Response Format
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid input data",
    "details": [
      {
        "field": "email",
        "message": "Email format is invalid"
      }
    ],
    "timestamp": "2025-10-26T16:00:00Z",
    "requestId": "req-123456"
  }
}
```

### Common Error Codes
- `VALIDATION_ERROR`: Input validation failed
- `AUTHENTICATION_REQUIRED`: Authentication required
- `INSUFFICIENT_PERMISSIONS`: User lacks required permissions
- `RESOURCE_NOT_FOUND`: Requested resource not found
- `DUPLICATE_RESOURCE`: Resource already exists
- `INTERNAL_SERVER_ERROR`: Unexpected server error

## Rate Limiting
- 1000 requests per hour per API key
- 100 requests per minute per IP address
- Rate limit headers included in responses:
  - `X-RateLimit-Limit`: Request limit
  - `X-RateLimit-Remaining`: Remaining requests
  - `X-RateLimit-Reset`: Reset timestamp

## SDKs and Libraries
- Java: `com.example.usersdk:user-api-client:1.0.0`
- JavaScript: `npm install @example/user-api-client`
- Python: `pip install example-user-api`

## Changelog
- **v1.2.0** (2025-10-26): Added phone number field to user updates
- **v1.1.0** (2025-10-20): Added role-based filtering
- **v1.0.0** (2025-10-15): Initial API release
