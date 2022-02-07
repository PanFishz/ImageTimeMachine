package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class Neon extends Filter{
    public Neon() {
        super(FilterType.NEON);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        g = (p >> 18) & 0xff;
         /*
        if ((r & 0x00FFFFFF) <= 200  && (g & 0x00FFFFFF) <= 200  &&( b & 0x00FFFFFF) <= 200 ) {
            return -10000;
        }*/

        if ((p & 0x00FFFFFF) <= 3900050) {
            return -10000;
        }

        // subtract RGB from 255
        r = 255 - r + g;
        g = 255 - g + b;
        b = 255 - b + r;

        // set new RGB value
       return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
