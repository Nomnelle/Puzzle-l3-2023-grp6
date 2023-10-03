package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Grille implements Parametres {

    private HashSet<Case> grille;
    private final int longueur;
    private int nombreCoups;

    public Grille() {
        this.grille = new HashSet<>();
        this.longueur = TAILLE;
        this.nombreCoups = 0;
    }

    public Grille(int l) {
        this.grille = new HashSet<>();;
        this.longueur = l;
        this.nombreCoups = 0;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getNombreCoups() { return nombreCoups; }

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
                    this.nombreCoups++;
                }
                break;
            case "bas":
                if (0 <= vide.getX() - 1) {
                    mouvante = retrouverCase(vide.getY(), vide.getX() - 1);
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                }
                break;
            case "gauche":
                if (longueur > vide.getY() + 1) {
                    mouvante = retrouverCase((vide.getY() + 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                }
                break;
            case "droite":
                if (0 <= vide.getY() - 1) {
                    mouvante = retrouverCase((vide.getY() - 1), vide.getX());
                    vide.echangerValeursCases(mouvante);
                    this.nombreCoups++;
                }
                break;
        }
    }

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

    public int compterColonne(){
        Case c = this.retournerCaseVide();
        return this.longueur - c.getX();
    }

    public boolean testerSiGrilleSoluble(){
        if((this.longueur%2) != 0){
            if((this.compterInversions()%2)==0){
                return true;
            }
        }else{
            if((this.compterColonne() % 2) != (this.compterInversions() % 2)){
                return true;
            }
        }
        return false;
    }

}
