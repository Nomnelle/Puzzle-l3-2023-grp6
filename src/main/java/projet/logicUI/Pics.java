package projet.logicUI;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import projet.controller.PuzzleController;

public class Pics {
    Image imageDefault;
    final int ORIGINALSIZE = 340; //size of picture
    final int dividedSize; //size of a part of the picture

    /**
     * Pics controller
     * Set the divided size
     * Initialize the picture
     *
     * @param rowAndColumn number of cases in X (or Y (same thing))
     */
    public Pics(int rowAndColumn) {
        this.dividedSize = ORIGINALSIZE/rowAndColumn;
        initializeDefaultPicture();
    }

    /**
     * divides the photo thanks to the size of the grid and according to its location in the grid
     *
     * @param y placement of Y axe
     * @param x placement of X axe
     * @return a divided part of the picture
     */
    public Image cutedPic(int y, int x) {
        PixelReader reader = imageDefault.getPixelReader();
        if (imageDefault.getHeight()>ORIGINALSIZE || imageDefault.getWidth()>ORIGINALSIZE){
            WritableImage resized = new WritableImage(reader, 0, 0, ORIGINALSIZE, ORIGINALSIZE); //image size reduction
            return new WritableImage(resized.getPixelReader(), dividedSize*y,dividedSize*x, dividedSize, dividedSize); //divide
        } else if (imageDefault.getHeight()<ORIGINALSIZE || imageDefault.getWidth()<ORIGINALSIZE){
            System.err.println("too short");
            return null;
        }
        return new WritableImage(reader, dividedSize*y,dividedSize*x, dividedSize, dividedSize); //divide
    }

    /**
     * Initialize the picture thanks to the previous choice of the user
     */
    private void initializeDefaultPicture() {
        imageDefault = new Image("/doc-files/"+ PuzzleController.image +".jpg");
    }
}