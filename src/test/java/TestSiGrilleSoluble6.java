import projet.modele.game.Case;
import projet.modele.game.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSiGrilleSoluble6 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = new Grille(4);
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
    public void testGrille6Soluble(){
        boolean soluble6 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 62;
        assert grilleTest.compterColonne() == 3;
        assert soluble6;
    }
}
