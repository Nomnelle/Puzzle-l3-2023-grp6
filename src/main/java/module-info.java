module com.example.puzzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.jetbrains.annotations;

    opens projet to javafx.fxml;
    exports projet;
    exports projet.controller;
    opens projet.controller to javafx.fxml;
    exports projet.modele.ia;
    opens projet.modele.ia to javafx.fxml;
    exports projet.modele.game;
    opens projet.modele.game to javafx.fxml;
    exports projet.logicUI;
    opens projet.logicUI to javafx.fxml;
}