package com.example.bachelorthesis_springnative.algorithm;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionSort {
    /**
     * Selectionsort
     *
     * @param list the list
     * @param <E> the type of elements that are in the list
     */
    public <E extends Comparable<E>> List<E> sort(List<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            E currentElement = list.get(i);

            E smallestRemainingElement = getSmallestElement(list.subList(i + 1, list.size()));

            if(currentElement.compareTo(smallestRemainingElement) >= 0) {
                swap(list, i, list.indexOf(smallestRemainingElement));
            }
        }

        return list;
    }

    /**
     * Returns the smallest element.
     * @param list the list
     * @return the minimum element
     * @param <E> the type of elements that are in the list
     */
    private <E extends Comparable<E>> E getSmallestElement(List<E> list) {
        return list.stream().min(Comparable::compareTo).orElse(null);
    }

    /**
     * Swaps two elements with the given indices of the given list.
     * @param list the list
     * @param indexA the index of the first element
     * @param indexB the index of the second element
     * @param <E> the type of elements that are in the list
     */
    private <E extends Comparable<E>> void swap(List<E> list, int indexA, int indexB) {
        E temp = list.get(indexA);
        list.set(indexA, list.get(indexB));
        list.set(indexB, temp);
    }
}
