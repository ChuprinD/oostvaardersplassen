package view;

import controller.NavigationController;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ScrollPane;

public class FirstPage implements Page {
    private final String pageName = "Q1";
    private final BorderPane root;

    public FirstPage(NavigationController navigationController) {
        root = new BorderPane();
    
        double windowWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();
    
        // Create header
        HBox header = HeaderFooterFactory.createHeader(pageName, navigationController, windowWidth, windowHeight);
        root.setTop(header);
        
        VBox mainContent = new VBox();
        VBox graphSection = createSectionGraph(windowWidth, windowHeight);
        VBox descriptionSection = createSectionDescription(windowWidth, windowHeight);
        mainContent.getChildren().addAll(graphSection, descriptionSection);

        // Scroll pane for the main content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        root.setCenter(scrollPane);
    
        // Create footer
        HBox footer = HeaderFooterFactory.createFooter(navigationController, windowWidth, windowHeight);
        root.setBottom(footer);
    }
    

    // Show a confirmation pop-up for downloading
    private void showDownloadPopup() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Download Confirmation");
        alert.setHeaderText("Do you want to download 'Graph Title' as a PDF?");
        alert.showAndWait();
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
        downloadButton.setOnAction(e -> showDownloadPopup());
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

        // Info button ("i")
        Button infoButton = new Button("i");
        infoButton.setStyle(
                "-fx-background-radius: 50%; -fx-font-size: 15px; -fx-padding: 3; -fx-background-color: #cccccc; -fx-translate-x: 20; -fx-font-weight: bold;");
        infoButton.setMinSize(windowWidth * 0.025, windowWidth * 0.025);
        infoButton.setMaxSize(windowWidth * 0.025, windowWidth * 0.025);
        infoButton.setAlignment(Pos.CENTER);

        HBox graphBox = new HBox(graphImage, infoButton);
        graphBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox graphSection = new VBox(titleBox, graphBox);
        graphSection.setAlignment(Pos.TOP_CENTER);
        graphSection.setPadding(new Insets(windowHeight * 0.05, 0, windowHeight * 0.07, 0));
        return graphSection;
    }

    private VBox createSectionDescription(double windowWidth, double windowHeight) {
        // Title for the description section
        Label descriptionTitle = new Label("Description Title");
        descriptionTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        VBox.setMargin(descriptionTitle, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        // Description text
        Label descriptionText = new Label(
                "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n"
                        + "Something Something Something Something Something Something Something \n");
        descriptionText.setStyle("-fx-font-size: 20px;");
        descriptionText.setWrapText(true);
        VBox.setMargin(descriptionText, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        VBox descriptionSection = new VBox(descriptionTitle, descriptionText);
        descriptionSection.setStyle("-fx-background-color: lightgray;");
        descriptionSection.setAlignment(Pos.TOP_LEFT);
        descriptionSection.setPadding(new Insets(windowHeight * 0.07, 0, windowHeight * 0.07, 0));
        return descriptionSection;
    }

    public BorderPane getRoot() {
        return root;
    }

    public String getPageName() {
        return pageName;
    }
}