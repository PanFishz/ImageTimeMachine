package ImageTimeMachine.unittest;
import ImageTimeMachine.model.editingTools.GrayScale;
import ImageTimeMachine.model.editingTools.Negative;
import ImageTimeMachine.model.editingTools.Neon;
import ImageTimeMachine.model.editingTools.Sepia;
import junit.framework.*;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.image.BufferedImage;

public class unittest extends TestCase {
    protected BufferedImage sampleImage;
    protected BufferedImage resultImage;
    protected Graphics2D graphics;


    @BeforeEach
    protected void setUp() {
        sampleImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = sampleImage.createGraphics();
        graphics.setPaint(new Color(0, 50, 100));
        graphics.fillRect(0, 0, sampleImage.getWidth(), sampleImage.getHeight());
    }


    public void test_GrayScale() {
        GrayScale grayFilter = new GrayScale();
        resultImage = grayFilter.processEditing(sampleImage);
        Color newColor = new Color(50, 50, 50);
        int expectedRGB = newColor.getRGB();
        int afterRGB = resultImage.getRGB(0, 0);
        System.out.println(afterRGB);
        assertEquals(afterRGB, expectedRGB);
    }

    public void test_Sepia() {
        Sepia sepiaFilter = new Sepia();
        resultImage = sepiaFilter.processEditing(sampleImage);
        Color newColor = new Color(90, 70, 40);
        int expectedRGB = newColor.getRGB();
        System.out.println(expectedRGB);
        int afterRGB = resultImage.getRGB(0, 0);
        System.out.println(afterRGB);
        assertEquals(afterRGB, expectedRGB);
    }

    public void test_Negative() {
        Negative negativeFilter = new Negative();
        resultImage = negativeFilter.processEditing(sampleImage);
        Color newColor = new Color(255, 205, 155);
        int expectedRGB = newColor.getRGB();
        System.out.println(expectedRGB);
        int afterRGB = resultImage.getRGB(0, 0);
        System.out.println(afterRGB);
        assertEquals(afterRGB, expectedRGB);
    }

    public void test_Neon() {
        graphics.setPaint(new Color(0, 0, 0));
        graphics.fillRect(0, 0, sampleImage.getWidth(), sampleImage.getHeight());
        Neon neonFilter = new Neon();
        resultImage = neonFilter.processEditing(sampleImage);
        Color newColor = new Color(255, 205, 155);
        int expectedRGB = newColor.getRGB();
        System.out.println(expectedRGB);
        int afterRGB = resultImage.getRGB(0, 0);
        System.out.println(afterRGB);
        assertEquals(afterRGB, expectedRGB);
    }
}