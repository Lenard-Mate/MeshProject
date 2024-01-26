package org.example.testDemo;

import org.example.data.Element;
import org.example.data.ElementWithHeight;
import org.example.data.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.*;


public class Main {

    private Demo subject;

    @BeforeEach
    public void beforeEach() {
        this.subject = new Demo();
    }


    @Test
    public void testIsNeighbourTrue() {

        Element element1 = new Element() ;
        Element element2 = new Element() ;

        element1.id = 0;
        element1.nodes = new int[]{0, 1, 2};

        element2.id = 1;
        element2.nodes = new int[]{1, 2, 4};

        Assertions.assertTrue(isNeighbour(element1, element2));

        element1.id = 2;
        element1.nodes = new int[]{3, 4, 5};

        element2.id = 3;
        element2.nodes = new int[]{7, 4, 12};

        Assertions.assertTrue(isNeighbour(element1, element2));
    }
    @Test
    public void testIsNeighbourFalse() {

        Element element1 = new Element() ;
        Element element2 = new Element() ;

        element1.id = 0;
        element1.nodes = new int[]{0, 1, 2};

        element2.id = 3;
        element2.nodes = new int[]{3, 4, 6};

        Assertions.assertFalse(isNeighbour(element1, element2));
    }

    @Test
    public void isNeighbourInListTrue(){
        List<ElementWithHeight> elementWithHeightList = new ArrayList<>();
        ElementWithHeight elementWithHeight = new ElementWithHeight() ;
        Element element1 = new Element() ;
        ElementWithHeight elementWithHeight2 = new ElementWithHeight() ;
        Element element2 = new Element() ;
        ElementWithHeight elementWithHeight3 = new ElementWithHeight() ;
        Element element3 = new Element() ;
        ElementWithHeight elementWithHeight4= new ElementWithHeight() ;
        Element element4 = new Element() ;
        Element element5 = new Element() ;

        element1.id = 0;
        element1.nodes = new int[]{0, 1, 2};

        element2.id = 3;
        element2.nodes = new int[]{2, 3, 4};
        elementWithHeight.setElement(element2);
        elementWithHeightList.add(elementWithHeight);

        element3.id = 4;
        element3.nodes = new int[]{5, 6, 7};
        elementWithHeight2.setElement(element3);
        elementWithHeightList.add(elementWithHeight2);

        element4.id = 5;
        element4.nodes = new int[]{5, 6, 7};
        elementWithHeight3.setElement(element4);
        elementWithHeightList.add(elementWithHeight3);

        element5.id = 6;
        element5.nodes = new int[]{5, 6, 7};
        elementWithHeight4.setElement(element5);
        elementWithHeightList.add(elementWithHeight4);


        Assertions.assertTrue(isNeighbourInList(elementWithHeightList, element1));
    }
    @Test
    public void isNeighbourInListFalse(){

        List<ElementWithHeight> elementWithHeightList = new ArrayList<>();
        ElementWithHeight elementWithHeight = new ElementWithHeight() ;
        Element element1 = new Element() ;
        ElementWithHeight elementWithHeight2 = new ElementWithHeight() ;
        Element element2 = new Element() ;
        ElementWithHeight elementWithHeight3 = new ElementWithHeight() ;
        Element element3 = new Element() ;
        ElementWithHeight elementWithHeight4= new ElementWithHeight() ;
        Element element4 = new Element() ;
        Element element5 = new Element() ;


        element1.id = 0;
        element1.nodes = new int[]{0, 1, 2};

        element2.id = 3;
        element2.nodes = new int[]{8, 3, 4};
        elementWithHeight.setElement(element2);
        elementWithHeightList.add(elementWithHeight);

        element3.id = 4;
        element3.nodes = new int[]{5, 6, 7};
        elementWithHeight2.setElement(element3);
        elementWithHeightList.add(elementWithHeight2);

        element4.id = 5;
        element4.nodes = new int[]{5, 6, 7};
        elementWithHeight3.setElement(element4);
        elementWithHeightList.add(elementWithHeight3);

        element5.id = 6;
        element5.nodes = new int[]{5, 6, 7};
        elementWithHeight4.setElement(element5);
        elementWithHeightList.add(elementWithHeight4);

        Assertions.assertFalse(isNeighbourInList(elementWithHeightList, element1));
    }

    @Test
    public void writeOutputTest() {
//
//        String correctValue = "[\n" +
//                "  {element_id: 0, value: 10},\n" +
//                "  {element_id: 15, value: 10},\n" +
//                "  {element_id: 26, value: 10}\n" +
//                "]";
//        Element element1 = new Element() ;
//        element1.id = 0;
//        element1.nodes = new int[]{0, 1, 2};
//
//        Element element2 = new Element() ;
//        element1.id = 1;
//        element1.nodes = new int[]{1, 2, 4};
//
//        Element element3 = new Element() ;
//        element1.id = 2;
//        element1.nodes = new int[]{1, 3, 4};
//
//        Element element4 = new Element() ;
//        element1.id = 3;
//        element1.nodes = new int[]{3, 4, 6};
//
//        Element element5 = new Element() ;
//        element1.id = 4;
//        element1.nodes = new int[]{5, 6, 8};
//
//        Element[] myElement = {element1,element2,element3,element4,element5};
//
//        Value value1 = new Value() ;
//        Value value2 = new Value() ;
//        Value value3 = new Value() ;
//        Value value4 = new Value() ;
//        Value value5 = new Value() ;
//
//        value1.element_id=0;
//        value2.element_id=0;
//        value3.element_id=0;
//        value4.element_id=0;
//
//        BigDecimal bigDecimalNumber1 = new BigDecimal("10");
//        BigDecimal bigDecimalNumber2 = new BigDecimal("1");
//
//        value1.value=bigDecimalNumber1;
//        value2.value=bigDecimalNumber2;
//        value3.value=bigDecimalNumber2;
//        value4.value=bigDecimalNumber2;
//
//
//        Value[] myValues = {value1,value2,value3,value4};
//
//        StringBuilder myResult = writeOutput(processData(myElement,myValues),3);
//
//        System.out.println(myResult);
//
//        Assertions.assertEquals(1, 1);

    }
}
