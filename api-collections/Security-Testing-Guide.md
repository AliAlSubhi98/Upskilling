# Security Level 1 Testing Guide for Postman

## 📋 **Step-by-Step Testing Instructions**

### **Step 1: Import Collections**
1. **Open Postman**
2. **Click "Import"** button (top left)
3. **Select these files:**
   - `Security-Testing-Collection.postman_collection.json`
   - `REST-API-Collection.postman_collection.json`
   - `GraphQL-API-Collection.postman_collection.json`
   - `Smart-Deploy-Monitor-Environment.postman_environment.json`

### **Step 2: Set Environment**
1. **Select Environment**: Click the environment dropdown (top right)
2. **Choose**: "Smart Deploy Monitor Environment"
3. **Verify**: `baseUrl` is set to `http://localhost:8080`

### **Step 3: Start Application & Redis**
```bash
# Terminal 1: Start Redis
docker run -d --name redis -p 6379:6379 redis:alpine

# Terminal 2: Start Application
cd /Users/alialsubhi/Desktop/Upskilling/practices/observability-cicd/smart-deploy-monitor
mvn spring-boot:run
```

## 🧪 **Security Testing Workflow**

### **1. Health Check**
- **Request**: `GET /actuator/health`
- **Expected**: 200 OK
- **Check**: Application is running

### **2. Security Headers Test**
- **Request**: `GET /api/v1/users?page=0&size=5`
- **Expected**: 200 OK
- **Check Headers**:
  - `X-Content-Type-Options: nosniff`
  - `X-Frame-Options: DENY`
  - `X-XSS-Protection: 1; mode=block`
  - `Strict-Transport-Security: max-age=31536000`
  - `Content-Security-Policy: default-src 'self'`

### **3. SQL Injection Tests**
- **Request**: `GET /api/v1/users?search=1' OR '1'='1`
- **Expected**: 400 Bad Request (blocked)
- **Check**: Input validation working

- **Request**: `GET /api/v1/users?search=1' UNION SELECT * FROM users`
- **Expected**: 400 Bad Request (blocked)
- **Check**: Advanced SQL injection protection

### **4. XSS Protection Tests**
- **Request**: `GET /api/v1/users?search=<script>alert('xss')</script>`
- **Expected**: 400 Bad Request (blocked)
- **Check**: XSS protection working

- **Request**: `GET /api/v1/users?search=javascript:alert('xss')`
- **Expected**: 400 Bad Request (blocked)
- **Check**: JavaScript URL protection

### **5. Path Traversal Test**
- **Request**: `GET /api/v1/users/../../../etc/passwd`
- **Expected**: 400 Bad Request (blocked)
- **Check**: Path traversal protection

### **6. Rate Limiting Test**
- **Request**: `GET /api/v1/users?page=0&size=1` (run 10 times quickly)
- **Expected**: First 9 requests: 200 OK, 10th request: 429 Too Many Requests
- **Check**: Rate limiting working (100 requests/minute)

### **7. GraphQL Security Test**
- **Request**: `POST /graphql`
- **Body**: `{"query": "query { users { edges { node { id firstName lastName } } totalCount } }"}`
- **Expected**: 200 OK with security headers
- **Check**: GraphQL endpoint secured

### **8. Malicious User Agent Test**
- **Request**: `GET /api/v1/users` with `User-Agent: <script>alert('xss')</script>`
- **Expected**: 200 OK (request processed, but XSS blocked)
- **Check**: User-Agent validation

## **Expected Results**

### **Security Headers (All Requests)**
```
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
Strict-Transport-Security: max-age=31536000; includeSubDomains; preload
Content-Security-Policy: default-src 'self'; script-src 'self' 'unsafe-inline'...
Referrer-Policy: strict-origin-when-cross-origin
Permissions-Policy: geolocation=(), microphone=(), camera=()...
```

### **Input Validation (Attack Requests)**
```
HTTP 400 Bad Request
{
  "error": "Invalid input detected"
}
```

### **Rate Limiting (Excessive Requests)**
```
HTTP 429 Too Many Requests
{
  "error": "Rate limit exceeded. Try again later."
}
```

### **Normal Requests**
```
HTTP 200 OK
{
  "content": [...],
  "pageable": {...},
  "totalElements": 10
}
```

## 🔍 **Advanced Testing**

### **1. Load Testing**
- **Run**: Rate Limiting Test 20 times
- **Expected**: First 100 requests succeed, then 429 errors
- **Check**: Redis-based distributed rate limiting

### **2. Security Headers on All Endpoints**
- **Test**: `/api/v1/users`, `/graphql`, `/swagger-ui`, `/actuator/health`
- **Expected**: All have security headers
- **Check**: Consistent security across all endpoints

### **3. GraphQL vs REST Security**
- **Compare**: GraphQL and REST responses
- **Expected**: Same security headers on both
- **Check**: Unified security implementation

## 🚨 **Troubleshooting**

### **Application Not Running**
```bash
# Check if application is running
curl http://localhost:8080/actuator/health

# If not running, start it
cd /Users/alialsubhi/Desktop/Upskilling/practices/observability-cicd/smart-deploy-monitor
mvn spring-boot:run
```

### **Redis Not Running**
```bash
# Check Redis status
docker exec redis redis-cli ping

# If not running, start it
docker run -d --name redis -p 6379:6379 redis:alpine
```

### **Rate Limiting Not Working**
- **Check**: Redis is running and accessible
- **Check**: Application logs for Redis connection errors
- **Expected**: No "Redis rate limiting failed" messages

### **Input Validation Not Working**
- **Check**: Application logs for security events
- **Expected**: "SECURITY_ALERT" log entries
- **Check**: InputValidationFilter is active

## 📈 **Performance Testing**

### **1. Rate Limiting Performance**
- **Test**: 100 rapid requests
- **Expected**: First 100 succeed, then 429 errors
- **Check**: Redis performance under load

### **2. Security Filter Performance**
- **Test**: Malicious requests with security filters
- **Expected**: Fast rejection (low latency)
- **Check**: Security doesn't impact performance

## **Success Criteria**

### **  All Tests Pass When:**
1. **Security Headers**: Present on all responses
2. **Input Validation**: Blocks SQL injection, XSS, path traversal
3. **Rate Limiting**: Enforces 100 requests/minute limit
4. **Security Logging**: Attack attempts logged
5. **Normal Operation**: Legitimate requests work normally

### **  Security Level 1 Complete When:**
- All security headers implemented
- Input validation working
- Rate limiting functional
- Security monitoring active
- No false positives on legitimate requests

## 🔗 **Useful Links**

- **GraphQL Playground**: http://localhost:8080/graphiql
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **Health Check**: http://localhost:8080/actuator/health
- **REST API**: http://localhost:8080/api/v1/users

---

**Happy Security Testing!**
