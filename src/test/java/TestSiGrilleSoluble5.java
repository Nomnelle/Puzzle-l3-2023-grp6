import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;

public class TestSiGrilleSoluble5 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = new Grille(4);
        grilleTest.getGrille().add(new Case(0,0,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"I",9,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(0,3,"0",15,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"N",14,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"K",11,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(1,3,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"M",13,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"J",10,grilleTest));
        grilleTest.getGrille().add(new Case(2,3,"L",12,grilleTest));
        grilleTest.getGrille().add(new Case(3,0,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(3,1,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(3,2,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(3,3,"E",5,grilleTest));
    }

    @Test
    public void testGrille5Soluble(){
        boolean soluble5 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 56;
        assert grilleTest.compterColonne() == 2;
        assert !soluble5;
    }
}
