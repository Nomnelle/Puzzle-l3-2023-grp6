package projet.logicUI;

import projet.modele.game.BDD;
import projet.modele.game.Chrono;

/**
 * Manage players victory, score, and send them to the database
 */
public class Player extends Thread {
    private final Chrono chrono;
    private final int movements;
    private final int size;
    private final String username;
    private double score;

    /**
     * Create and set a new player with his game stats
     * @param chrono A chronometer
     * @param movements movements count (int)
     * @param size size (int)
     */
    public Player(Chrono chrono, int movements, int size){
        this.chrono = chrono;
        this.movements = movements;
        this.size = size;
        this.username = String.valueOf(setUsername());
    }
    /**
     * Add the score in database
     * Adding the score to the database avoids calculating the score of 20 people each time you open the game, All we have to do is get it back
     */
    @Override
    public void run(){
        final BDD bdd = new BDD();
        bdd.ajouterDonnees(username, movements, chrono.toString(), size, score);
    }
    /**
     * Set the username
     * Remove excess or add the excess, it depends on the number of characters in the username
     * This makes it possible to homogenize the nicknames
     */
    private StringBuilder setUsername(){
        StringBuilder username = new StringBuilder(System.getProperty("user.name").toUpperCase());
        final int length = 6;
        if (username.length()<length){
            username.append(" ".repeat(length - username.length()));
            return username;
        } else if (username.length()>length){
            username.delete(length, username.length());
            return username;
        } else {
            return username;
        }
    }
    /**
     * Set the score according to time and number of moves
     */
    public void setScore(){
        int h = chrono.getHeure();
        int m = chrono.getMinute();
        int s = chrono.getSeconde();

        double time = h*3600 + m*60 + s;

        if (movements==0){score = 0;} //Security
        else score = (double) (500 /(movements)) + (500/(time+1));
        if (size==4) score = score/10;
        if (size==9) score = score*10;
        if (size==16) score = score*100;
    }
}