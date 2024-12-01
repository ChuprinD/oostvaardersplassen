package view;

import controller.NavigationController;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utils.Util;
import javafx.scene.control.ScrollPane;

public class FirstPage implements Page {
    private final String pageName = "Q1";
    private final BorderPane root;
    private final VBox descriptionSection;
    private final ScrollPane scrollPane;

    public FirstPage(NavigationController navigationController) {
        root = new BorderPane();
    
        double windowWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();
    
        // Create header
        HBox header = CommonComponents.createHeader(pageName, navigationController, windowWidth, windowHeight);
        root.setTop(header);
        
        VBox mainContent = new VBox();
        VBox graphSection = createSectionGraph(windowWidth, windowHeight);
        descriptionSection = createSectionDescription(windowWidth, windowHeight);
        mainContent.getChildren().addAll(graphSection, descriptionSection);

        // Scroll pane for the main content
        scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        root.setCenter(scrollPane);
    
        // Create footer
        //HBox footer = HeaderFooterFactory.createFooter(navigationController, windowWidth, windowHeight);
        //root.setBottom(footer);
    }

    private VBox createSectionGraph(double windowWidth, double windowHeight) {
        // Title for the graph section
        Label graphTitle = new Label("Graph Title");
        HBox.setMargin(graphTitle, new Insets(0, 0, windowHeight * 0.01, (windowWidth - windowWidth * 0.6) / 2));
        graphTitle.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Download button
        Button downloadButton = new Button("Download");
        HBox.setMargin(downloadButton, new Insets(0, windowWidth * 0.05,  windowHeight * 0.01, 0));
        downloadButton.setStyle("-fx-font-size: 20px; -fx-border-color: black;");
        downloadButton.setOnAction(e -> CommonComponents.showDownloadPopup());
        downloadButton.setMinSize(windowWidth * 0.08, windowHeight * 0.03);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleBox = new HBox(graphTitle, spacer, downloadButton);
        titleBox.setAlignment(Pos.CENTER);

        // Graph placeholder (Rectangle)
        Rectangle graphImage = new Rectangle(windowWidth * 0.6, windowHeight * 0.5);
        graphImage.setFill(Color.web("#808080"));
        graphImage.setStroke(Color.BLACK);
        graphImage.setStrokeWidth(1);

        graphImage.setOnMouseClicked(e -> openGraphPopup(windowWidth, windowHeight));

        // Info button ("i")
        Button infoButton = new Button("i");
        infoButton.setStyle("-fx-background-radius: 50%;" +
                            "-fx-font-size: 15px;" +
                            "-fx-padding: 3;" +
                            "-fx-background-color: #cccccc;" +
                            "-fx-translate-x: 20;" +
                            "-fx-font-weight: bold;");
        infoButton.setMinSize(Math.min(windowWidth, windowHeight) * 0.03, Math.min(windowWidth, windowHeight)  * 0.03);
        infoButton.setMaxSize(Math.min(windowWidth, windowHeight)  * 0.03, Math.min(windowWidth, windowHeight)  * 0.03);
        infoButton.setOnAction(e -> {
            double scrollTarget = (descriptionSection.getLayoutY() + + windowHeight * 0.13 * 3) / scrollPane.getContent().getBoundsInLocal().getHeight();
            scrollPane.setVvalue(scrollTarget);
        });
        infoButton.setAlignment(Pos.CENTER);

        HBox graphBox = new HBox(graphImage, infoButton);
        graphBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox graphSection = new VBox(titleBox, graphBox);
        graphSection.setAlignment(Pos.TOP_CENTER);
        graphSection.setPadding(new Insets(windowHeight * 0.13, 0, windowHeight * 0.13, 0));
        return graphSection;
    }

    private VBox createSectionDescription(double windowWidth, double windowHeight) {
        // Title for the description section
        Label descriptionTitle = new Label("Description Title");
        descriptionTitle.setStyle(
                "-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold;");
        VBox.setMargin(descriptionTitle, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        // Description text
        Label descriptionText = new Label(
                "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n");
        descriptionText.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");
        descriptionText.setWrapText(true);
        VBox.setMargin(descriptionText, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        VBox descriptionSection = new VBox(descriptionTitle, descriptionText);
        descriptionSection.setStyle("-fx-background-color: lightgray;");
        descriptionSection.setAlignment(Pos.TOP_LEFT);
        descriptionSection.setPadding(new Insets(windowHeight * 0.13, 0, windowHeight * 0.13, 0));
        return descriptionSection;
    }
    
    private void openGraphPopup(double windowWidth, double windowHeight) {
    // Create a new stage for the popup
    Stage popupStage = new Stage();
    popupStage.setTitle("Graph Popup");

    // Create a larger rectangle to simulate the enlarged graph
    Rectangle enlargedGraph = new Rectangle(windowWidth * 0.8, windowHeight * 0.8);
    enlargedGraph.setFill(Color.web("#808080"));
    enlargedGraph.setStroke(Color.BLACK);
    enlargedGraph.setStrokeWidth(2);

    // Close button
    Button closeButton = new Button("Close");
    closeButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ff4444; -fx-text-fill: white;");
    closeButton.setOnAction(e -> popupStage.close());

    // Layout for the popup content
    VBox popupContent = new VBox(20, enlargedGraph, closeButton);
    popupContent.setAlignment(Pos.CENTER);
    popupContent.setPadding(new Insets(20));

    // Set the scene and show the stage
    Scene popupScene = new Scene(popupContent, windowWidth * 0.9, windowHeight * 0.9);
    popupStage.setScene(popupScene);
    popupStage.show();
    }

    public BorderPane getRoot() {
        return root;
    }

    public String getPageName() {
        return pageName;
    }
}