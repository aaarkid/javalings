package wordstats.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextLoader {

    /** Reads the file and returns every word in it, lowercased, in order. */
    public static List<String> loadWords(Path file) throws IOException {
        List<String> words = new ArrayList<>();
        for (String line : Files.readAllLines(file)) {
            for (String w : line.toLowerCase().split("[^a-z]+")) {
                if (!w.isEmpty()) words.add(w);
            }
        }
        return words;
    }
}
