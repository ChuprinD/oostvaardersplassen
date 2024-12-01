package view;

import controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Util;

public class MainPage implements Page {
    private final String pageName = "Main";
    private final BorderPane root;
    private final ScrollPane scrollPane;
    private final HBox sectionAboutPreserve;
    private final HBox sectionAboutAnimals;

    public MainPage(NavigationController navigationController) {
        root = new BorderPane();

        double windowWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

        // Header
        HBox header = CommonComponents.createHeader(pageName, navigationController, windowWidth, windowHeight);
        root.setTop(header);

        // Main content sections
        VBox mainContent = new VBox();
        HBox sectionHome = createSectionHome(windowWidth, windowHeight);
        sectionAboutPreserve = createSectionAboutPreserve(windowWidth, windowHeight);
        sectionAboutAnimals = createSectionAboutAnimals(windowWidth, windowHeight);

        mainContent.getChildren().addAll(sectionHome, sectionAboutPreserve, sectionAboutAnimals);

        // Scroltlable pane for the main content
        scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        

        root.setCenter(scrollPane);

        // Footer
        //HBox footer = HeaderFooterFactory.createFooter(navigationController, windowWidth, windowHeight);
        //root.setBottom(footer);
    }

    public static MainPage createPageAndScroll(NavigationController navigationController, String scrollToSection) {
        MainPage mainPage = new MainPage(navigationController);
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();
    
        javafx.application.Platform.runLater(() -> {
            if ("About".equalsIgnoreCase(scrollToSection)) {
                double scrollTarget = (mainPage.sectionAboutPreserve.getLayoutY() + windowHeight * 0.2 * 2)
                        / mainPage.scrollPane.getContent().getBoundsInLocal().getHeight();
                mainPage.scrollPane.setVvalue(scrollTarget);
            }

            if ("Animals".equalsIgnoreCase(scrollToSection)) {
                double scrollTarget = (mainPage.sectionAboutAnimals.getLayoutY() + windowHeight * 0.2 * 4)
                        / mainPage.scrollPane.getContent().getBoundsInLocal().getHeight();
                mainPage.scrollPane.setVvalue(scrollTarget);
            }
        });
    
        return mainPage;
    }

