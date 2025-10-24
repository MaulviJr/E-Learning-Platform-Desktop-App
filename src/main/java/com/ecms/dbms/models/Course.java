/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Course {
    private int courseId;
    private String courseName;
    private String description;
    private String category;
    private int duration; // could be in hours or days
    private int instructorId; // foreign key reference to Instructor table
    private String prerequisite;
    private double price;

    // Constructor
    public Course(int courseId, String courseName, String description, String category, int duration, int instructorId, String prerequisite,double price) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.instructorId = instructorId;
        this.prerequisite = prerequisite;
        this.price = price;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getInstructorId() { return instructorId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }

    public String getPrerequisite() { return prerequisite; }
    public void setPrerequisite(String prerequisite) { this.prerequisite = prerequisite; }
    
    
     public double getPrice() { return price; }
     public void setPrice (double price) {
         this.price=price;
     }

    @Override
    public String toString() {
        return courseName + " (" + category + ")";
    }
}
