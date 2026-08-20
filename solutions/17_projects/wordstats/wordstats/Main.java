package wordstats;

import javalings.Check;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import wordstats.io.TextLoader;
import wordstats.stats.TextStats;
import wordstats.stats.WordCount;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> words = TextLoader.loadWords(Path.of("exercises/17_projects/wordstats/sample.txt"));
        Check.equals(119, words.size(), "119 words in sample.txt");
        Check.equals("the", words.get(0), "first word");
        Check.equals("useful", words.get(words.size() - 1), "last word");

        TextStats stats = new TextStats(words);
        Check.equals(119, stats.wordCount(), "word count");
        Check.equals(73, stats.uniqueCount(), "unique words");
        Check.equals(List.of(new WordCount("the", 18), new WordCount("he", 5), new WordCount("light", 5)),
            stats.topWords(3), "top 3 words");
        Check.equals("lighthouse", stats.longestWord(), "longest word");
        Check.isTrue(Math.abs(stats.averageLength() - 4.0672) < 0.001,
            "average length is about 4.07 (got " + stats.averageLength() + ")");

        TextStats empty = new TextStats(List.of());
        Check.equals(0.0, empty.averageLength(), "average of nothing is 0");
        Check.equals(List.of(), empty.topWords(3), "top words of nothing");
    }
}
