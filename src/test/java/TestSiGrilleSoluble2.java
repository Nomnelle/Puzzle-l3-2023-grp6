import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;

public class TestSiGrilleSoluble2 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = new Grille(3);
        grilleTest.getGrille().add(new Case(0,0,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"E",5,grilleTest));
    }

    @Test
    public void testGrille2Soluble(){
        boolean soluble2 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 11;
        assert !soluble2;
    }

}
