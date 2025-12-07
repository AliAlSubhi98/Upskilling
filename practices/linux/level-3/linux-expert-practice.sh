#!/bin/bash

# Linux Level 3: Expert System Administration
# This script provides hands-on practice with expert-level Linux administration

echo "🐧 Linux Level 3: Expert System Administration"
echo "=============================================="
echo ""

# Create expert practice environment
echo "=== Setting up expert practice environment ==="
mkdir -p ~/linux-expert/{automation,security,performance,networking}
cd ~/linux-expert

echo "Expert practice directory created: $(pwd)"
echo ""

# 1. Advanced Process Management
echo "=== 1. Advanced Process Management ==="
echo "Process hierarchy:"
pstree -p
echo ""

echo "Process resource usage:"
ps -eo pid,ppid,cmd,%mem,%cpu --sort=-%mem | head -10
echo ""

echo "Process limits:"
ulimit -a
echo ""

# 2. System Performance Tuning
echo "=== 2. System Performance Tuning ==="
echo "CPU frequency scaling:"
cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor 2>/dev/null || echo "CPU frequency info not available"
echo ""

echo "I/O scheduler:"
cat /sys/block/*/queue/scheduler 2>/dev/null | head -3
echo ""

echo "Memory overcommit settings:"
cat /proc/sys/vm/overcommit_memory
echo ""

# 3. Advanced Networking
echo "=== 3. Advanced Networking ==="
echo "Network statistics:"
cat /proc/net/dev
echo ""

echo "TCP connection states:"
ss -s
echo ""

echo "Network buffer sizes:"
cat /proc/sys/net/core/rmem_max
cat /proc/sys/net/core/wmem_max
echo ""

# 4. Security Hardening
echo "=== 4. Security Hardening ==="
echo "SELinux status:"
sestatus 2>/dev/null || echo "SELinux not available"
echo ""

echo "AppArmor status:"
aa-status 2>/dev/null || echo "AppArmor not available"
echo ""

echo "Firewall status:"
if command -v ufw &> /dev/null; then
    ufw status
elif command -v firewall-cmd &> /dev/null; then
    firewall-cmd --state
else
    echo "Firewall management not available"
fi
echo ""

# 5. Advanced File System Operations
echo "=== 5. Advanced File System Operations ==="
echo "File system mount options:"
mount | grep -E "(ext4|xfs|btrfs)"
echo ""

echo "Inode usage by filesystem:"
df -i
echo ""

echo "File system errors:"
dmesg | grep -i "error\|fail" | tail -5
echo ""

# 6. System Resource Monitoring
echo "=== 6. System Resource Monitoring ==="
echo "Creating monitoring script..."
cat > monitoring/system-monitor.sh << 'EOF'
#!/bin/bash
echo "=== System Monitor $(date) ==="
echo "Load Average: $(uptime | awk -F'load average:' '{print $2}')"
echo "Memory Usage: $(free | grep Mem | awk '{printf "%.1f%%", $3/$2 * 100.0}')"
echo "Disk Usage: $(df -h / | awk 'NR==2{print $5}')"
echo "Active Processes: $(ps aux | wc -l)"
echo "Network Connections: $(ss -tuln | wc -l)"
echo "================================"
EOF

chmod +x monitoring/system-monitor.sh
echo "Monitoring script created and executed:"
./monitoring/system-monitor.sh
echo ""

# 7. Advanced Text Processing
echo "=== 7. Advanced Text Processing ==="
echo "Creating complex log analysis..."
cat > logs/complex.log << 'EOF'
2024-01-01 10:00:01 [INFO] User john@example.com logged in from 192.168.1.100
2024-01-01 10:00:02 [DEBUG] Database query executed in 45ms
2024-01-01 10:00:03 [ERROR] Connection timeout to external API
2024-01-01 10:00:04 [WARN] High memory usage detected: 85%
2024-01-01 10:00:05 [INFO] User jane@example.com logged in from 192.168.1.101
2024-01-01 10:00:06 [DEBUG] Cache hit rate: 92%
2024-01-01 10:00:07 [ERROR] Database connection pool exhausted
2024-01-01 10:00:08 [INFO] User admin@example.com logged in from 192.168.1.102
EOF

echo "Log analysis:"
echo "--- User login patterns ---"
grep "logged in" logs/complex.log | awk '{print $6}' | sort | uniq -c
echo ""

echo "--- Error analysis ---"
grep "ERROR" logs/complex.log | awk '{print $4}' | sort | uniq -c
echo ""

echo "--- Performance metrics ---"
grep -o "[0-9]*ms\|[0-9]*%" logs/complex.log
echo ""

# 8. Automation Scripts
echo "=== 8. Automation Scripts ==="
echo "Creating backup automation script..."
cat > automation/backup-automation.sh << 'EOF'
#!/bin/bash
# Advanced backup script with rotation
BACKUP_DIR="/tmp/backups"
RETENTION_DAYS=7
DATE=$(date +%Y%m%d_%H%M%S)

mkdir -p $BACKUP_DIR

# Create backup
tar -czf $BACKUP_DIR/backup_$DATE.tar.gz ~/linux-expert/

# Clean old backups
find $BACKUP_DIR -name "backup_*.tar.gz" -mtime +$RETENTION_DAYS -delete

echo "Backup completed: backup_$DATE.tar.gz"
echo "Backup size: $(du -h $BACKUP_DIR/backup_$DATE.tar.gz | cut -f1)"
EOF

chmod +x automation/backup-automation.sh
echo "Backup automation script created"
echo ""

# 9. System Optimization
echo "=== 9. System Optimization ==="
echo "Kernel parameters:"
sysctl -a | grep -E "(vm\.|net\.)" | head -10
echo ""

echo "System limits:"
cat /etc/security/limits.conf | grep -v "^#" | grep -v "^$" | head -5
echo ""

echo "Swap usage:"
swapon -s
echo ""

# 10. Advanced Process Management
echo "=== 10. Advanced Process Management ==="
echo "Creating process monitoring script..."
cat > monitoring/process-monitor.sh << 'EOF'
#!/bin/bash
echo "=== Process Monitor $(date) ==="
echo "Top CPU consumers:"
ps -eo pid,ppid,cmd,%mem,%cpu --sort=-%cpu | head -6
echo ""
echo "Top memory consumers:"
ps -eo pid,ppid,cmd,%mem,%cpu --sort=-%mem | head -6
echo ""
echo "Process count by user:"
ps -eo user | sort | uniq -c | sort -nr
echo "================================"
EOF

chmod +x monitoring/process-monitor.sh
echo "Process monitoring script created and executed:"
./monitoring/process-monitor.sh
echo ""

# 11. Network Security
echo "=== 11. Network Security ==="
echo "Open ports analysis:"
ss -tuln | awk '{print $1, $5}' | sort | uniq -c
echo ""

echo "Network connections by state:"
ss -s
echo ""

echo "Listening services:"
ss -tuln | grep LISTEN | wc -l
echo ""

# 12. System Health Check
echo "=== 12. System Health Check ==="
echo "Creating comprehensive health check..."
cat > monitoring/health-check.sh << 'EOF'
#!/bin/bash
echo "=== System Health Check $(date) ==="
echo "Uptime: $(uptime)"
echo "Load Average: $(cat /proc/loadavg)"
echo "Memory: $(free -h | grep Mem)"
echo "Disk: $(df -h / | tail -1)"
echo "Processes: $(ps aux | wc -l)"
echo "Network: $(ss -tuln | wc -l)"
echo "================================"
EOF

chmod +x monitoring/health-check.sh
echo "Health check script created and executed:"
./monitoring/health-check.sh
echo ""

# 13. Performance Benchmarking
echo "=== 13. Performance Benchmarking ==="
echo "CPU benchmark (simple):"
time for i in {1..1000}; do echo $i > /dev/null; done
echo ""

echo "Disk I/O test:"
dd if=/dev/zero of=/tmp/testfile bs=1M count=10 2>/dev/null && rm /tmp/testfile
echo ""

# 14. Advanced System Administration
echo "=== 14. Advanced System Administration ==="
echo "System boot time:"
systemd-analyze time 2>/dev/null || echo "Boot analysis not available"
echo ""

echo "Service startup time:"
systemd-analyze blame 2>/dev/null | head -5 || echo "Service analysis not available"
echo ""

# 15. Expert Tips and Tricks
echo "=== 15. Expert Tips and Tricks ==="
echo "Command history analysis:"
history | awk '{print $2}' | sort | uniq -c | sort -nr | head -10
echo ""

echo "Most used directories:"
history | grep "cd " | awk '{print $3}' | sort | uniq -c | sort -nr | head -5
echo ""

echo "System information summary:"
echo "OS: $(uname -s)"
echo "Kernel: $(uname -r)"
echo "Architecture: $(uname -m)"
echo "Hostname: $(hostname)"
echo "Uptime: $(uptime -p)"
echo ""

echo "Linux Level 3 Expert Practice Complete!"
echo "=========================================="
echo ""
echo "What you've mastered:"
echo "Advanced process management and monitoring"
echo "System performance tuning and optimization"
echo "Advanced networking and security"
echo "File system analysis and optimization"
echo "Security hardening and monitoring"
echo "Automation and scripting"
echo "System health monitoring"
echo "Performance benchmarking"
echo "Expert-level troubleshooting"
echo ""
echo "You are now a Linux expert!"
echo "Next: Apply these skills in production environments"
