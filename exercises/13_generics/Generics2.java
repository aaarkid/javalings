// Generics2.java
//
// A generic class. `Pair<A, B>` holds two values of any two types.
//
// Complete the class: two private fields, a constructor, `first()` and
// `second()` getters, and a `swap()` method that returns a new Pair with the
// values the other way round (so a Pair<A, B> becomes a Pair<B, A>).

// I AM NOT DONE

import javalings.Check;

public class Generics2 {
    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<>("age", 13);
        Check.equals("age", p.first(), "first of pair");
        Check.equals(13, p.second(), "second of pair");

        Pair<Integer, String> s = p.swap();
        Check.equals(13, s.first(), "first after swap");
        Check.equals("age", s.second(), "second after swap");
    }
}

class Pair<A, B> {
}
