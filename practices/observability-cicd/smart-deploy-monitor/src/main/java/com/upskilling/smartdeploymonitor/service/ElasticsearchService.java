package com.upskilling.smartdeploymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ElasticsearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public boolean createIndex(String indexName) {
        try {
            // Create index with mapping
            Map<String, Object> mapping = new HashMap<>();
            Map<String, Object> properties = new HashMap<>();
            
            Map<String, Object> nameField = new HashMap<>();
            nameField.put("type", "text");
            nameField.put("analyzer", "standard");
            properties.put("name", nameField);
            
            Map<String, Object> emailField = new HashMap<>();
            emailField.put("type", "keyword");
            properties.put("email", emailField);
            
            Map<String, Object> ageField = new HashMap<>();
            ageField.put("type", "integer");
            properties.put("age", ageField);
            
            mapping.put("properties", properties);
            
            // Create index
            elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).create();
            return true;
        } catch (Exception e) {
            System.err.println("Error creating Elasticsearch index: " + e.getMessage());
            return false;
        }
    }

    public boolean indexDocument(String indexName, String id, Map<String, Object> document) {
        try {
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(id)
                    .withObject(document)
                    .build();
            
            elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
            return true;
        } catch (Exception e) {
            System.err.println("Error indexing document in Elasticsearch: " + e.getMessage());
            return false;
        }
    }

    public List<Map<String, Object>> searchDocuments(String indexName, String field, String value) {
        try {
            // Simple search using match query
            Map<String, Object> query = new HashMap<>();
            Map<String, Object> match = new HashMap<>();
            match.put(field, value);
            query.put("match", match);
            
            // For now, return empty list as we need to implement proper search
            // This is a simplified version for Level 1 demonstration
            return List.of();
        } catch (Exception e) {
            System.err.println("Error searching in Elasticsearch: " + e.getMessage());
            return List.of();
        }
    }

    public Map<String, Object> getIndexStats(String indexName) {
        Map<String, Object> stats = new HashMap<>();
        try {
            // Simplified stats for Level 1 demonstration
            stats.put("totalDocuments", 0);
            stats.put("status", "operational");
            stats.put("indexName", indexName);
        } catch (Exception e) {
            stats.put("status", "error");
            stats.put("message", "Failed to get Elasticsearch stats: " + e.getMessage());
        }
        return stats;
    }
}