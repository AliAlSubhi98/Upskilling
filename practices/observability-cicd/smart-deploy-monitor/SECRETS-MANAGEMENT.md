# Secrets Management Guide

This document outlines the secrets management strategy for the Smart Deploy Monitor application, following Security Level 2 best practices.

## Overview

Secrets management is critical for protecting sensitive information such as database credentials, API keys, JWT secrets, and other sensitive configuration data. This application implements a multi-layered approach to secrets management.

## Current Implementation

### Environment Variables

The application uses environment variables for secrets management, which is suitable for development and small-scale deployments.

**Configuration Files:**
- `application.yml` - Base configuration (no secrets)
- `application-docker.yml` - Docker-specific configuration
- Environment variables override configuration values

**Example:**
```yaml
# application.yml (no secrets)
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/smartdeploy}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:}
```

**Environment Variables:**
```bash
export DB_URL=jdbc:postgresql://localhost:5432/smartdeploy
export DB_USERNAME=postgres
export DB_PASSWORD=secure_password_here
export JWT_SECRET=your_jwt_secret_key_here
export REDIS_PASSWORD=your_redis_password_here
```

## Secrets Management Best Practices

### DO:
- Store secrets in environment variables
- Use `.env` files for local development (add to `.gitignore`)
- Rotate secrets regularly
- Use different secrets for different environments
- Never commit secrets to version control
- Use strong, randomly generated secrets
- Limit access to secrets (principle of least privilege)

### DON'T:
- Hardcode secrets in source code
- Commit secrets to Git repositories
- Share secrets via email or chat
- Use the same secrets across environments
- Store secrets in configuration files that are committed to Git
- Log secrets in application logs

## Production Secrets Management Options

### Option 1: HashiCorp Vault (Recommended for Production)

Vault provides secure, centralized secrets management with features like:
- Dynamic secrets generation
- Secrets rotation
- Audit logging
- Access control policies

**Integration Example:**
```java
@Configuration
public class VaultConfig {
    @Value("${vault.uri}")
    private String vaultUri;
    
    @Value("${vault.token}")
    private String vaultToken;
    
    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint endpoint = VaultEndpoint.from(URI.create(vaultUri));
        ClientAuthentication clientAuth = new TokenAuthentication(vaultToken);
        VaultTemplate vaultTemplate = new VaultTemplate(endpoint, clientAuth);
        return vaultTemplate;
    }
}
```

**Usage:**
```java
@Service
public class SecretsService {
    @Autowired
    private VaultTemplate vaultTemplate;
    
    public String getDatabasePassword() {
        VaultResponseSupport<Map<String, Object>> response = 
            vaultTemplate.read("secret/data/database");
        return (String) response.getData().get("password");
    }
}
```

### Option 2: AWS Secrets Manager / Parameter Store

For AWS deployments, use AWS Secrets Manager or Systems Manager Parameter Store:

**Maven Dependency:**
```xml
<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-java-sdk-secretsmanager</artifactId>
    <version>1.12.x</version>
</dependency>
```

**Usage:**
```java
@Service
public class AwsSecretsService {
    private final AWSSecretsManager client = AWSSecretsManagerClientBuilder.defaultClient();
    
    public String getSecret(String secretName) {
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId(secretName);
        GetSecretValueResult result = client.getSecretValue(request);
        return result.getSecretString();
    }
}
```

### Option 3: Kubernetes Secrets

For Kubernetes deployments, use Kubernetes Secrets:

**Create Secret:**
```bash
kubectl create secret generic app-secrets \
  --from-literal=db-password=secure_password \
  --from-literal=jwt-secret=jwt_secret_key
```

**Mount in Deployment:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: smart-deploy-monitor
spec:
  template:
    spec:
      containers:
      - name: app
        env:
        - name: DB_PASSWORD
          valueFrom:
            secretKeyRef:
              name: app-secrets
              key: db-password
```

## Local Development Setup

### Using .env File

1. Create a `.env` file in the project root:
```bash
DB_URL=jdbc:postgresql://localhost:5432/smartdeploy
DB_USERNAME=postgres
DB_PASSWORD=local_dev_password
JWT_SECRET=local_jwt_secret_key_change_in_production
REDIS_PASSWORD=
```

2. Add to `.gitignore`:
```
.env
.env.local
.env.*.local
```

3. Load environment variables:
```bash
# Linux/Mac
export $(cat .env | xargs)

# Windows PowerShell
Get-Content .env | ForEach-Object {
    $name, $value = $_.split('=')
    Set-Content env:\$name $value
}
```

## Docker Secrets Management

### Using Docker Secrets (Docker Swarm)

```yaml
version: '3.8'
services:
  app:
    image: smart-deploy-monitor:latest
    secrets:
      - db_password
      - jwt_secret
    environment:
      DB_PASSWORD_FILE: /run/secrets/db_password
      JWT_SECRET_FILE: /run/secrets/jwt_secret

secrets:
  db_password:
    external: true
  jwt_secret:
    external: true
```

### Using Docker Compose with .env

```yaml
services:
  app:
    environment:
      DB_PASSWORD: ${DB_PASSWORD}
      JWT_SECRET: ${JWT_SECRET}
```

## Secrets Rotation

### Manual Rotation Process

1. Generate new secret
2. Update secret in secrets management system
3. Update application configuration
4. Restart application
5. Verify application functionality
6. Remove old secret

### Automated Rotation (Vault)

Vault can automatically rotate secrets:
```bash
vault write database/rotate-root/my-database
```

## Security Checklist

- [x] No secrets in source code
- [x] Environment variables used for secrets
- [x] `.env` file in `.gitignore`
- [x] Different secrets for each environment
- [x] Secrets rotation policy defined
- [ ] Vault integration (for production)
- [ ] Secrets audit logging enabled
- [ ] Access control policies defined

## Next Steps for Level 3

For Security Level 3, implement:
- Centralized secrets management (Vault or cloud provider)
- Automated secrets rotation
- Secrets audit logging
- Integration with CI/CD pipelines
- Secrets scanning in CI/CD

## Resources

- [HashiCorp Vault Documentation](https://www.vaultproject.io/docs)
- [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/)
- [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/)
- [OWASP Secrets Management](https://cheatsheetseries.owasp.org/cheatsheets/Secrets_Management_Cheat_Sheet.html)

