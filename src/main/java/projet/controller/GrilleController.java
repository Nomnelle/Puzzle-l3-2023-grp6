package projet.controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import projet.logicUI.Pics;
import projet.logicUI.Player;
import projet.logicUI.ShiftCases;
import projet.modele.game.Case;
import projet.modele.game.Chrono;
import projet.modele.game.Grille;

import java.util.LinkedList;

/**
 * Control the GridPane and associated events
 */
public class GrilleController {

    /**
     * Object which allows going back thanks to saving the previous state
     */
    private static class UndoObjet {
        int direction;
        Node node;
        char xy;

        /**
         * Constructor
         *
         * @param direction direction
         * @param node      case
         * @param xy        axe
         */
        public UndoObjet(int direction, Node node, char xy){
            this.direction = direction;
            this.node = node;
            this.xy = xy;
        }
    }
    private final LinkedList<UndoObjet> undoObjets = new LinkedList<>();
    private final int size;
    private final int rowAndColumn;
    private boolean paused = false;
    private boolean gameExist = false;
    private boolean isMoving = false;
    private int moveCount = 0;
    private VBox[][] vBoxes;
    private Grille grille;
    private GridPane gridPane;
    private Chrono chrono;

    /**
     * Controller allowing us to assign a functional grid to our interface.
     *
     * @param size      size
     * @param grille    theoretical grid
     * @param gridPane  GridPane
     * @param chrono    Chronometer
     */
    protected GrilleController(int size, Grille grille, GridPane gridPane, Chrono chrono){
        this.size = size;
        this.rowAndColumn = (int) Math.sqrt(size);
        this.grille = grille;
        this.chrono = chrono;
        this.gridPane = gridPane;
    }
    /**
     * This method set the state of the game
     * set 'true' if the game might be paused
     */
    protected void isPaused(boolean isPaused){
        paused = isPaused;
    }
    /**
     * This function lets you know if a game is in progress
     *
     * @return 'true' if the game already exist
     */
    protected boolean gameExist(){
        return gameExist;
    }
    /**
     * This function allow to undo the last movement
     *
     * @return 'true' if the movement has been cancelled
     */
    protected boolean undoLastMovement(){
        if(!undoObjets.isEmpty() && !isMoving && !paused) {
            if (moveCount >= 0) {
                isMoving = true;
                timerMove();
                goBack();
                return true;
            }
        }
        return false;
    }
    /**
     * This function lets you get access to the grid
     *
     * @return the Grid
     */
    protected Grille getGrille(){
        return this.grille;
    }
    /**
     * This function lets you know how many movements have been done
     * @return movement count
     */
    protected int getMoveCount(){
        return moveCount;
    }
    /**
     * Initialize the grid
     * Step 1 : Divides the GridPane with constraints
     * Step 2 : Create a grid in commandline
     * Step 3 : Create an array of VBoxes
     * Step 4 : Converts the VBoxes array to VBox with positions equal to the 'Grid' object
     */
    protected void initializeGrid(){
        divideGridPane();
        createGrid();
        setVboxArray();
        associateGrilleVBox();
    }
    /**
     * Initialize the Loaded Grid
     * Step 1 : Divides the GridPane with constraints
     * Step 2 : Create an array of VBoxes
     * Step 3 : Converts the VBoxes array to VBox with positions equal to the 'Grid' object
     */
    protected void initializeGridLoaded(){
        divideGridPane();
        setVboxArray();
        associateGrilleVBox();
    }
    /**
     * This method allow to move cases thanks to a key (Z, Q, S, D)
     * Checks if the movement makes the user win
     *
     * @param scene the scene that contains the grid
     * @param score the score Label
     * @param victoire the label that say 'VICTORY'
     */
    protected void casesMove(Scene scene, Label score, Label victoire){
        scene.setOnKeyPressed(event -> {
            if (!paused && !isMoving){
                isMoving = true;
                timerMove();
                int[] avant;
                switch (event.getCode()) {
                    case Z -> {
                        avant = grille.deplacerCase("haut");
                        shift(avant, score, -1, 'y');
                    }
                    case S -> {
                        avant = grille.deplacerCase("bas");
                        shift(avant, score, 1, 'y');
                    }
                    case Q -> {
                        avant = grille.deplacerCase("gauche");
                        shift(avant, score, -1, 'x');
                    }
                    case D -> {
                        avant = grille.deplacerCase("droite");
                        shift(avant, score, 1, 'x');
                    }
                }
                victory(victoire);
            }
        });
    }

    /**
     * Executes a movement on the puzzle grid based on the given input.
     *
     * @param movement The direction of the movement: "haut" (up), "bas" (down), "gauche" (left), or "droite" (right).
     */
    public void executerMouvement(String movement) {
            int[] avant = grille.deplacerCase(movement);
            switch (movement) {
                case "haut":
                    Node node = recupererVbox(avant);
                    GridPane.setRowIndex(node, GridPane.getRowIndex(node) - 1);
                    break;
                case "bas":
                    node = recupererVbox(avant);
                    GridPane.setRowIndex(node, GridPane.getRowIndex(node) + 1);
                    break;
                case "gauche":
                    node = recupererVbox(avant);
                    GridPane.setColumnIndex(node, GridPane.getColumnIndex(node) - 1);
                    break;
                case "droite":
                    node = recupererVbox(avant);
                    GridPane.setColumnIndex(node, GridPane.getColumnIndex(node) + 1);
                    break;
        }
    }


