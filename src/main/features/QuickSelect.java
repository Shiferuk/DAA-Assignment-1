package main.features;

import java.util.Random;

public class QuickSelect {
    private Random RAND = new Random();

    public int select(int[] a, int k) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be empty or null.");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k is out of bounds. Must be between 0 and " + (a.length - 1));
        }

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int randomIndex = low + RAND.nextInt(high - low + 1);
            swap(a, low, randomIndex);

            int[] bounds = partition3Way(a, low, high);
            int lt = bounds[0];
            int gt = bounds[1];

            if (k < lt) {
                high = lt - 1;
            } else if (k > gt) {
                low = gt + 1;
            } else {
                return a[k];
            }
        }

        throw new IllegalArgumentException("Element not found for position k.");
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