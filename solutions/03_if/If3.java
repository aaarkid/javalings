// If3.java
//
// Java has `switch`, which Python got only recently as `match`.
// Modern Java (17+) lets you write it as an expression with arrows:
//
//     String size = switch (n) {
//         case 1 -> "one";
//         case 2, 3 -> "a few";
//         default -> "many";
//     };
//
// Rewrite `dayName` using a switch expression so all checks pass.
// Monday is 1, Sunday is 7. Anything else is "Unknown".

import javalings.Check;

public class If3 {
    public static void main(String[] args) {
        Check.equals("Monday", dayName(1), "day 1");
        Check.equals("Wednesday", dayName(3), "day 3");
        Check.equals("Sunday", dayName(7), "day 7");
        Check.equals("Unknown", dayName(9), "day 9");
        Check.equals("Unknown", dayName(0), "day 0");
    }

    static String dayName(int day) {
        return switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Unknown";
        };
    }
}
