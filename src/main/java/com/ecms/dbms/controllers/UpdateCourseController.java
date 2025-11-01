package com.ecms.dbms.controllers;

import com.ecms.dbms.Utilities;
import com.ecms.dbms.data.CourseDAO;
import com.ecms.dbms.database.DatabaseConnection;
import com.ecms.dbms.models.Course;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for updating an existing course (Instructor side)
 */
public class UpdateCourseController implements Initializable {

    @FXML
    private TextField txtCourseName;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    private Course course; // course currently being edited
    private final CourseDAO courseDAO = new CourseDAO(DatabaseConnection.getConnection());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Optional: set validators or default behavior here
    }

    /**
     * Called by the parent controller to pass the selected course.
     */
    public void setCourse(Course course) {
        this.course = course;
        if (course != null) {
            txtCourseName.setText(course.getCourseName());
            txtCategory.setText(course.getCategory());
            txtDuration.setText(String.valueOf(course.getDuration()));
            txtPrice.setText(String.valueOf(course.getPrice()));
        }
    }

    @FXML
    private void handleSave() {
        if (course == null) return;

        // Input validation
        if (txtCourseName.getText().isEmpty() || txtCategory.getText().isEmpty()
                || txtDuration.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            Utilities.showAlert(Alert.AlertType.WARNING, "Missing Data", "Please fill in all fields.");
            return;
        }

        try {
            course.setCourseName(txtCourseName.getText());
            course.setCategory(txtCategory.getText());
            course.setDuration(Integer.parseInt(txtDuration.getText()));
            course.setPrice(Double.parseDouble(txtPrice.getText()));

            boolean updated = courseDAO.updateCourse(course);
            if (updated) {
                Utilities.showAlert(Alert.AlertType.INFORMATION, "Success", "Course updated successfully!");
                closeWindow();
            } else {
                Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update course. Please try again.");
            }
        } catch (NumberFormatException e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Duration and price must be numeric values.");
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
