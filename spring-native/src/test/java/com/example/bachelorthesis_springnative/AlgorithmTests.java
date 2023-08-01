package com.example.bachelorthesis_springnative;

import com.example.bachelorthesis_springnative.algorithm.BlockSort;
import com.example.bachelorthesis_springnative.algorithm.SelectionSort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AlgorithmTests {
    @Autowired
    DataGeneration dataGeneration;

    @Autowired
    BlockSort blockSort;

    @Autowired
    SelectionSort selectionSort;

    @Test
    void testIfBlockSortIsCorrect() {
        List<Element> unsortedList = Element.fromIntList(dataGeneration.generateTestData(123, 10000));
        List<Element> sortedList = blockSort.sort(unsortedList, (int) (unsortedList.size() * 0.05));

        for (int i = 0; i < unsortedList.size() - 1; i++) {
            assertThat(sortedList.get(i).compareTo(sortedList.get(i + 1)) <= 0).isTrue();
        }
    }

    @Test
    void testIfSelectionSortIsCorrect() {
        List<Integer> unsortedList = dataGeneration.generateTestData(123, 10000);
        List<Integer> sortedList = selectionSort.sort(unsortedList);

        for (int i = 0; i < unsortedList.size() - 1; i++) {
            assertThat(sortedList.get(i).compareTo(sortedList.get(i + 1)) <= 0).isTrue();
        }
    }
}
