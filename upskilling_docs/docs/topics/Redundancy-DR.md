# Redundancy & DR

**Goal:** Ensure system reliability and disaster recovery.

**Current Level:** Level 1

---

## Level 1: Backup Basics
- **Competencies:** Manual backups, basic restore, local redundancy
- **Tools:** rsync, tar, cloud storage (Google Drive, Dropbox)
- **Checklist:**
  - [ ] Perform a manual backup of important data
  - [ ] Restore data from a backup
  - [ ] Use local redundancy (RAID1, mirrored disks)

## Level 2: Automated Backups & Monitoring
- **Competencies:** Scheduled backups, backup monitoring, offsite storage
- **Tools:** cron, backup scripts, AWS S3, backup monitoring tools
- **Checklist:**
  - [ ] Schedule automated backups
  - [ ] Monitor backup success/failure
  - [ ] Store backups offsite or in the cloud

## Level 3: Disaster Recovery Planning
- **Competencies:** DR plans, RTO/RPO, failover testing
- **Tools:** DR runbooks, failover scripts, cloud DR tools
- **Checklist:**
  - [ ] Create a disaster recovery plan
  - [ ] Define RTO (Recovery Time Objective) and RPO (Recovery Point Objective)
  - [ ] Test failover and recovery procedures

## Level 4: High Availability & Replication
- **Competencies:** Active/passive, active/active, replication, load balancing
- **Tools:** HAProxy, database replication, cloud load balancers
- **Checklist:**
  - [ ] Set up high availability for a service
  - [ ] Implement database replication
  - [ ] Use load balancing for redundancy

## Level 5: Enterprise Resilience
- **Competencies:** Multi-region, chaos engineering, automated DR
- **Tools:** Chaos Monkey, multi-region cloud, automated failover
- **Checklist:**
  - [ ] Design for multi-region redundancy
  - [ ] Practice chaos engineering to test resilience
  - [ ] Automate disaster recovery processes

---

## Resources
- [AWS Disaster Recovery Whitepaper](https://docs.aws.amazon.com/whitepapers/latest/disaster-recovery-workloads/welcome.html)
- [Google SRE Book - Chapter 12: Disaster Recovery](https://sre.google/sre-book/disaster-recovery/)
- [Backblaze Blog: Backup Strategies](https://www.backblaze.com/blog/the-3-2-1-backup-strategy/)

## Personal Notes