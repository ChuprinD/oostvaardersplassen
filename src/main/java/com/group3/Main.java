package com.group3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import com.group3.controller.NavigationController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the navigation controller
        NavigationController navigationController = new NavigationController(primaryStage);

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Show the main page
        navigationController.showPage("Home");

        // Set the title of the application
        primaryStage.setTitle("Preserve Application");

        // Show the stage
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(null);
        launch(args);
    }
}
