module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires postgresql;

    opens org.application to javafx.base, javafx.fxml;
    opens org.presentation to javafx.fxml;
    exports org.presentation;
}