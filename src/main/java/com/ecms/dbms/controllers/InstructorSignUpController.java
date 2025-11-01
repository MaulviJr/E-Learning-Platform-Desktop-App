package com.ecms.dbms.controllers;

import com.ecms.dbms.Utilities;
import com.ecms.dbms.ValidationUtil;
import com.ecms.dbms.data.InstructorDAO;
import com.ecms.dbms.models.Instructor;
import com.ecms.dbms.database.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InstructorSignUpController implements Initializable {

    @FXML
    private TextField contactText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField nameText;

    @FXML
    private PasswordField password;

    @FXML
    private TextField qualificationText;

    @FXML
    private TextField specializationText;

    @FXML
    private TextField username;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get database connection
        conn = DatabaseConnection.getConnection();
    }

    // Handle Sign Up button click
    @FXML
    private void handleSignUp() {
        try {
            // 1️⃣ Validate inputs
            System.out.println("I am handling input");
            if (nameText.getText().isEmpty() || emailText.getText().isEmpty() || username.getText().isEmpty()
                    || password.getText().isEmpty() || qualificationText.getText().isEmpty() || specializationText.getText().isEmpty()) {
                Utilities.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all required fields!");
                return;
            }

            if (!ValidationUtil.isValidUsername(username.getText())) {
                Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Username", "Username must be 4–15 characters long and contain only letters, digits, or underscores.");
                return;
            }
            if (!ValidationUtil.isValidPassword(password.getText())) {
                Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Password", "Password must contain at least 6 characters including letters and numbers.");
                return;
            }

            if (!ValidationUtil.isValidEmail(emailText.getText())) {
                Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please enter a valid email address.");
                return;
            }
            Random rand = new Random();
            int instructorId = 1000 + rand.nextInt(9000);
            // 2️⃣ Create Instructor object
            Instructor instructor = new Instructor(
                    instructorId, // Auto-generated ID (if your DB uses AUTO_INCREMENT)
                    nameText.getText(),
                    qualificationText.getText(),
                    specializationText.getText(),
                    emailText.getText(),
                    contactText.getText(),
                    LocalDate.now().toString(), // Current date as join date
                    username.getText(),
                    password.getText()
            );

            // 3️⃣ Insert into database
            InstructorDAO instructorDAO = new InstructorDAO(conn);

            // Optional: check if username exists first
            if (instructorDAO.usernameExists(username.getText())) {
                Utilities.showAlert(Alert.AlertType.ERROR, "Username Taken", "Please choose a different username.");
                return;
            }

            boolean success = instructorDAO.addInstructor(instructor);

            // 4️⃣ Show result message
            if (success) {
                Utilities.showAlert(Alert.AlertType.INFORMATION, "Success", "Instructor registered successfully!");
                clearFields();
                Utilities.setRoot("InstructorLogin");
            } else {
                Utilities.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to register instructor.");
            }

        } catch (Exception e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Exception", "Error: " + e.getMessage());
        }
    }

    // Utility method to clear input fields
    private void clearFields() {
        nameText.clear();
        emailText.clear();
        username.clear();
        password.clear();
        qualificationText.clear();
        specializationText.clear();
        contactText.clear();
    }
    @FXML
    private void navigateLogin() throws IOException {
        Utilities.setRoot("InstructorLogin");
    }

    // Utility method to show alerts
//    private void showAlert(Alert.AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}
