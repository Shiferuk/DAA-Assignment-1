package test;

import main.features.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    private Metrics metrics;
    private Random random;

    @BeforeEach
    public void setUp() {
        metrics = new Metrics();
        random = new Random(42);
    }

    @Test
    public void testMergeSortCorrectness() {
        for (int i = 0; i < 100; i++) {
            int n = random.nextInt(500) + 1;
            int[] arr1 = random.ints(n, -1000, 1000).toArray();
            int[] arr2 = arr1.clone();

            MergeSort.sort(arr1, metrics);
            Arrays.sort(arr2);
            assertArrayEquals(arr2, arr1);
        }
    }

    @Test
    public void testMergeSortEdgeCases() {
        int[] empty = new int[0];
        MergeSort.sort(empty, metrics);
        assertEquals(0, empty.length);

        int[] single = new int[]{42};
        MergeSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] duplicates = new int[]{5, 5, 5, 5, 5};
        MergeSort.sort(duplicates, metrics);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, duplicates);
    }

    @Test
    public void testQuickSortCorrectness() {
        for (int i = 0; i < 100; i++) {
            int n = random.nextInt(500) + 1;
            int[] arr1 = random.ints(n, -1000, 1000).toArray();
            int[] arr2 = arr1.clone();

            QuickSort.sort(arr1, metrics);
            Arrays.sort(arr2);
            assertArrayEquals(arr2, arr1);
        }
    }

    @Test
    public void testQuickSortEdgeCases() {
        int[] empty = new int[0];
        QuickSort.sort(empty, metrics);
        assertEquals(0, empty.length);

        int[] single = new int[]{42};
        QuickSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        int[] duplicates = new int[]{7, 7, 7, 7, 7, 7};
        QuickSort.sort(duplicates, metrics);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 7}, duplicates);
    }

    @Test
    public void testQuickSortDepthCheck() {
        int n = 100000;
        int[] sortedArr = new int[n];
        for (int i = 0; i < n; i++) sortedArr[i] = i;

        Metrics m = new Metrics();
        QuickSort.sort(sortedArr, m);

        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(m.getMaxDepth() <= maxAllowedDepth, "Depth " + m.getMaxDepth() + " exceeds " + maxAllowedDepth);
    }

    @Test
    public void testQuickSelectCorrectness() {
        for (int i = 0; i < 100; i++) {
            int n = random.nextInt(500) + 1;
            int[] arr = random.ints(n, -1000, 1000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(n);
            int selected = QuickSelect.select(arr.clone(), k, metrics);
            assertEquals(sorted[k], selected);
        }
    }

    @Test
    public void testQuickSelectInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[0], 0, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, -1, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, 3, metrics));
    }
}
