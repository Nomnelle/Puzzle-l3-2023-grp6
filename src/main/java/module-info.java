module com.example.puzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens controller.puzzle to javafx.fxml;
    exports controller.puzzle;
    exports modele;
    opens modele to javafx.fxml;
}