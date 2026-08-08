package library;

import javalings.Check;
import java.util.List;
import library.model.Book;
import library.service.Library;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Check.equals(0, lib.size(), "empty library");
        Check.equals(null, lib.oldest(), "oldest of empty library is null");

        lib.add(new Book("Dune", "Frank Herbert", 1965));
        lib.add(new Book("Emma", "Jane Austen", 1815));
        lib.add(new Book("Children of Dune", "Frank Herbert", 1976));
        lib.add(new Book("Persuasion", "Jane Austen", 1817));

        Check.equals(4, lib.size(), "four books");
        Check.equals("Emma", lib.oldest().title(), "oldest book");
        Check.equals(List.of("Dune", "Children of Dune"),
            lib.byAuthor("Frank Herbert").stream().map(Book::title).toList(), "books by Herbert");
        Check.equals(List.of(), lib.byAuthor("Nobody"), "no books by Nobody");
        Check.equals(List.of("Children of Dune", "Dune", "Emma", "Persuasion"), lib.titles(), "sorted titles");
    }
}
