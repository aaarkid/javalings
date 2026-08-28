// Variables4.java
//
// `final` makes a variable a constant: it gets a value once and never again.
// Python has no real constants, it just uses UPPER_CASE names as a promise.
// Java enforces it.
//
// One line below tries to change a constant. Delete that line. Everything
// else already does what the checks expect: `start` stays 3, `current`
// ends up 4.

// I AM NOT DONE

import javalings.Check;

public class Variables4 {
    public static void main(String[] args) {
        final int start = 3;
        int current = start;
        System.out.println("Start is " + start);

        current = current + 1;
        start = current; // TODO: delete this line
        System.out.println("Current is " + current);

        Check.equals(3, start, "start is still 3");
        Check.equals(4, current, "current is 4");
    }
}
