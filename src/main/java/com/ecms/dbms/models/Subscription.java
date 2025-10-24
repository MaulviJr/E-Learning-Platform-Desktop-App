/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Subscription {
    private int subscriptionId;
    private int learnerId;    // Foreign key from Learner table
    private int courseId;     // Foreign key from Course table
    private String startDate;
    private String expiryDate;
    private String paymentStatus;

    // Constructor
    public Subscription(int subscriptionId, int learnerId, int courseId, String startDate, String expiryDate, String paymentStatus) {
        this.subscriptionId = subscriptionId;
        this.learnerId = learnerId;
        this.courseId = courseId;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(int subscriptionId) { this.subscriptionId = subscriptionId; }

    public int getLearnerId() { return learnerId; }
    public void setLearnerId(int learnerId) { this.learnerId = learnerId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    @Override
    public String toString() {
        return "Subscription #" + subscriptionId + " [Course ID: " + courseId + ", Learner ID: " + learnerId + "]";
    }
}
