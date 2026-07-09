// Collections3.java
//
// HashMap is Python's dict.
//
//     Map<String, Integer> ages = new HashMap<>();
//     ages.put("Ada", 13);                 ages["Ada"] = 13
//     ages.get("Ada")                      ages["Ada"]     (null if missing, not an error)
//     ages.getOrDefault("Bob", 0)          ages.get("Bob", 0)
//     ages.containsKey("Ada")              "Ada" in ages
//     ages.keySet(), ages.values()         ages.keys(), ages.values()
//     for (String k : ages.keySet())       for k in ages:
//
// Implement `countWords`: how often each word appears.

// I AM NOT DONE

import javalings.Check;
import java.util.HashMap;
import java.util.Map;

public class Collections3 {
    public static void main(String[] args) {
        Map<String, Integer> counts = countWords("the cat and the dog and the bird");
        Check.equals(3, counts.get("the"), "the appears 3 times");
        Check.equals(2, counts.get("and"), "and appears 2 times");
        Check.equals(1, counts.get("bird"), "bird appears once");
        Check.equals(null, counts.get("fish"), "fish is not in the map");
        Check.equals(5, counts.size(), "5 different words");
    }

    static Map<String, Integer> countWords(String text) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : text.split(" ")) {
            // your code here
        }
        return counts;
    }
}
