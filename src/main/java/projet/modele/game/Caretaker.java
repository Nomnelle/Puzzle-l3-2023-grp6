package projet.modele.game;
import java.io.Serializable;
import java.util.LinkedList;

public class Caretaker implements Serializable {
    private LinkedList<Object> sauvegardeEtats;

    public Caretaker(){
        sauvegardeEtats = new LinkedList<>();
    }

    public boolean isEmpty(){
        return sauvegardeEtats.isEmpty();
    }

    public void saveGrille(Object memento){
        if(sauvegardeEtats.size()>5){
            sauvegardeEtats.removeFirst();
        }
        sauvegardeEtats.push(memento);
    }

    public Object retrieveMemento(){
        return sauvegardeEtats.pop();
    }

    public Object retrieveMemento(int index){
        return sauvegardeEtats.get(index);
    }

}
