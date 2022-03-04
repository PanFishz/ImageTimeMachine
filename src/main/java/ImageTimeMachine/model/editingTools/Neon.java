package ImageTimeMachine.model.editingTools;

public class Neon extends Filter {
    public Neon() {
        super(FilterType.NEON);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        g = (p >> 18) & 0xff;

        if ((p & 0x00FFFFFF) <= 3900050) {
            return -10000;
        }

        r = 255 - r + g;
        g = 255 - g + b;
        b = 255 - b + r;

       return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
