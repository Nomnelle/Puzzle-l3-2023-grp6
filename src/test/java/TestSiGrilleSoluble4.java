import projet.modele.game.Case;
import projet.modele.game.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSiGrilleSoluble4 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = new Grille(4);
        grilleTest.getGrille().add(new Case(0,0,"M",13,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"J",10,grilleTest));
        grilleTest.getGrille().add(new Case(0,3,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"L",12,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(1,3,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"E",5,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"x",0,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"I",9,grilleTest));
        grilleTest.getGrille().add(new Case(2,3,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(3,0,"0",15,grilleTest));
        grilleTest.getGrille().add(new Case(3,1,"N",14,grilleTest));
        grilleTest.getGrille().add(new Case(3,2,"K",11,grilleTest));
        grilleTest.getGrille().add(new Case(3,3,"G",7,grilleTest));
    }

    @Test
    public void testGrille4Soluble(){
        boolean soluble4 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 41;
        assert grilleTest.compterColonne() == 2;
        assert soluble4;
    }
}
