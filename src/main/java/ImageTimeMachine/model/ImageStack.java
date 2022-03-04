package ImageTimeMachine.model;

import java.awt.image.BufferedImage;
import java.util.Stack;

public class ImageStack {
    private final Stack<ImageTitleTrivia> stack;
    private final int stackSize = 30;

    public ImageStack() {
        stack = new Stack<>();
    }
    public Stack<ImageTitleTrivia> getStack() {
        return stack;
    }

    public void addToStack(BufferedImage image, String title) {
        ImageTitleTrivia imageTitlePair = new ImageTitleTrivia(image, title);

        if (stack.size() > stackSize) {
            stack.remove(0);
        }
        stack.push(imageTitlePair);
    }
    public ImageTitleTrivia peek() {
        return stack.peek();
    }

}
