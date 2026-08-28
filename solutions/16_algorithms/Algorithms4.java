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

import javalings.Check;
import java.util.HashMap;
import java.util.Map;

public class Algorithms4 {
    public static void main(String[] args) {
        Check.arrayEquals(new int[]{0, 1}, twoSum(new int[]{2, 7, 11, 15}, 9), "2 + 7");
        Check.arrayEquals(new int[]{1, 2}, twoSum(new int[]{3, 2, 4}, 6), "2 + 4, not 3 + 3");
        Check.arrayEquals(new int[]{-1, -1}, twoSum(new int[]{1, 2}, 10), "no pair");

        int[] big = new int[3_000_000];
        for (int i = 0; i < big.length; i++) big[i] = i;
        Check.arrayEquals(new int[]{2_999_998, 2_999_999}, twoSum(big, 5_999_997), "3 million elements (nested loops never finish here)");

        Check.isTrue(isAnagram("listen", "silent"), "listen / silent");
        Check.isTrue(!isAnagram("hello", "world"), "hello / world");
        Check.isTrue(!isAnagram("aab", "abb"), "aab / abb");
        Check.isTrue(!isAnagram("ab", "abc"), "different lengths");
    }

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] counts = new int[26];
        for (char c : a.toCharArray()) counts[c - 'a']++;
        for (char c : b.toCharArray()) counts[c - 'a']--;
        for (int n : counts) {
            if (n != 0) return false;
        }
        return true;
    }
}
