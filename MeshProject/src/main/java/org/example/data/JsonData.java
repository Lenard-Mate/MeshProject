package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class JsonData {

    @JsonProperty("elements")
    public Element[] elements;
    @JsonProperty("nodes")
    public Node[] nodes;
    @JsonProperty("values")
    public Value[] values;

    @Override
    public String toString() {
        var result = new StringBuilder();
        result.append("JsonFile{").append(System.lineSeparator());
        result.append("elements = ").append(Arrays.toString(elements)).append(System.lineSeparator());
        result.append("nodes = ").append(Arrays.toString(nodes)).append(System.lineSeparator());
        result.append("values = ").append(Arrays.toString(values)).append(System.lineSeparator());
        result.append("}");
        return result.toString();
    }
}
