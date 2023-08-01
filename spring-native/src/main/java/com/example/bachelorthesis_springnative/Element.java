package com.example.bachelorthesis_springnative;

import java.util.ArrayList;
import java.util.List;

public class Element implements Comparable<Element> {
    /**
     * A value to sort the element by.
     */
    Integer value;

    public Element(int value) {
        this.value = value;
    }

    /**
     * Converts a list of integers into a list of elements.
     * @param list a list of integers
     * @return a list of elements with the given integers
     */
    public static List<Element> fromIntList(List<Integer> list) {
        List<Element> output = new ArrayList<>();

        for (Integer integer : list) {
            output.add(new Element(integer));
        }

        return output;
    }

    /**
     * Compares this object with the Element {@code o} for order.
     * Returns a negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the Element {@code o}.
     */
    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.value, o.value);
    }
}
