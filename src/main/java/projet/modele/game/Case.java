package projet.modele.game;

import java.io.Serializable;

public class Case implements Serializable {

    private final int x, y;
    private class Piece implements Serializable{
        public String valeur;
        public int indice;
    }
    private Piece piece = new Piece();
    private Grille grille;

    public Case(int xVal, int yVal, String v, int ind, Grille g) {
        this.x = xVal;
        this.y = yVal;
        this.piece.valeur = v;
        this.piece.indice = ind;
        this.grille = g;
    }

    public Case(int xVal, int yVal, int ind, Grille g) {
        this.x = xVal;
        this.y = yVal;
        this.piece.valeur = String.valueOf(ind);
        this.piece.indice = ind;
        this.grille = g;
    }

    public Case(Case c){
        this.x = c.x;
        this.y = c.y;
        this.piece.valeur = c.piece.valeur;
        this.piece.indice = c.piece.indice;
        this.grille = c.grille;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getValeur() { return this.piece.valeur; }

    public int getIndice() { return this.piece.indice; }

    public void setGrille(Grille g) { this.grille = g; }

    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.piece.valeur + ")";
    }

    public void echangerValeursCases(Case c) {
        if (this.piece.indice == 0) {
            Piece temp = this.piece;
            this.piece = c.piece;
            c.piece = temp;
        }
    }
}
