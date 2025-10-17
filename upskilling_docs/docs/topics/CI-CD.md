# CI/CD (Continuous Integration & Deployment)

**Goal:** Build, test, and deploy software automatically using modern CI/CD tools and best practices.

**Current Level:** Level 1

---

## Level 1: CI/CD Fundamentals
- **Competencies:** Basic GitHub Actions, YAML Pipelines, Continuous Integration
- **Tools:** GitHub Actions, YAML, Workflows, Runners
- **Checklist:**
  - [x] Set up a GitHub Actions workflow that runs on push
  - [x] Automate a simple build process using GitHub Actions
  - [x] Debug a failing CI/CD pipeline

### Progress
**Completed:** Smart Deploy Monitor CI/CD Pipeline

**What I Learned:**
- **GitHub Actions**: Created YAML workflows for automated testing and building
- **Maven Integration**: Configured Maven builds in CI/CD pipeline with dependency caching
- **Java CI/CD**: Set up Java 17 environment with proper build and test automation
- **Artifact Management**: Uploaded build artifacts for deployment
- **Pipeline Debugging**: Fixed workflow issues and optimized for monorepo structure
- **Conditional Triggers**: Configured workflows to run only on specific directory changes

**Resources Used:**
- GitHub Actions documentation
- Maven documentation
- ChatGPT for troubleshooting pipeline issues
- Spring Boot Maven plugin documentation

**How I Applied This Knowledge:**
- Created a complete CI/CD pipeline for the Spring Boot application
- Implemented automated testing with Maven Surefire
- Set up dependency caching to speed up builds
- Configured proper Java environment setup
- Added artifact upload for deployment readiness
- Integrated with the observability application for end-to-end testing

## Level 2: Automated Testing & Deployments
- **Competencies:** Unit Tests, Linting, Docker Builds, Secrets Management
- **Tools:** Docker, Jest/Pytest, GitHub Secrets, CodeCov, Linters
- **Checklist:**
  - [ ] Integrate unit testing into CI/CD pipeline
  - [ ] Set up Docker image builds and push to GHCR
  - [ ] Configure secret management for sensitive credentials

## Level 3: Infrastructure as Code & Secure Deployments
- **Competencies:** Terraform, Kubernetes Deployments, GitOps
- **Tools:** Terraform, ArgoCD, Helm, Kubernetes
- **Checklist:**
  - [ ] Automate infrastructure provisioning with Terraform
  - [ ] Deploy a Kubernetes app using GitHub Actions
  - [ ] Implement role-based access control (RBAC) for CI/CD workflows

## Level 4: Advanced CI/CD Pipelines
- **Competencies:** Multi-Stage Builds, Observability, Deployment Rollbacks
- **Tools:** Prometheus, Grafana, Jaeger, Self-Hosted Runners, Canary Deployments
- **Checklist:**
  - [ ] Implement multi-stage builds for optimized CI/CD pipelines
  - [ ] Deploy a monitoring system for CI/CD health tracking
  - [ ] Use self-hosted runners to speed up workflows

## Level 5: Enterprise CI/CD & Large-Scale Infrastructure Automation
- **Competencies:** Kubernetes Operators, Advanced GitOps, Full Automation
- **Tools:** GitHub Enterprise, Cross-Cluster Deployments, Spinnaker, Tekton
- **Checklist:**
  - [ ] Design an enterprise-grade CI/CD pipeline for multiple teams
  - [ ] Optimize a highly available self-hosted runner system
  - [ ] Implement zero-downtime deployments at scale

---

## Resources
- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [Terraform by HashiCorp](https://www.terraform.io/)
- [ArgoCD Docs](https://argo-cd.readthedocs.io/)
- [Tekton Pipelines](https://tekton.dev/)

---

## Progress & Evidence

??? note "Level 1: CI/CD Basics"
    **Status:** Planned  
    **Focus:** Basics of CI/CD, GitHub Actions, YAML pipelines  
    **Next Task:** Set up first GitHub Actions workflow

??? note "Level 2: Automated Testing & Deployment"
    **Status:** Planned  
    **Focus:** Automated testing, deployment pipelines

??? note "Level 3: Secure Pipelines"
    **Status:** Planned  
    **Focus:** Secure CI/CD pipelines, Terraform (optional)

??? note "Level 4: Multi-Environment Pipelines"
    **Status:** Planned  
    **Focus:** Multi-environment pipelines, self-hosted runners

??? note "Level 5: Enterprise CI/CD"
    **Status:** Planned  
    **Focus:** Enterprise CI/CD, full infrastructure automation

---

## Personal Notes
