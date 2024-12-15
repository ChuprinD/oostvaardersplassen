package com.group3.view;

import com.group3.controller.NavigationController;
import com.group3.utils.Util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class MainPage implements Page {
    private final String pageName = "Home";
    private final BorderPane root;
    private ScrollPane scrollPane;
    private final HBox sectionAboutPreserve;
    private final HBox sectionAboutAnimals;
    private final double windowWidth;
    private final double windowHeight;

    public MainPage(NavigationController navigationController) {
        root = new BorderPane();

        windowWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
        windowHeight = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

        // Main content sections
        VBox mainContent = new VBox(windowHeight * 0.02);
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #1e4c40, #a8c28c); -fx-border-width: 0; -fx-border-color: transparent;");
        mainContent.setMinWidth(windowWidth);
        mainContent.setPadding(new Insets(0, windowWidth * 0.01, 0, windowWidth * 0.01));
        HBox sectionHome = createSectionHome();
        sectionAboutPreserve = createSectionAboutPreserve();
        sectionAboutAnimals = createSectionAboutAnimals();

        Region spacer = new Region();
        spacer.setMinHeight(windowHeight * 0.07);
        spacer.setMaxHeight(windowHeight * 0.07);
        spacer.setStyle("-fx-background-color: transparent;");

        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/WhiteWolf.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.1);
        imageView.setPreserveRatio(true);
        HBox bottomSpacer = new HBox(imageView);
        bottomSpacer.setAlignment(Pos.BOTTOM_CENTER);
        bottomSpacer.setPadding(new Insets(windowHeight * 0.02, 0, windowHeight * 0.02, 0));

        mainContent.getChildren().addAll(spacer, sectionHome, sectionAboutPreserve, sectionAboutAnimals, bottomSpacer);

        // Scrollable pane for the main content
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(mainContent);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: transparent; -fx-border-width: 0; -fx-border-color: transparent; -fx-padding: 0;");
        scrollPane.setMaxWidth(windowWidth);
        scrollPane.setFitToWidth(true);

        // Header  
        CommonComponents commonComponents = new CommonComponents(windowWidth, windowHeight, true, scrollPane, 0, 0);
        HBox header = commonComponents.createHeader(pageName, navigationController);
        HBox headerContainer = new HBox(header);
        headerContainer.setMinWidth(windowWidth);
        headerContainer.setMaxHeight(windowHeight * 0.07);
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setStyle("-fx-background-color: transparent;");

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().addAll(scrollPane, headerContainer);
        stackPane.setAlignment(Pos.TOP_CENTER);

        root.setCenter(stackPane);

        Platform.runLater(() -> {
            commonComponents.setSectionsPosition(sectionAboutAnimals.getLayoutY() + windowHeight * 0.8, sectionAboutPreserve.getLayoutY() + windowHeight * 0.35);
        });
        
    }

    /**
     * Creates a new MainPage, and then scrolls to a specific section based on the parameter given.
     * The section parameter should be either "About" or "Animals", and the method will scroll to the
     * About section or Animals section respectively. If the parameter is not one of the above values,
     * it will not scroll to any section.
     * @param navigationController The NavigationController that will be used to navigate from this page.
     * @param scrollToSection The section to scroll to. Must be either "About" or "Animals".
     * @return The newly created MainPage.
     */
    public static MainPage createPageAndScroll(NavigationController navigationController, String scrollToSection) {
        MainPage mainPage = new MainPage(navigationController);
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();
    
        javafx.application.Platform.runLater(() -> {
            double targetValue = 0;
            if ("About".equalsIgnoreCase(scrollToSection)) {
                targetValue = (mainPage.sectionAboutPreserve.getLayoutY() + windowHeight * 0.35)
                        / mainPage.scrollPane.getContent().getBoundsInLocal().getHeight();
            }

            if ("Animals".equalsIgnoreCase(scrollToSection)) {
                targetValue = (mainPage.sectionAboutAnimals.getLayoutY() + windowHeight * 0.8)
                        / mainPage.scrollPane.getContent().getBoundsInLocal().getHeight();
            }

            double currentValue = mainPage.scrollPane.getVvalue();
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(mainPage.scrollPane.vvalueProperty(), currentValue)),
                                             new KeyFrame(Duration.seconds(1), new KeyValue(mainPage.scrollPane.vvalueProperty(), targetValue)));
            timeline.play();
        });
        
        return mainPage;
    }

    /**
     * Creates the home section of the main page, containing a title and description for the
     * ecological consequences of introducing grey wolves on deer, cattle, and horse populations
     * in a preserve.
     * @return The newly created section.
     */
    private HBox createSectionHome() {
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/Blank diagram-Page1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.7);
        imageView.setPreserveRatio(true);


        Label title = new Label("Ecological Consequences of\n" +
                                "Introducing Grey Wolves on\n" +
                                "Deer, Cattle, and Horse\n" +
                                "Populations in a Preserve");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px;");
        title.setTextFill(Color.BLACK);
        title.setWrapText(true);
        title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
        title.setTextAlignment(TextAlignment.RIGHT);

        Label description = new Label(
                "Something Something Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something");
        
        description.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth, windowHeight)));
        description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");
        description.setWrapText(true);
        description.setTextFill(Color.BLACK);
        description.setTextAlignment(TextAlignment.RIGHT);

        VBox textBlock = new VBox(windowHeight * 0.02, title, description);
        textBlock.setAlignment(Pos.CENTER_RIGHT);
        textBlock.setPadding(new Insets(0, 0, 0, windowWidth * 0.05));
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        HBox content = new HBox(windowWidth * 0.05, imageView, textBlock);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #a8c28c;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        content.setMinHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        content.setMaxHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        content.setOpacity(0.9);
        return content;
    }

    /**
     * Creates a section titled "Oostvaarderplassen nature preserve" with a styled title,
     * description, and image. The section is visually structured with a VBox for text content
     * and an ImageView for the image, all contained within an HBox. The section is styled with
     * specific fonts, colors, and dimensions, and is intended to be part of the main page layout.
     *
     * @return An HBox containing the styled title, description, and image representing
     *         the Oostvaarderplassen nature preserve.
     */
    public HBox createSectionAboutPreserve() {
        Label title = new Label("Oostvaarderplassen nature\n" + "preserve");
        title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
        title.setTextFill(Color.BLACK);
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-text-fill: #82755b;");

        Label description = new Label(
                "Something Something Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something");
        description.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth, windowHeight)));
        description.setWrapText(true);
        description.setTextFill(Color.BLACK);
        description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px; -fx-text-fill: #82755b;");
        description.setTextAlignment(TextAlignment.LEFT);

        VBox textBlock = new VBox(windowHeight * 0.02, title, description);
        textBlock.setAlignment(Pos.CENTER_LEFT);
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/Blank diagram-Page1(1).png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.7);
        imageView.setPreserveRatio(true);

        // Combine text and image into one section
        HBox content = new HBox(windowWidth * 0.05, textBlock, imageView);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #ffffff;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        content.setMinHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        content.setMaxHeight(windowHeight - windowHeight * 0.07 - 3 * windowHeight * 0.02 + 3);
        content.setOpacity(0.7);
        return content;
    }

    
    /**
     * Creates a section displaying information about different animals. Each animal
     * is represented with an image, a title, and a description. The layout consists
     * of a horizontally arranged box containing vertically aligned blocks for each
     * animal. The section is styled with specific fonts, colors, and dimensions,
     * and has a semi-transparent background.
     *
     * @return An HBox containing the styled representation of each animal.
     */
    private HBox createSectionAboutAnimals() {
        HBox content = new HBox(windowWidth * 0.15);
        content.setAlignment(Pos.CENTER);

        String[] animalNames = {"Animal1", "Animal2", "Animal3"};
        for (String animalName : animalNames) {
            Label title = new Label(animalName);
            title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth, windowHeight)));
            title.setTextFill(Color.BLACK);
            title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px;");

            Label description = new Label("Something Something\nSomethingSomething\nSomethingSomething\nSomethingSomething");
            description.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth, windowHeight)));
            description.setWrapText(true);
            description.setTextFill(Color.BLACK);
            description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");

            Image image = new Image(CommonComponents.class.getResourceAsStream("/images/Blank diagram-Page1.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(windowHeight * 0.3);
            imageView.setPreserveRatio(true);

            VBox textBox = new VBox(windowHeight * 0.001, title, description);
            textBox.setAlignment(Pos.CENTER);

            VBox animalBlock = new VBox(windowHeight * 0.009, imageView, textBox);
            animalBlock.setAlignment(Pos.CENTER);

            content.getChildren().add(animalBlock);
        }

        content.setMinHeight(windowHeight - windowHeight * 0.07);
        content.setStyle("-fx-background-color: #a8c28c;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");                
        content.setMinHeight(windowHeight - windowHeight * 0.07 - 2 * windowHeight * 0.02 + 5);
        content.setMaxHeight(windowHeight - windowHeight * 0.07 - 2 * windowHeight * 0.02 + 5);
        content.setOpacity(0.9);
        return content;
    }


    /**
     * Returns the root of the page, which is a BorderPane containing all the page's content.
     * @return the root of the page
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * Returns the name of the page.
     * @return the name of the page
     */
    public String getPageName() {
        return pageName;
    }
}