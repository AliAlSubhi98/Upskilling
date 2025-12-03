#!/bin/bash

# Linux Level 2: Advanced System Administration
# This script provides hands-on practice with advanced Linux commands and system administration

echo "🐧 Linux Level 2: Advanced System Administration"
echo "================================================="
echo ""

# Create practice environment
echo "=== Setting up advanced practice environment ==="
mkdir -p ~/linux-advanced/{services,logs,scripts,monitoring}
cd ~/linux-advanced

echo "Advanced practice directory created: $(pwd)"
echo ""

# 1. Package Management
echo "=== 1. Package Management ==="
echo "Checking system package manager..."

if command -v apt &> /dev/null; then
    echo "Using APT (Debian/Ubuntu)"
    echo "Installed packages count: $(dpkg -l | wc -l)"
    echo "Available updates: $(apt list --upgradable 2>/dev/null | wc -l)"
elif command -v yum &> /dev/null; then
    echo "Using YUM (RHEL/CentOS)"
    echo "Installed packages count: $(rpm -qa | wc -l)"
elif command -v pacman &> /dev/null; then
    echo "Using Pacman (Arch)"
    echo "Installed packages count: $(pacman -Q | wc -l)"
else
    echo "Package manager not detected or using different system"
fi
echo ""

# 2. System Services
echo "=== 2. System Services ==="
echo "Checking system services..."

if command -v systemctl &> /dev/null; then
    echo "Active services:"
    systemctl list-units --type=service --state=active | head -10
    echo ""
    
    echo "Failed services:"
    systemctl list-units --type=service --state=failed
    echo ""
    
    echo "System status:"
    systemctl status | head -5
else
    echo "systemctl not available, checking with service command..."
    service --status-all | head -10
fi
echo ""

# 3. Process Management
echo "=== 3. Process Management ==="
echo "System processes (top 10 by CPU):"
ps aux --sort=-%cpu | head -11
echo ""

echo "System processes (top 10 by memory):"
ps aux --sort=-%mem | head -11
echo ""

echo "Process tree:"
pstree | head -10
echo ""

# 4. System Monitoring
echo "=== 4. System Monitoring ==="
echo "System load average:"
uptime
echo ""

echo "Memory usage:"
free -h
echo ""

echo "Disk usage:"
df -h
echo ""

echo "Network interfaces:"
ip addr show | grep -E "inet |UP" | head -10
echo ""

# 5. Log Management
echo "=== 5. Log Management ==="
echo "Recent system logs:"
if command -v journalctl &> /dev/null; then
    journalctl --no-pager -n 5
else
    echo "Checking /var/log/syslog:"
    tail -5 /var/log/syslog 2>/dev/null || echo "Syslog not accessible"
fi
echo ""

# 6. Cron Jobs
echo "=== 6. Cron Jobs ==="
echo "Current user's cron jobs:"
crontab -l 2>/dev/null || echo "No cron jobs for current user"
echo ""

echo "System cron jobs:"
ls -la /etc/cron.* 2>/dev/null | head -5
echo ""

# 7. User Management
echo "=== 7. User Management ==="
echo "Current user info:"
id
echo ""

echo "All users:"
cut -d: -f1 /etc/passwd | head -10
echo ""

echo "Groups:"
groups
echo ""

# 8. Network Configuration
echo "=== 8. Network Configuration ==="
echo "Network interfaces:"
ip link show
echo ""

echo "Routing table:"
ip route show
echo ""

echo "Active connections:"
ss -tuln | head -10
echo ""

# 9. File System Analysis
echo "=== 9. File System Analysis ==="
echo "File system types:"
df -T
echo ""

echo "Inode usage:"
df -i
echo ""

echo "Largest directories:"
du -h / 2>/dev/null | sort -hr | head -10
echo ""

# 10. Advanced File Operations
echo "=== 10. Advanced File Operations ==="
echo "Creating test files for advanced operations..."
mkdir -p test-files
cd test-files

# Create files with different timestamps
touch -t 202401010000 old-file.txt
touch -t 202412010000 new-file.txt
echo "Test content" > content-file.txt

echo "Files by modification time:"
ls -lt
echo ""

echo "Files by size:"
ls -lS
echo ""

echo "Finding files by type:"
find . -type f -name "*.txt"
echo ""

# 11. Text Processing Advanced
echo "=== 11. Advanced Text Processing ==="
echo "Creating sample log file..."
cat > ../logs/sample.log << 'EOF'
2024-01-01 10:00:01 INFO Application started
2024-01-01 10:00:02 DEBUG Loading configuration
2024-01-01 10:00:03 ERROR Database connection failed
2024-01-01 10:00:04 WARN Retrying connection
2024-01-01 10:00:05 INFO Connection established
2024-01-01 10:00:06 DEBUG User authentication
2024-01-01 10:00:07 INFO User logged in
EOF

echo "Log file created. Processing..."
cd ../logs

echo "Searching for ERROR messages:"
grep "ERROR" sample.log
echo ""

echo "Counting log levels:"
grep -o "INFO\|DEBUG\|ERROR\|WARN" sample.log | sort | uniq -c
echo ""

echo "Extracting timestamps:"
grep -o "[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\} [0-9]\{2\}:[0-9]\{2\}:[0-9]\{2\}" sample.log
echo ""

# 12. System Performance
echo "=== 12. System Performance ==="
echo "CPU information:"
lscpu | head -10
echo ""

echo "Memory details:"
cat /proc/meminfo | head -10
echo ""

echo "Load average details:"
cat /proc/loadavg
echo ""

# 13. Security Basics
echo "=== 13. Security Basics ==="
echo "Login attempts:"
last | head -5
echo ""

echo "Failed login attempts:"
grep "Failed password" /var/log/auth.log 2>/dev/null | tail -3 || echo "Auth log not accessible"
echo ""

echo "Open ports:"
ss -tuln | grep LISTEN
echo ""

# 14. Backup and Archive
echo "=== 14. Backup and Archive ==="
echo "Creating backup of practice files..."
cd ~/linux-advanced
tar -czf backup-$(date +%Y%m%d).tar.gz files/ scripts/ logs/ 2>/dev/null || echo "Backup creation skipped"

echo "Archive created:"
ls -la *.tar.gz 2>/dev/null || echo "No archives found"
echo ""

# 15. System Information
echo "=== 15. System Information ==="
echo "OS Information:"
uname -a
echo ""

echo "Kernel version:"
uname -r
echo ""

echo "System architecture:"
uname -m
echo ""

echo "Hostname:"
hostname
echo ""

echo "Linux Level 2 Practice Complete!"
echo "==================================="
echo ""
echo "What you've learned:"
echo "Package management (apt, yum, pacman)"
echo "System services (systemctl, service)"
echo "Process management (ps, top, htop)"
echo "System monitoring (uptime, free, df)"
echo "Log management (journalctl, tail)"
echo "Cron jobs (crontab)"
echo "User management (id, groups)"
echo "Network configuration (ip, ss)"
echo "File system analysis (du, df)"
echo "Advanced text processing (grep, awk, sed)"
echo "System performance monitoring"
echo "Security basics (last, auth logs)"
echo "Backup and archiving (tar)"
echo ""
echo "Next: Practice these commands in real scenarios!"
echo "Try: systemctl status <service> to check specific services"
