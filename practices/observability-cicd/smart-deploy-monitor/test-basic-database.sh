#!/bin/bash

# Basic Database Level 1 Testing Script
# Tests core functionality without requiring external services

set -e

BASE_URL="http://localhost:8080"
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Basic Database Level 1 Testing ===${NC}"
echo "Testing core database functionality without external dependencies"
echo ""

# Function to test endpoint
test_endpoint() {
    local method=$1
    local endpoint=$2
    local description=$3
    local data=$4
    
    echo -n "Testing $description... "
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" "$BASE_URL$endpoint" 2>/dev/null || echo -e "\n000")
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST -H "Content-Type: application/json" -d "$data" "$BASE_URL$endpoint" 2>/dev/null || echo -e "\n000")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | head -n -1)
    
    if [ "$http_code" = "200" ]; then
        echo -e "${GREEN}PASS${NC}"
        return 0
    else
        echo -e "${RED}FAIL (HTTP $http_code)${NC}"
        return 1
    fi
}

# Check if application is running
echo -e "${YELLOW}=== Application Health Check ===${NC}"
test_endpoint "GET" "/actuator/health" "Application health check"

echo ""

echo -e "${YELLOW}=== Database Controller Tests ===${NC}"

# Test database statistics (should work even without external services)
test_endpoint "GET" "/api/database/stats" "Database statistics"

# Test basic cache operations (Redis simulation)
test_endpoint "POST" "/api/database/cache/set" "Redis: Set cache with TTL" "key=test:key&value=test:value&ttlSeconds=60"
test_endpoint "GET" "/api/database/cache/get" "Redis: Get from cache" "key=test:key"

echo ""

echo -e "${YELLOW}=== User Management Tests ===${NC}"

# Test user management endpoints
test_endpoint "GET" "/api/users" "Get all users"
test_endpoint "GET" "/api/users/stats" "User statistics"

echo ""

echo -e "${YELLOW}=== API Documentation Tests ===${NC}"

# Test API documentation
test_endpoint "GET" "/swagger-ui/index.html" "Swagger UI"
test_endpoint "GET" "/v3/api-docs" "OpenAPI documentation"

echo ""

echo -e "${YELLOW}=== Level 1 Database Testing Summary ===${NC}"
echo "✅ Application health check"
echo "✅ Database controller endpoints"
echo "✅ User management operations"
echo "✅ API documentation access"
echo ""
echo -e "${GREEN}🎉 Basic Level 1 Database operations tested successfully!${NC}"
echo ""
echo -e "${YELLOW}Note:${NC} For full testing with Redis, Elasticsearch, and Qdrant:"
echo "- Start Redis: docker run -d -p 6379:6379 redis:alpine"
echo "- Start Elasticsearch: docker run -d -p 9200:9200 elasticsearch:8.11.0"
echo "- Start Qdrant: docker run -d -p 6333:6333 qdrant/qdrant"
echo "- Run: ./test-database-level1.sh"