package main.java;

public class Student {
    private final String name;
    private final int rollNumber;
    private final int marks;

    public Student(String name, int rollNumber, int marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return name + "," + rollNumber + "," + marks;
    }
}

