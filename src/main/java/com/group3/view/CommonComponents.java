package com.group3.view;

import com.group3.controller.NavigationController;
import com.group3.utils.Util;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CommonComponents {

    // Creates the header section with logo, navigation buttons, and dropdown menu
    public static HBox createHeader(String pageName, NavigationController navigationController, double windowWidth,
            double windowHeight) {
        // Logo on the left
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/main-logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(windowWidth * 0.07); 
        imageView.setFitHeight(windowHeight * 0.07); 

        HBox leftBox = new HBox(imageView);
        leftBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the header width for the left box
        leftBox.setAlignment(Pos.CENTER);

        // Center section with navigation buttons
        HBox centerBox = createButtons(navigationController, windowWidth, windowHeight);

        // Dropdown menu on the right
        ComboBox<String> dropdownMenu = new ComboBox<>();
        dropdownMenu.getItems().addAll("Main", "Q1", "Q2", "Q3", "Q4", "Q5");
        dropdownMenu.setOnAction(e -> navigationController.showPage(dropdownMenu.getValue())); // Handle page switching
        dropdownMenu.setValue(pageName); // Set current page
        int font_size = Util.getButtonFontSize(windowWidth, windowHeight);
        dropdownMenu.setStyle("-fx-font-size: " + font_size + "px;" +
                                "-fx-font-family: " + Util.getVoltaireFont(font_size).getFamily() + ";" +
                                "-fx-focus-color: transparent;" + 
                                "-fx-faint-focus-color: transparent;" + 
                                "-fx-background-radius: 15;" +
                                "-fx-border-radius: 15;" +
                                "-fx-border-width: 2px;");
                                
        dropdownMenu.setMinSize(windowWidth * 0.06, windowHeight * 0.03);

        HBox rightBox = new HBox(dropdownMenu);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the header width for the right box

        // Combine all sections into the header
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #a8c28c;" +
                        "-fx-border-color: transparent transparent gray transparent;" +
                        "-fx-border-width: 0 0 2 0;" +
                        "-fx-border-style: solid;");              
        header.setMinHeight(windowHeight * 0.07);
        header.getChildren().addAll(leftBox, centerBox, rightBox);

        return header;
    }

    // Creates navigation buttons for the center section
    private static HBox createButtons(NavigationController navigationController, double windowWidth,
            double windowHeight) {
        double buttonWidth = windowWidth * 0.06;
        double buttonHeight = windowHeight * 0.03;

        // Home button
        Button homeButton = new Button("Home");
        homeButton.setMinSize(buttonWidth, buttonHeight);
        homeButton.setFont(Util.getVoltaireFont(Util.getButtonFontSize(windowWidth, windowHeight)));
        homeButton.setStyle("-fx-background-radius: 15;" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-width: 2px;");
        homeButton.setOnAction(e -> navigationController.showPage("Main")); // Handle navigation
        homeButton.setFocusTraversable(false);

        // About button
        Button aboutButton = new Button("About");
        aboutButton.setMinSize(buttonWidth, buttonHeight);
        aboutButton.setFont(Util.getVoltaireFont(Util.getButtonFontSize(windowWidth, windowHeight)));
        aboutButton.setStyle("-fx-background-radius: 15;" +
                             "-fx-border-radius: 15;" +
                             "-fx-border-width: 2px;");
        aboutButton.setOnAction(e -> navigationController.showPage("Main.About")); // Handle navigation
        aboutButton.setFocusTraversable(false);

        // Placeholder button
        Button somethingButton = new Button("[something]");
        somethingButton.setMinSize(buttonWidth, buttonHeight);
        somethingButton.setFont(Util.getVoltaireFont(Util.getButtonFontSize(windowWidth, windowHeight)));
        somethingButton.setStyle("-fx-background-radius: 15;" +
                                 "-fx-border-radius: 15;" +
                                 "-fx-border-width: 2px;");
        somethingButton.setOnAction(e -> navigationController.showPage("Main.Animals")); // Handle navigation
        somethingButton.setFocusTraversable(false);

        // Center buttons with spacing
        HBox centerBox = new HBox(windowWidth * 0.02, homeButton, aboutButton, somethingButton);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the header/footer width for the buttons

        return centerBox;
    }

    // Show a confirmation pop-up for downloading
    public static void showDownloadPopup() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Download Confirmation");
        alert.setHeaderText("Do you want to download 'Graph Title' as a PDF?");
        alert.showAndWait();
    }
}
