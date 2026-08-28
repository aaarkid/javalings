// Generics2.java
//
// A generic class. `Pair<A, B>` holds two values of any two types.
//
// Complete the class: two private fields, a constructor, `first()` and
// `second()` getters, and a `swap()` method that returns a new Pair with the
// values the other way round (so a Pair<A, B> becomes a Pair<B, A>).

import javalings.Check;

public class Generics2 {
    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<>("age", 13);
        String key = p.first();
        int value = p.second();
        Check.equals("age", key, "first of pair");
        Check.equals(13, value, "second of pair");

        Pair<Integer, String> s = p.swap();
        int swappedKey = s.first();
        String swappedValue = s.second();
        Check.equals(13, swappedKey, "first after swap");
        Check.equals("age", swappedValue, "second after swap");
    }
}

class Pair<A, B> {
    private final A first;
    private final B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    A first() {
        return first;
    }

    B second() {
        return second;
    }

    Pair<B, A> swap() {
        return new Pair<>(second, first);
    }
}
