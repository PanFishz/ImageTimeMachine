package ImageTimeMachine.model.editingTools;


public class Sepia extends Filter {
    private int sepiaIntensity = 10;

    public Sepia() {
        super(FilterType.SEPIA);
    }

    public void setSepiaIntensity(int sepiaIntensity) {
        this.sepiaIntensity = sepiaIntensity;
    }

    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {

        int sepiaDepth = 20;
        int gry = (r + g + b) / 3;

        r = g = b = gry;
        r = r + (sepiaDepth * 2);
        g = g + sepiaDepth;

        r = normalizedRedGreen(r);

        g = normalizedRedGreen(g);

        b = normalizedBlue(b);

        return (a << 24) | (r << 16) | (g << 8) | b;

    }

    private int normalizedRedGreen(int value) {
        if (value > 255) {
            return 255;
        }
        return value;
    }

    private int normalizedBlue(int value) {
        value = normalizedRedGreen(value) - sepiaIntensity;
        if (value < 0) {
            return 0;
        }
        return normalizedRedGreen(value);
    }
}
