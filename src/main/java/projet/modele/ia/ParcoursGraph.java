package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class ParcoursGraph implements IA {
    protected int[][] etatBut;
    protected LinkedList<String> mouvements;
    protected LinkedList<Etat> graph;
    protected HashSet<Etat> visited;

    protected void execute(Grille g) throws CloneNotSupportedException{
        ArrayList<String> result = new ArrayList<>();
        graph.add(new Etat(g));
            visited.add(new Etat(g));
            boolean trouve = false;
            while((!graph.isEmpty())&&(!trouve)){
                Etat e = graph.pop();
                if(e.estComparable(this.etatBut))
                    if(!e.equals(etatBut)){
                        this.appliquerAction(e);
                    }else{
                        trouve = true;
                        result = e.getMouvements();
                    }
                else{
                    mouvements = new LinkedList<>();
                    return;
                }
            }
            if(trouve){
                mouvements = new LinkedList<>(result);
            }else{
                mouvements = new LinkedList<>();
            }


    }

    protected void init(Grille g){
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

    protected boolean estVisite(Etat etatTest) {
        return visited.add(etatTest);
    }

    public int getLongueurMouvements(){
        return mouvements.size();
    }

    protected  abstract void appliquerAction(Etat etatTest) throws CloneNotSupportedException;

}
