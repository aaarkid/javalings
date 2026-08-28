// Loops2.java
//
// `break` and `continue` work like in Python.
// `firstMultiple` returns the first number >= start that is divisible by k.
// `countOdd` counts the odd numbers in the array, using `continue` to skip
// the even ones (just to practice it).

// I AM NOT DONE

import javalings.Check;

public class Loops2 {
    public static void main(String[] args) {
        Check.equals(21, firstMultiple(20, 7), "first multiple of 7 from 20");
        Check.equals(20, firstMultiple(20, 5), "first multiple of 5 from 20");
        Check.equals(3, countOdd(new int[]{1, 2, 3, 4, 5}), "three odd numbers");
        Check.equals(0, countOdd(new int[]{2, 4}), "no odd numbers");
    }

    static int firstMultiple(int start, int k) {
        int n = start;
        while (true) {
            // your code here, replacing this return
            return -1;
        }
    }

    static int countOdd(int[] arr) {
        int count = 0;
        for (int x : arr) {
            // your code here
        }
        return count;
    }
}
