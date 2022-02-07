package ImageTimeMachine.model;


public class Blue extends Filter{
    public Blue() {
        super(FilterType.BLUE);
    }
    @Override
    public int calculatingRGB(int a, int r, int g, int b, int p) {
        return (a << 24) | (0 << 16) | (0 << 8) | b;
    }
}