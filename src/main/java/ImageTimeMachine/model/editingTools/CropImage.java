package ImageTimeMachine.model.editingTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CropImage extends Transformer {
    private final FilterType filterType;
    private String option = "center";
    private int imageWidth;
    private int imageHeight;
    private int newWidth;
    private int newHeight;
    private int xCoordinate, yCoordinate;

    public CropImage() {
        filterType = FilterType.CROPPING;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public FilterType getFilterType() {
        return filterType;
    }

    @Override
    public BufferedImage processEditing(BufferedImage image) {
        setNewWidHgt(image);
        setNewCoordinates();

        BufferedImage img = image.getSubimage(xCoordinate, yCoordinate,
                newWidth, newHeight);//fill in the corners of the desired crop location here
        BufferedImage copyOfImage = makeNewImage(newWidth, newHeight);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }

    private void setNewWidHgt(BufferedImage image) {
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        newWidth = (int) (imageWidth * 0.8);
        newHeight = (int) (imageHeight * 0.8);
    }

    private void setNewCoordinates() {
        switch (option) {
            case "North":
                xCoordinate = (imageWidth - newWidth) / 2;
                yCoordinate = 0;
                break;
            case "East":
                xCoordinate = (imageWidth - newWidth);
                yCoordinate = (imageHeight - newHeight) / 2;
                break;
            case "West":
                xCoordinate = 0;
                yCoordinate = (imageHeight - newHeight) / 2;
                break;
            case "South":
                xCoordinate = (imageWidth - newWidth) / 2;
                yCoordinate = (imageHeight - newHeight);
                break;
            case "Northeast":
                xCoordinate = (imageWidth - newWidth);
                yCoordinate = 0;
                break;
            case "Northwest":
                xCoordinate = 0;
                yCoordinate = 0;
                break;
            case "Southeast":
                xCoordinate = (imageWidth - newWidth);
                yCoordinate = (imageHeight - newHeight);
                break;
            case "Southwest":
                xCoordinate = 0;
                yCoordinate = (imageHeight - newHeight);
                break;
            case "Center":
            default:
                xCoordinate = (imageWidth - newWidth) / 2;
                yCoordinate = (imageHeight - newHeight) / 2;
                break;
        }
    }
}
