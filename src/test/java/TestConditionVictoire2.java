import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;

public class TestConditionVictoire2 {

    Grille grilleTest;

    @BeforeEach
    public void initGrille(){
        grilleTest = Grille.getInstance(3);
        grilleTest.getGrille().add(new Case(0,0,"A",1,grilleTest));
        grilleTest.getGrille().add(new Case(0,1,"B",2,grilleTest));
        grilleTest.getGrille().add(new Case(0,2,"C",3,grilleTest));
        grilleTest.getGrille().add(new Case(1,0,"D",4,grilleTest));
        grilleTest.getGrille().add(new Case(1,1,"E",5,grilleTest));
        grilleTest.getGrille().add(new Case(1,2,"F",6,grilleTest));
        grilleTest.getGrille().add(new Case(2,0,"G",7,grilleTest));
        grilleTest.getGrille().add(new Case(2,1,"H",8,grilleTest));
        grilleTest.getGrille().add(new Case(2,2,"x",0,grilleTest));
    }

    @Test
    public void testerVictoireGrille2(){
        boolean victoire2 = grilleTest.verifierVictoire();

        assert victoire2;
    }

}

