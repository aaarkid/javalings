// Algorithms4.java
//
// Using the right data structure turns a slow solution into a fast one.
//
// twoSum: find two DIFFERENT positions whose values add up to target, and
// return them as {i, j} with i < j. Two nested loops work but take n * n
// steps. With a HashMap from value to index you need one pass: for each
// number, check whether (target - number) was seen before.
//
// isAnagram: two words are anagrams if they use the same letters the same
// number of times. Counting letters in an int[26] is the classic trick
// ('c' - 'a' turns a lowercase letter into 0..25).

// I AM NOT DONE

import javalings.Check;
import java.util.HashMap;
import java.util.Map;

public class Algorithms4 {
    public static void main(String[] args) {
        Check.arrayEquals(new int[]{0, 1}, twoSum(new int[]{2, 7, 11, 15}, 9), "2 + 7");
        Check.arrayEquals(new int[]{1, 2}, twoSum(new int[]{3, 2, 4}, 6), "2 + 4, not 3 + 3");
        Check.arrayEquals(new int[]{-1, -1}, twoSum(new int[]{1, 2}, 10), "no pair");

        int[] big = new int[300_000];
        for (int i = 0; i < big.length; i++) big[i] = i;
        Check.arrayEquals(new int[]{299_998, 299_999}, twoSum(big, 599_997), "300k elements (nested loops are too slow here)");

        Check.isTrue(isAnagram("listen", "silent"), "listen / silent");
        Check.isTrue(!isAnagram("hello", "world"), "hello / world");
        Check.isTrue(!isAnagram("aab", "abb"), "aab / abb");
        Check.isTrue(!isAnagram("ab", "abc"), "different lengths");
    }

    static int[] twoSum(int[] nums, int target) {
        return new int[]{-1, -1};
    }

    static boolean isAnagram(String a, String b) {
        return false;
    }
}
