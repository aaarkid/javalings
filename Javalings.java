import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The Javalings runner. Run it with: java Javalings.java <command>
 *
 * Commands:
 *   list             show every exercise and whether it is done
 *   run <name>       compile and run one exercise
 *   hint <name>      print the hint for one exercise
 *   next             compile and run the first exercise that is not done
 *   verify           run every exercise in order, stop at the first failure
 *   watch            re-run the current exercise every time you save a file
 *   reset <name>     copy the original exercise back from git
 */
public class Javalings {

    static final String NOT_DONE = "// I AM NOT DONE";
    static final Path ROOT = Path.of("").toAbsolutePath();
    static final Path EXERCISES = ROOT.resolve("exercises");
    static final Path BUILD = ROOT.resolve(".build");

    record Exercise(String name, Path path, String mode, String hint) {
        boolean isProject() {
            return Files.isDirectory(path);
        }

        Path markerFile() {
            return isProject() ? path.resolve("README.md") : path;
        }

        boolean isDone() throws IOException {
            return Files.readAllLines(markerFile()).stream().noneMatch(l -> l.strip().equals(NOT_DONE));
        }

        List<Path> sourceFiles() throws IOException {
            if (!isProject()) return List.of(path);
            try (Stream<Path> s = Files.walk(path)) {
                return s.filter(p -> p.toString().endsWith(".java")).toList();
            }
        }

        String mainClass() {
            if (isProject()) return mode.substring("project:".length());
            String file = path.getFileName().toString();
            return file.substring(0, file.length() - ".java".length());
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage();
            return;
        }
        List<Exercise> all = loadExercises();
        switch (args[0]) {
            case "list" -> list(all);
            case "run" -> run(find(all, arg(args)), true);
            case "hint" -> System.out.println(find(all, arg(args)).hint());
            case "next" -> next(all);
            case "verify" -> verify(all);
            case "watch" -> watch(all);
            case "reset" -> reset(find(all, arg(args)));
            default -> usage();
        }
    }

    static void usage() {
        System.out.println("""
            Usage: java Javalings.java <command>

              list             show every exercise and whether it is done
              run <name>       compile and run one exercise
              hint <name>      print the hint for one exercise
              next             compile and run the first exercise that is not done
              verify           run every exercise in order, stop at the first failure
              watch            re-run the current exercise every time you save a file
              reset <name>     copy the original exercise back from git
            """);
    }

