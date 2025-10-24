package com.ecms.dbms;

import com.ecms.dbms.database.DatabaseConnection;
import java.sql.Connection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        
         scene = new Scene(Utilities.loadFXML("login"));
         
//        var javaVersion = SystemInfo.javaVersion();
//        var javafxVersion = SystemInfo.javafxVersion();
//
//        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
//      Connection conn = DatabaseConnection.getConnection();
//        if (conn != null) {
//            System.out.println("Database connection is working fine ✅");
//            System.out.println(conn);
//        }
//    }

}
    
}