// Strings1.java
//
// Useful String methods (compare with Python):
//
//     s.length()            len(s)
//     s.charAt(2)           s[2]
//     s.substring(1, 3)     s[1:3]
//     s.toUpperCase()       s.upper()
//     s.contains("ab")      "ab" in s
//     s.indexOf("ab")       s.find("ab")
//     s.trim()              s.strip()
//     s.equals(t)           s == t
//
// Implement the three methods.

// I AM NOT DONE

import javalings.Check;

public class Strings1 {
    public static void main(String[] args) {
        Check.equals('J', firstChar("Java"), "first char of Java");
        Check.equals('a', lastChar("Java"), "last char of Java");
        Check.equals("ava", withoutFirst("Java"), "Java without first char");
        Check.equals("", withoutFirst("x"), "x without first char");
    }

    static char firstChar(String s) {
        return ' ';
    }

    static char lastChar(String s) {
        return ' ';
    }

    static String withoutFirst(String s) {
        return s;
    }
}
