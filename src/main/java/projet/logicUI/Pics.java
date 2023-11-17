package projet.logicUI;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Pics {
    Image imageDefault;
    final int rowAndColumn;
    final int ORIGINALSIZE = 340;
    final int dividedSize;
    public Pics(int rowAndColumn) {
        this.rowAndColumn = rowAndColumn;
        this.dividedSize = 340/rowAndColumn;
        initializeDefaultPicture();
    }
    public Image cutedPic(int i, int j) {
        PixelReader reader = imageDefault.getPixelReader();
        if (imageDefault.getHeight()>ORIGINALSIZE || imageDefault.getWidth()>ORIGINALSIZE){
            WritableImage resized = new WritableImage(reader, 0, 0, ORIGINALSIZE, ORIGINALSIZE);
            return new WritableImage(resized.getPixelReader(), dividedSize*i,dividedSize*j, dividedSize, dividedSize);
        } else if (imageDefault.getHeight()<ORIGINALSIZE || imageDefault.getWidth()<ORIGINALSIZE){
            System.err.println("image trop petite");
            return null;
        }
        return new WritableImage(reader, dividedSize*i,dividedSize*j, dividedSize, dividedSize);
    }
    private void initializeDefaultPicture() {
        imageDefault = new Image("/doc-files/image2.jpg");
    }
}
