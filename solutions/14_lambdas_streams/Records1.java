// Records1.java
//
// A record is a class whose only job is to hold data. One line gives you the
// fields, a constructor, getters, equals, hashCode and toString:
//
//     record Point(int x, int y) { }
//
//     Point p = new Point(1, 2);
//     p.x()                          // 1  (getter is the field name, no "get")
//     p.equals(new Point(1, 2))      // true
//     p.toString()                   // "Point[x=1, y=2]"
//
// Records are immutable: no setters. Like a Python dataclass with frozen=True.
// You can still add methods.
//
// Turn Book into a record with title, author and year, and add a method
// `isOld()` that is true for books from before 1950.

import javalings.Check;

public class Records1 {
    public static void main(String[] args) {
        Book b = new Book("Dune", "Frank Herbert", 1965);
        Check.equals("Dune", b.title(), "title");
        Check.equals("Frank Herbert", b.author(), "author");
        Check.equals(1965, b.year(), "year");
        Check.equals(b, new Book("Dune", "Frank Herbert", 1965), "equal books");
        Check.equals("Book[title=Dune, author=Frank Herbert, year=1965]", b.toString(), "toString");
        Check.isTrue(!b.isOld(), "Dune is not old");
        Check.isTrue(new Book("Emma", "Jane Austen", 1815).isOld(), "Emma is old");
    }
}

record Book(String title, String author, int year) {
    boolean isOld() {
        return year < 1950;
    }
}
