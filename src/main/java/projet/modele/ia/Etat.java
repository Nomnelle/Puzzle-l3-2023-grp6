package projet.modele.ia;

import org.jetbrains.annotations.NotNull;
import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

/**
 * Represents a state in the puzzle game.
 * This class is used to store and manipulate the state of the puzzle grid.
 */
public class Etat implements Cloneable, Comparable<Etat> {
    private int[][] etatGrille;  //Represents the current state of the puzzle as a 2D array.
    private int xVide, yVide;  //The x and y coordinate of the empty cell in the puzzle.
    private int longueur;  //The side length of the puzzle grid.
    private ArrayList<String> mouvements;  //Keeps track of the movements made to reach the current state.
    private int profondeur;  //The depth of the current state in the search tree.
    private int heuristique;  //The Manhattan distance heuristic value for the current state.
    private int priorite;  //The priority value used in priority-based algorithms like a*

    /**
     * Default constructor for the Etat class.
     * It initializes an empty state.
     */
    private Etat() {
    }

    /**
     * Constructs an Etat object based on the given puzzle grid.
     *
     * @param g The Grille object representing the puzzle grid.
     */
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
        this.calculerManhattanDistance();
        this.calculerPriorite();
    }

    /**
     * Gets the depth of the state in the solving process.
     *
     * @return The depth of the state.
     */
    public int getProfondeur() {
        return this.profondeur;
    }

    /**
     * Gets the heuristic value of the state.
     *
     * @return The heuristic value of the state.
     */
    public int getHeuristique() {
        return this.heuristique;
    }

    /**
     * Gets the priority of the state.
     *
     * @return The priority of the state.
     */
    public int getPriorite() {
        return this.priorite;
    }

    /**
     * Sets the priority of the state.
     *
     * @param priorite The new priority value.
     */
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    /**
     * Sets the depth of the state in the solving process.
     *
     * @param profondeur The new depth value.
     */
    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    /**
     * Sets the heuristic value of the state.
     *
     * @param heuristique The new heuristic value.
     */
    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }

    /**
     * Clones the current state object.
     *
     * @return A cloned instance of the current state.
     * @throws CloneNotSupportedException if the cloning process is not supported.
     */
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

    /**
     * Gets the current state of the puzzle grid.
     *
     * @return The 2D array representing the current state of the puzzle grid.
     */
    public int[][] getEtatGrille() {
        return this.etatGrille;
    }

    /**
     * Gets the list of movements performed to reach the current state.
     *
     * @return The list of movements.
     */
    public ArrayList<String> getMouvements() {
        return this.mouvements;
    }

    /**
     * Calculates the position of the victory state for a given value.
     *
     * @param val The value for which the position is calculated.
     * @return The array containing the row and column of the victory position.
     */
    public int[] calculerPosVictoire(int val) {
        int[] result = new int[2];
        double div = (double) val / (double) this.longueur;
        result[0] = (int) Math.ceil(div) - 1;
        int temp = val % this.longueur - 1;
        if (temp == -1) {
            result[1] = this.longueur - 1;
        } else {
            result[1] = temp;
        }
        return result;
    }

    /**
     * Calculates the Manhattan distance heuristic for the current state.
     * It represents the sum of distances between each tile and its correct position.
     */
    public void calculerManhattanDistance() {

        int result = 0;

        for (int i = 0; i < this.longueur; i++) {
            for (int j = 0; j < this.longueur; j++) {
                if (this.etatGrille[i][j] != 0) {
                    int[] coordonneesVictoire = this.calculerPosVictoire(this.etatGrille[i][j]);
                    result += Math.abs(coordonneesVictoire[0] - i) + Math.abs(coordonneesVictoire[1] - j);
                }
            }
        }
        this.heuristique = result;

    }

    /**
     * Calculates the priority of the current state.
     * It is the sum of the heuristic value and the depth of the state.
     */
    public void calculerPriorite() {
        this.priorite = this.heuristique + this.profondeur;
    }

    /**
     * Simulates a movement in the puzzle grid and generates a new state.
     *
     * @param d The direction of the movement.
     * @return The new state after the simulated movement.
     * @throws CloneNotSupportedException if the cloning process is not supported.
     */
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
                    e.calculerManhattanDistance();
                    e.calculerPriorite();
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
                    e.calculerManhattanDistance();
                    e.calculerPriorite();
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
                    e.calculerManhattanDistance();
                    e.calculerPriorite();
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
                    e.calculerManhattanDistance();
                    e.calculerPriorite();
                }
                break;
        }
        return e;
    }

    /**
     * Checks if the state is comparable to a given 2D array.
     *
     * @param e The 2D array to compare with.
     * @return True if the state is comparable, false otherwise.
     */
    public boolean estComparable(int[][] e) {
        if (e.length == this.etatGrille.length) {
            return e[0].length == this.etatGrille[0].length;
        }
        return false;
    }

    /**
     * Generates a hash code for the state based on its grid configuration.
     *
     * @return The hash code for the state.
     */
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

    /**
     * Checks if the current state is equal to another object.
     * It compares the grid configuration.
     *
     * @param o The object to compare with.
     * @return True if the states are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etat etat = (Etat) o;
        return Arrays.deepEquals(etatGrille, etat.etatGrille);
    }

    /**
     * Checks if the current state is equal to a 2D array representing a grid configuration.
     *
     * @param state The 2D array to compare with.
     * @return True if the states are equal, false otherwise.
     */
    public boolean equals(int[][] state) {
        return Arrays.deepEquals(etatGrille, state);
    }

    /**
     * Gets a string representation of the state, displaying the grid configuration.
     *
     * @return A string representation of the state.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.longueur; i++) {
            s = s + Arrays.toString(this.etatGrille[i]) + "\n";
        }
        return s;
    }

    /**
     * Compares this state with another state for sorting purposes.
     *
     * @param etat The Etat object to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(@NotNull Etat etat) {
        return Integer.compare(this.priorite, etat.priorite);
    }
}
