package projet.controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import projet.logic.Shift;
import projet.PuzzleApplication;
import projet.modele.game.Chrono;
import projet.modele.game.Grille;

public class PuzzleController implements Initializable {
    /*
    ====================
    Nodes
    ====================
     */
    @FXML
    private GridPane gridPane;
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
    @FXML
    private Label labelChrono;

    private final Shift shift = new Shift(); //Logique de déplacement
    private Chrono chrono;
    GrilleController grilleController;
    private int increment = 0;
    public int getIncrement(){
        return increment;
    }
    /*
    ====================
    Button action
    ====================
     */
    @FXML
    protected void setButtonClose(){System.exit(0);}
    /*
    Play and Menu buttons
     */
    @FXML
    protected void setButtonMenu(){
        grilleController.isPaused(true);
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "bas");
        anchorPaneMid.setDisable(true);
        buttonPlay.setDisable(false);
    }
    @FXML
    protected void setButtonPlay(){
        if (grilleController !=null && grilleController.gameExist()){
            //Return to the game
            translationAnimationPlay();
            grilleController.isPaused(false);
        } else {
            //Show the choice of the number of cases
            buttonPlay.setDisable(true);
            buttonCase4.setVisible(true);
            buttonCase9.setVisible(true);
            buttonCase16.setVisible(true);
            buttonCase4.setDisable(false);
            buttonCase9.setDisable(false);
            buttonCase16.setDisable(false);
        }
    }
    /*
    Cases choice buttons
     */
    @FXML
    protected void buttonCase4(){
        increment++;

        grilleController = new GrilleController(4, Grille.getInstance(2, this), gridPane);

        disableButtonCase();
        translationAnimationPlay();
        initializeKeyListener();

        grilleController.isPaused(false);

        chrono = new Chrono(labelChrono);
    }
    @FXML
    protected void buttonCase9(){
        increment++;

        grilleController = new GrilleController(9, Grille.getInstance(3, this), gridPane);

        disableButtonCase();
        translationAnimationPlay();
        initializeKeyListener();

        grilleController.isPaused(false);

        chrono = new Chrono(labelChrono);
    }
    @FXML
    protected void buttonCase16(){
        increment++;

        grilleController = new GrilleController(16, Grille.getInstance(4, this), gridPane);

        disableButtonCase();
        translationAnimationPlay();
        initializeKeyListener();

        grilleController.isPaused(false);

        chrono = new Chrono(labelChrono);
    }
    /*
    Load et Save buttons
     */
    @FXML
    protected void setButtonLoad(){
    //La méthode initialize du contrôleur vérifiera l’existence du modèle sérialisé (i.e. du fichier modele.ser)
    //Si le fichier existe, elle désérialise le modèle et elle met à jour la vue en fonction de ce que contient le modèle


        translationAnimationPlay();
        initializeKeyListener();
    }
    @FXML
    protected void setButtonSave(){

    }
    /*
    BOUTON Stop AI
     */
    @FXML
    protected void setButtonStopAI(){

    }
    /*
    BOUTON Stats
     */
    @FXML
    protected void setButtonStatsShow(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneStats, 800);
    }
    /*
    BOUTON Undo
     */
    @FXML
    protected void setButtonUndo(){
        //Deactivates the undo button after 4 uses
        switch (grilleController.getUndoCount()){
            case 0 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (3)");}
                break;
            case 1 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (2)");}
                break;
            case 2 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (1)");}
                break;
            case 3 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (0)");buttonUndo.setDisable(true);}
                break;
        }
    }
    /*
    Back button
     */
    @FXML
    protected void setButtonBack(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "bas");
        anchorPaneStats.setDisable(true);
    }
    /*
    Style button
     */
    @FXML
    protected void setButtonStyle(){
        PuzzleApplication.styleChanger();
    }
    /*
    ====================
    Initialisation
    ====================
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initializing the Button Style
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
        //Initializing the style of AnchorPanes
        anchorPaneBackground.getStyleClass().add("anchorPaneBackground");
        anchorPaneDrag.getStyleClass().add("anchorPaneDrag");
        anchorPaneMid.getStyleClass().add("anchorPaneMid");
        anchorPaneMenu.getStyleClass().add("anchorPaneMenu");
        anchorPaneStats.getStyleClass().add("anchorPaneStats");
        //Initializing the style of the grid
        gridPane.getStyleClass().add("grid");
        //Initializing the style of Lines
        line1.getStyleClass().add("lines");
        line2.getStyleClass().add("lines");
        line3.getStyleClass().add("lines");
        line4.getStyleClass().add("lines");
        //Initializing the state of nodes
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
        anchorPaneStats.setLayoutY(160 + 600); //Position of the stats panel at opening (160 is the normal state)
        anchorPaneMid.setDisable(true);
        anchorPaneStats.setVisible(true);
        anchorPaneMenu.setVisible(true);
        shift.anchorPaneShift(anchorPaneDrag); //Allows the movement of the window
    }
    /*
    ====================
    Private methods
    ====================
     */
    private void disableButtonCase(){
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
        buttonCase4.setDisable(true);
        buttonCase9.setDisable(true);
        buttonCase16.setDisable(true);
    }
    private void translationAnimationPlay(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "haut"); //Menu disappearance
        shift.disabledNodeDuration(anchorPaneMid, 800); //Disable pane containing "menu" button during 800ms
        gridPane.setDisable(false); //Activating the GridPane
    }
    private void initializeKeyListener(){
        try {
            grilleController.casesMove(PuzzleApplication.getScene(), gridPane, labelScore);
        } catch (Exception e){
            System.err.println("Impossible");
        }
    }
}
