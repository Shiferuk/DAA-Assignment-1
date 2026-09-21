package main.features;

public class InsertionSort {
    public static void insertionSort(int[] arr, int low, int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= low) {
                metrics.incrementComparisons();
                if (arr[j] > temp) {
                    arr[j + 1] = arr[1];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }
}