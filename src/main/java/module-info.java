module com.example.puzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens projet to javafx.fxml;
    exports projet;
    exports projet.modele;
    opens projet.modele to javafx.fxml;
    exports projet.controller;
    opens projet.controller to javafx.fxml;
}