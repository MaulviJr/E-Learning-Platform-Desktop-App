/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */
//package com.ecms.models;

public class Instructor {
    private int instructorId;
    private String name;
    private String qualification;
    private String specialization;
    private String email;
    private String contactNo;
    private String joinDate;
        private String username;
              private String password;
    // Constructor
    public Instructor(int instructorId, String name, String qualification, String specialization, String email, String contactNo, String joinDate,String username,String password) {
        this.instructorId = instructorId;
        this.name = name;
        this.qualification = qualification;
        this.specialization = specialization;
        this.email = email;
        this.contactNo = contactNo;
        this.joinDate = joinDate;
        this.username=username;
        this.password=password;
    }

    // Getters and Setters
    public int getInstructorId() { return instructorId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }
    
    public String getUserName() {
        return username;
    }
    
       public String getPassword() {
        return password;
    }
       
    public void setUserName (String username){
        this.username=username;
    }
    
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }
}

