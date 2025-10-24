/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author abdul
 */
public class Utilities {

    private Utilities() {
    
    }
    
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
    public static void setRoot(String fxml) throws IOException {

    Parent root = loadFXML(fxml);
    App.scene.setRoot(root);
    
    // Get the stage and dynamically resize it based on the root's size
    Stage stage = (Stage) App.scene.getWindow();
//    Match the preferred size of the scene.
    stage.sizeToScene();
    
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
//        System.out.println("fxmlLoader: " + fxmlLoader);
        return fxmlLoader.load();
    }
    
    public static void loadPageInto(AnchorPane targetPane, String fxmlFile) {
    try {
        Parent root = loadFXML(fxmlFile);
        targetPane.getChildren().setAll(root);
    } catch (IOException e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "Could not load page: " + fxmlFile);
    }
}
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert byte array into hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage());
        }
    }
    
}