    /**
     * go back
     */
    private void goBack(){
        UndoObjet undo = undoObjets.removeLast(); //Remove the last movement saved
        shift(undo.node, undo.direction, undo.xy);
        grille.annulerMouvement();
    }

    /**
     * Verify if the user win
     *
     * @param victoire label that says "VICTORY !"
     */
    private void victory(Label victoire){
        if (grille.verifierVictoire()){
            chrono.pauseTime();
            Player player = new Player(chrono, moveCount, size);
            player.setScore();
            player.start();
            victoire.setVisible(true);
            paused = true;
            gameExist = false;
        }
    }

    /**
     * move a node on the GUI (the cases)
     *
     * @param avant (int[]) current theoretical case before displacement
     * @param score label that contains the movements count
     * @param direction (int) plus or minus
     * @param xy (char) axe X or Y
     */
    private void shift(int[] avant, Label score, int direction, char xy){
        if (avant != null) {
            Node node = recupererVbox(avant);
            if (node!=null){
                //Thread
                ShiftCases th = new ShiftCases(node,direction, gridPane, xy);
                th.start();
                //Score
                moveCount++;
                score.setText(String.valueOf(getMoveCount()));
                //Pattern
                undoObjets.add(new UndoObjet(direction, node, xy));
                if(undoObjets.size() > 4){
                    undoObjets.removeFirst();
                }
            }
        }
    }

    /**
     * move a node on the GUI (the cases)
     *
     * @param node current theoretical case before displacement
     * @param direction (int) plus or minus
     * @param xy (char) axe X or Y
     */
    private void shift(Node node, int direction, char xy){
        if (node!=null){
            ShiftCases th = new ShiftCases(node,-(direction), gridPane, xy);
            th.start();
        }
    }

    /**
     * Delays the movement of a node by setting isMoving to false after a calculated time
     * Else, the threads make bugs
     */
    private void timerMove(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(gridPane.getHeight()/gridPane.getRowCount()), e -> isMoving = false);
        //gridPane.getHeight()/gridPane.getRowCount() to make the delay dynamic based on the grid.
        //On the thread, the speed is gridPane.getHeight()/gridPane.getRowCount()/2.5 but with this value, the threads still collide
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
    }

    /**
     * Divide GridPane with Constraints
     */
    private void divideGridPane(){
        //Reset the old grid
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        //Constraints of lines
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight((double) 100 /rowAndColumn);
        //Constraints of columns
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth((double) 100 /rowAndColumn);

        //add lines + columns
        for (int i=0; i<rowAndColumn; i++){
            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    /**
     * Create a theoretical grid
     */
    private void createGrid(){
        //Retry the grid creation if she is not soluble
        boolean correct = false;
            while(!correct){
            grille.remplirGrille();
            correct = grille.testerSiGrilleSoluble()&&!grille.verifierVictoire();
        }
    }

    /**
     * Create an array of VBoxes and Associate them with ImageView that contains the photos
     */
    private void setVboxArray(){
        Pics pics = new Pics(rowAndColumn); //Initialize Pics manager with constraints
        int y =0; int x = 0; //used to put the parts of the picture inside ImageView

        vBoxes = new VBox[rowAndColumn][rowAndColumn];

        for (int i = 0; i<rowAndColumn; i++) {
            for (int j=0; j<rowAndColumn; j++){

                if (i == rowAndColumn - 1 && j == rowAndColumn - 1) continue; //Skip the last cell
                vBoxes[i][j] = new VBox();

                ImageView imageView = new ImageView();
                imageView.setImage(pics.cutedPic(y, x)); //put the parts of the picture inside ImageView

                vBoxes[i][j].getChildren().add(imageView); //add Image inside VBox

                if (y==rowAndColumn-1) { //new line
                    y=0; //-> reset Y
                    x++; //-> increment x
                } else {                //same line
                    y++; //increment y
                }
            }
        }
    }

    /**
     * associate the theoretical cases with VBoxes and set the pos of VBoxes in gridPane
     */
    private void associateGrilleVBox(){
        for (Case c : grille.getGrille()){
            if (c.getIndice()==0) continue; //Skip case with the indices 0

            int[] indexes = convertIndexes(c.getIndice()-1);
            VBox vBox = vBoxes[indexes[0]][indexes[1]];

            gridPane.add(vBox, c.getY(), c.getX());
        }
        gameExist = true; //A game exists from that moment
    }

    /**
     * Converts 1D to 2D array
     *
     * @param indice indice of the 2D array
     * @return 2D array
     */
    private int[] convertIndexes(int indice){
        int j = indice/rowAndColumn;
        int i = indice%rowAndColumn;
        return new int[]{j,i};
    }

    /**
     * @param avant current theoretical case before displacement
     * @return current node before displacement
     */
    private Node recupererVbox(int[] avant){
        for (Node n : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(n)==avant[0] && GridPane.getRowIndex(n)==avant[1]){
                return n;
            }
        }
        return null;
    }
}