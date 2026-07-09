// Collections2.java
//
// You will often see `List<String>` instead of `ArrayList<String>`.
// List is the interface (the "what it can do"), ArrayList is one
// implementation (the "how"). Prefer List in variable and parameter types:
//
//     List<String> names = new ArrayList<>();
//
// List.of("a", "b") creates a list you cannot change. Handy for tests.
//
// Implement `removeAll`: remove every occurrence of `value` from the list.
// Careful: removing while looping forward with an index skips elements.
// Either loop backwards, or use list.removeIf(x -> x.equals(value)).

import javalings.Check;
import java.util.ArrayList;
import java.util.List;

public class Collections2 {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("a", "b", "a", "a", "c"));
        removeAll(words, "a");
        Check.equals(List.of("b", "c"), words, "all a's removed");

        List<String> none = new ArrayList<>(List.of("x"));
        removeAll(none, "y");
        Check.equals(List.of("x"), none, "nothing removed when value is absent");
    }

    static void removeAll(List<String> list, String value) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).equals(value)) {
                list.remove(i);
            }
        }
    }
}
