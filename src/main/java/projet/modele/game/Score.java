package projet.modele.game;

public class Score {
    private final int time;
    private final int movements;
    public Score(int time, int movements){
        this.time = time;
        this.movements = movements;
    }

    private int scoreCalcul(){
        if (time<=1){return 0;}
        return time*movements; //Inexacte
    }
}
