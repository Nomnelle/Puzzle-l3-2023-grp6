package projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParcoursProfondeur extends ParcoursGraph implements IA {

    public ParcoursProfondeur(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
    }

    @Override
    protected void execute(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        graph.add(new Etat(g));
        boolean trouve = false;
        while((!graph.isEmpty())&&(!trouve)){
            Etat e = graph.removeFirst();
            if(e.getProfondeur()<30){
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

    @Override
    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        for(DEPLACEMENT d : DEPLACEMENT.values()){
            Etat e = etatTest.simulerDeplacement(d);
            if(e!=null){
                graph.addFirst(e);
            }
        }
    }
}
