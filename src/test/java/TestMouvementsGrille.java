import modele.Grille;
import modele.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMouvementsGrille {

    private Grille grilleTest = new Grille(3);

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
    public void testerMouvementBas(){

        grilleTest.deplacerCase("bas");
        System.out.println(grilleTest.toString());
        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();

        int[] grilleAttendue = {0, 8, 2, 1, 4, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

    @Test
    public void testerMouvementHaut(){

        grilleTest.deplacerCase("haut");
        System.out.println(grilleTest.toString());
        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();

        int[] grilleAttendue = {1, 8, 2, 7, 4, 3, 0, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

    @Test
    public void testerMouvementGauche(){

        grilleTest.deplacerCase("gauche");
        System.out.println(grilleTest.toString());
        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();

        int[] grilleAttendue = {1, 8, 2, 4, 0, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

    @Test
    public void testerMouvementDroite(){

        grilleTest.deplacerCase("droite");
        System.out.println(grilleTest.toString());
        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();

        int[] grilleAttendue = {1, 8, 2, 0, 4, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

    @Test
    public void testerMouvementError(){

        grilleTest.deplacerCase("cgeufgir");
        System.out.println(grilleTest.toString());
        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();

        int[] grilleAttendue = {1, 8, 2, 0, 4, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

}
