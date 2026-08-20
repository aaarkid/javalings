package wordstats.stats;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
        return new HashSet<>(words).size();
    }

    /** The n most frequent words, most frequent first. Ties: alphabetical. */
    public List<WordCount> topWords(int n) {
        Map<String, Integer> counts = new HashMap<>();
        for (String w : words) {
            counts.merge(w, 1, Integer::sum);
        }
        return counts.entrySet().stream()
            .map(e -> new WordCount(e.getKey(), e.getValue()))
            .sorted(Comparator.comparingInt(WordCount::count).reversed().thenComparing(WordCount::word))
            .limit(n)
            .toList();
    }

    /** The longest word. Ties: the one that appears first in the text. */
    public String longestWord() {
        String best = "";
        for (String w : words) {
            if (w.length() > best.length()) best = w;
        }
        return best;
    }

    /** Average word length, as a double. 0 for no words. */
    public double averageLength() {
        if (words.isEmpty()) return 0;
        int total = 0;
        for (String w : words) {
            total += w.length();
        }
        return total / (double) words.size();
    }
}
