package projet.controller;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import projet.PuzzleApplication;
import projet.modele.Case;
import projet.modele.Grille;

import java.util.Arrays;

public class GrilleController {
    private int taille; private int rowAndColumn;
    private VBox[][] vBoxes;
    Grille grille;
    protected GrilleController(int t, Grille grille){
        setTaille(t);
        this.grille = grille;
    }
    protected void setTaille(int setTaille){
        this.taille = setTaille;
        this.rowAndColumn = (int) Math.sqrt(taille); //Permet de trouver le nombre de lignes à ajouter (peu importe le nombre de case)
    }
    protected void initJeu(GridPane gridPane){
        //Divise la grille avec des contraintes
        try {diviserGrille(gridPane);} catch (Exception e){System.err.println("Impossible de creer la grille");}
        //Creer un objet grille
        createGrille();
        //Ajoute les VBoxes dans le gridpane
        try {vboxAdd(gridPane);} catch (Exception e){System.err.println("impossible d'ajouter la photo dans la grille");}
        //Associe la grille avec les VBoxes
        associateGrilleVBox(gridPane);
    }
    protected void vboxDeplacer(Scene scene, GridPane gridPane, boolean pause){
        if (!pause){
            scene.setOnKeyPressed(event -> {
               int[] avant;
                switch (event.getCode()){
                    case Z :
                        avant = grille.deplacerCase("haut");
                        if (avant!=null) {
                            Node node = recupererVbox(avant, gridPane);
                            gridPane.setRowIndex(node, GridPane.getRowIndex(node) - 1);
                            System.out.println(grille);
                        }
                        break;
                    case S :
                        avant = grille.deplacerCase("bas");
                        if (avant!=null) {
                            Node node = recupererVbox(avant, gridPane);
                            gridPane.setRowIndex(node, GridPane.getRowIndex(node) + 1);
                            System.out.println(grille);
                        }
                        break;
                    case Q :
                        avant = grille.deplacerCase("gauche");
                        if (avant!=null) {
                            Node node = recupererVbox(avant, gridPane);
                            gridPane.setColumnIndex(node, GridPane.getColumnIndex(node) - 1);
                            System.out.println(grille);
                        }
                        break;
                    case D :
                        avant = grille.deplacerCase("droite");
                        if (avant!=null) {
                            Node node = recupererVbox(avant, gridPane);
                            gridPane.setColumnIndex(node, GridPane.getColumnIndex(node) + 1);
                            System.out.println(grille);
                        }
                        break;
                }
            });
        }
        System.err.println(grille);
    }
    private Node recupererVbox(int[] avant, GridPane gridPane){
        for (Node n : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(n)==avant[0] && GridPane.getRowIndex(n)==avant[1]){
                return n;
            }
        }
        return null;
    }
    private void createGrille(){
        grille = Grille.getInstance(rowAndColumn); //TAILLE
        boolean correct = false;
        while(!correct){
            grille.remplirGrille();
            correct = grille.testerSiGrilleSoluble();
        }
        System.out.println(grille.getGrille());
    }
    private void associateGrilleVBox(GridPane gridPane){
        Label label;
        for (Case c : grille.getGrille()){
            if (c.getIndice()==0){continue;}
            int[] indice = convertirIndex((c.getIndice()-1));
            VBox v = vBoxes[indice[0]][indice[1]];
            int x = c.getX();
            int y = c.getY();
            label = new Label();
            label.setText(c.getValeur());
            v.getChildren().add(label);
            gridPane.add(v, y, x);
            v.getStyleClass().add("cell");
        }
        System.out.println(grille);
    }

    private int[] convertirIndex(int indice){
        int j = indice/rowAndColumn;
        int i = indice%rowAndColumn;
        return new int[]{j,i};
    }
    private void diviserGrille(GridPane gridPane){
        //Reset de l'ancienne grille
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        //Contraintes des lignes + colones
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.SOMETIMES);

        //Ajout des lignes + colones
        for (int i=0; i<rowAndColumn; i++){
            gridPane.getRowConstraints().add(rowConstraints);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }
    private void vboxAdd(GridPane gridPane){
        vBoxes = new VBox[rowAndColumn][rowAndColumn];
        for (int i = 0; i<rowAndColumn; i++) {
            for (int j=0; j<rowAndColumn; j++){
                if (i == rowAndColumn - 1 && j == rowAndColumn - 1) continue;
                vBoxes[i][j] = new VBox();
                System.out.println("colone : " + i + " ligne : " + j);
            }
        }
    }



    protected void cutPic(){

        //croppedimage

    }
    protected void getPic(){

//bufferedimage
        //sourceimage


    }
}