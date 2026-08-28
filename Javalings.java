import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
    static final int RUN_TIMEOUT_SECONDS = 15;
    static boolean watching = false;

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

        String chapter() {
            return path.getParent().getFileName().toString();
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
            case "hint" -> hint(find(all, arg(args)));
            case "next" -> next(all);
            case "verify" -> verify(all);
            case "watch" -> watch(all);
            case "reset" -> reset(find(all, arg(args)));
            default -> usage();
        }
    }

    static void usage() {
        System.out.println();
        System.out.println("  " + Ui.title("javalings") + Ui.subtle("  java Javalings.java <command>"));
        System.out.println();
        String[][] rows = {
            {"list", "every exercise, done or not"},
            {"run <name>", "compile and run one exercise"},
            {"hint <name>", "a hint for it"},
            {"next", "run the first exercise that is not done"},
            {"verify", "run every exercise in order, stop at the first failure"},
            {"watch", "re-run whenever you save"},
            {"reset <name>", "put the original exercise back"},
        };
        for (String[] r : rows) {
            System.out.printf("  %s%s%n", Ui.ink(String.format("%-14s", r[0])), Ui.subtle(r[1]));
        }
        System.out.println();
    }

    static String arg(String[] args) {
        if (args.length < 2) {
            Ui.fail("missing exercise name");
            Ui.help("java Javalings.java list");
            System.exit(1);
        }
        return args[1];
    }

    static Exercise find(List<Exercise> all, String name) {
        for (Exercise e : all) {
            if (e.name().equals(name)) return e;
        }
        Ui.fail("no exercise called " + name);
        Ui.help("java Javalings.java list");
        System.exit(1);
        return null;
    }

    // ---- commands ----

    static void list(List<Exercise> all) throws IOException {
        Exercise current = firstNotDone(all);
        String chapter = "";
        int done = 0;
        System.out.println();
        for (Exercise e : all) {
            if (!e.chapter().equals(chapter)) {
                chapter = e.chapter();
                System.out.println("  " + Ui.primary(chapter));
            }
            boolean d = e.isDone();
            if (d) done++;
            String mark;
            String name;
            if (d) {
                mark = Ui.success("✓");
                name = Ui.subtle(e.name());
            } else if (e == current) {
                mark = Ui.accent(">");
                name = Ui.accentBold(e.name());
            } else {
                mark = Ui.subtle("·");
                name = Ui.ink(e.name());
            }
            System.out.printf("  %s %s%n", mark, name);
        }
        System.out.println();
        System.out.println("  " + Ui.progress(done, all.size()));
        System.out.println();
    }

    static void hint(Exercise e) {
        System.out.println();
        System.out.println("  " + Ui.title("hint") + Ui.subtle("  " + e.name()));
        System.out.println();
        for (String line : e.hint().split("\n")) {
            System.out.println("  " + Ui.primary("│") + " " + line);
        }
        System.out.println();
        Ui.help("java Javalings.java run " + e.name());
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
            allDone();
            return;
        }
        run(e, true);
    }

    static void verify(List<Exercise> all) throws IOException {
        System.out.println();
        for (Exercise e : all) {
            if (!run(e, false)) {
                System.out.println();
                Ui.fail("stopped at " + e.name());
                Ui.help("java Javalings.java hint " + e.name());
                return;
            }
            if (!e.isDone()) {
                System.out.println();
                Ui.fail(e.name() + " runs, but still has the " + NOT_DONE + " line");
                Ui.note("remove that line when you are happy with your solution");
                return;
            }
            Ui.ok(e.name());
        }
        allDone();
    }

    /**
     * Watch mode is a small TUI: it takes over the screen (alternate buffer),
     * redraws in place whenever a file under exercises/ changes, and reads
     * single keys: h shows the hint, r re-runs, q quits. The terminal is put
     * back the way it was on exit.
     */
    static void watch(List<Exercise> all) throws Exception {
        Exercise current = firstNotDone(all);
        if (current == null) {
            allDone();
            return;
        }
        watching = true;
        Ui.enterScreen();
        Runtime.getRuntime().addShutdownHook(new Thread(Ui::leaveScreen));
        Keys keys = new Keys();
        keys.start();
        boolean showHint = false;
        draw(current, showHint);
        Map<Path, FileTime> seen = snapshot();
        while (true) {
            int key = keys.poll(500);
            Map<Path, FileTime> now = snapshot();
            boolean changed = !now.equals(seen);
            seen = now;
            if (key == 'q' || key == 3) {
                return;
            }
            if (key == 'h') {
                showHint = !showHint;
            } else if (key != 'r' && !changed) {
                continue;
            }
            if (changed) showHint = false;
            all = loadExercises();
            Exercise e = firstNotDone(all);
            if (e == null) {
                Ui.clear();
                allDone();
                Ui.help("q quit");
                while (keys.poll(500) != 'q') { }
                return;
            }
            String moved = null;
            if (!e.name().equals(current.name())) {
                moved = current.name();
                current = e;
            }
            draw(current, showHint);
            if (moved != null) {
                Ui.ok(moved + " done, next up: " + current.name());
            }
        }
    }

    static void draw(Exercise e, boolean showHint) throws IOException {
        Ui.clear();
        run(e, true);
        if (showHint) {
            System.out.println();
            for (String line : e.hint().split("\n")) {
                System.out.println("  " + Ui.primary("│") + " " + line);
            }
        }
        System.out.println();
        Ui.help("h hint", "r re-run", "q quit", "saving re-runs");
    }

    /** Reads single keys from the terminal in raw mode, on its own thread. */
    static final class Keys extends Thread {
        final java.util.concurrent.BlockingQueue<Integer> queue = new java.util.concurrent.LinkedBlockingQueue<>();

        Keys() {
            setDaemon(true);
        }

        @Override
        public void run() {
            try {
                int c;
                while ((c = System.in.read()) != -1) queue.add(c);
            } catch (IOException ignored) {
                // stdin closed, nothing more to read
            }
        }

        int poll(long millis) throws InterruptedException {
            Integer c = queue.poll(millis, TimeUnit.MILLISECONDS);
            return c == null ? -1 : c;
        }
    }

    static void reset(Exercise e) throws Exception {
        Path rel = ROOT.relativize(e.path());
        int code = new ProcessBuilder("git", "checkout", "--", rel.toString())
            .directory(ROOT.toFile()).inheritIO().start().waitFor();
        if (code == 0) Ui.ok("reset " + rel);
        else Ui.fail("could not reset " + rel);
    }

    static void allDone() throws IOException {
        System.out.println();
        System.out.println("  " + Ui.gradient("all " + loadExercises().size() + " done. go build something of your own."));
        System.out.println();
    }

    // ---- compile and run ----

    static boolean run(Exercise e, boolean verbose) throws IOException {
        if (verbose) header(e);
        Path out = BUILD.resolve(e.name());
        deleteTree(out);
        Files.createDirectories(out);

        List<String> javac = new ArrayList<>(List.of("javac", "-d", out.toString(), "-Xlint:none"));
        javac.add("javalings/Check.java");
        for (Path p : e.sourceFiles()) javac.add(ROOT.relativize(p).toString());

        Ui.Spinner spinner = verbose ? Ui.spin("compiling " + e.name()) : null;
        List<String> output = new ArrayList<>();
        int code = capture(javac, output);
        if (spinner != null) spinner.finish();
        if (code != 0) {
            for (String line : output) System.out.println("  " + Ui.compilerLine(line));
            System.out.println();
            Ui.fail(e.name() + " does not compile yet. the error names the line.");
            if (verbose && !watching) Ui.help("hint: java Javalings.java hint " + e.name());
            return false;
        }

        List<String> java = List.of("java", "-ea", "-cp", out.toString(), e.mainClass());
        if (exec(java) != 0) {
            System.out.println();
            Ui.fail(e.name() + " compiled, but running it failed");
            if (verbose && !watching) Ui.help("hint: java Javalings.java hint " + e.name());
            return false;
        }

        if (verbose) {
            System.out.println();
            Ui.ok(e.name() + " runs without errors");
            if (!e.isDone()) {
                Ui.note("happy with it? remove the " + NOT_DONE + " line to move on");
            }
        }
        return true;
    }

    static void header(Exercise e) throws IOException {
        List<Exercise> all = loadExercises();
        int done = 0;
        for (Exercise x : all) {
            if (x.isDone()) done++;
        }
        System.out.println();
        System.out.println("  " + Ui.title("javalings") + "  " + Ui.ink(Ui.bold(e.name()))
            + Ui.subtle("  " + e.chapter()));
        System.out.println("  " + Ui.progress(done, all.size()));
        System.out.println();
    }

    static int capture(List<String> cmd, List<String> output) throws IOException {
        Process p = new ProcessBuilder(cmd).directory(ROOT.toFile()).redirectErrorStream(true).start();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = r.readLine()) != null) output.add(line);
        }
        try {
            return p.waitFor();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 1;
        }
    }

    static int exec(List<String> cmd) throws IOException {
        try {
            Process p = new ProcessBuilder(cmd).directory(ROOT.toFile()).inheritIO().start();
            if (!p.waitFor(RUN_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                p.destroyForcibly();
                System.out.println();
                Ui.fail("still running after " + RUN_TIMEOUT_SECONDS + " seconds, stopped it. an endless loop?");
                return 1;
            }
            return p.exitValue();
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

    /**
     * Terminal styling. Violet is the structure, pink is the one pointer,
     * mint and red are only ever success and failure. The progress bar is
     * the single gradient on a screen. Colours are off when the output is
     * not a terminal or NO_COLOR is set, and fall back to the 16 ANSI
     * colours when the terminal does not announce truecolor.
     */
    static final class Ui {
        static final boolean ON = System.console() != null && System.getenv("NO_COLOR") == null;
        static final boolean TRUECOLOR = "truecolor".equals(System.getenv("COLORTERM"))
            || "24bit".equals(System.getenv("COLORTERM"));

        // truecolor seed, shifted from charmtone
        static final int[] PRIMARY = {111, 88, 255};   // violet
        static final int[] ACCENT = {255, 111, 168};   // pink
        static final int[] SUCCESS = {92, 224, 160};   // mint
        static final int[] ERROR = {240, 78, 110};     // red
        static final int[] SUBTLE = {133, 131, 146};   // grey
        static final int[] INK = {236, 235, 240};      // near white

        static final String OFF = "[0m";
        static final String BOLD_ON = "[1m";

        static String color(int[] rgb, int ansi) {
            if (!ON) return "";
            if (TRUECOLOR) return "[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
            return "[" + ansi + "m";
        }

        static String paint(int[] rgb, int ansi, String s) {
            return ON ? color(rgb, ansi) + s + OFF : s;
        }

        static String bold(String s) {
            return ON ? BOLD_ON + s + OFF : s;
        }

        static String primary(String s) {
            return paint(PRIMARY, 34, s);
        }

        static String accent(String s) {
            return paint(ACCENT, 35, s);
        }

        static String accentBold(String s) {
            return ON ? color(ACCENT, 35) + BOLD_ON + s + OFF : s;
        }

        static String success(String s) {
            return paint(SUCCESS, 32, s);
        }

        static String error(String s) {
            return paint(ERROR, 31, s);
        }

        static String subtle(String s) {
            return paint(SUBTLE, 90, s);
        }

        static String ink(String s) {
            return paint(INK, 39, s);
        }

        static String title(String s) {
            return ON ? color(PRIMARY, 34) + BOLD_ON + s + OFF : s;
        }

        static void ok(String s) {
            System.out.println("  " + success("✓ " + s));
        }

        static void fail(String s) {
            System.out.println("  " + error("✗ " + s));
        }

        static void note(String s) {
            System.out.println("  " + subtle(s));
        }

        /** Help line: key in ink, description in subtle, groups joined by a dot. */
        static void help(String... pairs) {
            StringBuilder sb = new StringBuilder("  ");
            for (int i = 0; i < pairs.length; i++) {
                if (i > 0) sb.append(subtle(" • "));
                sb.append(subtle(pairs[i]));
            }
            System.out.println(sb);
        }


        static void clear() {
            if (ON) System.out.print("[2J[H");
        }

        static void enterScreen() {
            stty("-icanon -echo min 1");
            if (ON) System.out.print("[?1049h[?25l");
        }

        static void leaveScreen() {
            if (ON) System.out.print("[?25h[?1049l");
            System.out.flush();
            stty("sane");
        }

        static void stty(String mode) {
            try {
                new ProcessBuilder("sh", "-c", "stty " + mode + " < /dev/tty")
                    .redirectErrorStream(true).start().waitFor();
            } catch (IOException | InterruptedException ignored) {
                // no tty: keys will not work, watching still does
            }
        }

        /** One gradient per screen: violet to mint across the filled part of the bar. */
        static String progress(int done, int total) {
            int cells = 30;
            int filled = total == 0 ? 0 : (int) Math.round(cells * (double) done / total);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < filled; i++) {
                double t = filled <= 1 ? 0 : (double) i / (filled - 1);
                int[] c = blend(PRIMARY, SUCCESS, t);
                sb.append(TRUECOLOR ? paint(c, 34, "━") : primary("━"));
            }
            sb.append(subtle("╌".repeat(cells - filled)));
            sb.append(subtle("  " + done + "/" + total));
            return sb.toString();
        }

        static String gradient(String s) {
            if (!ON || !TRUECOLOR) return title(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                double t = s.length() <= 1 ? 0 : (double) i / (s.length() - 1);
                sb.append(color(blend(PRIMARY, SUCCESS, t), 34)).append(BOLD_ON).append(s.charAt(i));
            }
            return sb.append(OFF).toString();
        }

        static int[] blend(int[] a, int[] b, double t) {
            return new int[]{
                (int) Math.round(a[0] + (b[0] - a[0]) * t),
                (int) Math.round(a[1] + (b[1] - a[1]) * t),
                (int) Math.round(a[2] + (b[2] - a[2]) * t),
            };
        }

        /** javac output: the message in red, the file position in grey, carets and counts dim. */
        static String compilerLine(String line) {
            int at = line.indexOf(": error: ");
            if (at > 0) {
                String where = line.substring(0, at);
                int slash = where.lastIndexOf('/');
                if (slash >= 0) where = where.substring(slash + 1);
                return subtle(where) + " " + error(line.substring(at + 2));
            }
            if (line.strip().equals("^") || line.matches("\\d+ errors?")) return subtle(line);
            return ink(line);
        }

        /** Braille spinner on the current line while a long step runs. */
        static Spinner spin(String label) {
            Spinner s = new Spinner(label);
            s.start();
            return s;
        }

        static final class Spinner extends Thread {
            static final String FRAMES = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
            final String label;
            volatile boolean running = true;

            Spinner(String label) {
                this.label = label;
                setDaemon(true);
            }

            @Override
            public void run() {
                if (!ON) return;
                int i = 0;
                while (running) {
                    System.out.print("\r  " + primary(String.valueOf(FRAMES.charAt(i % FRAMES.length())))
                        + " " + subtle(label));
                    System.out.flush();
                    i++;
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            }

            void finish() {
                running = false;
                try {
                    join();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                if (ON) System.out.print("\r[2K");
            }
        }
    }
}
