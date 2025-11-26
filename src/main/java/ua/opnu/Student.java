package ua.opnu;

import java.util.Arrays;
import java.util.function.Predicate;

public class Student {
    private String name;
    private String group;
    private int[] marks;

    Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return this.marks;
    }

    public static Student[] filter(Student[] students, Predicate<Student> predicate) {
        return Arrays.stream(students)
                .filter(predicate)
                .toArray(Student[]::new);
    }
}