package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.data.CourseDAO;
import com.ecms.dbms.data.SubscriptionDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Course;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LearnerMyCoursesController implements Initializable {

    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, Integer> colId;
    @FXML
    private TableColumn<Course, String> colTitle;
    @FXML
    private TableColumn<Course, String> colCategory;
    @FXML
    private TableColumn<Course, Integer> colDuration;
    @FXML
    private TableColumn<Course, Double> colProgress;

   @Override
public void initialize(URL url, ResourceBundle rb) {
    colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
    colTitle.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

    int learnerId = SessionManager.getLoggedInLearner().getLearnerId();

    SubscriptionDAO subscriptionDAO = new SubscriptionDAO(DatabaseConnection.getConnection());
    List<Course> courses = subscriptionDAO.getCoursesByLearnerId(learnerId);

    courseTable.setItems(FXCollections.observableArrayList(courses));
}

}