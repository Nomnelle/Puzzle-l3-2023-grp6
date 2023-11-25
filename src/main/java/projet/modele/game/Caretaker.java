package projet.modele.game;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * The Caretaker class is responsible for managing the history of states (Mementos) of an object.
 * It stores and retrieves Mementos using a LinkedList.
 */
public class Caretaker implements Serializable {
    private LinkedList<Object> sauvegardeEtats;  // List to store the Mementos

    /**
     * Constructs a Caretaker object with an empty list of Mementos.
     */
    public Caretaker(){
        sauvegardeEtats = new LinkedList<>();
    }


    /**
     * Checks if the list of Mementos is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return sauvegardeEtats.isEmpty();
    }

    /**
     * Saves a Memento by pushing it onto the list.
     *
     * @param memento The Memento to be saved.
     */
    public void saveGrille(Object memento){
        sauvegardeEtats.push(memento);
    }

    /**
     * Retrieves the latest saved Memento and removes it from the list.
     *
     * @return The latest saved Memento.
     */
    public Object retrieveMemento(){
        return sauvegardeEtats.pop();
    }

    /**
     * Retrieves a Memento at a specific index without removing it from the list.
     *
     * @param index The index of the desired Memento.
     * @return The Memento at the specified index.
     */
    public Object retrieveMemento(int index){
        return sauvegardeEtats.get(index);
    }

}
