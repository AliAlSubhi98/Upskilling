# Prometheus Verification Guide

## Verify Prometheus Scraping

### 1. Check Prometheus Targets

1. **Access Prometheus UI**
   - Open browser: http://localhost:9090
   - Navigate to: **Status** > **Targets**

2. **Verify Targets Status**
   - `prometheus` target should show **UP**
   - `smart-deploy-monitor` target should show **UP**
   - If target shows **DOWN**, check:
     - Application is running and accessible
     - Network connectivity between containers
     - `/actuator/prometheus` endpoint is accessible

### 2. Query Sample Metrics

1. **Navigate to Graph**
   - Go to: **Graph** in Prometheus UI

2. **Test Queries**
   ```
   # Application uptime
   application_uptime_seconds
   
   # HTTP requests total
   http_server_requests_seconds_count
   
   # JVM memory used
   jvm_memory_used_bytes
   
   # System CPU usage
   system_cpu_usage
   ```

3. **Verify Results**
   - Metrics should return values
   - Check time series data is being collected
   - Verify metric labels are present

### 3. Check Metrics Endpoint Directly

```bash
# From host machine
curl http://localhost:8080/actuator/prometheus

# From within Docker network
docker exec smart-deploy-monitor-app curl http://localhost:8080/actuator/prometheus
```

Expected: Should return Prometheus-formatted metrics

### Troubleshooting

**Target is DOWN:**
- Check application logs: `docker logs smart-deploy-monitor-app`
- Verify network: `docker network inspect smart-deploy-monitor_smart-deploy-network`
- Test connectivity: `docker exec smart-deploy-prometheus wget -O- http://smart-deploy-monitor-app:8080/actuator/prometheus`

**No metrics appearing:**
- Verify Spring Boot Actuator is enabled
- Check `/actuator/prometheus` endpoint is accessible
- Review Prometheus configuration file syntax

