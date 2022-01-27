package ImageTimeMachine;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class EditingTools {
    public static BufferedImage scale(BufferedImage src, int targetWidth, int targetHeight)
    {
        int imgWidth = src.getWidth();
        int imgHeight = src.getHeight();
        int x = 0, y = 0, newWidth, newHeight;
        double factor;
        BufferedImage img =
                new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        if (imgWidth >= imgHeight) {
            factor =  (double)targetWidth / (double)imgWidth ;
        } else {
            factor = (double)targetHeight /(double)imgHeight ;
        }

        newWidth = (int)(imgWidth * factor);
        newHeight = (int)(imgHeight * factor);
        if (newWidth < targetWidth) {
            x = (targetWidth - newWidth) / 2;
        }
        if (newHeight < targetHeight) {
            y = (targetHeight - newHeight) / 2;
        }
        Graphics g = img.createGraphics();
        g.drawImage(src, x, y, (int)(imgWidth * factor), (int)(imgHeight * factor), null);

        g.dispose();

        return img;
    }

    public static BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Automatic converstion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);
        //currentImage = gray;
        return gray;
    }

    public static BufferedImage toRed(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++)
        {

            for (int x = 0; x < width; x++)
            {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;

                // set new RGB keeping the r
                // value same as in original image
                // and setting g and b as 0.
                p = (a << 24) | (r << 16) | (0 << 8) | 0;

                res.setRGB(x, y, p);
            }
        }
        return res;
    }

    public static BufferedImage toSepia(BufferedImage image, int sepiaIntensity) {

        int width = image.getWidth();
        int height = image.getHeight();
        int sepiaDepth = 20;

        int[] imagePixels = image.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < imagePixels.length; i++) {
            int color = imagePixels[i];

            int r = (color >> 16) & 0xff;
            int g = (color >> 8) & 0xff;
            int b = (color) & 0xff;
            int gry = (r + g + b) / 3;

            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            // Darken blue color to increase sepia effect
            b -= sepiaIntensity;

            // normalize if out of bounds
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            imagePixels[i] = (color & 0xff000000) + (r << 16) + (g << 8) + b;
        }

        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        res.setRGB(0, 0, width, height, imagePixels, 0, width);
        return res;
    }

    public static BufferedImage cropImage(String option, BufferedImage currentImage) {
        int width = currentImage.getWidth(), height = currentImage.getHeight();
        int newWidth = (int) (width * 0.8);
        int newHeight = (int) (height * 0.8);
        BufferedImage img = currentImage.getSubimage((width - newWidth) / 2, (height - newHeight) / 2, newWidth, newHeight);//fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        return copyOfImage;
    }


}
