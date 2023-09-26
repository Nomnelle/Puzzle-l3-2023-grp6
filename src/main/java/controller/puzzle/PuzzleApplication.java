package controller.puzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class PuzzleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PuzzleApplication.class.getResource("puzzle-view.fxml")); //Vue
        Scene scene = new Scene(fxmlLoader.load(), 445, 613); //Scene

        //Parametres généraux du stage
        stage.setTitle("Puzzle");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm()); //Css
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}