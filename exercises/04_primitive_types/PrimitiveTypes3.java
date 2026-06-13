// PrimitiveTypes3.java
//
// A char is one character, written in single quotes: 'a'. Under the hood it
// is a number (the Unicode code), so you can do arithmetic with it:
// 'a' + 1 is 98, and (char) ('a' + 1) is 'b'.
//
// Implement both methods.

// I AM NOT DONE

import javalings.Check;

public class PrimitiveTypes3 {
    public static void main(String[] args) {
        Check.equals('b', nextLetter('a'), "after a comes b");
        Check.equals('z', nextLetter('y'), "after y comes z");
        Check.isTrue(isVowel('e'), "e is a vowel");
        Check.isTrue(!isVowel('x'), "x is not a vowel");
        Check.isTrue(isVowel('U'), "U is a vowel (uppercase too)");
    }

    static char nextLetter(char c) {
        return c + 1;
    }

    static boolean isVowel(char c) {
        // Tip: Character.toLowerCase(c) gives you the lowercase version.
        return false;
    }
}
