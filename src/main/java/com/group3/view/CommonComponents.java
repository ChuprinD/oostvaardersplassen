package com.group3.view;

import com.group3.controller.NavigationController;
import com.group3.utils.Util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CommonComponents {
    private ScrollPane scrollPane;
    private boolean isItMain = false;
    private double sectionAboutPreservePosition;
    private double sectionAboutAnimalsPosition;
    private double windowWidth;
    private double windowHeight;

    public CommonComponents(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public CommonComponents(double windowWidth, double windowHeight, boolean isItMain, ScrollPane scrollPane,
            double sectionAboutPreservePosition, double sectionAboutAnimalsPosition) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.isItMain = isItMain;
        this.scrollPane = scrollPane;
        this.sectionAboutPreservePosition = sectionAboutPreservePosition;
        this.sectionAboutAnimalsPosition = sectionAboutAnimalsPosition;
    }

    /**
     * Creates the header bar at the top of the application window. The header
     * bar contains the logo, navigation buttons, and a dropdown menu for
     * navigating to different pages.
     *
     * @param pageName the name of the page to be displayed in the header
     * @param navigationController the object that handles navigation
     * @return the header bar as an HBox
     */
    public HBox createHeader(String pageName, NavigationController navigationController) {
        HBox leftBox = createLogoSection();
        HBox centerBox = createButtons(navigationController);
        HBox rightBox = createDropdownMenu(navigationController);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #a8c28c;");
        header.setMinHeight(windowHeight * 0.07);
        header.setMinWidth(windowWidth - 2 * windowWidth * 0.01);
        header.setMaxWidth(windowWidth - 2 * windowWidth * 0.01);
        header.getChildren().addAll(leftBox, centerBox, rightBox);

        return header;
    }

    /**
     * Creates a logo section for the header containing an image.
     * The image is scaled to fit a specific height while preserving its aspect ratio.
     * The logo section is left-aligned within its container.
     *
     * @return An HBox containing the logo image.
     */
    private HBox createLogoSection() {
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/Wolf1e4c40Opacity65.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.07);
        imageView.setPreserveRatio(true);

        HBox leftBox = new HBox(imageView);
        leftBox.setMinWidth(windowWidth / 3);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPadding(new Insets(0, 0, 0, windowWidth * 0.07));

        return leftBox;
    }

    
    /**
     * Creates a section containing navigation buttons for the header bar.
     * The section is centered within its container and takes up one-third of the window width.
     * Each button is created with a width and height proportional to the window size and a font size
     * based on the window width. The buttons are also styled with a bold font and a background color.
     *
     * @param navigationController the object that handles navigation
     * @return the section containing the navigation buttons
     */
    private HBox createButtons(NavigationController navigationController) {
        double buttonWidth = windowWidth * 0.06;
        double buttonHeight = windowHeight * 0.03;
        int fontSize = Util.getButtonFontSize(windowWidth, windowHeight);

        Button homeButton = createButton("Home", fontSize, buttonWidth, buttonHeight, e -> navigateToHome(navigationController));
        Button aboutButton = createButton("About", fontSize, buttonWidth, buttonHeight, e -> navigateToAbout(navigationController));
        Button speciesButton = createButton("Species", fontSize, buttonWidth, buttonHeight, e -> navigateToSpecies(navigationController));

        HBox centerBox = new HBox(windowWidth * 0.02, homeButton, aboutButton, speciesButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setMinWidth(windowWidth / 3);

        return centerBox;
    }

    
    /**
     * Creates a button with the given text, font size, width, height, and action.
     * The button is also styled with a bold font and a background color.
     * The button is also set to not be focus traversable.
     *
     * @param text the text to display on the button
     * @param fontSize the font size of the button text
     * @param width the width of the button
     * @param height the height of the button
     * @param action the action to take when the button is clicked
     * @return the button
     */
    private Button createButton(String text, int fontSize, double width, double height, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setMinSize(width, height);
        button.setStyle("-fx-font-size: " + fontSize + "px;" +
                        "-fx-font-family: " + Util.getBoldFont(fontSize).getFamily() + ";" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;" +
                        "-fx-border-width: 2px;" +
                        "-fx-background-color: #1e4c40;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        button.setOnAction(action);
        button.setFocusTraversable(false);
        return button;
    }

    /**
     * Navigates to the home page if the navigation is not already on the home page.
     * If the navigation is on the home page, scrolls to the top of the page.
     * @param navigationController the object that handles navigation
     */

    private void navigateToHome(NavigationController navigationController) {
        if (isItMain) {
            scrollToPosition(0);
        } else {
            navigationController.showPage("Home");
        }
    }

    /**
     * Navigates to the about page if the navigation is not already on the about page.
     * If the navigation is on the about page, scrolls to the section about the preserve.
     * @param navigationController the object that handles navigation
     */
    private void navigateToAbout(NavigationController navigationController) {
        if (isItMain) {
            scrollToPosition(sectionAboutPreservePosition);
        } else {
            navigationController.showPage("Home.About");
        }
    }

    /**
     * Navigates to the species page if the navigation is not already on the species page.
     * If the navigation is on the species page, scrolls to the section about the animals.
     * @param navigationController the object that handles navigation
     */
    private void navigateToSpecies(NavigationController navigationController) {
        if (isItMain) {
            scrollToPosition(sectionAboutAnimalsPosition);
        } else {
            navigationController.showPage("Home.Animals");
        }
    }

    /**
     * Scrolls the scrollpane to a specific position within its content.
     * @param targetPosition the vertical position within the content to scroll to
     */
    private void scrollToPosition(double targetPosition) {
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double targetValue = targetPosition / contentHeight;
        double currentValue = scrollPane.getVvalue();

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(scrollPane.vvalueProperty(), currentValue)),
            new KeyFrame(Duration.seconds(1), new KeyValue(scrollPane.vvalueProperty(), targetValue))
        );
        timeline.play();
    }

    /**
     * Creates a dropdown menu for the header bar, allowing navigation to different pages.
     * The menu includes options such as "Home", "Herbivore Competition", "Predator-Prey Dynamics",
     * "Species-Specific Reactions", and "Climate and Population Trends". The menu is styled with
     * a specific font, background color, and size, and it triggers navigation actions upon selection.
     * 
     * @param navigationController the object that handles navigation between different pages.
     * @return An HBox containing the styled dropdown menu.
     */
    private HBox createDropdownMenu(NavigationController navigationController) {
        ComboBox<String> dropdownMenu = new ComboBox<>();
        dropdownMenu.setFocusTraversable(false);
        dropdownMenu.getItems().addAll(
            "Home", "Herbivore Competition", "Predator-Prey Dynamics", "Species-Specific Reactions", "Climate and Population Trends"
        );
        dropdownMenu.setOnAction(e -> navigationController.showPage(dropdownMenu.getValue()));
        dropdownMenu.setValue("Choose Analysis Focus");

        int fontSize = Util.getButtonFontSize(windowWidth, windowHeight);
        dropdownMenu.setStyle("-fx-font-size: " + fontSize + "px;" +
                              "-fx-font-family: " + Util.getBoldFont(fontSize).getFamily() + ";" +
                              "-fx-text-fill: white;" +
                              "-fx-background-radius: 6;" +  
                              "-fx-border-radius: 6;" +     
                              "-fx-border-width: 2;" +
                              "-fx-background-color: #1e4c40;" +
                              "-fx-alignment: center;");

        dropdownMenu.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(buildLayout(item, fontSize));
                    setStyle("-fx-background-color: #1e4c40; -fx-font-weight: normal; -fx-text-fill: white;");
                }
            }
        });

        dropdownMenu.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setAlignment(Pos.CENTER);
                setTextFill(Color.WHITE);
                setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
                setText(item);
            }
        });

        dropdownMenu.setMinSize(windowWidth * 0.06, windowHeight * 0.03);

        Platform.runLater(() -> {
            dropdownMenu.show();
            dropdownMenu.hide();
        });


        HBox rightBox = new HBox(dropdownMenu);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMinWidth(windowWidth / 3);

        return rightBox;
    }

    /**
     * Builds a layout for a dropdown menu item containing a label with the given text.
     * The layout is styled to have a white font, a background color of #82755b, and a
     * font size of the given fontSize. The label is centered and has a maximum width of
     * Double.MAX_VALUE, and the text is wrapped if it exceeds the maximum width.
     * @param text the text to be displayed in the label
     * @param fontSize the font size of the label
     * @return a VBox containing the label with the given text
     */
    private VBox buildLayout(String text, int fontSize) {
        Label labelName = new Label(text);
        labelName.setStyle("-fx-text-fill: white;" +
                           "-fx-font-size: " + fontSize + "px;" +
                           "-fx-font-family: " + Util.getRegularFont(fontSize).getFamily() + ";" +
                           "-fx-background-color: #82755b;" +
                           "-fx-background-radius: 6;" +
                           "-fx-font-weight: normal;" +
                           "-fx-padding: 6 6 6 6;");
        labelName.setWrapText(true);
        labelName.setMaxWidth(Double.MAX_VALUE);
        labelName.setAlignment(Pos.CENTER);
        labelName.setTextAlignment(TextAlignment.CENTER);

        VBox pane = new VBox(labelName);
        VBox.setVgrow(pane, Priority.ALWAYS);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(6, 12, 6, 7));
        return pane;
    }

    
    /**
     * Displays a confirmation dialog with the given title and message.
     * The dialog displays a default OK and Cancel button, and will wait for
     * the user to close it before allowing further interaction with the application.
     * @param title the title to display in the dialog
     * @param message the message to display in the dialog
     * @return true if the user clicked OK, false if the user clicked Cancel
     */
    public static boolean showConfirmationDialog(String title, String message, double windowWidth, double windowHeight) {
        // Create a new stage for the dialog
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        // Create the dialog content
        VBox dialogContent = new VBox(windowHeight * 0.02);
        dialogContent.setAlignment(Pos.CENTER);
        dialogContent.setStyle("-fx-background-color: #ffffff;");

        // Add a title to the dialog
        Label dialogTitle = new Label(message);
        dialogTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        dialogTitle.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
        dialogTitle.setWrapText(true);
        dialogTitle.setAlignment(Pos.CENTER);
        dialogTitle.setTextAlignment(TextAlignment.CENTER);

        // Create "Yes" and "No" buttons
        Button yesButton = new Button("YES");
        yesButton.setStyle("-fx-background-color: #1E4C40;" +
                           "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                           "-fx-text-fill: white;" +
                           "-fx-font-size: 10px;" +
                           "-fx-font-weight: bold;" +
                           " -fx-padding: 10;"+
                           "-fx-background-radius: 5;");
        yesButton.setMinWidth(windowWidth * 0.04);
        yesButton.setMaxWidth(windowWidth * 0.04);

        Button noButton = new Button("NO");
        noButton.setStyle("-fx-background-color: #E07A5F;" +
                          "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                          "-fx-text-fill: white;" +
                          "-fx-font-size: 10px;" +
                          "-fx-font-weight: bold;" +
                          " -fx-padding: 10;"+
                          "-fx-background-radius: 5;");
        noButton.setMinWidth(windowWidth * 0.04);
        noButton.setMaxWidth(windowWidth * 0.04);

        // Add button actions
        final boolean[] userChoice = {false}; // To capture the user's choice
        yesButton.setOnAction(e -> {
            userChoice[0] = true;
            dialog.close();
        });
        noButton.setOnAction(e -> {
            userChoice[0] = false;
            dialog.close();
        });

        // Arrange buttons in an HBox
        HBox buttonBox = new HBox(20, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add title and buttons to the VBox
        dialogContent.getChildren().addAll(dialogTitle, buttonBox);

        // Create a Scene with a custom layout
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); // Semi-transparent background
        root.getChildren().add(dialogContent);
        Scene scene = new Scene(root, 300, 150);

        // Add rounded rectangle for visual styling
        Rectangle background = new Rectangle(300, 150);
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.WHITE);
        root.getChildren().add(0, background);

        // Set the dialog scene
        dialog.setScene(scene);
        dialog.showAndWait();

        // Return the user's choice
        return userChoice[0];
    }

    /**
     * Displays an information alert with the specified title and message.
     * The alert is blocking, meaning it will wait for the user to close it
     * before allowing further interaction with the application.
     *
     * @param title the title of the alert dialog
     * @param message the message content of the alert dialog
     */
    public static void showNotification(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
    
        // Show the notification
        alert.showAndWait();
    }

    /**
     * Sets the vertical positions for the sections "About Animals" and "About Preserve".
     * This method updates the stored positions for these sections based on the provided
     * parameters.
     *
     * @param sectionAboutAnimalsPosition The vertical position for the "About Animals" section.
     * @param sectionAboutPreservePosition The vertical position for the "About Preserve" section.
     */
    public void setSectionsPosition(double sectionAboutAnimalsPosition, double sectionAboutPreservePosition) {
        this.sectionAboutAnimalsPosition = sectionAboutAnimalsPosition;
        this.sectionAboutPreservePosition = sectionAboutPreservePosition;
    }
}
