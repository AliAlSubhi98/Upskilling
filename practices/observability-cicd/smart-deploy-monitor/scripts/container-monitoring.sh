#!/bin/bash

# Container Monitoring Script for Smart Deploy Monitor
# Demonstrates container monitoring, health checks, and observability

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# Configuration
PROJECT_NAME="smart-deploy-monitor"
BASE_URL="http://localhost:8080"

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

# Function to check container health
check_container_health() {
    print_header "Container Health Status"
    
    # Get container status
    docker ps --filter "name=smart-deploy" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
    
    echo ""
    print_status "Detailed Health Information:"
    
    # Check each service
    services=("smart-deploy-postgres" "smart-deploy-redis" "smart-deploy-elasticsearch" "smart-deploy-qdrant" "smart-deploy-keycloak" "smart-deploy-monitor-app")
    
    for service in "${services[@]}"; do
        if docker ps --filter "name=$service" --format "{{.Names}}" | grep -q "$service"; then
            health_status=$(docker inspect --format='{{.State.Health.Status}}' "$service" 2>/dev/null || echo "no-healthcheck")
            print_status "$service: $health_status"
        else
            print_error "$service: Not running"
        fi
    done
}

# Function to check application health endpoints
check_application_health() {
    print_header "Application Health Endpoints"
    
    if ! curl -s "$BASE_URL/api/health/ping" > /dev/null 2>&1; then
        print_error "Application is not responding at $BASE_URL"
        return 1
    fi
    
    # Test health endpoints
    endpoints=(
        "/api/health/ping"
        "/api/health/detailed"
        "/api/health/ready"
        "/api/health/live"
    )
    
    for endpoint in "${endpoints[@]}"; do
        print_status "Testing $endpoint..."
        response=$(curl -s "$BASE_URL$endpoint")
        if [ $? -eq 0 ]; then
            print_success "$endpoint: OK"
            echo "$response" | jq . 2>/dev/null || echo "$response"
        else
            print_error "$endpoint: Failed"
        fi
        echo ""
    done
}

# Function to monitor resource usage
monitor_resources() {
    print_header "Resource Usage Monitoring"
    
    # Container resource usage
    print_status "Container Resource Usage:"
    docker stats --no-stream --format "table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}" | head -10
    
    echo ""
    print_status "System Resource Usage:"
    
    # CPU usage
    cpu_usage=$(top -l 1 | grep "CPU usage" | awk '{print $3}' | sed 's/%//')
    print_status "CPU Usage: ${cpu_usage}%"
    
    # Memory usage
    memory_info=$(vm_stat | grep -E "(free|active|inactive|wired)" | awk '{print $3}' | sed 's/\.//')
    print_status "Memory Info: $memory_info"
    
    # Disk usage
    disk_usage=$(df -h / | tail -1 | awk '{print $5}')
    print_status "Disk Usage: $disk_usage"
}

# Function to check network connectivity
check_network_connectivity() {
    print_header "Network Connectivity"
    
    # Check internal network connectivity
    print_status "Internal Network Connectivity:"
    
    # Test PostgreSQL
    if docker exec smart-deploy-postgres pg_isready -U postgres > /dev/null 2>&1; then
        print_success "PostgreSQL: Connected"
    else
        print_error "PostgreSQL: Connection failed"
    fi
    
    # Test Redis
    if docker exec smart-deploy-redis redis-cli ping > /dev/null 2>&1; then
        print_success "Redis: Connected"
    else
        print_error "Redis: Connection failed"
    fi
    
    # Test Elasticsearch
    if curl -s http://localhost:9200/_cluster/health > /dev/null 2>&1; then
        print_success "Elasticsearch: Connected"
    else
        print_error "Elasticsearch: Connection failed"
    fi
    
    # Test Qdrant
    if curl -s http://localhost:6333/healthz > /dev/null 2>&1; then
        print_success "Qdrant: Connected"
    else
        print_error "Qdrant: Connection failed"
    fi
    
    # Test Keycloak
    if curl -s http://localhost:8081/ > /dev/null 2>&1; then
        print_success "Keycloak: Connected"
    else
        print_error "Keycloak: Connection failed"
    fi
}

