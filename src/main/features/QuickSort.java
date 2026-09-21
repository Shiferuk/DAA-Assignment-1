package main.features;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random(); /*42*/

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        metrics.enterRecursion();
        quickSort(a, 0, a.length - 1, metrics);
        metrics.exitRecursion();
    }

    private static void quickSort(int[] arr, int low, int high, Metrics metrics) {
        while (low < high) {
            int randomIndex = low + random.nextInt(high - low + 1);
            swap(arr, low, randomIndex);

            int[] bounds = partition3Way(arr, low, high, metrics);
            int leftS = bounds[0] - low;
            int rightS = high - bounds[1];

            if (leftS < rightS) {
                if (low < bounds[0] - 1) {
                    metrics.enterRecursion();
                    quickSort(arr, low, leftS - 1, metrics);
                    metrics.exitRecursion();
                }
                low = rightS + 1;
            }
            else {
                if (bounds[1] + 1 < high) {
                    metrics.enterRecursion();
                    quickSort(arr, rightS + 1, high, metrics);
                    metrics.exitRecursion();
                }
                high = bounds[0] - 1;
            }
        }
    }

    public static int[] partition3Way(int[] arr, int low, int high, Metrics metrics) {
        int pivot = arr[low];
        int lt = low;
        int i = low + 1;
        int gt = high;

        while (i <= gt) {
            metrics.incrementComparisons();
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}