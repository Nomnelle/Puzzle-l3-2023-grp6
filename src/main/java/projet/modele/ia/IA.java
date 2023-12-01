package projet.modele.ia;

/**
 * Strategy design pattern
 * The IA interface represents the behavior of artificial intelligence for puzzle-solving.
 * It declares the DEPLACEMENT enum for possible movements and the next() method to retrieve the next movement.
 */
public interface IA {

    /**
     * The DEPLACEMENT enum represents possible movements for the intelligent agent.
     */
    public enum DEPLACEMENT{
        haut, bas, droite, gauche
    }

    /**
     * Retrieves the next movement determined by the intelligent agent.
     *
     * @return A string representing the next movement.
     */
    public String next();
}
