package com.example.bachelorthesis_springnative;

import com.example.bachelorthesis_springnative.algorithm.BlockSort;
import com.example.bachelorthesis_springnative.algorithm.Fibonacci;
import com.example.bachelorthesis_springnative.algorithm.SelectionSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.example.bachelorthesis_springnative.helper.FileHelper.writeToFile;

@Service
public class Algorithm {

    @Autowired
    DataGeneration dataGeneration;

    @Autowired
    SelectionSort selectionSort;

    @Autowired
    BlockSort blockSort;

    @Autowired
    Fibonacci fibonacci;

    /**
     * Sort a pseudorandom list with the given size (nElements) that is generated with the given seed.
     * The block size is set to 1/20th of the given size.
     * @param nElements the amount of elements to sort
     * @param seed the seed
     */
    public void blockSort(int nElements, int seed) {
        System.out.println("Blocksort started");
        StopWatch stopWatch = new StopWatch();

        // Testdatengenerierung
        List<Integer> unsortedIntegerList = dataGeneration.generateTestData(seed, nElements);
        List<Element> unsortedList = Element.fromIntList(unsortedIntegerList);

        // Testausführung
        stopWatch.start();
        blockSort.sort(unsortedList, (int) (unsortedList.size() * 0.05));
        stopWatch.stop();

        writeToFile(nElements, stopWatch, "Boot", "Blocksort", false, "");
    }

    /**
     * Sorts a pseudorandom list with the given size (nElements) that is generated with the given seed.
     * The block size is set to 1/20th of the given size.
     * The algorithm is not running on multiple threads parallel, but every thread gets a separate list to sort.
     * @param nElements the amount of elements to sort
     * @param nThreads the amount of threads
     * @param seed the seed
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void blockSortAsync(int nElements, int nThreads, int seed) {
        System.out.println("BlocksortAsync started");
        StopWatch stopWatch = new StopWatch();

        // Testdatengenerierung
        List<Integer> unsortedIntegerList = dataGeneration.generateTestData(seed, nElements);
        List<Element> unsortedList = Collections.unmodifiableList(Element.fromIntList(unsortedIntegerList));

        List<BlocksortRunnable> workers = new ArrayList<>();
        for(int i = 0; i < nThreads; i++) {
            workers.add(new BlocksortRunnable(blockSort, new ArrayList<>(unsortedList)));
        }

        try{
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);

            stopWatch.start();

            for (int i = 0; i < nThreads; i++) {
                executor.execute(workers.get(i));
            }
            // This will make the executor accept no new threads and finish all existing threads in the queue
            // Note: The threads will still continue "in the background"
            executor.shutdown();
            // Wait until all threads are finished
            executor.awaitTermination(3, TimeUnit.HOURS);

            stopWatch.stop();

            writeToFile(nElements, stopWatch, "Boot", "Blocksort", true, nThreads + " Threads");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts a pseudorandom list with the given size (nElements) that is generated with the given seed.
     * @param nElements the amount of elements to sort
     * @param seed the seed
     */
    public void selectionSort(int nElements, int seed) {
        System.out.println("Start");
        StopWatch stopWatch = new StopWatch();

        // Testdatengenerierung
        List<Integer> unsortedIntegerList = dataGeneration.generateTestData(seed, nElements);
        List<Element> unsortedList = Element.fromIntList(unsortedIntegerList);

        // Testausführung
        stopWatch.start();
        selectionSort.sort(unsortedList);
        stopWatch.stop();

        writeToFile(nElements, stopWatch, "Boot", "Selectionsort", false);
    }

    /**
     * Calculates the n-th fibonacci number.
     * @param n n
     */
    public void fibonacci(int n) {
        System.out.println("Start");
        StopWatch stopWatch = new StopWatch();

        // Testausführung
        stopWatch.start();
        fibonacci.generate(n);
        stopWatch.stop();

        writeToFile(n, stopWatch, "Boot", "Fibonacci", false, "");
        System.exit(0);
    }
}

class BlocksortRunnable implements Runnable {
    BlockSort blocksort;
    List<Element> unsortedList;

    BlocksortRunnable(BlockSort sortingAlgorithm, List<Element> unsortedList) {
        this.blocksort = sortingAlgorithm;
        this.unsortedList = unsortedList;
    }

    @Override
    public void run() {
        try {
            System.out.println("Start sorting ["+ Thread.currentThread().getId() + "]" + System.currentTimeMillis());
            blocksort.sort(unsortedList, (int) (unsortedList.size() * 0.05));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}