// If2.java
//
// Comparing text: in Python you write `word == "foo"`.
// In Java, == on Strings asks "is this the very same object?", which is
// usually not what you want. Use word.equals("foo") instead.
//
// Fix `fooIfFizz` so all checks pass. Careful with the middle case.
// The words in main are built at runtime on purpose: == is false for them
// even when the letters match.

import javalings.Check;

public class If2 {
    public static void main(String[] args) {
        String fizz = new String("fizz");
        String fuzz = new String("fuzz");
        Check.equals("foo", fooIfFizz(fizz), "fizz gives foo");
        Check.equals("bar", fooIfFizz(fuzz), "fuzz gives bar");
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
