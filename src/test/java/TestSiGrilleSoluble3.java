import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;

public class TestSiGrilleSoluble3 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = Grille.getInstance(4);
        grilleTest.getGrille().add(new Case(0,0,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(0,3,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"E",5,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(1,3,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"I",9,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"J",10,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"K",11,grilleTest));
        grilleTest.getGrille().add(new Case(2,3,"L",12,grilleTest));
        grilleTest.getGrille().add(new Case(3,0,"M",13,grilleTest));
        grilleTest.getGrille().add(new Case(3,1,"0",15,grilleTest));
        grilleTest.getGrille().add(new Case(3,2,"N",14,grilleTest));
        grilleTest.getGrille().add(new Case(3,3,"x",0,grilleTest));
    }

    @Test
    public void testGrille3Soluble(){
        boolean soluble3 = grilleTest.testerSiGrilleSoluble();
        assert grilleTest.compterInversions() == 1;
        assert grilleTest.compterColonne() == 1;
        assert !soluble3;
    }
}
