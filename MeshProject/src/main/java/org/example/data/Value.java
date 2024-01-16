package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {

    private int element_id;
    private double value;


    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }


    @JsonProperty("element_id")
    public int getElementId() {
        return element_id;
    }

    @JsonProperty("element_id")
    public void setElementId(int elementId) {
        this.element_id = elementId;
    }

    @JsonProperty("value")
    public double getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "element_id=" + element_id +
                ", value=" + value +
                '}';
    }
}
