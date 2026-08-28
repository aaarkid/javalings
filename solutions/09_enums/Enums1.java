// Enums1.java
//
// An enum is a type with a fixed list of values:
//
//     enum Color { RED, GREEN, BLUE }
//     Color c = Color.RED;
//
// Use it where Python code would pass around strings like "red" and hope
// nobody misspells them. Enums work great in a switch:
//
//     switch (c) {
//         case RED -> ...
//     }
//
// Complete the Season enum and the `next` method (WINTER wraps around
// to SPRING).

import javalings.Check;

public class Enums1 {
    public static void main(String[] args) {
        Check.equals(Season.SUMMER, next(Season.SPRING), "after spring comes summer");
        Check.equals(Season.AUTUMN, next(Season.SUMMER), "after summer comes autumn");
        Check.equals(Season.WINTER, next(Season.AUTUMN), "after autumn comes winter");
        Check.equals(Season.SPRING, next(Season.WINTER), "after winter comes spring");
        Check.equals(4, Season.values().length, "there are four seasons");
        Check.equals("AUTUMN", Season.AUTUMN.name(), "name() gives the text");
    }

    static Season next(Season s) {
        return switch (s) {
            case SPRING -> Season.SUMMER;
            case SUMMER -> Season.AUTUMN;
            case AUTUMN -> Season.WINTER;
            case WINTER -> Season.SPRING;
        };
    }
}

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}
