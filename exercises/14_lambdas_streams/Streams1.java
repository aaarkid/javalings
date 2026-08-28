// Streams1.java
//
// Streams are Java's way to chain map / filter / sum over a collection:
//
//     List<Integer> big = nums.stream()
//         .filter(n -> n > 10)
//         .map(n -> n * 2)
//         .toList();
//
//     int total = nums.stream().mapToInt(n -> n).sum();
//     long count = names.stream().filter(s -> s.startsWith("A")).count();
//
// Python: [n * 2 for n in nums if n > 10]. Same idea, written left to right.
//
// Implement the three methods with streams (no loops).

// I AM NOT DONE

import javalings.Check;
import java.util.List;

public class Streams1 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(3, 8, 12, 5, 20, 7);
        List<String> names = List.of("Ada", "bob", "Alan", "cleo", "Amy");

        Check.equals(List.of(16, 24, 40), doubleBig(nums), "doubled numbers over 7");
        Check.equals(55, sum(nums), "sum of all numbers");
        Check.equals(List.of("ADA", "ALAN", "AMY"), upperA(names), "names starting with A, uppercased");
    }

    // every number greater than 7, doubled, in order
    static List<Integer> doubleBig(List<Integer> nums) {
        return List.of();
    }

    static int sum(List<Integer> nums) {
        return 0;
    }

    static List<String> upperA(List<String> names) {
        return List.of();
    }
}
