# Security

**Goal:** Build secure systems with strong development and operational practices.

**Current Level:** Level 1

---

## Level 1: Basic Security
- **Competencies:** Understand and apply essential security practices to reduce obvious risks.
- **Tools:** Spring Security, BCrypt, HTTPS, Input Validation, Environment Variables
- **Checklist:**
  - [x] HTTPS enabled (with valid TLS cert)
  - [x] Input validation using Spring Boot validation annotations
  - [x] Secure password storage using BCrypt
  - [x] No secrets or credentials in code/repos

## Level 2: Secure Development Practices
- **Competencies:** Develop with security in mind and address common OWASP risks.
- **Tools:** Spring Security, OWASP ZAP, Snyk, Dependabot, JWT, CORS
- **Checklist:**
  - [ ] Protection against OWASP Top 10 (XSS, CSRF, SQLi, etc.)
  - [ ] Role-based access control (RBAC) with Spring Security
  - [ ] Rate limiting / brute-force protection
  - [ ] Secrets management system in place (e.g., Vault or SSM)
  - [ ] Dependency vulnerability scanning

## Level 3: App Layer Threat Modeling & Secure SDLC
- **Competencies:** Build software with security embedded in the design and lifecycle.
- **Tools:** Keycloak, OPA, Gosec, OWASP ZAP, GitHub Actions security scans
- **Checklist:**
  - [ ] Threat modeling done per service (e.g., STRIDE, DFD)
  - [ ] Security testing integrated in CI/CD (SAST, DAST)
  - [ ] Centralized authentication/authorization (OAuth2, OIDC, Keycloak)
  - [ ] Logging/auditing of sensitive operations
  - [ ] Input/output encoding via secure libraries

## Level 4: Continuous Security & Monitoring
- **Competencies:** Detect and respond to threats in real-time, minimize attack window.
- **Tools:** Loki/Grafana, Alertmanager, Cloudflare WAF, Falco, Prometheus, JWT/mTLS
- **Checklist:**
  - [ ] Centralized log aggregation (e.g., ELK/Promtail)
  - [ ] Real-time alerting on anomalies and abuse patterns
  - [ ] Web Application Firewall (WAF) in place
  - [ ] API security tokens with expiry, rotation (JWT, mTLS)
  - [ ] Security incidents documented and practiced (playbooks)

## Level 5: Zero Trust + Offensive Readiness
- **Competencies:** Architect systems assuming compromise; proactively test and defend.
- **Tools:** Istio, Tailscale, OPA/Rego, Casbin, Burp Suite, SecurityScorecard, GCP/AWS Identity-Aware Proxy
- **Checklist:**
  - [ ] Zero Trust architecture (identity-aware proxies, least privilege)
  - [ ] Bug bounty / red teaming / penetration testing pipeline
  - [ ] Secrets rotation automation + Just-in-Time access for any sensitive systems
  - [ ] AI/ML anomaly detection for usage & traffic using security tools and monitoring tools

---

## Resources
- [OWASP Top Ten](https://owasp.org/www-project-top-ten/)
- [Go Security Best Practices](https://securego.dev/)
- [GitHub Security Features](https://docs.github.com/en/code-security)

---

## Progress & Evidence

??? success "Level 1: Basic Security (18-10-2025)"
    **Status:** Completed  
    **Focus:** Spring Security, authentication, input validation, secure password storage  
    **Evidence:**  
    - [Smart Deploy Monitor Security Implementation](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **Spring Security Integration** (Completed 18-10-2025): Implemented authentication and authorization
    - **BCrypt Password Encryption** (Completed 18-10-2025): Secure password storage with BCrypt
    - **Input Validation** (Completed 18-10-2025): Jakarta Bean Validation for request validation
    - **Environment Security** (Completed 18-10-2025): No secrets in code, proper configuration management
    
    **What I Learned:**
    - **Spring Security**: Authentication and authorization framework for Spring Boot applications
    - **BCrypt Password Hashing**: Secure password storage with salt and hashing
    - **Input Validation**: Jakarta Bean Validation annotations for request validation
    - **Security Configuration**: Proper security configuration and best practices
    - **Environment Variables**: Secure configuration management without hardcoded secrets
    - **HTTPS Configuration**: SSL/TLS configuration for secure communication
    
    **Applied Knowledge:**
    - Implemented BCryptPasswordEncoder for secure password storage
    - Added input validation using @Valid, @NotBlank, @Email annotations
    - Configured Spring Security for authentication and authorization
    - Used environment variables for sensitive configuration
    - Implemented proper error handling for security-related issues
    - Applied security best practices throughout the application
    
    **Code Examples:**
    ```java
    @Configuration
    public class SecurityConfig {
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
    
    @RestController
    public class UserController {
        @PostMapping("/api/users")
        public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
            // Input validation automatically applied
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            // Secure password storage
        }
    }
    ```
    
    **Resources Used:**
    - Spring Security documentation and best practices
    - BCrypt password hashing implementation
    - Jakarta Bean Validation for input validation
    - Spring Boot security configuration
    - OWASP security guidelines
    
    **Key Achievement:** Successfully implemented comprehensive security measures in Spring Boot application including authentication, authorization, secure password storage, and input validation, demonstrating professional security practices.

??? note "Level 2: Secure Development"
    **Status:** Planned  
    **Focus:** Secure coding practices, dependency scanning

??? note "Level 3: Application Security"
    **Status:** Planned  
    **Focus:** Threat modeling, secure SDLC

??? note "Level 4: Security Operations"
    **Status:** Planned  
    **Focus:** Continuous security monitoring, incident response

??? note "Level 5: Advanced Security"
    **Status:** Planned  
    **Focus:** Zero trust architecture, offensive security readiness

---

## Personal Notes
