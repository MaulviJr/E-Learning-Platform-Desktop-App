module com.ecms.dbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;

        requires java.sql;

    
    opens com.ecms.dbms to javafx.graphics, javafx.fxml;
    opens com.ecms.dbms.controllers to javafx.fxml;
    opens com.ecms.dbms.models to javafx.base;
}
