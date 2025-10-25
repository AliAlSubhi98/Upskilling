# Architecture Diagrams

This document contains visual diagrams explaining the Smart Deploy Monitor system architecture, data flows, and component relationships.

## System Architecture Overview

```mermaid
graph TB
    subgraph "Client Layer"
        WEB[Web Application]
        MOBILE[Mobile App]
        API_CLIENT[API Client]
    end
    
    subgraph "API Gateway"
        GATEWAY[Load Balancer]
        AUTH[Authentication Service]
    end
    
    subgraph "Application Layer"
        USER_SERVICE[User Service]
        AUTH_SERVICE[Auth Service]
        MONITOR_SERVICE[Monitor Service]
        NOTIFICATION_SERVICE[Notification Service]
    end
    
    subgraph "Data Layer"
        POSTGRES[(PostgreSQL)]
        REDIS[(Redis Cache)]
        ELASTIC[(Elasticsearch)]
        QDRANT[(Qdrant Vector DB)]
    end
    
    subgraph "Infrastructure"
        DOCKER[Docker Containers]
        K8S[Kubernetes]
        MONITORING[Monitoring Stack]
    end
    
    WEB --> GATEWAY
    MOBILE --> GATEWAY
    API_CLIENT --> GATEWAY
    
    GATEWAY --> AUTH
    AUTH --> USER_SERVICE
    AUTH --> AUTH_SERVICE
    
    USER_SERVICE --> POSTGRES
    USER_SERVICE --> REDIS
    AUTH_SERVICE --> POSTGRES
    MONITOR_SERVICE --> ELASTIC
    NOTIFICATION_SERVICE --> QDRANT
    
    USER_SERVICE --> MONITORING
    AUTH_SERVICE --> MONITORING
    MONITOR_SERVICE --> MONITORING
    
    DOCKER --> K8S
    K8S --> MONITORING
```

## User Authentication Flow

```mermaid
sequenceDiagram
    participant U as User (أحمد)
    participant W as Web App
    participant A as Auth Service
    participant D as Database
    participant C as Cache
    
    U->>W: Login Request
    W->>A: Authenticate User
    A->>D: Check Credentials
    D-->>A: User Data
    A->>A: Validate Password
    A->>C: Store Session
    A-->>W: JWT Token
    W-->>U: Authentication Success
    
    Note over U,C: User أحمد successfully authenticated
```

## Database Integration Architecture

```mermaid
graph LR
    subgraph "Application"
        APP[Spring Boot App]
    end
    
    subgraph "Database Layer"
        POSTGRES[(PostgreSQL<br/>Primary Database)]
        REDIS[(Redis<br/>Cache & Sessions)]
        ELASTIC[(Elasticsearch<br/>Search & Analytics)]
        QDRANT[(Qdrant<br/>Vector Operations)]
    end
    
    subgraph "Operations"
        CRUD[CRUD Operations]
        CACHE[Cache Operations]
        SEARCH[Search Operations]
        VECTOR[Vector Operations]
    end
    
    APP --> CRUD
    APP --> CACHE
    APP --> SEARCH
    APP --> VECTOR
    
    CRUD --> POSTGRES
    CACHE --> REDIS
    SEARCH --> ELASTIC
    VECTOR --> QDRANT
    
    POSTGRES -.-> REDIS
    REDIS -.-> ELASTIC
    ELASTIC -.-> QDRANT
```

## API Request Flow

