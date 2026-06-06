// Methods4.java
//
// Write the method `isEven` yourself. It takes an int and returns a boolean.
// `%` is the remainder operator, the same as in Python.

import javalings.Check;

public class Methods4 {
    public static void main(String[] args) {
        Check.isTrue(isEven(4), "4 is even");
        Check.isTrue(!isEven(7), "7 is not even");
        Check.isTrue(isEven(0), "0 is even");
        Check.isTrue(!isEven(-3), "-3 is not even");
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }
}
