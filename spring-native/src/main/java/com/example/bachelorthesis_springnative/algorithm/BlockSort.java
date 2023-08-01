package com.example.bachelorthesis_springnative.algorithm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlockSort {
    /**
     * Blocksort
     * @param list the list
     * @param blockSize the size of the blocks
     * @param <E> the type of elements that are in the list
     * @return the sorted list
     */
    public <E extends Comparable<E>> List<E> sort(List<E> list, int blockSize) {
        // Divide list into equal sized blocks
        List<List<E>> blocks = divide(list, blockSize);
        sort(blocks);
        return merge(blocks);
    }

    /**
     * Divides the input array into blocks of size blockSize
     *
     * @param list      the list
     * @param blockSize the size of the blocks
     * @param <E> the type of elements that are in the list
     * @return a list of blocks with size blockSize
     */
    private <E extends Comparable<E>> List<List<E>> divide(List<E> list, int blockSize) {
        List<List<E>> blocks = new ArrayList<>();

        for (int i = 0; i < list.size(); i += blockSize) {
            List<E> block = new ArrayList<>();

            for (int j = i; j < i + blockSize && j < list.size(); j++) {
                block.add(list.get(j));
            }

            blocks.add(block);
        }

        return blocks;
    }

    /**
     * Sorts the elements within each block
     */
    private <E extends Comparable<E>> void sort(List<List<E>> list) {
        for (List<E> es : list) {
            Collections.sort(es);
        }
    }

    /**
     * Merges the blocks into a single sorted list
     *
     * @param blocks the list of blocks
     * @param <E> the type of elements that are in the list
     * @return a sorted list containing the merged blocks elements
     */
    private <E extends Comparable<E>> List<E> merge(List<List<E>> blocks) {
        List<E> result = new ArrayList<>();

        while (!blocks.isEmpty()) {
            int minIndex = indexOfMinimalFirstElement(blocks);

            // Remove the first element (the smallest) and append it to the result list
            result.add(blocks.get(minIndex).remove(0));

            // If the block is empty, remove it from the list of sorted blocks
            if (blocks.get(minIndex).isEmpty()) {
                blocks.remove(minIndex);
            }
        }

        return result;
    }

    /**
     * Returns the smallest elements index in the first block of each sorted block
     *
     * @param blocks the list of blocks
     * @param <E> the type of elements that are in the list
     * @return the index of the smallest element
     */
    private <E extends Comparable<E>> int indexOfMinimalFirstElement(List<List<E>> blocks) {
        int minIndex = 0;

        for (int i = 1; i < blocks.size(); i++) {
            E firstElementOfCurrentBlock = blocks.get(i).get(0);
            E firstElementOfMinBlock = blocks.get(minIndex).get(0);

            if (firstElementOfCurrentBlock.compareTo(firstElementOfMinBlock) < 0) {
                minIndex = i;
            }
        }

        return minIndex;
    }
}