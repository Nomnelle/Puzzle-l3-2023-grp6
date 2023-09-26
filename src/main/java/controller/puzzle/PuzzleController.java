package controller.puzzle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.TopAnchorPaneLogic;
import modele.Grille;

/**
 *
 * @author castagno
 */
public class PuzzleController implements Initializable {
    @FXML
    private Label score; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille;
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    @FXML
    private Button closeButton;
    @FXML
    private AnchorPane panDrag;
    @FXML
    private Button menuButton;
    @FXML
    private AnchorPane anchorPaneOfButton;

    // variable globale pour initialiser le modèle
    private Grille grilleModele = new Grille();

    // variables globales non définies dans la vue (fichier .fxml)
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c = new Label("2");
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
//SUPRIMME
       anchorPaneOfButton.setVisible(false);

                //ok

        closeButton.getStyleClass().add("closeButton"); //Style du bouton close
        menuButton.getStyleClass().add("menuButton");
        TopAnchorPaneLogic topAnchorPaneLogic = new TopAnchorPaneLogic();
        topAnchorPaneLogic.anchorPaneDrag(panDrag); //Permet de déplacer le jeu


        // TODO

        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p.getStyleClass().add("pane");
        c.getStyleClass().add("tuile");
        grille.getStyleClass().add("gridpane");
        GridPane.setHalignment(c, HPos.CENTER);
        fond.getChildren().add(p);
        p.getChildren().add(c);

        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p.setLayoutX(x);
        p.setLayoutY(y);
        p.setVisible(true);
        c.setVisible(true);
    }

    /*
     * Méthodes listeners pour gérer les événements (portent les mêmes noms que
     * dans Scene Builder
     */
    @FXML
    private void handleDragAction(MouseEvent event) {
        System.out.println("Glisser/déposer sur la grille avec la souris");
        double x = event.getX();//translation en abscisse
        double y = event.getY();//translation en ordonnée
        if (x > y) {
            for (int i = 0; i < grille.getChildren().size(); i++) { //pour chaque colonne
                //for (int j = 0; j < grille.getRowConstraints().size(); j++) { //pour chaque ligne
                System.out.println("ok1");
                grille.getChildren().remove(i);

                /*Node tuile = grille.getChildren().get(i);
                 if (tuile != null) {
                 int rowIndex = GridPane.getRowIndex(tuile);
                 int rowEnd = GridPane.getRowIndex(tuile);
                 }*/
                // }
            }
        } else if (x < y) {
            System.out.println("ok2");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Pane p = new Pane();
                    p.getStyleClass().add("pane");
                    grille.add(p, i, j);
                    p.setVisible(true);
                    grille.getStyleClass().add("gridpane");
                }
            }
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        String touche = ke.getText();
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite

        }
    }
    @FXML
    protected void setCloseButton(){
        System.exit(0);
    }
    @FXML
    protected void setMenuButton(){
        grille.setVisible(false);
        anchorPaneOfButton.setVisible(true);

    }
}
