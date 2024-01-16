package org.example;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.data.*;


public class Main {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        long start = System.currentTimeMillis();

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            var resource = Main.class.getClassLoader().getResource(args[0]);
            // TODO use final version.
//            var resource = new File(args[0]);

            JsonData json = objectMapper.readValue(resource, JsonData.class);


            Element[] myElement = json.elements;
            Node[] myNode = json.nodes;
            Value[] myValue = json.values;

            System.out.println(myValue[12]);
            meshProcess(myElement, myNode, myValue);

            BigDecimal jsonValue = new BigDecimal("15.7431295749031765");
            BigDecimal comparisonValue = new BigDecimal("15.743129574903177");
            int comparisonResult = jsonValue.compareTo(comparisonValue);

            if (comparisonResult > 0) {
                System.out.println("jsonValue is greater than comparisonValue");
            } else if (comparisonResult < 0) {
                System.out.println("jsonValue is less than comparisonValue");
            } else {
                System.out.println("jsonValue is equal to comparisonValue");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }


    public static void meshProcess(Element[] elements, Node[] nodes, Value[] myValue) {
        List<Element> localMaximaArray = new ArrayList<>();
        Element localMaxima;
        for (Element element : elements) {
            localMaxima = findAllLocalMaxima(element, elements, myValue);
            if (localMaxima != null) {
                localMaximaArray.add(localMaxima);
            }
        }

        sortListLocalMaxima(localMaximaArray, myValue);
    }

    public static Element findAllLocalMaxima(Element selectedElement, Element[] elements, Value[] values) {

        for (int i = 0; i < elements.length; i++) {

            if (values[i].value.compareTo(values[selectedElement.id].value) > 0 &&
                    isNeighbour(selectedElement, elements[i])
            ) {
                return null;
            }

        }
        return selectedElement;
    }

    private static boolean isNeighbour(Element selectedElement, Element elements) {
        return elements.nodes[0] == selectedElement.nodes[0] ||
                elements.nodes[0] == selectedElement.nodes[1] ||
                elements.nodes[0] == selectedElement.nodes[2] ||
                elements.nodes[1] == selectedElement.nodes[0] ||
                elements.nodes[1] == selectedElement.nodes[1] ||
                elements.nodes[1] == selectedElement.nodes[2] ||
                elements.nodes[2] == selectedElement.nodes[0] ||
                elements.nodes[2] == selectedElement.nodes[1] ||
                elements.nodes[2] == selectedElement.nodes[2];
    }


    public static void sortListLocalMaxima(List<Element> localMaximaArray, Value[] myValue) {


        List<CombinedElement> combinedElements = new ArrayList<>();

        for (int i = 0; i < localMaximaArray.toArray().length; i++) {
            CombinedElement combinedElement = new CombinedElement();
            combinedElement.setElements(localMaximaArray.get(i));
            combinedElement.setMyValues(myValue[localMaximaArray.get(i).id].value);

            combinedElements.add(combinedElement);
        }

        combinedElements = localMaximaListWithoutMultiplePoints(combinedElements);


        Collections.sort(combinedElements, Comparator.reverseOrder());

        System.out.println("[");
        for (CombinedElement obj : combinedElements) {
            System.out.println("{ element_id: " + obj.getElements().id + ", value: " + obj.getMyValues() + "},");
        }
        System.out.println("]");


    }

    public static Boolean areYouHaveNeighbourInList(List<CombinedElement> listOfElements, Element selectedElement) {
        for (int i = 0; i < listOfElements.toArray().length; i++) {
            if (isNeighbour(listOfElements.get(i).getElements(), selectedElement) && listOfElements.get(i).getElements().id != selectedElement.id) {
                return true;
            }
        }
        return false;
    }

    public static List<CombinedElement> localMaximaListWithoutMultiplePoints(List<CombinedElement> combinedElements) {
        List<CombinedElement> newCombinedElements = new ArrayList<>();

        for (int i = 0; i < combinedElements.toArray().length; i++) {
            if (!areYouHaveNeighbourInList(newCombinedElements, combinedElements.get(i).getElements())) {
                newCombinedElements.add(combinedElements.get(i));
            }
        }

        return newCombinedElements;
    }

}