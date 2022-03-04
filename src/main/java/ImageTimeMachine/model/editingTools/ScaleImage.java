package ImageTimeMachine.model.editingTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleImage extends Transformer {
    private final FilterType filterType;
    private int targetWidth = 450;
    private int targetHeight = 450;

    public ScaleImage() {
        filterType = FilterType.SCALING;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    @Override
    public FilterType getFilterType() {
        return filterType;
    }

    @Override
    public BufferedImage processEditing(BufferedImage image) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int x = 0, y = 0, newWidth, newHeight;
        double factor;
        BufferedImage img = makeNewImage(targetWidth, targetHeight);

        if (imageWidth >= imageHeight) {
            factor =  (double)targetWidth / (double)imageWidth ;
        } else {
            factor = (double)targetHeight /(double)imageHeight ;
        }

        newWidth = (int)(imageWidth * factor);
        newHeight = (int)(imageHeight * factor);
        if (newWidth < targetWidth) {
            x = (targetWidth - newWidth) / 2;
        }
        if (newHeight < targetHeight) {
            y = (targetHeight - newHeight) / 2;
        }
        Graphics g = img.createGraphics();
        g.drawImage(image, x, y, (int)(imageWidth * factor), (int)(imageHeight * factor), null);

        g.dispose();
        return img;
    }


}
