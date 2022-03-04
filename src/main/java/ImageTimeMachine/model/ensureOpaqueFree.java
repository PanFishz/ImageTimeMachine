package ImageTimeMachine.model;


import java.awt.image.BufferedImage;

public class ensureOpaqueFree {

    public BufferedImage opaqueFree(BufferedImage image) {
        if (image.getTransparency() == BufferedImage.OPAQUE)
            return image;
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        newImage.setRGB(0, 0, width, height, pixels, 0, width);
        return newImage;
    }
}