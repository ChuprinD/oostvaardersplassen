package com.group3.utils;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

public class Util {

    private static Font liberationSansRegularFont;
    private static Font liberationSansBoldFont;

    static {
        liberationSansRegularFont = Font.loadFont(Util.class.getResourceAsStream("/fonts/LiberationSans-Regular.ttf"), 20);
        liberationSansBoldFont = Font.loadFont(Util.class.getResourceAsStream("/fonts/LiberationSans-Bold.ttf"), 20);
    }

    /**
     * Returns a regular font with the specified size.
     * The font used is "Liberation Sans Regular".
     * 
     * @param size the size of the font
     * @return a Font object with the specified size and regular style
     */
    public static Font getRegularFont(double size) {
        return Font.font(liberationSansRegularFont.getFamily(), size);
    }

    /**
     * Returns a bold font with the specified size.
     * The font used is "Liberation Sans Bold".
     * @param size the size of the font
     * @return a Font object with the specified size and bold style
     */
    public static Font getBoldFont(double size) {
        return Font.font(liberationSansBoldFont.getFamily(), size);
    }

    /**
     * Returns the font size of the regular text in the page, given as follows.
     * The font size is proportional to the width of the screen.
     * The base font size is 4.
     * @param screenWidth the width of the screen
     * @return the font size of the regular text in the page
     */
    public static int getRegularFontSize(double screenWidth) {
        int baseFontSize = 4;
        int fontSize = (int) (baseFontSize + screenWidth * 0.01);
        return fontSize;
    }
    
    /**
     * Returns the font size of the title text in the page, given as follows.
     * The font size is proportional to the width of the screen.
     * The base font size is 12.
     * @param screenWidth the width of the screen
     * @return the font size of the title text in the page
     */
    public static int getTitleFontSize(double screenWidth) {
        int baseFontSize = 12;
        int fontSize = (int) (baseFontSize + screenWidth * 0.01);
        return fontSize;
    }
    
    /**
     * Returns the font size of the button text, calculated based on the screen width.
     * The font size is proportional to the width of the screen.
     * The base font size is 4.
     * @param screenWidth the width of the screen
     * @return the font size of the button text
     */
    public static int getButtonFontSize(double screenWidth) {
        int baseFontSize = 4;
        int fontSize = (int) (baseFontSize + screenWidth * 0.01);
        return fontSize;
    }

    /**
     * Captures the current page displayed in the provided BorderPane as a PNG image.
     * The image is saved to the file system with the name "page_snapshot.png".
     * If the capture and save operation is successful, the method returns the path to the image.
     * In case of an error, it prints the stack trace and returns null.
     *
     * @param root the BorderPane containing the current page layout to capture
     * @return the file path of the saved image, or null if an error occurs
     */
    public static String capturePageAsImage(BorderPane root) {
        try {
            WritableImage snapshot = root.snapshot(null, null);
            String imagePath = "page_snapshot.png";
            File file = new File(imagePath);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);

            return imagePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Exports a given image file to a PDF document. The method uses the specified image path
     * to read the image, scales it to fit the specified window width, and creates a PDF document
     * at the provided PDF path. After successfully writing the PDF, the original image file is deleted.
     * Any errors during the process are caught and printed to the stack trace.
     *
     * @param imagePath the file path of the image to be converted to a PDF
     * @param pdfPath the file path where the resulting PDF should be saved
     * @param windowWidth the width of the window, used to scale the image in the PDF
     */
    public static void exportToPDF(String imagePath, String pdfPath, double windowWidth) {
        try {

            ImageData imageData = ImageDataFactory.create(imagePath);
            float imageWidth = (float) windowWidth;
            float imageHeight = imageData.getHeight();

            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.setDefaultPageSize(new PageSize(imageWidth, imageHeight));
            
            Document document = new Document(pdfDoc);
            document.setMargins(0, 0, 0, 0);

            Image pageImage = new Image(ImageDataFactory.create(imagePath));
            pageImage.setAutoScale(true);
            document.add(pageImage);

            document.close();
             
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (!deleted) {
                    System.err.println("Failed to delete the image file: " + imagePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a file chooser dialog for the user to select a file to save to.
     * The file chooser is pre-set to only allow files with the given extension to be selected.
     * @param extensionDescription a human-readable description of the file extension (e.g. "PDF files")
     * @param extension the extension itself (e.g. "*.pdf")
     * @return the file chosen by the user, or null if the dialog was cancelled
     */
    public static File chooseFile(String extensionDescription, String extension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(extensionDescription, extension));
        return fileChooser.showSaveDialog(null);
    }
    
    /**
     * Calculates and returns the maximum width of a list of text items when rendered
     * on the screen with a specified font size. The maximum width is influenced by
     * the font size and the width of the screen.
     *
     * @param items an array of strings representing the text items to be measured
     * @param fontSize the font size used for rendering the text
     * @param windowWidth the width of the window, used to add a proportional padding to the calculated width
     * @return the maximum width of the text items plus a small proportional padding based on the window width
     */
    public static double getMaxItemWidth(String[] items, int fontSize, double windowWidth) {
        Text textHelper = new Text();
        textHelper.setFont(Util.getBoldFont(fontSize)); 
        double maxWidth = 0;

        for (String item : items) {
            textHelper.setText(item);
            double width = textHelper.getBoundsInLocal().getWidth(); 
            maxWidth = Math.max(maxWidth, width); 
        }

        return maxWidth + windowWidth * 0.025;
    }

}

