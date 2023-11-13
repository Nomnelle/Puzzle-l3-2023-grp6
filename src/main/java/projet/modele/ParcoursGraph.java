package projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class ParcoursGraph implements IA {
    protected int[][] etatBut;
    protected LinkedList<String> mouvements;
    protected LinkedList<Etat> graph;
    protected  ArrayList<Etat> visited;

    protected abstract void execute(Grille g) throws CloneNotSupportedException;

    protected void init(Grille g){
        visited = new ArrayList<>();
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
            if(e!=null){
                graph.add(e);
            }
        }
    }


}
