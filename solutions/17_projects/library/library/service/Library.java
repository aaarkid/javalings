package library.service;

import java.util.ArrayList;
import java.util.List;
import library.model.Book;

public class Library {

    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public int size() {
        return books.size();
    }

    public List<Book> byAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.author().equals(author)) result.add(b);
        }
        return result;
    }

    public Book oldest() {
        Book best = null;
        for (Book b : books) {
            if (best == null || b.year() < best.year()) best = b;
        }
        return best;
    }

    public List<String> titles() {
        return books.stream().map(Book::title).sorted().toList();
    }
}
