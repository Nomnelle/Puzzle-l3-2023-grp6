import projet.modele.Case;
import projet.modele.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMemento {

    private Grille grilleTest = Grille.getInstance(3);

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
    public void testMemento1(){
        grilleTest.deplacerCase("haut");
        grilleTest.deplacerCase("undo");

        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();
        int[] grilleAttendue = {1, 8, 2, 0, 4, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }

    @Test
    public void testMemento2(){
        grilleTest.deplacerCase("haut");
        grilleTest.deplacerCase("gauche");
        grilleTest.deplacerCase("gauche");
        grilleTest.deplacerCase("bas");
        grilleTest.deplacerCase("undo");
        grilleTest.deplacerCase("undo");
        grilleTest.deplacerCase("undo");
        grilleTest.deplacerCase("undo");

        int[] grilleObtenue = grilleTest.transformerGrilleArray1D();
        int[] grilleAttendue = {1, 8, 2, 0, 4, 3, 7, 6, 5};

        assertArrayEquals(grilleAttendue, grilleObtenue);
    }
}
