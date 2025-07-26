# Git Level 2: Branching Practice

## Goal
Master feature branch workflows, merging, and rebasing.

## Practice Exercises

### Exercise 1: Feature Branch Workflow
```bash
# Create and work on feature branches
git checkout -b feature/user-login
git checkout -b feature/dashboard
git checkout -b feature/settings

# Make changes, commit, and merge back to main
git checkout main
git merge feature/user-login
```

### Exercise 2: Merge vs Rebase
```bash
# Practice merge (creates merge commit)
git checkout main
git merge feature/dashboard

# Practice rebase (linear history)
git checkout feature/settings
git rebase main
git checkout main
git merge feature/settings  # Fast-forward merge
```

### Exercise 3: Branch Management
```bash
# List branches
git branch
git branch -a  # Include remote branches

# Delete branches
git branch -d feature/completed-feature
git branch -D feature/abandoned-feature  # Force delete

# Rename branches
git branch -m old-name new-name
```

## Skills to Practice
1. Creating feature branches from main
2. Making commits on feature branches
3. Merging branches (merge commit)
4. Rebasing branches (linear history)
5. Cleaning up merged branches 