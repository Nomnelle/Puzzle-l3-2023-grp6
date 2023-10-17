package projet.controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import projet.logic.UndoLogic;
import projet.logic.Shift;
import projet.modele.Grille;
import projet.PuzzleApplication;

public class PuzzleController implements Initializable {
    @FXML
    private GridPane grille; //Grille
    @FXML
    private AnchorPane anchorPaneBackground; // panneau recouvrant toute la fenêtre
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
    private Button buttonPlay;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonLoad;
    @FXML
    private Button buttonStopAI;
    @FXML
    private Button buttonStats;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonStyle;

    @FXML
    private Label labelScore;

    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    @FXML
    private Line line4;

    /*
    Classes Logiques
     */

    private final Shift shift = new Shift(); //Logique de déplacement
    private final UndoLogic undoLogic = new UndoLogic(); //Logique de Undo

    /*

     */
    // variable globale pour initialiser le modèle

    // variables globales non définies dans la vue (fichier .fxml)
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c = new Label("2");
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;



    /*
    Action des boutons
     */
    @FXML
    protected void setButtonClose(){System.exit(0);} //Fermer la fenetre
    @FXML
    protected void setButtonMenu(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "bas");
        anchorPaneMid.setDisable(true);
    }
    @FXML
    protected void setButtonPlay(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneMid, 800);
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
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneStats, 800);
    }
    @FXML
    protected void setButtonUndo(){
        //Désactive le bouton undo au bout de 4 appuis
        undoLogic.setCountPlus(buttonUndo);

        //Annule le dernier mouvement

        //...

    }
    @FXML
    protected void setbuttonBack(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "bas");
        anchorPaneStats.setDisable(true);
    }

    @FXML
    protected void setButtonStyle(){
        PuzzleApplication.styleChanger();
    }

    /*
    Initialisation
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialisation du style des Boutons
        buttonClose.getStyleClass().add("buttonClose");
        buttonMenu.getStyleClass().add("buttonMenu");
        buttonUndo.getStyleClass().add("buttonUndo");
        buttonPlay.getStyleClass().add("buttonPlay");
        buttonLoad.getStyleClass().add("buttonLoad");
        buttonSave.getStyleClass().add("buttonSave");
        buttonStopAI.getStyleClass().add("buttonStopAI");
        buttonStats.getStyleClass().add("buttonStats");
        buttonBack.getStyleClass().add("buttonBack");
        buttonStyle.getStyleClass().add("buttonStyle");

        //Initialisation du style des AnchorPanes
        anchorPaneBackground.getStyleClass().add("anchorPaneBackground");
        anchorPaneDrag.getStyleClass().add("anchorPaneDrag");
        anchorPaneMid.getStyleClass().add("anchorPaneMid");
        anchorPaneMenu.getStyleClass().add("anchorPaneMenu");
        anchorPaneStats.getStyleClass().add("anchorPaneStats");

        //Initialisation du style de la Grille
        grille.getStyleClass().add("grille");

        //Initialisation du style des Lignes
        line1.getStyleClass().add("lines");
        line2.getStyleClass().add("lines");
        line3.getStyleClass().add("lines");
        line4.getStyleClass().add("lines");

        //Initialisation de l'état des noeuds
        anchorPaneStats.setLayoutY(160 + 600); //Position du panneau stats à l'ouverture (160 est l'état normal)
        anchorPaneMid.setDisable(true);
        anchorPaneStats.setVisible(true);
        anchorPaneMenu.setVisible(true);
        shift.anchorPaneShift(anchorPaneDrag); //Permet le déplacement de la fenetre


/*

La méthode initialize du contrôleur vérifiera l’existence du modèle
sérialisé (i.e. du fichier modele.ser)

 */


/*

Si le fichier existe, elle désérialise le modèle et elle met à jour la
vue en fonction de ce que contient le modèle

 */



        /*
        Initialisation de l'état des panneaux
         */

        p.getStyleClass().add("pane");
        c.getStyleClass().add("tuile");

        anchorPaneBackground.getChildren().add(p);
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
    @FXML
    private void handleButtonAction(MouseEvent event ) {
        System.out.println("Clic de souris");
        try {
            labelScore.setText(Integer.toString(Integer.parseInt(labelScore.getText()) + 1));
        } catch (NumberFormatException nfe) {
            System.err.println("AHHHHHRRRGGGG");
        }
    }
}
