package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ParcoursAEtoile extends ParcoursGraph implements IA {

    PriorityQueue<Etat> listeOuverte = new PriorityQueue<>();

    public ParcoursAEtoile(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
    }

    @Override
    protected void execute(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        Etat courant = new Etat(g);
        listeOuverte.add(courant);
        boolean trouve = false;
        while ((!listeOuverte.isEmpty()) && (!trouve)) {

            courant = listeOuverte.poll();
            //Met à jour la listeFermee
            this.ajouterListFermee(courant);

            appliquerAction(courant);

            if(courant.equals(etatBut)){
                trouve = true;
            }
        }

        if(trouve){
            result = new ArrayList<>(courant.getMouvements());

        }

        mouvements = new LinkedList<>(result);
    }

    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        boolean ajoute = true;
        for (DEPLACEMENT d : DEPLACEMENT.values()) {
            Etat e = etatTest.simulerDeplacement(d);
            if (e != null) {
                if(!visited.contains(e)) {
                    ajoute = listeOuverte.add(e);
                } else {
                    Etat eListeOuverte = rechercherHashSet(e);
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


    @Override
    public String next() {
        return mouvements.pop();
    }

    //Cette fonction vérifie si l'état est dans la listeOuverte,
    // si c'est le cas l'état sera ajouté dans la listeFermee
    public void ajouterListFermee(Etat etat) {
        if(listeOuverte.contains(etat)) {
            boolean ajoute = visited.add(etat);
            if(ajoute){
                listeOuverte.remove(etat);
            }
        }
    }


    //Cette fonction recherche un noeud dans HashSet
    private Etat rechercherHashSet(Etat etat) {
        for(Etat e : listeOuverte) {
            if (e.equals(etat)) {
                return e;
            }
        }
        return null;
    }

}

