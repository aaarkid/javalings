// Arrays1.java
//
// A Java array is like a Python list with two big differences:
// it has a fixed length, and every element has the same type.
//
//     int[] numbers = {3, 1, 4};        // create with values
//     int[] empty = new int[5];         // five zeros
//     numbers[0]                        // first element, like Python
//     numbers.length                    // no parentheses! (unlike s.length())
//
// Fix the program.

// I AM NOT DONE

import javalings.Check;

public class Arrays1 {
    public static void main(String[] args) {
        int[] scores = {90, 85, 77, 100};
        System.out.println("There are " + scores.length() + " scores.");
        System.out.println("The last one is " + scores[4]);
        Check.equals(100, scores[scores.length - 1], "last score");
    }
}
