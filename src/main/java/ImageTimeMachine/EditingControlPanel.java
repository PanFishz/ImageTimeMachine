package ImageTimeMachine;

import ImageTimeMachine.model.*;
import ImageTimeMachine.model.editingTools.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class EditingControlPanel {
    private static final ScaleImage scaleImageTool = new ScaleImage();
    private static final CropImage cropImageTool = new CropImage();
    private static final GrayScale grayFilter = new GrayScale();
    private static GrayScaleAndBlue grayBlueFilter = new GrayScaleAndBlue();
    private static Blue blueFilter = new Blue();
    private static Negative negativeFilter = new Negative();
    private static Neon neonFilter = new Neon();
    private static NeonMore neonMoreFilter = new NeonMore();
    private static Red redFilter = new Red();
    private static final Sepia sepiaFilter = new Sepia();
    private static final ensureOpaqueFree opaqueFree= new ensureOpaqueFree();
    private static final ArrayList<Transformer> toollist = new ArrayList<>();

    public static void makeList() {
        toollist.add(grayBlueFilter);
        toollist.add(grayFilter);
        toollist.add(blueFilter);
        toollist.add(negativeFilter);
        toollist.add(neonFilter);
        toollist.add(redFilter);
        toollist.add(sepiaFilter);
        toollist.add(neonMoreFilter);
        Sepia sepiaFilter2 = new Sepia();
        sepiaFilter2.setSepiaIntensity(90);
        toollist.add(sepiaFilter2);
    }
    public static BufferedImage opaqueFree(BufferedImage sourceImage) {
        return opaqueFree.opaqueFree(sourceImage);
    }

    public static BufferedImage scaleImage(BufferedImage sourceImage, int targetWidth, int targetHeight) {
        scaleImageTool.setTargetWidth(targetWidth);
        scaleImageTool.setTargetHeight(targetHeight);
        return scaleImageTool.processEditing(sourceImage);
    }

    public static BufferedImage cropImage(String anchor, BufferedImage sourceImage) {
        cropImageTool.setOption(anchor);
        return cropImageTool.processEditing(sourceImage);
    }

    public static BufferedImage toGrayScale(BufferedImage sourceImage) {
        return grayFilter.processEditing(sourceImage);
    }

    public static BufferedImage toSepia(BufferedImage sourceImage, int sepiaIntensity) {
        sepiaFilter.setSepiaIntensity(sepiaIntensity);
        return sepiaFilter.processEditing(sourceImage);
    }

    public static BufferedImage toGrayBlue(BufferedImage sourceImage) {
        grayBlueFilter = new GrayScaleAndBlue();
        return grayBlueFilter.processEditing(sourceImage);
    }

    public static BufferedImage toBlue(BufferedImage sourceImage) {
        blueFilter = new Blue();
        return blueFilter.processEditing(sourceImage);
    }

    public static BufferedImage toRed(BufferedImage sourceImage) {
        redFilter = new Red();
        return redFilter.processEditing(sourceImage);
    }

    public static BufferedImage toNegative(BufferedImage sourceImage) {
        negativeFilter = new Negative();
        return negativeFilter.processEditing(sourceImage);
    }

    public static BufferedImage toNeon(BufferedImage sourceImage) {
        neonFilter = new Neon();
        return neonFilter.processEditing(sourceImage);
    }

    public static BufferedImage toNeonMore(BufferedImage sourceImage) {
        neonMoreFilter = new NeonMore();
        return neonMoreFilter.processEditing(sourceImage);
    }

    static BufferedImage surpriseMe(BufferedImage image) {
        Random rand = new Random();
        int upperbound = 9;
        int randomNumber = rand.nextInt(2);
        int randomFilter;
        for (int i = 0; i < randomNumber + 1; i ++) {
            randomFilter = rand.nextInt(upperbound);
            switch (randomFilter) {
                case 0:
                    image = toGrayScale(image);
                    break;
                case 1:
                    image = toSepia(image, 10);
                    break;
                case 2:
                    image = toNeon(image);
                    break;
                case 3:
                    image =toNeonMore(image);
                    break;
                case 4:
                    image = toRed(image);
                    break;
                case 5:
                    image = toNegative(image);
                    break;
                case 6:
                    image = toSepia(image, 90);
                    break;
                case 7:
                    image = toGrayBlue(image);
                    break;
                case 8:
                    image = toBlue(image);
                case 9:
                    makeList();
                    image = toollist.get(randomFilter).processEditing(image);
                default:
                    break;
            }
        }
        return image;
    }

}
