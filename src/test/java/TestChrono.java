import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projet.modele.Case;
import projet.modele.Chrono;
import projet.modele.Grille;

public class TestChrono {

    Chrono c;


    @Test
    public void testChrono1() throws InterruptedException {

        c = new Chrono();

        Thread.sleep(5000);

        c.pauseTime();

        System.out.println(c);
        assert c.getSeconde() == 5;
    }

    @Test
    public void testChrono2() throws InterruptedException {

        c = new Chrono();

        Thread.sleep(1000);

        c.pauseTime();

        Thread.sleep(3000);

        System.out.println(c);
        assert c.getSeconde() == 1;
    }

    @Test
    public void testChrono3() throws InterruptedException {
        c = new Chrono();

        Thread.sleep(60000);

        c.pauseTime();

        System.out.println(c);
        assert c.getMinute()==1;
        assert c.getSeconde()==0;
    }

    @Test
    public void testChrono4() throws InterruptedException {

        c = new Chrono();

        Thread.sleep(1000);

        c.pauseTime();

        Thread.sleep(3000);

        c.goTime();

        Thread.sleep(1000);

        c.pauseTime();

        System.out.println(c);
        assert c.getSeconde() == 2;
    }

}
