#!/bin/bash

# GitHub Issues Creation Script for Smart Deploy Monitor V2
# Make sure you have GitHub CLI installed: brew install gh

echo "Creating GitHub Issues for Smart Deploy Monitor V2..."

# Check if GitHub CLI is installed
if ! command -v gh &> /dev/null; then
    echo "GitHub CLI not found. Please install it first:"
    echo "   brew install gh"
    echo "   gh auth login"
    exit 1
fi

# Check if user is authenticated
if ! gh auth status &> /dev/null; then
    echo "Not authenticated with GitHub. Please run:"
    echo "   gh auth login"
    exit 1
fi

echo "GitHub CLI is ready!"

# Create labels first
echo "Creating labels..."
gh label create "task" --description "General tasks" --color "0e8a16" || true
gh label create "feature" --description "New features" --color "7057ff" || true
gh label create "bug" --description "Bug fixes" --color "d73a4a" || true
gh label create "documentation" --description "Documentation updates" --color "0075ca" || true
gh label create "story-points-1" --description "1 story point" --color "f9d0c4" || true
gh label create "story-points-2" --description "2 story points" --color "f9d0c4" || true
gh label create "story-points-3" --description "3 story points" --color "f9d0c4" || true
gh label create "story-points-4" --description "4 story points" --color "f9d0c4" || true
gh label create "story-points-5" --description "5 story points" --color "f9d0c4" || true
gh label create "sprint-1" --description "Sprint 1" --color "1d76db" || true
gh label create "sprint-2" --description "Sprint 2" --color "1d76db" || true
gh label create "sprint-3" --description "Sprint 3" --color "1d76db" || true
gh label create "priority-high" --description "High priority" --color "b60205" || true
gh label create "priority-medium" --description "Medium priority" --color "d93f0b" || true
gh label create "priority-low" --description "Low priority" --color "0e8a16" || true
gh label create "database" --description "Database related" --color "c2e0c6" || true
gh label create "authentication" --description "Authentication related" --color "c2e0c6" || true
gh label create "websocket" --description "WebSocket related" --color "c2e0c6" || true
gh label create "frontend" --description "Frontend related" --color "c2e0c6" || true
gh label create "api" --description "API related" --color "c2e0c6" || true

echo "Labels created!"

# Create milestones
echo "Creating milestones..."
gh api repos/AliAlSubhi98/Upskilling/milestones -f title="Sprint 1: Database & Authentication" -f description="Database setup and user authentication system" -f state="open" || true
gh api repos/AliAlSubhi98/Upskilling/milestones -f title="Sprint 2: Notifications & Dashboard" -f description="Real-time notifications and admin dashboard" -f state="open" || true
gh api repos/AliAlSubhi98/Upskilling/milestones -f title="Sprint 3: Documentation & Polish" -f description="API documentation and final polish" -f state="open" || true

echo "Milestones created!"

echo ""
echo "Setup complete! Now you can:"
echo "1. Go to: https://github.com/AliAlSubhi98/Upskilling/issues"
echo "2. Click 'New issue'"
echo "3. Use the templates in create-github-issues.md"
echo ""
echo "Or create issues manually using the GitHub web interface."
echo ""
echo "Quick Links:"
echo "   Issues: https://github.com/AliAlSubhi98/Upskilling/issues"
echo "   Labels: https://github.com/AliAlSubhi98/Upskilling/labels"
echo "   Milestones: https://github.com/AliAlSubhi98/Upskilling/milestones"