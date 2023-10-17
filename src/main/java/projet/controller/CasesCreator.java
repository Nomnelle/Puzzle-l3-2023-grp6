package projet.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import projet.logic.Protection;

public class CasesCreator {
    final static Pane[] cases = new Pane[16-1];
    int baseLayoutX = 20;
    final int RANGE1Y = 360;
    final int RANGE2Y = 460;
    private final int RANGE3Y = 560;
    private final int SQUARESIZE = 40;
    Protection protection = new Protection();
    private boolean firstGame = true;

    public void casesInit(){
        for (int i=0; i<15; i++){
            cases[i].setPrefSize(SQUARESIZE, SQUARESIZE);
        }
    }
    public void casesPlacement() { //Placement automatique des tuiles dans la grille
        //Fin
        if (protection.state(firstGame)){
            for (int i = 1; i <= 15; i++) {
                switch (i) {
                    case 1, 2, 3, 4 -> {
                        cases[i-1].setLayoutX(baseLayoutX * i);
                        cases[i-1].setLayoutY(RANGE1Y);
                    }
                    case 5, 6, 7, 8 -> {
                        cases[i-1].setLayoutX(baseLayoutX * (i - 4));
                        cases[i-1].setLayoutY(RANGE2Y);
                    }
                    case 9, 10, 11, 12 -> {
                        cases[i-1].setLayoutX(baseLayoutX * (i - 8));
                        cases[i-1].setLayoutY(RANGE3Y);
                    }
                }
            }
        } else {
            System.err.println("NONONON");
        }
        protection.setfirstGameFalse(firstGame);


        // NE PAS OUBLIER DE RESET SI NEW GAME
        //!!!!!!!!!!!!!!!!!
    }

    public void picsAdd(String url){

        //Image test = new Image()                        //image globale

        //Image[] imageCut = new Image(url)[12];          //image découpée

        for (int i=1; i<=12; i++){
           // imageCut[i].                              //DECOUPE O COUTO

        }



    }





}