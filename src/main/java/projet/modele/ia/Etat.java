package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class Etat implements Cloneable {
    private int[][] etatGrille;
    private int xVide, yVide;
    private int longueur;
    private ArrayList<String> mouvements;
    private int profondeur;

    private int heuristique;


    private Etat() {
    }

    public Etat(Grille g) {
        this.profondeur = 0;
        this.mouvements = new ArrayList<>();
        this.longueur = g.getLongueur();
        this.etatGrille = new int[longueur][longueur];
        this.xVide = g.retournerCaseVide().getX();
        this.yVide = g.retournerCaseVide().getY();

        for (int i = 0; i < g.getLongueur(); i++) {
            for (int j = 0; j < g.getLongueur(); j++) {
                this.etatGrille[i][j] = g.transformerGrilleArray2D()[i][j].getIndice();
            }
        }
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public int getHeuristique(){
        return this.heuristique;
    }

    protected Etat clone() throws CloneNotSupportedException {
        Etat e2 = (Etat) super.clone();

        Etat e3 = new Etat();

        e3.xVide = e2.xVide;
        e3.yVide = e2.yVide;
        e3.longueur = e2.longueur;
        e3.profondeur = e2.profondeur;
        e3.mouvements = new ArrayList<>(e2.mouvements);
        e3.etatGrille = new int[e3.longueur][e3.longueur];
        for (int i = 0; i < e3.longueur; i++) {
            System.arraycopy(e2.etatGrille[i], 0, e3.etatGrille[i], 0, e3.longueur);
        }

        return e3;
    }

    public int[][] getEtatGrille() {
        return this.etatGrille;
    }

    public ArrayList<String> getMouvements() {
        return this.mouvements;
    }

    public int[] calculerPosVictoire(int val) {
        int[] result = new int[2];
        double div = (double) val/ (double) this.longueur;
        result[0] = (int) Math.ceil(div) -1;
        int temp = val % this.longueur - 1;
        if(temp == -1) {
            result[1] = this.longueur - 1;
        }else{
            result[1] = temp;
        }
        return result;
    }

    public void ManhattanDistance() {

        int result = 0;

        for(int i = 0; i < this.longueur; i++) {
            for(int j = 0; j < this.longueur; j++) {
                if(this.etatGrille[i][j]!=0){
                    int[] coordonneesVictoire = this.calculerPosVictoire(this.etatGrille[i][j]);
                    result += Math.abs (coordonneesVictoire[0]-i) + Math.abs (coordonneesVictoire[1]-j);
                }
            }
        }
        this.heuristique = result;

    }

    public Etat simulerDeplacement(IA.DEPLACEMENT d) throws CloneNotSupportedException {
        Etat e = null;
        switch (d) {
            case haut:
                if (this.xVide + 1 < this.longueur) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide + 1][e.yVide];
                    e.etatGrille[e.xVide + 1][e.yVide] = tmp;
                    e.xVide++;
                    e.mouvements.add("haut");
                    e.profondeur++;
                }
                break;
            case bas:
                if (this.xVide - 1 >= 0) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide - 1][e.yVide];
                    e.etatGrille[e.xVide - 1][e.yVide] = tmp;
                    e.xVide--;
                    e.mouvements.add("bas");
                    e.profondeur++;
                }
                break;
            case gauche:
                if (this.yVide + 1 < this.longueur) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide][e.yVide + 1];
                    e.etatGrille[e.xVide][e.yVide + 1] = tmp;
                    e.yVide++;
                    e.mouvements.add("gauche");
                    e.profondeur++;
                }
                break;
            case droite:
                if (this.yVide - 1 >= 0) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide][e.yVide - 1];
                    e.etatGrille[e.xVide][e.yVide - 1] = tmp;
                    e.yVide--;
                    e.mouvements.add("droite");
                    e.profondeur++;
                }
                break;
        }
        return e;
    }

    public boolean estComparable(int[][] e) {
        if (e.length == this.etatGrille.length) {
            return e[0].length == this.etatGrille[0].length;
        }
        return false;
    }

    @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : this.etatGrille) {
            for (int i : row) {
                sb.append(i);
            }
        }
        return sb.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etat etat = (Etat) o;
        return Arrays.deepEquals(etatGrille, etat.etatGrille);
    }

    public boolean equals(int[][] state) {
        return Arrays.deepEquals(etatGrille, state);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.longueur; i++) {
            s = s + Arrays.toString(this.etatGrille[i]) + "\n";
        }
        return s;
    }
}
