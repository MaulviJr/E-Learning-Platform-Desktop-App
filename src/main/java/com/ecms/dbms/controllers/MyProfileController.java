package com.ecms.dbms.controllers;

import com.ecms.dbms.models.Instructor;
import com.ecms.dbms.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {
    @FXML private Label lblName;
    @FXML private Label lblEmail;
    @FXML private Label lblQualification;
    @FXML private Label lblSpecialization;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Instructor current = SessionManager.getLoggedInInstructor();
        if (current != null) {
            lblName.setText(current.getName());
            lblEmail.setText(current.getEmail());
            lblQualification.setText(current.getQualification());
            lblSpecialization.setText(current.getSpecialization());
        }
    }
}
