// Recursion3.java
//
// Two classics.
//
// fib(n): 0, 1, 1, 2, 3, 5, 8, ... The plain recursive version calls itself
// twice per step and gets slow around n = 40. Add a cache (memoization): a
// long[] where cache[n] stores the answer once known. The check for fib(80)
// only finishes in time if you do.
//
// hanoi(n): the number of moves needed for the Tower of Hanoi with n disks.
// Moving n disks = move n-1 disks away, move the big one, move n-1 back.

// I AM NOT DONE

import javalings.Check;

public class Recursion3 {
    public static void main(String[] args) {
        Check.equals(0L, fib(0), "fib 0");
        Check.equals(1L, fib(1), "fib 1");
        Check.equals(55L, fib(10), "fib 10");
        Check.equals(23416728348467685L, fib(80), "fib 80 (needs memoization)");
        Check.equals(1L, hanoi(1), "hanoi 1");
        Check.equals(7L, hanoi(3), "hanoi 3");
        Check.equals(1023L, hanoi(10), "hanoi 10");
    }

    static long[] cache = new long[100];

    static long fib(int n) {
        return 0;
    }

    static long hanoi(int disks) {
        return 0;
    }
}
