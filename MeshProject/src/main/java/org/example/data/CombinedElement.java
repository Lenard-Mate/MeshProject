package org.example.data;

import java.math.BigDecimal;
import java.util.List;

public class CombinedElement implements Comparable<CombinedElement> {
    Element elements;

    BigDecimal myValues;

    public Element getElements() {
        return elements;
    }

    public void setElements(Element elements) {
        this.elements = elements;
    }

    public BigDecimal getMyValues() {
        return myValues;
    }

    public void setMyValues(BigDecimal myValues) {
        this.myValues = myValues;
    }

    @Override
    public String toString() {
        return "\n" + "CombinedElement{" +
                "elements=" + elements +
                ", myValues=" + myValues +
                '}';
    }

    public CombinedElement() {
    }


    @Override
    public int compareTo(CombinedElement o) {
        return this.myValues.compareTo(o.myValues);
    }
}
