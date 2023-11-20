import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;
import projet.modele.ia.Etat;

public class TestManhattanDistance {

    Grille g = Grille.getInstance(3);
    Etat e;

    @BeforeEach
    public void init(){
        g.getGrille().add(new Case(0,0,8,g));
        g.getGrille().add(new Case(0,1,1,g));
        g.getGrille().add(new Case(0,2,3,g));
        g.getGrille().add(new Case(1,0,4,g));
        g.getGrille().add(new Case(1,1,0,g));
        g.getGrille().add(new Case(1,2,2,g));
        g.getGrille().add(new Case(2,0,7,g));
        g.getGrille().add(new Case(2,1,6,g));
        g.getGrille().add(new Case(2,2,5,g));

        e = new Etat(g);
    }

    @Test
    public void testCalculerPositionVictoire(){
        int[] test = e.calculerPosVictoire(8);

        assert test[0] == 2;
        assert test[1] == 1;
    }

    @Test
    public void testManhattanDistance(){
        e.calculerManhattanDistance();

        assert e.getHeuristique() == 10;
    }

}
