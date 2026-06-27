// Loops1.java
//
// A `while` loop is the same as in Python (plus parentheses and braces).
// Python's `for i in range(a, b)` becomes:
//
//     for (int i = a; i < b; i++) { ... }
//
// Implement `countDown`: it returns "5 4 3 2 1 liftoff" for n = 5.

import javalings.Check;

public class Loops1 {
    public static void main(String[] args) {
        Check.equals("5 4 3 2 1 liftoff", countDown(5), "count down from 5");
        Check.equals("1 liftoff", countDown(1), "count down from 1");
        Check.equals("liftoff", countDown(0), "count down from 0");
    }

    static String countDown(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            sb.append(i).append(" ");
        }
        sb.append("liftoff");
        return sb.toString();
    }
}
