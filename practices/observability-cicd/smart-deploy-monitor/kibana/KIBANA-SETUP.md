# Kibana Setup Guide

## Index Pattern Configuration

After starting Kibana, you need to create an index pattern to visualize logs.

### Steps to Create Index Pattern:

1. **Access Kibana UI**
   - Open browser: http://localhost:5601
   - Wait for Kibana to fully start (check health status)

2. **Create Index Pattern**
   - Navigate to: **Stack Management** > **Index Patterns** > **Create index pattern**
   - Index pattern name: `smart-deploy-monitor-logs-*`
   - Click **Next step**

3. **Configure Time Field**
   - Select time field: `@timestamp`
   - Click **Create index pattern**

4. **Verify Index Pattern**
   - You should see fields from your JSON logs:
     - `timestamp`
     - `level`
     - `logger`
     - `message`
     - `thread`
     - `application`
     - `environment`
     - `service`

### View Logs in Discover

1. Navigate to: **Discover** in the left menu
2. Select the index pattern: `smart-deploy-monitor-logs-*`
3. You should see logs from your application
4. Use filters to search by:
   - Log level (INFO, WARN, ERROR)
   - Logger name
   - Message content
   - Time range

### Create Visualizations

1. Navigate to: **Visualize** > **Create visualization**
2. Choose visualization type (e.g., Bar chart, Pie chart)
3. Select index pattern: `smart-deploy-monitor-logs-*`
4. Create visualizations for:
   - Log levels distribution
   - Errors over time
   - Top loggers
   - Application activity

