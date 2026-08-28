// Strings2.java
//
// Strings in Java cannot be changed. Every method like toUpperCase() gives
// you a NEW string and leaves the old one alone. (Python is the same, so this
// should feel familiar.)
//
// The program below expects `shout` to change the string in place. That does
// not work. Fix the `shout(word);` line in main so the check passes. Leave
// `String word = "hello";` and the `shout` method as they are.

// I AM NOT DONE

import javalings.Check;

public class Strings2 {
    public static void main(String[] args) {
        String word = "hello";
        shout(word);
        Check.equals("HELLO!", word, "word after shouting");
    }

    static String shout(String s) {
        return s.toUpperCase() + "!";
    }
}
