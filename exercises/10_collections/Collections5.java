// Collections5.java
//
// Sorting. Collections.sort(list) sorts in place, like Python's list.sort().
// For a custom order you pass a Comparator:
//
//     list.sort(Comparator.comparing(Student::getName));
//     list.sort(Comparator.comparingInt(Student::getGrade).reversed());
//
// Student::getName means "the method getName of each Student", the same idea
// as Python's key=lambda s: s.name. Real lambdas come in a later chapter.
//
// Implement `topStudents`: the names of the `n` students with the highest
// grades, best first. Do not change the original list.

// I AM NOT DONE

import javalings.Check;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Collections5 {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Ada", 91),
            new Student("Bob", 78),
            new Student("Cleo", 95),
            new Student("Dan", 85)
        );
        Check.equals(List.of("Cleo", "Ada"), topStudents(students, 2), "top 2");
        Check.equals(List.of("Cleo", "Ada", "Dan", "Bob"), topStudents(students, 4), "all four, best first");
        Check.equals("Ada", students.get(0).getName(), "original list untouched");
    }

    static List<String> topStudents(List<Student> students, int n) {
        List<Student> copy = new ArrayList<>(students);
        List<String> names = new ArrayList<>();
        return names;
    }
}

class Student {
    private final String name;
    private final int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    String getName() {
        return name;
    }

    int getGrade() {
        return grade;
    }
}
