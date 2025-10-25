#!/bin/bash

# Security Assessment Script for Smart Deploy Monitor
# Demonstrates security analysis, vulnerability scanning, and security best practices

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Configuration
BASE_URL="http://localhost:8080"
APP_JAR="target/smart-deploy-monitor-1.0.0.jar"
LOG_FILE="security-assessment.log"

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

print_header() {
    echo -e "${CYAN}================================${NC}"
    echo -e "${CYAN}$1${NC}"
    echo -e "${CYAN}================================${NC}"
}

# Function to check if application is running
check_application() {
    if ! curl -s "$BASE_URL/api/health/ping" > /dev/null 2>&1; then
        print_error "Application is not running at $BASE_URL"
        return 1
    fi
    print_success "Application is running"
    return 0
}

# Function to perform security headers check
check_security_headers() {
    print_header "Security Headers Analysis"
    
    print_status "Checking security headers..."
    
    # Test various endpoints for security headers
    endpoints=(
        "/api/health/ping"
        "/api/health/detailed"
        "/api/design/info"
        "/api/gateway/users"
    )
    
    for endpoint in "${endpoints[@]}"; do
        print_status "Testing $endpoint..."
        response=$(curl -s -I "$BASE_URL$endpoint")
        
        # Check for security headers
        if echo "$response" | grep -i "x-content-type-options" > /dev/null; then
            print_success "$endpoint: X-Content-Type-Options present"
        else
            print_warning "$endpoint: X-Content-Type-Options missing"
        fi
        
        if echo "$response" | grep -i "x-frame-options" > /dev/null; then
            print_success "$endpoint: X-Frame-Options present"
        else
            print_warning "$endpoint: X-Frame-Options missing"
        fi
        
        if echo "$response" | grep -i "x-xss-protection" > /dev/null; then
            print_success "$endpoint: X-XSS-Protection present"
        else
            print_warning "$endpoint: X-XSS-Protection missing"
        fi
        
        if echo "$response" | grep -i "strict-transport-security" > /dev/null; then
            print_success "$endpoint: Strict-Transport-Security present"
        else
            print_warning "$endpoint: Strict-Transport-Security missing"
        fi
        
        if echo "$response" | grep -i "content-security-policy" > /dev/null; then
            print_success "$endpoint: Content-Security-Policy present"
        else
            print_warning "$endpoint: Content-Security-Policy missing"
        fi
        
        echo ""
    done
}

# Function to test input validation
test_input_validation() {
    print_header "Input Validation Testing"
    
    print_status "Testing SQL Injection attempts..."
    
    # Test SQL injection on various endpoints
    sql_payloads=(
        "'; DROP TABLE users; --"
        "1' OR '1'='1"
        "admin'--"
        "1' UNION SELECT * FROM users--"
    )
    
    for payload in "${sql_payloads[@]}"; do
        print_status "Testing payload: $payload"
        response=$(curl -s -X POST -H "Content-Type: application/json" \
            -d "{\"email\":\"$payload\",\"password\":\"test\"}" \
            "$BASE_URL/api/auth/login" 2>/dev/null || echo "Request failed")
        
        if echo "$response" | grep -i "error\|exception\|sql" > /dev/null; then
            print_warning "Potential SQL injection vulnerability detected"
        else
            print_success "SQL injection attempt properly handled"
        fi
    done
    
    print_status "Testing XSS attempts..."
    
    # Test XSS payloads
    xss_payloads=(
        "<script>alert('XSS')</script>"
        "javascript:alert('XSS')"
        "<img src=x onerror=alert('XSS')>"
        "';alert('XSS');//"
    )
    
    for payload in "${xss_payloads[@]}"; do
        print_status "Testing XSS payload: $payload"
        response=$(curl -s -X POST -H "Content-Type: application/json" \
            -d "{\"name\":\"$payload\",\"email\":\"test@example.com\",\"message\":\"test\"}" \
            "$BASE_URL/api/design/contact" 2>/dev/null || echo "Request failed")
        
        if echo "$response" | grep -i "$payload" > /dev/null; then
            print_warning "Potential XSS vulnerability detected"
        else
            print_success "XSS attempt properly handled"
        fi
    done
}

