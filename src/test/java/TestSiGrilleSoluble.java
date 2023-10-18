import projet.modele.Case;
import projet.modele.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSiGrilleSoluble {

    Grille grilleTest;
    int iteration = 0;

    @BeforeEach
    public void initGrille(){
                grilleTest = Grille.getInstance(3);
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
    public void testGrille1Soluble(){
        boolean soluble1 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 10;
        assert soluble1;
    }
}
