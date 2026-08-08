# Project 1: library

Until now every exercise was one file. Real programs are split into many
files, grouped into packages. A package is a folder, and every file in it
starts with a `package` line:

    // file: library/model/Book.java
    package library.model;

    public record Book(String title, String author, int year) { }

Rules to remember:

- The folder path must match the package name: `library/model/Book.java`
  holds `package library.model;`.
- One public class (or record, enum, interface) per file, named like the file.
- Code in another package can only see things marked `public`. Methods too.
- To use a class from another package, import it:
  `import library.model.Book;`

`library/Main.java` is already written and tests the two classes it imports.
Your job is to create them, in the right folders:

`library/model/Book.java`

- a public record `Book` with `title` (String), `author` (String), `year` (int)

`library/service/Library.java`

- a public class `Library` with a public no-argument constructor
- `public void add(Book book)`
- `public int size()`
- `public List<Book> byAuthor(String author)`: all books by that author, in the
  order they were added
- `public Book oldest()`: the book with the smallest year, or `null` if empty
- `public List<String> titles()`: all titles, sorted alphabetically

Run it with `java Javalings.java run project_library`. When it passes, delete
the line below.

// I AM NOT DONE