# Function to test authentication and authorization
test_auth_security() {
    print_header "Authentication & Authorization Testing"
    
    print_status "Testing unauthenticated access to protected endpoints..."
    
    protected_endpoints=(
        "/api/users"
        "/api/auth/refresh"
        "/api/gateway/users"
    )
    
    for endpoint in "${protected_endpoints[@]}"; do
        print_status "Testing $endpoint without authentication..."
        response=$(curl -s -w "%{http_code}" "$BASE_URL$endpoint" -o /dev/null)
        
        if [ "$response" = "401" ] || [ "$response" = "403" ]; then
            print_success "$endpoint: Properly protected (HTTP $response)"
        else
            print_warning "$endpoint: May not be properly protected (HTTP $response)"
        fi
    done
    
    print_status "Testing authentication bypass attempts..."
    
    # Test various authentication bypass techniques
    bypass_attempts=(
        "Authorization: Bearer invalid-token"
        "Authorization: Bearer null"
        "Authorization: Bearer "
        "X-Forwarded-For: 127.0.0.1"
        "X-Real-IP: 127.0.0.1"
    )
    
    for attempt in "${bypass_attempts[@]}"; do
        print_status "Testing bypass attempt: $attempt"
        response=$(curl -s -w "%{http_code}" -H "$attempt" "$BASE_URL/api/users" -o /dev/null)
        
        if [ "$response" = "401" ] || [ "$response" = "403" ]; then
            print_success "Bypass attempt properly blocked (HTTP $response)"
        else
            print_warning "Potential authentication bypass vulnerability (HTTP $response)"
        fi
    done
}

# Function to test rate limiting
test_rate_limiting() {
    print_header "Rate Limiting Testing"
    
    print_status "Testing rate limiting on authentication endpoints..."
    
    # Test rapid requests to login endpoint
    for i in {1..10}; do
        response=$(curl -s -w "%{http_code}" -X POST -H "Content-Type: application/json" \
            -d "{\"email\":\"test@example.com\",\"password\":\"wrongpassword\"}" \
            "$BASE_URL/api/auth/login" -o /dev/null)
        
        if [ "$response" = "429" ]; then
            print_success "Rate limiting working (HTTP $response)"
            break
        elif [ "$i" -eq 10 ]; then
            print_warning "No rate limiting detected after 10 requests"
        fi
        
        sleep 0.1
    done
}

# Function to test HTTPS and SSL configuration
test_ssl_configuration() {
    print_header "SSL/TLS Configuration Testing"
    
    print_status "Testing HTTPS configuration..."
    
    # Test if HTTPS is available
    https_response=$(curl -s -k -w "%{http_code}" "https://localhost:8080/api/health/ping" -o /dev/null 2>/dev/null || echo "000")
    
    if [ "$https_response" = "200" ]; then
        print_success "HTTPS is properly configured"
        
        # Test SSL certificate
        print_status "Testing SSL certificate..."
        if openssl s_client -connect localhost:8080 -servername localhost < /dev/null 2>/dev/null | grep -i "verify return code: 0" > /dev/null; then
            print_success "SSL certificate is valid"
        else
            print_warning "SSL certificate issues detected"
        fi
    else
        print_warning "HTTPS not available or not properly configured"
    fi
}

# Function to test file upload security
test_file_upload_security() {
    print_header "File Upload Security Testing"
    
    print_status "Testing file upload security..."
    
    # Create test files with different extensions
    test_files=(
        "test.txt"
        "test.php"
        "test.jsp"
        "test.exe"
        "test.sh"
    )
    
    for file in "${test_files[@]}"; do
        echo "test content" > "/tmp/$file"
        print_status "Testing upload of $file..."
        
        # Test if file upload endpoint exists and handles file types properly
        response=$(curl -s -w "%{http_code}" -F "file=@/tmp/$file" "$BASE_URL/api/upload" -o /dev/null 2>/dev/null || echo "000")
        
        if [ "$response" = "000" ]; then
            print_status "$file: No file upload endpoint found (expected)"
        elif [ "$response" = "400" ] || [ "$response" = "415" ]; then
            print_success "$file: File type properly rejected (HTTP $response)"
        else
            print_warning "$file: File upload may not be properly secured (HTTP $response)"
        fi
        
        rm -f "/tmp/$file"
    done
}

