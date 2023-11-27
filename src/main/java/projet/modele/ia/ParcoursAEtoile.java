package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class ParcoursAEtoile extends ParcoursGraph implements IA {

    HashSet<Etat> listeFermee = new HashSet<>();

    public ParcoursAEtoile(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
    }

    @Override
    protected void execute(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        Etat courant = new Etat(g);
        visited.add(courant);
        boolean trouve = false;
        while ((!visited.isEmpty()) && (!trouve)) {

            courant = this.rechercherMeilleurNoeud();
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
                if(!listeFermee.contains(e)) {
                    ajoute = visited.add(e);
                } else {
                    Etat eListeOuverte = rechercherHashSet(visited, e);
                    if(eListeOuverte != null && !ajoute){
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


    public Etat rechercherMeilleurNoeud(){
        Etat meilleurEtat = null;

        for(Etat e : this.visited){
            if(!(meilleurEtat==null)){
                if(e.getPriorite()<meilleurEtat.getPriorite()){
                    meilleurEtat = e;
                }
            }else{
                meilleurEtat = e;
            }
        }
        return meilleurEtat;
    }

    //Cette fonction vérifie si l'état est dans la listeOuverte,
    // si c'est le cas l'état sera ajouté dans la listeFermee
    public void ajouterListFermee(Etat etat) {
        if (visited.contains(etat)) {
            boolean ajoute = listeFermee.add(etat);
            if(ajoute){
                visited.remove(etat);
            }
        }
    }


    //Cette fonction recherche un noeud dans HashSet
    private Etat rechercherHashSet(HashSet<Etat> set, Etat etat) {
        for (Etat e : set) {
            if (e.equals(etat)) {
                return e;
            }
        }
        return null;
    }

}

