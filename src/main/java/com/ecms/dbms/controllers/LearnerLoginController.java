/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.LearnerDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Learner;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class LearnerLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    void moveToLearnerSignUp() throws IOException {
        Utilities.setRoot("LearnerSignUp");
    }
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Connection conn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        conn = DatabaseConnection.getConnection();
    }   
    
     @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
         System.out.println("I am at login");
        if (username.isEmpty() || password.isEmpty()) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter username and password.");
            return;
        }
System.out.println("I am at login2");
        LearnerDAO learnerDAO = new LearnerDAO(conn);
        Learner learner = learnerDAO.loginLearner(username, password);

        if (learner != null) {
               System.out.println("I am at login3");
             Utilities.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + learner.getName() + "!");
            // TODO: Navigate to LearnerDashboard.fxml
              SessionManager.setLoggedInLearner(learner);
            Utilities.setRoot("LearnerDashboard");
            
        } else {
             Utilities.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }
    
}
