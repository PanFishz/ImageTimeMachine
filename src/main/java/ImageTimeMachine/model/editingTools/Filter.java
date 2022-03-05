package ImageTimeMachine.model.editingTools;

import java.awt.image.BufferedImage;

abstract class Filter extends Transformer {
    private final FilterType filterType;
    private int imageWidth;
    private int imageHeight;
    private int p, a, r, g, b;


    public Filter(FilterType filterType) {
        this.filterType = filterType;
    }

    @Override
    public FilterType getFilterType() {
        return filterType;
    }

    @Override
    public BufferedImage makeNewImage(int imageWidth, int imageHeight) {
        return new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage processEditing(BufferedImage image) {
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        BufferedImage editedImage = makeNewImage(imageWidth, imageHeight);
        return processImage(image, editedImage);
    }

    public BufferedImage processImage(BufferedImage image, BufferedImage editedImage) {
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {

                getARBG(image, x, y);

                p = calculatingRGB(a, r, g, b, p);

                if (p == -10000) {
                    continue;
                }
                editedImage.setRGB(x, y, p);
            }
        }
        return editedImage;
    }

    private void getARBG(BufferedImage image, int x, int y) {
        p = image.getRGB(x, y);

        a = (p >> 24) & 0xff;
        r = (p >> 16) & 0xff;
        g = (p >> 8) & 0xff;
        b = p & 0xff;
    }

    abstract public int calculatingRGB(int a, int r, int g, int b, int p);
}
