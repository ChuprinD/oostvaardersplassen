package view;

import controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainPage {
    private final BorderPane root;

    public MainPage(NavigationController navigationController) {
        double windowWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

        root = new BorderPane();

        // Horizontal navigation bar
        HBox navBar = new HBox();
        navBar.setAlignment(Pos.CENTER);
        navBar.setSpacing(windowWidth * 0.008);

        String[] sections = {"Q1", "Q2", "Q3", "Q4", "Q5"};
        for (String section : sections) {
            Button button = new Button(section);
            button.setPrefSize(windowWidth * 0.08, windowHeight * 0.03);
            button.setStyle("-fx-font-size: 20px;");
            button.setOnAction(e -> navigationController.showPage(section)); // Navigate to the selected page
            button.setFocusTraversable(false);
            navBar.getChildren().add(button);
        }

        navBar.setPadding(new Insets(windowHeight * 0.03, 0, 0, 0));

        root.setTop(navBar);

        // Central area with the image above the text
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setSpacing(windowHeight * 0.5); // Space between the image and the text
        centerContent.setPadding(new Insets(windowHeight * 0.04, 0, 0, 0)); // Add padding between navBar and the image

        // Add the image placeholder (gray square)
        StackPane imagePlaceholder = new StackPane();
        imagePlaceholder.setStyle("-fx-background-color: gray;");
        imagePlaceholder.setMinSize(windowWidth * 0.7, windowHeight * 0.7);
        imagePlaceholder.setMaxSize(windowWidth * 0.7, windowHeight * 0.7);

        // Text description below the image
        Text description = new Text("Description of the preserve. Here is some introductory text about the application.");
        description.setStyle("-fx-font-size: 32px; -fx-text-alignment: center;");

        // Add empty space below the text using Region
        Region spacer = new Region();
        spacer.setPrefHeight(windowHeight * 0.05);

        // Add the image, text, and spacer to the central container
        centerContent.getChildren().addAll(imagePlaceholder, description, spacer);

        // Wrap the central content in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(centerContent);
        scrollPane.setFitToWidth(true); // Allow the content to fit the width of the ScrollPane
        scrollPane.setFitToHeight(true); // Allow the content to fit the height of the ScrollPane
        scrollPane.setPannable(true); // Enable panning with the mouse
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Set the ScrollPane in the main layout
        root.setCenter(scrollPane);
    }

    public BorderPane getRoot() {
        return root;
    }
}
