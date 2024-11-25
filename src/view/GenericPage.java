package view;

import controller.NavigationController;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class GenericPage {

    // Main method to create a generic page with dropdown menu and graph box
    public static BorderPane createPage(NavigationController navigationController, String pageName, String description,
                                        int graphCount) {
        BorderPane root = new BorderPane();

        double windowWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

        // Create the dropdown menu at the top
        createDropdownMenu(pageName, navigationController, windowWidth, windowHeight, root);

        // Create the box for graphs in the center
        createGraphBox(graphCount, navigationController, windowWidth, windowHeight, root);

        return root;
    }

    // Creates a dropdown menu at the top of the page
    private static void createDropdownMenu(String pageName, NavigationController navigationController,
                                           double windowWidth, double windowHeight, BorderPane root) {
        ComboBox<String> dropDownMenu = new ComboBox<>();
        dropDownMenu.getItems().addAll("Main", "Q1", "Q2", "Q3", "Q4", "Q5");
        dropDownMenu.setValue(pageName);
        dropDownMenu.setStyle("-fx-font-size: 16px; -fx-faint-focus-color: transparent; -fx-focus-color: #cccccc;");
        dropDownMenu.setOnAction(e -> navigationController.showPage(dropDownMenu.getValue()));
        dropDownMenu.setPrefHeight(windowHeight * 0.04);
        dropDownMenu.setPrefWidth(windowWidth * 0.08);  
 
        // Center-align text within dropdown menu items
        dropDownMenu.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
                setAlignment(Pos.CENTER); // Center the text
                setPadding(new Insets(0, 0, 0, windowWidth * 0.014));
            }
        });

        // Center-align text for the list of items
        dropDownMenu.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
                setAlignment(Pos.CENTER);
            }
        });

        // Add the dropdown menu to the top bar (HBox)
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT); // Align dropdown to the right
        topBar.setSpacing(windowHeight * 0.01);
        topBar.setPadding(new Insets(windowHeight * 0.02, windowWidth * 0.01, 0, 0));
        topBar.getChildren().add(dropDownMenu);

        root.setTop(topBar);
    }

    // Creates the box containing graphs with optional scroll functionality
    private static void createGraphBox(int graphCount, NavigationController navigationController,
                                       double windowWidth, double windowHeight, BorderPane root) {
        HBox graphsBox = new HBox();
        graphsBox.setAlignment(Pos.TOP_CENTER);
        graphsBox.setSpacing(windowHeight * 0.05);

        double graphHeight = windowHeight * 0.4;
        double graphWidth = windowWidth * 0.4;
        double graphsSpacing = windowWidth * 0.007;

        // Add graphs to the box
        for (int i = 1; i <= graphCount; i++) {
            VBox graphContainer = new VBox();
            graphContainer.setAlignment(Pos.TOP_CENTER);
            graphContainer.setSpacing(windowWidth * 0.007);

            // Placeholder for the graph (gray square)
            StackPane square = new StackPane();
            square.setStyle("-fx-background-color: #808080;"); // Gray background
            square.setMinSize(graphWidth, graphHeight);
            square.setMaxSize(graphWidth, graphHeight);

            // Label for the graph
            Label graphLabel = new Label("Graph " + i);
            graphLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

            graphContainer.getChildren().addAll(square, graphLabel);
            graphsBox.getChildren().add(graphContainer);
        }

        double scrollPaneWidth = windowWidth * 0.9;

        // Add ScrollPane only if graphs exceed available space
        if (graphWidth * graphCount + graphsSpacing * (graphCount - 1) > scrollPaneWidth) {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(graphsBox);
            scrollPane.setPrefHeight(graphHeight + windowHeight * 0.09);
            scrollPane.setFitToHeight(false);
            scrollPane.setFitToWidth(false);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

            VBox scrollContainer = new VBox();
            scrollContainer.setAlignment(Pos.TOP_CENTER);
            scrollContainer.setPadding(new Insets(windowHeight * 0.1, 0, 0, 0));
            scrollContainer.setMaxWidth(scrollPaneWidth);
            scrollContainer.getChildren().add(scrollPane);

            root.setCenter(scrollContainer);
        } else {
            // No scroll needed, directly add the graphs box
            graphsBox.setPadding(new Insets(windowHeight * 0.1, 0, 0, 0));
            root.setCenter(graphsBox);
        }
    }
}
