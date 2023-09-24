import modele.Case;
import modele.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSiGrilleSoluble {

    Grille grilleTest1 = new Grille(3);
    Grille grilleTest2 = new Grille(3);
    Grille grilleTest3 = new Grille(4);
    Grille grilleTest4 = new Grille(4);
    Grille grilleTest5 = new Grille(4);
    Grille grilleTest6 = new Grille(4);

    @BeforeEach
    public void initGrille(){
        grilleTest1.getGrille().add(new Case(0,0,"A",1,grilleTest1));
        grilleTest1.getGrille().add(new Case(0,1,"H",8,grilleTest1));
        grilleTest1.getGrille().add(new Case(0,2,"B",2,grilleTest1));
        grilleTest1.getGrille().add(new Case(1,0,"x",0,grilleTest1));
        grilleTest1.getGrille().add(new Case(1,1,"D",4,grilleTest1));
        grilleTest1.getGrille().add(new Case(1,2,"C",3,grilleTest1));
        grilleTest1.getGrille().add(new Case(2,0,"G",7,grilleTest1));
        grilleTest1.getGrille().add(new Case(2,1,"F",6,grilleTest1));
        grilleTest1.getGrille().add(new Case(2,2,"E",5,grilleTest1));

        grilleTest2.getGrille().add(new Case(0,0,"H",8,grilleTest2));
        grilleTest2.getGrille().add(new Case(0,1,"A",1,grilleTest2));
        grilleTest2.getGrille().add(new Case(0,2,"B",2,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,0,"x",0,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,1,"D",4,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,2,"C",3,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,0,"G",7,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,1,"F",6,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,2,"E",5,grilleTest2));

        grilleTest3.getGrille().add(new Case(0,0,"A",1,grilleTest3));
        grilleTest3.getGrille().add(new Case(0,1,"B",2,grilleTest3));
        grilleTest3.getGrille().add(new Case(0,2,"C",3,grilleTest3));
        grilleTest3.getGrille().add(new Case(0,3,"D",4,grilleTest3));
        grilleTest3.getGrille().add(new Case(1,0,"E",5,grilleTest3));
        grilleTest3.getGrille().add(new Case(1,1,"F",6,grilleTest3));
        grilleTest3.getGrille().add(new Case(1,2,"G",7,grilleTest3));
        grilleTest3.getGrille().add(new Case(1,3,"H",8,grilleTest3));
        grilleTest3.getGrille().add(new Case(2,0,"I",9,grilleTest3));
        grilleTest3.getGrille().add(new Case(2,1,"J",10,grilleTest3));
        grilleTest3.getGrille().add(new Case(2,2,"K",11,grilleTest3));
        grilleTest3.getGrille().add(new Case(2,3,"L",12,grilleTest3));
        grilleTest3.getGrille().add(new Case(3,0,"M",13,grilleTest3));
        grilleTest3.getGrille().add(new Case(3,1,"0",15,grilleTest3));
        grilleTest3.getGrille().add(new Case(3,2,"N",14,grilleTest3));
        grilleTest3.getGrille().add(new Case(3,3,"x",0,grilleTest3));

        grilleTest4.getGrille().add(new Case(0,0,"M",13,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,1,"B",2,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,2,"J",10,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,3,"C",3,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,0,"A",1,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,1,"L",12,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,2,"H",8,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,3,"D",4,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,0,"E",5,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,1,"x",0,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,2,"I",9,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,3,"F",6,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,0,"0",15,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,1,"N",14,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,2,"K",11,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,3,"G",7,grilleTest4));

        grilleTest5.getGrille().add(new Case(0,0,"C",3,grilleTest5));
        grilleTest5.getGrille().add(new Case(0,1,"I",9,grilleTest5));
        grilleTest5.getGrille().add(new Case(0,2,"A",1,grilleTest5));
        grilleTest5.getGrille().add(new Case(0,3,"0",15,grilleTest5));
        grilleTest5.getGrille().add(new Case(1,0,"N",14,grilleTest5));
        grilleTest5.getGrille().add(new Case(1,1,"K",11,grilleTest5));
        grilleTest5.getGrille().add(new Case(1,2,"D",4,grilleTest5));
        grilleTest5.getGrille().add(new Case(1,3,"F",6,grilleTest5));
        grilleTest5.getGrille().add(new Case(2,0,"M",13,grilleTest5));
        grilleTest5.getGrille().add(new Case(2,1,"x",0,grilleTest5));
        grilleTest5.getGrille().add(new Case(2,2,"J",10,grilleTest5));
        grilleTest5.getGrille().add(new Case(2,3,"L",12,grilleTest5));
        grilleTest5.getGrille().add(new Case(3,0,"B",2,grilleTest5));
        grilleTest5.getGrille().add(new Case(3,1,"G",7,grilleTest5));
        grilleTest5.getGrille().add(new Case(3,2,"H",8,grilleTest5));
        grilleTest5.getGrille().add(new Case(3,3,"E",5,grilleTest5));

        grilleTest6.getGrille().add(new Case(0,0,"F",6,grilleTest6));
        grilleTest6.getGrille().add(new Case(0,1,"M",13,grilleTest6));
        grilleTest6.getGrille().add(new Case(0,2,"G",7,grilleTest6));
        grilleTest6.getGrille().add(new Case(0,3,"J",10,grilleTest6));
        grilleTest6.getGrille().add(new Case(1,0,"H",8,grilleTest6));
        grilleTest6.getGrille().add(new Case(1,1,"I",9,grilleTest6));
        grilleTest6.getGrille().add(new Case(1,2,"K",11,grilleTest6));
        grilleTest6.getGrille().add(new Case(1,3,"x",0,grilleTest6));
        grilleTest6.getGrille().add(new Case(2,0,"0",15,grilleTest6));
        grilleTest6.getGrille().add(new Case(2,1,"B",2,grilleTest6));
        grilleTest6.getGrille().add(new Case(2,2,"L",12,grilleTest6));
        grilleTest6.getGrille().add(new Case(2,3,"E",5,grilleTest6));
        grilleTest6.getGrille().add(new Case(3,0,"N",14,grilleTest6));
        grilleTest6.getGrille().add(new Case(3,1,"C",3,grilleTest6));
        grilleTest6.getGrille().add(new Case(3,2,"A",1,grilleTest6));
        grilleTest6.getGrille().add(new Case(3,3,"D",4,grilleTest6));
    }

    @Test
    public void testGrille1Soluble(){
        boolean soluble1 = grilleTest1.testerSiGrilleSoluble();
        assert grilleTest1.compterInversions() == 10;
        assert soluble1;
    }

    @Test
    public void testGrille2Soluble(){
        boolean soluble2 = grilleTest2.testerSiGrilleSoluble();
        assert grilleTest2.compterInversions() == 11;
        assert !soluble2;
    }

    @Test
    public void testGrille3Soluble(){
        boolean soluble3 = grilleTest3.testerSiGrilleSoluble();
        assert grilleTest3.compterInversions() == 1;
        assert grilleTest3.compterColonne() == 1;
        assert !soluble3;
    }

    @Test
    public void testGrille4Soluble(){
        boolean soluble4 = grilleTest4.testerSiGrilleSoluble();
        assert grilleTest4.compterInversions() == 41;
        assert grilleTest4.compterColonne() == 2;
        assert soluble4;
    }

    @Test
    public void testGrille5Soluble(){
        boolean soluble5 = grilleTest5.testerSiGrilleSoluble();
        assert grilleTest5.compterInversions() == 56;
        assert grilleTest5.compterColonne() == 2;
        assert !soluble5;
    }

    @Test
    public void testGrille6Soluble(){
        boolean soluble6 = grilleTest6.testerSiGrilleSoluble();
        assert grilleTest6.compterInversions() == 62;
        assert grilleTest6.compterColonne() == 3;
        assert soluble6;
    }
}
