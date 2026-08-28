// Algorithms1.java
//
// From here on the language is familiar and the exercises are about thinking.
//
// Searching a sorted array. `linearSearch` looks at every element: for a
// million elements that is up to a million steps. `binarySearch` looks at
// the middle, decides which half the value must be in, and repeats: about 20
// steps for a million elements.
//
// Both return the index of the value, or -1 if it is not there.
// Implement binarySearch with a loop. Keep two indexes, lo and hi, that
// fence in the part of the array that could still contain the value.

import javalings.Check;

public class Algorithms1 {
    public static void main(String[] args) {
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        Check.equals(5, linearSearch(sorted, 23), "linear search finds 23");
        Check.equals(5, binarySearch(sorted, 23), "binary search finds 23");
        Check.equals(0, binarySearch(sorted, 2), "first element");
        Check.equals(9, binarySearch(sorted, 91), "last element");
        Check.equals(-1, binarySearch(sorted, 7), "7 is not there");
        Check.equals(-1, binarySearch(new int[0], 7), "empty array");

        int[] big = new int[1_000_000];
        for (int i = 0; i < big.length; i++) big[i] = i * 2;
        steps = 0;
        Check.equals(388_888, binarySearch(big, 777_776), "finds a value in a million elements");
        Check.isTrue(steps >= 1 && steps <= 25, "binary search on a million elements took " + steps + " steps (must be between 1 and 25)");

        long start = System.nanoTime();
        for (int i = 0; i < 200_000; i++) {
            binarySearch(big, (i * 7) % big.length * 2);
        }
        long millis = (System.nanoTime() - start) / 1_000_000;
        Check.isTrue(millis < 2000, "200k lookups took " + millis + " ms (must be under 2000, linear search takes minutes)");
    }

    static int steps = 0;

    static int linearSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    static int binarySearch(int[] arr, int value) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            steps++;
            int mid = (lo + hi) / 2;
            if (arr[mid] == value) return mid;
            if (arr[mid] < value) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
