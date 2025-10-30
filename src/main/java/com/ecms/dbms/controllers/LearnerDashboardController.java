package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LearnerDashboardController implements Initializable {

    @FXML
    private AnchorPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load the initial view
        showMyCourses();
    }

    @FXML
    void showMyCourses() {
        Utilities.loadPageInto(contentArea, "LearnerMyCourses");
    }

    @FXML
    void showAllCourses() {
        Utilities.loadPageInto(contentArea, "AllCourses");
    }

    @FXML
    void showMyProfile() {
        Utilities.loadPageInto(contentArea, "LearnerProfile");
    }
    
     @FXML
    void logout() throws IOException {
        Utilities.setRoot("LearnerLogin");
        SessionManager.clearSession();
    }
}