package projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class PuzzleApplication extends Application {
    private static Scene SCENE;
    private static int STYLE = 1;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PuzzleApplication.class.getResource("puzzle-view.fxml")); //Vue
        SCENE = new Scene(fxmlLoader.load(), 380, 520); //Scene

        //Parametres généraux du stage
        stage.setTitle("Puzzle");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        styleChanger();
        stage.setScene(SCENE);

        stage.show();
    }

    /*
    Au lancement, le style est la feuille n°1, si la méthode est appelé ultérieurement, le style change.
     */

    public static void styleChanger(){
        SCENE.getStylesheets().clear();
        try {
            SCENE.getStylesheets().add(Objects.requireNonNull(PuzzleApplication.class.getResource("/css/style" + STYLE + ".css")).toExternalForm());
        } catch (RuntimeException e) {
            STYLE = 1;
            SCENE.getStylesheets().add(Objects.requireNonNull(PuzzleApplication.class.getResource("/css/style" + STYLE + ".css")).toExternalForm());
        }
        STYLE += 1;
    }

    public static void main(String[] args) {
        launch();
    }
}