/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Progress {
    private int progressId;
    private int learnerId;          // Foreign key from Learner table
    private int courseId;           // Foreign key from Course table
    private int completedModules;
    private int totalModules;
    private double progressPercentage;

    // Constructor
    public Progress(int progressId, int learnerId, int courseId, int completedModules, int totalModules, double progressPercentage) {
        this.progressId = progressId;
        this.learnerId = learnerId;
        this.courseId = courseId;
        this.completedModules = completedModules;
        this.totalModules = totalModules;
        this.progressPercentage = progressPercentage;
    }

    // Getters and Setters
    public int getProgressId() { return progressId; }
    public void setProgressId(int progressId) { this.progressId = progressId; }

    public int getLearnerId() { return learnerId; }
    public void setLearnerId(int learnerId) { this.learnerId = learnerId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getCompletedModules() { return completedModules; }
    public void setCompletedModules(int completedModules) { this.completedModules = completedModules; }

    public int getTotalModules() { return totalModules; }
    public void setTotalModules(int totalModules) { this.totalModules = totalModules; }

    public double getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(double progressPercentage) { this.progressPercentage = progressPercentage; }

    @Override
    public String toString() {
        return "Progress: " + progressPercentage + "% (" + completedModules + "/" + totalModules + " modules)";
    }
}
