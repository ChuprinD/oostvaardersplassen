import javafx.application.Application;
import javafx.stage.Stage;
import controller.NavigationController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the navigation controller
        NavigationController navigationController = new NavigationController(primaryStage);

        // Show the main page
        navigationController.showPage("Main");

        // Set the title of the application
        primaryStage.setTitle("Preserve Application");

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(null);
        launch(args);
    }
}
