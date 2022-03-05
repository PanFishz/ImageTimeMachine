package ImageTimeMachine.model.editingTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleImage extends Transformer {
    private final FilterType filterType;
    private int targetWidth = 450;
    private int targetHeight = 450;
    private int imageWidth;
    private int imageHeight;
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private int newWidth;
    private int newHeight;
    double factor;

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
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        BufferedImage img = makeNewImage(targetWidth, targetHeight);

        setFactor();
        setNewWidHgt();
        setXYCoordinates();
        Graphics g = img.createGraphics();
        g.drawImage(image, xCoordinate, yCoordinate, (int) (imageWidth * factor), (int) (imageHeight * factor), null);

        g.dispose();
        return img;
    }

    private void setFactor() {
        if (imageWidth >= imageHeight) {
            factor = (double) targetWidth / (double) imageWidth;
        } else {
            factor = (double) targetHeight / (double) imageHeight;
        }
    }

    private void setNewWidHgt() {
        newWidth = (int) (imageWidth * factor);
        newHeight = (int) (imageHeight * factor);
    }

    private void setXYCoordinates() {
        if (newWidth < targetWidth) {
            xCoordinate = (targetWidth - newWidth) / 2;
        }
        if (newHeight < targetHeight) {
            yCoordinate = (targetHeight - newHeight) / 2;
        }
    }
}
