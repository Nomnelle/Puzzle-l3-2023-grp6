import modele.Case;
import modele.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConditionsVictoire {

    Grille grilleTest1 = new Grille(3);
    Grille grilleTest2 = new Grille(3);
    Grille grilleTest3 = new Grille(4);
    Grille grilleTest4 = new Grille(4);

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

        grilleTest2.getGrille().add(new Case(0,0,"A",1,grilleTest2));
        grilleTest2.getGrille().add(new Case(0,1,"B",2,grilleTest2));
        grilleTest2.getGrille().add(new Case(0,2,"C",3,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,0,"D",4,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,1,"E",5,grilleTest2));
        grilleTest2.getGrille().add(new Case(1,2,"F",6,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,0,"G",7,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,1,"H",8,grilleTest2));
        grilleTest2.getGrille().add(new Case(2,2,"x",0,grilleTest2));

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

        grilleTest4.getGrille().add(new Case(0,0,"A",1,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,1,"B",2,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,2,"C",3,grilleTest4));
        grilleTest4.getGrille().add(new Case(0,3,"D",4,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,0,"E",5,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,1,"F",6,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,2,"G",7,grilleTest4));
        grilleTest4.getGrille().add(new Case(1,3,"H",8,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,0,"I",9,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,1,"J",10,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,2,"K",11,grilleTest4));
        grilleTest4.getGrille().add(new Case(2,3,"L",12,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,0,"M",13,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,1,"N",14,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,2,"0",15,grilleTest4));
        grilleTest4.getGrille().add(new Case(3,3,"x",0,grilleTest4));

    }

    @Test
    public void testerVictoireGrille1(){
        boolean victoire1 = grilleTest1.verifierVictoire();

        assert !victoire1;
    }

    @Test
    public void testerVictoireGrille2(){
        boolean victoire2 = grilleTest2.verifierVictoire();

        assert victoire2;
    }

    @Test
    public void testerVictoireGrille3(){
        boolean victoire3 = grilleTest3.verifierVictoire();

        assert !victoire3;
    }

    @Test
    public void testerVictoireGrille4(){
        boolean victoire4 = grilleTest4.verifierVictoire();

        assert victoire4;
    }

}

