/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//package com.ecms.dbms.controllers;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.Initializable;
//
///**
// * FXML Controller class
// *
// * @author abdul
// */
//public class LoginController implements Initializable {
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    
//}
package com.ecms.dbms.controllers;

import com.ecms.dbms.Utilities;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private Button logInstructor;

    @FXML
    private Button logLearner;
    
    public void navigateToLearner() throws IOException {       
        Utilities.setRoot("LearnerLogin");      
    }
    
      public void navigateToInstructor() throws IOException {       
        Utilities.setRoot("InstructorLogin");      
    }

}
