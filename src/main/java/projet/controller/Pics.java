package projet.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Pics {
    Image imageDefault;
    public Pics() {
        initializeDefaultPicture();
    }

    public Image cutedPic(int size, int i) {

        PixelReader reader = imageDefault.getPixelReader();

        System.out.println((int) ((imageDefault.getWidth()/size)+(imageDefault.getWidth()/size)*i));

        return new WritableImage(reader, (int) (imageDefault.getWidth()/size*i)
                ,(int) (imageDefault.getHeight()/size*i)
                ,(int) imageDefault.getWidth()/size, (int) imageDefault.getHeight()/size);
    }

    private void initializeDefaultPicture() {
        imageDefault = new Image("/doc-files/image2.jpg");
    }
}
