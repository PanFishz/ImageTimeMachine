package ImageTimeMachine;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import static ImageTimeMachine.EditingTools.*;

public class UI {
    private final JFrame frame = new JFrame("Image TiMeMaChInE - A Photo Editing Tool");

    private Label labelTitle = new Label("Image TiMeMaChInE - " +
            "send your photos to a different time");
    private Label label2 = new Label("Step 1. Choose your image:     ");
    private Label label3 = new Label("Original photo");
    private Label label4 = new Label("Step 2. Time Travel: ");
    private Label label5 = new Label("Time-travelled photo");
    private Label label6 = new Label("Title:                                             ");
    private Label label7 = new Label("Step 3. Save your journey: ");

    private Button button1 = new Button("select");
    private Button button2 = new Button("To the past");
    private Button button3 = new Button("To the 1890");
    private Button button4 = new Button("To the dark age");
    private Button buttonToSave = new Button("Save Image");
    private Button button6 = new Button("Tutorial");
    private Button button7 = new Button("More Examples");
    private Button button8 = new Button("Back to the Present");
    private Button button9 = new Button("Advanced Options");
    private Button button10 = new Button("Surprise Me!!");
    private Button button11 = new Button("To the Future!!");
    private Button button12 = new Button("To the Unknown!!");
    //private Button button13 = new Button("Surprise Me!!");
    private static int numOfEdits = 0;


    private ImageCanvas preEditCanvas;
    private ImageCanvas postEditCanvas;
    private ImageCanvas homeCanvas;
    private FileDialog fileDialog = new FileDialog(frame, "Open", FileDialog.LOAD);
    private SpringLayout spring = new SpringLayout();
    private Container contentPane = frame.getContentPane();
    private BufferedImage editImage = null;
    private static BufferedImage currentImage = null;
    private BufferedImage masterImage = null;
    private ImageServiceClient serviceConnection= null;


