package logic;
import javafx.scene.layout.AnchorPane;

public class TopAnchorPaneLogic {

    public void anchorPaneDrag(AnchorPane paneDrag){
        final double[] x = {0};
        final double[] y = {0};

        paneDrag.setOnMousePressed(event -> {
            x[0] = event.getSceneX();
            y[0] = event.getSceneY();
        });

        paneDrag.setOnMouseDragged(event -> {
            double a = event.getScreenX() - x[0];
            double b = event.getScreenY() - y[0];
            paneDrag.getScene().getWindow().setX(a);
            paneDrag.getScene().getWindow().setY(b);
        });
    }
}


