/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Assessment {
    private int assessmentId;
    private int courseId;          // Foreign key from Course table
    private String assessmentType; // Quiz / Assignment / Final
    private int maxMarks;
    private String date;

    // Constructor
    public Assessment(int assessmentId, int courseId, String assessmentType, int maxMarks, String date) {
        this.assessmentId = assessmentId;
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.maxMarks = maxMarks;
        this.date = date;
    }

    // Getters and Setters
    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String assessmentType) { this.assessmentType = assessmentType; }

    public int getMaxMarks() { return maxMarks; }
    public void setMaxMarks(int maxMarks) { this.maxMarks = maxMarks; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return assessmentType + " (" + maxMarks + " Marks)";
    }
}
