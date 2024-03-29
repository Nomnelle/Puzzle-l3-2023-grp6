import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;
import projet.modele.ia.ParcoursAEtoile;
import projet.modele.ia.ParcoursGraph;
import projet.modele.ia.ParcoursLargeur;
import projet.modele.ia.ParcoursProfondeur;

public class TestIA3 {

    Grille grilleTest = new Grille(4);

    ParcoursGraph ia;

    @BeforeEach
    public void initGrille(){
        grilleTest.getGrille().add(new Case(0,0,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"M",13,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(0,3,"J",10,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"I",9,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"K",11,grilleTest));
        grilleTest.getGrille().add(new Case(1,3,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"0",15,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"L",12,grilleTest));
        grilleTest.getGrille().add(new Case(2,3,"E",5,grilleTest));
        grilleTest.getGrille().add(new Case(3,0,"N",14,grilleTest));
        grilleTest.getGrille().add(new Case(3,1,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(3,2,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(3,3,"D",4,grilleTest));
    }

    @Test
    public void testParcoursLargeur() throws CloneNotSupportedException {
        try {
            ia = new ParcoursLargeur(grilleTest);

            int nbMouvements = ia.getLongueurMouvements();

            for (int i = 0; i < nbMouvements; i++) {
                grilleTest.deplacerCase(ia.next());
            }
        }catch(OutOfMemoryError oom){

        }

        assert grilleTest.verifierVictoire();
    }

    @Test
    public void testParcoursProfondeur() throws CloneNotSupportedException {
        try {
            ia = new ParcoursProfondeur(grilleTest);

            int nbMouvements = ia.getLongueurMouvements();

            for (int i = 0; i < nbMouvements; i++) {
                grilleTest.deplacerCase(ia.next());
            }
        }catch(OutOfMemoryError oom){

        }
        assert grilleTest.verifierVictoire();
    }

    @Test
    public void testParcoursAEtoile() throws CloneNotSupportedException{
        ia = new ParcoursAEtoile(grilleTest);

        int nbMouvements = ia.getLongueurMouvements();
        System.out.println(ia.getLongueurMouvements());

        for(int i = 0;i<nbMouvements;i++){
            grilleTest.deplacerCase(ia.next());
        }

        assert grilleTest.verifierVictoire();
    }
}
