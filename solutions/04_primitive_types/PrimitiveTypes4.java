// PrimitiveTypes4.java
//
// Converting between numbers and text:
//
//     Integer.parseInt("42")      ->  42        (String to int)
//     Double.parseDouble("2.5")   ->  2.5       (String to double)
//     String.valueOf(42)          ->  "42"      (anything to String)
//     "" + 42                     ->  "42"      (the lazy way)
//
// Implement `addStrings`: it gets two numbers written as text and returns
// their sum as text.

import javalings.Check;

public class PrimitiveTypes4 {
    public static void main(String[] args) {
        Check.equals("5", addStrings("2", "3"), "2 + 3");
        Check.equals("100", addStrings("58", "42"), "58 + 42");
        Check.equals("-1", addStrings("4", "-5"), "4 + -5");
    }

    static String addStrings(String a, String b) {
        int sum = Integer.parseInt(a) + Integer.parseInt(b);
        return String.valueOf(sum);
    }
}
