package com.ecms.dbms.models;

/**
 * Model class for joined data — learners enrolled in instructor’s courses.
 * Not directly linked to a DB table.
 */
public class EnrolledLearner {
    private String learnerName;
    private String courseName;
    private String instructorName;

    // Constructor
    public EnrolledLearner(String learnerName, String courseName, String instructorName) {
        this.learnerName = learnerName;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    // Getters and setters
    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public String toString() {
        return learnerName + " - " + courseName + " (" + instructorName + ")";
    }
}
