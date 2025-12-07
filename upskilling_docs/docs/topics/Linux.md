# Linux

**Goal:** Gain proficiency in Linux command-line tools, server management, virtualization, and automation.

**Current Level:** Level 1

---

## Level 1: Linux CLI Basics
- **Competencies:** Navigation, File Management, Permissions, Users & Groups
- **Commands:** `cd`, `ls`, `cp`, `mv`, `rm`, `chmod`, `chown`, `pwd`, `touch`, `cat`, `nano`, `sudo`, `man`
- **Checklist:**
  - [X] Navigate through directories and list files with different options
  - [X] Modify file permissions using chmod and chown
  - [X] Create and manage users with useradd and passwd

## Level 2: Package Management & Basic Scripting
- **Competencies:** Installing & Managing Software, Basic Shell Scripting, WSL
- **Commands:** `apt`, `yum`, `dnf`, `pacman`, `yay`, bash scripting, WSL
- **Checklist:**
  - [X] Install and manage software using package managers
  - [X] Set up and configure WSL on Windows
  - [X] Write a basic Bash script for automating repetitive tasks

## Level 3: Process Management & Networking
- **Competencies:** System Performance, Networking, Server Management
- **Commands:** `top`, `htop`, `ps`, `kill`, `netstat`, `ifconfig`, `systemctl`, `journalctl`, `crontab`
- **Checklist:**
  - [X] Monitor system performance using top/htop
  - [X] Kill or prioritize processes using nice/renice
  - [X] Set up and manage system services using systemctl

## Level 4: Virtualization, Containerization & Security
- **Competencies:** Docker, Firewall, SSH, System Hardening
- **Commands:** VirtualBox, KVM, Docker, SSH, `iptables`, `ufw`, `fail2ban`
- **Checklist:**
  - [ ] Deploy and manage virtual machines using KVM or VirtualBox
  - [ ] Set up and secure an SSH server
  - [ ] Configure firewall rules using iptables or ufw

## Level 5: Advanced System Administration & Automation
- **Competencies:** Kernel Compilation, Infrastructure as Code, Large-Scale Automation
- **Commands:** `systemd`, Ansible, Terraform, CI/CD, Kubernetes, Custom Kernels
- **Checklist:**
  - [ ] Compile and run a custom Linux kernel
  - [ ] Automate server setup using Ansible or Terraform
  - [ ] Optimize a large-scale Linux deployment

---

