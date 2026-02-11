package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeManagerTest {

    @Test
    void testAverageOfFourAndSix() {
        GradeManager manager = new GradeManager();
        manager.addGrade(4);
        manager.addGrade(6);
        assertEquals(5.0, manager.getAverage());
    }

    @Test
    void testAddInvalidGradeSeven() {
        GradeManager manager = new GradeManager();
        manager.addGrade(4);
        manager.addGrade(9);
        assertEquals(4.0, manager.getAverage());
    }

    @Test
    void testAverageForEmptyList() {
        GradeManager manager = new GradeManager();
        assertEquals(0.0, manager.getAverage());
    }
}