```mermaid
flowchart TD
    START([User Request]) --> LB{Load Balancer}
    LB --> AUTH{Authentication}
    AUTH -->|Valid| RATE{Rate Limiting}
    AUTH -->|Invalid| REJECT[401 Unauthorized]
    
    RATE -->|Within Limit| VALIDATE{Input Validation}
    RATE -->|Exceeded| THROTTLE[429 Too Many Requests]
    
    VALIDATE -->|Valid| PROCESS[Process Request]
    VALIDATE -->|Invalid| BAD[400 Bad Request]
    
    PROCESS --> DB[(Database)]
    PROCESS --> CACHE[(Cache)]
    PROCESS --> SEARCH[(Search)]
    
    DB --> RESPONSE[Response]
    CACHE --> RESPONSE
    SEARCH --> RESPONSE
    
    RESPONSE --> LOG[Log Request]
    LOG --> END([Response to User])
    
    REJECT --> END
    THROTTLE --> END
    BAD --> END
```

## Microservices Communication

```mermaid
graph TB
    subgraph "API Gateway"
        GATEWAY[API Gateway]
    end
    
    subgraph "Core Services"
        USER[User Service]
        AUTH[Auth Service]
        MONITOR[Monitor Service]
        NOTIFY[Notification Service]
    end
    
    subgraph "Supporting Services"
        CONFIG[Config Service]
        DISCOVERY[Service Discovery]
        LOGGING[Logging Service]
    end
    
    subgraph "External Services"
        KEYCLOAK[Keycloak]
        EMAIL[Email Service]
        SMS[SMS Service]
    end
    
    GATEWAY --> USER
    GATEWAY --> AUTH
    GATEWAY --> MONITOR
    GATEWAY --> NOTIFY
    
    USER --> AUTH
    USER --> CONFIG
    AUTH --> KEYCLOAK
    MONITOR --> LOGGING
    NOTIFY --> EMAIL
    NOTIFY --> SMS
    
    CONFIG --> DISCOVERY
    LOGGING --> DISCOVERY
```

## Data Flow for User Management

```mermaid
flowchart LR
    subgraph "User Operations"
        CREATE[Create User]
        READ[Read User]
        UPDATE[Update User]
        DELETE[Delete User]
    end
    
    subgraph "Data Processing"
        VALIDATE[Validate Input]
        ENCRYPT[Encrypt Password]
        AUDIT[Audit Log]
    end
    
    subgraph "Storage"
        POSTGRES[(PostgreSQL)]
        REDIS[(Redis Cache)]
        ELASTIC[(Elasticsearch)]
    end
    
    CREATE --> VALIDATE
    READ --> POSTGRES
    UPDATE --> VALIDATE
    DELETE --> AUDIT
    
    VALIDATE --> ENCRYPT
    ENCRYPT --> POSTGRES
    POSTGRES --> REDIS
    REDIS --> ELASTIC
    
    AUDIT --> POSTGRES
    POSTGRES --> ELASTIC
```

## Security Architecture

```mermaid
graph TB
    subgraph "Security Layers"
        WAF[Web Application Firewall]
        LB[Load Balancer]
        AUTH[Authentication]
        AUTHZ[Authorization]
        VALIDATE[Input Validation]
        ENCRYPT[Encryption]
    end
    
    subgraph "Security Services"
        RATE[Rate Limiting]
        HEADERS[Security Headers]
        LOGGING[Security Logging]
        MONITOR[Security Monitoring]
    end
    
    subgraph "Data Protection"
        PASSWORD[Password Hashing]
        TOKEN[JWT Tokens]
        SESSION[Session Management]
        CACHE[Secure Caching]
    end
    
    WAF --> LB
    LB --> AUTH
    AUTH --> AUTHZ
    AUTHZ --> VALIDATE
    VALIDATE --> ENCRYPT
    
    RATE --> HEADERS
    HEADERS --> LOGGING
    LOGGING --> MONITOR
    
    PASSWORD --> TOKEN
    TOKEN --> SESSION
    SESSION --> CACHE
```

## Deployment Architecture

