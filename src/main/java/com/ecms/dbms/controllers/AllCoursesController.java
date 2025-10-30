package com.ecms.dbms.controllers;

import com.ecms.dbms.SessionManager;
import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.CourseDAO;
import com.ecms.dbms.data.SubscriptionDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Course;
import com.ecms.dbms.models.Subscription;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllCoursesController implements Initializable {

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
    private TableColumn<Course, Double> colPrice;
    @FXML
    private Button subscribeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subscribeBtn.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        CourseDAO courseDAO = new CourseDAO(DatabaseConnection.getConnection());
        List<Course> courses = courseDAO.getAllCourses();
        courseTable.setItems(FXCollections.observableArrayList(courses));

        // Enable button when a course is selected
        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            subscribeBtn.setDisable(newSel == null);
        });
    }

    @FXML
    private void handleSubscribe() {
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            Utilities.showAlert(Alert.AlertType.WARNING, "No Course Selected", "Please select a course to subscribe.");
            return;
        }

        int learnerId = SessionManager.getLoggedInLearner().getLearnerId();
        int courseId = selectedCourse.getCourseId();

        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(DatabaseConnection.getConnection());

        // Check for duplicate subscription
        if (subscriptionDAO.isAlreadySubscribed(learnerId, courseId)) {
            Utilities.showAlert(Alert.AlertType.INFORMATION, "Already Subscribed",
                    "You are already subscribed to this course.");
            return;
        }

        // Use random ID for now (can later switch to AUTO_INCREMENT)
        int subscriptionId = (int) (Math.random() * 1_000_000);
        String startDate = LocalDate.now().toString();
        String expiryDate = LocalDate.now().plusYears(1).toString();

        Subscription newSubscription = new Subscription(subscriptionId, learnerId, courseId, startDate, expiryDate, "Paid");

        if (subscriptionDAO.addSubscription(newSubscription)) {
            Utilities.showAlert(Alert.AlertType.INFORMATION, "Success",
                    "You have successfully subscribed to " + selectedCourse.getCourseName());
        } else {
            Utilities.showAlert(Alert.AlertType.ERROR, "Failed", "Subscription failed. Please try again.");
        }
    }
}
