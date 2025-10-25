#!/bin/bash

# Git Level 3: Commit History & Recovery Practice (Java Version)
# This script guides you through advanced Git recovery and history management using Java examples

echo "🎯 Git Level 3: Commit History & Recovery Practice (Java)"
echo "========================================================="
echo ""

# Create a practice repository
echo "📁 Setting up Java practice repository..."
mkdir -p git-level3-java-practice
cd git-level3-java-practice

# Initialize repository
git init
echo "✅ Repository initialized"

# Create initial Java files and commits
echo ""
echo "📝 Creating Java application for practice..."
echo ""

# Create a simple Java calculator application
echo "Creating Calculator.java..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
}
EOF

git add Calculator.java
git commit -m "Initial commit: Basic Calculator class"

echo "✅ Created initial Calculator.java"

# Add more methods
echo "Adding multiplication method..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
}
EOF

git add Calculator.java
git commit -m "Add multiplication method"

echo "✅ Added multiplication method"

# Add division method
echo "Adding division method..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        return (double) a / b;
    }
}
EOF

git add Calculator.java
git commit -m "Add division method with error handling"

echo "✅ Added division method"

# Add subtraction method
echo "Adding subtraction method..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        return (double) a / b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
}
EOF

git add Calculator.java
git commit -m "Add subtraction method"

echo "✅ Added subtraction method"

# Show current status
echo ""
echo "📊 Current repository status:"
git log --oneline
echo ""

echo "🎯 EXERCISE 1: Git Reflog - Recover Lost Commits"
echo "================================================"
echo ""
echo "Let's practice recovering lost commits using git reflog:"
echo ""

# Create a branch and make some commits
echo "1. Creating a feature branch with Java improvements..."
git checkout -b feature/advanced-calculator
echo "✅ Created feature branch"

# Add power method
echo "Adding power method..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        return (double) a / b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }
}
EOF

git add Calculator.java
git commit -m "Add power method using Math.pow"

echo "✅ Added power method"

# Add square root method
echo "Adding square root method..."
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        return (double) a / b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }
    
    public static double sqrt(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number!");
        }
        return Math.sqrt(a);
    }
}
EOF

git add Calculator.java
git commit -m "Add square root method with validation"

echo "✅ Added square root method"
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
LOST_COMMIT=$(git reflog | grep "Add square root method" | awk '{print $1}')
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
cat > Calculator.java << 'EOF'
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to Calculator!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }
        return (double) a / b;
    }
    
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }
    
    public static double sqrt(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number!");
        }
        return Math.sqrt(a);
    }
    
    // BUG: This method has a logical error
    public static int buggyMethod() {
        int result = 10 / 0;  // This will cause ArithmeticException
        return result;
    }
}
EOF

git add Calculator.java
git commit -m "Introduce bug: division by zero in buggyMethod"

echo "✅ Created buggy commit"
echo ""

# Create a test script
echo "2. Creating Java test script to detect the bug..."
cat > TestCalculator.java << 'EOF'
public class TestCalculator {
    public static void main(String[] args) {
        try {
            // Try to compile and run the calculator
            ProcessBuilder pb = new ProcessBuilder("javac", "Calculator.java");
            Process compileProcess = pb.start();
            int compileResult = compileProcess.waitFor();
            
            if (compileResult != 0) {
                System.out.println("❌ Compilation failed - bug detected");
                System.exit(1);
            }
            
            // Try to run a simple test
            pb = new ProcessBuilder("java", "Calculator");
            Process runProcess = pb.start();
            int runResult = runProcess.waitFor();
            
            if (runResult != 0) {
                System.out.println("❌ Runtime error - bug detected");
                System.exit(1);
            }
            
            System.out.println("✅ Test passed - no bug");
            System.exit(0);
            
        } catch (Exception e) {
            System.out.println("❌ Test failed with exception: " + e.getMessage());
            System.exit(1);
        }
    }
}
EOF

# Compile the test
javac TestCalculator.java
git add TestCalculator.java
git commit -m "Add Java test script for bisect"

echo "✅ Created Java test script"
echo ""

echo "3. Starting git bisect to find the bug:"
echo "   Run these commands manually:"
echo "   git bisect start"
echo "   git bisect bad HEAD  # Current commit is bad"
echo "   git bisect good <commit-hash>  # Find a good commit from earlier"
echo "   git bisect run java TestCalculator  # Automatically test each commit"
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
echo "=================================="
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
echo "=============================================="
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
echo "- Practice these commands in real Java projects"
echo "- Use git reflog when you lose commits"
echo "- Use git bisect to find bugs systematically"
echo "- Use git revert for safe undos in shared repositories"
echo "- Use git tags for proper release management"
echo ""
echo "📚 Additional resources:"
echo "- Git documentation: https://git-scm.com/doc"
echo "- GitHub documentation: https://docs.github.com"
echo "- Pro Git book: https://git-scm.com/book"
