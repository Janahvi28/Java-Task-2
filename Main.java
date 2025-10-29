package main.java;

public class Main {
    public static void main(String[] args) {

        String filePath = "student_scores.csv";
        StudentScoreLogger logger = new StudentScoreLogger(filePath);

        Student s1 = new Student("Alice", 101, 95);
        Student s2 = new Student("Bob", 102, 79);
        Student s3 = new Student("Charlie", 103, 92);

        Thread t1 = new StudentWriterThread(s1, logger);
        Thread t2 = new StudentWriterThread(s2, logger);
        Thread t3 = new StudentWriterThread(s3, logger);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All student scores logged successfully!");
    }
}

