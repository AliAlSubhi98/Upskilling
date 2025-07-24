# Security

**Goal:** Build and maintain secure systems.

**Current Level:** Level 1

---

## Level 1: Basic Security
- **Competencies:** HTTPS, input validation, password storage, secrets management
- **Tools:** Let's Encrypt, bcrypt, .env files
- **Checklist:**
  - [ ] Enable HTTPS with valid TLS cert
  - [ ] Validate user input
  - [ ] Store passwords securely (e.g., bcrypt)
  - [ ] No secrets in code/repos

## Level 2: Secure Development Practices
- **Competencies:** OWASP Top 10, RBAC, rate limiting, dependency scanning
- **Tools:** ZAP, Snyk, Dependabot, Vault
- **Checklist:**
  - [ ] Protect against OWASP Top 10
  - [ ] Implement RBAC
  - [ ] Set up rate limiting
  - [ ] Use a secrets management system
  - [ ] Scan dependencies for vulnerabilities

## Level 3: Threat Modeling & Secure SDLC
- **Competencies:** Threat modeling, SAST/DAST, centralized auth, auditing
- **Tools:** Keycloak, OPA, GoSec, ZAP
- **Checklist:**
  - [ ] Perform threat modeling
  - [ ] Integrate security testing in CI/CD
  - [ ] Use centralized auth (OAuth2, OIDC)
  - [ ] Log/audit sensitive operations

## Level 4: Continuous Security & Monitoring
- **Competencies:** Log aggregation, alerting, WAF, token security, incident response
- **Tools:** Loki, Grafana, Cloudflare WAF, Falco
- **Checklist:**
  - [ ] Aggregate logs centrally
  - [ ] Set up real-time alerting
  - [ ] Use a WAF
  - [ ] Implement token expiry/rotation
  - [ ] Document security incidents

## Level 5: Zero Trust & Offensive Readiness
- **Competencies:** Zero Trust, red teaming, secrets rotation, anomaly detection
- **Tools:** Istio, Tailscale, OPA, Burp Suite
- **Checklist:**
  - [ ] Architect for Zero Trust
  - [ ] Run penetration tests
  - [ ] Automate secrets rotation
  - [ ] Use AI/ML for anomaly detection

---

## Resources
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Google Security Blog](https://security.googleblog.com/)
- [SecurityScorecard Blog](https://securityscorecard.com/blog)

## Personal Notes