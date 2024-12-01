package view;

import controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CommonComponents {

    // Creates the header section with logo, navigation buttons, and dropdown menu
    public static HBox createHeader(String pageName, NavigationController navigationController, double windowWidth,
            double windowHeight) {
        // Logo on the left
        Label logo = new Label("Logo");
        logo.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 1; -fx-alignment: center; -fx-font-size: 20px;");
        logo.setMinSize(windowWidth * 0.08, windowHeight * 0.04);

        HBox leftBox = new HBox(logo);
        leftBox.setPadding(new Insets(0, windowHeight * 0.01, 0, 0)); // Add padding on the right
        leftBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the header width for the left box
        leftBox.setAlignment(Pos.CENTER);

        // Center section with navigation buttons
        HBox centerBox = createButtons(navigationController, windowWidth, windowHeight);

        // Dropdown menu on the right
        ComboBox<String> dropdownMenu = new ComboBox<>();
        dropdownMenu.getItems().addAll("Main", "Q1", "Q2", "Q3", "Q4", "Q5");
        dropdownMenu.setOnAction(e -> navigationController.showPage(dropdownMenu.getValue())); // Handle page switching
        dropdownMenu.setValue(pageName); // Set current page
        dropdownMenu.setStyle("-fx-font-size: 20px; -fx-faint-focus-color: transparent; -fx-focus-color: #cccccc;");
        dropdownMenu.setMinSize(windowWidth * 0.06, windowHeight * 0.03);

        HBox rightBox = new HBox(dropdownMenu);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the header width for the right box

        // Combine all sections into the header
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #a9a9a9;" + 
                        "-fx-alignment: center-left;" +
                        "-fx-background-radius: 0 0 15 15;");              
        header.setMinHeight(windowHeight * 0.07);
        header.getChildren().addAll(leftBox, centerBox, rightBox);

        return header;
    }

    // Creates the footer section with logo and navigation buttons
    public static HBox createFooter(NavigationController navigationController, double windowWidth,
            double windowHeight) {
        // Logo on the left
        Label logo = new Label("Logo");
        logo.setStyle(
                "-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 1; -fx-alignment: center; -fx-font-size: 20px;");
        logo.setMinSize(windowWidth * 0.08, windowHeight * 0.04);

        HBox leftBox = new HBox(logo);
        leftBox.setPadding(new Insets(0, windowHeight * 0.01, 0, 0)); // Add padding on the right
        leftBox.setMinWidth(windowWidth / 3); // Allocate 1/3 of the footer width for the left box
        leftBox.setAlignment(Pos.CENTER);

        // Center section with navigation buttons
        HBox centerBox = createButtons(navigationController, windowWidth, windowHeight);

        // Combine all sections into the footer
        HBox footer = new HBox();
        footer.setStyle("-fx-background-color: #a9a9a9; -fx-alignment: center-left;"); // Light gray background
        footer.setMinHeight(windowHeight * 0.07);
        footer.getChildren().addAll(leftBox, centerBox);

        return footer;
    }

    // Creates navigation buttons for the center section
    private static HBox createButtons(NavigationController navigationController, double windowWidth,
            double windowHeight) {
        double buttonWidth = windowWidth * 0.06;
        double buttonHeight = windowHeight * 0.03;

        // Home button
        Button homeButton = new Button("Home");
        homeButton.setMinSize(buttonWidth, buttonHeight);
        homeButton.setStyle("-fx-font-size: 20px;");
        homeButton.setOnAction(e -> navigationController.showPage("Main")); // Handle navigation
        homeButton.setFocusTraversable(false);

        // About button
        Button aboutButton = new Button("About");
        aboutButton.setMinSize(buttonWidth, buttonHeight);
        aboutButton.setStyle("-fx-font-size: 20px;");
        aboutButton.setOnAction(e -> navigationController.showPage("Main.About")); // Handle navigation
        aboutButton.setFocusTraversable(false);

        // Placeholder button
        Button somethingButton = new Button("[something]");
        somethingButton.setMinSize(buttonWidth, buttonHeight);
        somethingButton.setStyle("-fx-font-size: 20px;");
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
