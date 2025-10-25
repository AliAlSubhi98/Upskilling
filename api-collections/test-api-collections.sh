#!/bin/bash

# Test API Collections - Smart Deploy Monitor
# This script demonstrates the API collections functionality

echo "🚀 Testing API Collections for Smart Deploy Monitor"
echo "=================================================="

BASE_URL="http://localhost:8080"
GRAPHQL_URL="$BASE_URL/graphql"
REST_URL="$BASE_URL/api/v1"

echo ""
echo "=== 1. Health Check ==="
curl -s "$BASE_URL/actuator/health" | jq '.' || echo "Health check failed"

echo ""
echo "=== 2. GraphQL Playground Check ==="
curl -s -I "$BASE_URL/graphiql" | head -1

echo ""
echo "=== 3. Swagger UI Check ==="
curl -s -I "$BASE_URL/swagger-ui/index.html" | head -1

echo ""
echo "=== 4. GraphQL - Get All Users (Mobile) ==="
curl -s -X POST "$GRAPHQL_URL" \
  -H "Content-Type: application/json" \
  -d '{"query": "query { users { edges { node { id firstName lastName } } totalCount } }"}' | jq '.'

echo ""
echo "=== 5. GraphQL - Get All Users (Admin) ==="
curl -s -X POST "$GRAPHQL_URL" \
  -H "Content-Type: application/json" \
  -d '{"query": "query { users { edges { node { id email firstName lastName role isActive createdAt } } pageInfo { hasNextPage } totalCount } }"}' | jq '.'

echo ""
echo "=== 6. REST API - Get Users ==="
curl -s "$REST_URL/users?page=0&size=5" | jq '.'

echo ""
echo "=== 7. REST API - Get Users with Sorting ==="
curl -s "$REST_URL/users?page=0&size=5&sortBy=firstName&sortDir=asc" | jq '.'

echo ""
echo "=== 8. GraphQL - Schema Introspection ==="
curl -s -X POST "$GRAPHQL_URL" \
  -H "Content-Type: application/json" \
  -d '{"query": "query { __schema { types { name } } }"}' | jq '.data.__schema.types[0:5]'

echo ""
echo "=== 9. Performance Test - Simple Query ==="
time curl -s -X POST "$GRAPHQL_URL" \
  -H "Content-Type: application/json" \
  -d '{"query": "query { users { totalCount } }"}' > /dev/null

echo ""
echo "=== 10. Create Test User via REST ==="
curl -s -X POST "$REST_URL/users" \
  -H "Content-Type: application/json" \
  -d '{"email": "collection-test@example.com", "password": "password123", "firstName": "Collection", "lastName": "Test", "role": "USER"}' | jq '.'

echo ""
echo "🎉 API Collections Test Complete!"
echo "================================="
echo ""
echo "📁 Collections Location: /Users/alialsubhi/Desktop/Upskilling/api-collections/"
echo "📋 Import these files into Postman:"
echo "   - GraphQL-API-Collection.postman_collection.json"
echo "   - REST-API-Collection.postman_collection.json"
echo "   - Smart-Deploy-Monitor-Environment.postman_environment.json"
echo ""
echo "🔗 Useful Links:"
echo "   - GraphQL Playground: $BASE_URL/graphiql"
echo "   - Swagger UI: $BASE_URL/swagger-ui/index.html"
echo "   - Health Check: $BASE_URL/actuator/health"
