package ImageTimeMachine;

import ImageTimeMachine.model.ImageCanvas;
import ImageTimeMachine.model.ImageStack;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ImageTimeMachine.EditingControlPanel.*;

public class UI {
    private final JFrame frame = new JFrame("Image TiMeMaChInE - A Photo Editing Tool");
    private static ImageStack stack = new ImageStack();
    private String imageTitle = null;

    private Label label1_Title = new Label("Image TiMeMaChInE - " +
            "send your photos to a different time");
    private Label label2_Step1 = new Label("Step 1. Choose your image:     ");
    private Label label3_OGImage = new Label("Original photo");
    private Label label4_Step2 = new Label("Step 2. Time Travel ~(^.^)~: ");
    private Label label5_AFImage = new Label("Time-travelled photo");
    private Label label6_ImageTitle = new Label("                                                          ");
    private Label label7_Step3 = new Label("Step 3. Save your journey: ");
    private Label label8_TranslatedTitle = new Label("                                                     ");
    private JTextArea text1_Trivia = new JTextArea(3,40);

    private Button button1_Select = new Button("select");
    private Button button2_ToDisPast = new Button("To the Distant Past");
    private Button button3_ToPast = new Button("To the Past");
    private Button button4_ToDark = new Button("To the Dark Ages");
    private Button button5_ToSave = new Button("Save Image");
    private Button button6_Tutorial = new Button("Tutorial");
    private Button button7_Examples = new Button("More Examples");
    private Button button8_BackPresent = new Button("Back to the Present");
    private Button button9_AdvanceOptions = new Button("Advanced Options");
    private Button button10_SurpriseMe = new Button("Surprise Me!!");
    private Button button11_ToFuture = new Button("To the Future!!");
    private Button button12_ToUnknown = new Button("To the Unknown!!");
    private Button button13_BackToLast = new Button("Back to the previous time period");
    private Button button14_Translate = new Button("Trivia");
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
    private ImageServiceClient serviceConnection= null; //Todo


