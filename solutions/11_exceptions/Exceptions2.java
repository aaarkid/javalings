// Exceptions2.java
//
// Throwing: Python's `raise ValueError("bad")` is
//
//     throw new IllegalArgumentException("bad");
//
// `safeDivide` should throw an IllegalArgumentException with the message
// "cannot divide by zero" when b is 0. (Java would throw an
// ArithmeticException on its own, but we want our own clear message.)
//
// The test below catches the exception to check it. Note the `finally` block:
// it runs no matter what, like in Python.

import javalings.Check;

public class Exceptions2 {
    public static void main(String[] args) {
        Check.equals(4, safeDivide(12, 3), "12 / 3");

        String message = "no exception";
        try {
            safeDivide(1, 0);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        } finally {
            System.out.println("  (finally block ran)");
        }
        Check.equals("cannot divide by zero", message, "message for division by zero");
    }

    static int safeDivide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("cannot divide by zero");
        }
        return a / b;
    }
}
