package projet.logicUI;

import projet.modele.game.BDD;
import projet.modele.game.Chrono;

public class Player {
    private final Chrono chrono;
    private final int movements;
    private final String username = System.getProperty("user.name");
    private final int size;
    private double score;

    public Player(Chrono chrono, int movements, int size){
        this.chrono = chrono;
        this.movements = movements;
        this.size = size;
    }

    public void victory(){
        setScore();
        bddADD();
    }
    private void setScore(){
        int h = chrono.getHeure();
        int m = chrono.getMinute();
        int s = chrono.getSeconde();

        double time = h*3600 + m*60 + s;

        if (movements==0){score = 0;} //Security
        else score = (double) (500 /(movements)) + (500/(time+1));
        System.out.println("LE SCORE : " + score);
    }

    /*
        Ajouter le score dans la bdd évite de calculer le score de 20 personnes à chaque fois qu'on ouvre le jeu,
        Il nous reste plus qu'à le récuperer
    */
    private void bddADD(){
        BDD bdd = new BDD();
        bdd.addData(username, movements, chrono.toString(), size, score);
    }
}
