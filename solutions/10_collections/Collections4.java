// Collections4.java
//
// HashSet is Python's set: no duplicates, fast "is it in there?" checks.
//
//     Set<Integer> seen = new HashSet<>();
//     seen.add(3);          // returns false if 3 was already there
//     seen.contains(3)
//
// `firstDuplicate` returns the first number that appears a second time, or
// -1 if every number is unique. `uniqueCount` counts distinct values.

import javalings.Check;
import java.util.HashSet;
import java.util.Set;

public class Collections4 {
    public static void main(String[] args) {
        Check.equals(2, firstDuplicate(new int[]{1, 2, 3, 2, 1}), "2 repeats before 1 does");
        Check.equals(-1, firstDuplicate(new int[]{1, 2, 3}), "no duplicates");
        Check.equals(3, uniqueCount(new int[]{5, 5, 6, 7, 6}), "three distinct values");
    }

    static int firstDuplicate(int[] numbers) {
        Set<Integer> seen = new HashSet<>();
        for (int n : numbers) {
            if (!seen.add(n)) return n;
        }
        return -1;
    }

    static int uniqueCount(int[] numbers) {
        Set<Integer> seen = new HashSet<>();
        for (int n : numbers) {
            seen.add(n);
        }
        return seen.size();
    }
}
