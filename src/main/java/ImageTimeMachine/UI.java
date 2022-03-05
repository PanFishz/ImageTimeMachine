package ImageTimeMachine;

import ImageTimeMachine.model.FileUtil;
import ImageTimeMachine.model.ImageCanvas;
import ImageTimeMachine.model.ImageManager;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static ImageTimeMachine.EditingControlPanel.*;
import static ImageTimeMachine.model.style.ColorPicker.*;
import static ImageTimeMachine.model.style.FontPicker.*;

public class UI {
    //region Members
    private final JFrame frame = new JFrame("Image TiMeMaChInE - A Photo Editing Tool");

    private final Label labTitle = new Label("Image TiMeMaChInE - send your photos to a different time");
    private final Label labStep1 = new Label("Step 1. Choose your image:     ");
    private final Label labOGImage = new Label("Original photo");
    private final Label labStep2 = new Label("Step 2. Time Travel ~(^.^)~: ");
    private final Label labAFImage = new Label("Time-travelled photo");
    private final Label labStep3 = new Label("Step 3. Save your journey: ");

    private final JTextArea txtTrivia = new JTextArea(2, 37);
    private final JTextArea txtImageTitle = new JTextArea("");

    private final Button btnUploadImage = new Button("Upload");
    private final Button btnToDisPast = new Button("To the Distant Past");
    private final Button btnToPast = new Button("To the Past");
    private final Button btnToDark = new Button("To the Dark Ages");
    private final Button btnToSave = new Button("Save Image");
    private final Button btnTutorial = new Button("Tutorial");
    private final Button btnExamples = new Button("More Examples");
    private final Button btnBack2Present = new Button("Back to the Present");
    private final Button btnAdvanceOptions = new Button("Advanced Options");
    private final Button btnSurpriseMe = new Button("Surprise Me!!");
    private final Button btnToFuture = new Button("To the Future!!");
    private final Button btnToUnknown = new Button("To the Unknown!!");
    private final Button btnBackToLast = new Button("Back to the previous time period");
    private final Button btnTrivia = new Button("Trivia");
    private final Button btnSelectFromWeb = new Button("Select From Web");

    private final ImageCanvas preEditCanvas = new ImageCanvas(null, 450, 450);
    private final ImageCanvas postEditCanvas = new ImageCanvas(null, 450, 450);
    private final ImageCanvas homeCanvas = new ImageCanvas(null, 900, 450);

    private final FileDialog fileDialog = new FileDialog(frame, "Open", FileDialog.LOAD);
    private final SpringLayout spring = new SpringLayout();
    private final Container contentPane = frame.getContentPane();
    private final ImageManager imageManager = new ImageManager();
    private final FileUtil fileUtil = new FileUtil();
    private final OkHttp okHttp = new OkHttp();
    //endregion

    public void initialize() {
        // declare UI components and initial setup
        configureJFrame();
        setupClickHandlers();
        addToContentPane();
        addStyles();
        putConstraint();
        setupHome();
    }

