package projet.logicUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Manage the windows moving, and the node animation
 */
public class ShiftUIDesign extends java.lang.Thread{
    AnchorPane paneDrag;
    private double x; private double y;

    /**
     * Constructor of stylistic shifts
     * @param anchorPane that is used to drag the window
     */
    public ShiftUIDesign(AnchorPane anchorPane){
        this.paneDrag = anchorPane;
        this.setDaemon(true);
    }
    /**
     * The Thread is used to move the software with an AnchorPane
     */
    @Override
    public void run() {
        //save initial position of mouse (on scene)
        paneDrag.setOnMousePressed(event -> {
            this.x = event.getSceneX();
            this.y = event.getSceneY();
        });

        //new XY position calculated by subtracting the window position with the mouse position
        paneDrag.setOnMouseDragged(event -> {
            Stage stage = (Stage) paneDrag.getScene().getWindow();
            stage.setX(event.getScreenX() - this.x);
            stage.setY(event.getScreenY() - this.y);
        });
    }

    /**
     * Movements of any node
     *
     * @param cible Node targeted by the method
     *
     * @param conflict Node to temporarily deactivate (can be null).
     *                allows you to prevent interactions with a component during the animation.
     *
     * @param px Displacement (px)
     * @param duration Animation duration in ms (int)
     * @param direction Direction of travel
     */

    public void nodeShift(Node cible, Node conflict, int px, int duration, String direction) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(duration)); //duration
        translateTransition.setNode(cible); //node

        switch (direction) {
            case "gauche" ->
                    translateTransition.setToX(cible.getTranslateX() - px);
            case "droite" ->
                    translateTransition.setToX(cible.getTranslateX() + px);
            case "haut" ->
                    translateTransition.setToY(cible.getTranslateY() - px);
            case "bas" ->
                    translateTransition.setToY(cible.getTranslateY() + px);
        }
        translateTransition.play();

        if (conflict != null){
            disabledNodeDuration(conflict, duration);
        }
    }

    /**
     * Deactivates a node for a specific period of time
     *
     * @param conflict Node to temporarily deactivate
     * @param duration Duration of desactivation in ms (int)
     */
    public void disabledNodeDuration(Node conflict, int duration){
        conflict.setDisable(true); //disable the conflicting node

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(duration), e -> conflict.setDisable(false)));
        timeline.play(); //enable the conflicting node after 'duration'
    }
}