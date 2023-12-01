package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The ParcoursLargeur class represents a breadth-first search algorithm for solving a puzzle.
 * It extends ParcoursGraph and implements the IA interface.
 */
public class ParcoursLargeur extends ParcoursGraph implements IA{

    /**
     * Constructs a ParcoursLargeur object and initializes the search algorithm.
     *
     * @param g The initial state of the puzzle grid.
     * @throws CloneNotSupportedException If cloning the initial state is not supported.
     */
    public ParcoursLargeur(Grille g) throws CloneNotSupportedException {
        this.initialiser(g);
        this.executer(g);
    }

    /**
     * Executes the breadth-first search algorithm to find a solution to the puzzle.
     *
     * @param g The initial state of the puzzle grid.
     * @throws CloneNotSupportedException If cloning the initial state is not supported.
     */
    @Override
    protected void executer(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        graph.add(new Etat(g));
        visited.add(new Etat(g));
        boolean trouve = false;
        while ((!graph.isEmpty()) && (!trouve)) {
            Etat e = graph.pop();
            if (e.estComparable(this.etatBut))
                if (!e.equals(etatBut)) {
                    this.appliquerAction(e);
                } else {
                    trouve = true;
                    result = e.getMouvements();
                }
            else {
                mouvements = new LinkedList<>();
                return;
            }
        }
        if (trouve) {
            mouvements = new LinkedList<>(result);
        } else {
            mouvements = new LinkedList<>();
        }
    }

    /**
     * Applies the possible actions to simulate the next states in the search.
     *
     * @param etatTest The state to test and simulate actions from.
     * @throws CloneNotSupportedException If cloning the state for simulation is not supported.
     */
    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        for (DEPLACEMENT d : DEPLACEMENT.values()) {
            Etat e = etatTest.simulerDeplacement(d);
            if (e != null) {
                if (this.estVisite(e)) {
                    graph.add(e);
                }
            }
        }
    }

    /**
     * Gets the next move in the solution path.
     *
     * @return The next move as a string.
     */
    @Override
    public String next() {
        return mouvements.removeFirst();
    }

    /**
     * Returns a string representation of the solution path.
     *
     * @return A string representation of the solution path.
     */
    @Override
    public  String toString(){
        return mouvements.toString();
    }

}
