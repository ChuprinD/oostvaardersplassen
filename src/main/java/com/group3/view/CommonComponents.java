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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CommonComponents {
    private ScrollPane scrollPane;
    private boolean isItMain = false;
    private HBox sectionAboutPreservePosition;
    private HBox sectionAboutAnimalsPosition;
    private double windowWidth;
    private double windowHeight;

    private double headerHeight;

    public CommonComponents(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        headerHeight = windowHeight * 0.07;
    }

    public CommonComponents(double windowWidth, double windowHeight, boolean isItMain, ScrollPane scrollPane,
            HBox sectionAboutPreservePosition, HBox sectionAboutAnimalsPosition) {
        this(windowWidth, windowHeight);
        this.isItMain = isItMain;
        this.scrollPane = scrollPane;
        this.sectionAboutPreservePosition = sectionAboutPreservePosition;
        this.sectionAboutAnimalsPosition = sectionAboutAnimalsPosition;
    }

    /**
     * Creates the header section at the top of the application window. The header
     * consists of a left section with the logo, a center section with navigation
     * buttons, and a right section with a dropdown menu.
     *
     * @param pageName the name of the page
     * @param navigationController the navigation controller
     * @param horizontalGap the horizontal gap between the header and the content
     * @return the header as an HBox
     */
    public HBox createHeader(String pageName, NavigationController navigationController, double horizontalGap) {
        HBox leftBox = createLogoSection();
        HBox centerBox = createButtons(navigationController);
        HBox rightBox = createDropdownMenu(navigationController);
        rightBox.setTranslateX(-windowWidth * 0.03);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #a8c28c;");
        header.setMinHeight(headerHeight);
        header.setMaxHeight(headerHeight);
        header.setMinWidth(windowWidth - 2 * horizontalGap);
        header.setMaxWidth(windowWidth - 2 * horizontalGap);
        header.getChildren().addAll(leftBox, centerBox, rightBox);

        return header;
    }

    /**
     * Creates the footer section at the bottom of the application window.
     * The footer consists of an image centered horizontally, with padding 
     * applied to the top and bottom. The image is scaled to maintain its 
     * aspect ratio and fits a specific height relative to the window height.
     *
     * @return the footer as an HBox containing the centered image
     */
    public HBox createFooter() {
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/logos/WhiteWolf.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.1);
        imageView.setPreserveRatio(true);

        HBox bottomSpacer = new HBox(imageView);
        bottomSpacer.setAlignment(Pos.BOTTOM_CENTER);
        bottomSpacer.setPadding(new Insets(windowHeight * 0.02, 0, windowHeight * 0.02, 0));
        return bottomSpacer;
    }

    /**
     * Creates the left section of the header containing the Wolf logo.
     * The logo is centered horizontally and scaled to fit the header height.
     * The section is styled with a specific width and padding to the right.
     *
     * @return the left section of the header as an HBox
     */
    private HBox createLogoSection() {
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/logos/Wolf1e4c40Opacity65.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(headerHeight);
        imageView.setPreserveRatio(true);

        HBox leftBox = new HBox(imageView);
        leftBox.setMinHeight(headerHeight);
        leftBox.setMaxHeight(headerHeight);
        leftBox.setMinWidth(windowWidth / 3);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPadding(new Insets(0, 0, 0, windowWidth * 0.07));

        return leftBox;
    }

    /**
     * Creates the center section of the header containing buttons for navigation.
     * The buttons are centered horizontally and styled with specific fonts, colors, and dimensions.
     * The section is styled with a specific width and padding to the right.
     *
     * @param navigationController the object that handles navigation between different pages.
     * @return the center section of the header as an HBox
     */
    private HBox createButtons(NavigationController navigationController) {
        double buttonWidth = windowWidth * 0.06;
        double buttonHeight = windowHeight * 0.03;
        int fontSize = Util.getButtonFontSize(windowWidth);

        Button homeButton = createButton("Home", fontSize, buttonWidth, buttonHeight, e -> navigateToHome(navigationController));
        Button aboutButton = createButton("About", fontSize, buttonWidth, buttonHeight, e -> navigateToAbout(navigationController));
        Button speciesButton = createButton("Species", fontSize, buttonWidth, buttonHeight, e -> navigateToSpecies(navigationController));

        HBox centerBox = new HBox(windowWidth * 0.02, homeButton, aboutButton, speciesButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setMinWidth(windowWidth / 3);
        centerBox.setMinHeight(headerHeight);
        centerBox.setMaxHeight(headerHeight);

        return centerBox;
    }

    /**
     * Creates a styled button with specified text, font size, dimensions, and action handler.
     * The button is styled with a bold font, rounded corners, and specific color settings.
     * It is intended to be used within a navigation header or other UI components.
     *
     * @param text The text to be displayed on the button.
     * @param fontSize The font size of the button text.
     * @param width The width of the button.
     * @param height The height of the button.
     * @param action The action to be executed when the button is clicked.
     * @return A Button object with the specified properties and styling.
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
            scrollToPosition(0, 0);
        } else {
            navigationController.showPage("Home");
        }
    }
    
    /**
     * Navigates to the "About" section of the home page. If the current 
     * page is the main page, it scrolls to the position of the "About" 
     * section. Otherwise, it navigates to the "Home.About" page.
     * 
     * @param navigationController the object that handles navigation
     */
    private void navigateToAbout(NavigationController navigationController) {
        if (isItMain) {
            scrollToPosition(sectionAboutPreservePosition.getBoundsInParent().getMinY(), sectionAboutPreservePosition.getBoundsInLocal().getHeight());
        } else {
            navigationController.showPage("Home.About");
        }
    }

    /**
     * Navigates to the "Animals" section of the home page. If the current 
     * page is the main page, it scrolls to the position of the "Animals" 
     * section. Otherwise, it navigates to the "Home.Animals" page.
     * 
     * @param navigationController the object that handles navigation
     */
    private void navigateToSpecies(NavigationController navigationController) {
        if (isItMain) {
            scrollToPosition(sectionAboutAnimalsPosition.getBoundsInParent().getMinY(), sectionAboutAnimalsPosition.getBoundsInLocal().getHeight());
        } else {
            navigationController.showPage("Home.Animals");
        }
    }

    /**
     * Scrolls the scrollPane to the given target position, with a smooth animation
     * over 1 second. The target position is given as a y-coordinate from the top of
     * the content area. The targetSectionSize parameter is the height of the section
     * to scroll to, and is used to calculate the center of the section within the
     * viewport.
     * @param targetPosition the y-coordinate of the target position to scroll to
     * @param targetSectionSize the height of the section to scroll to
     */
    private void scrollToPosition(double targetPosition, double targetSectionSize) {
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();

        double targetCenterY = targetPosition - (viewportHeight / 2) + (targetSectionSize / 2) - headerHeight / 2;
        
        double targetValue = targetCenterY / (contentHeight - viewportHeight);
        targetValue = Math.max(0, Math.min(1, targetValue));
        double currentValue = scrollPane.getVvalue();
         
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(scrollPane.vvalueProperty(), currentValue)),
            new KeyFrame(Duration.seconds(1), new KeyValue(scrollPane.vvalueProperty(), targetValue))
        );
        timeline.play();
    }

    /**
     * Creates a dropdown menu for navigation between different pages of the application.
     * The dropdown menu includes options for "Home", "Herbivore Competition", "Predator-Prey Dynamics",
     * and "Total Simulation". When an option is selected, the corresponding page is displayed.
     * The dropdown is styled with specific fonts, colors, and dimensions to fit the application's theme.
     * 
     * @param navigationController the controller responsible for handling navigation between pages.
     * @return an HBox containing the styled dropdown menu.
     */
    private HBox createDropdownMenu(NavigationController navigationController) {
        ComboBox<String> dropdownMenu = new ComboBox<>();
        dropdownMenu.setFocusTraversable(false);
        String[] dropdownElements = {"Home", "Herbivore Competition", "Predator-Prey Dynamics", "Total Simulation"};
        dropdownMenu.getItems().addAll(dropdownElements);
        dropdownMenu.setOnAction(e -> navigationController.showPage(dropdownMenu.getValue()));
        dropdownMenu.setValue("Choose Analysis Focus");

        int fontSize = Util.getButtonFontSize(windowWidth);
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
                    VBox currentVBox = buildLayout(item, fontSize);
                    setGraphic(currentVBox);
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

        double dropdownMenuWidth = Util.getMaxItemWidth(dropdownElements, fontSize, windowWidth);

        dropdownMenu.setMinWidth(dropdownMenuWidth);

        Platform.runLater(() -> {
            dropdownMenu.show();
            dropdownMenu.hide();
        });


        HBox rightBox = new HBox(dropdownMenu);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMinWidth(windowWidth / 3);
        rightBox.setMinHeight(headerHeight);
        rightBox.setMaxHeight(headerHeight);

        return rightBox;
    }
    
    /**
     * Creates a VBox with a centered Label that displays the given text in
     * the given font size. The Label is styled with a light brown background,
     * white text, and a rounded corner. The VBox is set to expand to fill its
     * parent container.
     * @param text the text to display in the Label
     * @param fontSize the font size of the Label
     * @return a VBox containing the styled Label
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
     * Displays a confirmation dialog with "Yes" and "No" options, allowing the user to make a choice.
     * The dialog is styled with a custom layout and appears as a modal window.
     * 
     * @param title       The title of the confirmation dialog.
     * @param message     The message displayed inside the dialog.
     * @param windowWidth The width of the window for styling purposes.
     * @param windowHeight The height of the window for styling purposes.
     * @return true if the user selects "Yes", false if the user selects "No".
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
        dialogTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        dialogTitle.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth)));
        dialogTitle.setWrapText(true);
        dialogTitle.setAlignment(Pos.CENTER);
        dialogTitle.setTextAlignment(TextAlignment.CENTER);

        // Create "Yes" and "No" buttons
        Button yesButton = new Button("YES");
        yesButton.setStyle("-fx-background-color: #1E4C40;" +
                           "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                           "-fx-text-fill: white;" +
                           "-fx-font-size: 14px;" +
                           "-fx-font-weight: bold;" +
                           " -fx-padding: 10;"+
                           "-fx-background-radius: 5;");
        yesButton.setMinWidth(windowWidth * 0.04);
        yesButton.setMaxWidth(windowWidth * 0.04);

        Button noButton = new Button("NO");
        noButton.setStyle("-fx-background-color: #E07A5F;" +
                          "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                          "-fx-text-fill: white;" +
                          "-fx-font-size: 14px;" +
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
        root.setStyle("-fx-background-color: #ffffff;");
        root.getChildren().add(dialogContent);
        root.setPadding(new Insets(20, 20, 20, 20));
        Scene scene = new Scene(root);

        // Set the dialog scene
        dialog.getIcons().add(new Image(NavigationController.class.getResourceAsStream("/images/icons/main-logo.png")));
        dialog.setScene(scene);
        dialog.showAndWait();

        // Return the user's choice
        return userChoice[0];
    }

    
    /**
     * Displays an informational notification popup with a given title and message.
     * The notification is styled as an Alert dialog of type INFORMATION and
     * requires the user to dismiss it manually.
     *
     * @param title   The title of the notification dialog.
     * @param message The message content displayed in the notification dialog.
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
     * Sets the positions for the sections about the preserve and animals.
     *
     * @param sectionAboutPreservePosition The HBox representing the position of the section about the preserve.
     * @param sectionAboutAnimalsPosition The HBox representing the position of the section about animals.
     */
    public void setSectionsPosition(HBox sectionAboutPreservePosition, HBox sectionAboutAnimalsPosition) {
        this.sectionAboutAnimalsPosition = sectionAboutAnimalsPosition;
        this.sectionAboutPreservePosition = sectionAboutPreservePosition;
    }

    /**
     * Sets the scroll pane that is being used to display the content of the page.
     * This is used to scroll the user to a specific section of the page.
     * @param scrollPane The ScrollPane that is being used to display the content of the page.
     */
    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Returns the height of the header as a double value.
     * 
     * @return The height of the header.
     */
    public double getHeaderHeight() {
        return headerHeight;
    }
}
