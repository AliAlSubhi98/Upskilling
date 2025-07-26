# Git Level 2: Merge vs Rebase

## Understanding the Difference

### Merge (Creates Merge Commit)
- Preserves branch history
- Shows when features were integrated
- Creates a merge commit with two parents

```bash
git checkout main
git merge feature/multiply
# Creates: "Merge branch 'feature/multiply' into main"
```

### Rebase (Linear History)
- Rewrites commit history
- Creates clean, linear progression
- No merge commits

```bash
git checkout feature/divide
git rebase main           # Move feature commits on top of main
git checkout main
git merge feature/divide  # Fast-forward merge (no merge commit)
```

## Practice Steps

### Step 1: Setup
```bash
git init
git add .
git commit -m "Initial calculator with add/subtract"
```

### Step 2: Create Feature Branches
```bash
git checkout -b feature/multiply
git checkout -b feature/divide
git checkout -b feature/power
```

### Step 3: Practice Merge Strategy
```bash
# Add multiply function, commit
git checkout main
git merge feature/multiply  # Creates merge commit
```

### Step 4: Practice Rebase Strategy
```bash
# Add divide function, commit
git checkout feature/divide
git rebase main            # Rewrite history
git checkout main
git merge feature/divide   # Fast-forward (no merge commit)
```

## When to Use Each
- **Merge**: When you want to preserve feature development history
- **Rebase**: When you want clean, linear project history 