// If1.java
//
// if / else if / else work like Python, with two changes:
// the condition sits inside parentheses ( ), and the body inside braces { }.
// `elif` is spelled `else if`.
//
// Write `bigger` so it returns the larger of the two numbers.
// Do not use Math.max, that would be too easy.

// I AM NOT DONE

import javalings.Check;

public class If1 {
    public static void main(String[] args) {
        Check.equals(10, bigger(10, 8), "bigger(10, 8)");
        Check.equals(42, bigger(32, 42), "bigger(32, 42)");
        Check.equals(5, bigger(5, 5), "bigger(5, 5)");
    }

    static int bigger(int a, int b) {
        // your code here
    }
}
