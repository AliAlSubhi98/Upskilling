#!/bin/bash

# Rate Limiting Test Script for Smart Deploy Monitor
# This script tests the rate limiting functionality by making rapid requests

BASE_URL="http://localhost:8080"
ENDPOINT="$BASE_URL/api/v1/users?page=0&size=1"
MAX_REQUESTS=15  # Test with 15 requests (limit is 100/minute)
DELAY=0.1        # 100ms delay between requests

echo "🚀 Testing Rate Limiting for Smart Deploy Monitor"
echo "=================================================="
echo "Endpoint: $ENDPOINT"
echo "Max Requests: $MAX_REQUESTS"
echo "Delay between requests: ${DELAY}s"
echo ""

# Test 1: Normal requests (should succeed)
echo "=== Test 1: Normal Requests (Should Succeed) ==="
for i in {1..5}; do
    echo "Request $i:"
    response=$(curl -s -w "HTTP %{http_code}" -o /dev/null "$ENDPOINT")
    echo "  $response"
    sleep $DELAY
done
echo ""

# Test 2: Rapid requests (should trigger rate limiting)
echo "=== Test 2: Rapid Requests (Should Trigger Rate Limiting) ==="
for i in {1..15}; do
    echo "Request $i:"
    response=$(curl -s -w "HTTP %{http_code}" -o /dev/null "$ENDPOINT")
    echo "  $response"
    
    # Check if rate limited
    if [[ $response == *"429"* ]]; then
        echo "  🚫 Rate limit triggered at request $i!"
        break
    fi
    
    sleep $DELAY
done
echo ""

# Test 3: Wait and retry (should work again)
echo "=== Test 3: Wait and Retry (Should Work Again) ==="
echo "Waiting 5 seconds..."
sleep 5

echo "Retry request:"
response=$(curl -s -w "HTTP %{http_code}" -o /dev/null "$ENDPOINT")
echo "  $response"
echo ""

# Test 4: Check Redis rate limiting
echo "=== Test 4: Redis Rate Limiting Check ==="
echo "Checking Redis rate limiting keys..."
redis_response=$(docker exec redis redis-cli keys "rate_limit:*" 2>/dev/null || echo "Redis not accessible")
echo "Redis keys: $redis_response"
echo ""

echo "🎯 Rate Limiting Test Complete!"
echo "==============================="
echo ""
echo "Expected Results:"
echo "✅ First 5 requests: HTTP 200"
echo "✅ Rapid requests: HTTP 429 (rate limited)"
echo "✅ After wait: HTTP 200 (rate limit reset)"
echo ""
echo "If you see 429 errors, rate limiting is working!"
echo "If all requests return 200, rate limiting may not be active."
