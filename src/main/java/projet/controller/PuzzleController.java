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
import projet.logic.Undo;
import projet.logic.Shift;
import projet.PuzzleApplication;

public class PuzzleController implements Initializable {
    /*
    ######################
    Déclaration des noeuds
    ######################
     */
    @FXML
    private GridPane grille;
    @FXML
    private AnchorPane anchorPaneBackground; // panneau recouvrant toute la fenêtre
    @FXML
    private AnchorPane anchorPaneDrag; //panneau TOP
    @FXML
    private AnchorPane anchorPaneMid; //panneau contenant undo, menu, le score et le chrono
    @FXML
    private AnchorPane anchorPaneStats; //panneau contenant les informations statistiques
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
    private Button buttonCase4;
    @FXML
    private Button buttonCase9;
    @FXML
    private Button buttonCase16;
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
    private int increment = 0;
    /*
    ######################
    Déclarations des classes
    ######################
     */
    private final Shift shift = new Shift(); //Logique de déplacement
    private final Undo undoLogic = new Undo(); //Logique de Undo
    GrilleController grilleController;

    /*
    ######################
    Action des boutons
    ######################
     */
    @FXML
    protected void setButtonClose(){System.exit(0);} //Fermer la fenetre
    @FXML
    protected void setButtonMenu(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "bas");
        anchorPaneMid.setDisable(true);
    }
    /*
    ............
    BOUTON JOUER
    ............
     */
    private void translationAnimationPlay(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "haut"); //Disparition du menu
        shift.disabledNodeDuration(anchorPaneMid, 800); //Desactivation du pane contenant le bouton "menu" durant 800ms
        grille.setDisable(false); //Activation de la grille
    }
    @FXML
    protected void setButtonPlay(){
        //Apparition du choix du nombre de cases
        buttonPlay.setDisable(true);
        buttonCase4.setVisible(true);
        buttonCase9.setVisible(true);
        buttonCase16.setVisible(true);
        buttonCase4.setDisable(false);
        buttonCase9.setDisable(false);
        buttonCase16.setDisable(false);
    }
    @FXML
    protected void buttonCase4(){
        grilleController = new GrilleController(4);
        grilleController.initJeu(grille);
        disableButtonCase();
        translationAnimationPlay();
        buttonPlay.setDisable(false);
        increment++;
    }
    @FXML
    protected void buttonCase9(){
        grilleController = new GrilleController(9);
        grilleController.initJeu(grille);
        disableButtonCase();
        translationAnimationPlay();
        buttonPlay.setDisable(false);
        increment++;
    }
    @FXML
    protected void buttonCase16(){
        grilleController = new GrilleController(16);
        grilleController.initJeu(grille);
        disableButtonCase();
        translationAnimationPlay();
        buttonPlay.setDisable(false);
        increment++;
    }
    private void disableButtonCase(){
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
        buttonCase4.setDisable(true);
        buttonCase9.setDisable(true);
        buttonCase16.setDisable(true);
    }
    public int getIncrement(){
        return increment;
    }
    /*
    ............
    BOUTON Load et Save
    ............
     */
    @FXML
    protected void setButtonLoad(){

    }
    @FXML
    protected void setButtonSave(){

    }
    /*
    ............
    BOUTON Stop AI
    ............
     */
    @FXML
    protected void setButtonStopAI(){

    }
    /*
    ............
    BOUTON Stats
    ............
     */
    @FXML
    protected void setButtonStatsShow(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneStats, 800);
    }
    /*
    ............
    BOUTON Undo
    ............
     */
    @FXML
    protected void setButtonUndo(){
        //Désactive le bouton undo au bout de 4 appuis
        undoLogic.setCountPlus(buttonUndo);

        //Annule le dernier mouvement

        //...

    }
    /*
    ............
    BOUTON Back
    ............
     */
    @FXML
    protected void setbuttonBack(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "bas");
        anchorPaneStats.setDisable(true);
    }
    /*
    ............
    BOUTON Style
    ............
     */
    @FXML
    protected void setButtonStyle(){
        PuzzleApplication.styleChanger();
    }

    /*
    ######################
    Initialisation
    ######################
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Initialisation du style des Boutons
        buttonClose.getStyleClass().add("buttonClose");
        buttonMenu.getStyleClass().add("buttonMenu");
        buttonUndo.getStyleClass().add("buttonUndo");
        buttonPlay.getStyleClass().add("buttonPlay");
        buttonCase4.getStyleClass().add("buttonPlay");
        buttonCase9.getStyleClass().add("buttonPlay");
        buttonCase16.getStyleClass().add("buttonPlay");

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
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
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
    Initialisation de l'état des cases
    */


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

/*
        grille.setOnDragDropped(MouseEvent -> {
            GrilleController grille16 = new GrilleController(); //Création de la grille
            if (grille.getOnDragDropped().equals("*.jpg")){
                grille16.cutPic();
            }
        });


 */

        System.out.println("Clic de souris");
        try {
            labelScore.setText(Integer.toString(Integer.parseInt(labelScore.getText()) + 1));
        } catch (NumberFormatException nfe) {
            System.err.println("AHHHHHRRRGGGG");
        }
    }
}
