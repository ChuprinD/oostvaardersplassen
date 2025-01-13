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
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.io.File;

import javax.swing.SwingUtilities;

import com.group3.Formulae.FormulaVariables;
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
    protected FormulaVariables formulaVariables;

    private final double topBarHeight = 23;

    public AbstractPage(String pageName, String graphTitle, NavigationController navigationController, FormulaVariables formulaVariables) {
        this.pageName = pageName;
        this.navigationController = navigationController;
        this.graphTitle = graphTitle;
        this.formulaVariables = formulaVariables;

        // Determine screen dimensions
        this.windowWidth = Screen.getPrimary().getVisualBounds().getWidth();
        this.windowHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Create the main layout
        this.root = new BorderPane();

        setupPage();
    }

    /**
     * Sets up the page layout, which consists of a header bar, content section, and footer.
     * The content section is divided into two parts: a graph section and a description section.
     * The page is embedded within a scrollable area, which is necessary for smaller screens.
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
     * Creates a section containing a graph based on a MathModel object. The section
     * includes a title, the graph itself, and buttons for downloading the graph, opening
     * a settings popup, and scrolling to the description section.
     *
     * @param title The title of the section.
     * @param graphGenerator The MathModel object that generates the graph.
     * @return A VBox containing the title, graph, and buttons.
     */
    protected VBox createSectionGraph(String title, MathModel graphGenerator) {
        // Title for the graph section
        Label graphTitle = new Label(this.graphTitle);
        graphTitle.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth) + "px; -fx-text-fill: #000000;");
        graphTitle.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth)));
        graphTitle.setAlignment(Pos.TOP_CENTER);

        // Graph 
        StackPane graphContainer = new StackPane();
        graphContainer.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 6; -fx-background-radius: 6;");
        graphContainer.setPadding(new Insets(5));
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(graphGenerator.getGraph(windowWidth * 0.7, windowHeight * 0.6, formulaVariables));
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
     * Creates a section containing a title and a description. The section has a transparent
     * background, with the title and description aligned to the left. The title font is bold
     * and the description font is regular. The text is wrapped if it exceeds the width of the
     * section. The section's minimum height is set to the height of the window minus the height
     * of the header and the height of the graph section.
     *
     * @param title The title of the section.
     * @param description The description of the section.
     * @return A VBox containing the title and description.
     */
    protected VBox createSectionDescription(String title, String description) {
        // Title for the description section
        Label descriptionTitle = new Label(title);
        descriptionTitle.setStyle(
                "-fx-font-size: " + Util.getTitleFontSize(windowWidth) + "px; -fx-font-weight: bold; -fx-text-fill: #82755b;");
        VBox.setMargin(descriptionTitle, new Insets(0, 0, 0, (windowWidth - windowWidth * 0.6) / 2));

        // Description text
        Label descriptionText = new Label(description);
        descriptionText.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px; -fx-text-fill: #82755b;");
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
     * Opens a popup window with a graph, given by the MathModel implementation. The graph is
     * displayed in a StackPane with a white background, and the title of the graph is aligned
     * to the left. The popup window has a close button at the bottom. The popup window is
     * maximized by default, and the graph is resized to fit the window.
     *
     * @param title The title of the graph.
     * @param graphGenerator The MathModel implementation that generates the graph.
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
            enlargedGraph.setContent(graphGenerator.getGraph(windowWidth * 0.9, windowHeight * 0.8, formulaVariables));
        });
        graphContainer.getChildren().add(enlargedGraph);
        graphContainer.setMaxWidth(windowWidth * 0.9);

        // Graph Title aligned with the graph
        Label graphTitle = new Label(this.graphTitle);
        graphTitle.setStyle("-fx-font-size:" + Util.getTitleFontSize(windowWidth) + "px;" +
                "-fx-font-family: " + Util.getBoldFont(Util.getTitleFontSize(windowWidth)).getFamily()
                + ";" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #000000;");

        // Align the title to the left using HBox
        HBox titleContainer = new HBox();
        titleContainer.getChildren().add(graphTitle);
        titleContainer.setAlignment(Pos.CENTER); // Align content to the left
        titleContainer.setPadding(new Insets(0, 0, 0, windowWidth * 0.05)); // Left padding matches graphContainer's padding

        // Close button at the bottom
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-size:" + 18 + "px;" +
                "-fx-font-family: " + Util.getBoldFont(18).getFamily()
                + ";" +
                "-fx-background-color: #E07A5F;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 5;" +
                "-fx-background-radius: 5;");
        closeButton.setOnAction(e -> popupStage.close());
        closeButton.setMaxWidth(80);

        // Layout for popup content
        VBox popupContent = new VBox(5);
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
     * Smoothly scrolls the scroll pane to the description section of the page.
     * Calculates the target vertical position to center the description section
     * within the visible viewport, and animates the scroll to that position over
     * one second.
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
     * Handles the action of downloading the current page view as a PDF.
     * Displays a confirmation dialog to the user. If confirmed, it captures the current
     * page as an image, prompts the user to choose a file location, and exports the image
     * to a PDF file. Shows a success notification upon successful export, or an error
     * notification if an exception occurs during the process.
     *
     * @param graphTitle the title of the graph to include in the confirmation dialog
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
    
    /**
     * Opens a settings dialog window for configuring initial populations and biomass
     * for various species in the simulation. The dialog allows the user to input
     * values for cattle, horse, deer, wolf populations, and grass biomass, with
     * limits displayed for each. A submit button updates the simulation state with
     * the new values, while a close button exits the dialog without changes.
     * The dialog is styled and centrally aligned, and upon submission, the graph
     * section is refreshed to reflect the updated settings.
     */
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

        String[] variableNames = new String[]{"Cattle Initial Population", "Horse Initial Population", "Deer Initial Population", "Grass Initial Biomass", "Wolf Initial Population"};
        double[] currentValue = new double[]{formulaVariables.getCattleInitialPopulation(), formulaVariables.getHorseInitialPopulation(), formulaVariables.getDeerInitialPopulation(), formulaVariables.getGrassInitialBiomass(), formulaVariables.getWolfInitialPopulation()};
        double[] maxValue = new double[]{formulaVariables.getMaxCattlePopulation(), formulaVariables.getMaxHorsePopulation(), formulaVariables.getMaxDeerPopulation(), formulaVariables.getMaxGrassBiomass(), formulaVariables.getMaxWolfPopulation()};
        // Adding fields and labels to the grid
        for (int i = 0; i < 5; i++) {
            // Create a VBox to stack Label and TextField vertically
            VBox fieldBox = new VBox(5); // Space between label and text field
            fieldBox.setAlignment(Pos.CENTER); // Center align both Label and TextField
    
            Label label = new Label(variableNames[i] + " (Limit):" + maxValue[i]);
            label.setStyle("-fx-text-fill: #000000;");
            label.setFont(Util.getRegularFont(16));
            label.setTextAlignment(TextAlignment.CENTER);
    
            TextField textField = new TextField();
            textField.setMaxWidth(windowWidth * 0.1);
            textField.setText(String.valueOf(currentValue[i]));
            inputFields[i] = textField;   
    
            // Add Label and TextField to the VBox
            fieldBox.getChildren().addAll(label, textField);
    
            // Add the VBox to the GridPane
            if (i == 4) {
                // Place the 5th element in the center of the window
                gridPane.add(fieldBox, 0, 2, 2, 1); // Place in the 3rd row, span 2 columns
                GridPane.setHalignment(fieldBox, HPos.CENTER); // Align the VBox horizontally in the center
            } else {
                gridPane.add(fieldBox, i % 2, i / 2); // Place in a 2-column layout
            }
        }

        // "SUBMIT" and "CLOSE" buttons
        Button submitButton = new Button("SUBMIT");
        submitButton.setMinWidth(windowWidth * 0.05);
        submitButton.setStyle("-fx-background-color: #1E4C40;" +
                              "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                              "-fx-text-fill: white;" +
                              "-fx-font-size: 14px;" +
                              "-fx-font-weight: bold;" +
                              "-fx-padding: 10;" +
                              "-fx-background-radius: 5;");
        submitButton.setOnAction(e -> {
            // Handle the input data
            for (int i = 0; i < 5; i++) {
                if (Double.parseDouble(inputFields[i].getText()) > maxValue[i]) {
                    inputFields[i].setText(String.valueOf(maxValue[i]));  
                }
            }

            formulaVariables.setCattleInitialPopulation(Double.parseDouble(inputFields[0].getText()));
            formulaVariables.setHorseInitialPopulation(Double.parseDouble(inputFields[1].getText()));
            formulaVariables.setDeerInitialPopulation(Double.parseDouble(inputFields[2].getText()));
            formulaVariables.setGrassInitialBiomass(Double.parseDouble(inputFields[3].getText()));
            formulaVariables.setWolfInitialPopulation(Double.parseDouble(inputFields[4].getText()));

            VBox newGraphSection = createSectionGraph();

            int graphIndex = ((VBox) scrollPane.getContent()).getChildren().indexOf(graphSection);
            ((VBox) scrollPane.getContent()).getChildren().set(graphIndex, newGraphSection);

            graphSection = newGraphSection;
            dialog.close();
        });

        Button resetButton = new Button("RESET");
        resetButton.setMinWidth(windowWidth * 0.05);
        resetButton.setStyle("-fx-background-color:rgb(143, 134, 16);" +
                             "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                             "-fx-text-fill: white;" +
                             "-fx-font-size: 14px;" +
                             "-fx-font-weight: bold;" +
                             "-fx-padding: 10;" +
                             "-fx-background-radius: 5;");
                            
        resetButton.setOnAction(e -> {
            formulaVariables.setDefaultInitialPopulations();
            inputFields[0].setText(String.valueOf(formulaVariables.getCattleInitialPopulation()));
            inputFields[1].setText(String.valueOf(formulaVariables.getHorseInitialPopulation()));
            inputFields[2].setText(String.valueOf(formulaVariables.getDeerInitialPopulation()));
            inputFields[3].setText(String.valueOf(formulaVariables.getWolfInitialPopulation()));
            inputFields[4].setText(String.valueOf(formulaVariables.getGrassInitialBiomass()));

            VBox newGraphSection = createSectionGraph();

            int graphIndex = ((VBox) scrollPane.getContent()).getChildren().indexOf(graphSection);
            ((VBox) scrollPane.getContent()).getChildren().set(graphIndex, newGraphSection);

            graphSection = newGraphSection;

            dialog.close();
        });                     

        Button closeButton = new Button("CLOSE");
        closeButton.setMinWidth(windowWidth * 0.05);
        closeButton.setStyle("-fx-background-color: #E07A5F;" +
                             "-fx-font-family: " + Util.getBoldFont(14).getFamily() + ";" +
                             "-fx-text-fill: white;" +
                             "-fx-font-size: 14px;" +
                             "-fx-font-weight: bold;" +
                             "-fx-padding: 10;" +
                             "-fx-background-radius: 5;");
        closeButton.setOnAction(e -> dialog.close());

        HBox buttonBox = new HBox(20, submitButton, resetButton, closeButton);
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

    /**
     * Returns the root of the page, which is a BorderPane containing all the page's content.
     * @return the root of the page
     */
    @Override
    public BorderPane getRoot() {
        return root;
    }

    /**
     * Returns the name of the current page.
     * 
     * @return the name of the page as a string
     */
    @Override
    public String getPageName() {
        return pageName;
    }
}
