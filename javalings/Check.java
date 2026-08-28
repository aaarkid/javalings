package javalings;

/**
 * Small helper used by the exercises to check answers.
 * If a check fails, the program stops and prints what went wrong.
 */
public class Check {

    private static final boolean COLOR = System.console() != null && System.getenv("NO_COLOR") == null;
    private static final String MINT = "\u001b[38;2;92;224;160m";
    private static final String RED = "\u001b[38;2;240;78;110m";
    private static final String OFF = "\u001b[0m";

    private static void pass(String what) {
        System.out.println("  " + (COLOR ? MINT + "\u2713 " + what + OFF : "ok: " + what));
    }

    public static void equals(Object expected, Object actual, String what) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            fail(what + "\n  expected: " + show(expected) + "\n  but got:  " + show(actual));
        }
        pass(what);
    }

    public static void equals(Object expected, Object actual) {
        equals(expected, actual, "value should be " + show(expected));
    }

    public static void isTrue(boolean condition, String what) {
        if (!condition) fail(what);
        pass(what);
    }

    public static void arrayEquals(int[] expected, int[] actual, String what) {
        if (!java.util.Arrays.equals(expected, actual)) {
            fail(what + "\n  expected: " + java.util.Arrays.toString(expected)
                + "\n  but got:  " + java.util.Arrays.toString(actual));
        }
        pass(what);
    }

    public static void fail(String message) {
        System.out.println();
        System.out.println("  " + (COLOR ? RED + "\u2717 check failed: " + message + OFF : "CHECK FAILED: " + message));
        System.exit(1);
    }

    private static String show(Object o) {
        if (o instanceof String s) return "\"" + s + "\"";
        return String.valueOf(o);
    }
}
