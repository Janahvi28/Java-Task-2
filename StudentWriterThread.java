package main.java;

public class StudentWriterThread extends Thread {

    private final Student student;
    private final StudentScoreLogger logger;

    public StudentWriterThread(Student student, StudentScoreLogger logger) {
        this.student = student;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.logStudentScore(student);
    }
}

