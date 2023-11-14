package projet.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Pics {
    Image imageDefault;
    int size;
    int dividedSize;
    public Pics(int size) {
        this.size = size;
        this.dividedSize = 340/size;
        initializeDefaultPicture();
    }
    public Image cutedPic(int i, int j) {
        PixelReader reader = imageDefault.getPixelReader();
        if (imageDefault.getHeight()>340 || imageDefault.getWidth()>340){
            WritableImage resized = new WritableImage(reader, 0, 0, 340, 340);
            return new WritableImage(resized.getPixelReader(), dividedSize*i,dividedSize*j, dividedSize, dividedSize);
        } else if (imageDefault.getHeight()<340 || imageDefault.getWidth()<340){
            System.err.println("image trop petite");
            return null;
        }
        return new WritableImage(reader, dividedSize*i,dividedSize*j, dividedSize, dividedSize);
    }
    private void initializeDefaultPicture() {
        imageDefault = new Image("/doc-files/image1.jpg");
    }
}
