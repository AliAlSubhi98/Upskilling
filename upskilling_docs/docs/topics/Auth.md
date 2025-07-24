# Authentication & Authorization

**Goal:** Securely manage user access and permissions.

**Current Level:** Level 1

---

## Level 1: Auth Basics
- **Competencies:** Password authentication, session management, basic user roles
- **Tools:** bcrypt, JWT, session cookies
- **Checklist:**
  - [ ] Implement password-based login
  - [ ] Use secure password hashing (e.g., bcrypt)
  - [ ] Manage user sessions securely

## Level 2: OAuth & Social Login
- **Competencies:** OAuth2, social login, token-based auth, refresh tokens
- **Tools:** OAuth2 libraries, Google/Facebook login, JWT
- **Checklist:**
  - [ ] Add OAuth2 or social login to an app
  - [ ] Use JWTs for stateless authentication
  - [ ] Implement token refresh/expiry

## Level 3: Authorization & RBAC
- **Competencies:** Role-based access control, permissions, scopes
- **Tools:** OPA, Casbin, Keycloak
- **Checklist:**
  - [ ] Implement RBAC in an app
  - [ ] Define and enforce permissions/scopes
  - [ ] Use a policy engine (e.g., OPA, Casbin)

## Level 4: Advanced Auth & Federation
- **Competencies:** SSO, SAML, OIDC, multi-factor auth, federation
- **Tools:** SAML, OIDC, Auth0, Okta, MFA tools
- **Checklist:**
  - [ ] Set up SSO with SAML or OIDC
  - [ ] Add multi-factor authentication
  - [ ] Federate identity across systems

## Level 5: Enterprise Security & Zero Trust
- **Competencies:** Just-in-time access, secrets rotation, auditing, zero trust
- **Tools:** Identity-aware proxies, Vault, audit logging tools
- **Checklist:**
  - [ ] Implement just-in-time access controls
  - [ ] Automate secrets rotation
  - [ ] Audit and monitor auth events
  - [ ] Architect for zero trust

---

## Resources
- [OAuth 2.0 and OpenID Connect](https://auth0.com/docs/protocols/oauth2)
- [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)
- [Keycloak Documentation](https://www.keycloak.org/documentation)

## Personal Notes