package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {
    @JsonProperty("id")
    public int id;
    @JsonProperty("x")
    public double x;
    @JsonProperty("y")
    public double y;

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
