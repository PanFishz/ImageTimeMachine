package ImageTimeMachine.model.editingTools;


public class GrayScale extends Filter {
    public GrayScale() {
        super(FilterType.GRAY);
    }

    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        int avg = (r + g + b) / 3;
        return (a << 24) | (avg << 16) | (avg << 8) | avg;
    }

}
