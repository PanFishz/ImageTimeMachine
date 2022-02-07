package ImageTimeMachine.model;

import java.awt.*;

public class ImageCanvas extends Canvas {
    private Image img;

    public ImageCanvas(Image img) {
        this.img = img;
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