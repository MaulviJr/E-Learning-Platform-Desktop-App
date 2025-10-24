/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms.models;

/**
 *
 * @author abdul
 */

public class Learner {
    private int learnerId;
    private String name;
    private String gender;
    private String dob;
    private String email;
    private String contactNo;
    private String registrationDate;
    private String username;   // For login authentication
    private String password;   // Hashed password

    // Constructor (including username and password)
    public Learner(int learnerId, String name, String gender, String dob, String email, String contactNo, 
                   String registrationDate, String username, String password) {
        this.learnerId = learnerId;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.contactNo = contactNo;
        this.registrationDate = registrationDate;
        this.username = username;
        this.password = password;
    }

    // Overloaded constructor (without username/password, if needed elsewhere)
    public Learner(int learnerId, String name, String gender, String dob, String email, String contactNo, String registrationDate) {
        this(learnerId, name, gender, dob, email, contactNo, registrationDate, null, null);
    }

    // Getters and Setters
    public int getLearnerId() { return learnerId; }
    public void setLearnerId(int learnerId) { this.learnerId = learnerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
