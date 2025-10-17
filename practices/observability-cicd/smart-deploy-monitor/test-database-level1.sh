#!/bin/bash

# Database Level 1 Testing Script
# Tests Redis TTL-based caching, Elasticsearch search/filter, and Qdrant vector operations

set -e

BASE_URL="http://localhost:8080"
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Database Level 1 Testing ===${NC}"
echo "Testing Redis TTL-based caching, Elasticsearch search/filter, and Qdrant vector operations"
echo ""

# Function to test endpoint
test_endpoint() {
    local method=$1
    local endpoint=$2
    local description=$3
    local data=$4
    
    echo -n "Testing $description... "
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" "$BASE_URL$endpoint")
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST -H "Content-Type: application/json" -d "$data" "$BASE_URL$endpoint")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | head -n -1)
    
    if [ "$http_code" = "200" ]; then
        echo -e "${GREEN}PASS${NC}"
        return 0
    else
        echo -e "${RED}FAIL (HTTP $http_code)${NC}"
        echo "Response: $body"
        return 1
    fi
}

# Function to test with parameters
test_endpoint_with_params() {
    local method=$1
    local endpoint=$2
    local description=$3
    local params=$4
    
    echo -n "Testing $description... "
    
    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" "$BASE_URL$endpoint?$params")
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST "$BASE_URL$endpoint?$params")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | head -n -1)
    
    if [ "$http_code" = "200" ]; then
        echo -e "${GREEN}PASS${NC}"
        return 0
    else
        echo -e "${RED}FAIL (HTTP $http_code)${NC}"
        echo "Response: $body"
        return 1
    fi
}

echo -e "${YELLOW}=== Redis TTL-based Caching Tests ===${NC}"

# Test Redis cache operations
test_endpoint_with_params "POST" "/api/database/cache/set" "Redis: Set cache with TTL" "key=test:key&value=test:value&ttlSeconds=60"
test_endpoint_with_params "GET" "/api/database/cache/get" "Redis: Get from cache" "key=test:key"
test_endpoint_with_params "POST" "/api/database/cache/user" "Redis: Cache user data" "userId=user123&userData=John Doe&ttlMinutes=5"
test_endpoint_with_params "GET" "/api/database/cache/user/user123" "Redis: Get cached user"

echo ""

echo -e "${YELLOW}=== Elasticsearch Search/Filter Tests ===${NC}"

# Test Elasticsearch operations
test_endpoint_with_params "GET" "/api/database/search/filters" "Elasticsearch: Search with filters" "email=test@example.com&isActive=true"
test_endpoint_with_params "GET" "/api/database/search/queries" "Elasticsearch: Search with queries" "searchText=test"
test_endpoint_with_params "GET" "/api/database/search/combined" "Elasticsearch: Combined search" "searchText=test&role=USER&isActive=true"

echo ""

echo -e "${YELLOW}=== Qdrant Vector Operations Tests ===${NC}"

# Test Qdrant operations
test_endpoint "POST" "/api/database/vector/collection" "Qdrant: Create vector collection"
test_endpoint_with_params "POST" "/api/database/vector/insert" "Qdrant: Insert vector" "pointId=point123&vector=0.1,0.2,0.3,0.4,0.5&metadata=test"
test_endpoint_with_params "GET" "/api/database/vector/search" "Qdrant: Search vectors" "queryVector=0.1,0.2,0.3,0.4,0.5&limit=3"
test_endpoint_with_params "GET" "/api/database/vector/search-filtered" "Qdrant: Search vectors with filter" "queryVector=0.1,0.2,0.3,0.4,0.5&filterKey=metadata&filterValue=test&limit=3"

echo ""

echo -e "${YELLOW}=== Database Statistics ===${NC}"

# Test database statistics
test_endpoint "GET" "/api/database/stats" "Database: Get statistics"

echo ""

echo -e "${YELLOW}=== Level 1 Demo ===${NC}"

# Test comprehensive Level 1 demo
test_endpoint "POST" "/api/database/demo/level1" "Database: Level 1 comprehensive demo"

echo ""

echo -e "${YELLOW}=== Level 1 Database Operations Summary ===${NC}"
echo "✅ Redis TTL-based caching operations"
echo "✅ Elasticsearch search/filter operations" 
echo "✅ Qdrant vector insert/search operations"
echo "✅ Database statistics and monitoring"
echo "✅ Comprehensive Level 1 demonstration"
echo ""
echo -e "${GREEN}🎉 All Level 1 Database operations completed successfully!${NC}"
echo ""
echo -e "${YELLOW}Next Steps:${NC}"
echo "- Level 2: Schema Design (normalization, indexing, analyzers)"
echo "- Level 3: Advanced Usage (SQL functions, aggregations, optimization)"
echo "- Level 4: Operational Architecture (replication, sharding, caching patterns)"
echo "- Level 5: Distributed DBs (geo-distribution, failover, hybrid search)"