import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;
import projet.modele.ia.*;

public class TestIA {

    Grille grilleTest = new Grille(2);
    ParcoursGraph ia;

    @BeforeEach
    public void initGrille(){
        grilleTest.getGrille().add(new Case(0,0,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"B",2,grilleTest));
    }

    @Test
    public void testerEtatsIdentiques(){
        Etat e = new Etat(grilleTest);

        int[][] grilleTab2D = new int[grilleTest.getLongueur()][grilleTest.getLongueur()];
        for(int i = 0;i<grilleTab2D.length;i++){
            for(int j = 0;j<grilleTab2D.length;j++){
                grilleTab2D[i][j] = e.getEtatGrille()[i][j];
            }
        }

        assert e.estComparable(grilleTab2D);
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

        for(int i = 0;i<nbMouvements;i++){
            grilleTest.deplacerCase(ia.next());
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
