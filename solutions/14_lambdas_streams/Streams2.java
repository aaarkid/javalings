// Streams2.java
//
// More stream tools:
//
//     .sorted()                                natural order
//     .sorted(Comparator.comparing(...))       custom order
//     .distinct()                              remove duplicates
//     .limit(3)                                first three
//     .anyMatch(x -> ...)  .allMatch(...)      like Python's any() / all()
//     .collect(Collectors.joining(", "))       join strings
//     .collect(Collectors.groupingBy(f))       Map from key to list of items
//
// Implement the methods using streams. Duplicates ("fig" twice) are dropped
// everywhere: every method works on the distinct words.

import javalings.Check;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Streams2 {
    public static void main(String[] args) {
        List<String> words = List.of("pear", "fig", "apple", "kiwi", "fig", "plum");

        Check.equals("apple, fig, kiwi, pear, plum", sortedUnique(words), "sorted unique words joined");
        Check.equals(List.of("fig", "kiwi", "pear"), shortestThree(words), "three shortest, ties by alphabet");
        Check.isTrue(anyLongerThan(words, 4), "some word is longer than 4");
        Check.isTrue(!anyLongerThan(words, 5), "no word is longer than 5");

        Map<Integer, List<String>> byLength = groupByLength(words);
        Check.equals(List.of("pear", "kiwi", "plum"), byLength.get(4), "4-letter words");
        Check.equals(List.of("apple"), byLength.get(5), "5-letter words");
        Check.equals(List.of("fig"), byLength.get(3), "3-letter words, fig only once");
    }

    static String sortedUnique(List<String> words) {
        return words.stream().distinct().sorted().collect(Collectors.joining(", "));
    }

    static List<String> shortestThree(List<String> words) {
        return words.stream()
            .distinct()
            .sorted(Comparator.comparing(String::length).thenComparing(s -> s))
            .limit(3)
            .toList();
    }

    static boolean anyLongerThan(List<String> words, int n) {
        return words.stream().anyMatch(w -> w.length() > n);
    }

    static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream().distinct().collect(Collectors.groupingBy(String::length));
    }
}
