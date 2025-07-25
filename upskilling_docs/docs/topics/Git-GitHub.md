# Git & GitHub

**Goal:** Master version control and collaboration using Git and GitHub.

**Current Level:** Level 1

---

## Level 1: Git Basics
- **Competencies:** Init, Clone, Add, Commit, Push, Pull, Branching
- **Commands:** `git init`, `git clone`, `git add`, `git commit`, `git push`, `git pull`, `.gitignore`
- **Checklist:**
    - [x] Create a new repo and push to GitHub
    - [x] Clone a repo and make your first commit
    - [x] Use .gitignore to exclude files

## Level 2: Advanced Branching & Merging
- **Competencies:** Fast-Forward Merges, Three-Way Merges, Rebasing, Merge Conflicts, PRs
- **Commands:** `git branch`, `git checkout -b`, `git rebase`, `git merge`, `git cherry-pick`, GitHub PRs
- **Checklist:**
    - [x] Create feature branches and merge using both merge and rebase
    - [ ] Resolve complex merge conflicts
    - [ ] Use git cherry-pick to selectively move commits across branches

## Level 3: Commit History & Recovery
- **Competencies:** Reflog, Reset, Revert, Bisect, Tagging
- **Commands:** `git reflog`, `git reset --hard`, `git revert`, `git bisect`, `git tag`
- **Checklist:**
    - [ ] Recover lost commits using reflog
    - [ ] Use git bisect to find a bug in commit history
    - [ ] Reset changes safely without losing work

## Level 4: Enterprise GitOps & Security
- **Competencies:** Protected Branches, Signed Commits, CI/CD, LFS, GitHub Actions
- **Commands:** `git commit -S`, `git lfs`, GitHub Actions, Protected Branch Policies
- **Checklist:**
    - [ ] Enforce signed commits for security
    - [ ] Set up GitHub Actions for automated testing
    - [ ] Implement branch protection rules to enforce workflow policies

## Level 5: Deep Git Internals & Large-Scale GitOps
- **Competencies:** Custom Hooks, Packfiles, GitHub API, Large Repos
- **Commands:** `git fsck`, `git worktree`, `git gc`, `git filter-branch`, GitHub API
- **Checklist:**
    - [ ] Optimize a large repository using git gc and repack
    - [ ] Write a custom Git hook for automated commit checks
    - [ ] Use the GitHub GraphQL API to automate repo management

---

## Resources
- [Pro Git Book](https://git-scm.com/book/en/v2)
- [GitHub Docs](https://docs.github.com/en)
- [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials)

---

## Progress & Evidence

??? info "Level 1: Git Basics"
    **Status:**  Completed  
    **Focus:** Init, Clone, Add, Commit, Push, Pull, Branching  
    **Commands Practiced:**  
    `git init`, `git clone`, `git add`, `git commit`, `git push`, `git pull`, `.gitignore`  
    **Evidence:**  
    - [Commit History - GitHub Push Example](https://github.com/your-repo/example-commit)

??? info "Level 2: Branching & Merging"
    **Status:**  Completed  
    **Focus:** Fast-Forward, Rebasing, Merge Conflicts, PRs  
    **Evidence:**  
    - [Feature Branching Walkthrough](https://github.com/your-repo/feature-branch-example)

??? warning "Level 3: Commit History & Recovery"
    **Status:**  In Progress  
    **Focus:** `git reflog`, `git reset`, `git revert`, `git bisect`  
    **Next Task:** Recover deleted commit and document reflog use

??? note "Level 4: GitOps & Security"
    **Status:**  Planned  
    **Focus:** CI/CD, Signed Commits, LFS, Protected Branches

??? note "Level 5: Git Internals & Automation"
    **Status:**  Planned  
    **Focus:** Hooks, Packfiles, GitHub API, Performance

---

## Personal Notes

??? info "Note #1: Level 2 – Branching & Merging"
    To achieve **Level 2: Branching & Merging**, I followed these steps:

    **1. Create a feature branch**
    ```bash
    git checkout -b feature/advanced-branching
    ```

    **2. Make changes and commit**
    ```bash
    git add .
    git commit -m "Update Git-GitHub.md with Level 2 notes"
    ```

    **3. Rebase onto `main`**
    ```bash
    # Switch to main
    git checkout main

    # Pull the latest changes
    git pull origin main

    # Switch back to your feature branch
    git checkout feature/advanced-branching

    # Rebase onto latest main
    git rebase main
    ```

    **4. Push the branch and create a Pull Request**
    ```bash
    git push origin feature/advanced-branching
    ```

    **5. Merge Pull Request**
    ```bash
    - Open your PR on GitHub
    - Click "Merge pull request"
    - Confirm with "Confirm merge"
    - Optionally click "Delete branch"
    ```

---
