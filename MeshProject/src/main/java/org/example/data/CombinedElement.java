package org.example.data;

import java.util.List;

public class CombinedElement {
    Element elements;

    Double  myValues;

    public Element getElements() {
        return elements;
    }

    public void setElements(Element elements) {
        this.elements = elements;
    }

    public Double getMyValues() {
        return myValues;
    }

    public void setMyValues(Double myValues) {
        this.myValues = myValues;
    }

    @Override
    public String toString() {
        return "\n"+"CombinedElement{" +
                "elements=" + elements +
                ", myValues=" + myValues +
                '}';
    }

    public CombinedElement() {
    }
}
