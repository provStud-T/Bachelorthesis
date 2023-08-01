package com.example.bachelorthesis_springnative.algorithm;

import org.springframework.stereotype.Service;
@Service
public class Fibonacci {
    /**
     * Returns the n-th number of the fibonacci sequence
     *
     * @param n n
     */
    public void generate(int n) {
        int prevNumber, currentNumber, nextNumber;
        prevNumber = 0;
        currentNumber = 1;

        for (int i = 2; i < n; ++i) {
            nextNumber = prevNumber + currentNumber;
            prevNumber = currentNumber;
            currentNumber = nextNumber;
        }
    }
}

