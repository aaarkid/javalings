// Algorithms2.java
//
// Sorting by hand. Java has Arrays.sort, but writing a sort once teaches you
// what it costs.
//
// Insertion sort: walk from left to right. For each element, slide it left
// past every bigger neighbour until it sits in the right place. The part of
// the array left of the current position is always sorted.
//
//     [5, 2, 4, 1]
//     [2, 5, 4, 1]   2 slid past 5
//     [2, 4, 5, 1]   4 slid past 5
//     [1, 2, 4, 5]   1 slid past everything
//
// Sort in place, and return how many times you moved an element one step.

import javalings.Check;

public class Algorithms2 {
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 1};
        int moves = insertionSort(a);
        Check.arrayEquals(new int[]{1, 2, 4, 5}, a, "sorted");
        Check.equals(5, moves, "1 + 1 + 3 moves");

        int[] already = {1, 2, 3};
        Check.equals(0, insertionSort(already), "a sorted array needs no moves");

        int[] rev = {9, 7, 5, 3, 1, -2};
        insertionSort(rev);
        Check.arrayEquals(new int[]{-2, 1, 3, 5, 7, 9}, rev, "reversed input");
        insertionSort(new int[0]);
        System.out.println("  ok: empty array does not crash");
    }

    static int insertionSort(int[] arr) {
        int moves = 0;
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
                moves++;
            }
            arr[j + 1] = current;
        }
        return moves;
    }
}
