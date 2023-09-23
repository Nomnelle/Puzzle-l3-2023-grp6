package modele;

import java.util.HashSet;
import java.util.Arrays;
public class Grille implements Parametres {

    private final HashSet<Case> grille;
    private final int longueur;

    public Grille() {
        this.grille = new HashSet<>();
        this.longueur = TAILLE;
    }

    public Grille(int l) {
        this.grille = new HashSet<>();
        this.longueur = l;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getLongueur() {
        return longueur;
    }

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

    public Case[][] transformerGrilleArray2D() {
        Case[][] tableau = new Case[this.longueur][this.longueur];
        for (Case c : this.grille) {
            tableau[c.getX()][c.getY()] = c;
        }
        return tableau;
    }

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

    public Case retrouverCase(int y, int x) {
        for (Case c : this.grille) {
            if (y == c.getY() && x == c.getX()) {
                return c;
            }
        }
        return null;
    }

    public Case retournerCaseVide() {
        for (Case c : this.grille) {
            if (c.getIndice() == 0) {
                return c;
            }
        }
        return null;
    }

    public void deplacerCase(String input) {
        Case vide = retournerCaseVide();
        Case mouvante;
        switch (input) {
            case "haut":
                if (longueur > vide.getX() + 1) {
                    mouvante = retrouverCase(vide.getY(), vide.getX() + 1);
                    vide.echangerValeursCases(mouvante);
                }
            case "bas":
                if (0 <= vide.getX() - 1) {
                    mouvante = retrouverCase(vide.getY(), vide.getX() - 1);
                    vide.echangerValeursCases(mouvante);
                }
            case "gauche":
                if (longueur > vide.getY() + 1) {
                    mouvante = retrouverCase((vide.getY() + 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                }
            case "droite":
                if (0 <= vide.getY() - 1) {
                    mouvante = retrouverCase((vide.getY() - 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                }
        }
    }

    public boolean verifierVictoire() {
        int[] etatActuel = this.transformerGrilleArray1D();
        for(int i = 1; i < (longueur^2) - 1; i++) {
            if (etatActuel[i-1] > etatActuel[i]) {
                return false;
            }
        }
        return true;
    }

    public int compterInversions(){
        int[] grilleTest = this.transformerGrilleArray1D();
        int compteur = 0;
        boolean finTri;

        for(int i = grilleTest.length-1; i<=0; i--){
            finTri = true;
            for(int j = 0; j<i-1;j++){
                if((grilleTest[j]<grilleTest[j+1])){
                    int tmp = grilleTest[j];
                    grilleTest[j] = grilleTest[j+1];
                    grilleTest[j+1] = tmp;
                    if((grilleTest[j]!=0)&&(grilleTest[j+1]!=0)){
                        compteur +=1;
                    }
                }
            }

        }

        return compteur;
    }

    public int compterColonne(){
        Case c = this.retournerCaseVide();
        return this.longueur - c.getY();
    }

    public boolean testerSolubiliteGrille(){

        if(this.longueur%2!=0){
            if(this.compterInversions()%2==0){
                return true;
            }
        }else{
            if(this.compterInversions()%2 != this.compterColonne()%2){
                return true;
            }
        }

        return false;
    }

}
