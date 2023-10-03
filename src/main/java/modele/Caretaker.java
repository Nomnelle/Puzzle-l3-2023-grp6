package modele;
import java.util.LinkedList;

public class Caretaker {
    private LinkedList<Memento> sauvegardeEtats;

    public Caretaker(){
        sauvegardeEtats = new LinkedList<>();
    }

    public boolean isEmpty(){
        return sauvegardeEtats.isEmpty();
    }

    public void saveGrille(Memento memento){
        if(sauvegardeEtats.size()>5){
            sauvegardeEtats.removeFirst();
        }
        sauvegardeEtats.push(memento);
    }

    public Memento retrieveMemento(){
        return sauvegardeEtats.pop();
    }

    public Memento retrieveMemento(int index){
        return sauvegardeEtats.get(index);
    }

}
