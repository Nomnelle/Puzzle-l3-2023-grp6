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
import logic.Shift;
import modele.Grille;

/**
 *
 * @author castagno
 */
public class PuzzleController implements Initializable {
    @FXML
    private GridPane grille; //Grille
    @FXML
    private AnchorPane AnchorPaneBackground; // panneau recouvrant toute la fenêtre
    @FXML
    private AnchorPane anchorPaneDrag; //panneau TOP
    @FXML
    private AnchorPane anchorPaneMid; //panneau contenant undo, menu et le score
    @FXML
    private AnchorPane anchorPaneStats; //panneau contenant undo, menu et le score
    @FXML
    private AnchorPane anchorPaneMenu; //panneau du menu

    @FXML
    private Button buttonClose;
    @FXML
    private Button buttonMenu;
    @FXML
    private Button buttonUndo;
    @FXML
    private Button buttonResume;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonLoad;
    @FXML
    private Button buttonStopAI;
    @FXML
    private Button buttonStats;

    @FXML
    private Label labelScore; // value will be injected by the FXMLLoader

    // variable globale pour initialiser le modèle
    private Grille grilleModele = new Grille();

    // variables globales non définies dans la vue (fichier .fxml)
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c = new Label("2");
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;
    private final Shift shiftLogic = new Shift(); //logique de déplacement

    /*
    Action des boutons
     */
    @FXML
    protected void setButtonClose(){System.exit(0);} //Fermer la fenetre
    @FXML
    protected void setButtonMenu(){
        grille.setDisable(true);
        shiftLogic.nodeShift(anchorPaneMenu, 600, 1000, "bas");
    }
    @FXML
    protected void setButtonPlay(){
        shiftLogic.nodeShift(anchorPaneMenu, 600, 1000, "haut");
        grille.setDisable(false);
    }
    @FXML
    protected void setButtonLoad(){

    }
    @FXML
    protected void setButtonSave(){

    }
    @FXML
    protected void setButtonStopAI(){

    }
    @FXML
    protected void setButtonStatsShow(){

    }
    @FXML
    protected void setButtonUndo(){

    }

    /*
    Initialisation
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buttonClose.getStyleClass().add("buttonClose"); //Style
        buttonMenu.getStyleClass().add("buttonMenu"); //Style
        //anchorPaneMid.getStyleClass().add("anchorPaneMid");
        //anchorPaneStats.getStyleClass().add("anchorPaneStats");


        Shift topAnchorPaneLogic = new Shift();
        topAnchorPaneLogic.anchorPaneShift(anchorPaneDrag); //Déplacer la fenetre


        // TODO

        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p.getStyleClass().add("pane");
        c.getStyleClass().add("tuile");
        grille.getStyleClass().add("gridpane");
        GridPane.setHalignment(c, HPos.CENTER);
        AnchorPaneBackground.getChildren().add(p);
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
                for (int j = 0; j < grille.getRowConstraints().size(); j++) { //pour chaque ligne
                System.out.println("ok1");
                //grille.getChildren().remove(i);

                /*Node tuile = grille.getChildren().get(i);
                 if (tuile != null) {
                 int rowIndex = GridPane.getRowIndex(tuile);
                 int rowEnd = GridPane.getRowIndex(tuile);
                 }*/
                // }
                }
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
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        String touche = ke.getText();
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite

        }
    }
}
