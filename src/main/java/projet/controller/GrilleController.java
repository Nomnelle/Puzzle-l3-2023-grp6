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
import projet.logicUI.ShiftCases;
import projet.modele.game.BDD;
import projet.modele.game.Case;
import projet.modele.game.Chrono;
import projet.modele.game.Grille;

import java.util.LinkedList;

public class GrilleController {
    private class UndoObjet {
        int direction;
        Node node;
        char xy;
        public UndoObjet(int direction, Node node, char xy){
            this.direction = direction;
            this.node = node;
            this.xy = xy;
        }
    }
    private final int size; private final int rowAndColumn;
    private boolean paused = false; private boolean gameExist = false; boolean isMoving = false;
    private int moveCount = 0;
    private VBox[][] vBoxes; Grille grille;
    String username = System.getProperty("user.name");
    private static final BDD bdd = new BDD();
    Chrono chrono;
    GridPane gridPane;
    private final LinkedList<UndoObjet> undoObjets = new LinkedList<>();

    protected GrilleController(int size, Grille grille, GridPane gridPane, Chrono chrono){
        this.size = size;
        this.rowAndColumn = (int) Math.sqrt(size);
        this.grille = grille;
        this.chrono = chrono;
        this.gridPane = gridPane;
        initializeGrid(gridPane);
    }
    /**
     * This function lets you know the size of the grid
     * @return the size of the grid
     */
    protected int getSize(){
        return size;
    }
    /**
     * This function allow to define the state of the game
     * Sets paused to true if the game is paused
     */
    protected void isPaused(boolean isPaused){
        paused = isPaused;
    }
    /**
     * This function lets you know if a game is in progress
     * @return 'true' if the game already exist
     */
    protected boolean gameExist(){
        return gameExist;
    }
    /**
     * This function allow to undo the last movement
     * @return 'true' if the movement has been cancelled
     */
    protected boolean undoLastMovement(){
        if(!undoObjets.isEmpty() && !isMoving && !paused) {
            isMoving = true;
            timerMove();
            if (moveCount >= 0) {
                goBack();
                return true;
            }
        }
        return false;
    }
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
     * @param gridPane The GridPane to initialize. He will first be reset before being configured.
     */
    protected void initializeGrid(GridPane gridPane){
        divideGridPane(gridPane); //Divides the GridPane with constraints
        createGrille(); //Create a grid
        setVboxArray(); //Create an array of VBoxes
        associateGrilleVBox(gridPane);//Converts the VBoxes array to VBox with positions equal to the 'Grid' object
        }
    /**
     * This method allow to move cases
     * @param scene the scene that contains the grid
     * @param score the score Label
     */
    protected void casesMove(Scene scene, Label score, Label victoire){
        scene.setOnKeyPressed(event -> {
            if (!paused && !isMoving){
                isMoving = true;
                timerMove();
                int[] avant;
                switch (event.getCode()) {
                    case Z -> {
                        System.out.println("Z");
                        avant = grille.deplacerCase("haut");
                        shift(avant, score, -1, 'y');
                    }
                    case S -> {
                        System.out.println("S");
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

    private void victory(Label victoire){ //Victoire ??
        if (grille.verifierVictoire()){
            chrono.pauseTime();
            bdd.addData(username, moveCount, chrono.toString(), size);
            victoire.setDisable(false);
            victoire.setVisible(true);
            paused = true;
        }
    }
    private void shift(int[] avant, Label score, int direction, char xy){
        if (avant != null) {
            Node node = recupererVbox(avant, gridPane);
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
                System.out.println("BASIC MOVE");
                System.out.println(grille);
            }
        }
    }
    private void shift(Node node, int direction, char xy){
        if (node!=null){
            ShiftCases th = new ShiftCases(node,-(direction), gridPane, xy);
            th.start();
        }
    }
    public void goBack(){
            UndoObjet undo = undoObjets.removeLast();
            shift(undo.node, undo.direction, undo.xy);
            grille.undoLastMovement();
            System.out.println("RETURN");
            System.out.println(grille);
    }

    private void timerMove(){ //Empeche l'utilisateur de spam les touches et de faire tout buger
        KeyFrame keyFrame = new KeyFrame(Duration.millis((gridPane.getHeight()/gridPane.getRowCount())), e -> isMoving = false);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
    }
    private void divideGridPane(GridPane gridPane){
        //Reset the old grid
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        //Constraints of lines + columns
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight((double) 100 /rowAndColumn);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth((double) 100 /rowAndColumn);

        //Addition of lines + columns
        for (int i=0; i<rowAndColumn; i++){
            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }
    private void createGrille(){
        grille = Grille.getInstance(rowAndColumn); //Set size
        //Retry the grid creation if she is not soluble
        boolean correct = false;
            while(!correct){
            grille.remplirGrille();
            correct = grille.testerSiGrilleSoluble();
        }
    }
    private void setVboxArray(){
        Pics pics = new Pics(rowAndColumn);
        int x =0; int z = 0;
        vBoxes = new VBox[rowAndColumn][rowAndColumn];
        for (int i = 0; i<rowAndColumn; i++) {
            for (int j=0; j<rowAndColumn; j++){
                if (i == rowAndColumn - 1 && j == rowAndColumn - 1) continue; //Skip the last cell
                vBoxes[i][j] = new VBox();

                ImageView imageView = new ImageView();
                imageView.setImage(pics.cutedPic(x, z));

                vBoxes[i][j].getChildren().add(imageView);

                if (x==rowAndColumn-1) {
                    x=0;
                    z++;
                } else {
                    x++;
                }
                if (z==rowAndColumn) {
                    z=0;
                }
            }
        }
    }
    private void associateGrilleVBox(GridPane gridPane){
        for (Case c : grille.getGrille()){
            if (c.getIndice()==0) continue; //Skip case with the indices 0

            int[] indexes = convertIndexes(c.getIndice()-1);
            VBox vBox = vBoxes[indexes[0]][indexes[1]];

            gridPane.add(vBox, c.getY(), c.getX());
        }
        gameExist = true; //A game exists from that moment
    }
    private int[] convertIndexes(int indice){
        int j = indice/rowAndColumn;
        int i = indice%rowAndColumn;
        return new int[]{j,i};
    }
    private Node recupererVbox(int[] avant, GridPane gridPane){
        for (Node n : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(n)==avant[0] && GridPane.getRowIndex(n)==avant[1]){
                return n;
            }
        }
        return null;
    }

    //..................QUAND L'UTILISATEUR A GAGNER, gameExist = false;..............

}