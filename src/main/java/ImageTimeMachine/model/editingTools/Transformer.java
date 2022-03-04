package ImageTimeMachine.model.editingTools;

import java.awt.image.BufferedImage;

public abstract class Transformer {
    abstract FilterType getFilterType();
    public abstract BufferedImage processEditing(BufferedImage image);
    public BufferedImage makeNewImage(int imageWidth, int imageHeight) {
        return  new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    }
}
