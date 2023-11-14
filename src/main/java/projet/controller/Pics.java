package projet.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Pics {
    Image imageDefault;
    int size;
    int dividedHeight; int dividedWidth;
    public Pics(int size) {
        this.size = size;
        initializeDefaultPicture();
        dividedHeight = (int) imageDefault.getHeight() / size;
        dividedWidth = (int) imageDefault.getWidth() / size;
    }
    public Image cutedPic(int i, int j) {
        PixelReader reader = imageDefault.getPixelReader();

        int dividedHeight = (int) imageDefault.getHeight() / size;
        int dividedWidth = (int) imageDefault.getWidth() / size;

        System.out.println("site : "+ size);
        System.out.println("width / size *i : " + dividedHeight *i);
        System.out.println("width / size * j : " + dividedWidth * j);
        System.out.println("height / size : " + dividedHeight);
        System.out.println("width / size : " + dividedWidth);

        if (i==size || j==size-1){
            return null;
        }
        return new WritableImage(reader, dividedWidth*i,dividedHeight*j, dividedWidth, dividedHeight);
    }
    private void initializeDefaultPicture() {
        imageDefault = new Image("/doc-files/image2.jpg");
    }
}
