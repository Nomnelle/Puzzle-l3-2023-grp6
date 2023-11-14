import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;
import projet.modele.ia.ParcoursGraph;
import projet.modele.ia.ParcoursLargeur;
import projet.modele.ia.ParcoursProfondeur;

public class TestIA2 {

    Grille grilleTest = Grille.getInstance(3);
    ParcoursGraph ia;

    @BeforeEach
    public void initGrille(){
        grilleTest.getGrille().add(new Case(0,0,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"E",5,grilleTest));
    }

    @Test
    public void testParcoursLargeur() throws CloneNotSupportedException {
        ia = new ParcoursLargeur(grilleTest);

        int nbMouvements = ia.getLongueurMouvements();

        for(int i = 0;i<nbMouvements;i++){
            grilleTest.deplacerCase(ia.next());
        }

        assert grilleTest.verifierVictoire();
    }

    @Test
    public void testParcoursProfondeur() throws CloneNotSupportedException {
        ia = new ParcoursProfondeur(grilleTest);

        int nbMouvements = ia.getLongueurMouvements();

        for (int i = 0; i < nbMouvements; i++) {
            grilleTest.deplacerCase(ia.next());
        }

        assert grilleTest.verifierVictoire();
    }
}
