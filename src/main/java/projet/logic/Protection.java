package projet.logic;

public class Protection {
    private static boolean firstGame;
    public void setfirstGameFalse(boolean firstGame){ //Rajoute 1 à count et vérifie le nombre de coups
        firstGame = false;
    }
    public boolean state(boolean firstGame){
        return firstGame;
    }




}
