package org.example;

import java.util.ArrayList;
import java.util.List;

public class GradeManager {
    private List<Integer> grades = new ArrayList<>();

    public void addGrade(int grade) {
        if (grade >= 1 && grade <= 6) grades.add(grade);
    }

    public double getAverage() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}