package projet.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParcoursProfondeur extends ParcoursGraph implements IA {

    public ParcoursProfondeur(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
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
