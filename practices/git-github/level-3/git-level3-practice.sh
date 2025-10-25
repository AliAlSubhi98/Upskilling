#!/bin/bash

# Git Level 3: Commit History & Recovery Practice
# This script guides you through advanced Git recovery and history management

echo "🎯 Git Level 3: Commit History & Recovery Practice"
echo "=================================================="
echo ""

# Create a practice repository
echo "📁 Setting up practice repository..."
mkdir -p git-level3-practice
cd git-level3-practice

# Initialize repository
git init
echo "✅ Repository initialized"

# Create initial files and commits
echo ""
echo "📝 Creating initial commits for practice..."
echo "Hello World" > app.py
git add app.py
git commit -m "Initial commit: Hello World app"

echo "def add(a, b):" >> app.py
echo "    return a + b" >> app.py
git add app.py
git commit -m "Add addition function"

echo "def multiply(a, b):" >> app.py
echo "    return a * b" >> app.py
git add app.py
git commit -m "Add multiplication function"

echo "def divide(a, b):" >> app.py
echo "    if b == 0:" >> app.py
echo "        return 'Error: Division by zero'" >> app.py
echo "    return a / b" >> app.py
git add app.py
git commit -m "Add division function with error handling"

echo "def subtract(a, b):" >> app.py
echo "    return a - b" >> app.py
git add app.py
git commit -m "Add subtraction function"

echo "✅ Created 5 commits for practice"
echo ""

# Show current status
echo "📊 Current repository status:"
git log --oneline
echo ""

echo "🎯 EXERCISE 1: Git Reflog - Recover Lost Commits"
echo "================================================"
echo ""
echo "Let's practice recovering lost commits using git reflog:"
echo ""

# Create a branch and make some commits
echo "1. Creating a feature branch with commits..."
git checkout -b feature/calculator-improvements
echo "def power(a, b):" >> app.py
echo "    return a ** b" >> app.py
git add app.py
git commit -m "Add power function"

echo "def sqrt(a):" >> app.py
echo "    import math" >> app.py
echo "    return math.sqrt(a)" >> app.py
git add app.py
git commit -m "Add square root function"

echo "✅ Created feature branch with 2 commits"
echo ""

# Show reflog
echo "2. Viewing reflog to see all commit history:"
git reflog
echo ""

# Simulate losing commits (reset to previous commit)
echo "3. Simulating lost commits (resetting to previous state)..."
git reset --hard HEAD~2
echo "✅ Reset to 2 commits ago (commits 'lost')"
echo ""

echo "4. Current status after reset:"
git log --oneline
echo ""

echo "5. Using reflog to recover lost commits:"
echo "   Look at the reflog above to find the commit hash of the 'lost' commits"
echo "   Then run: git reset --hard <commit-hash>"
echo ""

# Get the commit hash from reflog
LOST_COMMIT=$(git reflog | grep "Add square root function" | awk '{print $1}')
if [ ! -z "$LOST_COMMIT" ]; then
    echo "6. Recovering lost commits using reflog:"
    git reset --hard $LOST_COMMIT
    echo "✅ Successfully recovered lost commits!"
    echo ""
    echo "7. Current status after recovery:"
    git log --oneline
    echo ""
fi

echo "🎯 EXERCISE 2: Git Bisect - Find Bug in History"
echo "==============================================="
echo ""
echo "Let's practice using git bisect to find when a bug was introduced:"
echo ""

# Create a buggy commit
echo "1. Creating a buggy commit..."
echo "# This is a buggy version" >> app.py
echo "def buggy_function():" >> app.py
echo "    return 1 / 0  # This will cause an error" >> app.py
git add app.py
git commit -m "Introduce bug: division by zero"

echo "✅ Created buggy commit"
echo ""

# Create a test script
echo "2. Creating test script to detect the bug..."
cat > test_app.py << 'EOF'
#!/usr/bin/env python3
import sys
import subprocess

def test_app():
    try:
        # Try to run the app and check for errors
        result = subprocess.run(['python3', 'app.py'], 
                             capture_output=True, text=True, timeout=5)
        if result.returncode != 0:
            print("❌ Test failed - bug detected")
            return 1
        else:
            print("✅ Test passed - no bug")
            return 0
    except Exception as e:
        print(f"❌ Test failed with exception: {e}")
        return 1

if __name__ == "__main__":
    sys.exit(test_app())
EOF

chmod +x test_app.py
git add test_app.py
git commit -m "Add test script for bisect"

echo "✅ Created test script"
echo ""

