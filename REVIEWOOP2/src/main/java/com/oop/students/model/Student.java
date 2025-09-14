package com.oop.students.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String id;
    private String fullName;
    private LocalDate birth;
    private String major;      // CNTT or KTPM
    private double gpa;        // 0..10
    private String classGroup; // homeroom

    public Student() {}

    public Student(String id, String fullName, LocalDate birth, String major, double gpa, String classGroup) {
        this.id = id;
        this.fullName = fullName;
        this.birth = birth;
        this.major = major;
        this.gpa = gpa;
        this.classGroup = classGroup;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getBirth() { return birth; }
    public void setBirth(LocalDate birth) { this.birth = birth; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public String getClassGroup() { return classGroup; }
    public void setClassGroup(String classGroup) { this.classGroup = classGroup; }

    @Override
    public String toString() {
        DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s | %-22s | %s | %-4s | %4.2f | %s",
                id, fullName, birth.format(DF), major, gpa, classGroup);
    }
}
