package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {
    private int id;
    private double x;
    private double y;

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }


    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("x")
    public double getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(double x) {
        this.x = x;
    }

    @JsonProperty("y")
    public double getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
