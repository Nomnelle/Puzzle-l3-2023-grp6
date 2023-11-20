package projet.controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import projet.logicUI.Player;
import projet.logicUI.Serial;
import projet.logicUI.ShiftUIDesign;
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
    private Button buttonRestart;
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
    private Label labelChrono;
    @FXML
    private Label labelVictoire;
    @FXML
    private TableView<Player> arrayPlayers;

    private ShiftUIDesign shift;
    private Chrono chrono;
    private GrilleController grilleController;
    private final Serial serial = new Serial();
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
        buttonRestart.setDisable(false);
        buttonPlay.setText("RESUME");
        chrono.pauseTime();
        buttonSave.setDisable(false);
    }
    @FXML
    protected void setButtonPlay(){
        if (grilleController !=null && grilleController.gameExist()){
            //Return to the game
            translationAnimationPlay();
            grilleController.isPaused(false);
            chrono.goTime();
        } else {
            setChoiceShow();
        }
    }
    @FXML
    protected void setButtonRestart(){
        setChoiceShow();

    }
    /*
    Cases choice buttons
     */
    @FXML
    protected void buttonCase4(){
        buttonCaseLogic(4, 2);
    }
    @FXML
    protected void buttonCase9(){
        buttonCaseLogic(9, 3);
    }
    @FXML
    protected void buttonCase16(){
        buttonCaseLogic(16, 4);
    }
    /*
    Load et Save buttons
     */
    @FXML
    protected void setButtonLoad(){
    //La méthode initialize du contrôleur vérifiera l’existence du modèle sérialisé (i.e. du fichier modele.ser)
    //Si le fichier existe, elle désérialise le modèle et elle met à jour la vue en fonction de ce que contient le modèle
        Serial serial = new Serial();
        Grille grille = serial.deserial();

        labelVictoire.setVisible(false);

        grilleController = new GrilleController(grille.getLongueur()*grille.getLongueur(), grille, gridPane, chrono);
        grilleController.initializeGridLoaded();

        disableButtonCase();
        translationAnimationPlay();
        initializeKeyListener();

        grilleController.isPaused(false);

        chrono.reset();
        chrono.goTime();

        buttonUndo.setText("UNDO (4)");
        buttonUndo.setDisable(false);
    }
    @FXML
    protected void setButtonSave(){
            Serial serial = new Serial(grilleController.getGrille());
            serial.saveGrille();
            buttonLoad.setDisable(false);
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
        //Load Array
        TableColumn colName = new TableColumn<>();

       // arrayPlayers.


        //Load window
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneStats, 800);
    }
    /*
    BOUTON Undo
     */
    @FXML
    protected void setButtonUndo(){
        //Deactivates the undo button after 4 uses
        switch (grilleController.getGrille().getCompteurMemento()){
            case 4 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (3)");}
                break;
            case 3 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (2)");}
                break;
            case 2 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (1)");}
                break;
            case 1 :
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (0)"); buttonUndo.setDisable(true);}
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
        buttonRestart.getStyleClass().add("buttonPlay");
        buttonCase4.getStyleClass().add("buttonPlay");
        buttonCase9.getStyleClass().add("buttonPlay");
        buttonCase16.getStyleClass().add("buttonPlay");
        buttonLoad.getStyleClass().add("buttonLoadSave");
        buttonSave.getStyleClass().add("buttonLoadSave");
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
        //Initializing the state of nodes
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
        buttonRestart.setDisable(true);
        anchorPaneStats.setLayoutY(160 + 600); //Position of the stats panel at opening (160 is the normal state)
        anchorPaneMid.setDisable(true);
        anchorPaneStats.setVisible(true);
        anchorPaneMenu.setVisible(true);

        shift = new ShiftUIDesign(anchorPaneDrag); //UI Design shift
        shift.start(); //Allows the movement of the window

        chrono = new Chrono(labelChrono); //Chrono creation

        if (!serial.verifySave()){
            buttonLoad.setDisable(true);
        }
        buttonSave.setDisable(grilleController == null || !grilleController.gameExist());

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
            grilleController.casesMove(PuzzleApplication.getScene(), labelScore, labelVictoire);
        } catch (Exception e){
            System.err.println("Impossible");
        }
    }
    private void setChoiceShow(){
        //Show the choice of the number of cases
        buttonPlay.setDisable(true);
        buttonCase4.setVisible(true);
        buttonCase9.setVisible(true);
        buttonCase16.setVisible(true);
        buttonCase4.setDisable(false);
        buttonCase9.setDisable(false);
        buttonCase16.setDisable(false);
    }
    private void buttonCaseLogic(int gridPaneSize, int gridSize){
        labelVictoire.setVisible(false);
        increment++;

        grilleController = new GrilleController(gridPaneSize, new Grille(gridSize, this), gridPane, chrono);
        grilleController.initializeGrid();

        disableButtonCase();
        translationAnimationPlay();
        initializeKeyListener();

        grilleController.isPaused(false);

        chrono.reset();
        chrono.goTime();

        buttonUndo.setText("UNDO (4)");
        buttonUndo.setDisable(false);
    }
}
