package projet.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import projet.logic.Protection;

public class CasesCreator {
    private static int taille = 4; //La valeur par défaut est 4
    final static Pane[] cases = new Pane[taille-1]; // Creer un tableau de "taille - 1" Panes pour eviter d'avoir 17 cases par exemple

    public void casesInit(GridPane grille){
        final int size = 40;

        for (int i=0; i<taille; i++){
            grille.getChildren().add(cases[i]); //Ajoute chaque cases dans la grille
            cases[i].setPrefSize(size, size); //Définit la taille de chaque case
        }
    }

    public void grilleCreator(int taille){
        GridPane grille = new GridPane();

        grille.addColumn(0);
        grille.addColumn(1);
        grille.addRow(0);
        grille.addRow(1);
        if (taille==8){
            grille.addColumn(2);
            grille.addRow(2);
        } else if (taille==16){
            grille.addColumn(3);
            grille.addRow(3);
        }
    }
    public void picsAdd(String url){
            //Image test = new Image()                        //image globale
            //Image[] imageCut = new Image(url)[12];          //image découpée
            for (int i = 1; i <= 12; i++) {
                // imageCut[i].                              //DECOUPE O COUTO
            }
    }
}
