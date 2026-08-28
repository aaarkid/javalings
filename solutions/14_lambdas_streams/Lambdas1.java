// Lambdas1.java
//
// A lambda is a function without a name, like Python's lambda:
//
//     lambda x: x * 2                x -> x * 2
//     lambda a, b: a + b             (a, b) -> a + b
//
// The type of a lambda is a "functional interface": an interface with exactly
// one method. Java ships with common ones in java.util.function:
//
//     Function<Integer, Integer> twice = x -> x * 2;    twice.apply(5) is 10
//     Predicate<String> isEmpty = s -> s.isEmpty();       isEmpty.test("") is true
//     BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
//
// Fill in the three lambdas.

import javalings.Check;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambdas1 {
    public static void main(String[] args) {
        Function<Integer, Integer> square = x -> x * x;
        Predicate<String> isLong = s -> s.length() > 5;                // true for length > 5
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);  // "ab", 3 -> "ababab"

        Check.equals(49, square.apply(7), "square of 7");
        Check.isTrue(isLong.test("elephant"), "elephant is long");
        Check.isTrue(!isLong.test("cat"), "cat is not long");
        Check.isTrue(!isLong.test("fives"), "5 letters is not long");
        Check.isTrue(isLong.test("sixsix"), "6 letters is long");
        Check.equals("ababab", repeat.apply("ab", 3), "repeat ab 3 times");
    }
}
