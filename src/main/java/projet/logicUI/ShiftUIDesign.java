package projet.logicUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ShiftUIDesign extends java.lang.Thread{
    private final TranslateTransition translateTransition = new TranslateTransition();
    AnchorPane paneDrag;
    public ShiftUIDesign(AnchorPane anchorPane){
        this.paneDrag = anchorPane;
        this.isDaemon();
    }
    /*
    ######################
    Le Thread sert à déplacer le logiciel avec un AnchorPane
    ######################
     */
    @Override
    public void run() {
            //initialisation des variables
            //On ne peux pas modifier les variables en lambda, c'est pour cela que des tableaux d'une case sont nécessaires
            final double[] x = {0,0};
            final double[] y = {0,0};

            //pression de la souris, sauvegarde de la position initiale
            paneDrag.setOnMousePressed(event -> {
                x[0] = event.getSceneX();
                y[0] = event.getSceneY();
            });

            //mouvement de la souris durant la pression,
            paneDrag.setOnMouseDragged(event -> {
                x[1] = event.getScreenX() - x[0];
                y[1]= event.getScreenY() - y[0];

                paneDrag.getScene().getWindow().setX(x[1]);
                paneDrag.getScene().getWindow().setY(y[1]);
            });
        }

    /**
     * Déplacements d'un noeud quelconque en fonction de 4 ou 5 parametres
     *
     * @param cible Noeud ciblé par la méthode
     *
     * @param conflit Noeud à désactiver temporairement.
     *                Ce parametre est facultatif en fonction du contexte,
     *                il permet d'empecher les interactions avec un composant durant l'animation.
     *
     * @param px Déplacement quantifié en pixel
     * @param duration Durée de l'animation en ms. (entier)
     * @param direction Direction du déplacement
     */

    public void nodeShift(Node cible, Node conflit, int px, int duration, String direction) {
        translateTransition.setDuration(Duration.millis(duration)); //duration
        translateTransition.setNode(cible); //node

        switch (direction) {
            case "gauche" ->
                //déplacement vers la gauche
                    translateTransition.setToX(cible.getTranslateX() - px);
            case "droite" ->
                //déplacement vers la droite
                    translateTransition.setToX(cible.getTranslateX() + px);
            case "haut" ->
                //déplacement vers le haut
                    translateTransition.setToY(cible.getTranslateY() - px);
            case "bas" ->
                //déplacement vers le bas
                    translateTransition.setToY(cible.getTranslateY() + px);
        }

        translateTransition.play();

        if (conflit != null){
            disabledNodeDuration(conflit, duration);
        }

    }
    public void nodeShift(Node cible, int px, int duration, String direction){
        nodeShift(cible, null, px, duration, direction);
    }
    public void disabledNodeDuration(Node conflit, int duration){
        conflit.setDisable(true); //désactive le noeud conflictuel

        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), e -> conflit.setDisable(false));
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
    }
}