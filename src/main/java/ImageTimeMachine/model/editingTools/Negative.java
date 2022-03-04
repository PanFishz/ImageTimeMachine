package ImageTimeMachine.model.editingTools;


public class Negative extends Filter {
    public Negative() {
        super(FilterType.NEGATIVE);
    }

    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;

        // set new RGB value
        return   (a << 24) | (r << 16) | (g << 8) | b;
    }
}
