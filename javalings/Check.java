package javalings;

/**
 * Small helper used by the exercises to check answers.
 * If a check fails, the program stops and prints what went wrong.
 */
public class Check {

    public static void equals(Object expected, Object actual, String what) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            fail(what + "\n  expected: " + show(expected) + "\n  but got:  " + show(actual));
        }
        System.out.println("  ok: " + what);
    }

    public static void equals(Object expected, Object actual) {
        equals(expected, actual, "value should be " + show(expected));
    }

    public static void isTrue(boolean condition, String what) {
        if (!condition) fail(what);
        System.out.println("  ok: " + what);
    }

    public static void arrayEquals(int[] expected, int[] actual, String what) {
        if (!java.util.Arrays.equals(expected, actual)) {
            fail(what + "\n  expected: " + java.util.Arrays.toString(expected)
                + "\n  but got:  " + java.util.Arrays.toString(actual));
        }
        System.out.println("  ok: " + what);
    }

    public static void fail(String message) {
        System.out.println("\nCHECK FAILED: " + message);
        System.exit(1);
    }

    private static String show(Object o) {
        if (o instanceof String s) return "\"" + s + "\"";
        return String.valueOf(o);
    }
}
