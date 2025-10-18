#!/bin/bash

# Stop Database Services

set -e

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Stopping Database Services ===${NC}"
echo "Stopping Redis, Elasticsearch, and Qdrant services..."
echo ""

docker-compose -f docker-services.yml down

echo -e "${GREEN}✓ All services stopped${NC}"
echo ""
echo -e "${YELLOW}Note:${NC} Data volumes are preserved. To remove all data:"
echo "docker-compose -f docker-services.yml down -v"