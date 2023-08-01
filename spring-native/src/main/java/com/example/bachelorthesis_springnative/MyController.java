package com.example.bachelorthesis_springnative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {
    @Autowired
    Algorithm algorithm;

    @GetMapping("/selectionsort")
    public void selectionSort(@RequestParam int amount, @RequestParam int seed) {
        algorithm.selectionSort(amount, seed);
    }

    @GetMapping("/blocksort")
    public void blockSort(@RequestParam int amount, @RequestParam int seed) {
        algorithm.blockSort(amount, seed);
    }

    @GetMapping("/blocksort-async")
    public void blockSortAsync(@RequestParam int amount, @RequestParam int threadAmount, @RequestParam int seed) {
        algorithm.blockSortAsync(amount, threadAmount, seed);
    }

    @GetMapping("/fibonacci")
    public void fibonacci(@RequestParam int n) {
        algorithm.fibonacci(n);
    }
}
