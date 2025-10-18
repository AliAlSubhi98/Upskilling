# Testing

**Goal:** Confidently test and validate software.

**Current Level:** Level 1

---

## Level 1: Basic Testing
- **Competencies:** Write unit tests for Spring Boot applications, test REST controllers and services, use JUnit 5 and Mockito, apply arrange-act-assert pattern, measure code coverage
- **Tools:** JUnit 5, Mockito, Spring Boot Test, Maven Surefire, JaCoCo
- **Checklist:**
  - [x] Write unit tests for service layer methods
  - [x] Test REST controller endpoints with MockMvc
  - [x] Use mocks for dependencies and external services
  - [x] Configure and review code coverage reports

## Level 2: Integration Testing
- **Competencies:** Develop integration tests for Spring Boot applications, test database interactions, use @SpringBootTest, test with real database connections
- **Tools:** @SpringBootTest, @DataJpaTest, @WebMvcTest, TestContainers, H2 Database
- **Checklist:**
  - [ ] Write integration tests for REST API endpoints with real database
  - [ ] Test database operations with @DataJpaTest
  - [ ] Use TestContainers for database integration tests
  - [ ] Integrate tests into CI pipeline with automated coverage reporting

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

---

## Progress & Evidence

??? success "Level 1: Basic Testing (18-10-2025)"
    **Status:** Completed  
    **Focus:** Unit testing, REST controller testing, service layer testing, code coverage  
    **Evidence:**  
    - [Smart Deploy Monitor Test Suite](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor/src/test)
    - **Unit Tests** (Completed 18-10-2025): Comprehensive unit tests for controllers and services
    - **Integration Tests** (Completed 18-10-2025): Database integration tests with @SpringBootTest
    - **Code Coverage** (Completed 18-10-2025): JaCoCo coverage reporting integrated with Maven
    
    **What I Learned:**
    - **JUnit 5 Testing**: Modern testing framework with annotations and assertions
    - **Mockito Mocking**: Mocking dependencies and external services for isolated testing
    - **Spring Boot Test**: @SpringBootTest, @WebMvcTest, @DataJpaTest for different test types
    - **MockMvc Testing**: Testing REST controllers with HTTP request simulation
    - **Test Coverage**: JaCoCo integration for code coverage measurement
    - **Test Organization**: Arrange-Act-Assert pattern and test structure best practices
    
    **Applied Knowledge:**
    - Created comprehensive unit tests for UserService and UserController
    - Implemented MockMvc tests for all REST endpoints
    - Used Mockito to mock database repositories and external services
    - Configured JaCoCo for code coverage reporting
    - Integrated testing into CI/CD pipeline with GitHub Actions
    - Applied testing best practices for maintainable test code
    
    **Code Examples:**
    ```java
    @SpringBootTest
    @AutoConfigureMockMvc
    class DemoControllerTest {
        @Autowired
        private MockMvc mockMvc;
        
        @Test
        void testHealthEndpoint() throws Exception {
            mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Application is running!"));
        }
    }
    ```
    
    **Resources Used:**
    - JUnit 5 documentation and best practices
    - Spring Boot Testing documentation
    - Mockito framework for mocking
    - JaCoCo for code coverage
    - Maven Surefire for test execution
    
    **Key Achievement:** Successfully implemented comprehensive testing suite for Spring Boot application with unit tests, integration tests, and code coverage reporting, demonstrating professional testing skills.

??? note "Level 2: Integration Testing"
    **Status:** Planned  
    **Focus:** Integration tests, HTTP simulation, CI integration

??? note "Level 3: Advanced Testing"
    **Status:** Planned  
    **Focus:** Complex tests, benchmarks, middleware testing

??? note "Level 4: End-to-End Testing"
    **Status:** Planned  
    **Focus:** E2E tests, container-based integration

??? note "Level 5: Enterprise Testing"
    **Status:** Planned  
    **Focus:** Performance, security, chaos testing

---

## Personal Notes
