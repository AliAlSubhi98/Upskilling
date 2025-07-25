# Testing

**Goal:** Confidently test and validate software.

**Current Level:** Level 1

---

## Level 1: Basic Testing
- **Competencies:** Understand the role of testing in Golang, write basic unit tests using the built-in testing package with table-driven tests, apply the arrange–act–assert pattern and use sub-tests, measure code coverage using `go test -cover`.
- **Tools:** Go’s built-in testing package, `go test` command, basic assertion methods, optional use of Testify/assert
- **Checklist:**
  - [ ] Write unit tests for simple utility functions
  - [ ] Create table-driven tests covering various edge cases
  - [ ] Configure and review code coverage reports using `go test -cover`

## Level 2: Intermediate Testing
- **Competencies:** Develop integration tests for backend services (e.g., REST API handlers), test HTTP endpoints using `net/http/httptest`, use dependency injection to mock dependencies, start incorporating tests into CI.
- **Tools:** `httptest`, Testify/mock or GoMock, in-memory databases or test containers, GitHub Actions
- **Checklist:**
  - [ ] Write integration tests for REST API endpoints and verify HTTP responses
  - [ ] Use mocks to simulate external services
  - [ ] Integrate tests into a CI pipeline with automated coverage reporting

## Level 3: Advanced Testing
- **Competencies:** Implement table-driven tests for complex logic and concurrency, write benchmarks for backend performance, simulate load and detect race conditions, test middleware and error handling.
- **Tools:** `go test -race`, Go benchmarking, advanced Testify, GoMock or similar
- **Checklist:**
  - [ ] Develop benchmarks for high-traffic API endpoints
  - [ ] Write tests that simulate concurrent requests and identify race conditions
  - [ ] Test custom middleware and simulate error scenarios

## Level 4: Expert Testing
- **Competencies:** Design end-to-end tests for distributed systems, use Docker-based test environments, verify async processing and inter-service communication.
- **Tools:** Docker, Docker Compose, testcontainers-go, integration frameworks, CI with multi-container support
- **Checklist:**
  - [ ] Build an end-to-end test suite using Docker
  - [ ] Simulate async processing and verify results
  - [ ] Integrate comprehensive E2E tests within CI/CD

## Level 5: Master Testing
- **Competencies:** Architect load and performance tests for Golang backends, integrate security/static analysis, apply chaos testing to assess resilience, mentor others on scalable testing.
- **Tools:** Go benchmarks, Vegeta, chaos tools, `golangci-lint`, CI/CD with performance dashboards
- **Checklist:**
  - [ ] Develop load testing for real-world stress scenarios
  - [ ] Incorporate automated security and static analysis into CI
  - [ ] Lead testing optimization and mentoring initiatives

---

## Resources
- [Testing Trophy by Kent C. Dodds](https://kentcdodds.com/blog/the-testing-trophy)
- [OWASP Testing Guide](https://owasp.org/www-project-web-security-testing-guide/)
- [Awesome Testing](https://github.com/TheJambo/awesome-testing)

## Personal Notes
