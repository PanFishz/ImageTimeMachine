package ImageTimeMachine.model;

import java.awt.*;

public class ImageCanvas extends Canvas {
    private Image img;

    public ImageCanvas(Image img, int width, int height) {
        this.img = img;
        this.setSize(width, height);
    }

    public void paint(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
    }

    public void setImage(Image img) {
        this.img = img;
    }
}