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
        return (a << 24) | (r << 16) | (g << 8) | b;

    }
}
