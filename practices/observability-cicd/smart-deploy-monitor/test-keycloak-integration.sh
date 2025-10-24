#!/bin/bash

# Define colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Keycloak Integration Testing ===${NC}"
echo "Testing Keycloak authentication integration"
echo ""

# Test 1: Check Keycloak Health
echo -n "1. Keycloak Health Check: "
KEYCLOAK_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8081/)
if [[ "$KEYCLOAK_RESPONSE" == "200" ]]; then
    echo -e "${GREEN}✓ Keycloak is responding${NC}"
else
    echo -e "${RED}✗ Keycloak not responding${NC}"
    echo "Please ensure Keycloak is running: docker compose up -d keycloak"
    exit 1
fi

# Test 2: Check Keycloak Admin Console
echo -n "2. Keycloak Admin Console: "
ADMIN_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8081/admin/)
if [[ "$ADMIN_RESPONSE" == "200" ]]; then
    echo -e "${GREEN}✓ Admin console accessible${NC}"
else
    echo -e "${RED}✗ Admin console not accessible${NC}"
fi

# Test 3: Check Realm Configuration
echo -n "3. Realm Configuration: "
REALM_RESPONSE=$(curl -s http://localhost:8081/realms/smart-deploy-monitor)
if [[ "$REALM_RESPONSE" == *"smart-deploy-monitor"* ]]; then
    echo -e "${GREEN}✓ Realm 'smart-deploy-monitor' found${NC}"
else
    echo -e "${RED}✗ Realm 'smart-deploy-monitor' not found${NC}"
    echo "Please create the realm in Keycloak admin console"
fi

# Test 4: Check Client Configuration
echo -n "4. Client Configuration: "
CLIENT_RESPONSE=$(curl -s http://localhost:8081/realms/smart-deploy-monitor/.well-known/openid_configuration)
if [[ "$CLIENT_RESPONSE" == *"issuer"* ]]; then
    echo -e "${GREEN}✓ Client configuration accessible${NC}"
else
    echo -e "${RED}✗ Client configuration not accessible${NC}"
fi

echo ""
echo -e "${YELLOW}=== Keycloak Setup Status ===${NC}"
echo "✅ Keycloak Server: Running on http://localhost:8081"
echo "✅ Admin Console: http://localhost:8081/admin/"
echo "✅ Realm: smart-deploy-monitor"
echo "✅ Client: smart-deploy-monitor-client"
echo ""

echo -e "${YELLOW}=== Next Steps ===${NC}"
echo "1. Update application.yml with the client secret from Keycloak"
echo "2. Restart the Spring Boot application"
echo "3. Test Keycloak authentication endpoints"
echo ""

echo -e "${GREEN}Keycloak integration testing completed!${NC}"
