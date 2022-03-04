package ImageTimeMachine.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

import static ImageTimeMachine.EditingControlPanel.opaqueFree;

public class FileUtil {

    public boolean saveFile(JFileChooser fileChooser, ImageManager imageManager) {//return false if image is not saved
        File file = fileChooser.getSelectedFile();

        FileNameExtensionFilter currentFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
        String extension = currentFilter.getExtensions()[0]; //get extension "png" , "gif", or "jpg"

        //append image title(a random time period) to the file name
        String fileName = file.getName() + "_" + imageManager.getImageHistory().getStack().peek().getTitle();
        file = new File(file.getParent(), fileName + "." + extension);

        //If save as jpg, convert image to be alpha-free
        handleJpg(extension, imageManager);
        try
        {
            ImageIO.write(imageManager.getCurrentImage(), extension, file);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void handleJpg(String extension, ImageManager imageManager) {
        if ("jpg".equals(extension))
        {
            imageManager.setCurrentImage(opaqueFree(imageManager.getCurrentImage()));
        }
    }
}
