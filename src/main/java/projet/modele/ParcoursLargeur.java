package projet.modele;
public class ParcoursLargeur extends ParcoursGraph implements IA{

    public ParcoursLargeur(Grille g) throws CloneNotSupportedException {
        this.init(g);
        this.execute(g);
    }

    protected void appliquerAction(Etat etatTest) throws CloneNotSupportedException {
        for (DEPLACEMENT d : DEPLACEMENT.values()) {
            Etat e = etatTest.simulerDeplacement(d);
            if (e != null) {
                if (this.estVisite(e)) {
                    graph.add(e);
                }
            }
        }
    }

    @Override
    public String next() {
        return mouvements.removeFirst();
    }

    @Override
    public  String toString(){
        return mouvements.toString();
    }

}
