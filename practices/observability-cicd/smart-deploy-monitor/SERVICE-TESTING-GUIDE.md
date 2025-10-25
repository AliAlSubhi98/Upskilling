# Database Services Testing Guide

## 🖥️ **Service UIs and Testing Methods**

### 1. **PostgreSQL** - Spring Boot API + Swagger UI
**  Has UI: Swagger UI**
- **URL:** http://localhost:8080/swagger-ui/index.html
- **Features:** Interactive API documentation with "Try it out" buttons
- **Test Endpoints:**
  - `GET /api/users` - List all users
  - `GET /api/users/statistics` - Database statistics
  - `POST /api/database/demo/level1` - Level 1 demo

**Manual Testing:**
```bash
# View all users
curl http://localhost:8080/api/users

# Get database statistics  
curl http://localhost:8080/api/database/stats

# Level 1 demo
curl -X POST http://localhost:8080/api/database/demo/level1
```

### 2. **Redis** - Command Line Interface
**❌ No UI, but easy CLI testing**
```bash
# Connect to Redis CLI
docker exec smart-deploy-redis redis-cli

# Test commands inside Redis CLI:
ping                    # Should return PONG
set test:key "Hello"    # Set a key
get test:key            # Get the key
setex temp:key 10 "expires"  # Set with TTL
ttl temp:key            # Check TTL
keys *                  # List all keys
```

**Or test from outside:**
```bash
docker exec smart-deploy-redis redis-cli ping
docker exec smart-deploy-redis redis-cli set "test:key" "Hello Redis" EX 60
docker exec smart-deploy-redis redis-cli get "test:key"
```

### 3. **Elasticsearch** - REST API + Dev Tools
**  Has UI: Dev Tools**
- **URL:** http://localhost:9200/_cat/indices
- **Search UI:** http://localhost:9200/_search?pretty
- **Cluster Health:** http://localhost:9200/_cluster/health

**Manual Testing:**
```bash
# Check health
curl http://localhost:9200/_cluster/health

# List indices
curl http://localhost:9200/_cat/indices

# Create index
curl -X PUT "localhost:9200/my-index" -H 'Content-Type: application/json' -d '{
  "mappings": {
    "properties": {
      "name": {"type": "text"},
      "age": {"type": "integer"}
    }
  }
}'

# Add document
curl -X POST "localhost:9200/my-index/_doc/1" -H 'Content-Type: application/json' -d '{
  "name": "John Doe",
  "age": 30
}'

# Search documents
curl -X GET "localhost:9200/my-index/_search?pretty"

# Search with query
curl -X GET "localhost:9200/my-index/_search?pretty" -H 'Content-Type: application/json' -d '{
  "query": {
    "match": {
      "name": "John"
    }
  }
}'
```

### 4. **Qdrant** - REST API + Web UI
**  Has UI: Web Interface**
- **URL:** http://localhost:6333/dashboard
- **Collections:** http://localhost:6333/collections
- **Health:** http://localhost:6333/health

**Manual Testing:**
```bash
# Check health
curl http://localhost:6333/health

# List collections
curl http://localhost:6333/collections

# Create collection
curl -X PUT "http://localhost:6333/collections/my-collection" -H 'Content-Type: application/json' -d '{
  "vectors": {
    "size": 128,
    "distance": "Cosine"
  }
}'

# Insert vector
curl -X PUT "http://localhost:6333/collections/my-collection/points" -H 'Content-Type: application/json' -d '{
  "points": [
    {
      "id": "point-1",
      "vector": [0.1, 0.2, 0.3, 0.4, 0.5],
      "payload": {"name": "test"}
    }
  ]
}'

# Search vectors
curl -X POST "http://localhost:6333/collections/my-collection/points/search" -H 'Content-Type: application/json' -d '{
  "vector": [0.1, 0.2, 0.3, 0.4, 0.5],
  "limit": 5
}'
```

## 🚀 **Quick Testing Commands**

### Test All Services at Once:
```bash
# Run the comprehensive test
./test-level1-comprehensive.sh
```

### Individual Service Tests:
```bash
# PostgreSQL (via our API)
curl http://localhost:8080/api/database/stats

# Redis
docker exec smart-deploy-redis redis-cli ping

# Elasticsearch
curl http://localhost:9200/_cluster/health

# Qdrant
curl http://localhost:6333/collections
```

## 🎯 **Level 1 Database Operations Summary**

| Service | UI Available | Testing Method | Status |
|---------|-------------|----------------|---------|
| **PostgreSQL** |   Swagger UI | REST API |   Working |
| **Redis** | ❌ CLI Only | Command Line |   Working |
| **Elasticsearch** |   Dev Tools | REST API |   Working |
| **Qdrant** |   Web UI | REST API |   Working |

## 🎉 **All Level 1 Database Fundamentals: COMPLETE!**

- **PostgreSQL**: CRUD operations with JPA entities
- **Redis**: TTL-based caching operations  
- **Elasticsearch**: Search and filter operations
- **Qdrant**: Vector insert and search operations

**All services are running and fully functional!** 🚀