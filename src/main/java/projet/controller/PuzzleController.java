package projet.controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import projet.logicUI.LoadStats;
import projet.logicUI.Serial;
import projet.logicUI.ShiftUIDesign;
import projet.PuzzleApplication;
import projet.modele.game.Chrono;
import projet.modele.game.Grille;
import projet.modele.ia.ParcoursAEtoile;

public class PuzzleController implements Initializable {
    /**
    ====================
    Nodes
    ====================
     */
    @FXML
    private GridPane gridPane; //visual grid
    @FXML
    private AnchorPane anchorPaneBackground; //panel covering the entire window
    @FXML
    private AnchorPane anchorPaneDrag; //panel TOP
    @FXML
    private AnchorPane anchorPaneMid; //panel containing undo, menu, score and timer
    @FXML
    private AnchorPane anchorPaneStats; //panel containing statistical information
    @FXML
    private AnchorPane anchorPaneMenu; //menu panel
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
    private Button buttonAI;
    @FXML
    private Button buttonStats;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonStyle;
    @FXML
    private Button buttonImage;
    @FXML
    private Label labelScore;
    @FXML
    private Label labelChrono;
    @FXML
    private Label labelVictoire;
    @FXML
    private ListView<String> arrayPlayers;
    private ShiftUIDesign shift; //Manage movements and animation of the software
    private volatile GrilleController grilleController; //Manage and create the grid
    private Chrono chrono;
    private LoadStats loadStats = new LoadStats();
    private boolean iaEnCours;
    private ParcoursAEtoile ia = new ParcoursAEtoile();
    public static int picture = 1; //default image : the first

    /**
     * Button that closes the software
     */
    @FXML
    protected void setButtonClose(){System.exit(0);}

    /**
     * Button which asks for the choice of grid, or continues a game already started
     */
    @FXML
    protected void setButtonPlay(){
        if (grilleController !=null && grilleController.gameExist()){ //Check the existence of a part
            //Return to the game
            goInGame();
            if (iaEnCours){
                Platform.runLater(() -> ia.resoudreGUI(grilleController.getGrille(), grilleController));
            }
        } else {
            setChoiceShow(); //Request a grid size
        }
    }

    /**
     * Button that restarts a new game and allows the player to change the image
     */
    @FXML
    protected void setButtonRestart(){
        stopperIA();
        buttonImage.setDisable(false);
        setChoiceShow();
    }

    /**
     * Cases choice buttons : initialisation for 3 puzzle
     */
    @FXML
    protected void buttonCase4(){
        grilleController = new GrilleController(4, new Grille(2), gridPane, chrono);
        grilleController.initializeGrid();
        resetAll();
        goInGame();
    }

    /**
     * Cases choice buttons : initialisation for 8 puzzle
     */
    @FXML
    protected void buttonCase9(){
        grilleController = new GrilleController(9, new Grille(3), gridPane, chrono);
        grilleController.initializeGrid();
        resetAll();
        goInGame();
    }

    /**
     * Cases choice buttons : initialisation for 15 puzzle
     */
    @FXML
    protected void buttonCase16(){
        grilleController = new GrilleController(16, new Grille(4), gridPane, chrono);
        grilleController.initializeGrid();
        resetAll();
        goInGame();
    }

    /**
     * Button that allows the user to load a game
     */
    @FXML
    protected void setButtonLoad(){
        stopperIA();
        Serial serial = new Serial(); //Manage serialization
        Grille grille = serial.deserialize(); //Deserialize

        if (grille != null) { //can be null (it already happened, because of old version of .ser file)
            grilleController = new GrilleController(grille.getLongueur()*grille.getLongueur(), grille, gridPane, chrono);
            grilleController.initializeGridLoaded();
            resetAll();
            goInGame();
        }
    }

    /**
     * Button that allows the user to save a game
     */
    @FXML
    protected void setButtonSave(){
        if (grilleController.getGrille() != null) {
            Serial serial = new Serial(grilleController.getGrille());
            serial.saveGrid();
            buttonLoad.setDisable(false);
        }
    }

    /**
     * Button which allows access to the menu
     */
    @FXML
    protected void setButtonMenu(){
        stopperIA();

        chrono.pauseTime();

        setChoiceHide();
        buttonSave.setDisable(false);
        buttonRestart.setDisable(false);

        grilleController.isPaused(true);

        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "bas");
        anchorPaneMid.setDisable(true);

        if (grilleController.gameExist()){
            buttonPlay.setText("RESUME");
        } else {
            buttonPlay.setText("PLAY");
        }