    private void putConstraint() {
        spring.putConstraint(SpringLayout.WEST, labTitle, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labTitle, 10, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, btnTutorial, 100, SpringLayout.EAST, labTitle);
        spring.putConstraint(SpringLayout.NORTH, btnTutorial, 5, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, btnExamples, 820, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnExamples, 30, SpringLayout.NORTH, btnTutorial);

        spring.putConstraint(SpringLayout.WEST, preEditCanvas, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, preEditCanvas, 40, SpringLayout.SOUTH, labTitle);

        spring.putConstraint(SpringLayout.WEST, postEditCanvas, 450, SpringLayout.WEST, preEditCanvas);
        spring.putConstraint(SpringLayout.NORTH, postEditCanvas, 40, SpringLayout.SOUTH, labTitle);

        spring.putConstraint(SpringLayout.WEST, homeCanvas, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, homeCanvas, 40, SpringLayout.SOUTH, labTitle);

        spring.putConstraint(SpringLayout.WEST, labOGImage, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labOGImage, 68, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, labStep2, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labStep2, 30, SpringLayout.SOUTH, labStep1);

        spring.putConstraint(SpringLayout.WEST, labStep3, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labStep3, 60, SpringLayout.SOUTH, labStep2);

        spring.putConstraint(SpringLayout.WEST, labAFImage, 725, SpringLayout.EAST, labOGImage);
        spring.putConstraint(SpringLayout.NORTH, labAFImage, 68, SpringLayout.NORTH, contentPane);

        spring.putConstraint(SpringLayout.WEST, txtImageTitle, 390, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, txtImageTitle, 7, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, txtTrivia, 500, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, txtTrivia, 7, SpringLayout.SOUTH, txtImageTitle);

        spring.putConstraint(SpringLayout.WEST, labStep1, 50, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, labStep1, 80, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, btnUploadImage, 240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnUploadImage, 76, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, btnSelectFromWeb, 330, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnSelectFromWeb, 76, SpringLayout.SOUTH, preEditCanvas);

        spring.putConstraint(SpringLayout.WEST, btnToDisPast, 240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToDisPast, 15, SpringLayout.SOUTH, btnUploadImage);

        spring.putConstraint(SpringLayout.WEST, btnToPast, 405, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToPast, 15, SpringLayout.SOUTH, btnUploadImage);

        spring.putConstraint(SpringLayout.WEST, btnToDark, 515, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToDark, 15, SpringLayout.SOUTH, btnUploadImage);

        spring.putConstraint(SpringLayout.WEST, btnToFuture, 665, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToFuture, 15, SpringLayout.SOUTH, btnUploadImage);

        spring.putConstraint(SpringLayout.WEST, btnToUnknown, 800, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToUnknown, 15, SpringLayout.SOUTH, btnUploadImage);

        spring.putConstraint(SpringLayout.WEST, btnToSave, 240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnToSave, 56, SpringLayout.SOUTH, labStep2);

        spring.putConstraint(SpringLayout.WEST, btnBack2Present, 240, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnBack2Present, 10, SpringLayout.SOUTH, btnToDisPast);

        spring.putConstraint(SpringLayout.WEST, btnAdvanceOptions, 170, SpringLayout.WEST, btnBack2Present);
        spring.putConstraint(SpringLayout.NORTH, btnAdvanceOptions, 10, SpringLayout.SOUTH, btnToDisPast);

        spring.putConstraint(SpringLayout.WEST, btnSurpriseMe, 170, SpringLayout.WEST, btnAdvanceOptions);
        spring.putConstraint(SpringLayout.NORTH, btnSurpriseMe, 10, SpringLayout.SOUTH, btnToDisPast);

        spring.putConstraint(SpringLayout.WEST, btnBackToLast, 130, SpringLayout.WEST, btnSurpriseMe);
        spring.putConstraint(SpringLayout.NORTH, btnBackToLast, 10, SpringLayout.SOUTH, btnToDisPast);

        spring.putConstraint(SpringLayout.WEST, btnTrivia, 877, SpringLayout.WEST, contentPane);
        spring.putConstraint(SpringLayout.NORTH, btnTrivia, 7, SpringLayout.SOUTH, postEditCanvas);
    }

    private void addStyles() {
        btnToSave.setForeground(getSaveBtnColor());
        btnAdvanceOptions.setForeground(getAdvanceBtnColor());
        btnSurpriseMe.setForeground(getSurpriseBtnColor());

        labTitle.setFont(getLabelTitleFont());
        labOGImage.setFont(getImageLabelFont());
        labOGImage.setForeground(getImageLabelColor());
        labAFImage.setFont(getImageLabelFont());
        labAFImage.setForeground(getImageLabelColor());

        txtImageTitle.setFont(getImageTitleFont());
        txtImageTitle.setForeground(getImageTitleColor());
        txtImageTitle.setSize(450, 10);

        txtTrivia.setOpaque(false);
        txtTrivia.setBackground(Color.lightGray);
        txtTrivia.setSize(350, 200);
        txtTrivia.setWrapStyleWord(true);

        txtImageTitle.setOpaque(false);
        txtImageTitle.setBackground(Color.lightGray);
    }

    private void addToContentPane() {
        contentPane.setLayout(spring);
        contentPane.add(labTitle);
        contentPane.add(labStep1);
        contentPane.add(labOGImage);
        contentPane.add(labStep2);
        contentPane.add(labAFImage);
        contentPane.add(txtImageTitle);
        contentPane.add(labStep3);
        contentPane.add(btnUploadImage);
        contentPane.add(btnToDisPast);
        contentPane.add(btnToPast);
        contentPane.add(btnToDark);
        contentPane.add(btnToSave);
        contentPane.add(btnTutorial);
        contentPane.add(btnExamples);
        contentPane.add(btnBack2Present);
        contentPane.add(btnAdvanceOptions);
        contentPane.add(btnSurpriseMe);
        contentPane.add(btnToFuture);
        contentPane.add(btnToUnknown);
        contentPane.add(btnBackToLast);
        contentPane.add(btnTrivia);
        contentPane.add(preEditCanvas);
        contentPane.add(postEditCanvas);
        contentPane.add(homeCanvas);
        contentPane.add(txtTrivia);
        contentPane.add(btnSelectFromWeb);
    }

