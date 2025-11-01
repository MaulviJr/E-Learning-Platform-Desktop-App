/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import com.ecms.dbms.ValidationUtil;
import com.ecms.dbms.data.InstructorDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Instructor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class InstructorLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button loginBtn;
     @FXML
     private Button SignUpBtn;
    @FXML
    private PasswordField passwordField;
private Connection conn;
    @FXML
    private TextField usernameField;

    @FXML
    void moveToInstructorSignUp() throws IOException {
        Utilities.setRoot("InstructorSignUp");
    }
    
//    String userName=usernameText.getText().trim();
//    String password=passwordText.getText().trim();
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = DatabaseConnection.getConnection();
    } 
    
    @FXML
       private void navigateHome() throws IOException {
        Utilities.setRoot("Login");
    }
    @FXML
    private void handleLogin() throws IOException {
        
//        if (!ValidationUtil.isValidUsername(usernameField.getText())) {
//    Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Username", "Username must be 4–15 characters long and contain only letters, digits, or underscores.");
//    return;
//}
//if (!ValidationUtil.isValidPassword(passwordField.getText())) {
//    Utilities.showAlert(Alert.AlertType.WARNING, "Invalid Password", "Password must contain at least 6 characters including letters and numbers.");
//    return;
//}
        
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter username and password.");
            return;
        }

        InstructorDAO instructorDAO = new InstructorDAO(conn);
        Instructor instructor = instructorDAO.loginInstructor(username, password);
        

        if (instructor != null) {
            Utilities.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + instructor.getName() + "!");
            // TODO: Navigate to InstructorDashboard.fxml
            SessionManager.setLoggedInInstructor(instructor);
                Utilities.setRoot("InstructorDashboard");
        } else {
            Utilities.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    
    
 
    
}
