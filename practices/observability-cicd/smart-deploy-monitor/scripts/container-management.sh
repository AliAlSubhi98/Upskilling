#!/bin/bash

# Container Management Script for Smart Deploy Monitor
# Demonstrates advanced container orchestration and management

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
COMPOSE_FILE="docker-compose.yml"
PROJECT_NAME="smart-deploy-monitor"

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

# Function to check if Docker is running
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        print_error "Docker is not running. Please start Docker Desktop."
        exit 1
    fi
    print_success "Docker is running"
}

# Function to build and start all services
start_services() {
    print_status "Building and starting all services..."
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME up --build -d
    print_success "All services started successfully"
}

# Function to stop all services
stop_services() {
    print_status "Stopping all services..."
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME down
    print_success "All services stopped"
}

# Function to restart services
restart_services() {
    print_status "Restarting services..."
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME restart
    print_success "Services restarted"
}

# Function to show service status
show_status() {
    print_status "Service Status:"
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME ps
    
    echo ""
    print_status "Health Check Status:"
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME ps --format "table {{.Name}}\t{{.Status}}\t{{.Ports}}"
}

# Function to show logs
show_logs() {
    local service=${1:-""}
    if [ -n "$service" ]; then
        print_status "Showing logs for $service..."
        docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME logs -f $service
    else
        print_status "Showing logs for all services..."
        docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME logs -f
    fi
}

# Function to clean up containers and volumes
cleanup() {
    print_warning "This will remove all containers, networks, and volumes. Are you sure? (y/N)"
    read -r response
    if [[ "$response" =~ ^([yY][eE][sS]|[yY])$ ]]; then
        print_status "Cleaning up..."
        docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME down -v --remove-orphans
        docker system prune -f
        print_success "Cleanup completed"
    else
        print_status "Cleanup cancelled"
    fi
}

# Function to scale services
scale_service() {
    local service=$1
    local replicas=$2
    
    if [ -z "$service" ] || [ -z "$replicas" ]; then
        print_error "Usage: scale <service> <replicas>"
        exit 1
    fi
    
    print_status "Scaling $service to $replicas replicas..."
    docker-compose -f $COMPOSE_FILE -p $PROJECT_NAME up --scale $service=$replicas -d
    print_success "Service $service scaled to $replicas replicas"
}

# Function to show resource usage
show_resources() {
    print_status "Container Resource Usage:"
    docker stats --format "table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\t{{.NetIO}}\t{{.BlockIO}}" --no-stream
}

# Function to backup volumes
backup_volumes() {
    local backup_dir="./backups/$(date +%Y%m%d_%H%M%S)"
    mkdir -p "$backup_dir"
    
    print_status "Backing up volumes to $backup_dir..."
    
    # Backup PostgreSQL data
    docker run --rm -v smart-deploy-monitor_postgres_data:/data -v "$(pwd)/$backup_dir":/backup alpine tar czf /backup/postgres_data.tar.gz -C /data .
    
    # Backup Redis data
    docker run --rm -v smart-deploy-monitor_redis_data:/data -v "$(pwd)/$backup_dir":/backup alpine tar czf /backup/redis_data.tar.gz -C /data .
    
    print_success "Backup completed: $backup_dir"
}

# Function to restore volumes
restore_volumes() {
    local backup_dir=$1
    
    if [ -z "$backup_dir" ]; then
        print_error "Usage: restore <backup_directory>"
        exit 1
    fi
    
    if [ ! -d "$backup_dir" ]; then
        print_error "Backup directory $backup_dir does not exist"
        exit 1
    fi
    
    print_warning "This will restore volumes from $backup_dir. Are you sure? (y/N)"
    read -r response
    if [[ "$response" =~ ^([yY][eE][sS]|[yY])$ ]]; then
        print_status "Restoring volumes..."
        
        # Restore PostgreSQL data
        docker run --rm -v smart-deploy-monitor_postgres_data:/data -v "$(pwd)/$backup_dir":/backup alpine tar xzf /backup/postgres_data.tar.gz -C /data
        
        # Restore Redis data
        docker run --rm -v smart-deploy-monitor_redis_data:/data -v "$(pwd)/$backup_dir":/backup alpine tar xzf /backup/redis_data.tar.gz -C /data
        
        print_success "Volumes restored from $backup_dir"
    else
        print_status "Restore cancelled"
    fi
}

# Function to show help
show_help() {
    echo "Smart Deploy Monitor Container Management"
    echo ""
    echo "Usage: $0 <command> [options]"
    echo ""
    echo "Commands:"
    echo "  start           Start all services"
    echo "  stop            Stop all services"
    echo "  restart         Restart all services"
    echo "  status          Show service status"
    echo "  logs [service]  Show logs (optionally for specific service)"
    echo "  cleanup         Remove all containers and volumes"
    echo "  scale <service> <replicas>  Scale a service"
    echo "  resources       Show resource usage"
    echo "  backup          Backup volumes"
    echo "  restore <dir>   Restore volumes from backup"
    echo "  help            Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 start"
    echo "  $0 logs smart-deploy-monitor"
    echo "  $0 scale smart-deploy-monitor 3"
    echo "  $0 backup"
}

# Main script logic
case "${1:-help}" in
    start)
        check_docker
        start_services
        ;;
    stop)
        check_docker
        stop_services
        ;;
    restart)
        check_docker
        restart_services
        ;;
    status)
        check_docker
        show_status
        ;;
    logs)
        check_docker
        show_logs "$2"
        ;;
    cleanup)
        check_docker
        cleanup
        ;;
    scale)
        check_docker
        scale_service "$2" "$3"
        ;;
    resources)
        check_docker
        show_resources
        ;;
    backup)
        check_docker
        backup_volumes
        ;;
    restore)
        check_docker
        restore_volumes "$2"
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
