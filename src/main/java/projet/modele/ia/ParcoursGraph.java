package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * The ParcoursGraph abstract class represents a general graph-based algorithm for solving a puzzle.
 * It implements the IA interface.
 */
public abstract class ParcoursGraph implements IA {
    protected int[][] etatBut;  // The target state of the puzzle.
    protected LinkedList<String>  mouvements;  // The list of movements in the solution path.
    protected LinkedList<Etat> graph;  // The graph representing possible states.
    protected HashSet<Etat> visited;  // A set to keep track of visited states.
    protected Thread threadResolution;  // Thread for resolving the puzzle.

    /**
     * Abstract method to be implemented by subclasses to execute the algorithm.
     *
     * @param g The initial state of the puzzle grid.
     * @throws CloneNotSupportedException If cloning the initial state is not supported.
     */
    protected abstract void executer(Grille g) throws CloneNotSupportedException;

    /**
     * Initializes the ParcoursGraph with the target state of the puzzle.
     *
     * @param g The initial state of the puzzle grid.
     */
    protected void initialiser(Grille g){
        visited = new HashSet<>();
        this.graph = new LinkedList<>();
        this.etatBut = new int[g.getLongueur()][g.getLongueur()];
        int ind = 1;

        for(int i=0;i<g.getLongueur();i++){
            for(int j=0;j<g.getLongueur();j++){
                this.etatBut[i][j] = ind;
                ind++;
            }
        }
        this.etatBut[g.getLongueur()-1][g.getLongueur()-1] = 0;
    }

    /**
     * Checks if a state has been visited.
     *
     * @param etatTest The state to test for visitation.
     * @return True if the state is visited, false otherwise.
     */
    protected boolean estVisite(Etat etatTest) {
        return visited.add(etatTest);
    }

    /**
     * Gets the length of the solution path.
     *
     * @return The number of movements in the solution path.
     */
    public int getLongueurMouvements(){
        return mouvements.size();
    }

    /**
     * Abstract method to be implemented by subclasses to apply possible actions to simulate next states.
     *
     * @param etatTest The state to test and simulate actions from.
     * @throws CloneNotSupportedException If cloning the state for simulation is not supported.
     */
    protected  abstract void appliquerAction(Etat etatTest) throws CloneNotSupportedException;

}
