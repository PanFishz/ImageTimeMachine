package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class NeonMore extends Filter{
    public NeonMore() {
        super(FilterType.NEON_MORE);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        g = (p >> 18) & 0xff;
        if ((p & 0x00FFFFFF) >= 0 && (p & 0x00FFFFFF) <= 3900050) {
            //System.out.println("LLLLLLLLLLLLLLLblack");
            return -1;
        }

        // subtract RGB from 255
        r = 255 - r + g - b;
        g = 255 - g + b - r;
        b = 255 - b + r - g;

        // set new RGB value
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}