package controller.puzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.TopAnchorPaneLogic;

import java.io.IOException;
import java.util.Objects;

public class PuzzleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PuzzleApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 445, 613);

        stage.setTitle("Puzzle");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);

        try
        {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());
        } catch (Exception e){
            System.err.println("non");
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}