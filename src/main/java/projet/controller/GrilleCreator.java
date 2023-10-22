package projet.controller;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class GrilleCreator {
    private int taille;
    private int rowAndColumn;
    public void setTaille(int setTaille){
        this.taille = setTaille;
        this.rowAndColumn = (int) Math.sqrt(taille); //Permet de trouver le nombre de lignes à ajouter (peu importe le nombre de case)
    }
    public void creerGrille(GridPane grille){
        try {
            diviserGrille(grille);
        } catch (Exception e){
            System.err.println("Impossible de creer la grille");
        }
        try {
            picsAdd(grille);
        } catch (Exception e){
            System.err.println("d'ajouter la photo dans la grille");
        }

        System.out.println(grille.getRowCount());
    }
    private void diviserGrille(GridPane grille){
        //Reset de l'ancienne grille
        grille.getChildren().clear();
        grille.getColumnConstraints().clear();
        grille.getRowConstraints().clear();

        //Contraintes des lignes + colones
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.SOMETIMES);

        //Ajout des lignes + colones
        for (int i=0; i<rowAndColumn; i++){
           grille.getRowConstraints().add(rowConstraints);
           grille.getColumnConstraints().add(columnConstraints);
        }
    }
    private void picsAdd(GridPane grille){
        for (int i = 0; i<rowAndColumn; i++) {
            for (int j=0; j<rowAndColumn; j++){
                if (i == rowAndColumn - 1 && j == rowAndColumn - 1) continue;
                VBox vbox = new VBox();
                vbox.setAlignment(Pos.TOP_CENTER); // Centre le bouton horizontalement et le place en haut verticalement
                grille.add(vbox, i, j);
                vbox.getStyleClass().add("cell");
            }
        }
    }
}