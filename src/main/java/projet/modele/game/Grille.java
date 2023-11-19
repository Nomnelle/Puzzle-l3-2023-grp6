package projet.modele.game;

import projet.controller.PuzzleController;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Grille implements Parametres, Serializable {

    private static Grille INSTANCE;

    private static class Memento {
        private final HashSet<Case> grilleSauvegarde;

        public Memento(HashSet<Case> etatGrille){
            this.grilleSauvegarde = new HashSet<>();

            for(Case c : etatGrille){
                this.grilleSauvegarde.add(new Case(c));
            }
        }

        public HashSet<Case> getGrilleSauvegarde() {
            return this.grilleSauvegarde;
        }
    }

    private HashSet<Case> grille;
    private final int longueur;
    private int nombreCoups;
    private Caretaker pastGrid;
    private int instanceGUI = 0;
    private int compteurMemento;


    private Grille(int l) {
        /**
         * Construct a Grille object
         * @param length of the grid
         */
        this.grille = new HashSet<>();;
        this.longueur = l;
        this.nombreCoups = 0;
        pastGrid = new Caretaker();
        compteurMemento = 4;
    }

    private Grille(int l, PuzzleController c) {
        /**
         * Construct a Grille object
         * @param length of the grid, controller to link to the GUI
         */
        this.grille = new HashSet<>();;
        this.longueur = l;
        this.nombreCoups = 0;
        pastGrid = new Caretaker();
        instanceGUI = c.getIncrement();
        compteurMemento = 4;
    }

    public static Grille getInstance(int longueur){
        /**
         * Construct a Grille object if it does not exist, or retrieve the existing object
         * @param length of the grid
         * @return a Grille object
         */
        if(INSTANCE==null){
            INSTANCE = new Grille(longueur);
        }
        return INSTANCE;
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        /**
         * Return the Grille object when serialized - avoid multiple object with Singleton design pattern
         * @return a Grille object
         */
        return INSTANCE;
    }

    public static Grille getInstance(int longueur, PuzzleController controller){
        /**
         * Construct a Grille object if it does not exist, or retrieve the existing object
         * @param length of the grid, controller to link to the GUI
         * @return a Grille object
         */
        if((INSTANCE==null)||(INSTANCE.instanceGUI!=controller.getIncrement())){
            INSTANCE = new Grille(longueur, controller);
        }
        return INSTANCE;
    }

    public HashSet<Case> getGrille() {
        /**
         * Get you the representation of the grille
         * @return the value of the attribute grille
         */
        return grille;
    }

    public int getLongueur() {
        /**
         * Get you the length of the grille
         * @return the value the of attribute longueur
         */
        return longueur;
    }

    public int getNombreCoups() {
        /**
         * Get you the number of movements performed by the player
         * @return the attribute nbCoups
         */
        return nombreCoups;
    }

    public int getCompteurMemento(){
        /**
         * Get you the number of time the player can undo
         * @return the number of time the player can undo
         */
        return compteurMemento;
    }

    public void remplirGrille(){
        /**
         * fill the grid with numbers from one to length - 1, and randomize their order
         */
        this.grille = new HashSet<>();
        ArrayList<Integer> listIndex = new ArrayList<>();
        for(int i = 0;i<longueur*longueur;i++){
            listIndex.add(i);
        }

        Collections.shuffle(listIndex);

        if(this.longueur<6){
            for(int i = 0; i<this.longueur; i++){
                for(int j = 0; j<this.longueur; j++){
                    this.grille.add(new Case(i, j, VALEUR[listIndex.get(i * this.longueur + j)], listIndex.get(i * this.longueur + j), this));
                }
            }
        }else{
            for(int i = 0; i<this.longueur; i++){
                for(int j = 0; j<this.longueur; j++){
                    this.grille.add(new Case(i, j, listIndex.get(i * this.longueur + j), this));
                }
            }
        }

    }

    public int[] transformerGrilleArray1D() {
        /**
         * Transform the grid in a 1D array
         * @return the grid representation in 1D array
         */
        Case[][] tableau2D = transformerGrilleArray2D();
        int[] resultat = new int[this.longueur*this.longueur];
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < longueur; j++) {
                resultat[i * longueur + j] = tableau2D[i][j].getIndice();
            }
        }
        return resultat;
    }

    public Case[][] transformerGrilleArray2D() {
        /**
         * Transform the grid in a 2D array
         * @return the grid representation in 2D array
         */
        Case[][] tableau = new Case[this.longueur][this.longueur];
        for (Case c : this.grille) {
            tableau[c.getX()][c.getY()] = c;
        }
        return tableau;
    }
    @Override
    public String toString() {
        /**
         * Override toString method in class Object, in order to have a String representation of the grid
         * @return the grid representation in a String
         */
        Case[][] tableau = transformerGrilleArray2D();
        String result = "";
        for (int i = 0; i < this.longueur; i++) {
            result += "[";
            String subResult = "";
            for (int j = 0; j<this.longueur; j++){
                subResult += tableau[i][j].getValeur()+",";
            }
            result += subResult.substring(0, subResult.length() - 1);
            result += "]\n";
        }
        return result;
    }

    public Case retrouverCase(int y, int x) {
        /**
         * retrieve a cell placed in the corresponding x and y coordinates
         * @param x and y of a cell
         * @return the cell places in this x and y coordinates
         */
        for (Case c : this.grille) {
            if (y == c.getY() && x == c.getX()) {
                return c;
            }
        }
        return null;
    }

    public Case retournerCaseVide() {
        /**
         * retrieve the empty cell
         * @return the empty cell
         */
        for (Case c : this.grille) {
            if (c.getIndice() == 0) {
                return c;
            }
        }
        return null;
    }

    public int[] deplacerCase(String input) {
        /**
         * move a cell according to an input
         * @param an input in french - haut, bas, droite, and gauche
         * @return the coordinates of the moving cell before it moves
         */
        Case vide = retournerCaseVide();
        Case mouvante = null;
        boolean mouvement = false;
        int[] coordinates = new int[2];
        switch (input) {
            case "haut":
                if (longueur > vide.getX() + 1) {
                    saveToMemento();
                    mouvante = retrouverCase(vide.getY(), vide.getX() + 1);
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                    mouvement = true;
                }
                break;
            case "bas":
                if (0 <= vide.getX() - 1) {
                    saveToMemento();
                    mouvante = retrouverCase(vide.getY(), vide.getX() - 1);
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                    mouvement = true;
                }
                break;
            case "gauche":
                if (longueur > vide.getY() + 1) {
                    saveToMemento();
                    mouvante = retrouverCase((vide.getY() + 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                    mouvement = true;
                }
                break;
            case "droite":
                if (0 <= vide.getY() - 1) {
                    saveToMemento();
                    mouvante = retrouverCase((vide.getY() - 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                    mouvement = true;
                }
                break;
            case "undo":
                    undoLastMovement();
                break;
        }
        if(mouvement){
            coordinates[0] = mouvante.getY();
            coordinates[1] = mouvante.getX();
            return coordinates;
        }
        return null;
    }

    public boolean verifierVictoire() {
        /**
         * check if the player has won
         * @return true if the grid is in the expected state to declare the player as winner
         */
        int[] etatActuel = this.transformerGrilleArray1D();
        if(etatActuel[etatActuel.length -1]==0){
            for(int i = 1; i < (longueur*longueur) - 1; i++) {
                if (etatActuel[i-1] > etatActuel[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int compterInversions(){
        /**
         * count how many times we have to exchange two cells before the grid is in victory setting - bubble sort
         * @return how many times cells had exchange their place
         */
        int[] grilleTest = this.transformerGrilleArray1D();
        int compteur = 0;
        boolean finTri;

        grilleTest = retirerCaseVideTableau(grilleTest);

        for(int i = (longueur*longueur)-1; i> 0; i--){
            finTri = true;
            for(int j = 0; j<(i-1);j++){
                if(grilleTest[j]>grilleTest[j+1]){
                    int tmp = grilleTest[j];
                    grilleTest[j] = grilleTest[j+1];
                    grilleTest[j+1] = tmp;
                    finTri = false;
                    compteur ++;
                }
            }
            if(finTri){
                break;
            }

        }

        return compteur;
    }

    public static int[] retirerCaseVideTableau(int[] tabGrille){
        /**
         * remove the empty cell from a 1D array representing a grid
         * @param the 1D array representation of a grid
         * @return the 1D array representation of the grid without the empty cell
         */
        int[] newTabGrille = new int[tabGrille.length - 1];
        int i = 0;
        for(int indice : tabGrille){
            if(indice !=0){
                newTabGrille[i] = indice;
                i++;
            }
        }
        return newTabGrille;
    }

    public int compterColonne(){
        /**
         * return the difference between the actual column of the empty celle, it the column she should be
         * @return the difference between the actual column of the empty celle, it the column she should be
         */
        Case c = this.retournerCaseVide();
        return this.longueur - c.getX();
    }

    public boolean testerSiGrilleSoluble(){
        /**
         * use retirerCaseVideTableau, compterInversions and compterColonne methods in order to decide if the grid can be solved or not
         * @return true if the grid can be solved
         */
        if((this.longueur%2) != 0){
            return (this.compterInversions() % 2) == 0;
        }else{
            return (this.compterColonne() % 2) != (this.compterInversions() % 2);
        }
    }

    public void saveToMemento(){
        /**
         * save the last grid into the caretaker
         */
        if(this.compteurMemento==0){
            this.pastGrid.saveGrille(new Memento(this.grille));
        }
    }

    public void undoLastMovement(){
        /**
         * retrieve the last saved grid from the caretaker in order to restore the grid in a previous state
         */
        if(!this.pastGrid.isEmpty()){
            if(this.pastGrid.retrieveMemento() instanceof Memento memento){
                this.grille = new HashSet<>(memento.getGrilleSauvegarde());
                this.compteurMemento--;
            }
        }
    }

}
