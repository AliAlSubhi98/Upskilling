#!/bin/bash

# Linux Level 1: Basic Commands Practice Script
# This script provides hands-on practice with essential Linux commands

echo "🐧 Linux Level 1: Basic Commands Practice"
echo "=========================================="
echo ""

# Create practice directory structure
echo "=== Setting up practice environment ==="
mkdir -p ~/linux-practice/{files,scripts,logs}
cd ~/linux-practice

echo "Practice directory created: $(pwd)"
echo ""

# 1. File System Navigation
echo "=== 1. File System Navigation ==="
echo "Current directory: $(pwd)"
echo "Home directory: $HOME"
echo "Root directory: /"
echo ""

echo "Listing current directory contents:"
ls -la
echo ""

echo "Navigating to files directory:"
cd files
echo "Current directory: $(pwd)"
echo ""

# 2. File Operations
echo "=== 2. File Operations ==="
echo "Creating sample files..."

# Create various file types
touch file1.txt file2.txt file3.txt
echo "Hello World" > hello.txt
echo "This is a test file" > test.txt
echo "Linux is awesome!" > linux.txt

echo "Files created:"
ls -la *.txt
echo ""

# 3. Directory Operations
echo "=== 3. Directory Operations ==="
echo "Creating directories..."
mkdir -p subdir1/subdir2
mkdir -p backup
mkdir -p temp

echo "Directory structure:"
tree . 2>/dev/null || find . -type d | head -10
echo ""

# 4. File Permissions
echo "=== 4. File Permissions ==="
echo "Current file permissions:"
ls -la *.txt
echo ""

echo "Changing permissions..."
chmod 755 hello.txt
chmod 644 test.txt
chmod 600 linux.txt

echo "Updated permissions:"
ls -la *.txt
echo ""

# 5. File Content Operations
echo "=== 5. File Content Operations ==="
echo "Displaying file contents:"
echo "--- hello.txt ---"
cat hello.txt
echo ""

echo "--- test.txt ---"
cat test.txt
echo ""

echo "--- linux.txt ---"
cat linux.txt
echo ""

# 6. Text Processing
echo "=== 6. Text Processing ==="
echo "Searching for 'Linux' in files:"
grep -n "Linux" *.txt
echo ""

echo "Word count for each file:"
wc -w *.txt
echo ""

# 7. File Operations
echo "=== 7. File Operations ==="
echo "Copying files..."
cp hello.txt backup/
cp test.txt backup/

echo "Files in backup directory:"
ls -la backup/
echo ""

echo "Moving files..."
mv linux.txt subdir1/
echo "Files in subdir1:"
ls -la subdir1/
echo ""

# 8. System Information
echo "=== 8. System Information ==="
echo "Current user: $(whoami)"
echo "Current date: $(date)"
echo "System uptime: $(uptime)"
echo ""

echo "Disk usage:"
df -h | head -5
echo ""

echo "Memory usage:"
free -h
echo ""

# 9. Process Information
echo "=== 9. Process Information ==="
echo "Current processes (top 5):"
ps aux | head -6
echo ""

echo "System load:"
uptime
echo ""

# 10. Environment Variables
echo "=== 10. Environment Variables ==="
echo "PATH: $PATH"
echo "HOME: $HOME"
echo "USER: $USER"
echo "SHELL: $SHELL"
echo ""

# 11. Command History
echo "=== 11. Command History ==="
echo "Recent commands (last 5):"
history | tail -5
echo ""

# 12. File Search
echo "=== 12. File Search ==="
echo "Finding all .txt files:"
find . -name "*.txt" -type f
echo ""

echo "Finding files modified in last 5 minutes:"
find . -mmin -5 -type f
echo ""

# 13. Text Editors
echo "=== 13. Text Editors ==="
echo "Creating a simple script..."
cat > scripts/simple-script.sh << 'EOF'
#!/bin/bash
echo "Hello from a simple script!"
echo "Current date: $(date)"
echo "Current user: $(whoami)"
EOF

chmod +x scripts/simple-script.sh
echo "Script created and made executable"
echo ""

# 14. Running the script
echo "=== 14. Running the Script ==="
echo "Executing the script:"
./scripts/simple-script.sh
echo ""

# 15. Cleanup demonstration
echo "=== 15. Cleanup Demonstration ==="
echo "Files before cleanup:"
ls -la
echo ""

echo "Removing temporary files..."
rm -f temp/*
echo "Files after cleanup:"
ls -la
echo ""

echo "Linux Level 1 Practice Complete!"
echo "===================================="
echo ""
echo "What you've learned:"
echo "File system navigation (cd, ls, pwd)"
echo "File operations (touch, cp, mv, rm)"
echo "Directory operations (mkdir, rmdir)"
echo "File permissions (chmod, chown)"
echo "Text processing (cat, grep, wc)"
echo "System information (whoami, date, uptime)"
echo "Process management (ps, top)"
echo "File search (find)"
echo "Basic scripting"
echo ""
echo "Next: Practice these commands manually to reinforce learning!"
echo "Try: man <command> to learn more about any command"
