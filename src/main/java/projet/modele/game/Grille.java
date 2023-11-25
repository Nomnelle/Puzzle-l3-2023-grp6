package projet.modele.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * The Grille class represents the grid of a puzzle game.
 * It implements the Parametres interface and includes methods for managing the grid state.
 */
public class Grille implements Parametres, Serializable {

    /**
     * The Memento class is a nested class used to save past grid states.
     */
    private static class Memento implements Serializable{  //class to save past grid state
        private final HashSet<Case> grilleSauvegarde;  //save a state of the grid

        /**
         * Constructs a Memento object with the given grid state.
         *
         * @param etatGrille The state of the grid to be saved.
         */
        public Memento(HashSet<Case> etatGrille){
            this.grilleSauvegarde = new HashSet<>();

            for(Case c : etatGrille){
                this.grilleSauvegarde.add(new Case(c));
            }
        }

        /**
         * Gets the saved grid state.
         *
         * @return The saved grid state.
         */
        public HashSet<Case> getGrilleSauvegarde() {
            return this.grilleSauvegarde;
        }
    }

    private HashSet<Case> grille; //representation of the grid
    private final int longueur;  //number of cells in a row or in a column
    private int nombreCoups;  //number of time the player has moved a cell
    private Caretaker pastGrid;  //save past states of the grid
    private int compteurMemento;  //save number of time the player got back to a past grid


    /**
     * Constructs a Grille object with the specified length.
     *
     * @param l The length of the grid.
     */
    public Grille(int l) {
        this.grille = new HashSet<>();
        this.longueur = l;
        this.nombreCoups = 0;
        pastGrid = new Caretaker();
        compteurMemento = 4;
    }

    /**
     * Get you the representation of the grille
     * @return the value of the attribute grille
     */
    public HashSet<Case> getGrille() {
        return grille;
    }

    /**
     * Get you the length of the grille
     * @return the value the of attribute longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * Get you the number of movements performed by the player
     * @return the attribute nbCoups
     */
    public int getNombreCoups() {
        return nombreCoups;
    }

    /**
     * Get you the number of time the player can undo
     * @return the number of time the player can undo
     */
    public int getCompteurMemento(){
        return compteurMemento;
    }

    /**
     * Fills the grid with numbers and randomizes their order.
     */
    public void remplirGrille(){
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

    /**
     * Transforms the grid into a 1D array.
     *
     * @return The grid representation in a 1D array.
     */
    public int[] transformerGrilleArray1D() {
        Case[][] tableau2D = transformerGrilleArray2D();
        int[] resultat = new int[this.longueur*this.longueur];
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < longueur; j++) {
                resultat[i * longueur + j] = tableau2D[i][j].getIndice();
            }
        }
        return resultat;
    }

    /**
     * Transform the grid in a 2D array
     * @return the grid representation in 2D array
     */
    public Case[][] transformerGrilleArray2D() {
        Case[][] tableau = new Case[this.longueur][this.longueur];
        for (Case c : this.grille) {
            tableau[c.getX()][c.getY()] = c;
        }
        return tableau;
    }

    /**
     * Override toString method in class Object, in order to have a String representation of the grid
     * @return the grid representation in a String
     */
    @Override
    public String toString() {
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

    /**
     * retrieve a cell placed in the corresponding x and y coordinates
     * @param x,y coordinates of a cell
     * @return the cell places in this x and y coordinates
     */
    public Case retrouverCase(int y, int x) {
        for (Case c : this.grille) {
            if (y == c.getY() && x == c.getX()) {
                return c;
            }
        }
        return null;
    }

    /**
     * retrieve the empty cell
     * @return the empty cell
     */
    public Case retournerCaseVide() {
        for (Case c : this.grille) {
            if (c.getIndice() == 0) {
                return c;
            }
        }
        return null;
    }

    /**
     * move a cell according to an input
     * @param input an input in french - haut, bas, droite, and gauche
     * @return the coordinates of the moving cell before it moves
     */
    public int[] deplacerCase(String input) {
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

    /**
     * Checks if the player has won the game.
     *
     * @return True if the grid is in the expected state for the player to win.
     */
    public boolean verifierVictoire() {
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

    /**
     * Counts how many times two cells must be exchanged before the grid is in a winning state (bubble sort).
     *
     * @return The number of times cells have exchanged their places.
     */
    public int compterInversions(){
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

    /**
     * Removes the empty cell from a 1D array representing a grid.
     *
     * @param tabGrille The 1D array representation of a grid.
     * @return The 1D array representation of the grid without the empty cell.
     */
    public static int[] retirerCaseVideTableau(int[] tabGrille){
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

    /**
     * return the difference between the actual column of the empty cell, and the column she should be
     * @return the difference between the actual column of the empty cell, and the column she should be
     */
    public int compterColonne(){
        Case c = this.retournerCaseVide();
        return this.longueur - c.getX();
    }

    /**
     * Uses various methods to decide if the grid can be solved.
     *
     * @return True if the grid can be solved.
     */
    public boolean testerSiGrilleSoluble(){
        if((this.longueur%2) != 0){
            return (this.compterInversions() % 2) == 0;
        }else{
            return (this.compterColonne() % 2) != (this.compterInversions() % 2);
        }
    }

    /**
     * Saves the last grid state into the caretaker.
     */
    public void saveToMemento(){
        if(this.compteurMemento>0){
            this.pastGrid.saveGrille(new Memento(this.grille));
        }
    }

    /**
     * Retrieves the last saved grid state from the caretaker to restore the grid to a previous state.
     */
    public void undoLastMovement(){
        if(!this.pastGrid.isEmpty()&&this.compteurMemento>0){
            if(this.pastGrid.retrieveMemento() instanceof Memento memento){
                this.grille = new HashSet<>(memento.getGrilleSauvegarde());
                this.compteurMemento--;
            }
        }
    }

}
