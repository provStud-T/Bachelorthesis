package com.example.bachelorthesis_springnative.helper;

import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileWriter;

public class FileHelper {
    /**
     * Writes the given string into a file with the given fileName in directory "results"
     * @param string the string
     * @param fileName the fileName
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void writeToFile(String string, String fileName) {
        try {
            File dir = new File("results");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File("results/" + fileName + ".txt");

            System.out.println("Output file: " + file.getAbsoluteFile());

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(string);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Writes two files based on the given information in the directory "results".
     * The files will contain the information of the given stopWatch.
     * One file will contain the total time, the other will contain the average time per element.
     * <br>
     * FileName: "(Total/TPE) - variant, algorithm, (Async/Sync), others.txt"
     * @param amountOfElements the amount of elements
     * @param stopWatch the stopWatch
     * @param variant the variant (Boot or Native)
     * @param algorithm the algorithm
     * @param async true if async, false if sync
     * @see FileHelper#writeToFile(int, StopWatch, String, String, boolean, String)
     */
    public static void writeToFile(int amountOfElements, StopWatch stopWatch,
                                   String variant, String algorithm, boolean async) {
        writeToFile(amountOfElements, stopWatch, variant, algorithm, async, "");
    }

    /**
     * Writes two files based on the given information in the directory "results".
     * The files will contain the information of the given stopWatch.
     * One file will contain the total time, the other will contain the average time per element.
     * <br>
     * FileName: "(Total/TPE) - variant, algorithm, (Async/Sync), others.txt"
     *
     * @param amountOfElements the amount of elements
     * @param stopWatch the stopWatch
     * @param variant the variant (Boot or Native)
     * @param algorithm the algorithm
     * @param async true if async, false if sync
     * @param others additional information (e.g. the date, to create a separate file)
     * @see FileHelper#writeToFile(int, StopWatch, String, String, boolean)
     */
    public static void writeToFile(int amountOfElements, StopWatch stopWatch,
                                   String variant, String algorithm, boolean async, String others) {
        writeToFile(amountOfElements + " " + stopWatch.getTotalTimeNanos() + "\n",
                "Total - " + variant + ", " + algorithm + ", "
                        + (async ? "Async" : "Sync") + (others.isBlank() ? "" : ", " + others));
        writeToFile(amountOfElements + " " + stopWatch.getTotalTimeNanos() + "\n",
                "TPE - " + variant + ", " + algorithm + ", "
                        + (async ? "Async" : "Sync") + (others.isBlank() ? "" : ", " + others));
    }
}
