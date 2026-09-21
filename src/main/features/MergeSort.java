package main.features;

import java.util.Arrays;

public class MergeSort {
    private int cutoff = 15;

    public void sort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        int[] buffer = new int[a.length];
        mergeSort(a, buffer, 0, a.length - 1);
    }

    private void mergeSort(int[] a, int[] buffer, int low, int high) {
        if (high - low + 1 <= cutoff) {
            insertionSort(a, low, high);
            return;
        }

        int mid = low + (high - low) / 2;
        mergeSort(a, buffer, low, mid);
        mergeSort(a, buffer, mid + 1, high);

        if (a[mid] <= a[mid + 1]) {
            return;
        }

        merge(a, buffer, low, mid, high);
    }

    private void merge(int[] a, int[] buffer, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            buffer[k] = a[k];
        }

        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = buffer[j++];
            } else if (j > high) {
                a[k] = buffer[i++];
            } else if (buffer[j] < buffer[i]) {
                a[k] = buffer[j++];
            } else {
                a[k] = buffer[i++];
            }
        }
    }

    private void insertionSort(int[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int temp = a[i];
            int j = i - 1;
            while (j >= low && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }
}