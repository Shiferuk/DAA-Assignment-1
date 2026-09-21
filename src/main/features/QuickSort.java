package main.features;

import java.util.Random;

public class QuickSort {
    private Random RAND = new Random();

    public void sort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] a, int low, int high) {
        while (low < high) {
            int randomIndex = low + RAND.nextInt(high - low + 1);
            swap(a, low, randomIndex);

            int[] bounds = partition3Way(a, low, high);
            int lt = bounds[0];
            int gt = bounds[1];

            if (lt - low < high - gt) {
                quickSort(a, low, lt - 1);
                low = gt + 1;
            } else {
                quickSort(a, gt + 1, high);
                high = lt - 1;
            }
        }
    }

    private int[] partition3Way(int[] a, int low, int high) {
        int pivot = a[low];
        int lt = low;
        int i = low + 1;
        int gt = high;

        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}