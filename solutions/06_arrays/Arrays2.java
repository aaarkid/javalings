// Arrays2.java
//
// Two ways to loop over an array:
//
//     for (int i = 0; i < arr.length; i++) { ... arr[i] ... }   // with index
//     for (int x : arr) { ... x ... }                            // "for each", like Python's for x in arr
//
// Implement sum and max. For max, assume the array has at least one element.

import javalings.Check;

public class Arrays2 {
    public static void main(String[] args) {
        int[] a = {4, 8, 15, 16, 23, 42};
        Check.equals(108, sum(a), "sum of the array");
        Check.equals(42, max(a), "max of the array");
        Check.equals(0, sum(new int[0]), "sum of an empty array");
        Check.equals(-2, max(new int[]{-7, -2, -9}), "max of negative numbers");
    }

    static int sum(int[] arr) {
        int total = 0;
        for (int x : arr) {
            total += x;
        }
        return total;
    }

    static int max(int[] arr) {
        int best = arr[0];
        for (int x : arr) {
            if (x > best) best = x;
        }
        return best;
    }
}
