package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Element {
    private int id;
    private List<Integer> nodes;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("nodes")
    public List<Integer> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "\n"+"Element{" +
                "id=" + id +
                ", nodes=" + nodes +
                '}';
    }
}
