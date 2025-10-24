# Authentication & Authorization

**Goal:** Secure systems with robust, scalable, and standards-compliant authentication and authorization flows.

**Current Level:** Level 1 (Complete)

---

## Level 1: Basic Auth
- **Competencies:**
  - Understand authentication vs. authorization
  - Manage Keycloak realms and clients
  - Handle HTTP cookies and status codes
  - Implement password hashing and session management
  - Understand basic JWT concepts
- **Key Tools & Practices:**
  - Keycloak, HTTP/HTTPS protocols, Cookie management
- **Checklist:**
  - [x] Initialize Keycloak for web apps
  - [x] Configure and secure Keycloak
  - [x] Integrate multiple social logins

## Level 2: Intermediate Auth
- **Competencies:**
  - Manage sessions with expiration and idle timeouts
  - Implement “Remember Me” securely
  - Enforce RBAC on protected routes
  - Apply strict authorization
- **Key Tools & Practices:**
  - OPA, HttpOnly cookies, CSRF protection
- **Checklist:**
  - Secure “remember me” with long-lived tokens
  - Build admin-only sections
  - Enforce CSRF protection via token validation

## Level 3: Advanced Auth
- **Competencies:**
  - Implement JWTs for stateless auth
  - Handle token expiration, refresh flows, and secure storage
  - Integrate OAuth2 and OpenID Connect
  - Enforce authorization with scopes, roles, and attributes
- **Key Tools & Practices:**
  - JWT, Refresh Tokens, OAuth2, OIDC
- **Checklist:**
  - Develop REST API to issue/verify JWTs
  - Implement seamless token refresh
  - Integrate OAuth2 providers (e.g., Google login)

## Level 4: Expert Auth
- **Competencies:**
  - Design SSO for multi-app access
  - Master OAuth2 grant types
  - Link social logins with external identities
  - Integrate enterprise protocols (SAML, OAuth/OIDC)
  - Implement MFA and advanced authorization (group-based, ABAC)
- **Key Tools & Practices:**
  - Auth0, Keycloak, MFA, SSO, SAML
- **Checklist:**
  - Set up and enforce MFA
  - Optimize social login and identity linking
  - Integrate and troubleshoot enterprise protocols

## Level 5: Master Auth
- **Competencies:**
  - Architect advanced auth (passwordless, WebAuthn)
  - Follow OWASP best practices
  - Design zero-trust microservice authorization
  - Enable audit logging and proactive monitoring
  - Guide adoption of evolving auth standards (OAuth 2.1, decentralized ID)
- **Key Tools & Practices:**
  - Magic Links, WebAuthn, Zero-Trust frameworks, OWASP, Audit Logging, Decentralized Identity
- **Checklist:**
  - Validate and implement passwordless/biometric auth
  - Establish robust logging and monitoring
  - Mentor teams on secure coding and auth practices

---

## Resources
- [OAuth 2.0 Spec](https://oauth.net/2/)
- [OpenID Connect](https://openid.net/connect/)
- [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)
- [Keycloak Docs](https://www.keycloak.org/documentation)

---

## Progress & Evidence

??? success "Level 1: JWT Authentication & Authorization (18-10-2025)"
    **Status:** Completed  
    **Focus:** JWT authentication, OAuth2 integration, RBAC, security best practices  
    **Evidence:**  
    - [Smart Deploy Monitor Authentication System](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **JWT Authentication** (Completed 18-10-2025): Access and refresh token implementation with Spring Security
    - **OAuth2 Integration** (Completed 18-10-2025): Google and GitHub OAuth2 client configuration
    - **Role-Based Access Control** (Completed 18-10-2025): USER and ADMIN roles with endpoint protection
    - **Security Configuration** (Completed 18-10-2025): CORS, CSRF protection, and security headers
    
    **What I Learned:**
    - **JWT Fundamentals**: Token generation, validation, expiration, and refresh mechanisms
    - **Spring Security**: Authentication filters, security configuration, and user details service
    - **OAuth2 Integration**: External authentication providers and social login flows
    - **RBAC Implementation**: Role-based access control with method-level security
    - **Security Best Practices**: Password hashing, token storage, and secure communication
    - **Authentication Flows**: Registration, login, token refresh, and logout processes
    
    **Applied Knowledge:**
    - Implemented JWT-based authentication with access and refresh tokens
    - Configured Spring Security with custom authentication filters
    - Integrated OAuth2 providers (Google, GitHub) for external authentication
    - Applied role-based access control to protect endpoints and resources
    - Implemented secure password hashing with BCrypt
    - Configured CORS and security headers for production deployment
    
    **Authentication Architecture Examples:**
    ```java
    // JWT Service for token management
    @Service
    public class JwtService {
        public String generateToken(UserDetails userDetails) {
            return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        }
        
        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
    }
    
    // Security Configuration with RBAC
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/database/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }
    }
    ```
    
    **Authentication Endpoints Implemented:**
    - `POST /api/auth/register` - User registration with validation
    - `POST /api/auth/login` - User authentication with JWT tokens
    - `POST /api/auth/refresh` - Token refresh mechanism
    - `POST /api/auth/logout` - User logout (client-side token removal)
    
    **Security Features Implemented:**
    - JWT access tokens with configurable expiration
    - Refresh tokens for seamless authentication
    - Role-based access control (USER, ADMIN)
    - OAuth2 integration for external providers
    - Password hashing with BCrypt
    - CORS configuration for cross-origin requests
    - Security headers and CSRF protection
    
    **Resources Used:**
    - Spring Security documentation and best practices
    - JWT.io for token debugging and validation
    - OAuth2 specification and implementation guides
    - OWASP authentication security guidelines
    - Spring Boot security configuration patterns
    
    **Key Achievement:** Successfully implemented a comprehensive authentication and authorization system with JWT tokens, OAuth2 integration, role-based access control, and security best practices, demonstrating professional-level authentication system design.

??? note "Level 2: Session Management"
    **Status:** Planned  
    **Focus:** Session management, RBAC implementation

??? note "Level 3: Modern Auth"
    **Status:** Planned  
    **Focus:** JWT, OAuth2, scopes

??? note "Level 4: Enterprise Auth"
    **Status:** Planned  
    **Focus:** SSO, OAuth2, MFA, protocols

??? note "Level 5: Advanced Security"
    **Status:** Planned  
    **Focus:** Passwordless, WebAuthn, zero-trust, logging

---

## Personal Notes
