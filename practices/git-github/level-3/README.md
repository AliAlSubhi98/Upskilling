# Git Level 3: Commit History & Recovery (Java Version)

This directory contains practice exercises for Git Level 3 using Java examples instead of Python.

## 🎯 What You'll Learn

- **Git Reflog** - Recover lost commits using `git reflog`
- **Git Bisect** - Find bugs systematically with `git bisect`
- **Git Reset** - Safe recovery with different reset types
- **Git Revert** - Safe undo with `git revert`
- **Git Tagging** - Release management with `git tag`
- **Advanced Recovery** - Handle accidental deletions and branch issues

## 🚀 Getting Started

### Prerequisites
- Git installed on your system
- Java Development Kit (JDK) installed
- Basic understanding of Java (for the examples)

### Running the Practice

1. **Make the script executable:**
   ```bash
   chmod +x git-level3-java.sh
   ```

2. **Run the practice script:**
   ```bash
   ./git-level3-java.sh
   ```

3. **Follow the interactive exercises:**
   - The script will create a Java Calculator application
   - You'll practice Git recovery techniques
   - All examples use Java instead of Python

## 📋 Practice Exercises

### Exercise 1: Git Reflog
- Create a Java Calculator class
- Make commits and simulate losing them
- Use `git reflog` to recover lost commits
- Practice with `git reset --hard <commit-hash>`

### Exercise 2: Git Bisect
- Introduce a bug in the Java code
- Create a Java test script
- Use `git bisect to find the bug
- Practice systematic bug finding

### Exercise 3: Git Reset
- Practice different types of reset:
  - `git reset --soft` (keeps changes staged)
  - `git reset --mixed` (keeps changes unstaged)
  - `git reset --hard` (discards changes)

### Exercise 4: Git Revert
- Practice safe undo with `git revert`
- Create new commits that undo previous changes
- Learn when to use revert vs reset

### Exercise 5: Git Tagging
- Create lightweight and annotated tags
- Practice release management
- Learn tag best practices

### Exercise 6: Advanced Recovery
- Recover from accidental branch deletion
- Recover from accidental file deletion
- Practice advanced recovery techniques

## 🎯 Key Commands You'll Practice

```bash
# Git Reflog
git reflog                    # View all commit history
git reset --hard <commit>     # Recover lost commits

# Git Bisect
git bisect start              # Start bisect session
git bisect bad HEAD           # Mark current commit as bad
git bisect good <commit>      # Mark known good commit
git bisect run java TestCalculator  # Automatically test commits

# Git Reset
git reset --soft HEAD~1       # Undo commit, keep changes staged
git reset --mixed HEAD~1      # Undo commit, keep changes unstaged
git reset --hard HEAD~1       # Undo commit, discard changes

# Git Revert
git revert HEAD               # Create new commit that undoes last commit
git revert <commit>           # Revert specific commit

# Git Tagging
git tag v1.0.0                # Create lightweight tag
git tag -a v1.0.0 -m "Release" # Create annotated tag
git push origin --tags        # Push tags to remote
```

## 📚 What You'll Build

A simple Java Calculator application with methods for:
- Addition
- Subtraction
- Multiplication
- Division (with error handling)
- Power calculation
- Square root (with validation)

## 🎉 Benefits

- **No Python knowledge required** - Uses Java examples
- **Real-world scenarios** - Practical Git recovery techniques
- **Step-by-step guidance** - Interactive exercises
- **Safe practice** - Learn without fear of losing work
- **Professional skills** - Essential for advanced Git workflows

## 🚀 Next Steps

After completing these exercises:
1. Practice these techniques in your real Java projects
2. Use `git reflog` when you lose commits
3. Use `git bisect` to find bugs systematically
4. Use `git revert` for safe undos in shared repositories
5. Use `git tags` for proper release management

## 📖 Additional Resources

- [Git Documentation](https://git-scm.com/doc)
- [GitHub Documentation](https://docs.github.com)
- [Pro Git Book](https://git-scm.com/book)
- [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials)
