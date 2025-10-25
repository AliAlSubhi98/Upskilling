package com.upskilling.smartdeploymonitor.graphql.dto;

import java.util.List;

public class UserConnection {
    private List<UserEdge> edges;
    private PageInfo pageInfo;
    private int totalCount;

    public UserConnection() {}

    public UserConnection(List<UserEdge> edges, PageInfo pageInfo, int totalCount) {
        this.edges = edges;
        this.pageInfo = pageInfo;
        this.totalCount = totalCount;
    }

    public List<UserEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<UserEdge> edges) {
        this.edges = edges;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