## Resources
- [Linux Journey](https://linuxjourney.com/)
- [The Linux Command Line Book](https://linuxcommand.org/)
- [DigitalOcean Tutorials](https://www.digitalocean.com/community/tutorials)

---

## Progress & Evidence

??? success "Level 1: CLI Basics (26-10-2025)"
    **Status:** Completed  
    **Focus:** Navigation, permissions, basic file operations  
    **Commands:** `ls`, `cd`, `mkdir`, `chmod`, `chown`, `grep`, `find`  
    **Achievements:**
    - Mastered file system navigation and permissions
    - Created comprehensive practice scripts
    - Demonstrated text processing and file operations
    - Built automation scripts for system monitoring

    ??? tip "Essential Commands with Examples"
        **File Navigation:**
        ```bash
        # List files with details
        ls -la
        # Output: -rw-r--r-- 1 user staff 1024 Oct 26 10:00 file.txt
        
        # Change directory
        cd /home/user/documents
        
        # Show current directory
        pwd
        # Output: /home/user/documents
        ```

        **File Operations:**
        ```bash
        # Create file
        touch newfile.txt
        
        # Copy file
        cp source.txt destination.txt
        
        # Move/rename file
        mv oldname.txt newname.txt
        
        # Remove file
        rm filename.txt
        ```

        **Permissions:**
        ```bash
        # Change permissions
        chmod 755 script.sh
        chmod +x executable.sh
        
        # Change ownership
        chown user:group file.txt
        ```

    ??? tip "Text Processing Commands"
        **Search and Filter:**
        ```bash
        # Search in files
        grep "pattern" file.txt
        # Output: line containing pattern
        
        # Count lines, words, characters
        wc -l file.txt
        # Output: 42 file.txt
        
        # Display file content
        cat file.txt
        head -10 file.txt
        tail -10 file.txt
        ```

        **File Search:**
        ```bash
        # Find files by name
        find . -name "*.txt"
        # Output: ./file1.txt ./file2.txt
        
        # Find files by type
        find . -type f -name "*.log"
        ```

??? success "Level 2: System Administration (26-10-2025)"
    **Status:** Completed  
    **Focus:** Package managers, shell scripting, system monitoring  
    **Commands:** `apt`, `yum`, `systemctl`, `cron`, `top`, `htop`
    **Achievements:**
    - Advanced system monitoring and process management
    - Log analysis and text processing
    - User management and security basics
    - Backup and archiving automation

    ??? tip "System Monitoring Commands"
        **Process Management:**
        ```bash
        # Show running processes
        ps aux
        # Output: USER PID %CPU %MEM VSZ RSS TTY STAT START TIME COMMAND
        
        # Real-time process monitor
        top
        # Output: Interactive process list with CPU/Memory usage
        
        # System load
        uptime
        # Output: 10:30:45 up 5 days, 2:15, 3 users, load average: 0.15, 0.20, 0.25
        ```

        **System Information:**
        ```bash
        # Memory usage
        free -h
        # Output: total used free shared buff/cache available
        # Mem: 7.8Gi 2.1Gi 4.2Gi 123Mi 1.5Gi 5.4Gi
        
        # Disk usage
        df -h
        # Output: Filesystem Size Used Avail Use% Mounted on
        # /dev/sda1 20G 8.5G 11G 45% /
        ```

    ??? tip "Package Management"
        **Ubuntu/Debian (apt):**
        ```bash
        # Update package list
        sudo apt update
        
        # Install package
        sudo apt install nginx
        
        # Remove package
        sudo apt remove nginx
        
        # List installed packages
        dpkg -l | grep nginx
        ```

        **RHEL/CentOS (yum/dnf):**
        ```bash
        # Install package
        sudo yum install nginx
        # or
        sudo dnf install nginx
        
        # Update system
        sudo yum update
        ```

    ??? tip "Service Management"
        **Systemd Services:**
        ```bash
        # Check service status
        systemctl status nginx
        # Output: ● nginx.service - A high performance web server
        
        # Start/stop/restart service
        sudo systemctl start nginx
        sudo systemctl stop nginx
        sudo systemctl restart nginx
        
        # Enable/disable service
        sudo systemctl enable nginx
        sudo systemctl disable nginx
        ```

    ??? tip "Log Management"
        **System Logs:**
        ```bash
        # View system logs
        journalctl -f
        # Output: Real-time log streaming
        
        # View specific service logs
        journalctl -u nginx
        
        # View logs from today
        journalctl --since today
        ```

??? success "Level 3: Advanced Administration (26-10-2025)"
    **Status:** Completed  
    **Focus:** Process management, server management, automation
    **Achievements:**
    - Expert-level system administration
    - Performance tuning and optimization
    - Advanced networking and security
    - Comprehensive automation scripts

    ??? tip "Advanced Process Management"
        **Process Hierarchy:**
        ```bash
        # Show process tree
        pstree -p
        # Output: systemd(1)─┬─systemd-journal(1234)
        #                  ├─systemd-logind(1235)
        #                  └─nginx(1236)─┬─nginx(1237)
        #                               └─nginx(1238)
        
        # Process resource usage
        ps -eo pid,ppid,cmd,%mem,%cpu --sort=-%mem | head -10
        # Output: PID PPID CMD %MEM %CPU
        # 1234 1 nginx 2.5 0.1
        ```

        **Process Control:**
        ```bash
        # Kill process by PID
        kill 1234
        
        # Force kill
        kill -9 1234
        
        # Kill by name
        pkill nginx
        
        # Change process priority
        nice -n 10 command
        renice 10 -p 1234
        ```

    ??? tip "Performance Monitoring"
        **System Performance:**
        ```bash
        # CPU information
        lscpu
        # Output: Architecture: x86_64
        # CPU(s): 4
        # Thread(s) per core: 2
        
        # Memory details
        cat /proc/meminfo | head -10
        # Output: MemTotal: 8388608 kB
        # MemFree: 4294967 kB
        
        # I/O statistics
        iostat -x 1
        # Output: Device r/s w/s rkB/s wkB/s rrqm/s wrqm/s %util
        # sda 0.00 0.00 0.00 0.00 0.00 0.00 0.00
        ```

    ??? tip "Network Administration"
        **Network Configuration:**
        ```bash
        # Show network interfaces
        ip addr show
        # Output: 1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536
        # inet 127.0.0.1/8 scope host lo
        
        # Show routing table
        ip route show
        # Output: default via 192.168.1.1 dev eth0
        # 192.168.1.0/24 dev eth0 proto kernel scope link
        
        # Show network connections
        ss -tuln
        # Output: Netid State Recv-Q Send-Q Local Address:Port
        # tcp LISTEN 0 128 0.0.0.0:22
        ```

    ??? tip "Security Hardening"
        **Firewall Management:**
        ```bash
        # UFW (Ubuntu)
        sudo ufw status
        sudo ufw enable
        sudo ufw allow 22/tcp
        
        # iptables
        sudo iptables -L
        sudo iptables -A INPUT -p tcp --dport 22 -j ACCEPT
        ```

        **Security Monitoring:**
        ```bash
        # Check failed login attempts
        grep "Failed password" /var/log/auth.log
        
        # Check open ports
        nmap localhost
        
        # Check file permissions
        find / -type f -perm 777 2>/dev/null
        ```

    ??? tip "Automation Scripts"
        **Backup Script:**
        ```bash
        #!/bin/bash
        BACKUP_DIR="/backup/$(date +%Y%m%d)"
        mkdir -p $BACKUP_DIR
        tar -czf $BACKUP_DIR/system-backup.tar.gz /etc /var/log
        ```

        **System Health Check:**
        ```bash
        #!/bin/bash
        echo "=== System Health Check ==="
        echo "Load: $(uptime | awk -F'load average:' '{print $2}')"
        echo "Memory: $(free | grep Mem | awk '{printf "%.1f%%", $3/$2 * 100.0}')"
        echo "Disk: $(df -h / | awk 'NR==2{print $5}')"
        ```

    
??? note "Level 4: Virtualization & Security (Planned)"
    **Status:** Planned  
    **Focus:** VMs, containers, security hardening
    **Commands:** VirtualBox, KVM, Docker, SSH, `iptables`, `ufw`, `fail2ban`
    **Checklist:**
    - [ ] Deploy and manage virtual machines using KVM or VirtualBox
    - [ ] Set up and secure an SSH server
    - [ ] Configure firewall rules using iptables or ufw
    - [ ] Implement fail2ban for intrusion prevention
    - [ ] Set up Docker containers and orchestration
    - [ ] Configure SELinux or AppArmor
    - [ ] Implement log monitoring and analysis
    - [ ] Set up automated security scanning

    ??? tip "Virtualization Commands"
        **KVM/QEMU:**
        ```bash
        # List virtual machines
        virsh list --all
        # Output: Id Name State
        # 1 vm1 running
        
        # Create new VM
        virt-install --name vm1 --ram 1024 --disk path=/var/lib/libvirt/images/vm1.qcow2
        
        # Start/stop VM
        virsh start vm1
        virsh shutdown vm1
        ```

        **Docker:**
        ```bash
        # Run container
        docker run -d --name nginx nginx
        
        # List containers
        docker ps -a
        
        # Build image
        docker build -t myapp .
        ```

    ??? tip "Security Hardening"
        **SSH Security:**
        ```bash
        # Edit SSH config
        sudo nano /etc/ssh/sshd_config
        
        # Disable root login
        PermitRootLogin no
        
        # Use key authentication
        PasswordAuthentication no
        
        # Restart SSH
        sudo systemctl restart sshd
        ```

        **Firewall Rules:**
        ```bash
        # UFW basic setup
        sudo ufw default deny incoming
        sudo ufw default allow outgoing
        sudo ufw allow ssh
        sudo ufw enable
        ```

??? note "Level 5: Kernel & Infrastructure (Planned)"
    **Status:** Planned  
    **Focus:** Kernel customization, infrastructure as code
    **Commands:** `systemd`, Ansible, Terraform, CI/CD, Kubernetes, Custom Kernels
    **Checklist:**
    - [ ] Compile and run a custom Linux kernel
    - [ ] Automate server setup using Ansible or Terraform
    - [ ] Optimize a large-scale Linux deployment
    - [ ] Set up Kubernetes cluster
    - [ ] Implement CI/CD pipelines
    - [ ] Configure infrastructure as code
    - [ ] Set up monitoring and alerting
    - [ ] Implement disaster recovery procedures

    ??? tip "Infrastructure as Code"
        **Ansible Playbook:**
        ```yaml
        ---
        - hosts: webservers
          tasks:
            - name: Install nginx
              apt: name=nginx state=present
            - name: Start nginx
              systemd: name=nginx state=started enabled=yes
        ```

        **Terraform Configuration:**
        ```hcl
        resource "aws_instance" "web" {
          ami           = "ami-0c02fb55956c7d316"
          instance_type = "t2.micro"
          
          tags = {
            Name = "WebServer"
          }
        }
        ```

    ??? tip "Kernel Compilation"
        **Custom Kernel:**
        ```bash
        # Download kernel source
        wget https://kernel.org/pub/linux/kernel/v5.x/linux-5.15.tar.xz
        tar -xf linux-5.15.tar.xz
        cd linux-5.15
        
        # Configure kernel
        make menuconfig
        
        # Compile kernel
        make -j$(nproc)
        make modules_install
        make install
        ```

---

## Personal Notes

??? info "Note #1: Level 1 – CLI Basics Mastery (26-10-2025)"
    **Exercise Overview:** Mastered essential Linux command-line operations through comprehensive practice scripts.

    **Skills Demonstrated:**
    ```bash
    # File system navigation
    ls -la                    # List files with details
    cd /path/to/directory     # Change directory
    pwd                       # Show current directory
    
    # File operations
    touch newfile.txt         # Create file
    cp source.txt dest.txt   # Copy file
    mv oldname.txt newname.txt # Move/rename file
    rm filename.txt          # Remove file
    
    # Permissions
    chmod 755 script.sh       # Set permissions
    chown user:group file.txt # Change ownership
    
    # Text processing
    grep "pattern" file.txt   # Search text
    find . -name "*.txt"      # Find files
    cat file.txt             # Display content
    ```

    **What I Did:**
    - Created practice scripts for hands-on learning
    - Got comfortable with file system navigation and permissions
    - Learned text processing and file operations
    - Built automation scripts for system monitoring

??? info "Note #2: Level 2 – System Administration (26-10-2025)"
    **Exercise Overview:** Advanced to system-level administration with package management, service configuration, and monitoring.

    **Skills Demonstrated:**
    ```bash
    # System monitoring
    ps aux                    # Show running processes
    top                       # Real-time process monitor
    free -h                   # Memory usage
    df -h                     # Disk usage
    
    # Package management
    sudo apt update           # Update package list
    sudo apt install nginx    # Install package
    sudo systemctl status nginx # Check service status
    
    # Log management
    journalctl -f             # Follow system logs
    journalctl -u nginx       # Service-specific logs
    
    # User management
    sudo useradd newuser      # Create user
    sudo usermod -aG sudo user # Add to group
    ```

    **What I Did:**
    - Advanced system administration and monitoring
    - Learned log analysis and text processing with grep, awk, sed
    - Got comfortable with user management and security basics
    - Built backup automation and archiving scripts

??? info "Note #3: Level 3 – Advanced Administration (26-10-2025)"
    **Exercise Overview:** Achieved expert-level Linux administration with performance tuning, security hardening, and automation.

    **Skills Demonstrated:**
    ```bash
    # Advanced process management
    pstree -p                 # Process hierarchy
    ps -eo pid,ppid,cmd,%mem,%cpu --sort=-%mem # Resource usage
    kill -9 PID               # Force kill process
    
    # Performance monitoring
    htop                      # Interactive process monitor
    iostat -x 1              # I/O statistics
    vmstat 1                 # Virtual memory stats
    
    # Network administration
    ip addr show             # Network interfaces
    ip route show            # Routing table
    ss -tuln                 # Network connections
    
    # Security hardening
    sudo ufw status          # Firewall status
    sudo ufw enable          # Enable firewall
    sudo fail2ban-client status # Intrusion prevention
    ```

    **What I Did:**
    - Expert-level system administration
    - Learned performance tuning and optimization techniques
    - Got comfortable with advanced networking and security concepts
    - Built comprehensive automation and scripting solutions

    **Real-World Applications:**
    - Created monitoring scripts for system health
    - Implemented security hardening measures
    - Built automated backup solutions
    - Configured advanced networking features

??? info "Note #4: macOS Compatibility Learning (26-10-2025)"
    **Challenge:** Some Linux commands don't work on macOS (Darwin), but concepts are transferable.

    **Adaptations Made:**
    ```bash
    # Linux vs macOS differences
    # Linux: free -h          # macOS: vm_stat
    # Linux: systemctl        # macOS: launchctl
    # Linux: ip addr          # macOS: ifconfig
    # Linux: ss -tuln         # macOS: netstat -an
    ```

    **Key Learning Points:**
    - macOS uses BSD-based commands vs GNU/Linux
    - System service management differs (launchctl vs systemctl)
    - Network tools have different syntax but same concepts
    - File system hierarchy is similar across Unix-like systems

    **Transferable Skills:**
    - Command-line proficiency applies across Unix systems
    - System administration concepts are universal
    - Scripting and automation skills are portable
    - Security and networking principles are consistent

---

## Quick Reference Commands

??? tip "Essential Commands Cheat Sheet"
    **File Operations:**
    ```bash
    ls -la          # List files with details
    cp -r src dest  # Copy directory recursively
    mv old new      # Move/rename file
    rm -rf dir      # Remove directory recursively
    chmod 755 file  # Set permissions
    chown user:group file  # Change ownership
    ```

    **System Information:**
    ```bash
    uname -a        # System information
    uptime          # System uptime and load
    free -h         # Memory usage
    df -h           # Disk usage
    ps aux          # Running processes
    top             # Real-time process monitor
    ```

    **Network Commands:**
    ```bash
    ip addr show    # Network interfaces
    ip route show   # Routing table
    ss -tuln        # Network connections
    ping host       # Test connectivity
    netstat -tuln   # Network statistics
    ```

    **Package Management:**
    ```bash
    # Ubuntu/Debian
    sudo apt update && sudo apt upgrade
    sudo apt install package
    sudo apt remove package
    
    # RHEL/CentOS
    sudo yum update
    sudo yum install package
    sudo yum remove package
    ```

    **Service Management:**
    ```bash
    systemctl status service    # Check status
    systemctl start service      # Start service
    systemctl stop service       # Stop service
    systemctl restart service    # Restart service
    systemctl enable service     # Enable on boot
    systemctl disable service    # Disable on boot
    ```

    **Log Management:**
    ```bash
    journalctl -f               # Follow logs
    journalctl -u service       # Service logs
    journalctl --since today    # Today's logs
    tail -f /var/log/syslog     # Follow syslog
    grep "error" /var/log/*     # Search logs
    ```

    **Text Processing:**
    ```bash
    grep "pattern" file         # Search text
    awk '{print $1}' file      # Extract columns
    sed 's/old/new/g' file     # Replace text
    sort file                  # Sort lines
    uniq file                  # Remove duplicates
    wc -l file                 # Count lines
    ```

    **File Search:**
    ```bash
    find . -name "*.txt"       # Find files by name
    find . -type f -size +100M  # Find large files
    find . -mtime -7           # Find files modified in last 7 days
    locate filename            # Quick file search
    which command              # Find command location
    whereis command            # Find command files
    ```

    **Process Management:**
    ```bash
    ps aux | grep process      # Find process
    kill PID                   # Kill process
    kill -9 PID                # Force kill
    pkill process              # Kill by name
    nice -n 10 command         # Run with priority
    nohup command &            # Run in background
    ```

    **Archive Operations:**
    ```bash
    tar -czf archive.tar.gz files/    # Create archive
    tar -xzf archive.tar.gz           # Extract archive
    tar -tzf archive.tar.gz           # List archive contents
    zip -r archive.zip files/         # Create zip
    unzip archive.zip                  # Extract zip
    ```

    **User Management:**
    ```bash
    useradd username           # Create user
    usermod -aG group user     # Add user to group
    passwd username            # Change password
    su - username              # Switch user
    sudo command               # Run as root
    id username               # Show user info
    ```

    **System Monitoring:**
    ```bash
    htop                      # Interactive process monitor
    iostat -x 1               # I/O statistics
    vmstat 1                  # Virtual memory stats
    sar -u 1                  # CPU utilization
    lsof -i :port             # List open files/ports
    netstat -tuln             # Network connections
    ```

    **Security Commands:**
    ```bash
    sudo ufw status           # Firewall status
    sudo ufw enable           # Enable firewall
    sudo ufw allow 22         # Allow SSH
    sudo fail2ban-client status # Intrusion prevention
    last                      # Login history
    who                       # Current users
    ```

---

## Troubleshooting Guide

??? tip "Common Linux Issues & Solutions"
    **System Won't Boot:**
    ```bash
    # Boot from live USB and chroot
    mount /dev/sda1 /mnt
    mount --bind /dev /mnt/dev
    mount --bind /proc /mnt/proc
    mount --bind /sys /mnt/sys
    chroot /mnt
    ```

    **Disk Space Issues:**
    ```bash
    # Find large files
    find / -type f -size +100M 2>/dev/null
    # Clean package cache
    sudo apt clean
    # Remove old kernels
    sudo apt autoremove
    ```

    **Network Problems:**
    ```bash
    # Reset network
    sudo systemctl restart networking
    # Check DNS
    nslookup google.com
    # Test connectivity
    ping -c 4 8.8.8.8
    ```

    **Service Won't Start:**
    ```bash
    # Check service status
    systemctl status service
    # Check logs
    journalctl -u service
    # Test configuration
    service --test-config
    ```

    **Permission Issues:**
    ```bash
    # Fix ownership
    sudo chown -R user:group /path
    # Fix permissions
    sudo chmod -R 755 /path
    # Check SELinux
    getenforce
    setenforce 0
    ```

---

## Learning Path Recommendations

??? tip "Progressive Learning Path"
    **Beginner (Level 1):**
    1. Master basic file operations
    2. Learn text processing commands
    3. Understand file permissions
    4. Practice with shell scripting basics

    **Intermediate (Level 2):**
    1. System administration tasks
    2. Package management
    3. Service management
    4. Log analysis and monitoring

    **Advanced (Level 3):**
    1. Performance tuning
    2. Security hardening
    3. Network administration
    4. Automation and scripting

    **Expert (Level 4-5):**
    1. Virtualization and containers
    2. Infrastructure as code
    3. Kernel customization
    4. Large-scale deployments

**Practice Recommendations:**
- Set up a home lab with virtual machines
- Practice with different Linux distributions
- Contribute to open source projects
- Build automation scripts for common tasks
- Join Linux communities and forums
