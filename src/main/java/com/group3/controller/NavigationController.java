package com.group3.controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NavigationController {
    private final Stage primaryStage; // The primary application stage (window)
    private final PageManager pageManager; // Manages all the pages of the application

    public NavigationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.pageManager = new PageManager(this);
    }

    /**
     * Shows the page with the given name in the main application window. The method
     * creates a new scene with the requested page and sets it as the scene of the
     * primary stage. It also sets the application icon and maximizes the stage
     * to fill the screen.
     * 
     * @param pageName the name of the page to show. Must be one of the page names
     *                 registered in the PageManager.
     */
    public void showPage(String pageName) {
        // Create a new scene using the requested page
        Scene scene = new Scene(pageManager.getPage(pageName));
        primaryStage.setScene(scene);
        
        // Set the application icon
        primaryStage.getIcons().add(new Image(NavigationController.class.getResourceAsStream("/images/icons/main-logo.png")));

        // Set the primary stage size to match the screen dimensions
        primaryStage.setWidth(javafx.stage.Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(javafx.stage.Screen.getPrimary().getBounds().getHeight());

        // Maximize the stage to fill the screen
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    /**
     * Gets the primary stage of the application. This is the stage that
     * contains all the pages of the application.
     * @return the primary stage of the application
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Gets the page manager object. The page manager is responsible for
     * initializing and retrieving all the pages of the application.
     * @return the page manager object
     */
    public PageManager getPageManager() {
        return pageManager;
    }
}
