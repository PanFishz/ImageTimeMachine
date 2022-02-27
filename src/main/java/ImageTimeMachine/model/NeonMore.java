package ImageTimeMachine.model;

public class NeonMore extends Filter{
    public NeonMore() {
        super(FilterType.NEON_MORE);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        g = (p >> 18) & 0xff;
        if ((p & 0x00FFFFFF) >= 0 && (p & 0x00FFFFFF) <= 3900050) {
            return -1;
        }

        r = 255 - r + g - b;
        g = 255 - g + b - r;
        b = 255 - b + r - g;

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}