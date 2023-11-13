package projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class ParcoursGraph implements IA {
    protected int[][] etatBut;
    protected LinkedList<String> mouvements;
    protected LinkedList<Etat> graph;
    protected  ArrayList<Etat> visited;

    protected abstract void execute(Grille g) throws CloneNotSupportedException;

    protected boolean estVisite(Etat etatTest) {
        for(Etat e : visited){
            if(e.estComparable(etatTest.getEtatGrille())){
                if(e.estIdentique(etatTest.getEtatGrille())){
                    return true;
                }
            }
        }
        visited.add(etatTest);
        return false;
    }

    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        for(DEPLACEMENT d : DEPLACEMENT.values()){
            Etat e = etatTest.simulerDeplacement(d);
            System.out.println(e+"-->"+d);
            if(e!=null){
                graph.add(e);
            }
        }
    }


}
