package ImageTimeMachine.model.editingTools;


public class GrayScaleAndBlue extends Filter {
    public GrayScaleAndBlue() {
        super(FilterType.GRAYBLUE);
    }

    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        if (r <= 50 && g <= 50 && b <= 50) {

            p = (a << 24) | 255;

            return p;
        }
        int avg = (r + g + b) / 3;
        return (a << 24) | (avg << 16) | (avg << 8) | avg;
    }

}
