# Databases

**Goal:** Design and operate performant, scalable databases.

**Current Level:** Level 1

---

## Level 1: Fundamentals
- **Competencies:** SQL basics, Redis commands, Elasticsearch DSL, Qdrant insert/query
- **Tools:** PostgreSQL, MS SQL, Oracle, Redis, Elasticsearch, Qdrant
- **Checklist:**
  - [ ] Perform CRUD in SQL
  - [ ] Search/filter with Elastic filters vs queries
  - [ ] Insert/search vectors in Qdrant
  - [ ] Use Redis for TTL-based caching

## Level 2: Schema Design
- **Competencies:** Normalization, indexing, analyzers, Redis eviction, Qdrant collection config
- **Tools:** B-tree/GiST/GIN, EXPLAIN ANALYZE, Elastic mappings, Redis data types, Qdrant token filters
- **Checklist:**
  - [ ] Normalize SQL tables and create optimal indexes
  - [ ] Create analyzers in Elastic for multi-language support
  - [ ] Tune Redis eviction policy
  - [ ] Define Qdrant collection schema with metadata fields

## Level 3: Advanced Usage
- **Competencies:** SQL functions, CTEs, triggers, Elastic aggregations/scripts, Redis rate limits, Qdrant optimization
- **Tools:** PL/pgSQL, T-SQL, Elastic Painless scripts, Redis Streams/Lua, Qdrant payload indexing
- **Checklist:**
  - [ ] Use SQL functions and triggers to automate logic
  - [ ] Aggregate and bucket results in Elastic
  - [ ] Implement Redis-based rate limiting
  - [ ] Optimize Qdrant collection with multiple segments and query filters

## Level 4: Operational Architecture
- **Competencies:** Replica design, shard tuning, cache patterns, Qdrant payloads
- **Tools:** PgBouncer, AlwaysOn Ags, Redis caching layers, Qdrant indexed payloads
- **Checklist:**
  - [ ] Route SQL reads to replicas
  - [ ] Tune Elastic shard counts and replicas
  - [ ] Implement Redis caching for different workloads
  - [ ] Filter and score with Qdrant payloads

## Level 5: Distributed DBs
- **Competencies:** Geo-distribution, failover, hybrid search, alerting
- **Tools:** CockroachDB, Cassandra, Redis Sentinel, Prometheus + Grafana, Elastic + Qdrant
- **Checklist:**
  - [ ] Deploy CockroachDB for geo-distributed SQL
  - [ ] Handle key eviction and latency in Redis cluster
  - [ ] Implement hybrid search (vector + keyword)
  - [ ] Set alerts on DB metrics and failover

---

## Resources
- [PostgreSQL Official Docs](https://www.postgresql.org/docs/)
- [Redis Command Reference](https://redis.io/commands/)
- [Elasticsearch Guide](https://www.elastic.co/guide/)
- [Qdrant Docs](https://qdrant.tech/documentation/)

---

## Progress & Evidence

??? note "Level 1: Fundamentals"
    **Status:** Planned  
    **Focus:** SQL basics, Redis commands, Elasticsearch DSL, Qdrant insert/query  
    **Tools:** PostgreSQL, MS SQL, Oracle, Redis, Elasticsearch, Qdrant  
    **Next Task:** Start with basic CRUD operations in SQL

??? note "Level 2: Schema Design"
    **Status:** Planned  
    **Focus:** Normalization, indexing, analyzers, Redis eviction, Qdrant collection config  
    **Tools:** B-tree/GiST/GIN, EXPLAIN ANALYZE, Elastic mappings

??? note "Level 3: Advanced Usage"
    **Status:** Planned  
    **Focus:** SQL functions, CTEs, triggers, Elastic aggregations/scripts

??? note "Level 4: Operational Architecture"
    **Status:** Planned  
    **Focus:** Replica design, shard tuning, cache patterns

??? note "Level 5: Distributed DBs"
    **Status:** Planned  
    **Focus:** Geo-distribution, failover, hybrid search, alerting

---

## Personal Notes
