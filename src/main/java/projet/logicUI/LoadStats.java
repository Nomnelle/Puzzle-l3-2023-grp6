package projet.logicUI;

import projet.modele.game.BDD;

import java.util.ArrayList;
/**
 * Allows you to load the database and the game simultaneously
 */
public class LoadStats extends Thread{
    private ArrayList<String> arrayList;

    /**
     * Get the score of the player
     */
    @Override
    public void run(){
        final BDD bdd = new BDD();
        String query = "SELECT Joueur.nom_Joueur, Partie.taille_Puzzle, Partie.score " +
                "FROM Partie " +
                "JOIN Joueur ON Partie.idPartie = Joueur.idPartie "+
                "WHERE Partie.score IS NOT NULL "+
                "ORDER BY Partie.score DESC "+
                "LIMIT 15";
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
}
