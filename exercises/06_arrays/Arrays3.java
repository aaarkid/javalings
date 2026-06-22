// Arrays3.java
//
// Arrays cannot grow. To "add" an element you create a bigger array and copy.
// Implement `append`: it returns a new array with `value` at the end.
// Do not use any library methods for this one, do the copying yourself.
// (Next chapter you will meet ArrayList, which does this for you.)

// I AM NOT DONE

import javalings.Check;

public class Arrays3 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = append(a, 4);
        Check.arrayEquals(new int[]{1, 2, 3, 4}, b, "append 4");
        Check.arrayEquals(new int[]{1, 2, 3}, a, "the original array is untouched");
        Check.arrayEquals(new int[]{9}, append(new int[0], 9), "append to an empty array");
    }

    static int[] append(int[] arr, int value) {
        return arr;
    }
}
