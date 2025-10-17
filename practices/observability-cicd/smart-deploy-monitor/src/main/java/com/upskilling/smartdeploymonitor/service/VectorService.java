package com.upskilling.smartdeploymonitor.service;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections.*;
import io.qdrant.client.grpc.Points.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Service for Qdrant vector operations
 * Demonstrates Level 1 Database fundamentals: Qdrant insert/query operations
 */
@Service
public class VectorService {

    @Value("${qdrant.host:localhost}")
    private String qdrantHost;

    @Value("${qdrant.port:6333}")
    private int qdrantPort;

    private static final String COLLECTION_NAME = "user_embeddings";

    /**
     * Initialize Qdrant client
     * @return Qdrant client instance
     */
    private QdrantClient getQdrantClient() {
        return new QdrantClient(QdrantGrpcClient.newBuilder(qdrantHost, qdrantPort, false).build());
    }

    /**
     * Create collection for vector storage
     * @return true if collection created successfully
     */
    public boolean createCollection() {
        try (QdrantClient client = getQdrantClient()) {
            // Check if collection exists
            if (client.collectionExists(CollectionExistsRequest.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .build()).getResult()) {
                return true; // Collection already exists
            }

            // Create collection with vector configuration
            CreateCollection.Builder createRequest = CreateCollection.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .setVectorsConfig(VectorsConfig.newBuilder()
                    .setParams(VectorParams.newBuilder()
                        .setSize(128) // Vector dimension
                        .setDistance(Distance.Cosine)
                        .build())
                    .build())
                .build();

            client.createCollection(createRequest);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating collection: " + e.getMessage());
            return false;
        }
    }

    /**
     * Insert vector into Qdrant
     * @param pointId Point ID
     * @param vector Vector data
     * @param payload Payload data
     * @return true if inserted successfully
     */
    public boolean insertVector(String pointId, List<Float> vector, Map<String, Object> payload) {
        try (QdrantClient client = getQdrantClient()) {
            // Convert payload to Qdrant format
            Map<String, Value> qdrantPayload = new HashMap<>();
            for (Map.Entry<String, Object> entry : payload.entrySet()) {
                qdrantPayload.put(entry.getKey(), 
                    Value.newBuilder().setStringValue(entry.getValue().toString()).build());
            }

            // Create point
            PointStruct point = PointStruct.newBuilder()
                .setId(PointId.newBuilder().setUuid(pointId).build())
                .setVectors(Vectors.newBuilder()
                    .setVector(Vector.newBuilder()
                        .addAllData(vector)
                        .build())
                    .build())
                .putAllPayload(qdrantPayload)
                .build();

            // Insert point
            UpsertPoints.Builder upsertRequest = UpsertPoints.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .addPoints(point)
                .build();

            client.upsertPoints(upsertRequest);
            return true;
        } catch (Exception e) {
            System.err.println("Error inserting vector: " + e.getMessage());
            return false;
        }
    }

    /**
     * Search vectors in Qdrant
     * @param queryVector Query vector
     * @param limit Number of results to return
     * @return List of search results
     */
    public List<Map<String, Object>> searchVectors(List<Float> queryVector, int limit) {
        try (QdrantClient client = getQdrantClient()) {
            // Create search request
            SearchPoints.Builder searchRequest = SearchPoints.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .setVector(queryVector.toArray(new Float[0]))
                .setLimit(limit)
                .setWithPayload(WithPayloadSelector.newBuilder()
                    .setEnable(true)
                    .build())
                .setWithVectors(WithVectorsSelector.newBuilder()
                    .setEnable(false)
                    .build())
                .build();

            // Execute search
            SearchResponse response = client.searchPoints(searchRequest);
            
            // Convert results to Map format
            List<Map<String, Object>> results = new ArrayList<>();
            for (ScoredPoint point : response.getResultList()) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", point.getId().getUuid());
                result.put("score", point.getScore());
                result.put("payload", point.getPayloadMap());
                results.add(result);
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Error searching vectors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Search vectors with filter
     * @param queryVector Query vector
     * @param filter Filter conditions
     * @param limit Number of results to return
     * @return List of filtered search results
     */
    public List<Map<String, Object>> searchVectorsWithFilter(List<Float> queryVector, 
                                                             Map<String, Object> filter, 
                                                             int limit) {
        try (QdrantClient client = getQdrantClient()) {
            // Create filter condition
            Filter.Builder filterBuilder = Filter.newBuilder();
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                filterBuilder.addMust(Condition.newBuilder()
                    .setField(FieldCondition.newBuilder()
                        .setKey(entry.getKey())
                        .setMatch(Match.newBuilder()
                            .setValue(Value.newBuilder()
                                .setStringValue(entry.getValue().toString())
                                .build())
                            .build())
                        .build())
                    .build());
            }

            // Create search request with filter
            SearchPoints.Builder searchRequest = SearchPoints.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .setVector(queryVector.toArray(new Float[0]))
                .setFilter(filterBuilder.build())
                .setLimit(limit)
                .setWithPayload(WithPayloadSelector.newBuilder()
                    .setEnable(true)
                    .build())
                .build();

            // Execute search
            SearchResponse response = client.searchPoints(searchRequest);
            
            // Convert results to Map format
            List<Map<String, Object>> results = new ArrayList<>();
            for (ScoredPoint point : response.getResultList()) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", point.getId().getUuid());
                result.put("score", point.getScore());
                result.put("payload", point.getPayloadMap());
                results.add(result);
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Error searching vectors with filter: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Get collection information
     * @return Collection statistics
     */
    public String getCollectionInfo() {
        try (QdrantClient client = getQdrantClient()) {
            CollectionInfo response = client.getCollection(GetCollectionInfoRequest.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .build());
            
            return String.format("Collection: %s, Points: %d, Status: %s", 
                COLLECTION_NAME, 
                response.getPointsCount(), 
                response.getStatus());
        } catch (Exception e) {
            return "Error getting collection info: " + e.getMessage();
        }
    }

    /**
     * Delete collection
     * @return true if deleted successfully
     */
    public boolean deleteCollection() {
        try (QdrantClient client = getQdrantClient()) {
            client.deleteCollection(DeleteCollectionRequest.newBuilder()
                .setCollectionName(COLLECTION_NAME)
                .build());
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting collection: " + e.getMessage());
            return false;
        }
    }
}