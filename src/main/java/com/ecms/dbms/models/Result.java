/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Result {
    private int resultId;
    private int assessmentId;   // Foreign key from Assessment table
    private int learnerId;      // Foreign key from Learner table
    private double marksObtained;
    private String grade;

    // Constructor
    public Result(int resultId, int assessmentId, int learnerId, double marksObtained, String grade) {
        this.resultId = resultId;
        this.assessmentId = assessmentId;
        this.learnerId = learnerId;
        this.marksObtained = marksObtained;
        this.grade = grade;
    }

    // Getters and Setters
    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }

    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }

    public int getLearnerId() { return learnerId; }
    public void setLearnerId(int learnerId) { this.learnerId = learnerId; }

    public double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(double marksObtained) { this.marksObtained = marksObtained; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Result #" + resultId + " | Marks: " + marksObtained + " | Grade: " + grade;
    }
}
