package projet.logicUI;

import projet.modele.game.BDD;
import projet.modele.game.Chrono;

import java.util.ArrayList;

public class Player extends Thread {
    private Chrono chrono;
    private int movements;
    private int size;
    private String username;
    private double score;
    private final BDD bdd = new BDD();
    public ArrayList<String> arrayList;

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
     * Allows you to load the database and the game simultaneously
     */
    public Player(){
        this.start();
    }

    /**
     * Victory method
     */
    public void victory(){
        setScore();
        bddADD();
    }
    /**
     * Get the score of the player
     */
    @Override
    public void run(){
        String query = "SELECT Joueur.nom_Joueur, Partie.taille_Puzzle, Partie.score " +
                            "FROM Partie " +
                            "JOIN Joueur ON Partie.idPartie = Joueur.idPartie "+
                            "WHERE Partie.score IS NOT NULL "+
                            "ORDER BY Partie.score DESC "+
                            "LIMIT 20";
        arrayList = bdd.requeterBDD(query);
    }

    /**
     * @return A list of the best players
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        char oldCharacter = ':';
        char newCharacter = '\t';

        int i = 0;

        for (String user : arrayList){
            i++;
            String updatedString = user.replace(oldCharacter, newCharacter);
            stringBuilder.append("      ").append(i).append("         ").append(newCharacter).append(updatedString).append("\n");
        }
        return stringBuilder.toString();
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
    private void setScore(){
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
    /**
     * Add the score in database
     * Adding the score to the database avoids calculating the score of 20 people each time you open the game, All we have to do is get it back
     */
    private void bddADD(){
        bdd.ajouterDonnees(username, movements, chrono.toString(), size, score);
    }
}