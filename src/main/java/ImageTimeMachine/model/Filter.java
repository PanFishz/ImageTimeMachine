package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

abstract class Filter extends Transformer {
    private FilterType filterType;


    public Filter(FilterType filterType) {
        this.filterType = filterType;
    }
    @Override
    public FilterType getFilterType() {
        return filterType;
    }

    @Override
    public BufferedImage makeNewImage(int imageWidth, int imageHeight) {
        return  new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage processEditing(BufferedImage image) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        BufferedImage editedImage = makeNewImage(imageWidth, imageHeight);
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {

                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                p = calculatingRGB(a, r, g, b, p);

                if (p == -10000) {
                    continue;
                }
                editedImage.setRGB(x, y, p);
            }
        }
        return editedImage;
    }

    abstract public int calculatingRGB(int a, int r, int g, int b, int p);
}
