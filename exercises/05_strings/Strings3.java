// Strings3.java
//
// Building a string piece by piece with `+` in a loop works, but Java has a
// tool made for it: StringBuilder.
//
//     StringBuilder sb = new StringBuilder();
//     sb.append("a");
//     sb.append(1);
//     String result = sb.toString();   // "a1"
//
// Implement `repeat` without using String.repeat (build it yourself), and
// `reverse` without using StringBuilder.reverse (walk the string backwards).

// I AM NOT DONE

import javalings.Check;

public class Strings3 {
    public static void main(String[] args) {
        Check.equals("ababab", repeat("ab", 3), "ab three times");
        Check.equals("", repeat("xyz", 0), "xyz zero times");
        Check.equals("avaJ", reverse("Java"), "Java reversed");
        Check.equals("racecar", reverse("racecar"), "racecar reversed");
    }

    static String repeat(String s, int times) {
        return s;
    }

    static String reverse(String s) {
        return s;
    }
}
