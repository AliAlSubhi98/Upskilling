#!/bin/bash

# Database Testing Script for Smart Deploy Monitor
# This script demonstrates database functionality

echo "🗄️  Smart Deploy Monitor - Database Testing"
echo "=============================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if application is running
check_app_running() {
    if curl -s http://localhost:8080/actuator/health > /dev/null 2>&1; then
        return 0
    else
        return 1
    fi
}

# Test database connection
test_database_connection() {
    print_status "Testing database connection..."
    
    if check_app_running; then
        response=$(curl -s http://localhost:8080/actuator/health)
        if echo "$response" | grep -q "UP"; then
            print_success "Application is running and healthy"
            return 0
        else
            print_error "Application is running but not healthy"
            return 1
        fi
    else
        print_error "Application is not running on port 8080"
        print_status "Please start the application first: mvn spring-boot:run"
        return 1
    fi
}

# Test user creation
test_user_creation() {
    print_status "Testing user creation..."
    
    # Create a test user
    response=$(curl -s -X POST http://localhost:8080/api/users \
        -H "Content-Type: application/json" \
        -d '{
            "email": "test@example.com",
            "password": "password123",
            "firstName": "Test",
            "lastName": "User"
        }')
    
    if echo "$response" | grep -q "success.*true"; then
        print_success "User created successfully"
        echo "Response: $response"
        return 0
    else
        print_error "Failed to create user"
        echo "Response: $response"
        return 1
    fi
}

# Test user retrieval
test_user_retrieval() {
    print_status "Testing user retrieval..."
    
    # Get all users
    response=$(curl -s http://localhost:8080/api/users)
    
    if echo "$response" | grep -q "success.*true"; then
        print_success "Users retrieved successfully"
        echo "Response: $response"
        return 0
    else
        print_error "Failed to retrieve users"
        echo "Response: $response"
        return 1
    fi
}

# Test user statistics
test_user_statistics() {
    print_status "Testing user statistics..."
    
    response=$(curl -s http://localhost:8080/api/users/statistics)
    
    if echo "$response" | grep -q "success.*true"; then
        print_success "User statistics retrieved successfully"
        echo "Response: $response"
        return 0
    else
        print_error "Failed to retrieve user statistics"
        echo "Response: $response"
        return 1
    fi
}

# Test password verification
test_password_verification() {
    print_status "Testing password verification..."
    
    response=$(curl -s -X POST http://localhost:8080/api/users/verify-password \
        -H "Content-Type: application/json" \
        -d '{
            "email": "test@example.com",
            "password": "password123"
        }')
    
    if echo "$response" | grep -q "valid.*true"; then
        print_success "Password verification successful"
        echo "Response: $response"
        return 0
    else
        print_error "Password verification failed"
        echo "Response: $response"
        return 1
    fi
}

# Main test function
run_tests() {
    echo ""
    print_status "Starting database tests..."
    echo ""
    
    local tests_passed=0
    local tests_total=5
    
    # Test 1: Database connection
    if test_database_connection; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 2: User creation
    if test_user_creation; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 3: User retrieval
    if test_user_retrieval; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 4: User statistics
    if test_user_statistics; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 5: Password verification
    if test_password_verification; then
        ((tests_passed++))
    fi
    echo ""
    
    # Summary
    echo "=============================================="
    print_status "Test Results: $tests_passed/$tests_total tests passed"
    
    if [ $tests_passed -eq $tests_total ]; then
        print_success "All database tests passed! 🎉"
        return 0
    else
        print_warning "Some tests failed. Check the output above."
        return 1
    fi
}

# Help function
show_help() {
    echo "Usage: $0 [OPTION]"
    echo ""
    echo "Options:"
    echo "  -h, --help     Show this help message"
    echo "  -t, --test     Run all database tests"
    echo "  -c, --check    Check if application is running"
    echo ""
    echo "Examples:"
    echo "  $0 --test      # Run all tests"
    echo "  $0 --check     # Check application status"
}

# Main script logic
case "${1:-}" in
    -h|--help)
        show_help
        ;;
    -t|--test)
        run_tests
        ;;
    -c|--check)
        test_database_connection
        ;;
    "")
        print_status "No option specified. Running all tests..."
        run_tests
        ;;
    *)
        print_error "Unknown option: $1"
        show_help
        exit 1
        ;;
esac