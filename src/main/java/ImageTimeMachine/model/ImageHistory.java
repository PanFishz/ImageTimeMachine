package ImageTimeMachine.model;

import java.awt.image.BufferedImage;

public class ImageHistory {
    private int numbOfEdits; //numbOfEdits is not necessary tied to stack
    private static ImageStack stack; //stack has a max limitation

    public ImageHistory() {
        numbOfEdits = 0;
        stack = new ImageStack();
    }

    public int getNumbOfEdits() {
        return numbOfEdits;
    }

    public ImageStack getStack() {
        return stack;
    }

    public void incrementEdits() {
        numbOfEdits++;
    }

    public void resetEdits() {
        numbOfEdits = 0;
    }

    public void resetStack() {
        stack = new ImageStack();
    }

    public void addToStack(BufferedImage image, String title) {
        stack.addToStack(image, title);
    }

    public void addTrivia(String trivia) {
        stack.peek().setTrivia(trivia);
    }

    public int isStackEmpty() {
        int stackSize = stack.getStack().size();
        if (stackSize == 1 || stackSize == 0) {
            return stackSize;
        }
        return -1;
    }

    public void popAStack() {
        stack.getStack().pop();
        numbOfEdits--;
    }
}
