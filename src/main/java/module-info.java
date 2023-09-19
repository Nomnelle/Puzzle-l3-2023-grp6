module com.example.puzzle {
    requires javafx.controls;
    requires javafx.fxml;


    opens controller.puzzle to javafx.fxml;
    exports controller.puzzle;
}