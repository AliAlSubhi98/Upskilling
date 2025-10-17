#!/bin/bash

# Basic Testing Script for Smart Deploy Monitor
# This script tests the basic functionality without requiring a database

echo "🧪 Smart Deploy Monitor - Basic Testing"
echo "========================================"

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

# Test 1: Compilation
test_compilation() {
    print_status "Testing compilation..."
    
    if mvn clean compile -q; then
        print_success "Compilation successful"
        return 0
    else
        print_error "Compilation failed"
        return 1
    fi
}

# Test 2: Check if JAR can be built
test_jar_build() {
    print_status "Testing JAR build..."
    
    if mvn package -DskipTests -q; then
        print_success "JAR build successful"
        return 0
    else
        print_error "JAR build failed"
        return 1
    fi
}

# Test 3: Check if application can start (without database)
test_application_start() {
    print_status "Testing application startup (no database)..."
    
    # Start application in background
    mvn spring-boot:run -Dspring-boot.run.profiles=test > app.log 2>&1 &
    APP_PID=$!
    
    # Wait for application to start
    sleep 15
    
    # Check if application is running
    if ps -p $APP_PID > /dev/null; then
        print_success "Application started successfully (PID: $APP_PID)"
        
        # Test basic endpoint
        if curl -s http://localhost:8080/demo/health > /dev/null 2>&1; then
            print_success "Basic endpoint is responding"
            # Kill the application
            kill $APP_PID
            return 0
        else
            print_warning "Application started but endpoints not responding"
            kill $APP_PID
            return 1
        fi
    else
        print_error "Application failed to start"
        return 1
    fi
}

# Test 4: Check project structure
test_project_structure() {
    print_status "Testing project structure..."
    
    local required_files=(
        "pom.xml"
        "src/main/java/com/upskilling/smartdeploymonitor/SmartDeployMonitorApplication.java"
        "src/main/java/com/upskilling/smartdeploymonitor/entity/User.java"
        "src/main/java/com/upskilling/smartdeploymonitor/repository/UserRepository.java"
        "src/main/java/com/upskilling/smartdeploymonitor/service/UserService.java"
        "src/main/java/com/upskilling/smartdeploymonitor/controller/UserController.java"
        "src/main/resources/application.yml"
        "docker-compose.yml"
        "DATABASE-SETUP.md"
    )
    
    local missing_files=()
    
    for file in "${required_files[@]}"; do
        if [ ! -f "$file" ]; then
            missing_files+=("$file")
        fi
    done
    
    if [ ${#missing_files[@]} -eq 0 ]; then
        print_success "All required files present"
        return 0
    else
        print_error "Missing files: ${missing_files[*]}"
        return 1
    fi
}

# Test 5: Check dependencies
test_dependencies() {
    print_status "Testing Maven dependencies..."
    
    if mvn dependency:resolve -q; then
        print_success "All dependencies resolved"
        return 0
    else
        print_error "Dependency resolution failed"
        return 1
    fi
}

# Main test function
run_tests() {
    echo ""
    print_status "Starting basic tests..."
    echo ""
    
    local tests_passed=0
    local tests_total=5
    
    # Test 1: Project structure
    if test_project_structure; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 2: Dependencies
    if test_dependencies; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 3: Compilation
    if test_compilation; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 4: JAR build
    if test_jar_build; then
        ((tests_passed++))
    fi
    echo ""
    
    # Test 5: Application start (optional - might fail without database)
    if test_application_start; then
        ((tests_passed++))
    else
        print_warning "Application start test failed (expected without database)"
    fi
    echo ""
    
    # Summary
    echo "========================================"
    print_status "Test Results: $tests_passed/$tests_total tests passed"
    
    if [ $tests_passed -ge 4 ]; then
        print_success "Basic tests passed! 🎉"
        print_status "Database integration is ready for testing with a real database"
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
    echo "  -t, --test     Run all basic tests"
    echo "  -c, --compile  Test compilation only"
    echo "  -j, --jar      Test JAR build only"
    echo ""
    echo "Examples:"
    echo "  $0 --test      # Run all tests"
    echo "  $0 --compile   # Test compilation only"
}

# Main script logic
case "${1:-}" in
    -h|--help)
        show_help
        ;;
    -t|--test)
        run_tests
        ;;
    -c|--compile)
        test_compilation
        ;;
    -j|--jar)
        test_jar_build
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