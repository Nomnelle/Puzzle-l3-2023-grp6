package controller.puzzle;
import java.util.HashSet;
import java.util.Arrays;
public class Grille implements Parametres {

    private final HashSet<Case> grille;
    private int longueur;
    private String[] etatVictoire;

    public Grille() {
        this.grille = new HashSet<>();
        this.longueur = TAILLE;
        this.etatVictoire = RESULTAT;
    }

    public Grille(int l) {
        this.grille = new HashSet<>();
        this.longueur = l;
        this.etatVictoire = RESULTAT;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getLongueur() {
        return longueur;
    }

    public String[] transformerGrilleArray1D() {
        String[][] tableau2D = transformerGrilleArray2D();
        String[] resultat = new String[longueur ^ 2];
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < longueur; j++) {
                resultat[i * longueur + j] = tableau2D[j][i];
            }
        }
        return resultat;
    }

    public String[][] transformerGrilleArray2D() {
        String[][] tableau = new String[this.longueur][this.longueur];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        return tableau;
    }

    @Override
    public String toString() {
        String[][] tableau = transformerGrilleArray2D();
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
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
            if (c.getValeur().compareTo("x") == 0) {
                return c;
            }
        }
        return null;
    }

    public static void echangerValeursCases(Case c1, Case c2) {
        if (c1.getValeur().compareTo("x") == 0 || c2.getValeur().compareTo("x") == 0) {
            String temp = c1.getValeur();
            c1.setValeur(c2.getValeur());
            c2.setValeur(temp);
        }
    }

    public void deplacerCase(String input) {
        Case vide = retournerCaseVide();
        Case mouvante;
        switch (input) {
            case "haut":
                if (longueur > vide.getX() + 1) {
                    mouvante = retrouverCase(vide.getY(), vide.getX() + 1);
                    echangerValeursCases(vide, mouvante);
                }
            case "bas":
                if (0 > vide.getX() - 1) {
                    mouvante = retrouverCase(vide.getY(), vide.getX() - 1);
                    echangerValeursCases(vide, mouvante);
                }
            case "droite":
                if (longueur > vide.getY() + 1) {
                    mouvante = retrouverCase(vide.getY() + 1, vide.getX());
                    echangerValeursCases(vide, mouvante);
                }
            case "gauche":
                if (0 > vide.getY() - 1) {
                    mouvante = retrouverCase(vide.getY() - 1, vide.getX());
                    echangerValeursCases(vide, mouvante);
                }
        }
    }

    public boolean verifierVictoire() {
        String[] etatActuel = this.transformerGrilleArray1D();
        for(int i = 0; i < (longueur^2); i++) {
            if (etatActuel[i].compareTo(this.etatVictoire[i]) != 0) {
                return false;
            }
        }
        return true;
    }

}
