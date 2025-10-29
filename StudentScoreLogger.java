package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentScoreLogger {

    private final String filePath;
    private final Object lock = new Object();

    public StudentScoreLogger(String filePath) {
        this.filePath = filePath;
    }

    public void logStudentScore(Student student) {
        synchronized (lock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(student.toString());
                writer.newLine();
                System.out.println("Logged: " + student);
            } catch (IOException e) {
                System.err.println("Error writing student score: " + e.getMessage());
            }
        }
    }
}

