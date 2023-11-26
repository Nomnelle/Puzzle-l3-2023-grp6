import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Case;
import projet.modele.game.Grille;
import projet.modele.ia.ParcoursAEtoile;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.*;


public class TestParcoursAEtoileTest {
    private Grille g;

    @BeforeEach
    public void init() {
        g = new Grille(3);
        g.remplirGrille();
        g.getGrille().add(new Case(0, 0, 8, g));
        g.getGrille().add(new Case(0, 1, 1, g));
        g.getGrille().add(new Case(0, 2, 3, g));
        g.getGrille().add(new Case(1, 0, 4, g));
        g.getGrille().add(new Case(1, 1, 0, g));
        g.getGrille().add(new Case(1, 2, 2, g));
        g.getGrille().add(new Case(2, 0, 7, g));
        g.getGrille().add(new Case(2, 1, 6, g));
        g.getGrille().add(new Case(2, 2, 5, g));

    }

    @Test
    public void testParcoursAEtoile() throws CloneNotSupportedException {
        Grille grille = new Grille(3);

        grille.remplirGrille();

        ParcoursAEtoile parcoursAEtoile = new ParcoursAEtoile(grille);
        String resultat = parcoursAEtoile.next();

        Assertions.assertNotNull(resultat);
    }


}

