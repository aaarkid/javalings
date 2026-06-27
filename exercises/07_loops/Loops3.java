// Loops3.java
//
// Nested loops. `multiplicationTable(3)` should return this text, with one
// line per row and a newline "\n" after every row including the last:
//
//     1 2 3
//     2 4 6
//     3 6 9
//
// Use a StringBuilder.

// I AM NOT DONE

import javalings.Check;

public class Loops3 {
    public static void main(String[] args) {
        Check.equals("1 2 3\n2 4 6\n3 6 9\n", multiplicationTable(3), "table of 3");
        Check.equals("1\n", multiplicationTable(1), "table of 1");
        System.out.print(multiplicationTable(5));
    }

    static String multiplicationTable(int n) {
        return "";
    }
}
