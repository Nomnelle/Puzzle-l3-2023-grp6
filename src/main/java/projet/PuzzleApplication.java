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
        FXMLLoader fxmlLoader = new FXMLLoader(PuzzleApplication.class.getResource("puzzle-view.fxml"));
        SCENE = new Scene(fxmlLoader.load(), 380, 520); //Scene

        //General parameter
        stage.setTitle("Puzzle");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        styleSwitch();
        stage.setScene(SCENE);

        stage.show();
    }
    public static Scene getScene(){
        return SCENE;
    }

    /**
     This function allows you to change the style of the graphical interface
     */
    public static void styleSwitch(){
        //At launch, the style is sheet #1, if the method is called later, the style changes.
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