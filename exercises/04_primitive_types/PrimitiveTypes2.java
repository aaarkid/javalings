// PrimitiveTypes2.java
//
// Python ints can be as large as you like. A Java int stops at 2147483647
// and then wraps around to a negative number. For bigger values use `long`
// (up to about 9 quintillion). A long literal ends with an L: 3000000000L.
//
// Make the checks pass by choosing the right types.

// I AM NOT DONE

import javalings.Check;

public class PrimitiveTypes2 {
    public static void main(String[] args) {
        int seconds = 3000000000;
        System.out.println("Seconds in ~95 years: " + seconds);
        Check.isTrue(seconds > 0, "the number of seconds is positive");

        int million = 1000000;
        int product = million * million;
        Check.isTrue(product > 0, "a million times a million is positive");
    }
}
