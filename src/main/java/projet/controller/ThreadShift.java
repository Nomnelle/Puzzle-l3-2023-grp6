package projet.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ThreadShift extends java.lang.Thread {
    Node node;
    double goal;
    int where;
    public ThreadShift(Node node, int rowAndCol, int where){
        this.node = node;
        this.goal = 5.0;//(344/rowAndCol)/2;
        this.where = where;
    }

    Task<Void>task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {


            final int[] i = {0};
            while (i[0]<goal){
                System.out.println(i + " ---> " + goal);

                i[0] = i[0] + 1;
                Platform.runLater(() -> node.setTranslateY(node.getTranslateY() - i[0]));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            GridPane.setRowIndex(node, GridPane.getRowIndex(node) + (where));

            return null;
        }
    };
}
