# Manual GitHub Issues Setup Guide

## Step 1: Go to GitHub Issues
**URL:** https://github.com/AliAlSubhi98/Upskilling/issues

## Step 2: Create Labels
Click "Labels" tab, then "New label" for each:

### Priority Labels
- `priority-high` (Red: #b60205) - High priority tasks
- `priority-medium` (Orange: #d93f0b) - Medium priority tasks  
- `priority-low` (Green: #0e8a16) - Low priority tasks

### Type Labels
- `task` (Green: #0e8a16) - General tasks
- `feature` (Purple: #7057ff) - New features
- `bug` (Red: #d73a4a) - Bug fixes
- `documentation` (Blue: #0075ca) - Documentation updates

### Story Points Labels
- `story-points-1` (Light: #f9d0c4) - 1 story point
- `story-points-2` (Light: #f9d0c4) - 2 story points
- `story-points-3` (Light: #f9d0c4) - 3 story points
- `story-points-4` (Light: #f9d0c4) - 4 story points
- `story-points-5` (Light: #f9d0c4) - 5 story points

### Sprint Labels
- `sprint-1` (Blue: #1d76db) - Sprint 1
- `sprint-2` (Blue: #1d76db) - Sprint 2
- `sprint-3` (Blue: #1d76db) - Sprint 3

### Technology Labels
- `database` (Light Green: #c2e0c6) - Database related
- `authentication` (Light Green: #c2e0c6) - Authentication related
- `websocket` (Light Green: #c2e0c6) - WebSocket related
- `frontend` (Light Green: #c2e0c6) - Frontend related
- `api` (Light Green: #c2e0c6) - API related

## Step 3: Create Milestones
Click "Milestones" tab, then "New milestone":

### Milestone 1: Sprint 1 - Database & Authentication
- **Title:** Sprint 1: Database & Authentication
- **Description:** Database setup and user authentication system
- **Due Date:** 2 weeks from now

### Milestone 2: Sprint 2 - Notifications & Dashboard  
- **Title:** Sprint 2: Notifications & Dashboard
- **Description:** Real-time notifications and admin dashboard
- **Due Date:** 4 weeks from now

### Milestone 3: Sprint 3 - Documentation & Polish
- **Title:** Sprint 3: Documentation & Polish
- **Description:** API documentation and final polish
- **Due Date:** 6 weeks from now

## Step 4: Create Issues
Click "New issue" and use these templates:

### Issue 1: Database Setup
**Title:** `Task 1: Database Setup (3 story points)`

**Body:**
```markdown
## Task Description
Set up PostgreSQL database connection for Smart Deploy Monitor V2

## User Story
As a developer, I want to connect to a PostgreSQL database so that I can store user data and application state.

## Acceptance Criteria
- [ ] PostgreSQL Docker container running
- [ ] Spring Boot connects to database successfully
- [ ] Basic CRUD operations work
- [ ] Database schema is created automatically

## Technical Details
- Use Docker Compose for PostgreSQL
- Add Spring Data JPA dependency
- Create User entity with JPA annotations
- Configure application.yml for database connection

## Story Points
3 points
```

**Labels:** `task`, `database`, `story-points-3`, `sprint-1`, `priority-high`
**Milestone:** Sprint 1: Database & Authentication
**Assignee:** @AliAlSubhi98

### Issue 2: User Authentication
**Title:** `Task 2: User Authentication System (5 story points)`

**Body:**
```markdown
## Task Description
Implement JWT-based user authentication system

## User Story
As a user, I want to register and login so that I can access protected features of the application.

## Acceptance Criteria
- [ ] User registration endpoint
- [ ] User login endpoint
- [ ] JWT token generation and validation
- [ ] Password encryption (BCrypt)
- [ ] Protected endpoints require authentication

## Technical Details
- Use Spring Security
- JWT token with 24-hour expiration
- User entity with email, password, roles
- Authentication filter for protected routes

## Dependencies
- Depends on: Task 1 (Database Setup)

## Story Points
5 points
```

**Labels:** `feature`, `authentication`, `story-points-5`, `sprint-1`, `priority-high`
**Milestone:** Sprint 1: Database & Authentication
**Assignee:** @AliAlSubhi98

### Issue 3: Real-time Notifications
**Title:** `Task 3: Real-time Notifications (4 story points)`

**Body:**
```markdown
## Task Description
Implement WebSocket-based real-time notifications

## User Story
As a user, I want to receive real-time notifications so that I can stay updated on system events.

## Acceptance Criteria
- [ ] WebSocket connection established
- [ ] Server can send notifications to clients
- [ ] Client receives and displays notifications
- [ ] Notification types: system alerts, user messages

## Technical Details
- Use Spring WebSocket
- STOMP protocol for messaging
- Frontend WebSocket client
- Notification entity in database

## Dependencies
- Depends on: Task 2 (User Authentication)

## Story Points
4 points
```

**Labels:** `feature`, `websocket`, `story-points-4`, `sprint-2`, `priority-medium`
**Milestone:** Sprint 2: Notifications & Dashboard
**Assignee:** @AliAlSubhi98

### Issue 4: Admin Dashboard
**Title:** `Task 4: Admin Dashboard (3 story points)`

**Body:**
```markdown
## Task Description
Create admin dashboard for system management

## User Story
As an admin, I want to view system statistics and manage users so that I can monitor and control the application.

## Acceptance Criteria
- [ ] Admin login page
- [ ] User management interface
- [ ] System statistics display
- [ ] Real-time metrics dashboard

## Technical Details
- React frontend component
- Admin role-based access
- REST API endpoints for admin functions
- Charts for system metrics

## Dependencies
- Depends on: Task 2 (User Authentication)

## Story Points
3 points
```

**Labels:** `feature`, `frontend`, `story-points-3`, `sprint-2`, `priority-medium`
**Milestone:** Sprint 2: Notifications & Dashboard
**Assignee:** @AliAlSubhi98

### Issue 5: API Documentation
**Title:** `Task 5: API Documentation (2 story points)`

**Body:**
```markdown
## Task Description
Add comprehensive API documentation with Swagger/OpenAPI

## User Story
As a developer, I want to see API documentation so that I can understand how to use the endpoints.

## Acceptance Criteria
- [ ] Swagger UI accessible
- [ ] All endpoints documented
- [ ] Request/response examples
- [ ] Authentication documentation

## Technical Details
- SpringDoc OpenAPI 3
- Swagger UI configuration
- API annotations on controllers
- Example requests and responses

## Dependencies
None

## Story Points
2 points
```

**Labels:** `documentation`, `api`, `story-points-2`, `sprint-3`, `priority-low`
**Milestone:** Sprint 3: Documentation & Polish
**Assignee:** @AliAlSubhi98

## Step 5: Track Progress
- Update issue status as you work
- Add comments with progress updates
- Close issues when completed
- Use the project board to visualize progress

## Quick Links
- **Issues:** https://github.com/AliAlSubhi98/Upskilling/issues
- **Labels:** https://github.com/AliAlSubhi98/Upskilling/labels
- **Milestones:** https://github.com/AliAlSubhi98/Upskilling/milestones
- **Projects:** https://github.com/AliAlSubhi98/Upskilling/projects (create a project board)