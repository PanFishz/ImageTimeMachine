package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class ImageManager {
    private BufferedImage currentImage;
    private BufferedImage masterImage;
    private final ImageHistory imageHistory;
    private final ImageResource imageResource;

    public ImageManager() {
        currentImage = null;
        masterImage = null;
        imageHistory = new ImageHistory();
        imageResource = new ImageResource();
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public ImageResource getImageResource() {
        return imageResource;
    }

    public ImageHistory getImageHistory() {
        return imageHistory;
    }

    public void setCurrentImage(BufferedImage image) {
        currentImage = image;
    }

    public void setMasterImage(BufferedImage image) {
        masterImage = image;
    }

    public void masterToCurrent() {
        currentImage = masterImage;
    }

    public boolean isCurrentNull() {
        return currentImage == null;
    }

    public void resetStack() {
        imageHistory.resetStack();
    }

    public void resetNumOfEdits() {
        imageHistory.resetEdits();
    }

    public void addToStack(String title) {
        if (title == null) {
            imageHistory.addToStack(currentImage, "The Present");
        } else {
            imageHistory.addToStack(currentImage, title);
            imageHistory.incrementEdits();
        }
    }

    public BufferedImage getHomeImage() {
        return imageResource.homeImage();
    }

    public BufferedImage getBlankImage() {
        return imageResource.blankImage();
    }

    public BufferedImage getTutorialImage() {
        return imageResource.tutorialImage();
    }

    public BufferedImage getExampleImage() {
        return imageResource.exampleImage();
    }

    public boolean hasUnsavedProcess() {
        return imageHistory.getNumbOfEdits() > 0;
    }

    public void processNewImage() {
        currentImage = masterImage;
        resetStack();
        addToStack(null);
        resetNumOfEdits();
    }
}
