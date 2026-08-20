package wordstats.stats;

import java.util.List;

public class TextStats {

    private final List<String> words;

    public TextStats(List<String> words) {
        this.words = words;
    }

    public int wordCount() {
        return words.size();
    }

    /** Number of different words. */
    public int uniqueCount() {
        // TODO
        return 0;
    }

    /** The n most frequent words, most frequent first. Ties: alphabetical. */
    public List<WordCount> topWords(int n) {
        // TODO: count with a Map, then sort the entries
        return List.of();
    }

    /** The longest word. Ties: the one that appears first in the text. */
    public String longestWord() {
        // TODO
        return "";
    }

    /** Average word length, as a double. 0 for no words. */
    public double averageLength() {
        // TODO
        return 0;
    }
}
