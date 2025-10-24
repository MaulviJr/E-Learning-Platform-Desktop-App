/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Certificate {
    private int certificateId;
    private int learnerId;       // Foreign key from Learner table
    private int courseId;        // Foreign key from Course table
    private String issueDate;
    private int instructorId;    // Foreign key from Instructor table
    private String verificationCode;

    // Constructor
    public Certificate(int certificateId, int learnerId, int courseId, String issueDate, int instructorId, String verificationCode) {
        this.certificateId = certificateId;
        this.learnerId = learnerId;
        this.courseId = courseId;
        this.issueDate = issueDate;
        this.instructorId = instructorId;
        this.verificationCode = verificationCode;
    }

    // Getters and Setters
    public int getCertificateId() { return certificateId; }
    public void setCertificateId(int certificateId) { this.certificateId = certificateId; }

    public int getLearnerId() { return learnerId; }
    public void setLearnerId(int learnerId) { this.learnerId = learnerId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public int getInstructorId() { return instructorId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }

    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }

    @Override
    public String toString() {
        return "Certificate #" + certificateId + " | Learner ID: " + learnerId + " | Course ID: " + courseId;
    }
}
