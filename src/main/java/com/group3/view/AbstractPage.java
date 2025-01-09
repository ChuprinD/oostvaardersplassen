package com.group3.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;

import java.io.File;

import javax.swing.SwingUtilities;

import com.group3.controller.NavigationController;
import com.group3.mathModels.MathModel;
import com.group3.utils.Util;

public abstract class AbstractPage implements Page {
    protected final String pageName;
    protected final String graphTitle;
    protected final NavigationController navigationController;
    protected final double windowWidth;
    protected final double windowHeight;

    protected BorderPane root; // Main root element
    protected VBox descriptionSection; // Description section of the page
    protected VBox graphSection; // Graph section of the page
    protected ScrollPane scrollPane; // Scrollable area

    private final double topBarHeight = 23;

    public AbstractPage(String pageName, String graphTitle, NavigationController navigationController) {
        this.pageName = pageName;
        this.navigationController = navigationController;
        this.graphTitle = graphTitle;

        // Determine screen dimensions
        this.windowWidth = Screen.getPrimary().getVisualBounds().getWidth();
        this.windowHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Create the main layout
        this.root = new BorderPane();

        setupPage();
    }

    /**
     * Sets up the page layout by creating the page header, main content area, and
     * scrollable area. The page header is a bar at the top of the page that contains
     * the logo and navigation buttons. The main content area is where the graph and
     * description sections are placed. The scrollable area is a window that can be
     * scrolled to view more content than can fit on the screen.
     */
    private void setupPage() {
        double verticalGapBetweenSections = windowHeight * 0.03;
        double horizontalGap = windowWidth * 0.02;
        // Page header
        CommonComponents commonComponents = new CommonComponents(windowWidth, windowHeight);
        HBox header = commonComponents.createHeader(pageName, navigationController, horizontalGap);
        HBox headerContainer = new HBox(header);
        headerContainer.setMinWidth(windowWidth);
        headerContainer.setMaxWidth(windowWidth);
        headerContainer.setMaxHeight(commonComponents.getHeaderHeight());
        headerContainer.setMinHeight(commonComponents.getHeaderHeight());
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setStyle("-fx-background-color: transparent;");

        // Main content area
        VBox mainContent = new VBox(verticalGapBetweenSections);
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #1e4c40, #a8c28c); -fx-border-width: 0; -fx-border-color: transparent;");
        mainContent.setMinWidth(windowWidth);
        mainContent.setMaxWidth(windowWidth);
        mainContent.setPadding(new Insets(0, horizontalGap, 0, horizontalGap));
        
        // Add sections
        graphSection = createSectionGraph();
        descriptionSection = createSectionDescription();

        Region spacer = new Region();
        spacer.setMinHeight(commonComponents.getHeaderHeight());
        spacer.setMaxHeight(commonComponents.getHeaderHeight());
        spacer.setStyle("-fx-background-color: transparent;");

        HBox footer = commonComponents.createFooter();

        mainContent.getChildren().addAll(spacer, graphSection, descriptionSection, footer);

        // Scrollable area
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(mainContent);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: transparent; -fx-border-width: 0; -fx-border-color: transparent; -fx-padding: 0;");
        scrollPane.setMaxWidth(windowWidth);
        scrollPane.setMinWidth(windowWidth);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().addAll(scrollPane, headerContainer);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setMaxWidth(windowWidth);
        stackPane.setMinWidth(windowWidth);

        // Place elements on the page
        root.setCenter(stackPane);
    }

    
    protected abstract VBox createSectionGraph();
    protected abstract VBox createSectionDescription();

