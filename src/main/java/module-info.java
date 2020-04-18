module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.presentation to javafx.fxml;
    exports org.presentation;
}