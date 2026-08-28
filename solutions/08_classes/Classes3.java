// Classes3.java
//
// Printing an object: `System.out.println(point)` calls point.toString().
// By default that gives something ugly like "Point@1b6d3586". Override it
// to get something readable. This is Python's __str__ / __repr__.
//
// Comparing objects: `a.equals(b)` is Python's __eq__. By default it checks
// whether a and b are the very same object. Override it to compare contents.
//
// Make both checks pass. The `@Override` line tells the compiler "I mean to
// replace an existing method", so it complains if you misspell the name.

import javalings.Check;

public class Classes3 {
    public static void main(String[] args) {
        Point p = new Point(3, 4);
        Point q = new Point(3, 4);
        Check.equals("(3, 4)", p.toString(), "toString gives (3, 4)");
        Check.isTrue(p.equals(q), "two points with the same coordinates are equal");
        Check.isTrue(!p.equals(new Point(4, 3)), "different coordinates are not equal");
        Check.isTrue(!p.equals(new Point(3, 5)), "same x, different y is not equal");
        Check.equals("(1, -2)", new Point(1, -2).toString(), "toString gives (1, -2)");
    }
}

class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object other) {
        // `other` could be anything, so first check it is a Point:
        if (!(other instanceof Point)) return false;
        Point o = (Point) other;
        return x == o.x && y == o.y;
    }
}
