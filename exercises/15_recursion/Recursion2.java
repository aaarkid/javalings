// Recursion2.java
//
// Recursion on text. `isPalindrome` should ignore case and spaces.
// "A man a plan a canal Panama" is a palindrome.
//
// Idea: clean the string first (lowercase, remove spaces), then a recursive
// helper: a string of length 0 or 1 is a palindrome; otherwise the first and
// last characters must match and the middle must be a palindrome.

// I AM NOT DONE

import javalings.Check;

public class Recursion2 {
    public static void main(String[] args) {
        Check.isTrue(isPalindrome("racecar"), "racecar");
        Check.isTrue(isPalindrome("A man a plan a canal Panama"), "a man a plan...");
        Check.isTrue(!isPalindrome("java"), "java is not");
        Check.isTrue(isPalindrome(""), "empty string is");
        Check.equals("ab ab ab", powerString("ab", 3), "ab three times with spaces");
        Check.equals("", powerString("x", 0), "zero times");
    }

    static boolean isPalindrome(String s) {
        return false;
    }

    // powerString("ab", 3) -> "ab ab ab", built recursively without loops
    static String powerString(String s, int n) {
        return "";
    }
}
