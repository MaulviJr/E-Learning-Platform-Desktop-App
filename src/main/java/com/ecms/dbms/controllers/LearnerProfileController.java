package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.models.Learner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LearnerProfileController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Learner learner = SessionManager.getLoggedInLearner();
        if (learner != null) {
            nameLabel.setText(learner.getName());
            emailLabel.setText(learner.getEmail());
            usernameLabel.setText(learner.getUsername());
        }
    }
}