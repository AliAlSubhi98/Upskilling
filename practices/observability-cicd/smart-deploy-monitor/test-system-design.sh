#!/bin/bash

echo "🧪 Testing System Design Implementation"
echo "======================================"

# Start the application in background
echo "Starting application..."
java -jar target/smart-deploy-monitor-1.0.0.jar &
APP_PID=$!

# Wait for application to start
echo "Waiting for application to start..."
sleep 20

# Test Client-Server Architecture (Step 1)
echo ""
echo "📡 Testing Client-Server Architecture:"
echo "--------------------------------------"

echo "1. Testing basic health check..."
curl -s http://localhost:8080/api/health/ping | jq '.' || echo "❌ Basic health check failed"

echo ""
echo "2. Testing detailed health check..."
curl -s http://localhost:8080/api/health/detailed | jq '.' || echo "❌ Detailed health check failed"

echo ""
echo "3. Testing readiness check..."
curl -s http://localhost:8080/api/health/ready | jq '.' || echo "❌ Readiness check failed"

echo ""
echo "4. Testing liveness check..."
curl -s http://localhost:8080/api/health/live | jq '.' || echo "❌ Liveness check failed"

# Test Monolith vs Microservices (Step 2)
echo ""
echo "🏗️  Testing Monolith vs Microservices:"
echo "--------------------------------------"

echo "1. Testing API Gateway - Get all users..."
curl -s http://localhost:8080/api/gateway/users | jq '.' || echo "❌ API Gateway users failed"

echo ""
echo "2. Testing API Gateway - Send email notification..."
curl -s -X POST http://localhost:8080/api/gateway/notifications/email \
  -H "Content-Type: application/json" \
  -d '{"recipient":"test@example.com","subject":"Test Email","body":"This is a test email"}' | jq '.' || echo "❌ Email notification failed"

echo ""
echo "3. Testing API Gateway - Send SMS notification..."
curl -s -X POST http://localhost:8080/api/gateway/notifications/sms \
  -H "Content-Type: application/json" \
  -d '{"phoneNumber":"+1234567890","message":"Test SMS"}' | jq '.' || echo "❌ SMS notification failed"

echo ""
echo "✅ System Design Testing Complete!"
echo "=================================="

# Stop the application
echo "Stopping application..."
kill $APP_PID 2>/dev/null || true
wait $APP_PID 2>/dev/null || true

echo "Test completed!"
