package com.ecms.dbms.controllers;

import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.LearnerDAO;
import com.ecms.dbms.models.Learner;
import com.ecms.dbms.database.DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.ecms.dbms.ValidationUtil;
import java.io.IOException;

public class LearnerSignUpController implements Initializable {

    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
private static final String DOB_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // yyyy-mm-dd format
private static final String CONTACT_REGEX = "^(\\+92|0)?3\\d{9}$"; // Pakistani mobile pattern (optional)

    
    @FXML
    private TextField DOBText;

    @FXML
    private TextField contactText;

    @FXML
    private TextField emailText;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField nameText;

    @FXML
      private PasswordField password;
//    private TextField password;

    @FXML
    private TextField username;

    private Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Connect to database
        conn = DatabaseConnection.getConnection();

        // Populate gender dropdown
        genderComboBox.getItems().addAll("M", "F");
        genderComboBox.setValue("M"); // Default selection
    }
    
     @FXML
    private void navigateLogin() throws IOException {
        Utilities.setRoot("LearnerLogin");
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        try {
            // 1️⃣ Input validation
            if (nameText.getText().isEmpty() || emailText.getText().isEmpty() || username.getText().isEmpty() ||
                password.getText().isEmpty() || DOBText.getText().isEmpty() || genderComboBox.getValue() == null) {
               Utilities.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all required fields!");
                return;
            }
            
            if (!ValidationUtil.isValidEmail(emailText.getText())) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        if (!ValidationUtil.isValidDOB(DOBText.getText())) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Invalid DOB Format", "Use yyyy-mm-dd format (e.g., 2001-08-15).");
            return;
        }

        if (!ValidationUtil.isValidContact(contactText.getText())) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Contact", "Please enter a valid Pakistani contact number.");
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

            
            Random rand = new Random();
int learnerId = 1000 + rand.nextInt(9000);

            // 2️⃣ Create Learner object
            Learner learner = new Learner(
                    learnerId, // Auto-increment ID
                    nameText.getText(),
                    genderComboBox.getValue(),
                    DOBText.getText(),
                    emailText.getText(),
                    contactText.getText(),
                    LocalDate.now().toString(), // Registration date = today
                    username.getText(),
                    password.getText()
            );

            // 3️⃣ Use DAO to save learner
            LearnerDAO learnerDAO = new LearnerDAO(conn);

            // Optional: check if username already exists
            if (learnerDAO.usernameExists(username.getText())) {
                Utilities.showAlert(Alert.AlertType.ERROR, "Username Taken", "This username is already in use. Please choose another.");
                return;
            }

            boolean success = learnerDAO.addLearner(learner);

            // 4️⃣ Show result message
            if (success) {
               Utilities.showAlert(Alert.AlertType.INFORMATION, "Success", "Learner registered successfully!");
                clearFields();
                 Utilities.setRoot("LearnerLogin");
            } else {
Utilities.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to register learner.");
            }

        } catch (Exception e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
        }
    }

    // Utility method to clear all input fields
    private void clearFields() {
        nameText.clear();
        emailText.clear();
        username.clear();
        password.clear();
        contactText.clear();
        DOBText.clear();
        genderComboBox.setValue("Male");
    }

    
}
