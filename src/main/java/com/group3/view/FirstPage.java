package com.group3.view;

import java.io.File;

import javax.swing.SwingUtilities;

import com.group3.controller.NavigationController;
import com.group3.mathModels.Graphs;
import com.group3.utils.Util;

import javafx.geometry.Pos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class FirstPage implements Page {
    private final String pageName = "Herbivore Competition";
    private final BorderPane root;
    private final VBox descriptionSection;
    private final ScrollPane scrollPane;
    private final double windowWidth;
    private final double windowHeight;
    private final NavigationController navigationController;

    public FirstPage(NavigationController navigationController) {
        this.navigationController = navigationController;
        root = new BorderPane();

        windowWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
        windowHeight = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

        // Header
        CommonComponents commonComponents = new CommonComponents(windowWidth, windowHeight);
        HBox header = commonComponents.createHeader(pageName, navigationController);
        HBox headerContainer = new HBox(header);
        headerContainer.setMinWidth(windowWidth);
        headerContainer.setMaxHeight(windowHeight * 0.07);
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setStyle("-fx-background-color: transparent;");
        
        VBox mainContent = new VBox(windowHeight * 0.02);
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #1e4c40, #a8c28c); -fx-border-width: 0; -fx-border-color: transparent;");
        mainContent.setMinWidth(windowWidth);
        mainContent.setPadding(new Insets(0, windowWidth * 0.01, 0, windowWidth * 0.01));
        VBox graphSection = createSectionGraph();
        descriptionSection = createSectionDescription();

        Region spacer = new Region();
        spacer.setMinHeight(windowHeight * 0.07);
        spacer.setMaxHeight(windowHeight * 0.07);
        spacer.setStyle("-fx-background-color: transparent;");

        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/WhiteWolf.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.1);
        imageView.setPreserveRatio(true);
        HBox bottomSpacer = new HBox(imageView);
        bottomSpacer.setAlignment(Pos.BOTTOM_CENTER);
        bottomSpacer.setPadding(new Insets(windowHeight * 0.02, 0, windowHeight * 0.02, 0));
        
        mainContent.getChildren().addAll(spacer, graphSection, descriptionSection, bottomSpacer);

        // Scroll pane for the main content
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(mainContent);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: transparent; -fx-border-width: 0; -fx-border-color: transparent; -fx-padding: 0;");
        scrollPane.setMaxWidth(windowWidth);
        scrollPane.setFitToWidth(true);

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().addAll(scrollPane, headerContainer);
        stackPane.setAlignment(Pos.TOP_CENTER);

        root.setCenter(stackPane);
    }

    /**
     * Creates a section containing a graph visualization, including a title,
     * a download button, and an info button. The section is structured with
     * an HBox for the title and buttons, and another HBox for the graph and
     * info button. The graph is rendered using a SwingNode to display a
     * prey-predator model graph. The download button triggers a download
     * popup, while the info button scrolls to the description section.
     *
     * @return A VBox containing the graph and associated controls.
     */
    private VBox createSectionGraph() {
        // Title for the graph section
        String graphTitleText = "Graph Title";
        Label graphTitle = new Label(graphTitleText);
        graphTitle.setStyle("-fx-font-weight: bold;");
        graphTitle.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
        graphTitle.setAlignment(Pos.TOP_LEFT);

        HBox titleBox = new HBox();
        titleBox.getChildren().add(graphTitle);
        titleBox.setAlignment(Pos.TOP_LEFT);
        HBox.setMargin(graphTitle, new Insets(0, 0, windowHeight * 0.01, (windowWidth - windowWidth * 0.64) / 2));

        // Graph 
        SwingNode swingNode = new SwingNode();
        Graphs preyPredatorGraph = new Graphs();
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(preyPredatorGraph.getPreyPredator(windowWidth * 0.6, windowHeight * 0.5));
        });

        swingNode.setOnMouseClicked(event -> openGraphPopup());

        // Info button ("i")
        Image imageInfoButton = new Image(CommonComponents.class.getResourceAsStream("/images/infoButton.png"));
        ImageView infoButton = new ImageView(imageInfoButton);
        infoButton.setFitHeight(windowHeight * 0.035);
        infoButton.setFitWidth(windowWidth * 0.035);
        infoButton.setPreserveRatio(true);
        infoButton.setOnMouseClicked(e -> {
            javafx.application.Platform.runLater(() -> {
                scrollToDescription();
            });
        });

        // Download button
        Image imageDownloadButton = new Image(CommonComponents.class.getResourceAsStream("/images/downloadButton.png"));
        ImageView downloadButton = new ImageView(imageDownloadButton);
        downloadButton.setFitHeight(windowHeight * 0.035);
        downloadButton.setFitWidth(windowWidth * 0.035);
        downloadButton.setPreserveRatio(true);
        downloadButton.setOnMouseClicked(e -> {
            handleDownloadAction(graphTitleText);
        });

        VBox buttons = new VBox(windowHeight * 0.01);
        buttons.getChildren().addAll(downloadButton, infoButton);
        buttons.setAlignment(Pos.BOTTOM_LEFT);

        HBox graphBox = new HBox(windowWidth * 0.007);
        graphBox.getChildren().addAll(swingNode, buttons);
        graphBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox graphSection = new VBox(titleBox, graphBox);
        graphSection.setPrefWidth(windowWidth);
        graphSection.setAlignment(Pos.CENTER);
        graphSection.setStyle("-fx-background-color: #a8c28c;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6");
        graphSection.setMinHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        graphSection.setOpacity(0.9);
        return graphSection;
    }
    
    
    /**
     * Handles the download action by first displaying a confirmation dialog
     * to the user. If confirmed, captures the current page as an image and
     * prompts the user to choose a file location to save the page as a PDF.
     * Notifies the user of success or error during the process.
     */
    private void handleDownloadAction(String graphTitle) {
        try {
            boolean isConfirmed = CommonComponents.showConfirmationDialog("Download Confirmation",
                    "Would you like to download\n" + graphTitle + " as a PDF?", windowWidth, windowHeight);
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
    
    /**
     * Scrolls the scrollpane to the description section with an animation.
     * The scroll target is calculated by adding a fraction of the window height
     * to the Y position of the description section, and then dividing by the
     * height of the content of the scrollpane.
     */
    private void scrollToDescription() {
        double scrollTarget = (descriptionSection.getLayoutY() + windowHeight * 0.74) / scrollPane.getContent().getBoundsInLocal().getHeight();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(scrollPane.vvalueProperty(), scrollPane.getVvalue())),
            new KeyFrame(Duration.seconds(1), new KeyValue(scrollPane.vvalueProperty(), scrollTarget))
        );
        timeline.play();
    }
    
    /**
     * Creates a section containing a title and text description. The section
     * is structured with a VBox containing the title and description. The
     * description is displayed in a centered box with a semi-transparent
     * background. The description is intended to be part of the main page
     * layout.
     *
     * @return A VBox containing the styled description section.
     */
    private VBox createSectionDescription() {
        // Title for the description section
        Label descriptionTitle = new Label("Description Title");
        descriptionTitle.setStyle(
                "-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold; -fx-text-fill: #82755b;");
        VBox.setMargin(descriptionTitle, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        // Description text
        Label descriptionText = new Label(
                "Something Something Something Something\n"
                        + "Something Something Something Something\n"
                        + "Something Something Something Something\n"
                        + "Something Something Something Something\n");
        descriptionText.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px; -fx-text-fill: #82755b;");
        descriptionText.setWrapText(true);
        VBox.setMargin(descriptionText, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        VBox descriptionSection = new VBox(descriptionTitle, descriptionText);
        descriptionSection.setAlignment(Pos.CENTER_LEFT);
        descriptionSection.setStyle("-fx-background-color: #ffffff;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        descriptionSection.setMinHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        descriptionSection.setOpacity(0.7);
        return descriptionSection;
    }
    
    /**
     * Opens a popup window displaying a graph visualization. The popup
     * contains a graph rendered using a SwingNode, accompanied by a title
     * and a close button. The graph is displayed within a styled container
     * that includes padding and background color. The title is aligned to
     * the left and styled with bold font. The close button allows users to
     * close the popup. The popup window is maximized and styled with
     * semi-transparent background colors.
     */
    private void openGraphPopup() {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.setTitle("Graph Popup");
    
        // Graph container with padding and background style
        StackPane graphContainer = new StackPane();
        graphContainer.setStyle("-fx-background-color: #f7f7f7; -fx-border-radius: 6; -fx-background-radius: 6;");
        graphContainer.setPadding(new Insets(20));
    
        // Add SwingNode with graph content
        SwingNode enlargedGraph = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            Graphs preyPredatorGraph = new Graphs();
            enlargedGraph.setContent(preyPredatorGraph.getPreyPredator(windowWidth * 0.9, windowHeight * 0.8));
        });
        graphContainer.getChildren().add(enlargedGraph);
        graphContainer.setMaxWidth(windowWidth * 0.9);
    
        // Graph Title aligned with the graph
        Label graphTitle = new Label("GRAPH TITLE");
        graphTitle.setStyle("-fx-font-size:" + Util.getTitleFontSize(windowWidth, windowHeight) + "px;" +
                            "-fx-font-family: " + Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)).getFamily() + ";" +
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
                             "-fx-font-family: " + Util.getBoldFont(Util.getButtonFontSize(windowWidth, windowHeight)).getFamily() + ";" +
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
        popupContent.setStyle("-fx-background-color: #a8c28c; -fx-background-radius: 10; -fx-border-radius: 10;");
    
        // Set the scene and show the stage
        Scene popupScene = new Scene(popupContent, windowWidth * 0.9, windowHeight * 0.9);
        popupStage.setScene(popupScene);
        popupStage.setMaximized(true);
        popupStage.show();
    }  

    /**
     * Returns the root of the page, which is a BorderPane containing all the page's content.
     * @return the root of the page
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * Returns the name of the page.
     * @return the name of the page
     */
    public String getPageName() {
        return pageName;
    }
}