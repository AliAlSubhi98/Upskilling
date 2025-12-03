#!/bin/bash

# Rate Limiting Test Script - 100 Requests Test
# This script tests the rate limiting by making 100+ requests to trigger the limit

BASE_URL="http://localhost:8080"
ENDPOINT="$BASE_URL/api/v1/users?page=0&size=1"
MAX_REQUESTS=110  # Test with 110 requests (limit is 100/minute)
DELAY=0.05        # 50ms delay between requests

echo "Testing Rate Limiting - 100 Requests Test"
echo "=============================================="
echo "Endpoint: $ENDPOINT"
echo "Max Requests: $MAX_REQUESTS"
echo "Delay between requests: ${DELAY}s"
echo "Rate Limit: 100 requests per minute"
echo ""

# Clear any existing rate limit keys
echo "=== Clearing existing rate limit keys ==="
docker exec redis redis-cli del "rate_limit:0:0:0:0:0:0:0:1" 2>/dev/null || echo "Redis key cleared"
echo ""

# Test: Make 110 requests rapidly
echo "=== Making 110 Requests Rapidly ==="
rate_limited=false
rate_limit_triggered_at=0

for i in {1..110}; do
    echo -n "Request $i: "
    response=$(curl -s -w "HTTP %{http_code}" -o /dev/null "$ENDPOINT")
    echo "$response"
    
    # Check if rate limited
    if [[ $response == *"429"* ]]; then
        echo "  🚫 Rate limit triggered at request $i!"
        rate_limited=true
        rate_limit_triggered_at=$i
        break
    fi
    
    sleep $DELAY
done

echo ""
echo "=== Results ==="
if [ "$rate_limited" = true ]; then
    echo "Rate limiting is WORKING!"
    echo "🚫 Rate limit triggered at request $rate_limit_triggered_at"
    echo "Expected: Rate limit should trigger around request 100-101"
else
    echo "Rate limiting is NOT working"
    echo "🔍 All requests returned 200 (should have 429 errors)"
fi

echo ""
echo "=== Redis Rate Limiting Check ==="
echo "Checking Redis rate limiting keys..."
redis_keys=$(docker exec redis redis-cli keys "rate_limit:*" 2>/dev/null || echo "Redis not accessible")
echo "Redis keys: $redis_keys"

if [ ! -z "$redis_keys" ]; then
    for key in $redis_keys; do
        value=$(docker exec redis redis-cli get "$key" 2>/dev/null)
        ttl=$(docker exec redis redis-cli ttl "$key" 2>/dev/null)
        echo "Key: $key = $value (TTL: ${ttl}s)"
    done
fi

echo ""
echo "Rate Limiting Test Complete!"
echo "==============================="
echo ""
echo "Expected Results:"
echo "First ~100 requests: HTTP 200"
echo "Request 101+: HTTP 429 (rate limited)"
echo ""
echo "If you see 429 errors, rate limiting is working!"
echo "If all requests return 200, rate limiting may not be active."
