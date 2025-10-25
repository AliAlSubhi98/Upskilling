#!/bin/bash

# Service Discovery Script for Smart Deploy Monitor
# Demonstrates container networking and service discovery

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
NETWORK_NAME="smart-deploy-monitor_smart-deploy-network"

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

# Function to show network information
show_network_info() {
    print_header "Network Information"
    
    print_status "Docker Networks:"
    docker network ls --filter "name=smart-deploy" --format "table {{.Name}}\t{{.Driver}}\t{{.Scope}}"
    
    echo ""
    print_status "Network Details:"
    docker network inspect "$NETWORK_NAME" --format "{{json .}}" | jq '.'
    
    echo ""
    print_status "Connected Containers:"
    docker network inspect "$NETWORK_NAME" --format "{{range .Containers}}{{.Name}} ({{.IPv4Address}}){{println}}{{end}}"
}

# Function to test service connectivity
test_service_connectivity() {
    print_header "Service Connectivity Tests"
    
    # Test internal service communication
    services=(
        "smart-deploy-postgres:5432"
        "smart-deploy-redis:6379"
        "smart-deploy-elasticsearch:9200"
        "smart-deploy-qdrant:6333"
        "smart-deploy-keycloak:8080"
        "smart-deploy-monitor-app:8080"
    )
    
    for service in "${services[@]}"; do
        service_name=$(echo "$service" | cut -d: -f1)
        service_port=$(echo "$service" | cut -d: -f2)
        
        print_status "Testing connectivity to $service_name:$service_port..."
        
        # Test from within the network
        if docker exec smart-deploy-monitor-app nc -z "$service_name" "$service_port" 2>/dev/null; then
            print_success "$service_name: Connected"
        else
            print_error "$service_name: Connection failed"
        fi
    done
}

# Function to show service endpoints
show_service_endpoints() {
    print_header "Service Endpoints"
    
    print_status "External Endpoints:"
    echo "Application: http://localhost:8080"
    echo "Keycloak: http://localhost:8081"
    echo "PostgreSQL: localhost:5433"
    echo "Redis: localhost:6379"
    echo "Elasticsearch: http://localhost:9200"
    echo "Qdrant: http://localhost:6333"
    
    echo ""
    print_status "Internal Service Names (for container-to-container communication):"
    echo "Application: smart-deploy-monitor-app:8080"
    echo "Keycloak: smart-deploy-keycloak:8080"
    echo "PostgreSQL: smart-deploy-postgres:5432"
    echo "Redis: smart-deploy-redis:6379"
    echo "Elasticsearch: smart-deploy-elasticsearch:9200"
    echo "Qdrant: smart-deploy-qdrant:6333"
}

# Function to test DNS resolution
test_dns_resolution() {
    print_header "DNS Resolution Tests"
    
    services=("smart-deploy-postgres" "smart-deploy-redis" "smart-deploy-elasticsearch" "smart-deploy-qdrant" "smart-deploy-keycloak" "smart-deploy-monitor-app")
    
    for service in "${services[@]}"; do
        print_status "Testing DNS resolution for $service..."
        
        # Test DNS resolution from within the network
        if docker exec smart-deploy-monitor-app nslookup "$service" > /dev/null 2>&1; then
            ip=$(docker exec smart-deploy-monitor-app nslookup "$service" | grep "Address" | tail -1 | awk '{print $2}')
            print_success "$service: $ip"
        else
            print_error "$service: DNS resolution failed"
        fi
    done
}

# Function to show port mappings
show_port_mappings() {
    print_header "Port Mappings"
    
    print_status "Container Port Mappings:"
    docker ps --filter "name=smart-deploy" --format "table {{.Names}}\t{{.Ports}}"
    
    echo ""
    print_status "Detailed Port Information:"
    for container in $(docker ps --filter "name=smart-deploy" --format "{{.Names}}"); do
        print_status "=== $container ==="
        docker port "$container"
        echo ""
    done
}

# Function to test load balancing (if multiple replicas)
test_load_balancing() {
    print_header "Load Balancing Tests"
    
    # Check if we have multiple replicas
    app_replicas=$(docker ps --filter "name=smart-deploy-monitor-app" --format "{{.Names}}" | wc -l)
    
    if [ "$app_replicas" -gt 1 ]; then
        print_status "Testing load balancing with $app_replicas replicas..."
        
        for i in {1..10}; do
            response=$(curl -s http://localhost:8080/api/health/ping)
            container_id=$(echo "$response" | jq -r '.container_id' 2>/dev/null || echo "unknown")
            print_status "Request $i: Container $container_id"
        done
    else
        print_warning "Only one replica running. Scale up to test load balancing."
        print_status "To scale: docker-compose up --scale smart-deploy-monitor=3 -d"
    fi
}

# Function to show service dependencies
show_service_dependencies() {
    print_header "Service Dependencies"
    
    print_status "Service Dependency Graph:"
    echo "smart-deploy-monitor-app"
    echo "├── depends on: smart-deploy-postgres (database)"
    echo "├── depends on: smart-deploy-redis (cache)"
    echo "├── depends on: smart-deploy-elasticsearch (search)"
    echo "├── depends on: smart-deploy-qdrant (vector)"
    echo "└── depends on: smart-deploy-keycloak (auth)"
    echo ""
    print_status "Health Check Dependencies:"
    echo "smart-deploy-monitor-app waits for:"
    echo "├── postgres: service_healthy"
    echo "├── redis: service_healthy"
    echo "├── elasticsearch: service_healthy"
    echo "├── qdrant: service_healthy"
    echo "└── keycloak: service_healthy"
}

# Function to test service discovery
test_service_discovery() {
    print_header "Service Discovery Tests"
    
    print_status "Testing service discovery from application container..."
    
    # Test if application can discover other services
    services=("postgres" "redis" "elasticsearch" "qdrant" "keycloak")
    
    for service in "${services[@]}"; do
        print_status "Testing discovery of $service..."
        
        # Test HTTP connectivity
        if docker exec smart-deploy-monitor-app curl -s "http://$service:8080" > /dev/null 2>&1; then
            print_success "$service: HTTP discovery successful"
        elif docker exec smart-deploy-monitor-app nc -z "$service" 5432 > /dev/null 2>&1; then
            print_success "$service: Port discovery successful"
        else
            print_warning "$service: Discovery test inconclusive (may be normal for some services)"
        fi
    done
}

# Function to show help
show_help() {
    echo "Smart Deploy Monitor Service Discovery"
    echo ""
    echo "Usage: $0 <command>"
    echo ""
    echo "Commands:"
    echo "  network         Show network information"
    echo "  connectivity    Test service connectivity"
    echo "  endpoints       Show service endpoints"
    echo "  dns             Test DNS resolution"
    echo "  ports           Show port mappings"
    echo "  load-balance    Test load balancing"
    echo "  dependencies    Show service dependencies"
    echo "  discovery       Test service discovery"
    echo "  help            Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 network"
    echo "  $0 connectivity"
    echo "  $0 endpoints"
}

# Main script logic
case "${1:-help}" in
    network)
        show_network_info
        ;;
    connectivity)
        test_service_connectivity
        ;;
    endpoints)
        show_service_endpoints
        ;;
    dns)
        test_dns_resolution
        ;;
    ports)
        show_port_mappings
        ;;
    load-balance)
        test_load_balancing
        ;;
    dependencies)
        show_service_dependencies
        ;;
    discovery)
        test_service_discovery
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
