package features;

public class MergeSort {
    private static final int stopOn = 15;

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] arr_2 = new int[arr.length];
        metrics.enterRecursion();
        mergeSort(arr, arr_2, 0, arr.length - 1, metrics);
        metrics.exitRecursion();
    }

    private static void mergeSort(int[] arr, int[] arr_2, int low, int high, Metrics metrics) {
        if (low >= high) {
            InsertionSort.insertionSort(arr, low, high, metrics);
            return;
        }
        int mid = low + (high - low) / 2;
        metrics.enterRecursion();
        mergeSort(arr, arr_2, low, mid, metrics);
        metrics.exitRecursion();
        metrics.enterRecursion();
        mergeSort(arr, arr_2, mid + 1, high, metrics);
        metrics.exitRecursion();
        merge(arr, arr_2, low, mid, high, metrics);
    }

    private static void merge(int[] arr, int[] arr_2, int low, int mid, int high, Metrics metrics) {
        for (int k = low; k <= high; k++) {
            arr_2[k] = arr[k];
        }
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = arr_2[j++];
            } else if (j > high) {
                arr[k] = arr_2[i++];
            } else {
                metrics.incrementComparisons();
                if (arr_2[j] < arr_2[i]) {
                    arr[k] = arr_2[j++];
                } else {
                    arr[k] = arr_2[i++];
                }
            }
        }
    }
}