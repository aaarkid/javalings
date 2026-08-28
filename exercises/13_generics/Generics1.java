// Generics1.java
//
// `List<String>` is a generic type: List works for any element type, and you
// fill in which one. You can write generic methods yourself:
//
//     static <T> T first(List<T> list) {
//         return list.get(0);
//     }
//
// The `<T>` before the return type declares "T is a type parameter". Then it
// works with any list, and the compiler knows that first(names) is a String.
//
// `last` is written for String only. Make it generic so it works for both
// checks.

// I AM NOT DONE

import javalings.Check;
import java.util.List;

public class Generics1 {
    public static void main(String[] args) {
        List<String> names = List.of("Ada", "Bob", "Cleo");
        List<Integer> nums = List.of(1, 2, 3);
        String name = last(names);
        int num = last(nums);
        Check.equals("Cleo", name, "last name");
        Check.equals(3, num, "last number");
    }

    static String last(List<String> list) {
        return list.get(list.size() - 1);
    }
}
