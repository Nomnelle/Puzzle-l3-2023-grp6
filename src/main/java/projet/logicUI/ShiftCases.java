package projet.logicUI;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ShiftCases extends java.lang.Thread {
    final Node node;
    final int direction;
    final double cellSize;
    final char xy;
    public ShiftCases(Node node, int direction, GridPane gridPane, char xy){
        setDaemon(true);

        this.node = node;
        this.direction = direction;
        this.cellSize = gridPane.getHeight()/gridPane.getRowCount();
        this.xy = xy;
    }
    @Override
    public void run(){
        for (int i=0; i<cellSize/2.5; i++){
            final double way = (direction*i)*2.5;
            Platform.runLater(() -> {
                if (xy=='x'){node.setTranslateX(way);} else {node.setTranslateY(way);}
            });
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Platform.runLater(() -> {
            if (xy=='x'){
                GridPane.setColumnIndex(node, GridPane.getColumnIndex(node) + direction);
                node.setTranslateX(0);
            } else {
                GridPane.setRowIndex(node, GridPane.getRowIndex(node) + direction);
                node.setTranslateY(0);
            }
        });
    }
}
