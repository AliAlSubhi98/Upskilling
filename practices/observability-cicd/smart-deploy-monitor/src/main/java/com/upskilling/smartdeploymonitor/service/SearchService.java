package com.upskilling.smartdeploymonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for Elasticsearch search and filter operations
 * Demonstrates Level 1 Database fundamentals: Elasticsearch DSL, filters vs queries
 */
@Service
public class SearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * Search users with Elasticsearch filters
     * Filters are used for exact matches and are faster than queries
     * @param email Email to filter by
     * @param isActive Active status filter
     * @return List of matching users
     */
    public List<Map<String, Object>> searchUsersWithFilters(String email, Boolean isActive) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        
        // Add filters (exact matches, cached, faster)
        if (email != null && !email.isEmpty()) {
            boolQuery.filter(QueryBuilders.termQuery("email.keyword", email));
        }
        
        if (isActive != null) {
            boolQuery.filter(QueryBuilders.termQuery("isActive", isActive));
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(boolQuery)
            .build();
        
        SearchHits<Map> searchHits = elasticsearchOperations.search(searchQuery, Map.class);
        return searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    /**
     * Search users with Elasticsearch queries
     * Queries are used for relevance scoring and full-text search
     * @param searchText Text to search for
     * @return List of matching users with relevance scores
     */
    public List<Map<String, Object>> searchUsersWithQueries(String searchText) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        
        // Add queries (relevance scoring, full-text search)
        if (searchText != null && !searchText.isEmpty()) {
            boolQuery.should(QueryBuilders.matchQuery("email", searchText).boost(2.0f));
            boolQuery.should(QueryBuilders.matchQuery("firstName", searchText).boost(1.5f));
            boolQuery.should(QueryBuilders.matchQuery("lastName", searchText).boost(1.5f));
            boolQuery.minimumShouldMatch(1);
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(boolQuery)
            .withSorts(SortBuilders.scoreSort().order(SortOrder.DESC))
            .build();
        
        SearchHits<Map> searchHits = elasticsearchOperations.search(searchQuery, Map.class);
        return searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    /**
     * Range query for date filtering
     * @param startDate Start date for range
     * @param endDate End date for range
     * @return List of users created in date range
     */
    public List<Map<String, Object>> searchUsersByDateRange(String startDate, String endDate) {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("createdAt")
            .gte(startDate)
            .lte(endDate);
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(rangeQuery)
            .withSorts(SortBuilders.fieldSort("createdAt").order(SortOrder.DESC))
            .build();
        
        SearchHits<Map> searchHits = elasticsearchOperations.search(searchQuery, Map.class);
        return searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    /**
     * Combined filters and queries
     * Demonstrates the difference between filters (exact, cached) and queries (relevance)
     * @param searchText Text to search for (query)
     * @param role Role to filter by (filter)
     * @param isActive Active status filter
     * @return List of matching users
     */
    public List<Map<String, Object>> searchUsersCombined(String searchText, String role, Boolean isActive) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        
        // Add filters (exact matches, cached)
        if (role != null && !role.isEmpty()) {
            boolQuery.filter(QueryBuilders.termQuery("role.keyword", role));
        }
        
        if (isActive != null) {
            boolQuery.filter(QueryBuilders.termQuery("isActive", isActive));
        }
        
        // Add queries (relevance scoring)
        if (searchText != null && !searchText.isEmpty()) {
            boolQuery.must(QueryBuilders.multiMatchQuery(searchText, "email", "firstName", "lastName"));
        }
        
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(boolQuery)
            .withSorts(SortBuilders.scoreSort().order(SortOrder.DESC))
            .build();
        
        SearchHits<Map> searchHits = elasticsearchOperations.search(searchQuery, Map.class);
        return searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }

    /**
     * Get search statistics
     * @return Search service statistics
     */
    public String getSearchStats() {
        return String.format("Elasticsearch service active. Connection: %s", 
            elasticsearchOperations.getElasticsearchClient() != null ? "Connected" : "Disconnected");
    }
}