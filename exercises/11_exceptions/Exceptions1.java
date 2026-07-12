// Exceptions1.java
//
// try / except in Python is try / catch in Java:
//
//     try {
//         int n = Integer.parseInt(text);
//     } catch (NumberFormatException e) {
//         System.out.println("not a number: " + e.getMessage());
//     }
//
// Implement `parseOrDefault`: parse the text as an int, or return the
// fallback if the text is not a valid number.

// I AM NOT DONE

import javalings.Check;

public class Exceptions1 {
    public static void main(String[] args) {
        Check.equals(42, parseOrDefault("42", 0), "parses 42");
        Check.equals(-1, parseOrDefault("forty-two", -1), "falls back for words");
        Check.equals(7, parseOrDefault("", 7), "falls back for empty text");
    }

    static int parseOrDefault(String text, int fallback) {
        return Integer.parseInt(text);
    }
}
