package ImageTimeMachine.model;

import java.awt.image.BufferedImage;
import java.util.Stack;

public class ImageStack {
    private Stack<ImageTitlePair> stack;
    private final int stackSize = 30;

    public ImageStack() {
        stack = new Stack<>();
    }
    public Stack<ImageTitlePair> getStack() {
        return stack;
    }

    public void addToStack(BufferedImage image, String title) {
        ImageTitlePair imageTitlePair = new ImageTitlePair(image, title);

        if (stack.size() > stackSize) {
            stack.remove(0);
        }
        stack.push(imageTitlePair);

    }
}
