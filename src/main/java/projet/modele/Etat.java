package projet.modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Etat implements Cloneable{
    public int[][] etatGrille;
    public int xVide, yVide;
    public int longueur;
    public ArrayList<String> mouvements;

    private Etat(){}

    public Etat(Grille g){
        mouvements = new ArrayList<>();
        this.longueur = g.getLongueur();
        this.etatGrille = new int[longueur][longueur];
        this.xVide = g.retournerCaseVide().getX();
        this.yVide = g.retournerCaseVide().getY();

        for(int i = 0;i<g.getLongueur();i++){
            for(int j = 0;j<g.getLongueur();j++){
                this.etatGrille[i][j] = g.transformerGrilleArray2D()[i][j].getIndice();
            }
        }
    }

    public int getLongueur(){
        return this.longueur;
    }

    protected Etat clone() throws CloneNotSupportedException{
        Etat e2 = (Etat) super.clone();

        Etat e3 = new Etat();

        e3.xVide = e2.xVide;
        e3.yVide = e2.yVide;
        e3.longueur = e2.longueur;
        e3.mouvements = new ArrayList<>(e2.mouvements);
        e3.etatGrille = new int[e3.longueur][e3.longueur];
        for(int i = 0;i<e3.longueur;i++){
            System.arraycopy(e2.etatGrille[i], 0, e3.etatGrille[i], 0, e3.longueur);
        }

        return e3;
    }

    public int[][] getEtatGrille(){
        return this.etatGrille;
    }

    public ArrayList<String> getMouvements(){
        return this.mouvements;
    }

    public Etat simulerDeplacement(IA.DEPLACEMENT d) throws CloneNotSupportedException {
        Etat e = null;
        switch (d){
            case haut:
                if(this.xVide+1<this.longueur) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide+1][e.yVide];
                    e.etatGrille[e.xVide+1][e.yVide] = tmp;
                    e.xVide++;
                    e.mouvements.add("haut");
                }
                break;
            case bas:
                if(this.xVide-1>=0) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide-1][e.yVide];
                    e.etatGrille[e.xVide-1][e.yVide] = tmp;
                    e.xVide--;
                    e.mouvements.add("bas");
                }
                break;
            case gauche:
                if(this.yVide+1<this.longueur) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide][e.yVide+1];
                    e.etatGrille[e.xVide][e.yVide+1] = tmp;
                    e.yVide++;
                    e.mouvements.add("gauche");
                }
                break;
            case droite:
                if(this.yVide-1>=0) {
                    e = this.clone();
                    int tmp = e.etatGrille[e.xVide][e.yVide];
                    e.etatGrille[e.xVide][e.yVide] = e.etatGrille[e.xVide][e.yVide - 1];
                    e.etatGrille[e.xVide][e.yVide - 1] = tmp;
                    e.yVide--;
                    e.mouvements.add("droite");
                }
                break;
        }
        return e;
    }

    public boolean estComparable(int[][] e){
        if(e.length == this.etatGrille.length){
            return e[0].length == this.etatGrille[0].length;
        }
        return false;
    }

    public boolean estIdentique(int[][] etatBut){
        boolean but = true;
        for(int i =0;i<longueur;i++) {
            for (int j = 0; j < longueur; j++) {
                if (etatGrille[i][j] != etatBut[i][j]) {
                    return false;
                }
            }
        }
        return but;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i=0;i<this.longueur;i++){
            s = s+Arrays.toString(this.etatGrille[i])+"\n";
        }
        return s;
    }
}
