package logic;

import javafx.scene.Node;

/*
Logique du bouton Undo
 */

public class UndoLogic {
    private static int COUNT;

    /*
    Usable methods
     */

    public void setCountPlus(Node node){ //Rajoute 1 à count et vérifie le nombre de coups
        grisState(increment(), node);
    }
    public void setCountReset(Node node){ //Reset le bouton undo (NOUVELLE PARTIE)
        grisState(reset(), node);
    }

    /*
    Private methods
     */

    private int increment(){
        return COUNT +=1;
    }
    private int reset(){
        return COUNT = 0;
    }
    private void grisState(int count, Node node){
        if (count>=4){
            node.setDisable(true);
            COUNT = 0;
        } else {
            if (node.isDisable()){
                node.setDisable(false);
            }
        }
    }
}
