// Lambdas2.java
//
// Passing behaviour into a method. `applyToAll` takes a list and a function
// and returns a new list with the function applied to each element (Python's
// map, or a list comprehension).
//
// Write `applyToAll` as a generic method, then use it in main to produce the
// two lists.

import javalings.Check;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Lambdas2 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4);
        List<String> words = List.of("hi", "there");

        List<Integer> doubled = applyToAll(nums, n -> n * 2);   // use applyToAll
        List<Integer> lengths = applyToAll(words, w -> w.length());   // use applyToAll: length of each word

        Check.equals(List.of(2, 4, 6, 8), doubled, "doubled numbers");
        Check.equals(List.of(2, 5), lengths, "word lengths");
        Check.equals(List.of("HI", "THERE"), applyToAll(words, String::toUpperCase), "applyToAll with a String result");
    }

    static <T, R> List<R> applyToAll(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(f.apply(item));
        }
        return result;
    }
}
