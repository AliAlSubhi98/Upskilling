# Documentation

**Goal:** Write and maintain clear, effective documentation.

**Current Level:** Level 2 (In Progress)

---

## Level 1: Documentation Basics
- **Competencies:** Writing README files, basic Markdown, documenting code usage
- **Tools:** Markdown, GitHub README, Google Docs
- **Checklist:**
  - [x] Write a clear project README
  - [x] Document code usage and examples
  - [x] Use basic Markdown formatting

## Level 2: Structured Docs & Standards
- **Competencies:** API docs, docstrings, templates, style guides
- **Tools:** Sphinx, JSDoc, OpenAPI/Swagger, Confluence
- **Checklist:**
  - [x] Add docstrings/comments to functions and classes
  - [x] Create API documentation using a standard tool
  - [x] Follow a documentation style guide
  - [x] Create documentation templates
  - [x] Implement code documentation standards

## Level 3: Collaborative & Living Docs
- **Competencies:** Collaborative editing, versioned docs, changelogs, diagrams
- **Tools:** Google Docs, Notion, Mermaid, PlantUML
- **Checklist:**
  - [ ] Collaborate on docs with teammates
  - [ ] Maintain a changelog for your project
  - [ ] Add diagrams to explain architecture or flows

## Level 4: Automation & Publishing
- **Competencies:** Automated doc generation, publishing, continuous documentation
- **Tools:** MkDocs, Read the Docs, GitHub Pages, CI/CD integration
- **Checklist:**
  - [ ] Automate documentation builds and publishing
  - [ ] Host docs online for easy access
  - [ ] Integrate doc updates into CI/CD pipeline

## Level 5: Documentation as a Product
- **Competencies:** User guides, onboarding, localization, feedback loops
- **Tools:** Helpdesk platforms, translation tools, analytics
- **Checklist:**
  - [ ] Create user guides or onboarding docs
  - [ ] Localize documentation for multiple languages
  - [ ] Collect and act on user feedback for docs

---

## Resources
- [Google Technical Writing Courses](https://developers.google.com/tech-writing)
- [Diátaxis Documentation Framework](https://diataxis.fr/)
- [Write the Docs Community](https://www.writethedocs.org/)

---

## Progress & Evidence

??? success "Level 1: Documentation Basics (18-10-2025)"
    **Status:** Completed  
    **Focus:** READMEs, basic technical writing, Markdown formatting  
    **Evidence:**  
    - [Upskilling Documentation Site](https://AliAlSubhi98.github.io/Upskilling/)
    - [Smart Deploy Monitor Project](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/observability-cicd/smart-deploy-monitor)
    - **Comprehensive README Files** (Completed 18-10-2025): Created detailed README files for all projects
    - **MkDocs Documentation** (Completed 18-10-2025): Built complete documentation site with navigation
    - **Markdown Proficiency** (Completed 18-10-2025): Professional formatting and structure
    
    **What I Learned:**
    - **README Best Practices**: Clear project descriptions, setup instructions, usage examples
    - **Markdown Mastery**: Headers, lists, code blocks, tables, links, and formatting
    - **Documentation Structure**: Logical organization and navigation
    - **Technical Writing**: Clear, concise, and user-friendly documentation
    - **MkDocs Integration**: Static site generation and GitHub Pages deployment
    - **Version Control**: Documentation as code with Git integration
    
    **Applied Knowledge:**
    - Created comprehensive README files for all projects with setup and usage instructions
    - Built professional MkDocs documentation site with proper navigation
    - Implemented consistent documentation standards across all projects
    - Used advanced Markdown features for professional presentation
    - Integrated documentation with CI/CD for automated deployment
    
    **Resources Used:**
    - MkDocs documentation and Material theme
    - GitHub Pages for hosting
    - Markdown best practices and formatting
    - Technical writing principles
    
    **Key Achievement:** Successfully created and maintained comprehensive documentation for multiple projects, demonstrating professional documentation skills and technical writing proficiency.

??? success "Level 2: Structured Documentation (Completed)"
    **Status:** Completed  
    **Focus:** API docs, docstrings, templates, style guides  
    **Evidence:**
    - [Documentation Level 2 Practice](https://github.com/AliAlSubhi98/Upskilling/tree/main/practices/documentation/level-2)
    - **Java Documentation Examples** (Completed 26-10-2025): Comprehensive JavaDoc examples with full documentation standards
    - **API Documentation Template** (Completed 26-10-2025): Complete REST API documentation template with examples
    - **Documentation Style Guide** (Completed 26-10-2025): Comprehensive style guide with formatting standards and best practices

    ??? tip "Level 2: Structured Documentation Commands & Examples"
        **JavaDoc Documentation:**
        ```java
        /**
         * Creates a new user with the provided information.
         * 
         * This method validates the input data, encrypts the password using BCrypt,
         * and persists the user to the database.
         * 
         * @param user The user object containing all necessary information
         * @return User The created user with generated ID and timestamps
         * @throws IllegalArgumentException if email is already in use
         * @throws ValidationException if input data is invalid
         * 
         * @example
         * <pre>{@code
         * User newUser = new User();
         * newUser.setEmail("ahmed.hassan@example.com");
         * newUser.setPassword("securePassword123");
         * User createdUser = userService.createUser(newUser);
         * }</pre>
         */
        public User createUser(User user) {
            // Implementation
        }
        ```
        
        **API Documentation Structure:**
        ```markdown
        ### GET /users
        Retrieve a list of users with optional filtering and pagination.
        
        **Parameters:**
        - `page` (optional, integer): Page number (default: 0)
        - `size` (optional, integer): Number of items per page (default: 10)
        - `role` (optional, string): Filter by user role
        
        **Response:**
        ```json
        {
          "content": [
            {
              "id": "uuid",
              "email": "ahmed.hassan@example.com",
              "firstName": "أحمد",
              "lastName": "حسن",
              "role": "USER",
              "isActive": true,
              "createdAt": "2025-10-26T10:00:00Z"
            }
          ],
          "pageable": {
            "pageNumber": 0,
            "pageSize": 10,
            "totalElements": 100,
            "totalPages": 10
          }
        }
        ```
        
        **Status Codes:**
        - `200 OK`: Successfully retrieved users
        - `400 Bad Request`: Invalid parameters
        - `401 Unauthorized`: Authentication required
        ```
        
        **Documentation Style Guide:**
        ```markdown
        # Headers
        # Level 1 Header (Page Title)
        ## Level 2 Header (Major Sections)
        ### Level 3 Header (Subsections)
        
        # Code Blocks
        ```java
        public class Example {
            // Code here
        }
        ```
        
        # Tables
        | Column 1 | Column 2 | Column 3 |
        |----------|----------|----------|
        | Data 1   | Data 2   | Data 3   |
        ```
        
        **Documentation Templates:**
        ```markdown
        # API Documentation Template
        ## Overview
        Brief description of the API, its purpose, and main functionality.
        
        ## Base URL
        ```
        https://api.example.com/v1
        ```
        
        ## Authentication
        Describe how to authenticate with the API.
        
        ## Endpoints
        [Detailed endpoint documentation with examples]
        ```

??? note "Level 3: Advanced Documentation"
    **Status:** Planned  
    **Focus:** Documentation as code, automated generation

??? note "Level 4: Documentation Systems"
    **Status:** Planned  
    **Focus:** Team documentation standards, governance

??? note "Level 5: Documentation Leadership"
    **Status:** Planned  
    **Focus:** Org-wide documentation strategy and culture

---

## Personal Notes