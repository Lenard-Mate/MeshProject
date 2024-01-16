package org.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Value {
    @JsonProperty("element_id")
    public int element_id;
    @JsonProperty("value")
    public BigDecimal value;


    @Override
    public String toString() {
        return "Value{" +
                "element_id=" + element_id +
                ", value=" + value +
                '}';
    }
}
