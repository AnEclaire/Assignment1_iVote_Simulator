package concepts;

/**
 * Emma Gutierrez
 * CS 3560 - Summer 2024
 * Dr. Yu Sun
 * Student.java
 */
public class Student {
    private String id;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student ID: " + id;
    }
}
