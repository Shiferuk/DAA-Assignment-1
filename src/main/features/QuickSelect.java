package main.features;

import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random(42);

    public static int select(int[] arr, int num, Metrics metrics) {
        if (arr == null || arr.length == 0 || num < 0 || num >= arr.length) {
            throw new IllegalArgumentException("Invalid input array or index num out of range.");
        }
        metrics.enterRecursion();
        int result = quickSelect(arr, 0, arr.length - 1, num, metrics);
        metrics.exitRecursion();
        return result;
    }

    private static int quickSelect(int[] arr, int low, int high, int num,  Metrics metrics) {
        if (low == high) {
            return arr[low];
        }

        int pivotIdx = low + random.nextInt(high - low + 1);
        swap(arr, low, pivotIdx);

        int[] res_p = QuickSort.partition3Way(arr, low, high, metrics);

        if (num >= res_p[0] && num <= res_p[1]) {
            return arr[num];
        } else if (num < res_p[0]) {
            metrics.enterRecursion();
            int res = quickSelect(arr, low, res_p[0] - 1, num, metrics);
            metrics.exitRecursion();
            return res;
        } else {
            metrics.enterRecursion();
            int res = quickSelect(arr, res_p[1] + 1, high, num, metrics);
            metrics.exitRecursion();
            return res;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}