```mermaid
graph TB
    subgraph "Development"
        DEV[Development Environment]
        TEST[Testing Environment]
    end
    
    subgraph "CI/CD Pipeline"
        BUILD[Build Process]
        TEST_AUTO[Automated Tests]
        SECURITY[Security Scans]
        DEPLOY[Deployment]
    end
    
    subgraph "Production"
        PROD[Production Environment]
        MONITOR[Monitoring]
        BACKUP[Backup Systems]
    end
    
    DEV --> BUILD
    TEST --> BUILD
    BUILD --> TEST_AUTO
    TEST_AUTO --> SECURITY
    SECURITY --> DEPLOY
    DEPLOY --> PROD
    
    PROD --> MONITOR
    PROD --> BACKUP
    MONITOR --> BACKUP
```

## Monitoring and Observability

```mermaid
graph LR
    subgraph "Application Metrics"
        APP[Application]
        HEALTH[Health Checks]
        METRICS[Custom Metrics]
    end
    
    subgraph "Infrastructure Metrics"
        CPU[CPU Usage]
        MEMORY[Memory Usage]
        DISK[Disk Usage]
        NETWORK[Network I/O]
    end
    
    subgraph "Monitoring Stack"
        PROMETHEUS[Prometheus]
        GRAFANA[Grafana]
        ALERT[Alert Manager]
        LOGS[Log Aggregation]
    end
    
    APP --> HEALTH
    APP --> METRICS
    HEALTH --> PROMETHEUS
    METRICS --> PROMETHEUS
    
    CPU --> PROMETHEUS
    MEMORY --> PROMETHEUS
    DISK --> PROMETHEUS
    NETWORK --> PROMETHEUS
    
    PROMETHEUS --> GRAFANA
    PROMETHEUS --> ALERT
    PROMETHEUS --> LOGS
```

## Database Relationships

```mermaid
erDiagram
    USER {
        uuid id PK
        string email UK
        string password_hash
        string first_name
        string last_name
        enum role
        boolean is_active
        timestamp created_at
        timestamp updated_at
    }
    
    SESSION {
        uuid id PK
        uuid user_id FK
        string token
        timestamp expires_at
        timestamp created_at
    }
    
    AUDIT_LOG {
        uuid id PK
        uuid user_id FK
        string action
        string resource
        json metadata
        timestamp created_at
    }
    
    USER ||--o{ SESSION : "has"
    USER ||--o{ AUDIT_LOG : "generates"
```

## Component Interaction Diagram

```mermaid
graph TB
    subgraph "Frontend"
        UI[User Interface]
        DASHBOARD[Dashboard]
        FORMS[Forms]
    end
    
    subgraph "Backend Services"
        CONTROLLER[Controller Layer]
        SERVICE[Service Layer]
        REPOSITORY[Repository Layer]
    end
    
    subgraph "External Integrations"
        KEYCLOAK[Keycloak]
        EMAIL[Email Service]
        SMS[SMS Gateway]
    end
    
    UI --> CONTROLLER
    DASHBOARD --> CONTROLLER
    FORMS --> CONTROLLER
    
    CONTROLLER --> SERVICE
    SERVICE --> REPOSITORY
    SERVICE --> KEYCLOAK
    SERVICE --> EMAIL
    SERVICE --> SMS
    
    REPOSITORY --> DATABASE[(Database)]
```

---

## Diagram Usage Guidelines

### When to Use Each Diagram Type

1. **System Architecture** - High-level system overview
2. **Sequence Diagrams** - User interactions and workflows
3. **Flowcharts** - Process flows and decision trees
4. **Entity Relationship** - Database schema relationships
5. **Network Diagrams** - Infrastructure and connectivity

### Best Practices

- **Keep diagrams simple** and focused on key concepts
- **Use consistent colors** and styling
- **Include legends** for complex diagrams
- **Update diagrams** when architecture changes
- **Version control** diagram source files

### Tools for Creating Diagrams

- **Mermaid** - Text-based diagram creation
- **PlantUML** - UML and other diagram types
- **Draw.io** - Visual diagram editor
- **Lucidchart** - Collaborative diagramming
- **Visio** - Microsoft diagramming tool
