package projet.modele.ia;

import projet.modele.game.Grille;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ParcoursAEtoile extends ParcoursGraph implements IA {

    @Override
    public String next() {

        return mouvements.pop();

    }

    public ParcoursAEtoile(Grille g) throws CloneNotSupportedException {

        this.init(g);
        this.execute(g);

    }


    HashSet<Etat> listeFermee = new HashSet<>();
    HashSet<Etat> listeOuverte = new HashSet<>();


    //Cette fonction vérifie si l'état est dans la listeOuverte,
    // si c'est le cas l'état sera ajouté dans la listeFermee
    public void ajouterListFermee(Etat etat) {

        if (listeOuverte.contains(etat)) {
            //Cette instruction ajoute l'état à la listeFerme
            listeFermee.add(etat);
        }

    }

    @Override
    protected void execute(Grille g) throws CloneNotSupportedException {
        ArrayList<String> result = new ArrayList<>();
        Etat courant = null;
        while (!visited.isEmpty()) {
            Iterator<Etat> iterator = visited.iterator();
            if (iterator.hasNext()) {
                courant = iterator.next();
                iterator.remove();
            }

            //Met à jour la listeFermee
            appliquerAction(courant);

            //Vérifie si l'état but est atteint
            try { //On vérifie si courant n'est pas null avant d'effectuer la comparaison avec this.etatBut
                if (courant != null && courant.equals(this.etatBut)) {
                    result = new ArrayList<>(courant.getMouvements());
                    break;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
        System.out.println(result);
    }

    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {

        for (DEPLACEMENT d : DEPLACEMENT.values()) {
            Etat e = etatTest.simulerDeplacement(d);
            if (e != null) {
                if (this.estVisite(e)) {
                    visited.add(e);
                } else {
                    Etat eListeOuverte = rechercherHashSet(listeOuverte, e);
                    if (eListeOuverte != null && e.getPriorite() > eListeOuverte.getPriorite()) {
                        eListeOuverte.setPriorite(e.getPriorite());
                        eListeOuverte.setHeuristique(e.getHeuristique());
                        eListeOuverte.setProfondeur(e.getProfondeur());
                        eListeOuverte.setParent(e.getParent());
                    }

                }
            }

        }
        //Cette instruction ajoute l'état à la listeFermee
        ajouterListFermee(etatTest);
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

