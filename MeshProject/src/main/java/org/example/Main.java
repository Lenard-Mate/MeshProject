package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.data.Element;
import org.example.data.ElementWithHeight;
import org.example.data.JsonData;
import org.example.data.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {

            var resource = new File(args[0]);

            JsonData json = objectMapper.readValue(resource, JsonData.class);


            Element[] myElement = json.elements;
            Value[] myValue = json.values;

            writeOutput(processData(myElement, myValue), Long.parseLong(args[1]));

            long end = System.currentTimeMillis();
            System.err.println(end - start + " ms");
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

        return eliminateIllegalLocalMaxima(sortListLocalMaxima(localMaximaArray, myValue), elements, myValue);
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

    public static boolean isNeighbour(Element selectedElement, Element elements) {
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

        combinedElements.sort(Comparator.reverseOrder());


        return combinedElements;
    }

    public static StringBuilder writeOutput(List<ElementWithHeight> elementWithHeights, long limit) {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(System.lineSeparator()).append("  ");
        String delimiter = "," + System.lineSeparator() + "  ";
        var joinedString = elementWithHeights.stream().limit(limit).map(Objects::toString).collect(Collectors.joining(delimiter));
        sb.append(joinedString);
        sb.append(System.lineSeparator()).append(']');
        System.out.println(sb);
        return sb;
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
        List<ElementWithHeight> bannedList = new ArrayList<>();
        int elementSize = combinedElements.size();
        for (int i = 0; i < elementSize; i++) {
            if (!isNeighbourInList(newElementWithHeights, combinedElements.get(i).getElement()) && isNeighbourInBannedList(combinedElements.get(i).getElement(), bannedList)) {
                newElementWithHeights.add(combinedElements.get(i));
                bannedList.addAll(findNeighbour(combinedElements.get(i), combinedElements));

            } else {
                bannedList.add(combinedElements.get(i));
            }
        }

        return newElementWithHeights;
    }

    public static boolean isNeighbourInBannedList(Element selectedElement, List<ElementWithHeight> bannedList) {
        return !isNeighbourInList(bannedList, selectedElement);
    }

    public static List<ElementWithHeight> findNeighbour(ElementWithHeight selectedElement, List<ElementWithHeight> combinedElements) {
        List<ElementWithHeight> allNeighbour = new ArrayList<>();
        for (ElementWithHeight combinedElement : combinedElements) {
            if (isNeighbour(selectedElement.getElement(), combinedElement.getElement())) {
                allNeighbour.add(combinedElement);
            }
        }
        return allNeighbour;
    }


    public static List<ElementWithHeight> findNeighbour(ElementWithHeight selectedElement, Element[] combinedElements, Value[] myValue) {
        List<ElementWithHeight> allNeighbour = new ArrayList<>();
        for (Element combinedElement : combinedElements) {
            if (isNeighbour(selectedElement.getElement(), combinedElement) &&
                    combinedElement.id != selectedElement.getElement().id && (
                    myValue[combinedElement.id].value.compareTo(selectedElement.getHeight()) > 0 || myValue[combinedElement.id].value.compareTo(selectedElement.getHeight()) == 0
            )
            ) {
                ElementWithHeight newCombinedElement = new ElementWithHeight();
                newCombinedElement.setElement(combinedElement);
                newCombinedElement.setHeight(myValue[combinedElement.id].value);
                allNeighbour.add(newCombinedElement);
            }
        }
        return allNeighbour;
    }

    public static boolean checkForId(ElementWithHeight iteratedElement, List<ElementWithHeight> listOfElements) {

        for (ElementWithHeight listOfElement : listOfElements) {
            if (iteratedElement.getElement().id == listOfElement.getElement().id) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkAllNeighbour(ElementWithHeight elementWithHeights, Element[] elements, Value[] myValue) {

        Deque<ElementWithHeight> buffer = new ArrayDeque<>(findNeighbour(elementWithHeights, elements, myValue));
        Set<Integer> checkedElements=new HashSet<>();


        ElementWithHeight current;
        while ((current=buffer.poll())!=null) {
            if(current.getHeight().compareTo(elementWithHeights.getHeight())>0){
                return false;
            }

            checkedElements.add(current.getElement().id);
            for(var neighbour :findNeighbour(current, elements, myValue)){
                if(!checkedElements.contains(neighbour.getElement().id)){
                    buffer.push(neighbour);
                }
            }

        }

//        List<ElementWithHeight> aux = findNeighbour(elementWithHeights, elements, myValue);
//        List<ElementWithHeight> auxDouble;

//        for (int i = 0; i < aux.size(); i++) {
//            auxDouble = findNeighbour(aux.get(i), elements, myValue);
//            for (int j = 0; j < auxDouble.size(); j++) {
//                if (!checkForId(auxDouble.get(j), aux)) {
//                    aux.add(auxDouble.get(j));
//                }
//
//            }
//        }
//
//        for(int i=0;i<aux.size();i++){
//            if(aux.get(i).getHeight().compareTo(elementWithHeights.getHeight())>0){
//                return false;
//            }
//        }

        return true;
    }

    public static List<ElementWithHeight> eliminateIllegalLocalMaxima(List<ElementWithHeight> elementWithHeights, Element[] elements, Value[] myValue) {
        List<ElementWithHeight> newElementWithHeights = new ArrayList<>();
        for (ElementWithHeight elementWithHeight : elementWithHeights) {
            if (checkAllNeighbour(elementWithHeight, elements, myValue)) {
                newElementWithHeights.add(elementWithHeight);
            }
        }

        return newElementWithHeights;
    }

}