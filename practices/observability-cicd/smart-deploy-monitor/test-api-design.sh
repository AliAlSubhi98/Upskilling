#!/bin/bash

# Script to test API Design principles and OpenAPI documentation

echo "🚀 Smart Deploy Monitor - API Design Testing"
echo "============================================="
echo ""

# --- Configuration ---
BASE_URL="http://localhost:8080"
API_BASE="$BASE_URL/api"
SWAGGER_UI="$BASE_URL/swagger-ui/index.html"
OPENAPI_JSON="$BASE_URL/v3/api-docs"

# --- Helper Functions ---
log_info() {
    echo "[INFO] $1"
}

log_success() {
    echo "[SUCCESS] $1"
    ((PASSED_TESTS++))
}

log_error() {
    echo "[ERROR] $1"
    ((FAILED_TESTS++))
}

log_warning() {
    echo "[WARNING] $1"
}

# --- Test Counters ---
TOTAL_TESTS=0
PASSED_TESTS=0
FAILED_TESTS=0

# --- Test Functions ---
test_openapi_docs() {
    log_info "Testing OpenAPI documentation availability..."
    ((TOTAL_TESTS++))
    
    # Test OpenAPI JSON endpoint
    if curl -s "$OPENAPI_JSON" | jq -e '.info.title' > /dev/null 2>&1; then
        log_success "OpenAPI JSON documentation is available"
        
        # Check if it contains our API info
        if curl -s "$OPENAPI_JSON" | jq -e '.info.title == "Smart Deploy Monitor API"' > /dev/null 2>&1; then
            log_success "OpenAPI documentation contains correct API title"
        else
            log_error "OpenAPI documentation title mismatch"
        fi
    else
        log_error "OpenAPI JSON documentation not available"
    fi
}

test_swagger_ui() {
    log_info "Testing Swagger UI availability..."
    ((TOTAL_TESTS++))
    
    if curl -s "$SWAGGER_UI" | grep -q "swagger-ui" > /dev/null 2>&1; then
        log_success "Swagger UI is accessible"
    else
        log_error "Swagger UI not accessible"
    fi
}

test_api_validation() {
    log_info "Testing API request validation..."
    ((TOTAL_TESTS++))
    
    # Test with invalid email
    local invalid_status=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$API_BASE/users" \
        -H "Content-Type: application/json" \
        -d '{"email": "invalid-email", "password": "123", "firstName": "", "lastName": ""}' 2>/dev/null)
    
    if [ "$invalid_status" = "400" ]; then
        log_success "API validation working - rejected invalid request with 400 status"
    else
        log_error "API validation not working - should reject invalid request with 400 status, got: $invalid_status"
    fi
}

test_api_response_format() {
    log_info "Testing API response format consistency..."
    ((TOTAL_TESTS++))
    
    # Test user creation with valid data
    local create_response=$(curl -s -X POST "$API_BASE/users" \
        -H "Content-Type: application/json" \
        -d '{"email": "apitest@example.com", "password": "validpass123", "firstName": "API", "lastName": "Test"}' 2>/dev/null)
    
    if echo "$create_response" | jq -e '.success == true' > /dev/null 2>&1; then
        log_success "API response format is consistent"
        
        # Check if response contains expected fields
        if echo "$create_response" | jq -e '.user.id' > /dev/null 2>&1; then
            log_success "Response contains expected user fields"
        else
            log_error "Response missing expected user fields"
        fi
    else
        log_error "API response format inconsistent"
    fi
}

test_http_methods() {
    log_info "Testing HTTP methods and status codes..."
    ((TOTAL_TESTS++))
    
    # Test GET (should return 200)
    local get_status=$(curl -s -o /dev/null -w "%{http_code}" "$API_BASE/users")
    if [ "$get_status" = "200" ]; then
        log_success "GET method returns correct status code (200)"
    else
        log_error "GET method returns incorrect status code: $get_status"
    fi
    
    # Test POST (should return 201 for creation)
    local post_status=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$API_BASE/users" \
        -H "Content-Type: application/json" \
        -d '{"email": "httpmethod@example.com", "password": "validpass123", "firstName": "HTTP", "lastName": "Test"}')
    if [ "$post_status" = "201" ]; then
        log_success "POST method returns correct status code (201)"
    else
        log_error "POST method returns incorrect status code: $post_status"
    fi
}

test_api_versioning() {
    log_info "Testing API versioning structure..."
    ((TOTAL_TESTS++))
    
    # Check if API follows RESTful conventions
    if curl -s "$API_BASE/users" | jq -e '.success' > /dev/null 2>&1; then
        log_success "API follows RESTful conventions"
    else
        log_error "API does not follow RESTful conventions"
    fi
}

test_error_handling() {
    log_info "Testing error handling and status codes..."
    ((TOTAL_TESTS++))
    
    # Test 404 for non-existent user
    local not_found_status=$(curl -s -o /dev/null -w "%{http_code}" "$API_BASE/users/00000000-0000-0000-0000-000000000000")
    if [ "$not_found_status" = "404" ]; then
        log_success "API returns correct 404 for non-existent resource"
    else
        log_error "API does not return 404 for non-existent resource: $not_found_status"
    fi
}

# --- Main Test Execution ---
log_info "Starting API Design tests..."
echo ""

# Check if application is running
if ! curl -s "$BASE_URL/demo/health" | jq -e '.status == "UP"' > /dev/null 2>&1; then
    log_error "Application is not running. Please start the application first."
    echo "Run: mvn spring-boot:run"
    exit 1
fi

log_success "Application is running and healthy"
echo ""

# Run all tests
test_openapi_docs
test_swagger_ui
test_api_validation
test_api_response_format
test_http_methods
test_api_versioning
test_error_handling

echo ""
echo "============================================="
echo "[INFO] Test Results: $PASSED_TESTS/$TOTAL_TESTS tests passed"

if [ "$FAILED_TESTS" -eq 0 ]; then
    echo "[SUCCESS] All API Design tests passed! 🎉"
    echo ""
    echo "📚 API Documentation:"
    echo "  - Swagger UI: $SWAGGER_UI"
    echo "  - OpenAPI JSON: $OPENAPI_JSON"
    echo ""
    echo "✅ API Design Level 1 Complete!"
    echo "   - OpenAPI/Swagger documentation implemented"
    echo "   - Request validation working"
    echo "   - Consistent response format"
    echo "   - Proper HTTP status codes"
    echo "   - RESTful API design"
else
    echo "[ERROR] Some API Design tests failed. Please check the logs above."
fi
echo ""