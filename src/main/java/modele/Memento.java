package modele;

import java.util.HashSet;

public class Memento {
    private final HashSet<Case> grilleSauvegarde;

    public Memento(HashSet<Case> etatGrille){
        this.grilleSauvegarde = new HashSet<>();

        for(Case c : etatGrille){
            this.grilleSauvegarde.add(new Case(c));
        }
    }

    public HashSet<Case> getGrilleSauvegarde() {
        return this.grilleSauvegarde;
    }
}
