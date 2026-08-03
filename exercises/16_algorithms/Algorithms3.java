// Algorithms3.java
//
// Merge sort: split the array in half, sort each half (recursively), then
// merge the two sorted halves by repeatedly taking the smaller front element.
// It is much faster than insertion sort for big arrays: n * log(n) steps
// instead of n * n.
//
// Implement `merge` first (two sorted arrays in, one sorted array out), then
// `mergeSort` using it. Do not modify the input array.

// I AM NOT DONE

import javalings.Check;
import java.util.Arrays;

public class Algorithms3 {
    public static void main(String[] args) {
        Check.arrayEquals(new int[]{1, 2, 3, 4, 5, 6}, merge(new int[]{1, 4, 5}, new int[]{2, 3, 6}), "merge two halves");
        Check.arrayEquals(new int[]{1, 2}, merge(new int[]{}, new int[]{1, 2}), "merge with an empty side");
        Check.arrayEquals(new int[]{1, 1, 2}, merge(new int[]{1}, new int[]{1, 2}), "merge with duplicates");

        int[] input = {38, 27, 43, 3, 9, 82, 10};
        int[] sorted = mergeSort(input);
        Check.arrayEquals(new int[]{3, 9, 10, 27, 38, 43, 82}, sorted, "merge sort");
        Check.arrayEquals(new int[]{38, 27, 43, 3, 9, 82, 10}, input, "input untouched");
        Check.arrayEquals(new int[]{7}, mergeSort(new int[]{7}), "one element");

        int[] big = new int[200_000];
        for (int i = 0; i < big.length; i++) big[i] = (i * 7919) % 100_003;
        int[] expected = big.clone();
        Arrays.sort(expected);
        Check.arrayEquals(expected, mergeSort(big), "200k elements");
    }

    static int[] merge(int[] a, int[] b) {
        return a;
    }

    static int[] mergeSort(int[] arr) {
        // Tip: Arrays.copyOfRange(arr, from, to) copies a slice.
        return arr;
    }
}