    // Section 1: Home
    private HBox createSectionHome(double windowWidth, double windowHeight) {
        Label title = new Label("Grey Wolves in Oostvaardersplassen");
        title.setStyle("-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold;");

        Label description = new Label(
                "Something Something Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n");
        description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");
        description.setWrapText(true);

        VBox textBlock = new VBox(windowHeight * 0.001, title, description);
        textBlock.setAlignment(Pos.CENTER);
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        // Replace the square with the carousel
        HBox imageBlock = createRectangleCarousel(windowWidth, windowHeight);

        // Combine text and carousel into one section
        HBox content = new HBox(windowWidth * 0.05, textBlock, imageBlock);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(windowHeight * 0.2, 0, windowHeight * 0.2, 0));
        return content;
    }

    // Section 2: About the Preserve
    public HBox createSectionAboutPreserve(double windowWidth, double windowHeight) {
        Label title = new Label("Oostvaardersplassen");
        title.setStyle("-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold;");

        Label description = new Label(
                "Something Something Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n"
                        + "SomethingSomething Something\n");
        description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");
        description.setWrapText(true);

        VBox textBlock = new VBox(windowHeight * 0.001, title, description);
        textBlock.setAlignment(Pos.CENTER);
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        Rectangle image = new Rectangle(windowWidth * 0.4, windowHeight * 0.35);
        image.setFill(Color.web("#808080"));
        image.setStroke(Color.BLACK);
        image.setStrokeWidth(2);

        // Combine text and image into one section
        HBox content = new HBox(windowWidth * 0.05, image, textBlock);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: lightgray;");
        content.setPadding(new Insets(windowHeight * 0.2, 0, windowHeight * 0.2, 0));
        return content;
    }

    // Section 3: About the Animals
    private HBox createSectionAboutAnimals(double windowWidth, double windowHeight) {
        HBox content = new HBox(windowWidth * 0.15);
        content.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            Label title = new Label("Animal" + (i + 1));
            title.setStyle("-fx-font-size: " + Util.getTitleFontSize(windowWidth, windowHeight) + "px; -fx-font-weight: bold;");

            Label description = new Label(
                    "Something Something\n"
                            + "SomethingSomething\n"
                            + "SomethingSomething\n"
                            + "SomethingSomething\n");
            description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth, windowHeight) + "px;");
            description.setWrapText(true);

            Rectangle square = new Rectangle(windowWidth * 0.2, windowHeight * 0.25);
            square.setFill(Color.web("#808080"));
            square.setStroke(Color.BLACK);
            square.setStrokeWidth(2);

            VBox animalBlock = new VBox(windowHeight * 0.001, square, title, description);
            animalBlock.setAlignment(Pos.CENTER);

            content.getChildren().add(animalBlock);
        }

        content.setPadding(new Insets(windowHeight * 0.2, 0, windowHeight * 0.2, 0));
        return content;
    }

    // Create carousel with rectangles
    private HBox createRectangleCarousel(double windowWidth, double windowHeight) {
        Color[] colors = {
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.ORANGE
        };

        final int[] currentIndex = {0};

        StackPane rectanglePane = new StackPane();
        rectanglePane.setMaxWidth(windowWidth * 0.4);
        rectanglePane.setMaxHeight(windowHeight * 0.4);

        Rectangle rectangle = new Rectangle(windowWidth * 0.45, windowHeight * 0.45);
        rectangle.setFill(colors[currentIndex[0]]);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);

        rectanglePane.getChildren().add(rectangle);

        HBox carouselDots = new HBox(10);
        carouselDots.setAlignment(Pos.CENTER);
        for (int i = 0; i < colors.length; i++) {
            Label dot = new Label("●");
            dot.setStyle("-fx-font-size: 20px; -fx-text-fill: #cccccc;");
            carouselDots.getChildren().add(dot);
        }

        updateDots(carouselDots, currentIndex[0]);

        // Left arrow
        Button leftArrow = new Button("<");
        leftArrow.setStyle(
                "-fx-background-radius: 50%; -fx-background-color: white; -fx-font-size: 18px;"
        );
        leftArrow.setOnAction(event -> {
            currentIndex[0] = (currentIndex[0] - 1 + colors.length) % colors.length;
            rectangle.setFill(colors[currentIndex[0]]);
            updateDots(carouselDots, currentIndex[0]);
        });

        // Right arrow
        Button rightArrow = new Button(">");
        rightArrow.setStyle(
                "-fx-background-radius: 50%; -fx-background-color: white; -fx-font-size: 18px;"
        );
        rightArrow.setOnAction(event -> {
            currentIndex[0] = (currentIndex[0] + 1) % colors.length;
            rectangle.setFill(colors[currentIndex[0]]);
            updateDots(carouselDots, currentIndex[0]);
        });

        StackPane.setAlignment(leftArrow, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightArrow, Pos.CENTER_RIGHT);
        StackPane.setMargin(leftArrow, new Insets(0, 10, 0, 10));
        StackPane.setMargin(rightArrow, new Insets(0, 10, 0, 10));
        rectanglePane.getChildren().addAll(leftArrow, rightArrow);

        VBox carouselBox = new VBox(10, rectanglePane, carouselDots);
        carouselBox.setAlignment(Pos.CENTER);
        return new HBox(carouselBox);
    }

    // Update the style of the active dot
    private void updateDots(HBox carouselDots, int activeIndex) {
        for (int i = 0; i < carouselDots.getChildren().size(); i++) {
            Label dot = (Label) carouselDots.getChildren().get(i);
            if (i == activeIndex) {
                dot.setStyle("-fx-font-size: 20px; -fx-text-fill: #00aaff;");
            } else {
                dot.setStyle("-fx-font-size: 20px; -fx-text-fill: #cccccc;");
            }
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    public String getPageName() {
        return pageName;
    }
}
