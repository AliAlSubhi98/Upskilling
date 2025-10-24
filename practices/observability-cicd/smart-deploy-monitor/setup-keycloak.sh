#!/bin/bash

echo "=== Keycloak Setup Script ==="
echo "Setting up Keycloak for Smart Deploy Monitor"
echo ""

# Wait for Keycloak to be ready
echo "Waiting for Keycloak to be ready..."
for i in {1..60}; do
  if curl -s http://localhost:8081/health/ready > /dev/null 2>&1; then
    echo "✓ Keycloak is ready!"
    break
  fi
  echo -n "."
  sleep 5
done

echo ""
echo "Keycloak Admin Console: http://localhost:8081"
echo "Username: admin"
echo "Password: admin123"
echo ""

echo "=== Manual Setup Required ==="
echo "1. Go to http://localhost:8081"
echo "2. Login with admin/admin123"
echo "3. Create a new realm called 'smart-deploy-monitor'"
echo "4. Create a client called 'smart-deploy-monitor-client'"
echo "5. Set client protocol to 'openid-connect'"
echo "6. Set access type to 'confidential'"
echo "7. Enable 'Service accounts roles'"
echo "8. Save and copy the client secret"
echo "9. Update application.yml with the client secret"
echo ""

echo "=== Next Steps ==="
echo "1. Update application.yml with the client secret"
echo "2. Restart the Spring Boot application"
echo "3. Test Keycloak authentication"
echo ""

echo "Setup script completed!"