    private void setupClickHandlers() {
        btnUploadImage.addActionListener(this::actionPerformedUploadImage);
        btnToDisPast.addActionListener(this::actionPerformedDisPast);
        btnToPast.addActionListener(this::actionPerformedPast);
        btnToDark.addActionListener(this::actionPerformedDark);
        btnTutorial.addActionListener(this::actionPerformedTutorial);
        btnExamples.addActionListener(this::actionPerformedExamples);
        btnToSave.addActionListener(this::actionPerformedSave);
        btnBack2Present.addActionListener(this::actionPerformedBackPresent);
        btnAdvanceOptions.addActionListener(this::actionPerformedAdvanceOptions);
        btnSurpriseMe.addActionListener(this::actionPerformedSurpriseME);
        btnToFuture.addActionListener(this::actionPerformedFuture);
        btnToUnknown.addActionListener(this::actionPerformedUnknown);
        btnBackToLast.addActionListener(this::actionPerformedBackToLast);
        btnTrivia.addActionListener(this::actionPerformedTrivia);
        btnSelectFromWeb.addActionListener(this::actionPerformedSelectWeb);
    }

    private void configureJFrame() {
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setLocation(0, 0);
        frame.setBackground(Color.white);
        frame.setLayout(spring);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupHome() {
        // set up UI components
        frame.setVisible(true);//has to be placed at the end of setup

        btnTrivia.setVisible(false);

        homeCanvas.setImage(imageManager.getHomeImage());
        homeCanvas.repaint();
        homeCanvas.setVisible(true);

        preEditCanvas.setVisible(false);
        postEditCanvas.setVisible(false);
        txtImageTitle.setVisible(false);
        frame.setVisible(true);//has to be placed at the end of setup
    }

    private void uploadImage() {
        //upload image from user's device
        txtTrivia.setVisible(false);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) //user didn't choose cancel
        {
            String d = (fileDialog.getDirectory() + fileDialog.getFile());
            BufferedImage tempImage = imageManager.getImageResource().fileToImage(d);
            if (tempImage != null) {
                btnTrivia.setVisible(false);
                imageManager.setMasterImage(tempImage);
                displayNewImage();
            } else {
                errorMessage(null, 4); //tempImage == null, format not accepted
            }
        }
    }

    private void displayNewImage() {
        // setup when a new image is uploaded
        imageManager.processNewImage();

        preEditCanvas.setImage(scaleImage(imageManager.getCurrentImage(), 450, 450));
        preEditCanvas.repaint();
        postEditCanvas.setImage(imageManager.getBlankImage()); //blank image is the right size, scaling not necessary

        postEditCanvas.repaint();
        homeCanvas.setVisible(false);
        preEditCanvas.setVisible(true);
        postEditCanvas.setVisible(true);
        txtImageTitle.setVisible(false);
        labStep1.setText("Step 1: Select another photo: ");
    }

    private void toDisPast() {
        // Button "To the Distant Past" logic
        imageManager.setCurrentImage(toGrayScale(imageManager.getCurrentImage()));
        displayImage();
    }


    private void toPast() {
        // Button "To the Past" logic
        imageManager.setCurrentImage(toSepia(imageManager.getCurrentImage(), 10));
        displayImage();

    }

    private void toDark() {
        // Button "To the Dark Age" logic
        imageManager.setCurrentImage(toBlue(imageManager.getCurrentImage()));
        imageManager.setCurrentImage(toSepia(imageManager.getCurrentImage(), 10));
        displayImage();
    }

    private void displayTutorial() {
        // Button "Tutorial" logic
        JLabel picLabel = new JLabel(new ImageIcon(imageManager.getTutorialImage()));
        JOptionPane.showMessageDialog(null, picLabel, "Tutorial",
                JOptionPane.PLAIN_MESSAGE, null);
    }

    private void displayExamples() {
        // Button "Examples" logic
        JLabel picLabel = new JLabel(new ImageIcon(imageManager.getExampleImage()));
        JOptionPane.showMessageDialog(null, picLabel,
                "Filter Examples", JOptionPane.PLAIN_MESSAGE, null);
    }

