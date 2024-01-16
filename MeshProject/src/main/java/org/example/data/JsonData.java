package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class JsonData {

    private Element[] elements;
    private Node[] nodes;
    private Value[] values;

    @JsonProperty("elements")
    public Element[] getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(Element[] elements) {
        this.elements = elements;
    }

    @JsonProperty("nodes")
    public Node[] getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    @JsonProperty("values")
    public Value[] getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(Value[] values) {
        this.values = values;
    }

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