# Function to show container logs
show_container_logs() {
    local service=${1:-""}
    
    if [ -n "$service" ]; then
        print_header "Logs for $service"
        docker logs --tail 50 "$service" 2>/dev/null || print_error "Service $service not found"
    else
        print_header "Recent Logs from All Services"
        
        services=("smart-deploy-postgres" "smart-deploy-redis" "smart-deploy-elasticsearch" "smart-deploy-qdrant" "smart-deploy-keycloak" "smart-deploy-monitor-app")
        
        for service in "${services[@]}"; do
            if docker ps --filter "name=$service" --format "{{.Names}}" | grep -q "$service"; then
                print_status "=== $service ==="
                docker logs --tail 10 "$service" 2>/dev/null
                echo ""
            fi
        done
    fi
}

# Function to perform load testing
load_test() {
    print_header "Load Testing Application"
    
    if ! curl -s "$BASE_URL/api/health/ping" > /dev/null 2>&1; then
        print_error "Application is not responding. Cannot perform load test."
        return 1
    fi
    
    print_status "Performing load test on health endpoints..."
    
    # Test health endpoint with multiple requests
    for i in {1..10}; do
        start_time=$(date +%s%N)
        curl -s "$BASE_URL/api/health/ping" > /dev/null
        end_time=$(date +%s%N)
        response_time=$(( (end_time - start_time) / 1000000 ))
        print_status "Request $i: ${response_time}ms"
    done
    
    print_success "Load test completed"
}

# Function to check security
check_security() {
    print_header "Security Checks"
    
    # Check if containers are running as non-root
    print_status "Container User Security:"
    containers=("smart-deploy-postgres" "smart-deploy-redis" "smart-deploy-elasticsearch" "smart-deploy-qdrant" "smart-deploy-keycloak" "smart-deploy-monitor-app")
    
    for container in "${containers[@]}"; do
        if docker ps --filter "name=$container" --format "{{.Names}}" | grep -q "$container"; then
            user=$(docker exec "$container" whoami 2>/dev/null || echo "unknown")
            print_status "$container: Running as $user"
        fi
    done
    
    # Check exposed ports
    print_status "Exposed Ports:"
    docker ps --format "table {{.Names}}\t{{.Ports}}" | grep smart-deploy
    
    # Check for security updates
    print_status "Security Updates Check:"
    for container in "${containers[@]}"; do
        if docker ps --filter "name=$container" --format "{{.Names}}" | grep -q "$container"; then
            if docker exec "$container" apk list --upgradable 2>/dev/null | grep -q "upgradable"; then
                print_warning "$container: Has upgradable packages"
            else
                print_success "$container: No security updates needed"
            fi
        fi
    done
}

# Function to generate monitoring report
generate_report() {
    local report_file="container-monitoring-report-$(date +%Y%m%d_%H%M%S).txt"
    
    print_header "Generating Monitoring Report: $report_file"
    
    {
        echo "Smart Deploy Monitor - Container Monitoring Report"
        echo "Generated: $(date)"
        echo "=================================================="
        echo ""
        
        echo "Container Health Status:"
        docker ps --filter "name=smart-deploy" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
        echo ""
        
        echo "Resource Usage:"
        docker stats --no-stream --format "table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}"
        echo ""
        
        echo "Network Connectivity:"
        check_network_connectivity
        echo ""
        
        echo "Application Health:"
        check_application_health
        echo ""
        
    } > "$report_file"
    
    print_success "Report generated: $report_file"
}

# Function to show help
show_help() {
    echo "Smart Deploy Monitor Container Monitoring"
    echo ""
    echo "Usage: $0 <command> [options]"
    echo ""
    echo "Commands:"
    echo "  health          Check container health status"
    echo "  app-health      Check application health endpoints"
    echo "  resources       Monitor resource usage"
    echo "  network         Check network connectivity"
    echo "  logs [service]  Show container logs"
    echo "  load-test       Perform load testing"
    echo "  security        Check security status"
    echo "  report          Generate monitoring report"
    echo "  help            Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 health"
    echo "  $0 logs smart-deploy-monitor-app"
    echo "  $0 load-test"
    echo "  $0 report"
}

# Main script logic
case "${1:-help}" in
    health)
        check_container_health
        ;;
    app-health)
        check_application_health
        ;;
    resources)
        monitor_resources
        ;;
    network)
        check_network_connectivity
        ;;
    logs)
        show_container_logs "$2"
        ;;
    load-test)
        load_test
        ;;
    security)
        check_security
        ;;
    report)
        generate_report
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
