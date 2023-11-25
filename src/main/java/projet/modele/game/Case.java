package projet.modele.game;

import java.io.Serializable;

/**
 * Represents a cell in a grid, containing coordinates, a piece with value and winning position index,
 * and a reference to the grid it belongs to.
 */
public class Case implements Serializable {

    private final int x, y;  //coordinates of the cells
    private class Piece implements Serializable{  //Structure for winning position index and the value contained in the cell
        public String valeur;
        public int indice;
    }
    private Piece piece = new Piece();  //winning position index and the value contained in the cell
    private Grille grille;  //the grid it belongs to

    /**
     * Constructs a Case object with specified coordinates, value, winning position index, and grid reference.
     *
     * @param xVal The x-coordinate of the cell.
     * @param yVal The y-coordinate of the cell.
     * @param v The value contained in the cell.
     * @param ind The winning position index.
     * @param g The grid in which the cell is located.
     */
    public Case(int xVal, int yVal, String v, int ind, Grille g) {
        this.x = xVal;
        this.y = yVal;
        this.piece.valeur = v;
        this.piece.indice = ind;
        this.grille = g;
    }

    /**
     * Constructs a Case object with specified coordinates, default value (converted from index),
     * winning position index, and grid reference.
     *
     * @param xVal The x-coordinate of the cell.
     * @param yVal The y-coordinate of the cell.
     * @param ind The winning position index.
     * @param g The grid in which the cell is located.
     */
    public Case(int xVal, int yVal, int ind, Grille g) {
        this.x = xVal;
        this.y = yVal;
        this.piece.valeur = String.valueOf(ind);
        this.piece.indice = ind;
        this.grille = g;
    }

    /**
     * Constructs a Case object by copying another Case object.
     *
     * @param c The Case object to copy.
     */
    public Case(Case c){
        this.x = c.x;
        this.y = c.y;
        this.piece.valeur = c.piece.valeur;
        this.piece.indice = c.piece.indice;
        this.grille = c.grille;
    }

    /**
     * Gets the x-coordinate of the cell.
     *
     * @return The x-coordinate of the cell.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the cell.
     *
     * @return The y-coordinate of the cell.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the value contained in the cell.
     *
     * @return The value contained in the cell.
     */
    public String getValeur() { return this.piece.valeur; }

    /**
     * Gets the winning position index.
     *
     * @return The winning position index.
     */
    public int getIndice() { return this.piece.indice; }

    /**
     * Sets the grid reference for the cell.
     *
     * @param g The grid in which the cell is located.
     */
    public void setGrille(Grille g) { this.grille = g; }

    /**
     * Overrides the equals method to check equality based on coordinates.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    /**
     * Overrides the hashCode method to determine the hash code based on coordinates.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }

    /**
     * Overrides the toString method to provide a string representation of the Case object.
     *
     * @return A string representation of the Case object.
     */
    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.piece.valeur + ")";
    }

    /**
     * Swaps the values of two Case objects if the current piece index is 0.
     *
     * @param c The Case object to swap values with.
     */
    public void echangerValeursCases(Case c) {
        if (this.piece.indice == 0) {
            Piece temp = this.piece;
            this.piece = c.piece;
            c.piece = temp;
        }
    }
}
