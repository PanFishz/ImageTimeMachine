package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class ImageTitleTrivia {
    private final BufferedImage image;
    private final String title;
    private String trivia;

    public ImageTitleTrivia(BufferedImage image, String title) {
        this.image = image;
        this.title = title;
        this.trivia = null;
    }

    public void setTrivia(String translation) {
        this.trivia = translation;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTrivia() {
        return trivia;
    }
}