    public void initialize() throws IOException {
        //serviceConnection = new ImageServiceClient();
        //serviceConnection.startConnection("127.0.0.1", 4444);

        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setLocation(0, 0);
        frame.setBackground(Color.white);
        frame.setLayout(spring);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        preEditCanvas = new ImageCanvas(null);
        preEditCanvas.setSize(450, 450);

        postEditCanvas = new ImageCanvas(null);
        postEditCanvas.setSize(450, 450);

        homeCanvas = new ImageCanvas(null);
        homeCanvas.setSize(900, 450);

        button1.addActionListener(new CustomButtonClickHandler());
        button2.addActionListener(new CustomButtonClickHandler2());
        button3.addActionListener(new CustomButtonClickHandler3());
        button4.addActionListener(new CustomButtonClickHandler4());
        button6.addActionListener(new CustomButtonClickHandler5());
        button7.addActionListener(new CustomButtonClickHandler6());
        buttonToSave.addActionListener(new CustomButtonClickHandler7());
        button8.addActionListener(new CustomButtonClickHandler8());
        button9.addActionListener(new CustomButtonClickHandler9());
        button10.addActionListener(new CustomButtonClickHandler10());
        button11.addActionListener(new CustomButtonClickHandler11());
        button12.addActionListener(new CustomButtonClickHandler12());
        //button11.addActionListener(new CustomButtonClickHandler10());

        buttonToSave.setForeground(new Color(126, 165, 242) );
        button9.setForeground(new Color(6, 155, 134) );
        button10.setForeground(new Color(156, 65, 134) );


        contentPane.setLayout(spring);
        contentPane.add(labelTitle);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(label7);
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(buttonToSave);
        contentPane.add(button6);
        contentPane.add(button7);
        contentPane.add(button8);
        contentPane.add(button9);
        contentPane.add(button10);
        contentPane.add(button11);
        contentPane.add(button12);
        //contentPane.add(button10);
        contentPane.add(preEditCanvas);
        contentPane.add(postEditCanvas);
        contentPane.add(homeCanvas);

        labelTitle.setFont(new Font("Serif", Font.PLAIN, 25));
        label3.setFont(new Font("Serif", Font.PLAIN, 15));
        label3.setForeground(new Color(111, 122, 143));
        label5.setFont(new Font("Serif", Font.PLAIN, 15));
        label5.setForeground(new Color(111, 122, 143));
        label6.setFont(new Font("Serif", Font.PLAIN, 18));
        label6.setForeground(new Color(11, 122, 143));


        spring.putConstraint(SpringLayout.WEST, labelTitle,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labelTitle,
                10, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, button6,
                100, SpringLayout.EAST, labelTitle);
        spring.putConstraint(SpringLayout.NORTH, button6,
                5, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, button7,
                820, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button7,
                30, SpringLayout.NORTH, button6);

        spring.putConstraint(SpringLayout.WEST, preEditCanvas,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, preEditCanvas,
                40, SpringLayout.SOUTH, labelTitle);

        spring.putConstraint(SpringLayout.WEST, postEditCanvas,
                450, SpringLayout.WEST, preEditCanvas);
        spring.putConstraint(SpringLayout.NORTH, postEditCanvas,
                40,
                SpringLayout.SOUTH, labelTitle);

        spring.putConstraint(SpringLayout.WEST, homeCanvas,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, homeCanvas,
                40, SpringLayout.SOUTH, labelTitle);

        spring.putConstraint(SpringLayout.WEST, label3,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label3,
                10,
                SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, label4,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label4,
                20,
                SpringLayout.SOUTH, label2);

        spring.putConstraint(SpringLayout.WEST, label7,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label7,
                100,
                SpringLayout.SOUTH, label4);

        spring.putConstraint(SpringLayout.WEST, label5,
                640, SpringLayout.EAST, label3);
        spring.putConstraint(SpringLayout.NORTH, label5,
                10,
                SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, label6,
                500, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label6,
                7,
                SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, label2,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label2,
                50,
                SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, button1,
                0,
                SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, button1,
                45,
                SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, button2,
                210, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button2,
                10, SpringLayout.SOUTH, button1);

        spring.putConstraint(SpringLayout.WEST, button3,
                330, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button3,
                10,
                SpringLayout.SOUTH, button1);


        spring.putConstraint(SpringLayout.WEST, button4,
                460, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button4,
                10,
                SpringLayout.SOUTH, button1);

        spring.putConstraint(SpringLayout.WEST, button11,
                600, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button11,
                10,
                SpringLayout.SOUTH, button1);

        spring.putConstraint(SpringLayout.WEST, button12,
                740, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button12,
                10,
                SpringLayout.SOUTH, button1);

        spring.putConstraint(SpringLayout.WEST, buttonToSave,
                230, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, buttonToSave,
                95,
                SpringLayout.SOUTH, label4);

        spring.putConstraint(SpringLayout.WEST, button8,
                210, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button8,
                10,
                SpringLayout.SOUTH, button2);

        spring.putConstraint(SpringLayout.WEST, button9,
                170, SpringLayout.WEST, button8);
        spring.putConstraint(SpringLayout.NORTH, button9,
                10,
                SpringLayout.SOUTH, button2);

        spring.putConstraint(SpringLayout.WEST, button10,
                160, SpringLayout.WEST, button9);
        spring.putConstraint(SpringLayout.NORTH, button10,
                10,
                SpringLayout.SOUTH, button2);

        frame.setVisible(true);

        BufferedImage og_image = null;
        try
        {
            og_image = ImageIO.read(new File("/Users/yushanmeyers/Documents/cats2.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        og_image = scale(og_image, 900, 450);
        homeCanvas.setImage(og_image);
        homeCanvas.repaint();
        homeCanvas.setVisible(true);
        preEditCanvas.setVisible(false);
        postEditCanvas.setVisible(false);
        label6.setVisible(false);
    }

    private void imageLoading() throws IOException {
        if ( numOfEdits > 0) {
            if (warning() == 0) {
                return;
            }
        }

        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {

            String d = (fileDialog.getDirectory() + fileDialog.getFile());

            try {
                masterImage = ImageIO.read(new File(d));
                currentImage = masterImage;
                editImage = scale(masterImage, 450, 450);
            } catch (Exception ex) {
                System.out.println("Error");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "This photo doesn't work. Choose another one.",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
            }

            preEditCanvas.setImage(editImage);
            preEditCanvas.repaint();

            BufferedImage blank_image = ImageIO.read(
                    new File("/Users/yushanmeyers/Documents/blank_image.jpg"));

            postEditCanvas.setImage(blank_image);
            postEditCanvas.repaint();
            homeCanvas.setVisible(false);
            preEditCanvas.setVisible(true);
            postEditCanvas.setVisible(true);
            label2.setText("Step 1: Select another photo: ");
            numOfEdits = 0;
        }
    }


    class ImageCanvas extends Canvas {
        private Image img;

        public ImageCanvas(Image img) {
            this.img = img;
        }

        public void paint(Graphics g) {
            if (img != null) {
                g.drawImage(img, 0, 0, this);
            }
        }

        public void setImage(Image img) {
            this.img = img;
        }
    }

    public class CustomButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label6.setVisible(false);
            try{
                imageLoading();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please ensure compliance!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class CustomButtonClickHandler2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                currentImage = toGrayScale(currentImage);
                editImage = scale(currentImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                BufferedImage sepia = toSepia(currentImage, 10);
                currentImage = sepia;
                sepia = scale(sepia, 450, 450);
                postEditCanvas.setImage(sepia);
                postEditCanvas.repaint();
                File outputfile = new File("saved.png");
                ImageIO.write(sepia, "png", outputfile);
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler4 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                editImage = toRed(currentImage);
                editImage = toSepia(editImage, 10);
                currentImage = editImage;
                editImage = scale(editImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler5 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = null;
            try
            {
                image = ImageIO.read(new File("/Users/yushanmeyers/Documents/filter_tutorial.png"));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel, "Tutorial",
                    JOptionPane.PLAIN_MESSAGE, null);

        }
    }

    public class CustomButtonClickHandler6 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = null;
            try
            {
                image = ImageIO.read(new File("/Users/yushanmeyers/Documents/filter_example.png"));
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel,
                    "Filters Examples", JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    public class CustomButtonClickHandler7 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentImage == null)
            {
                JOptionPane.showMessageDialog(frame, "No image to save!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFileChooser savechooser = new JFileChooser();
            FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG File", "png");
            savechooser.addChoosableFileFilter(pngFilter);
            savechooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG File", "jpg"));
            savechooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF File", "gif"));

            savechooser.setFileFilter(pngFilter); // Default choose PNG

            int returnVal = savechooser.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = savechooser.getSelectedFile();

                FileNameExtensionFilter currentFilter = (FileNameExtensionFilter) savechooser.getFileFilter();
                String ext = currentFilter.getExtensions()[0];

                if (!currentFilter.accept(file))
                {
                    // File does not not have the correct extension, fix it
                    String fileName = file.getName();
                    file = new File(file.getParent(), fileName + "." + ext);
                }

                String format = "jpg".equals(ext) ? "JPEG" : ext;
                // May not be strictly necessary, just a reminder that file ext != file format

                try
                {
                    if (!ImageIO.write(currentImage, format, file))
                    {
                        JOptionPane.showMessageDialog(frame, "Image not saved, choose another format!",
                                "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }

                numOfEdits = 0;
            }
        }
    }

    public class CustomButtonClickHandler8 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( numOfEdits != 0) {
                if (warning() == 0) {
                    return;
                }
            }
            if(currentImage == null)
            {
                JOptionPane.showMessageDialog(frame,
                        "No image to sent back to the present, please upload an image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentImage = masterImage;
            editImage = currentImage;
            editImage = scale(editImage, 450, 450);

            postEditCanvas.setImage(editImage);
            postEditCanvas.repaint();
            label6.setVisible(false);
            numOfEdits = 0;
        }
    }

    public class CustomButtonClickHandler9 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentImage == null)
            {
                JOptionPane.showMessageDialog(frame, "No image to crop, please upload an image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String anchor = getCropData();
            if (anchor == null) {
                return;
            }
            else {
                editImage = cropImage(anchor, currentImage);
                currentImage = editImage;
                editImage = scale(editImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
                numOfEdits++;
            }
        }
    }

    public class CustomButtonClickHandler10 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                currentImage = masterImage;
                editImage = toRed(currentImage);
                currentImage = editImage;
                editImage = scale(editImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler11 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                editImage = toNeon(currentImage);
                currentImage = editImage;
                editImage = scale(editImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler12 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                editImage = toNegative(currentImage);
                currentImage = editImage;
                editImage = scale(editImage, 450, 450);
                postEditCanvas.setImage(editImage);
                postEditCanvas.repaint();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try
            {
                getService();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            numOfEdits++;
        }
    }

    public void getService() throws IOException {
        //String[] searchTermList = {"unicorn","dogs_cats","programmer","ucla"};
        //String result = serviceConnection.sendMessage(searchTermList[(int) ((Math.random() * (4 - 1)) + 1)]);
        //String result = serviceConnection.sendMessage("smol_cat"); //jbhjbjbljk
        //System.out.println(result);
        //label6.setText(result);
        String[] titleList = {"RandomWord_1","RandomWord_2","RandomWord_3","RandomWord_4",
                "RandomWord_5","RandomWord_6","RandomWord_7","RandomWord_8",
                "RandomWord_9","RandomWord_10","RandomWord_11","RandomWord_12"};
        label6.setText("Traveled to: " + titleList[(int) ((Math.random() * (12 - 1)) + 1)]);
        label6.setVisible(true);
    }

    public int warning() {
        Object[] options = {"Cancel", "Yes, continue without saving"};
        int n = JOptionPane.showOptionDialog(frame,
                "You haven't saved your photo's time travel journey. Still want to continue?",
                "Warning - You haven't saved your image",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]);
        return n;
    }

    public String getCropData(){
        Object[] possibilities = {"Center", "North", "East", "West", "South",
                "Northeast", "Northwest", "Southeast", "Southwest"};
        String selection = (String) JOptionPane.showInputDialog(
                frame,
                "Choose an anchor to crop your image :\n",
                "Grid Anchor Based Image Cropping",
                JOptionPane.PLAIN_MESSAGE, null, possibilities, "Center");
        return selection;
    }

}
