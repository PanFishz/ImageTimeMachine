package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class ImageTitlePair {
    private BufferedImage image;
    private String title;
    private String translation;

    public ImageTitlePair(BufferedImage image, String title) {
        this.image = image;
        this.title = title;
        this.translation = null;
    }

    public void setTrivia(String translation) {
        this.translation = translation;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTranslation() {
        return translation;
    }
}
