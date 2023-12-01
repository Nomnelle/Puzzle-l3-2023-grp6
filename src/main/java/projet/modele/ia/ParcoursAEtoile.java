package projet.modele.ia;

import projet.controller.GrilleController;
import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * The ParcoursAEtoile class represents an A* algorithm for solving a puzzle.
 * It extends the ParcoursGraph abstract class and implements the IA interface.
 */
public class ParcoursAEtoile extends ParcoursGraph implements IA {

    PriorityQueue<Etat> listeOuverte = new PriorityQueue<>();  //Priority queue for open list

    /**
     * Default constructor for ParcoursAEtoile.
     */
    public ParcoursAEtoile(){}

    /**
     * Constructor for ParcoursAEtoile with an initial grid state.
     *
     * @param g The initial state of the puzzle grid.
     * @throws CloneNotSupportedException If cloning the initial state is not supported.
     */
    public ParcoursAEtoile(Grille g) throws CloneNotSupportedException {
        this.initialiser(g);
        this.executer(g);
    }

    /**
     * Executes the A* algorithm to find the solution path for the puzzle.
     *
     * @param g The initial state of the puzzle grid.
     * @throws CloneNotSupportedException If cloning the initial state is not supported.
     */
    @Override
    protected void executer(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        Etat courant = new Etat(g);
        listeOuverte.add(courant);
        boolean trouve = false;
        while ((!listeOuverte.isEmpty()) && (!trouve)) {

            courant = listeOuverte.poll();
            //update the closed list
            this.ajouterListFermee(courant);

            appliquerAction(courant);

            if (courant.equals(etatBut)) {
                trouve = true;
            }
        }

        if (trouve) {
            result = new ArrayList<>(courant.getMouvements());

        }

        mouvements = new LinkedList<>(result);
    }

    /**
     * Solves the puzzle and updates the GUI using a separate thread.
     *
     * @param g         The initial state of the puzzle grid.
     * @param gControl  The controller for updating the GUI.
     */
    public void resoudreGUI(Grille g, GrilleController gControl){
        threadResolution = new Thread(() -> {
            initialiser(g);
            try {
                executer(g);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            } catch(OutOfMemoryError oom){

            }

            while(!mouvements.isEmpty()){
                if(threadResolution.isInterrupted()){
                    break;
                }


                gControl.executerMouvement(next());
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        });
        threadResolution.start();
    }

    /**
     * Stops the puzzle-solving thread.
     */
    public void stopperResolution() {
        if (threadResolution != null && threadResolution.isAlive()) {
            threadResolution.interrupt();
        }
    }

    /**
     * Applies possible actions to simulate next states in the A* algorithm.
     *
     * @param etatTest The state to test and simulate actions from.
     * @throws CloneNotSupportedException If cloning the state for simulation is not supported.
     */
    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        boolean ajoute = true;
        for (DEPLACEMENT d : DEPLACEMENT.values()) {
            Etat e = etatTest.simulerDeplacement(d);
            if (e != null) {
                if(!visited.contains(e)) {
                    ajoute = listeOuverte.add(e);
                } else {
                    Etat eListeOuverte = rechercherListeOuverte(e);
                    if(eListeOuverte != null && ajoute){
                        if (e.getPriorite() < eListeOuverte.getPriorite()) {
                            eListeOuverte.setPriorite(e.getPriorite());
                            eListeOuverte.setHeuristique(e.getHeuristique());
                            eListeOuverte.setProfondeur(e.getProfondeur());
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the next movement in the solution path.
     *
     * @return The next movement in the solution path.
     */
    @Override
    public String next() {
        return mouvements.pop();
    }

    /**
     * Adds a state to the closed list if it is in the open list.
     *
     * @param etat The state to add to the closed list.
     */
    private void ajouterListFermee(Etat etat) {
        if(listeOuverte.contains(etat)) {
            boolean ajoute = visited.add(etat);
            if(ajoute){
                listeOuverte.remove(etat);
            }
        }
    }

    /**
     * Searches for a state in the open list.
     *
     * @param etat The state to search for in the open list.
     * @return The found state or null if not found.
     */
    private Etat rechercherListeOuverte(Etat etat) {
        for(Etat e : listeOuverte) {
            if (e.equals(etat)) {
                return e;
            }
        }
        return null;
    }

}

