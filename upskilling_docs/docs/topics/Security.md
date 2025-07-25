# Security

**Goal:** Build secure systems with strong development and operational practices.

**Current Level:** Level 1

---

## Level 1: Basic Security
- **Competencies:** Understand and apply essential security practices to reduce obvious risks.
- **Tools:** Let's Encrypt, bcrypt, `.env` files, basic linters
- **Checklist:**
  - [ ] HTTPS enabled (with valid TLS cert)
  - [ ] Input validation (e.g., using basic html.EscapeString, etc.)
  - [ ] Secure password storage (e.g., bcrypt)
  - [ ] No secrets or credentials in code/repos

## Level 2: Secure Development Practices
- **Competencies:** Develop with security in mind and address common OWASP risks.
- **Tools:** Go’s security best practices, CSRF, ZAP, Snyk, Dependabot, HashiCorp Vault
- **Checklist:**
  - [ ] Protection against OWASP Top 10 (XSS, CSRF, SQLi, etc.)
  - [ ] Role-based access control (RBAC)
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

## Personal Notes
