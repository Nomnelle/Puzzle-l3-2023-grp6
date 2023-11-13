package projet.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ParcoursLargeur extends ParcoursGraph implements IA{

    public ParcoursLargeur(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
    }

    protected void execute(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        graph.add(new Etat(g));
        boolean trouve = false;
        while((!graph.isEmpty())&&(!trouve)){
            Etat e = graph.removeFirst();
            if(e.estComparable(this.etatBut))
                if(!e.estIdentique(this.etatBut)){
                    if(!this.estVisite(e)){
                        this.appliquerAction(e);
                    }
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

    @Override
    public String next() {
        return mouvements.removeFirst();
    }

    public int getLongueurMouvements(){
        return mouvements.size();
    }

    @Override
    public  String toString(){
        return mouvements.toString();
    }

}
