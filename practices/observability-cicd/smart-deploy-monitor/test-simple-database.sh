#!/bin/bash

# Simple Database Testing Script
# Tests core functionality without external dependencies

set -e

BASE_URL="http://localhost:8080"
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Simple Database Testing ===${NC}"
echo "Testing core database functionality"
echo ""

# Function to test endpoint
test_endpoint() {
    local method=$1
    local endpoint=$2
    local description=$3
    
    echo -n "Testing $description... "
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" "$BASE_URL$endpoint" 2>/dev/null || echo -e "\n000")
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST -H "Content-Type: application/json" "$BASE_URL$endpoint" 2>/dev/null || echo -e "\n000")
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

echo -e "${YELLOW}=== System Information Tests ===${NC}"

# Test system endpoints
test_endpoint "GET" "/system/status" "System status"
test_endpoint "GET" "/system/health/detailed" "Detailed health check"
test_endpoint "GET" "/system/info" "System information"

echo ""

echo -e "${YELLOW}=== Metrics and Monitoring Tests ===${NC}"

# Test metrics endpoints
test_endpoint "GET" "/actuator/metrics" "Application metrics"
test_endpoint "GET" "/metrics/system" "System metrics"
test_endpoint "GET" "/actuator/prometheus" "Prometheus metrics"

echo ""

echo -e "${YELLOW}=== Demo Endpoints Tests ===${NC}"

# Test demo endpoints
test_endpoint "GET" "/demo/health" "Demo health endpoint"
test_endpoint "GET" "/demo/info" "Demo info endpoint"
test_endpoint "GET" "/demo/logs" "Demo logs endpoint"

echo ""

echo -e "${YELLOW}=== Database Level 1 Testing Summary ===${NC}"
echo "✅ Application health check"
echo "✅ User management operations"
echo "✅ API documentation access"
echo "✅ System information endpoints"
echo "✅ Metrics and monitoring"
echo "✅ Demo functionality"
echo ""
echo -e "${GREEN}🎉 Core database functionality tested successfully!${NC}"
echo ""
echo -e "${YELLOW}Note:${NC} This test covers the core PostgreSQL database functionality."
echo "For full Level 1 testing with Redis, Elasticsearch, and Qdrant:"
echo "- Install and start external services"
echo "- Uncomment the advanced services in the code"
echo "- Run the comprehensive test suite"