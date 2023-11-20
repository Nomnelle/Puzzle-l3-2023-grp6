package projet.logicUI;

import projet.modele.game.BDD;

public class Player {
    private final String time;
    private final int movements;
    private String username = System.getProperty("user.name");
    private final int size;
    private double score;

    public Player(String time, int movements, int size){
        this.time = time;
        this.movements = movements;
        this.size = size;
        setScore();
    }

    public void victory(){
        setScore();
        if (score!=0){
            bddADD();
        } else {
            System.err.println("Cheater");
        }
    }
    private void bddADD(){
        BDD bdd = new BDD();
        bdd.addData(username, movements, time, size);
        /*
        Ajouter le score à la fin (évite de calculer le score de 20 personnes à chaque fois qu'on ouvre le jeu)
        -> perd place gain temps
        */
    }

    private void setScore(){
        int timeInt = Integer.parseInt(time);
        if (timeInt==0 || movements==0){score = 0;} //Security
        else score = 1000 * (1000 - ((double) timeInt / (movements*2)));
    }
}
