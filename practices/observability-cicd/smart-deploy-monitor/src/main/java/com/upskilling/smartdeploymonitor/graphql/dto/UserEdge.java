package com.upskilling.smartdeploymonitor.graphql.dto;

import com.upskilling.smartdeploymonitor.entity.User;

public class UserEdge {
    private User node;
    private String cursor;

    public UserEdge() {}

    public UserEdge(User node, String cursor) {
        this.node = node;
        this.cursor = cursor;
    }

    public User getNode() {
        return node;
    }

    public void setNode(User node) {
        this.node = node;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
