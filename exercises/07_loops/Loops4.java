// Loops4.java
//
// The classic: FizzBuzz. For the numbers 1 to n, return a line for each:
// "Fizz" if divisible by 3, "Buzz" if divisible by 5, "FizzBuzz" if both,
// otherwise the number itself. Join the lines with "\n" (no newline at the end).
//
// Tip: `fizzBuzzOf(int i)` for a single number, then a loop that joins them.

// I AM NOT DONE

import javalings.Check;

public class Loops4 {
    public static void main(String[] args) {
        Check.equals("1", fizzBuzz(1), "n = 1");
        Check.equals("1\n2\nFizz\n4\nBuzz", fizzBuzz(5), "n = 5");
        Check.equals("FizzBuzz", fizzBuzzOf(15), "15 is FizzBuzz");
        Check.equals("Fizz", fizzBuzzOf(9), "9 is Fizz");
        Check.equals("Buzz", fizzBuzzOf(10), "10 is Buzz");
        Check.equals("7", fizzBuzzOf(7), "7 is 7");
    }

    static String fizzBuzzOf(int i) {
        return "";
    }

    static String fizzBuzz(int n) {
        return "";
    }
}
