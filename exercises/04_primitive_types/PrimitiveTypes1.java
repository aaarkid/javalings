// PrimitiveTypes1.java
//
// Integer division: in Python, 7 / 2 gives 3.5. In Java, when both sides are
// ints, the result is an int and the decimals are thrown away: 7 / 2 gives 3.
//
// To get 3.5 you need at least one side to be a double: 7 / 2.0 or
// (double) 7 / 2. The `(double)` part is called a cast.
//
// Fix `average` so it returns the real average.

// I AM NOT DONE

import javalings.Check;

public class PrimitiveTypes1 {
    public static void main(String[] args) {
        Check.equals(3.5, average(7, 2), "average of 7 and 2 (total 7, count 2)");
        Check.equals(2.0, average(6, 3), "average of total 6, count 3");
        Check.equals(0.25, average(1, 4), "average of total 1, count 4");
    }

    static double average(int total, int count) {
        return total / count;
    }
}
