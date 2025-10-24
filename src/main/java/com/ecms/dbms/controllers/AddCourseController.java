/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


/**
 * FXML Controller class
 *
 * @author abdul
 */
package com.ecms.dbms.controllers;


import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.CourseDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Course;
import com.ecms.dbms.models.Instructor;
import com.ecms.dbms.SessionManager;
import java.sql.Connection;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddCourseController {

    @FXML
    private TextField courseNameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField prerequisiteField;

    @FXML
    private TextField priceField;

    // When instructor clicks “Add Course”
    @FXML
    private void handleAddCourse() {
        String courseName = courseNameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String category = categoryField.getText().trim();
        String durationText = durationField.getText().trim();
        String prerequisite = prerequisiteField.getText().trim();
        String priceText = priceField.getText().trim();

        // 🔍 Basic validation
        if (courseName.isEmpty() || description.isEmpty() || category.isEmpty() ||
            durationText.isEmpty() || priceText.isEmpty()) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all required fields.");
            return;
        }

        int duration;
        double price;
        try {
            duration = Integer.parseInt(durationText);
        } catch (NumberFormatException e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Duration must be a number.");
            return;
        }

        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Price must be a valid number.");
            return;
        }

        // 🔑 Get instructor ID from SessionManager
        Instructor instructor= SessionManager.getLoggedInInstructor();
        if (instructor==null) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Instructor session not found.");
            return;
        }
         int instructorId=instructor.getInstructorId();
    

        // 🎲 Generate random course ID
        int courseId = new Random().nextInt(90000) + 10000;

        // Create Course object
        Course course = new Course(courseId, courseName, description, category, duration, instructorId, prerequisite, price);

        // Save to DB
        try (Connection conn = DatabaseConnection.getConnection()) {
            CourseDAO dao = new CourseDAO(conn);
            boolean success = dao.addCourse(course);

            if (success) {
                Utilities.showAlert(Alert.AlertType.INFORMATION, "Success", "Course added successfully!");
                clearFields();
            } else {
                Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add course. Please try again.");
            }
        } catch (Exception e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            e.printStackTrace();
        }
    }

    // Clear input fields after adding a course
    private void clearFields() {
        courseNameField.clear();
        descriptionArea.clear();
        categoryField.clear();
        durationField.clear();
        prerequisiteField.clear();
        priceField.clear();
    }
}

