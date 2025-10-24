/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ecms.dbms.controllers;

import com.ecms.dbms.Utilities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class InstructorDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane contentArea;
     
     

@FXML
void showMyCourses() {
    Utilities.loadPageInto(contentArea, "MyCourses");
}



@FXML
void showMyLearners() {
    Utilities.loadPageInto(contentArea, "MyLearners");
}

@FXML
void showMyProfile() {
    System.out.println("profuile");
    Utilities.loadPageInto(contentArea, "MyProfile");
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//          showMyProfile();
    }    
    
}
