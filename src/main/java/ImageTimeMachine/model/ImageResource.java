package ImageTimeMachine.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ImageTimeMachine.EditingControlPanel.scaleImage;

public class ImageResource {
    public BufferedImage blankImage() {
        return fileToImage("blank_image.png");
    }
    public BufferedImage homeImage() {
        return fileToImage("cats2.png", 900, 450);
    }
    public  BufferedImage tutorialImage() {
        return fileToImage("tutorial.png");
    }
    public BufferedImage exampleImage() {
        return fileToImage("filterExamples.png");
    }
    public BufferedImage fileToImage(String pathName, int width, int height) {
        // read file into a BufferedImage
        return scaleImage(fileToImage(pathName), width, height);
    }

    public BufferedImage fileToImage(String pathName) {
        // read file into a BufferedImage
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(pathName));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return image;
    }
}
