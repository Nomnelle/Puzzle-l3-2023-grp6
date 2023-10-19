package projet.controller;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CasesCreator {
    private int taille;
    public void taille(int taille){
        this.taille = taille-1; //-1 pour laisser une place vide dans la grille
    }
    public void casesInit(GridPane grille){
        //Reset (éviter les bugs)
        grille.getChildren().removeAll();

        //Création
        final Pane[] cases = new Pane[taille];
        final double sizeOfPanes = Math.sqrt((double) 340 /taille);

        for (int i=0; i<Math.sqrt(taille)-1; i++){ //FAUX
            //Divise la grille en "taille" (colones et lignes)

            grille.addRow(i);
            grille.addColumn(i);


            //Insertion des tuiles



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
