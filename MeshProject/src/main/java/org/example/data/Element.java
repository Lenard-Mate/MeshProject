package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Element {

    @JsonProperty("id")
    public int id;
    @JsonProperty("nodes")
    public int[] nodes;


    @Override
    public String toString() {
        return "\n" + "Element{" +
                "id=" + id +
                ", nodes=" + Arrays.toString(nodes) +
                '}';
    }
}
