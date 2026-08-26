<p align="center">
  <img src="assets/banner.jpg" alt="javalings" width="100%">
</p>

<p align="center">
  67 exercises &middot; 18 chapters &middot; no build tool &middot; JDK 17+
</p>

Small exercises to get you used to reading and writing Java code. Inspired by
[Rustlings](https://github.com/rust-lang/rustlings), written for someone who
already knows a bit of Python.

Every exercise is a Java file that is broken in some way: it does not compile,
or it runs but gives the wrong answer. You fix it, the runner checks it, you
move on. The first chapters are about the language. The last ones are about
writing real algorithms and splitting a program into several files.

## Setup

You need a JDK, version 17 or newer. Check with:

    java -version

If that prints a version number, you are ready. If not, install one, for
example from https://adoptium.net. Any text editor works. VS Code with the
"Extension Pack for Java" is a good choice.

Then get the exercises:

    git clone https://github.com/aaarkid/javalings.git
    cd javalings

## Doing the exercises

    java Javalings.java list          # see every exercise
    java Javalings.java next          # run the first exercise that is not done
    java Javalings.java run intro2    # run one exercise by name
    java Javalings.java hint intro2   # get a hint
    java Javalings.java watch         # re-run automatically every time you save

Each exercise file starts with a comment that explains the task, and a line
`// I AM NOT DONE`. Fix the code, and when you are happy with it, delete that
line. The runner then moves to the next exercise.

`watch` is the nicest way to work: open the exercise in your editor, keep the
terminal next to it, and save to see what happens.

## Chapters

| Chapter | What you learn |
|---|---|
| 00_intro | running the exercises |
| 01_variables | types, `final` |
| 02_methods | parameters, return values |
| 03_if | `if`, `else if`, `switch` |
| 04_primitive_types | `int` vs `double`, `long`, `char`, casting |
| 05_strings | String methods, StringBuilder, split and join |
| 06_arrays | fixed-size arrays, 2D arrays |
| 07_loops | `for`, `while`, `break`, `continue`, nested loops |
| 08_classes | fields, constructors, `private`, `toString`, `equals`, `static` |
| 09_enums | enums with data |
| 10_collections | ArrayList, HashMap, HashSet, sorting with Comparator |
| 11_exceptions | try / catch, throwing, checked exceptions |
| 12_inheritance | `extends`, `abstract`, interfaces |
| 13_generics | generic methods and classes |
| 14_lambdas_streams | records, lambdas, streams |
| 15_recursion | base cases, memoization |
| 16_algorithms | binary search, insertion and merge sort, hashing, stacks, BFS |
| 17_projects | packages, multi-file programs, interfaces across files, file IO |

The projects in chapter 17 are folders instead of single files. Each has a
README with the task, and the `I AM NOT DONE` line lives in that README.

## Solutions

The `solutions/` folder has one answer for every exercise. Try for a while
before you look. `scripts/check_solutions.sh` runs the whole set against the
solutions.
