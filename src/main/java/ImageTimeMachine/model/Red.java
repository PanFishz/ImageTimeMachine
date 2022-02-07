package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class Red extends Filter{
    public Red() {
        super(FilterType.RED);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        return (a << 24) | (r << 16) | (0 << 8) | 0;
    }
}
