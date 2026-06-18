// Strings4.java
//
// Two more tools:
//
//     "a,b,c".split(",")            -> array of "a", "b", "c"
//     String.join("-", parts)       -> "a-b-c"
//     String.format("%d apples", 3) -> "3 apples"   (like Python's f-strings, sort of)
//
// `capitalizeWords` should uppercase the first letter of every word.
// `csvSum` gets numbers separated by commas and returns their sum.

// I AM NOT DONE

import javalings.Check;

public class Strings4 {
    public static void main(String[] args) {
        Check.equals("Hello Big World", capitalizeWords("hello big world"), "capitalize three words");
        Check.equals("Java", capitalizeWords("java"), "capitalize one word");
        Check.equals(10, csvSum("1,2,3,4"), "sum of 1,2,3,4");
        Check.equals(7, csvSum("7"), "sum of a single number");
    }

    static String capitalizeWords(String sentence) {
        return sentence;
    }

    static int csvSum(String csv) {
        return 0;
    }
}
