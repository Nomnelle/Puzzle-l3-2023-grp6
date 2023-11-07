import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.Grille;

import java.io.*;

public class TestSerialization {

    Grille g;
    int longueur;

    @BeforeEach
    public void initTest() {
        longueur = 2;
        g = Grille.getInstance(longueur);
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(Grille.getInstance(longueur));
        oos.close();

        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(is);
        Grille deserialized = (Grille) ois.readObject();
        assert deserialized==g;
    }
}
