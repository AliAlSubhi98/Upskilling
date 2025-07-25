# Redundancy & Disaster Recovery

**Goal:** Build resilient systems with backup, high availability, and automated recovery strategies.

**Current Level:** Level 1

---

## Level 1: Basic Backup
- **Competencies:** Minimum viable protection from data loss.
- **Tools:** `pg_dump`, cron jobs, rsync, simple S3 uploads, any backup tool
- **Checklist:**
  - [ ] Perform basic backup using any tool

## Level 2: Automated Backups & Monitoring
- **Competencies:** Improved reliability and observability.
- **Tools:** pgBackRest, barman, restic, Prometheus + Alertmanager
- **Checklist:**
  - [ ] Scheduled, incremental backups (e.g., WAL archiving for PostgreSQL)
  - [ ] Monitoring backup success/failure (alerts if missed)
  - [ ] Backup encryption and retention policy
  - [ ] Manual restore plan tested occasionally

## Level 3: HA (High Availability)
- **Competencies:** Avoid downtime with minimal data loss (RPO, RTO ~ minutes)
- **Tools:** PostgreSQL replication, Patroni, HAProxy, etcd, Ansible
- **Checklist:**
  - [ ] Hot standby or streaming replication
  - [ ] Automated failover (e.g., Patroni, repmgr)
  - [ ] Load balancer or service discovery for DB connection routing
  - [ ] Configuration and infrastructure as code

## Level 4: Geo-Redundancy & DR Planning
- **Competencies:** Survive full region failures
- **Tools:** Logical replication, pglogical, Citus, DNS failover
- **Checklist:**
  - [ ] Cross-region replication (async or cascaded)
  - [ ] Automated DNS failover (Route53, Cloudflare, etc.)
  - [ ] Regular DR drills (simulate failure + restore)
  - [ ] RTO & RPO defined and measured
  - [ ] App layer aware of region failover

## Level 5: Fully Automated Self-Healing & Multi-Region Active-Active
- **Competencies:** Always-on, resilient, self-healing infrastructure
- **Tools:** Citus, Kubernetes, Vault, Service Mesh, GitOps, Terraform
- **Checklist:**
  - [ ] Multi-region active-active setup (e.g., sharded or conflict-resolved)
  - [ ] Infrastructure self-heals on failure (k8s, operators)
  - [ ] Continuous backup + PITR (Point-in-Time Recovery)
  - [ ] Infrastructure & DB schema versioned (e.g., GitOps)

---

## Resources
- [PostgreSQL High Availability Guide](https://www.postgresql.org/docs/current/high-availability.html)
- [Disaster Recovery Planning by AWS](https://aws.amazon.com/disaster-recovery/)
- [Citus Multi-Node Docs](https://docs.citusdata.com/en/latest/)

## Personal Notes
