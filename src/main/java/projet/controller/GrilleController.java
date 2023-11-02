package projet.controller;
import javafx.scene.layout.*;
import projet.logic.Protection;
import projet.modele.Grille;

public class GrilleController {
    private int taille; private int rowAndColumn;
    private VBox[][] vBoxes;
    private Grille grille;
    protected GrilleController(int taille){
        this.grille = null;
        setTaille(this.taille);

    }
    protected void setTaille(int setTaille){
        this.taille = setTaille;
        this.rowAndColumn = (int) Math.sqrt(taille); //Permet de trouver le nombre de lignes à ajouter (peu importe le nombre de case)
    }
    protected void initJeu(GridPane grille){
        //Divise la grille avec des contraintes
        try {diviserGrille(grille);} catch (Exception e){System.err.println("Impossible de creer la grille");}
        //Reinitialiser la grille

        //Creer un objet grille
        associerGrilleEtGridPane();
        //Associe la grille avec les VBoxes

        //Ajoute les VBoxes dans le gridpane
        try {vboxAdd(grille);} catch (Exception e){System.err.println("impossible d'ajouter la photo dans la grille");}
    }

    private void associerGrilleEtGridPane(){

        grille = Grille.getInstance(rowAndColumn, true); //TAILLE

        boolean correct = false;
        while(!correct){
            grille.remplirGrille();
            correct = grille.testerSiGrilleSoluble();
        }

        System.out.println(grille.getGrille());


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
    private void vboxAdd(GridPane grille){
        vBoxes = new VBox[rowAndColumn][rowAndColumn];
        for (int i = 0; i<rowAndColumn; i++) {
            for (int j=0; j<rowAndColumn; j++){
                if (i == rowAndColumn - 1 && j == rowAndColumn - 1) continue;
                vBoxes[i][j] = new VBox();
                grille.add(vBoxes[i][j], i, j);
                vBoxes[i][j].getStyleClass().add("cell");
                System.out.println("i : " + i + "j : " +j);
            }
        }
    }
    private void vboxDeplacer(){




    }










    protected void cutPic(){

        //croppedimage

    }
    protected void getPic(){

//bufferedimage
        //sourceimage


    }
}