    public void initialize() throws IOException {
        serviceConnection = new ImageServiceClient(); //Todo
        serviceConnection.startConnection("127.0.0.1", 4444);

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

        button1_Select.addActionListener(new CustomButtonClickHandler1_SelectPhoto());
        button2_ToDisPast.addActionListener(new CustomButtonClickHandler2_ToPast());
        button3_ToPast.addActionListener(new CustomButtonClickHandler3_To1890());
        button4_ToDark.addActionListener(new CustomButtonClickHandler4_ToDark());
        button6_Tutorial.addActionListener(new CustomButtonClickHandler5_Tutorial());
        button7_Examples.addActionListener(new CustomButtonClickHandler6_Examples());
        button5_ToSave.addActionListener(new CustomButtonClickHandler7_ToSave());
        button8_BackPresent.addActionListener(new CustomButtonClickHandler8_BackToPresent());
        button9_AdvanceOptions.addActionListener(new CustomButtonClickHandler9_AdvanceOptions());
        button10_SurpriseMe.addActionListener(new CustomButtonClickHandler10_SurpriseMe());
        button11_ToFuture.addActionListener(new CustomButtonClickHandler11_ToFuture());
        button12_ToUnknown.addActionListener(new CustomButtonClickHandler12_ToUnknown());
        button13_BackToLast.addActionListener(new CustomButtonClickHandler13_BackToLast());
        button14_Translate.addActionListener(new CustomButtonClickHandler14_Trivia());


        button5_ToSave.setForeground(new Color(126, 165, 242) );
        button9_AdvanceOptions.setForeground(new Color(6, 155, 134) );
        button10_SurpriseMe.setForeground(new Color(156, 65, 134) );


        contentPane.setLayout(spring);
        contentPane.add(label1_Title);
        contentPane.add(label2_Step1);
        contentPane.add(label3_OGImage);
        contentPane.add(label4_Step2);
        contentPane.add(label5_AFImage);
        contentPane.add(label6_ImageTitle);
        contentPane.add(label7_Step3);
        contentPane.add(label8_TranslatedTitle);
        contentPane.add(button1_Select);
        contentPane.add(button2_ToDisPast);
        contentPane.add(button3_ToPast);
        contentPane.add(button4_ToDark);
        contentPane.add(button5_ToSave);
        contentPane.add(button6_Tutorial);
        contentPane.add(button7_Examples);
        contentPane.add(button8_BackPresent);
        contentPane.add(button9_AdvanceOptions);
        contentPane.add(button10_SurpriseMe);
        contentPane.add(button11_ToFuture);
        contentPane.add(button12_ToUnknown);
        contentPane.add(button13_BackToLast);
        contentPane.add(button14_Translate);
        contentPane.add(preEditCanvas);
        contentPane.add(postEditCanvas);
        contentPane.add(homeCanvas);
        contentPane.add(text1_Trivia);

        label1_Title.setFont(new Font("Serif", Font.PLAIN, 25));
        label3_OGImage.setFont(new Font("Serif", Font.PLAIN, 10));
        label3_OGImage.setForeground(new Color(111, 122, 143));
        label5_AFImage.setFont(new Font("Serif", Font.PLAIN, 10));
        label5_AFImage.setForeground(new Color(111, 122, 143));
        label6_ImageTitle.setFont(new Font("Serif", Font.PLAIN, 18));
        label6_ImageTitle.setForeground(new Color(11, 122, 143));
        label8_TranslatedTitle.setFont(new Font("Serif", Font.PLAIN, 18));
        label8_TranslatedTitle.setForeground(new Color(11, 122, 143));
        text1_Trivia.setOpaque(false);
        text1_Trivia.setBackground(Color.lightGray);



        spring.putConstraint(SpringLayout.WEST, label1_Title,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label1_Title,
                10, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, button6_Tutorial,
                100, SpringLayout.EAST, label1_Title);
        spring.putConstraint(SpringLayout.NORTH, button6_Tutorial,
                5, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, button7_Examples,
                820, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button7_Examples,
                30, SpringLayout.NORTH, button6_Tutorial);

        spring.putConstraint(SpringLayout.WEST, preEditCanvas,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, preEditCanvas,
                40, SpringLayout.SOUTH, label1_Title);

        spring.putConstraint(SpringLayout.WEST, postEditCanvas,
                450, SpringLayout.WEST, preEditCanvas);
        spring.putConstraint(SpringLayout.NORTH, postEditCanvas,
                40, SpringLayout.SOUTH, label1_Title);

        spring.putConstraint(SpringLayout.WEST, homeCanvas,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, homeCanvas,
                40, SpringLayout.SOUTH, label1_Title);

        spring.putConstraint(SpringLayout.WEST, label3_OGImage,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label3_OGImage,
                68, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, label4_Step2,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label4_Step2,
                30, SpringLayout.SOUTH, label2_Step1);

        spring.putConstraint(SpringLayout.WEST, label7_Step3,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label7_Step3,
                60, SpringLayout.SOUTH, label4_Step2);

        spring.putConstraint(SpringLayout.WEST, label5_AFImage,
                725, SpringLayout.EAST, label3_OGImage);
        spring.putConstraint(SpringLayout.NORTH, label5_AFImage,
                68, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, label6_ImageTitle,
                390, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label6_ImageTitle,
                7, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, text1_Trivia,
                500, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, text1_Trivia,
                7, SpringLayout.SOUTH, label6_ImageTitle);

        spring.putConstraint(SpringLayout.WEST, label2_Step1,
                50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, label2_Step1,
                80, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, button1_Select,
                240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button1_Select,
                76, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, button2_ToDisPast,
                240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button2_ToDisPast,
                15, SpringLayout.SOUTH, button1_Select);

        spring.putConstraint(SpringLayout.WEST, button3_ToPast,
                405, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button3_ToPast,
                15, SpringLayout.SOUTH, button1_Select);

        spring.putConstraint(SpringLayout.WEST, button4_ToDark,
                515, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button4_ToDark,
                15, SpringLayout.SOUTH, button1_Select);

        spring.putConstraint(SpringLayout.WEST, button11_ToFuture,
                665, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button11_ToFuture,
                15, SpringLayout.SOUTH, button1_Select);

        spring.putConstraint(SpringLayout.WEST, button12_ToUnknown,
                800, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button12_ToUnknown,
                15, SpringLayout.SOUTH, button1_Select);

        spring.putConstraint(SpringLayout.WEST, button5_ToSave,
                240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button5_ToSave,
                56, SpringLayout.SOUTH, label4_Step2);

        spring.putConstraint(SpringLayout.WEST, button8_BackPresent,
                240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button8_BackPresent,
                10, SpringLayout.SOUTH, button2_ToDisPast);

        spring.putConstraint(SpringLayout.WEST, button9_AdvanceOptions,
                170, SpringLayout.WEST, button8_BackPresent);
        spring.putConstraint(SpringLayout.NORTH, button9_AdvanceOptions,
                10, SpringLayout.SOUTH, button2_ToDisPast);

        spring.putConstraint(SpringLayout.WEST, button10_SurpriseMe,
                170, SpringLayout.WEST, button9_AdvanceOptions);
        spring.putConstraint(SpringLayout.NORTH, button10_SurpriseMe,
                10, SpringLayout.SOUTH, button2_ToDisPast);

        spring.putConstraint(SpringLayout.WEST, button13_BackToLast,
                130, SpringLayout.WEST, button10_SurpriseMe);
        spring.putConstraint(SpringLayout.NORTH, button13_BackToLast,
                10, SpringLayout.SOUTH, button2_ToDisPast);

        spring.putConstraint(SpringLayout.WEST, button14_Translate,
                855, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, button14_Translate,
                5, SpringLayout.SOUTH, postEditCanvas);

        button14_Translate.setVisible(false);
        frame.setVisible(true);

        BufferedImage homeImage = null;
        try {
            homeImage = ImageIO.read(new File("cats2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeImage = scaleImage(homeImage, 900, 450);
        homeCanvas.setImage(homeImage);
        homeCanvas.repaint();
        homeCanvas.setVisible(true);
        preEditCanvas.setVisible(false);
        postEditCanvas.setVisible(false);
        label6_ImageTitle.setVisible(false);
    }

    private void imageLoading() throws IOException {
        if ( numOfEdits > 0 ) {
            if (warning() == 0) {
                return;
            }
        }

        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {

            String d = (fileDialog.getDirectory() + fileDialog.getFile());
            button14_Translate.setVisible(false);
            label8_TranslatedTitle.setVisible(false);

            try {
                masterImage = ImageIO.read(new File(d));
                currentImage = masterImage;

                stack = new ImageStack();
                stack.addToStack(currentImage, "The Present");

                editImage = scaleImage(currentImage, 450, 450);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "This photo doesn't work. Choose another one.",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }

            preEditCanvas.setImage(editImage);
            preEditCanvas.repaint();
            BufferedImage blank_image = ImageIO.read(new File("blank_image.png"));
            //URL url = new URL("http://abeautifulmess.com/wp-content/uploads/2021/07/dollhouse5.jpg");
            //BufferedImage blank_image = ImageIO.read(url);

            postEditCanvas.setImage(blank_image);
            postEditCanvas.repaint();
            homeCanvas.setVisible(false);
            preEditCanvas.setVisible(true);
            postEditCanvas.setVisible(true);
            label6_ImageTitle.setVisible(false);
            label2_Step1.setText("Step 1: Select another photo: ");
            numOfEdits = 0;
        }
    }

    public class CustomButtonClickHandler1_SelectPhoto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                imageLoading();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please ensure compliance!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class CustomButtonClickHandler2_ToPast implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);
            try {
                currentImage = toGrayScale(currentImage);
                displayImage();
            } catch (Exception ex) {
                label6_ImageTitle.setVisible(false);
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler3_To1890 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);
            try {
                currentImage = toSepia(currentImage, 10);
                displayImage();
            } catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler4_ToDark implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);
            try {
                currentImage = toBlue(currentImage);
                currentImage = toSepia(currentImage, 10);
                displayImage();
            } catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler5_Tutorial implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("tutorial.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel, "Tutorial",
                    JOptionPane.PLAIN_MESSAGE, null);

        }
    }

    public class CustomButtonClickHandler6_Examples implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("filterExamples.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel,
                    "Filter Examples", JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    public class CustomButtonClickHandler7_ToSave implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentImage == null) {
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

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = savechooser.getSelectedFile();

                FileNameExtensionFilter currentFilter = (FileNameExtensionFilter) savechooser.getFileFilter();
                String ext = currentFilter.getExtensions()[0];

                String fileName = file.getName() + "_" + stack.getStack().peek().getTitle();
                file = new File(file.getParent(), fileName + "." + ext);

                if (!currentFilter.accept(file)) {
                    // File does not have the correct extension, fix it
                    fileName = file.getName();
                    file = new File(file.getParent(), fileName + "." + ext);
                }

                String format = "jpg".equals(ext) ? "JPEG" : ext;
                // May not be strictly necessary, just a reminder that file ext != file format

                try {
                    if (!ImageIO.write(currentImage, format, file)) {
                        JOptionPane.showMessageDialog(frame, "Image not saved, choose another format!",
                                "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                numOfEdits = 0;
            }
        }
    }

    public class CustomButtonClickHandler8_BackToPresent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( numOfEdits > 0) {
                if (warning() == 0) {
                    return;
                }
            }
            if(currentImage == null) {
                JOptionPane.showMessageDialog(frame,
                        "No image to sent back to the present, please upload an image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentImage = masterImage;
            displayImage();
            label6_ImageTitle.setText("                  Back to the Present");
            label6_ImageTitle.setVisible(true);
            label8_TranslatedTitle.setVisible(false);
            stack = new ImageStack();
            stack.addToStack(currentImage, "The Present");

            numOfEdits = 0;
        }
    }

    public class CustomButtonClickHandler9_AdvanceOptions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(currentImage == null) {
                JOptionPane.showMessageDialog(frame, "No image to crop, please upload an image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String anchor = getCropData();
            if (anchor == null) {
                return;
            }
            else {
                currentImage = cropImage(anchor, currentImage);
                String translation = stack.getStack().peek().getTranslation();
                stack.addToStack(currentImage, stack.getStack().peek().getTitle());
                stack.getStack().peek().setTrivia(translation);
                displayImage();
                numOfEdits++;
            }
        }
    }

    public class CustomButtonClickHandler10_SurpriseMe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);
            try{
                currentImage = masterImage;
                currentImage = surpriseMe(currentImage);
                displayImage();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler11_ToFuture implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);

            try{
                currentImage = toNeon(currentImage);
                displayImage();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }

    public class CustomButtonClickHandler12_ToUnknown implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label8_TranslatedTitle.setVisible(false);
            try{
                currentImage = toNegative(currentImage);
                displayImage();
            }catch (Exception ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Please upload your image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addStackAndTitle();
            numOfEdits++;
        }
    }
    public class CustomButtonClickHandler13_BackToLast implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (stack.getStack().size() == 0) {
                JOptionPane.showMessageDialog(frame, "Please upload an image!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (stack.getStack().size() == 1) {
                JOptionPane.showMessageDialog(frame, "Can't go back anymore!",
                        "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                stack.getStack().pop();
                numOfEdits --;
            }
            currentImage = stack.getStack().peek().getImage();
            displayImage();

            String result = stack.getStack().peek().getTitle();
            label6_ImageTitle.setText("Traveled to: " + result);
            if (stack.getStack().peek().getTranslation() != null) {
                label8_TranslatedTitle.setText(stack.getStack().peek().getTranslation());
                label8_TranslatedTitle.setVisible(true);
            }
            else {
                label8_TranslatedTitle.setVisible(false);
            }
            label6_ImageTitle.setVisible(true);
        }
    }
    public class CustomButtonClickHandler14_Trivia implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                String trivia = getFactService(null);
                text1_Trivia.setText(trivia);
                text1_Trivia.setLineWrap(true);
                text1_Trivia.setVisible(true);
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public void displayImage() {
        editImage = scaleImage(currentImage, 450, 450);
        postEditCanvas.setImage(editImage);
        postEditCanvas.repaint();
    }



    public String getTriviaService() throws IOException {

        //String result = serviceConnection.sendMessage("smol_cat"); //yufyvuvhvhy //rubber duck
        //System.out.println(result);
        String result = serviceConnection.sendMessage(".");
        //System.out.println(result);
        //result = serviceConnection.sendMessage("bnjkbvhjkvjhk");
        //System.out.println(result);
        //result = serviceConnection.sendMessage("smol_cat"); //yufyvuvhvhy //rubber duck
        //System.out.println(result);

        String[] titleList = {"RandomWord_1","RandomWord_2","RandomWord_3","RandomWord_4",
                "RandomWord_5","RandomWord_6","RandomWord_7","RandomWord_8",
                "RandomWord_9","RandomWord_10","RandomWord_11","RandomWord_12"};
        //String result = titleList[(int) ((Math.random() * (12 - 1)) + 1)];

        return result;
    }

    public String getWordService(String word) throws IOException {
        OkHttp obj = new OkHttp();
        String response = (obj.sendGETSync(word, 1));
        System.out.println(response);
        label6_ImageTitle.setText("Traveled to: " + response);
        label6_ImageTitle.setVisible(true);
        button14_Translate.setVisible(true);
        return response;
    }

    public String getFactService(String word) throws IOException {
        OkHttp obj = new OkHttp();
        String response = (obj.sendGETSync(word, 2));
        System.out.println(response);
        stack.getStack().peek().setTrivia(response);
        return response;
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
                options[0]);
        return n;
    }

    public String getCropData() {
        Object[] possibilities = {"Center", "North", "East", "West", "South",
                "Northeast", "Northwest", "Southeast", "Southwest"};
        String selection = (String) JOptionPane.showInputDialog(
                frame,
                "Choose an anchor to crop your image :\n",
                "Grid Anchor Based Image Cropping",
                JOptionPane.PLAIN_MESSAGE, null, possibilities, "Center");
        return selection;
    }

    public void addStackAndTitle() {
        try {
            imageTitle = getWordService(null);
            stack.addToStack(currentImage, imageTitle);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
