// If2.java
//
// Comparing text: in Python you write `word == "foo"`.
// In Java, == on Strings asks "is this the very same object?", which is
// usually not what you want. Use word.equals("foo") instead.
//
// Fix `fooIfFizz` so all checks pass. Careful with the middle case.

import javalings.Check;

public class If2 {
    public static void main(String[] args) {
        Check.equals("foo", fooIfFizz("fizz"), "fizz gives foo");
        Check.equals("bar", fooIfFizz("fuzz"), "fuzz gives bar");
        Check.equals("baz", fooIfFizz("anything else"), "other input gives baz");
    }

    static String fooIfFizz(String input) {
        if (input.equals("fizz")) {
            return "foo";
        } else if (input.equals("fuzz")) {
            return "bar";
        } else {
            return "baz";
        }
    }
}