        if (!grilleController.gameExist()){
            buttonImage.setDisable(false);
        }
    }

    /**
     * Button that manage the IA
     */
    @FXML
    protected void setButtonAI(){
        if (grilleController !=null && grilleController.gameExist()) {
            this.iaEnCours = !this.iaEnCours;
            translationAnimationPlay();

            if (this.iaEnCours) {
                buttonAI.setText("STOP AI");
                grilleController.isPaused(true); //The users can't move
                ia = new ParcoursAEtoile();
                Platform.runLater(() -> ia.resoudreGUI(grilleController.getGrille(), grilleController));
                buttonUndo.setDisable(true);
                chrono.goTime();
            } else {
                stopperIA();
                buttonAI.setText("START AI");
            }
        }
    }

    /**
     * Button that allows the user to view the ranking
     * This button also set the ListView
     */
    @FXML
    protected void setButtonStatsShow() {
        //Load ranking panel
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "haut");
        shift.disabledNodeDuration(anchorPaneStats, 800);
        //Load Array
        try {
            ObservableList<String> players = FXCollections.observableArrayList("Position           ID          Size    Score\n" + loadStats.toString());
            arrayPlayers.setItems(players);
        } catch (Exception ignored){}
    }

    /**
     * Button that allows you to cancel a move
     */
    @FXML
    protected void setButtonUndo(){
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
            case 1 : //Deactivates the undo button after 4 uses
                if (grilleController.undoLastMovement()) {buttonUndo.setText("UNDO (0)"); buttonUndo.setDisable(true);}
                break;
        }
    }

    /**
     * Button that allows you to go back when you are on the ranking panel
     */
    @FXML
    protected void setButtonBack(){
        shift.nodeShift(anchorPaneStats, anchorPaneMenu, 600, 800, "bas");
    }

    /**
     * Button that allows you to change the style
     */
    @FXML
    protected void setButtonStyle(){
        PuzzleApplication.styleSwitch();
    }
    /*
     * Button that allows you to change the image
     */
    @FXML
    protected void setButtonImage(){
        if (picture <2) picture += 1; else picture = 1; //Never go above the range and set int
        buttonImage.setText("Picture " + picture); //Set the button text
    }

    /**
     * Initialization
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
        buttonAI.getStyleClass().add("buttonAI");
        buttonStats.getStyleClass().add("buttonStats");
        buttonBack.getStyleClass().add("buttonBack");
        buttonStyle.getStyleClass().add("buttonStyle");
        buttonImage.getStyleClass().add("buttonStyle");
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
        buttonSave.setDisable(true);
        anchorPaneStats.setLayoutY(760); //Position of the stats panel at opening (160 is the normal state)
        anchorPaneMid.setDisable(true);

        shift = new ShiftUIDesign(anchorPaneDrag); //UI Design shift
        shift.start(); //Allows the movement of the window

        chrono = new Chrono(labelChrono); //Chrono creation

        if (!Serial.verifySave()){ //Checks if a backup file exists
            buttonLoad.setDisable(true); //If not, disable the loading option
        }

        loadStats.setDaemon(true);
        loadStats.start();
    }

    /**
     * Go in game
     */
    private void goInGame(){
        translationAnimationPlay();

        grilleController.isPaused(false);

        chrono.goTime();

        buttonUndo.setDisable(false);
        buttonImage.setDisable(true);
    }

    /**
     * Reset the state of the game
     */
    private void resetAll(){
        initializeKeyListener();
        labelVictoire.setVisible(false);

        chrono.reset();

        buttonAI.setText("START AI");
        buttonUndo.setText("UNDO (4)");
        labelScore.setText("0");

        if (this.iaEnCours){
            iaEnCours = false;
        }
    }

    /**
     * Show the grid view
     */
    private void translationAnimationPlay(){
        shift.nodeShift(anchorPaneMenu, anchorPaneMenu, 600, 800, "haut"); //Menu disappearance
        shift.disabledNodeDuration(anchorPaneMid, 800); //Disable pane containing "menu" button during 800ms
        gridPane.setDisable(false); //Activating the GridPane
    }

    /**
     * Initialize the key listener
     */
    private void initializeKeyListener(){
        try {
            grilleController.casesMove(PuzzleApplication.getScene(), labelScore, labelVictoire);
        } catch (Exception e){
            System.err.println("Impossible");
        }
    }

    /**
     * Show the choice of the number of cases and hide the play button
     */
    private void setChoiceShow(){
        buttonPlay.setVisible(false);
        buttonCase4.setVisible(true);
        buttonCase9.setVisible(true);
        buttonCase16.setVisible(true);
    }

    /**
     * Hide the choice of the number of cases and show the play button
     */
    private void setChoiceHide(){
        buttonPlay.setVisible(true);
        buttonCase4.setVisible(false);
        buttonCase9.setVisible(false);
        buttonCase16.setVisible(false);
    }

    /**
     * that stop the AI
     */
    private void stopperIA(){
        if (grilleController != null){
            ia.stopperResolution();
            grilleController.isPaused(false);
            chrono.goTime();
        }
    }
}