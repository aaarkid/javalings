# Project 3: wordstats

A small tool that reads a text file and reports on the words in it. This
project adds reading files from disk:

    List<String> lines = Files.readAllLines(Path.of("some/file.txt"));

Reading a file can fail (file missing, no permission), so `readAllLines`
throws a checked `IOException`. Either catch it or declare `throws IOException`.

Files:

    wordstats/Main.java                the tests
    wordstats/io/TextLoader.java       implement: file -> list of lowercase words
    wordstats/stats/WordCount.java     a record, complete
    wordstats/stats/TextStats.java     implement: the statistics
    sample.txt                         the text used by the tests

Splitting text into words: `line.toLowerCase().split("[^a-z]+")` splits on
anything that is not a lowercase letter. It may produce empty strings at the
start of a line, drop those.

Run with `java Javalings.java run project_wordstats`. Delete the line below
when it passes. That was the last exercise: you now know enough Java to
build real things. Go make something.

// I AM NOT DONE