    /**
     * Creates a section of the page containing a graph and buttons to download the graph and
     * view the description of the graph. The section is visually structured with a title
     * at the top, the graph in the middle, and the buttons at the bottom. The section is
     * styled with specific fonts, colors, and dimensions, and is intended to be part of the
     * main page layout.
     *
     * @param title The title of the section
     * @param graphGenerator The math model that generates the graph
     * @return A VBox containing the styled title, graph, and buttons representing the
     *         section of the page.
     */
    protected VBox createSectionGraph(String title, MathModel graphGenerator) {
        // Title for the graph section
        Label graphTitle = new Label(this.graphTitle);
        graphTitle.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-text-fill: #000000;");
        graphTitle.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
        graphTitle.setAlignment(Pos.TOP_CENTER);

        // Graph 
        StackPane graphContainer = new StackPane();
        graphContainer.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 6; -fx-background-radius: 6;");
        graphContainer.setPadding(new Insets(5));
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(graphGenerator.getGraph( windowWidth * 0.6, windowHeight * 0.5));
        });
        graphContainer.getChildren().add(swingNode);
        graphContainer.setMaxWidth(windowWidth * 0.9);
        graphContainer.setOnMouseClicked(event -> openGraphPopup(title, graphGenerator));
        swingNode.setOnMouseClicked(event -> openGraphPopup(title, graphGenerator));

        // Info button ("i")
        Image imageInfoButton = new Image(CommonComponents.class.getResourceAsStream("/images/icons/infoButton.png"));
        ImageView infoButton = new ImageView(imageInfoButton);
        infoButton.setFitHeight(windowHeight * 0.04);
        infoButton.setFitWidth(windowWidth * 0.04);
        infoButton.setPreserveRatio(true);
        infoButton.setOnMouseClicked(e -> {
            javafx.application.Platform.runLater(() -> {
                scrollToDescription();
            });
        });

        // Download button
        Image imageDownloadButton = new Image(CommonComponents.class.getResourceAsStream("/images/icons/downloadButton.png"));
        ImageView downloadButton = new ImageView(imageDownloadButton);
        downloadButton.setFitHeight(windowHeight * 0.04);
        downloadButton.setFitWidth(windowWidth * 0.04);
        downloadButton.setPreserveRatio(true);
        downloadButton.setOnMouseClicked(e -> {
            handleDownloadAction(this.graphTitle);
        });

        // Settings button
        Image imageSettingsButton = new Image(CommonComponents.class.getResourceAsStream("/images/icons/settingsButton.png"));
        ImageView settingsButton = new ImageView(imageSettingsButton);
        settingsButton.setFitHeight(windowHeight * 0.04);
        settingsButton.setFitWidth(windowWidth * 0.04);
        settingsButton.setPreserveRatio(true);
        settingsButton.setOnMouseClicked(e -> {
            openSettings();
        });

        VBox buttons = new VBox(windowHeight * 0.01);
        buttons.getChildren().addAll(settingsButton, downloadButton, infoButton);
        buttons.setAlignment(Pos.BOTTOM_LEFT);

        HBox graphBox = new HBox(windowWidth * 0.007);
        graphBox.getChildren().addAll(graphContainer, buttons);
        graphBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox graphSection = new VBox(windowHeight * 0.02,graphTitle, graphBox);
        graphSection.setPrefWidth(windowWidth);
        graphSection.setAlignment(Pos.CENTER);
        graphSection.setStyle("-fx-background-color: #a8c28c;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6");
        graphSection.setMinHeight(windowHeight - windowHeight * 0.07 - 2 * windowHeight * 0.03 - topBarHeight);
        graphSection.setOpacity(0.9);
        return graphSection;
    }

    /**
     * Creates a section for the description of the graph, with a title and text describing the graph.
     * The section is styled with specific fonts, colors, and dimensions, and is intended to be part of the main page layout.
     *
     * @param title The title text for the description section.
     * @param description The description text for the section.
     * @return The newly created section.
     */
    protected VBox createSectionDescription(String title, String description) {
        // Title for the description section
        Label descriptionTitle = new Label(title);
        descriptionTitle.setStyle(
                "-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold; -fx-text-fill: #82755b;");
        VBox.setMargin(descriptionTitle, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        // Description text
        Label descriptionText = new Label(description);
        descriptionText.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px; -fx-text-fill: #82755b;");
        descriptionText.setWrapText(true);
        VBox.setMargin(descriptionText, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        VBox descriptionSection = new VBox(descriptionTitle, descriptionText);
        descriptionSection.setAlignment(Pos.CENTER_LEFT);
        descriptionSection.setStyle("-fx-background-color: #ffffff;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        descriptionSection.setMinHeight(windowHeight - windowHeight * 0.07 - 2 * windowHeight * 0.03 - topBarHeight);
        descriptionSection.setOpacity(0.7);
        return descriptionSection;
    }

    /**
     * Opens a new popup window with a graph generated by the given MathModel.
     *
     * @param title The title text for the popup window.
     * @param graphGenerator The MathModel object to generate the graph content.
     */
    private void openGraphPopup(String title, MathModel graphGenerator) {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Graph Popup");

        // Graph container with padding and background style
        StackPane graphContainer = new StackPane();
        graphContainer.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 6; -fx-background-radius: 6;");
        graphContainer.setPadding(new Insets(20));

        // Add SwingNode with graph content
        SwingNode enlargedGraph = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            enlargedGraph.setContent(graphGenerator.getGraph(windowWidth * 0.9, windowHeight * 0.8));
        });
        graphContainer.getChildren().add(enlargedGraph);
        graphContainer.setMaxWidth(windowWidth * 0.9);

        // Graph Title aligned with the graph
        Label graphTitle = new Label(this.graphTitle);
        graphTitle.setStyle("-fx-font-size:" + Util.getTitleFontSize(windowWidth, windowHeight) + "px;" +
                "-fx-font-family: " + Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)).getFamily()
                + ";" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #000000;");

        // Align the title to the left using HBox
        HBox titleContainer = new HBox();
        titleContainer.getChildren().add(graphTitle);
        titleContainer.setAlignment(Pos.CENTER_LEFT); // Align content to the left
        titleContainer.setPadding(new Insets(0, 0, 0, windowWidth * 0.05)); // Left padding matches graphContainer's padding

        // Close button at the bottom
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-size:" + Util.getButtonFontSize(windowWidth, windowHeight) + "px;" +
                "-fx-font-family: " + Util.getBoldFont(Util.getButtonFontSize(windowWidth, windowHeight)).getFamily()
                + ";" +
                "-fx-background-color: #E07A5F;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 10 20;");
        closeButton.setOnAction(e -> popupStage.close());
        closeButton.setMaxWidth(100);

        // Layout for popup content
        VBox popupContent = new VBox(20);
        popupContent.getChildren().addAll(titleContainer, graphContainer, closeButton);
        popupContent.setAlignment(Pos.CENTER);
        //popupContent.setPadding(new Insets(20));
        popupContent.setStyle("-fx-background-color: #a8c28c;");

        // Set the scene and show the stage
        Scene popupScene = new Scene(popupContent, windowWidth * 0.9, windowHeight * 0.9);
        popupStage.setScene(popupScene);
        popupStage.getIcons().add(new Image(NavigationController.class.getResourceAsStream("/images/icons/main-logo.png")));
        popupStage.setMaximized(true);
        popupStage.show();
    }
    
    /**
     * Scrolls the content of the scrollPane to the description section.
     * The target position is calculated as the window height plus 75% of the window height
     * divided by the total height of the content.
     */
    
    private void scrollToDescription() {

        double targetPosition = descriptionSection.getBoundsInParent().getMinY();
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();

        double targetCenterY = targetPosition - (viewportHeight / 2) + (descriptionSection.getBoundsInParent().getHeight() / 2) - windowHeight * 0.07 / 2;
        
        double targetValue = targetCenterY / (contentHeight - viewportHeight);
        targetValue = Math.max(0, Math.min(1, targetValue));
        
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(scrollPane.vvalueProperty(), scrollPane.getVvalue())),
                new KeyFrame(Duration.seconds(1), new KeyValue(scrollPane.vvalueProperty(), targetValue)));
        timeline.play();
    }
    
    /**
     * Handles the download action for the page by showing a confirmation dialog
     * to the user, capturing the page snapshot as a PNG image, and then saving
     * the image as a PDF file to the user's chosen location using the iText PDF
     * library. The page is then re-shown after the download action is completed.
     * If any error occurs during the process, a notification is shown to the user.
     * @param graphTitle the title of the graph to be saved
     */
    private void handleDownloadAction(String graphTitle) {
        try {
            boolean isConfirmed = CommonComponents.showConfirmationDialog("Download Confirmation",
                    "Would you like to download\n" + graphTitle + "\nas a PDF?", windowWidth, windowHeight);
            if (isConfirmed) {
                String imagePath = Util.capturePageAsImage(root);
                if (imagePath == null) {
                    System.out.println("Error capturing page snapshot.");
                    return;
                }

                File file = Util.chooseFile("PDF Files", "*.pdf");
                if (file != null) {
                    Util.exportToPDF(imagePath, file.getAbsolutePath(), windowWidth);
                    CommonComponents.showNotification("Success",
                            "The page was successfully saved as a PDF: " + file.getAbsolutePath());
                }

                navigationController.showPage(this.pageName);
            }
        } catch (Exception e) {
            CommonComponents.showNotification("Error", "An error occurred while saving the page.");
            e.printStackTrace();
        }
    }
    
    private void openSettings() {
        // Create a new dialog window
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Graph Settings");

        // Create the dialog content
        VBox dialogContent = new VBox(windowHeight * 0.02);
        dialogContent.setAlignment(Pos.CENTER);
        dialogContent.setStyle("-fx-background-color: #ffffff; -fx-padding: 20; -fx-spacing: 15;");

        // Input fields
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(windowWidth * 0.02); // Horizontal gap between columns
        gridPane.setVgap(windowHeight * 0.02); // Vertical gap between rows

        TextField[] inputFields = new TextField[6];

        // Adding fields and labels to the grid
        for (int i = 0; i < 6; i++) {
            // Create a VBox to stack Label and TextField vertically
            VBox fieldBox = new VBox(5); // Space between label and text field
            fieldBox.setAlignment(Pos.CENTER); // Center align both Label and TextField
    
            Label label = new Label("Variable " + (i + 1) + " (Limit):");
            label.setStyle("-fx-text-fill: #000000;");
            label.setFont(Util.getRegularFont(16));
            label.setTextAlignment(TextAlignment.CENTER);
    
            TextField textField = new TextField();
            textField.setMaxWidth(windowWidth * 0.2); 
            inputFields[i] = textField;
    
            // Add Label and TextField to the VBox
            fieldBox.getChildren().addAll(label, textField);
    
            // Add the VBox to the GridPane
            gridPane.add(fieldBox, i % 2, i / 2); // Place in a 2-column layout
        }

        // "SUBMIT" and "CLOSE" buttons
        Button submitButton = new Button("SUBMIT");
        submitButton.setStyle("-fx-background-color: #1E4C40;" +
                              "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                              "-fx-text-fill: white;" +
                              "-fx-font-size: 14px;" +
                              "-fx-font-weight: bold;" +
                              "-fx-padding: 10;" +
                              "-fx-background-radius: 5;");
        submitButton.setOnAction(e -> {
            // Handle the input data
            for (int i = 0; i < inputFields.length; i++) {
                System.out.println("Variable " + (i + 1) + ": " + inputFields[i].getText());
            }
            dialog.close();
        });

        Button closeButton = new Button("CLOSE");
        closeButton.setStyle("-fx-background-color: #E07A5F;" +
                             "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                             "-fx-text-fill: white;" +
                             "-fx-font-size: 14px;" +
                             "-fx-font-weight: bold;" +
                             "-fx-padding: 10;" +
                             "-fx-background-radius: 5;");
        closeButton.setOnAction(e -> dialog.close());

        HBox buttonBox = new HBox(20, submitButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Combine all components
        dialogContent.getChildren().addAll(gridPane, buttonBox);

        // Set the background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);");
        root.getChildren().add(dialogContent);

        // Set the scene
        Scene scene = new Scene(root);
        dialog.getIcons().add(new Image(NavigationController.class.getResourceAsStream("/images/icons/main-logo.png")));
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    @Override
    public BorderPane getRoot() {
        return root;
    }

    @Override
    public String getPageName() {
        return pageName;
    }
}
