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
     * 
     * @param size the size of the font
     * @return a Font object with the specified size and bold style
     */
    public static Font getBoldFont(double size) {
        return Font.font(liberationSansBoldFont.getFamily(), size);
    }

    /**
     * Returns the font size of the regular font in the page, given as follows.
     * The font size is proportional to the minimum of the screen width and
     * screen height. The base font size is 16.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return the font size of the regular font in the page
     */
    public static int getRegularFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 14;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
    
    /**
     * Returns the font size of the title in the page, given as follows.
     * The font size is proportional to the minimum of the screen width and
     * screen height. The base font size is 30.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return the font size of the title in the page
     */
    public static int getTitleFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 30;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }
    
    /**
     * Returns the font size of the button in the page, given as follows.
     * The font size is proportional to the minimum of the screen width and
     * screen height. The base font size is 10.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return the font size of the button in the page
     */
    public static int getButtonFontSize(double screenWidth, double screenHeight) {
        int baseFontSize = 10;
        int fontSize = (int) (baseFontSize + Math.min(screenWidth, screenHeight) * 0.01);
        return fontSize;
    }

    /**
     * Captures the given page as a PNG image and saves it to a file named
     * "page_snapshot.png". The image is saved to the same directory as the
     * current Java application. The method returns the path of the saved image file
     * if successful, or null if an error occurs.
     * 
     * @param root the root of the page to capture
     * @return the path of the saved image file, or null if an error occurs
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
     * Exports the current page as a PDF to the specified path.
     * 
     * @param imagePath the path to the image of the page to export
     * @param pdfPath the path to export the PDF to
     * @param windowWidth the width of the window
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
     * Opens a file chooser dialog for the user to select a file to save.
     * The dialog is preconfigured with the given extension description and
     * extension, and will only show files with that extension.
     * The method returns the selected file, or null if the dialog was cancelled.
     * 
     * @param extensionDescription a description of the file extension, e.g. "PNG images"
     * @param extension the file extension to filter by, e.g. "*.png"
     * @return the selected file, or null if the dialog was cancelled
     */
    public static File chooseFile(String extensionDescription, String extension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(extensionDescription, extension));
        return fileChooser.showSaveDialog(null);
    }
    
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

