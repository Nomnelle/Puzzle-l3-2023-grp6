package projet.logicUI;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ShiftCases extends java.lang.Thread {
    final Node node;
    final int direction;
    final double cellSize;
    final char xy;
    final double SPEED = 2.5;

    /**
     * Constructor of shift case thread
     *
     * @param node node
     * @param direction (int) plus or minus
     * @param gridPane the gridPane
     * @param xy (char) axe X or Y
     */
    public ShiftCases(Node node, int direction, GridPane gridPane, char xy){
        setDaemon(true);

        this.node = node;
        this.direction = direction;
        this.cellSize = gridPane.getHeight()/gridPane.getRowCount();
        this.xy = xy;
    }
    @Override
    public void run(){
        //Divide the celle size by a number and multiply the movement by the same number
        for (int i=0; i<cellSize/SPEED; i++){ //example : 100/2.5 = 40
            final double way = (direction*i)*SPEED; //example 1 * 40 * 2.5 = 100 = cell size (target)
            Platform.runLater(() -> {
                if (xy=='x'){node.setTranslateX(way);} else {node.setTranslateY(way);} //move the node
            });
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        Platform.runLater(() -> {
            //in case the calculations are incorrect, we place the node in the right place at the end
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