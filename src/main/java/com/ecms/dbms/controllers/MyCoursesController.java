/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.CourseDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Course;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class MyCoursesController implements Initializable {
@FXML
void openAddCourse() {
     try {
        // Load the AddCourse.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCourse.fxml"));
        Parent root = loader.load();

        // Create a new Stage (a new popup window)
        Stage stage = new Stage();
        stage.setTitle("Add New Course");
        stage.setScene(new Scene(root));
        stage.setResizable(false); // optional
        stage.initModality(Modality.APPLICATION_MODAL); // prevents clicking background
        stage.showAndWait(); // wait until dialog is closed before returning

    } catch (IOException e) {
        e.printStackTrace();
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Unable to open Add Course window.");
    }
}
    /**
     * Initializes the controller class.
     */
      @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> colId;
    @FXML private TableColumn<Course, String> colTitle;
    @FXML private TableColumn<Course, String> colCategory;
    @FXML private TableColumn<Course, String> colDuration;
     @FXML private TableColumn<Course, Integer> colPrice;
     @FXML private Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
    colTitle.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    // populate courses for this instructor
    int instructorId = SessionManager.getLoggedInInstructor().getInstructorId();
    CourseDAO dao = new CourseDAO(DatabaseConnection.getConnection());
    List<Course> courses = dao.getCoursesByInstructorId(instructorId);
    courseTable.setItems(FXCollections.observableArrayList(courses));

    // hide button by default
    deleteBtn.setVisible(false);

    // listen for row selection
    courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
        deleteBtn.setVisible(newSel != null);
    });
    }
    
    @FXML
void handleDeleteCourse(ActionEvent event) {
    Course selected = courseTable.getSelectionModel().getSelectedItem();
    if (selected == null) return;

    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Confirm Delete");
    confirm.setHeaderText("Are you sure you want to delete this course?");
    confirm.setContentText(selected.getCourseName());

    confirm.showAndWait().ifPresent(response -> {
        if (response.getText().equals("OK")) {
            CourseDAO dao = new CourseDAO(DatabaseConnection.getConnection());
            boolean success = dao.deleteCourse(selected.getCourseId());
            if (success) {
                courseTable.getItems().remove(selected);
                Utilities.showAlert(Alert.AlertType.INFORMATION, "Deleted", "Course deleted successfully.");
            } else {
                Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete course.");
            }
        }
    });
}
    
}
