// Algorithms5.java
//
// A stack: you put things on top and take them from the top (last in, first
// out). Java: `Deque<Character> stack = new ArrayDeque<>();` with push(),
// pop(), peek() and isEmpty().
//
// `isBalanced` checks whether brackets are properly nested:
//   "([]{})"  yes     "([)]"  no     "(("  no     ""  yes
// Walk the string. Opening bracket: push it. Closing bracket: the top of the
// stack must be its partner, otherwise fail. At the end the stack must be
// empty.
//
// `evalRpn` evaluates reverse Polish notation: "3 4 + 2 *" means (3 + 4) * 2.
// Numbers go on the stack, an operator pops two numbers and pushes the result.

// I AM NOT DONE

import javalings.Check;
import java.util.ArrayDeque;
import java.util.Deque;

public class Algorithms5 {
    public static void main(String[] args) {
        Check.isTrue(isBalanced("([]{})"), "([]{})");
        Check.isTrue(isBalanced(""), "empty");
        Check.isTrue(isBalanced("{[()()]}"), "{[()()]}");
        Check.isTrue(!isBalanced("([)]"), "([)] is not balanced");
        Check.isTrue(!isBalanced("(("), "(( is not balanced");
        Check.isTrue(!isBalanced(")"), ") is not balanced");

        Check.equals(14, evalRpn("3 4 + 2 *"), "(3 + 4) * 2");
        Check.equals(5, evalRpn("10 2 /"), "10 / 2");
        Check.equals(-1, evalRpn("2 3 -"), "2 - 3");
        Check.equals(42, evalRpn("42"), "just a number");
    }

    static boolean isBalanced(String s) {
        return false;
    }

    static int evalRpn(String expr) {
        return 0;
    }
}
