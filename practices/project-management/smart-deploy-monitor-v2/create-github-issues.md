# Creating GitHub Issues for Smart Deploy Monitor V2

## How to Create Issues

### Method 1: Using GitHub Web Interface
1. Go to: https://github.com/AliAlSubhi98/Upskilling/issues
2. Click "New issue"
3. Use the templates below

### Method 2: Using GitHub CLI (if installed)
```bash
# Install GitHub CLI first: brew install gh
gh auth login
gh issue create --title "Task 1: Database Setup" --body "$(cat issue-1.md)"
```

## Issue Templates

### Issue #1: Database Setup
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

## Labels
- task
- database
- story-points-3
- sprint-1
- priority-high

## Assignee
@AliAlSubhi98

## Milestone
Sprint 1 - Database & Authentication
```

### Issue #2: User Authentication
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

## Labels
- feature
- authentication
- story-points-5
- sprint-1
- priority-high

## Assignee
@AliAlSubhi98

## Milestone
Sprint 1 - Database & Authentication
```

### Issue #3: Real-time Notifications
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

## Labels
- feature
- websocket
- story-points-4
- sprint-1
- priority-medium

## Assignee
@AliAlSubhi98

## Milestone
Sprint 2 - Notifications & Dashboard
```

### Issue #4: Admin Dashboard
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

## Labels
- feature
- frontend
- story-points-3
- sprint-1
- priority-medium

## Assignee
@AliAlSubhi98

## Milestone
Sprint 2 - Notifications & Dashboard
```

### Issue #5: API Documentation
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

## Labels
- documentation
- api
- story-points-2
- sprint-1
- priority-low

## Assignee
@AliAlSubhi98

## Milestone
Sprint 3 - Documentation & Polish
```

## Creating Milestones

### Sprint 1: Database & Authentication (8 points)
- Task 1: Database Setup (3 points)
- Task 2: User Authentication (5 points)

### Sprint 2: Notifications & Dashboard (7 points)
- Task 3: Real-time Notifications (4 points)
- Task 4: Admin Dashboard (3 points)

### Sprint 3: Documentation & Polish (2 points)
- Task 5: API Documentation (2 points)

## Labels to Create
- `task` - General tasks
- `feature` - New features
- `bug` - Bug fixes
- `documentation` - Documentation updates
- `story-points-1` through `story-points-8`
- `sprint-1`, `sprint-2`, `sprint-3`
- `priority-high`, `priority-medium`, `priority-low`
- `database`, `authentication`, `websocket`, `frontend`, `api`