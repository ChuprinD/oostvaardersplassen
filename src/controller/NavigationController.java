package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController {
    private final Stage primaryStage; // The primary application stage (window)
    private final PageManager pageManager; // Manages all the pages of the application

    public NavigationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.pageManager = new PageManager(this);
    }

    public void showPage(String pageName) {
        // Create a new scene using the requested page
        Scene scene = new Scene(pageManager.getPage(pageName));
        primaryStage.setScene(scene);

        // Set the primary stage size to match the screen dimensions
        primaryStage.setWidth(javafx.stage.Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(javafx.stage.Screen.getPrimary().getBounds().getHeight());

        // Maximize the stage to fill the screen
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
