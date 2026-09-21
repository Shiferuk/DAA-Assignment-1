package main;

import main.features.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final int[] sizes = {1000, 10000, 100000, 1000000};
    private static final String[] inputTypes = {"random", "sorted", "duplicates"};
    private static final int repeats = 5;

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (int n : sizes) {
                for (String type : inputTypes) {
                    runBenchmark("MergeSort", type, n, writer);
                    runBenchmark("QuickSort", type, n, writer);
                    runBenchmark("QuickSelect", type, n, writer);
                }
            }
            System.out.println("Benchmark completed. Results saved to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(String algo, String inputType, int n, PrintWriter writer) {
        long[] times = new long[repeats];
        long[] comps = new long[repeats];
        int[] depths = new int[repeats];

        Metrics metrics = new Metrics();
        Random rng = new Random(12345);

        for (int r = 0; r < repeats; r++) {
            int[] data = generateInput(n, inputType, rng);
            metrics.reset();

            long startTime = System.nanoTime();
            if (algo.equals("MergeSort")) {
                MergeSort.sort(data, metrics);
            } else if (algo.equals("QuickSort")) {
                QuickSort.sort(data, metrics);
            } else if (algo.equals("QuickSelect")) {
                QuickSelect.select(data, n / 2, metrics);
            }
            long endTime = System.nanoTime();

            times[r] = (endTime - startTime) / 1_000_000; // ms
            comps[r] = metrics.getComparisons();
            depths[r] = metrics.getMaxDepth();
        }

        Arrays.sort(times);
        Arrays.sort(comps);
        Arrays.sort(depths);

        long medianTime = times[repeats / 2];
        long medianComp = comps[repeats / 2];
        int medianDepth = depths[repeats / 2];

        writer.printf("%s,%s,%d,%d,%d,%d%n", algo, inputType, n, medianTime, medianComp, medianDepth);
    }

    private static int[] generateInput(int n, String type, Random rng) {
        int[] arr = new int[n];
        if (type.equals("random")) {
            for (int i = 0; i < n; i++) arr[i] = rng.nextInt();
        } else if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) arr[i] = i;
        } else if (type.equals("duplicates")) {
            for (int i = 0; i < n; i++) arr[i] = rng.nextInt(10);
        }
        return arr;
    }
}
