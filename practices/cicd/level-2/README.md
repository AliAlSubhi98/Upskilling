# CI/CD Level 2 Practice

Working on CI/CD Level 2: Automated Testing & Deployments.

## What I'm Learning

1. Docker builds and pushing to GitHub Container Registry
2. Managing secrets with GitHub Secrets
3. Adding code linting to CI/CD
4. Setting up automated deployments

## Tasks

- [x] Docker image builds and push to GHCR
- [ ] Secrets management
- [ ] Code linting in CI/CD
- [ ] Automated deployment pipelines

## Progress

### Step 1: Docker Build and Push to GHCR (Done)

Added Docker build job to CI/CD workflow that:
- Builds Docker image after tests pass
- Pushes to GitHub Container Registry (GHCR)
- Tags images with branch name, PR number, and commit SHA
- Uses Docker Buildx for multi-platform support

The workflow now builds and pushes the image automatically on every push to main/develop branches.

Verified: Workflow completed successfully, both test and build-docker jobs passed.

### Step 2: Secrets Management (In Progress)

Learning how to:
- Set up GitHub Secrets
- Use secrets securely in workflows
- Manage environment-specific secrets
- Best practices for secret rotation

## Notes

Will document progress as I go through each step.

Testing Docker build workflow - Dec 2025

