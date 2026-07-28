// Recursion1.java
//
// A method that calls itself. Nothing new compared with Python, but from here
// on the exercises are about thinking, not about Java syntax.
//
// Every recursive method needs:
//   1. a base case: an input so small the answer is known without recursion
//   2. a step that makes the problem smaller and calls itself
//
// factorial(n) = n * factorial(n - 1), factorial(0) = 1
// sumDigits(1234) = 4 + sumDigits(123)

import javalings.Check;

public class Recursion1 {
    public static void main(String[] args) {
        Check.equals(1L, factorial(0), "0!");
        Check.equals(120L, factorial(5), "5!");
        Check.equals(2432902008176640000L, factorial(20), "20! (needs a long)");
        Check.equals(10, sumDigits(1234), "digits of 1234");
        Check.equals(7, sumDigits(7), "digits of 7");
        Check.equals(0, sumDigits(0), "digits of 0");
    }

    static long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    static int sumDigits(int n) {
        if (n < 10) return n;
        return n % 10 + sumDigits(n / 10);
    }
}
