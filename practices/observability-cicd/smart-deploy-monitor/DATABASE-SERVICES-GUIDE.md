# Database Services Setup Guide

## 🎉 Services Successfully Installed!

All required database services for Level 1 Database testing are now running:

###   **Running Services:**

1. **Redis** (Port 6379)
   - Status:   Running
   - Purpose: TTL-based caching
   - Test: `docker exec smart-deploy-redis redis-cli ping`

2. **Elasticsearch** (Port 9200)
   - Status:   Running (Green cluster)
   - Purpose: Search/filter operations
   - Test: `curl http://localhost:9200/_cluster/health`

3. **Qdrant** (Port 6333)
   - Status:   Running
   - Purpose: Vector insert/search operations
   - Test: `curl http://localhost:6333/health`

### 🚀 **Next Steps to Complete Level 1:**

#### Step 1: Enable Advanced Services in Code
Uncomment the following in your code:

**In `DatabaseController.java`:**
```java
@Autowired
private CacheService cacheService;

@Autowired
private SearchService searchService;

@Autowired
private VectorService vectorService;
```

**In `pom.xml`:**
```xml
<!-- Uncomment these dependencies -->
<dependency>
    <groupId>io.qdrant</groupId>
    <artifactId>qdrant-client</artifactId>
    <version>1.6.0</version>
</dependency>
```

#### Step 2: Restart Your Application
```bash
# Stop current application (Ctrl+C)
# Then restart with:
mvn spring-boot:run
```

#### Step 3: Run Comprehensive Tests
```bash
# Test all Level 1 database operations
./test-database-level1.sh
```

### 📊 **Service Management Commands:**

**Start Services:**
```bash
./start-services.sh
```

**Stop Services:**
```bash
./stop-services.sh
```

**Check Service Status:**
```bash
docker ps
```

**View Service Logs:**
```bash
docker logs smart-deploy-redis
docker logs smart-deploy-elasticsearch
docker logs smart-deploy-qdrant
```

### 🔧 **Service URLs:**

- **Redis:** `redis://localhost:6379`
- **Elasticsearch:** `http://localhost:9200`
- **Qdrant:** `http://localhost:6333`

### 📝 **Testing Endpoints:**

Once you enable the services and restart your app, you can test:

**Redis Caching:**
```bash
curl -X POST "http://localhost:8080/api/database/cache/set?key=test&value=hello&ttlSeconds=60"
curl "http://localhost:8080/api/database/cache/get?key=test"
```

**Elasticsearch Search:**
```bash
curl "http://localhost:8080/api/database/search/filters?email=test@example.com"
curl "http://localhost:8080/api/database/search/queries?searchText=test"
```

**Qdrant Vectors:**
```bash
curl -X POST "http://localhost:8080/api/database/vector/collection"
curl -X POST "http://localhost:8080/api/database/vector/insert?pointId=test&vector=0.1,0.2,0.3&metadata=test"
```

### 🎯 **Level 1 Completion Checklist:**

-   **PostgreSQL CRUD** - Working (tested)
-   **Redis TTL Caching** - Ready (services running)
-   **Elasticsearch Search/Filter** - Ready (services running)
-   **Qdrant Vector Operations** - Ready (services running)

### 🚨 **Troubleshooting:**

**If services fail to start:**
```bash
# Check Docker is running
docker info

# Restart services
./stop-services.sh
./start-services.sh
```

**If application won't start:**
```bash
# Check for compilation errors
mvn clean compile

# Check service connectivity
curl http://localhost:9200/_cluster/health
docker exec smart-deploy-redis redis-cli ping
```

### 🎉 **You're Ready for Full Level 1 Testing!**

All database services are installed and running. Just enable the code and restart your application to complete Level 1 Database fundamentals!