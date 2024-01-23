package org.example;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.data.*;


public class Main {


    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

//            var resource = Main.class.getClassLoader().getResource(args[0]);
            // TODO use final version.
            var resource = new File(args[0]);

            JsonData json = objectMapper.readValue(resource, JsonData.class);


            Element[] myElement = json.elements;
            Value[] myValue = json.values;

            writeOutput(processData(myElement, myValue),Long.parseLong(args[1]));


        } catch (IOException e) {

        }


    }


    public static List<ElementWithHeight> processData(Element[] elements, Value[] myValue) {
        List<Element> localMaximaArray = new ArrayList<>();
        Element localMaxima;
        for (Element element : elements) {
            localMaxima = findAllLocalMaxima(element, elements, myValue);
            if (localMaxima != null) {
                localMaximaArray.add(localMaxima);
            }
        }

       return sortListLocalMaxima(localMaximaArray, myValue);
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


    public static List<ElementWithHeight> sortListLocalMaxima(List<Element> localMaximaArray, Value[] myValue) {


        List<ElementWithHeight> combinedElements = new ArrayList<>();

        for (int i = 0; i < localMaximaArray.toArray().length; i++) {
            ElementWithHeight combinedElement = new ElementWithHeight();
            combinedElement.setElement(localMaximaArray.get(i));
            combinedElement.setHeight(myValue[localMaximaArray.get(i).id].value);

            combinedElements.add(combinedElement);
        }

        combinedElements = localMaximaListWithoutMultiplePoints(combinedElements);

        Collections.sort(combinedElements, Comparator.reverseOrder());


      return combinedElements;
    }

    public static void writeOutput(List<ElementWithHeight> elementWithHeights,long limit){
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(System.lineSeparator()).append("  ");
        String delimiter = ","+System.lineSeparator()+"  ";
        var joinedString = elementWithHeights.stream().limit(limit).map(Objects::toString).collect(Collectors.joining(delimiter));
        sb.append(joinedString);
        sb.append(System.lineSeparator()).append(']');
        System.out.println(sb);
    }

    public static Boolean isNeighbourInList(List<ElementWithHeight> listOfElements, Element selectedElement) {
        int elementSize = listOfElements.size();
        for (int i = 0; i < elementSize; i++) {
            if (isNeighbour(listOfElements.get(i).getElement(), selectedElement) && listOfElements.get(i).getElement().id != selectedElement.id) {
                return true;
            }
        }
        return false;
    }

    public static List<ElementWithHeight> localMaximaListWithoutMultiplePoints(List<ElementWithHeight> combinedElements) {
        List<ElementWithHeight> newElementWithHeights = new ArrayList<>();
        int elementSize = combinedElements.size();
        for (int i = 0; i < elementSize; i++) {
            if (!isNeighbourInList(newElementWithHeights, combinedElements.get(i).getElement())) {
                newElementWithHeights.add(combinedElements.get(i));
            }
        }

        return newElementWithHeights;
    }

}