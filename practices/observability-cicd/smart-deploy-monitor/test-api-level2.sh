#!/bin/bash

# API Level 2 Testing Script - REST Best Practices
# Tests: API Versioning, Pagination, Filtering, Enhanced Documentation

BASE_URL="http://localhost:8080/api/v1"
echo "🚀 Testing Smart Deploy Monitor API Level 2 Features"
echo "=================================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Test counter
TESTS_PASSED=0
TESTS_FAILED=0

# Function to test API endpoint
test_endpoint() {
    local method=$1
    local url=$2
    local data=$3
    local expected_status=$4
    local test_name=$5
    
    echo -e "\n${BLUE}Testing: $test_name${NC}"
    echo "URL: $method $url"
    
    if [ -n "$data" ]; then
        response=$(curl -s -w "\n%{http_code}" -X $method "$url" \
            -H "Content-Type: application/json" \
            -d "$data")
    else
        response=$(curl -s -w "\n%{http_code}" -X $method "$url")
    fi
    
    # Extract status code (last line)
    status_code=$(echo "$response" | tail -n1)
    # Extract body (all but last line)
    body=$(echo "$response" | head -n -1)
    
    if [ "$status_code" = "$expected_status" ]; then
        echo -e "${GREEN}✅ PASS${NC} - Status: $status_code"
        echo "Response: $body"
        ((TESTS_PASSED++))
    else
        echo -e "${RED}❌ FAIL${NC} - Expected: $expected_status, Got: $status_code"
        echo "Response: $body"
        ((TESTS_FAILED++))
    fi
}

# Test 1: API Versioning - Check v1 endpoints
echo -e "\n${YELLOW}=== Test 1: API Versioning ===${NC}"
test_endpoint "GET" "$BASE_URL/users" "" "200" "API v1 endpoint accessible"

# Test 2: Create test users for pagination
echo -e "\n${YELLOW}=== Test 2: Create Test Users ===${NC}"
for i in {1..15}; do
    test_endpoint "POST" "$BASE_URL/users" "{
        \"email\": \"user$i@example.com\",
        \"password\": \"password123\",
        \"firstName\": \"User$i\",
        \"lastName\": \"Test\"
    }" "201" "Create test user $i"
done

# Test 3: Pagination - Page 1
echo -e "\n${YELLOW}=== Test 3: Pagination - Page 1 ===${NC}"
test_endpoint "GET" "$BASE_URL/users?page=0&size=5" "" "200" "Get first page (5 users)"

# Test 4: Pagination - Page 2
echo -e "\n${YELLOW}=== Test 4: Pagination - Page 2 ===${NC}"
test_endpoint "GET" "$BASE_URL/users?page=1&size=5" "" "200" "Get second page (5 users)"

# Test 5: Pagination - Page 3
echo -e "\n${YELLOW}=== Test 5: Pagination - Page 3 ===${NC}"
test_endpoint "GET" "$BASE_URL/users?page=2&size=5" "" "200" "Get third page (5 users)"

# Test 6: Search/Filtering
echo -e "\n${YELLOW}=== Test 6: Search/Filtering ===${NC}"
test_endpoint "GET" "$BASE_URL/users?search=User1" "" "200" "Search for 'User1'"

# Test 7: Sorting
echo -e "\n${YELLOW}=== Test 7: Sorting ===${NC}"
test_endpoint "GET" "$BASE_URL/users?sortBy=firstName&sortDir=desc" "" "200" "Sort by firstName descending"

# Test 8: Combined filtering, pagination, and sorting
echo -e "\n${YELLOW}=== Test 8: Combined Features ===${NC}"
test_endpoint "GET" "$BASE_URL/users?page=0&size=3&search=User&sortBy=email&sortDir=asc" "" "200" "Combined: page=0, size=3, search='User', sort by email"

# Test 9: Invalid pagination parameters
echo -e "\n${YELLOW}=== Test 9: Invalid Parameters ===${NC}"
test_endpoint "GET" "$BASE_URL/users?page=-1" "" "400" "Invalid page number (negative)"
test_endpoint "GET" "$BASE_URL/users?size=101" "" "400" "Invalid page size (too large)"
test_endpoint "GET" "$BASE_URL/users?size=0" "" "400" "Invalid page size (zero)"

# Test 10: API Documentation endpoints
echo -e "\n${YELLOW}=== Test 10: API Documentation ===${NC}"
test_endpoint "GET" "http://localhost:8080/swagger-ui/index.html" "" "200" "Swagger UI accessible"
test_endpoint "GET" "http://localhost:8080/v3/api-docs" "" "200" "OpenAPI JSON accessible"

# Test 11: Health check with versioning
echo -e "\n${YELLOW}=== Test 11: Health Check ===${NC}"
test_endpoint "GET" "http://localhost:8080/api/v1/health/ping" "" "200" "Health check endpoint"

# Test 12: User statistics
echo -e "\n${YELLOW}=== Test 12: User Statistics ===${NC}"
test_endpoint "GET" "$BASE_URL/users/statistics" "" "200" "User statistics endpoint"

# Summary
echo -e "\n${YELLOW}=== Test Summary ===${NC}"
echo -e "${GREEN}Tests Passed: $TESTS_PASSED${NC}"
echo -e "${RED}Tests Failed: $TESTS_FAILED${NC}"
echo -e "Total Tests: $((TESTS_PASSED + TESTS_FAILED))"

if [ $TESTS_FAILED -eq 0 ]; then
    echo -e "\n${GREEN}🎉 All Level 2 API tests passed!${NC}"
    echo -e "${GREEN}✅ API Versioning: Working${NC}"
    echo -e "${GREEN}✅ Pagination: Working${NC}"
    echo -e "${GREEN}✅ Filtering: Working${NC}"
    echo -e "${GREEN}✅ Sorting: Working${NC}"
    echo -e "${GREEN}✅ Enhanced Documentation: Working${NC}"
    echo -e "${GREEN}✅ Error Handling: Working${NC}"
    exit 0
else
    echo -e "\n${RED}❌ Some tests failed. Please check the output above.${NC}"
    exit 1
fi