    private void toSave() {
        // Button "Save Image" logic
        JFileChooser fileChooser = makeFileChooser();

        int returnVal = fileChooser.showSaveDialog(null); //get user input "save" or "cancel"

        if (returnVal == JFileChooser.APPROVE_OPTION) { //if the user chooses "save"
            if (!fileUtil.saveFile(fileChooser, imageManager)) {
                errorMessage(null, 3);
            } else {
                imageManager.resetNumOfEdits();
            }
        }
    }


    private JFileChooser makeFileChooser() {
        //dropdown menu for saved file formats
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG File", "png");
        fileChooser.addChoosableFileFilter(pngFilter);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG File", "jpg"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF File", "gif"));

        fileChooser.setFileFilter(pngFilter); // Default value of the dropdown menu: PNG
        fileChooser.setAcceptAllFileFilterUsed(false); //disable "all file"options
        return fileChooser;
    }

    private void toBackPresent() {
        // Button "Back to Present" logic
        imageManager.processNewImage();
        displayImage();
        txtImageTitle.setText("                  Back to the Present");
        txtImageTitle.setVisible(true);
        txtTrivia.setVisible(false);
    }

    private void advanceOptions() {
        // Button "Advance Options" logic
        String anchor = getCropData();
        if (anchor != null) {
            imageManager.setCurrentImage(cropImage(anchor, imageManager.getCurrentImage()));
            imageManager.addToStack(imageManager.getImageHistory().getStack().peek().getTitle());
            imageManager.getImageHistory().addTrivia(imageManager.getImageHistory().getStack().peek().getTrivia());
            displayImage();
            imageManager.getImageHistory().incrementEdits();
        }
    }

    private void toSurpriseMe() {
        // Button "Surprise Me" logic
        imageManager.masterToCurrent();
        imageManager.setCurrentImage(surpriseMe(imageManager.getCurrentImage()));
        displayImage();
    }

    private void toFuture() {
        // Button "To the Future" logic
        imageManager.setCurrentImage(toNeon(imageManager.getCurrentImage()));
        displayImage();
    }

    private void toUnknown() {
        // Button "To the Unknown" logic
        imageManager.setCurrentImage(toNegative(imageManager.getCurrentImage()));
        displayImage();

    }

    private void toBackToLast() {
        // Button "Back to the previous time period" logic
        imageManager.getImageHistory().popAStack();
        imageManager.setCurrentImage(imageManager.getImageHistory().getStack().peek().getImage());
        displayImage();

        txtImageTitle.setText("Traveled to: " + imageManager.getImageHistory().getStack().peek().getTitle());
        if (imageManager.getImageHistory().getStack().peek().getTrivia() != null) {
            txtTrivia.setText(imageManager.getImageHistory().getStack().peek().getTrivia());
            txtTrivia.setVisible(true);
        } else {
            txtTrivia.setVisible(false);
        }
        txtImageTitle.setVisible(true);
    }

    private void displayImage() {
        postEditCanvas.setImage(scaleImage(imageManager.getCurrentImage(), 450, 450));
        postEditCanvas.repaint();
    }


    private void socketService() throws IOException {
        ImageServiceClient serviceConnection = new ImageServiceClient();
        serviceConnection.startConnection("127.0.0.1", 4444);
        okHttp.sendGETSync(imageManager.getImageHistory().getStack().peek().getTitle(), 4);
        String result = serviceConnection.sendMessage("raining tacos");
        System.out.print(result);
    }

    private void imageFromTitleService() throws IOException {
        String result = okHttp.sendGETSync(imageManager.getImageHistory().getStack().peek().getTitle(), 4);
        System.out.print(result);
    }

    private String timePeriodService(String word) throws IOException {
        String response = (okHttp.sendGETSync(word, 1));
        System.out.println(response);
        txtImageTitle.setText("Traveled to: " + response);
        txtImageTitle.setVisible(true);
        btnTrivia.setVisible(true);
        return response;
    }

    private void triviaService(String word) {
        String response = null;
        try {
            response = (okHttp.sendGETSync(word, 2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        txtTrivia.setText(response);
        txtTrivia.setLineWrap(true);
        txtTrivia.setVisible(true);
        imageManager.getImageHistory().getStack().peek().setTrivia(response);
    }

    private void imageFromWebService() {
        try {
            String searchTerm = JOptionPane.showInputDialog("Enter a search term for image searching: ");
            if (searchTerm == null) //User pressed CANCEL
            {
                return;
            }
            String response = (okHttp.sendGETSync(searchTerm, 3));
            System.out.println(response);
            imageManager.setMasterImage(ImageIO.read(new URL(response)));
            displayNewImage();
        } catch (IOException ex) {
            errorMessage(ex, 2);
            imageFromWebService();
        }
    }

    private void errorMessage(Exception ex, int type) {
        String message = "";
        if (ex != null) {
            ex.printStackTrace();
        }
        switch (type) {
            case 0:
                message = "Please upload an image.";
                break;
            case 1:
                message = "Can't go back anymore!";
                break;
            case 2:
                message = "This search term doesn't work.";
                break;
            case 3:
                message = "Error, image is not saved.";
                break;
            case 4:
                message = "This photo doesn't work. Please select another one.";
                break;
            default:
                break;
        }
        JOptionPane.showMessageDialog(frame, message, "Image TiMeMaChInE", JOptionPane.ERROR_MESSAGE);
    }

    private int warning() {
        Object[] options = {"Cancel", "Yes, continue without saving"};
        return JOptionPane.showOptionDialog(frame,
                "You haven't saved your photo's time travel journey. Still want to continue?",
                "Warning - You haven't saved your image",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
    }

    private String getCropData() {
        Object[] possibilities = {"Center", "North", "East", "West", "South",
                "Northeast", "Northwest", "Southeast", "Southwest"};
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose an anchor to crop your image :        \n",
                "Grid Anchor Based Image Cropping",
                JOptionPane.PLAIN_MESSAGE, null, possibilities, "Center");
    }

    private void addStackAndTitle() {
        try {
            String imageTitle = timePeriodService(null);
            imageManager.addToStack(imageTitle);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean proceedWithoutSaving() {
        return !(imageManager.hasUnsavedProcess() && warning() == 0); // warning() != 0 : proceed
    }

    private boolean hasImage() {
        if (imageManager.isCurrentNull()) {
            errorMessage(null, 0);
            return false;
        }
        return true;
    }

    private boolean isHistoryEmpty() {
        int errorCode = imageManager.getImageHistory().isStackEmpty();
        if (errorCode == 0 || errorCode == 1) {
            errorMessage(null, errorCode);
            return true;
        }
        return false;
    }


    private void actionPerformedUploadImage(ActionEvent e) {
        if (proceedWithoutSaving()) {
            uploadImage();
        }
    }

    private void actionPerformedDisPast(ActionEvent e) {
        txtTrivia.setVisible(false);
        toDisPast();
        addStackAndTitle();
    }

    private void actionPerformedPast(ActionEvent e) {
        txtTrivia.setVisible(false);
        toPast();
        addStackAndTitle();
    }

    private void actionPerformedDark(ActionEvent e) {
        txtTrivia.setVisible(false);
        toDark();
        addStackAndTitle();
    }

    private void actionPerformedTutorial(ActionEvent e) {
        displayTutorial();
    }

    private void actionPerformedExamples(ActionEvent e) {
        displayExamples();
    }

    private void actionPerformedSave(ActionEvent e) {
        if (hasImage()) {
            toSave();
        }
    }

    private void actionPerformedBackPresent(ActionEvent e) {
        if (proceedWithoutSaving() && hasImage()) {
            toBackPresent();
        }
    }

    private void actionPerformedAdvanceOptions(ActionEvent e) {
        if (hasImage()) {
            advanceOptions();
        }
    }

    private void actionPerformedSurpriseME(ActionEvent e) {
        txtTrivia.setVisible(false);
        toSurpriseMe();
        addStackAndTitle();
    }

    private void actionPerformedFuture(ActionEvent e) {
        txtTrivia.setVisible(false);
        toFuture();
        addStackAndTitle();

    }

    private void actionPerformedUnknown(ActionEvent e) {
        txtTrivia.setVisible(false);
        toUnknown();
        addStackAndTitle();

    }

    private void actionPerformedBackToLast(ActionEvent e) {
        if (!isHistoryEmpty()) {
            toBackToLast();
        }
    }

    private void actionPerformedTrivia(ActionEvent e) {
        triviaService(null);
    }

    private void actionPerformedSelectWeb(ActionEvent e) {
        if (proceedWithoutSaving()) {
            imageFromWebService();
        }
    }
}