echo "3. Starting git bisect to find the bug:"
echo "   Run these commands manually:"
echo "   git bisect start"
echo "   git bisect bad HEAD  # Current commit is bad"
echo "   git bisect good <commit-hash>  # Find a good commit from earlier"
echo "   git bisect run ./test_app.py  # Automatically test each commit"
echo "   git bisect reset  # Reset when done"
echo ""

echo "🎯 EXERCISE 3: Git Reset - Safe Recovery"
echo "======================================="
echo ""
echo "Let's practice different types of git reset:"
echo ""

echo "1. Soft reset (keeps changes in staging area):"
echo "   git reset --soft HEAD~1"
echo "   # This undoes the last commit but keeps changes staged"
echo ""

echo "2. Mixed reset (keeps changes in working directory):"
echo "   git reset --mixed HEAD~1"
echo "   # This undoes the last commit and unstages changes"
echo ""

echo "3. Hard reset (discards all changes):"
echo "   git reset --hard HEAD~1"
echo "   # This undoes the last commit and discards all changes"
echo "   # ⚠️ WARNING: This is destructive!"
echo ""

echo "4. Reset to specific commit:"
echo "   git reset --hard <commit-hash>"
echo "   # This resets to a specific commit"
echo ""

echo "🎯 EXERCISE 4: Git Revert - Safe Undo"
echo "====================================="
echo ""
echo "Let's practice using git revert to safely undo commits:"
echo ""

echo "1. Revert the last commit:"
echo "   git revert HEAD"
echo "   # This creates a new commit that undoes the last commit"
echo ""

echo "2. Revert a specific commit:"
echo "   git revert <commit-hash>"
echo "   # This creates a new commit that undoes a specific commit"
echo ""

echo "3. Revert multiple commits:"
echo "   git revert <commit1> <commit2> <commit3>"
echo "   # This reverts multiple commits"
echo ""

echo "🎯 EXERCISE 5: Git Tagging - Release Management"
echo "======================================="
echo ""
echo "Let's practice creating and managing release tags:"
echo ""

echo "1. Create lightweight tag:"
echo "   git tag v1.0.0"
echo "   # Creates a simple tag for version 1.0.0"
echo ""

echo "2. Create annotated tag:"
echo "   git tag -a v1.0.0 -m 'Release version 1.0.0'"
echo "   # Creates an annotated tag with message"
echo ""

echo "3. List all tags:"
echo "   git tag"
echo "   # Shows all tags"
echo ""

echo "4. Show tag information:"
echo "   git show v1.0.0"
echo "   # Shows tag details"
echo ""

echo "5. Push tags to remote:"
echo "   git push origin --tags"
echo "   # Pushes all tags to remote repository"
echo ""

echo "6. Delete tag:"
echo "   git tag -d v1.0.0"
echo "   # Deletes local tag"
echo "   git push origin --delete v1.0.0"
echo "   # Deletes remote tag"
echo ""

echo "🎯 EXERCISE 6: Advanced Recovery Techniques"
echo "=========================================="
echo ""
echo "Let's practice advanced recovery techniques:"
echo ""

echo "1. Recover from accidental branch deletion:"
echo "   git reflog"
echo "   git checkout -b <branch-name> <commit-hash>"
echo "   # Recreates a deleted branch"
echo ""

echo "2. Recover from accidental commit deletion:"
echo "   git reflog"
echo "   git cherry-pick <commit-hash>"
echo "   # Applies a deleted commit to current branch"
echo ""

echo "3. Recover from accidental file deletion:"
echo "   git checkout HEAD -- <filename>"
echo "   # Recovers a deleted file from last commit"
echo ""

echo "4. Recover from accidental staging:"
echo "   git reset HEAD <filename>"
echo "   # Unstages a file"
echo ""

echo "5. Recover from accidental commit message:"
echo "   git commit --amend -m 'New commit message'"
echo "   # Changes the last commit message"
echo ""

echo "🎉 Git Level 3 Practice Complete!"
echo "================================="
echo ""
echo "📋 What you've practiced:"
echo "✅ Git reflog for commit recovery"
echo "✅ Git bisect for bug finding"
echo "✅ Git reset for safe recovery"
echo "✅ Git revert for safe undo"
echo "✅ Git tagging for release management"
echo "✅ Advanced recovery techniques"
echo ""
echo "🚀 Next steps:"
echo "- Practice these commands in real projects"
echo "- Use git reflog when you lose commits"
echo "- Use git bisect to find bugs systematically"
echo "- Use git revert for safe undos in shared repositories"
echo "- Use git tags for proper release management"
echo ""
echo "📚 Additional resources:"
echo "- Git documentation: https://git-scm.com/doc"
echo "- GitHub documentation: https://docs.github.com"
echo "- Pro Git book: https://git-scm.com/book"
