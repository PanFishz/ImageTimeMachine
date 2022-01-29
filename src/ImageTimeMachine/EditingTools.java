package ImageTimeMachine;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;

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

    public static BufferedImage toGrayScale(BufferedImage img) {
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Automatic converstion....
        //ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        //op.filter(img, gray);
        int width = img.getWidth();
        int height = img.getHeight();

        // convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Here (x,y)denotes the coordinate of image
                // for modifying the pixel value.
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // calculate average
                int avg = (r + g + b) / 3;

                // replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8)
                        | avg;

                gray.setRGB(x, y, p);
            }
        }
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
                //The signed left shift operator "<<" shifts a bit pattern to the left
                //
                //The signed right shift operator ">>" shifts a bit pattern to the right.
                //
                //The unsigned right shift operator ">>>" shifts a zero into the leftmost position
                p = (a << 24) | (r << 16) | (0 << 8) | 0;

                res.setRGB(x, y, p);
            }
        }
        return res;
    }

    public static BufferedImage toNeon(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 18) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r + g;
                g = 255 - g + b;
                b = 255 - b + r;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                res.setRGB(x, y, p);
            }
        }
        return res;
    }

    public static BufferedImage toNeonMore(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 18) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r + g - b;
                g = 255 - g + b - r;
                b = 255 - b + r - g;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                res.setRGB(x, y, p);
            }
        }
        return res;
    }

    public static BufferedImage toNegative(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
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
        int xCoordinate, yCoordinate;

        switch (option) {
            case "North":
                xCoordinate = (int)((width - newWidth) / 2);
                yCoordinate = 0;
                break;
            case "East":
                xCoordinate = (width - newWidth);
                yCoordinate = (int)((height - newHeight) / 2);
                break;
            case "West":
                xCoordinate = 0;
                yCoordinate = (int)((height - newHeight) / 2);
                break;
            case "South":
                xCoordinate = (int)((width - newWidth) / 2);
                yCoordinate = (height - newHeight);
                break;
            case "Northeast":
                xCoordinate = (width - newWidth);
                yCoordinate = 0;
                break;
            case "Northwest":
                xCoordinate = 0;
                yCoordinate = 0;
                break;
            case "Southeast":
                xCoordinate = (width - newWidth);
                yCoordinate = (height - newHeight);
                break;
            case "Southwest":
                xCoordinate = 0;
                yCoordinate = (height - newHeight);
                break;

            case "Center":
            default:
                xCoordinate = (width - newWidth) / 2;
                yCoordinate = (height - newHeight) / 2;
                break;
        }

        BufferedImage img = currentImage.getSubimage(xCoordinate, yCoordinate,
                newWidth, newHeight);//fill in the corners of the desired crop location here
        BufferedImage copyOfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        System.out.println(currentImage.getWidth() + " " + currentImage.getHeight());

        System.out.println(img.getWidth() + " " + img.getHeight());
        System.out.println(copyOfImage.getWidth() + " " + copyOfImage.getHeight());

        return copyOfImage;
    }


}
