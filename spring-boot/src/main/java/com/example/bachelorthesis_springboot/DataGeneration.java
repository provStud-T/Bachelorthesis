package com.example.bachelorthesis_springboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataGeneration {
    /**
     * Returns a list of Integers with the given amount.
     * @param seed the seed
     * @param amount the amount
     * @return a list of Integers with the given amount
     */
    public List<Integer> generateTestData(int seed, int amount) {
        Random random = new Random(seed);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            list.add(random.nextInt());
        }

        return list;
    }
}
