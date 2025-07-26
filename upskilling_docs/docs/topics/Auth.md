# Authentication & Authorization

**Goal:** Secure systems with robust, scalable, and standards-compliant authentication and authorization flows.

**Current Level:** Level 1

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
  - Initialize Keycloak for web apps
  - Configure and secure Keycloak
  - Integrate multiple social logins

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

??? note "Level 1: Authentication Basics"
    **Status:** Planned  
    **Focus:** Auth vs AuthZ, cookies, sessions, hashing  
    **Next Task:** Implement basic session-based authentication

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