    static String arg(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing exercise name. Try: java Javalings.java list");
            System.exit(1);
        }
        return args[1];
    }

    static Exercise find(List<Exercise> all, String name) {
        for (Exercise e : all) {
            if (e.name().equals(name)) return e;
        }
        System.out.println("No exercise called '" + name + "'. Try: java Javalings.java list");
        System.exit(1);
        return null;
    }

    // ---- commands ----

    static void list(List<Exercise> all) throws IOException {
        int done = 0;
        for (Exercise e : all) {
            boolean d = e.isDone();
            if (d) done++;
            System.out.printf("%s  %-22s %s%n", d ? "[x]" : "[ ]", e.name(), ROOT.relativize(e.path()));
        }
        System.out.printf("%n%d / %d done%n", done, all.size());
    }

    static Exercise firstNotDone(List<Exercise> all) throws IOException {
        for (Exercise e : all) {
            if (!e.isDone()) return e;
        }
        return null;
    }

    static void next(List<Exercise> all) throws IOException {
        Exercise e = firstNotDone(all);
        if (e == null) {
            System.out.println(ALL_DONE);
            return;
        }
        run(e, true);
    }

    static void verify(List<Exercise> all) throws IOException {
        for (Exercise e : all) {
            if (!run(e, false)) {
                System.out.println("\nStopped at " + e.name() + ". Fix it, then run verify again.");
                return;
            }
            if (!e.isDone()) {
                System.out.println("\n" + e.name() + " runs, but still has the " + NOT_DONE + " line.");
                System.out.println("Remove that line when you are happy with your solution.");
                return;
            }
            System.out.println("ok  " + e.name());
        }
        System.out.println(ALL_DONE);
    }

    static void watch(List<Exercise> all) throws Exception {
        System.out.println("Watching for changes. Press Ctrl+C to stop.\n");
        Exercise current = firstNotDone(all);
        if (current == null) {
            System.out.println(ALL_DONE);
            return;
        }
        run(current, true);
        Map<Path, FileTime> seen = snapshot();
        while (true) {
            Thread.sleep(700);
            Map<Path, FileTime> now = snapshot();
            if (now.equals(seen)) continue;
            seen = now;
            System.out.println("\n----------------------------------------\n");
            all = loadExercises();
            Exercise e = firstNotDone(all);
            if (e == null) {
                System.out.println(ALL_DONE);
                return;
            }
            if (!e.name().equals(current.name())) {
                System.out.println("Moving on to " + e.name() + "!\n");
                current = e;
            }
            run(e, true);
        }
    }

    static void reset(Exercise e) throws Exception {
        Path rel = ROOT.relativize(e.path());
        int code = new ProcessBuilder("git", "checkout", "--", rel.toString())
            .directory(ROOT.toFile()).inheritIO().start().waitFor();
        System.out.println(code == 0 ? "Reset " + rel : "Could not reset " + rel);
    }

    // ---- compile and run ----

    static boolean run(Exercise e, boolean verbose) throws IOException {
        Path out = BUILD.resolve(e.name());
        deleteTree(out);
        Files.createDirectories(out);

        List<String> javac = new ArrayList<>(List.of("javac", "-d", out.toString(), "-Xlint:none"));
        javac.add(ROOT.resolve("javalings/Check.java").toString());
        for (Path p : e.sourceFiles()) javac.add(p.toString());

        if (verbose) System.out.println("Compiling " + e.name() + " ...");
        if (exec(javac) != 0) {
            System.out.println("\n" + e.name() + " does not compile yet. Read the error above, it tells you the line.");
            if (verbose) hintReminder(e);
            return false;
        }

        List<String> java = List.of("java", "-ea", "-cp", out.toString(), e.mainClass());
        if (verbose) System.out.println("Running " + e.name() + " ...\n");
        if (exec(java) != 0) {
            System.out.println("\n" + e.name() + " compiled, but running it failed.");
            if (verbose) hintReminder(e);
            return false;
        }

        if (verbose) {
            System.out.println("\n" + e.name() + " runs without errors.");
            if (!e.isDone()) {
                System.out.println("When you are happy with it, remove the " + NOT_DONE + " line from "
                    + ROOT.relativize(e.markerFile()) + " and move on.");
            }
        }
        return true;
    }

    static void hintReminder(Exercise e) {
        System.out.println("Stuck? Try: java Javalings.java hint " + e.name());
    }

    static int exec(List<String> cmd) throws IOException {
        try {
            return new ProcessBuilder(cmd).directory(ROOT.toFile()).inheritIO().start().waitFor();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 1;
        }
    }

    // ---- exercise loading ----

    /**
     * Every chapter folder under exercises/ has an info.toml with entries like:
     *
     *   [[exercises]]
     *   name = "intro1"
     *   path = "intro1.java"
     *   mode = "run"
     *   hint = """
     *   multi-line hint
     *   """
     *
     * This is a tiny subset of TOML, enough for our needs.
     */
    static List<Exercise> loadExercises() throws IOException {
        List<Exercise> all = new ArrayList<>();
        List<Path> chapters;
        try (Stream<Path> s = Files.list(EXERCISES)) {
            chapters = s.filter(Files::isDirectory).sorted().toList();
        }
        for (Path chapter : chapters) {
            Path info = chapter.resolve("info.toml");
            if (!Files.exists(info)) continue;
            all.addAll(parseInfo(chapter, Files.readAllLines(info)));
        }
        return all;
    }

    static List<Exercise> parseInfo(Path chapter, List<String> lines) {
        List<Exercise> result = new ArrayList<>();
        Map<String, String> current = null;
        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i).strip();
            if (line.equals("[[exercises]]")) {
                if (current != null) result.add(toExercise(chapter, current));
                current = new HashMap<>();
                i++;
            } else if (line.isEmpty() || line.startsWith("#") || current == null) {
                i++;
            } else {
                int eq = line.indexOf('=');
                String key = line.substring(0, eq).strip();
                String value = line.substring(eq + 1).strip();
                if (value.startsWith("\"\"\"")) {
                    StringBuilder sb = new StringBuilder();
                    i++;
                    while (!lines.get(i).strip().equals("\"\"\"")) {
                        sb.append(lines.get(i)).append('\n');
                        i++;
                    }
                    value = sb.toString().stripTrailing();
                } else {
                    value = value.substring(1, value.length() - 1);
                }
                current.put(key, value);
                i++;
            }
        }
        if (current != null) result.add(toExercise(chapter, current));
        return result;
    }

    static Exercise toExercise(Path chapter, Map<String, String> m) {
        return new Exercise(m.get("name"), chapter.resolve(m.get("path")),
            m.getOrDefault("mode", "run"), m.getOrDefault("hint", "No hint for this one."));
    }

    // ---- helpers ----

    static Map<Path, FileTime> snapshot() throws IOException {
        Map<Path, FileTime> m = new HashMap<>();
        try (Stream<Path> s = Files.walk(EXERCISES)) {
            for (Path p : s.toList()) {
                if (Files.isRegularFile(p)) m.put(p, Files.getLastModifiedTime(p));
            }
        }
        return m;
    }

    static void deleteTree(Path dir) throws IOException {
        if (!Files.exists(dir)) return;
        try (Stream<Path> s = Files.walk(dir)) {
            for (Path p : s.sorted((a, b) -> b.compareTo(a)).toList()) Files.delete(p);
        }
    }

    static final String ALL_DONE = """

        +----------------------------------------------+
        |  You finished every exercise. Well done!     |
        |  Now go build something of your own in Java. |
        +----------------------------------------------+
        """;
}
