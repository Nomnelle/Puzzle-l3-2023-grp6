package projet.modele.ia;

public interface IA {

    public enum DEPLACEMENT{
        haut, bas, droite, gauche
    }

    public String next();
}
