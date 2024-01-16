package org.example.data;

import java.math.BigDecimal;

public class ElementWithHeight implements Comparable<ElementWithHeight> {
   private Element elements;
   private BigDecimal height;

    public Element getElement() {
        return elements;
    }

    public void setElement(Element elements) {
        this.elements = elements;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal myValues) {
        this.height = myValues;
    }

    @Override
    public int compareTo(ElementWithHeight other) {
        return this.height.compareTo(other.height);
    }

    @Override
    public String toString() {
        return "{element_id: "+elements.id+", value: "+height+"}";
    }
}
