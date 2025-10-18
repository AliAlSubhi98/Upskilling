#!/bin/bash

# Start Database Services for Level 1 Testing
# Redis, Elasticsearch, and Qdrant

set -e

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Starting Database Services for Level 1 Testing ===${NC}"
echo "Starting Redis, Elasticsearch, and Qdrant services..."
echo ""

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo -e "${RED}Error: Docker is not running. Please start Docker Desktop first.${NC}"
    exit 1
fi

echo -e "${YELLOW}Starting services with Docker Compose...${NC}"
docker-compose -f docker-services.yml up -d

echo ""
echo -e "${YELLOW}Waiting for services to be ready...${NC}"

# Wait for Redis
echo -n "Waiting for Redis... "
timeout=60
while [ $timeout -gt 0 ]; do
    if docker exec smart-deploy-redis redis-cli ping > /dev/null 2>&1; then
        echo -e "${GREEN}✓ Ready${NC}"
        break
    fi
    sleep 2
    timeout=$((timeout - 2))
done

if [ $timeout -le 0 ]; then
    echo -e "${RED}✗ Timeout${NC}"
fi

# Wait for Elasticsearch
echo -n "Waiting for Elasticsearch... "
timeout=60
while [ $timeout -gt 0 ]; do
    if curl -s http://localhost:9200/_cluster/health > /dev/null 2>&1; then
        echo -e "${GREEN}✓ Ready${NC}"
        break
    fi
    sleep 2
    timeout=$((timeout - 2))
done

if [ $timeout -le 0 ]; then
    echo -e "${RED}✗ Timeout${NC}"
fi

# Wait for Qdrant
echo -n "Waiting for Qdrant... "
timeout=60
while [ $timeout -gt 0 ]; do
    if curl -s http://localhost:6333/health > /dev/null 2>&1; then
        echo -e "${GREEN}✓ Ready${NC}"
        break
    fi
    sleep 2
    timeout=$((timeout - 2))
done

if [ $timeout -le 0 ]; then
    echo -e "${RED}✗ Timeout${NC}"
fi

echo ""
echo -e "${GREEN}🎉 All services are ready!${NC}"
echo ""
echo -e "${YELLOW}Service URLs:${NC}"
echo "• Redis: http://localhost:6379"
echo "• Elasticsearch: http://localhost:9200"
echo "• Qdrant: http://localhost:6333"
echo ""
echo -e "${YELLOW}Next steps:${NC}"
echo "1. Uncomment the advanced services in your code"
echo "2. Restart your Spring Boot application"
echo "3. Run the comprehensive Level 1 tests"
echo ""
echo -e "${YELLOW}To stop services:${NC}"
echo "docker-compose -f docker-services.yml down"