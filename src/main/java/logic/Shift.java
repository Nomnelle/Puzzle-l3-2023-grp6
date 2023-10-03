package logic;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.function.Function;

public class ShiftLogic {

    /*
    Déplacements du logiciel avec un AnchorPane
     */
    public void anchorPaneShift(AnchorPane paneDrag){
        //initialisation des variables
        //On ne peux pas modifier les variables en lambda, c'est pour cela que des tableaux sont nécessaires
        final double[] x = {0};
        final double[] y = {0};

        //pression de la souris, sauvegarde de la position initiale
        paneDrag.setOnMousePressed(event -> {
                    x[0] = event.getSceneX();
                    y[0] = event.getSceneY();
        });

        //mouvement de la souris durant la pression,
        paneDrag.setOnMouseDragged(event -> {
            double a = event.getScreenX() - x[0];
            double b = event.getScreenY() - y[0];
            paneDrag.getScene().getWindow().setX(a);
            paneDrag.getScene().getWindow().setY(b);
        });
    }

    /**
     * Déplacements d'un noeud quelconque en fonction de 4 parametres
     *
     * @param node noeud quelconque
     * @param px Déplacement quantifié en pixel
     * @param duration Durée de l'animation en ms (Nombre entier)
     * @param direction Direction vers laquelle le noeud est envoyé
     */

    public void nodeShift(Node node, int px, int duration, String direction) {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(duration)); //duration
        translateTransition.setNode(node); //node

        switch (direction) {
            case "gauche" ->
                //déplacement vers la gauche
                    translateTransition.setToX(node.getTranslateX() - px);
            case "droite" ->
                //déplacement vers la droite
                    translateTransition.setToX(node.getTranslateX() + px);
            case "haut" ->
                //déplacement vers le haut
                    translateTransition.setToY(node.getTranslateY() - px);
            case "bas" ->
                //déplacement vers le bas
                    translateTransition.setToY(node.getTranslateY() + px);
        }
        translateTransition.play();
    }
}