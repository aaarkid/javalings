// Collections1.java
//
// ArrayList is Java's version of a Python list: it grows as needed.
//
//     import java.util.ArrayList;                (at the top of the file)
//     ArrayList<String> names = new ArrayList<>();
//     names.add("Ada");            names.append("Ada")
//     names.get(0)                 names[0]
//     names.size()                 len(names)
//     names.remove(0)              del names[0]
//     names.contains("Ada")        "Ada" in names
//
// The <String> part says what the list holds. It cannot hold plain `int`,
// you write ArrayList<Integer> instead (Java wraps the int for you).
//
// Implement `evens`: return a new list with only the even numbers.

// I AM NOT DONE

import javalings.Check;
import java.util.ArrayList;
import java.util.List;

public class Collections1 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }
        Check.equals(List.of(2, 4, 6, 8, 10), evens(numbers), "even numbers from 1 to 10");
        Check.equals(List.of(), evens(new ArrayList<>()), "evens of an empty list");
    }

    static ArrayList<Integer> evens(ArrayList<Integer> numbers) {
        return numbers;
    }
}
