/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.LearnerDAO;
import com.ecms.dbms.data.SubscriptionDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.EnrolledLearner;
import com.ecms.dbms.models.Learner;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class MyLearnersController implements Initializable {
@FXML private TableView<EnrolledLearner> learnerTable;
@FXML private TableColumn<EnrolledLearner, String> colLearnerName;
@FXML private TableColumn<EnrolledLearner, String> colCourseName;
@FXML private TableColumn<EnrolledLearner, String> colInstructorName;

@Override
public void initialize(URL url, ResourceBundle rb) {
    colLearnerName.setCellValueFactory(new PropertyValueFactory<>("learnerName"));
    colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    colInstructorName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));

    int instructorId = SessionManager.getLoggedInInstructor().getInstructorId();
    SubscriptionDAO dao = new SubscriptionDAO(DatabaseConnection.getConnection());
    List<EnrolledLearner> list = dao.getLearnersByInstructorId(instructorId);
    learnerTable.setItems(FXCollections.observableArrayList(list));
}  
    
}
