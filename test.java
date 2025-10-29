package test.java;

import main.java.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentScoreLoggerTest {

    private static final String TEST_FILE = "test_scores.csv";
    private StudentScoreLogger logger;

    @BeforeEach
    void setup() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        logger = new StudentScoreLogger(TEST_FILE);
    }

    @Test
    void testSingleWrite() throws IOException {
        Student s = new Student("Test", 1, 95);
        logger.logStudentScore(s);

        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));
        assertEquals(1, lines.size());
        assertEquals("Test,1,95", lines.get(0));
    }

    @Test
    void testMultiThreadedWrite() throws InterruptedException, IOException {
        Student s1 = new Student("A", 100, 30);
        Student s2 = new Student("B", 101, 47);
        Student s3 = new Student("C", 102, 68);

        Thread t1 = new StudentWriterThread(s1, logger);
        Thread t2 = new StudentWriterThread(s2, logger);
        Thread t3 = new StudentWriterThread(s3, logger);

        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();

        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE));
        assertEquals(3, lines.size());
        assertTrue(lines.contains("A,100,30"));
        assertTrue(lines.contains("B,101,47"));
        assertTrue(lines.contains("C,102,68"));
    }
}

