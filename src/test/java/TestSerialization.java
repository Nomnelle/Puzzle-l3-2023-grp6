import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.game.Grille;

import java.io.*;

public class TestSerialization {

    Grille g;
    int longueur;

    @BeforeEach
    public void initTest() {
        longueur = 2;
        g = new Grille(2);
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(g);
        oos.close();

        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(is);
        Grille deserialized = (Grille) ois.readObject();
        assert deserialized==g;
    }
}