# Function to test information disclosure
test_information_disclosure() {
    print_header "Information Disclosure Testing"
    
    print_status "Testing for information disclosure..."
    
    # Test common information disclosure endpoints
    disclosure_endpoints=(
        "/actuator"
        "/actuator/env"
        "/actuator/configprops"
        "/actuator/beans"
        "/actuator/mappings"
        "/actuator/health"
        "/swagger-ui.html"
        "/api-docs"
        "/.env"
        "/config"
        "/admin"
        "/debug"
        "/test"
    )
    
    for endpoint in "${disclosure_endpoints[@]}"; do
        print_status "Testing $endpoint..."
        response=$(curl -s -w "%{http_code}" "$BASE_URL$endpoint" -o /dev/null)
        
        if [ "$response" = "404" ]; then
            print_success "$endpoint: Not accessible (HTTP $response)"
        elif [ "$response" = "401" ] || [ "$response" = "403" ]; then
            print_success "$endpoint: Properly protected (HTTP $response)"
        else
            print_warning "$endpoint: May expose sensitive information (HTTP $response)"
        fi
    done
}

# Function to generate security report
generate_security_report() {
    local report_file="security-assessment-report-$(date +%Y%m%d_%H%M%S).txt"
    
    print_header "Generating Security Assessment Report: $report_file"
    
    {
        echo "Smart Deploy Monitor - Security Assessment Report"
        echo "Generated: $(date)"
        echo "=================================================="
        echo ""
        
        echo "Security Headers Analysis:"
        check_security_headers
        echo ""
        
        echo "Input Validation Testing:"
        test_input_validation
        echo ""
        
        echo "Authentication & Authorization Testing:"
        test_auth_security
        echo ""
        
        echo "Rate Limiting Testing:"
        test_rate_limiting
        echo ""
        
        echo "SSL/TLS Configuration:"
        test_ssl_configuration
        echo ""
        
        echo "File Upload Security:"
        test_file_upload_security
        echo ""
        
        echo "Information Disclosure Testing:"
        test_information_disclosure
        echo ""
        
    } > "$report_file"
    
    print_success "Security assessment report generated: $report_file"
}

# Function to show help
show_help() {
    echo "Smart Deploy Monitor Security Assessment"
    echo ""
    echo "Usage: $0 <command>"
    echo ""
    echo "Commands:"
    echo "  headers        Check security headers"
    echo "  validation     Test input validation"
    echo "  auth           Test authentication and authorization"
    echo "  rate-limit     Test rate limiting"
    echo "  ssl            Test SSL/TLS configuration"
    echo "  file-upload    Test file upload security"
    echo "  disclosure     Test information disclosure"
    echo "  report         Generate comprehensive security report"
    echo "  help           Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 headers"
    echo "  $0 validation"
    echo "  $0 report"
}

# Main script logic
case "${1:-help}" in
    headers)
        check_application && check_security_headers
        ;;
    validation)
        check_application && test_input_validation
        ;;
    auth)
        check_application && test_auth_security
        ;;
    rate-limit)
        check_application && test_rate_limiting
        ;;
    ssl)
        check_application && test_ssl_configuration
        ;;
    file-upload)
        check_application && test_file_upload_security
        ;;
    disclosure)
        check_application && test_information_disclosure
        ;;
    report)
        check_application && generate_security_report
        ;;
    help|--help|-h)
        show_help
        ;;
    *)
        print_error "Unknown command: $1"
        show_help
        exit 1
        ;;
esac
