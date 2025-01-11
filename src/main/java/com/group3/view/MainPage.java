package com.group3.view;

import java.util.HashMap;

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
    private BorderPane root;
    private ScrollPane scrollPane;
    private HBox sectionAboutPreserve;
    private HBox sectionAboutAnimals;
    private final double windowWidth;
    private final double windowHeight;
    private final double topBarHeight = 23;

    public MainPage(NavigationController navigationController) {
        root = new BorderPane();

        windowWidth = javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();
        windowHeight = javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

        CommonComponents commonComponents = new CommonComponents(windowWidth, windowHeight, true, scrollPane, null, null);

        // Main content sections
        double verticalGapBetweenSections = windowHeight * 0.03;
        double horizontalGap = windowWidth * 0.02;

        VBox mainContent = new VBox(verticalGapBetweenSections);
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #1e4c40, #a8c28c); -fx-border-width: 0; -fx-border-color: transparent;");
        mainContent.setMinWidth(windowWidth);
        mainContent.setPadding(new Insets(0, horizontalGap, 0, horizontalGap));
        HBox sectionHome = createSectionHome(verticalGapBetweenSections, horizontalGap, commonComponents.getHeaderHeight());
        sectionAboutPreserve = createSectionAboutPreserve(verticalGapBetweenSections, horizontalGap, commonComponents.getHeaderHeight());
        sectionAboutAnimals = createSectionAboutAnimals(verticalGapBetweenSections, horizontalGap, commonComponents.getHeaderHeight());

        Region spacer = new Region();
        spacer.setMinHeight(commonComponents.getHeaderHeight());
        spacer.setMaxHeight(commonComponents.getHeaderHeight());
        spacer.setStyle("-fx-background-color: transparent;");

        HBox footer = commonComponents.createFooter();

        mainContent.getChildren().addAll(spacer, sectionHome, sectionAboutPreserve, sectionAboutAnimals, footer);

        // Scrollable pane for the main content
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(mainContent);
        scrollPane.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: transparent; -fx-border-width: 0; -fx-border-color: transparent; -fx-padding: 0;");
        scrollPane.setMaxWidth(windowWidth);
        scrollPane.setMinWidth(windowWidth);
        scrollPane.setPannable(true);
        scrollPane.layout();

        commonComponents.setScrollPane(scrollPane);

        // Header   
        HBox header = commonComponents.createHeader(pageName, navigationController, horizontalGap);
        HBox headerContainer = new HBox(header);
        headerContainer.setMinWidth(windowWidth - horizontalGap * 2);
        headerContainer.setMaxWidth(windowWidth - horizontalGap * 2);
        headerContainer.setMaxHeight(commonComponents.getHeaderHeight());
        headerContainer.setMinHeight(commonComponents.getHeaderHeight());
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setStyle("-fx-background-color: transparent;");

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().addAll(scrollPane, headerContainer);
        stackPane.setAlignment(Pos.TOP_CENTER);

        root.setCenter(stackPane);

        Platform.runLater(() -> {
            commonComponents.setSectionsPosition(sectionAboutPreserve, sectionAboutAnimals);
        });
    }

    
    /**
     * Creates a new MainPage and scrolls to the given section.
     * 
     * @param navigationController the NavigationController that will be used to navigate from this page.
     * @param scrollToSection the name of the section to scroll to. Must be "About" or "Animals".
     * @return the newly created page.
     */
    public static MainPage createPageAndScroll(NavigationController navigationController, String scrollToSection) {
        MainPage mainPage = new MainPage(navigationController);
        double windowHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();
    
        javafx.application.Platform.runLater(() -> {
            double targetCenterY = 0;
            double contentHeight = mainPage.scrollPane.getContent().getBoundsInLocal().getHeight();
            double viewportHeight = mainPage.scrollPane.getViewportBounds().getHeight();

            if ("About".equalsIgnoreCase(scrollToSection)) {
                double targetY = mainPage.sectionAboutPreserve.getBoundsInParent().getMinY();
                targetCenterY = targetY - (viewportHeight / 2)
                        + (mainPage.sectionAboutPreserve.getBoundsInLocal().getHeight() / 2) - windowHeight * 0.07 / 2;
            }

            if ("Animals".equalsIgnoreCase(scrollToSection)) {
                double targetY = mainPage.sectionAboutAnimals.getBoundsInParent().getMinY();
                targetCenterY = targetY - (viewportHeight / 2)
                        + (mainPage.sectionAboutAnimals.getBoundsInLocal().getHeight() / 2) - windowHeight * 0.07 / 2;
            }
            
            double targetValue = targetCenterY / (contentHeight - viewportHeight);
            targetValue = Math.max(0, Math.min(1, targetValue));
            double currentValue = mainPage.scrollPane.getVvalue();
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(mainPage.scrollPane.vvalueProperty(), currentValue)),
                                             new KeyFrame(Duration.seconds(1), new KeyValue(mainPage.scrollPane.vvalueProperty(), targetValue)));
            timeline.play();
        });
        
        return mainPage;
    }

    /**
     * Creates the "Home" section for the main page, featuring an image and text
     * content that introduces the ecological balance initiative in the
     * Oostvaardepreserve. The section highlights the reintroduction of gray wolves and
     * its impact on predator-prey dynamics, particularly concerning deer, cattle, and
     * horse populations. The layout consists of an HBox containing an ImageView and
     * a VBox with the title and description labels, styled with specific fonts, colors,
     * and dimensions.
     *
     * @param verticalGapBetweenSections The vertical gap between sections.
     * @param horizontalGap The horizontal gap between elements.
     * @param headerHeight The height of the header.
     * @return An HBox containing the image and text content for the home section.
     */
    private HBox createSectionHome(double verticalGapBetweenSections, double horizontalGap, double headerHeight) {
        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/pictures/Page1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.7);
        imageView.setPreserveRatio(true);

        Label title = new Label("Ecological Balance in the Oostvaardepreserve:\n" +
                "Introducing Grey Wolves");
        title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth)));
        title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth) + "px;");
        title.setTextFill(Color.BLACK);
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.RIGHT);

        Label descriptionFirst = new Label("Exploring the impacts of predator-prey dynamics on\n" +
                "deer, cattle, and horse populations within a preserved ecosystem");
        descriptionFirst.setFont(Util.getBoldFont(Util.getRegularFontSize(windowWidth)));
        descriptionFirst
                .setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px;");
        descriptionFirst.setTextFill(Color.BLACK);
        descriptionFirst.setWrapText(true);
        descriptionFirst.setTextAlignment(TextAlignment.RIGHT);

        Label descriptionSecond = new Label("The Oostvaardepreserve is embarking on a groundbreaking initiative\n" +
                "to reintroduce gray wolves as a means of restoring natural balance\n" +
                "within its diverse ecosystem. By examining the interactions between\n" +
                "predators and prey, this project aims to foster biodiversity, stabilize\n" +
                "population dynamics, and ensure long-term ecological health for species\n" +
                "such as deer, cattle, and horses");

        descriptionSecond.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth)));
        descriptionSecond.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px;");
        descriptionSecond.setWrapText(true);
        descriptionSecond.setTextFill(Color.BLACK);
        descriptionSecond.setTextAlignment(TextAlignment.RIGHT);

        VBox descriptionBox = new VBox(windowHeight * 0.001, descriptionFirst, descriptionSecond);
        descriptionBox.setAlignment(Pos.CENTER_RIGHT);

        VBox textBlock = new VBox(windowHeight * 0.02, title, descriptionBox);
        textBlock.setAlignment(Pos.CENTER_RIGHT);
        textBlock.setPadding(new Insets(0, 0, 0, windowWidth * 0.05));
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        HBox content = new HBox(imageView, textBlock);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #a8c28c;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6");
        content.setMaxWidth(windowWidth - 2 * horizontalGap);
        content.setMinWidth(windowWidth - 2 * horizontalGap);
        content.setMinHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections - topBarHeight);
        content.setMaxHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections - topBarHeight);
        content.setOpacity(0.9);
        return content;
    }
    
    /**
     * Creates the "About Preserve" section for the main page, featuring an
     * image and text content that highlights the ecological significance and
     * biodiversity of the Oostvaardersplassen Nature Preserve. The section
     * includes descriptive labels covering the preserve's wetlands, grasslands,
     * and forests, emphasizing its role as a sanctuary for diverse flora and fauna.
     * The layout consists of an HBox containing a VBox with the title and 
     * description labels and an ImageView, styled with specific fonts, colors, 
     * and dimensions.
     *
     * @param verticalGapBetweenSections The vertical gap between sections.
     * @param horizontalGap The horizontal gap between elements.
     * @param headerHeight The height of the header.
     * @return An HBox containing the image and text content for the "About Preserve" section.
     */
    public HBox createSectionAboutPreserve(double verticalGapBetweenSections, double horizontalGap, double headerHeight) {
        Label title = new Label("Oostvaardersplassen Nature Preserve:\n" + "A Unique Haven for Wildlife");
        title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth)));
        title.setTextFill(Color.BLACK);
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth)
                + "px; -fx-text-fill: #82755b;");

        Label descriptionFirst = new Label("A protected area showcasing the delicate interplay\n" +
                                           "of wetlands, grasslands, and forests, supporting diverse flora and fauna");
        descriptionFirst.setFont(Util.getBoldFont(Util.getRegularFontSize(windowWidth)));
        descriptionFirst.setStyle("-fx-font-weight: bold;  -fx-text-fill: #82755b; -fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px;");
        descriptionFirst.setWrapText(true);
        descriptionFirst.setTextAlignment(TextAlignment.LEFT);
        
        Label descriptionSecond = new Label("The Oostvaardersplassen nature preserve is a remarkable\n" +
                                            "sanctuary in the Netherlands, celebrated for its rich\n" +
                                            "biodiversity and ecological significance. Spanning expansive\n" +
                                            "wetlands, open grasslands, and dense forests, it provides\n " +
                                            "a vital habitat for numerous species, including deer, wild horses,\n" +
                                            "and a wide variety of bird-life. This preserve serves as a\n" +
                                            "living example of natural processes in action, offering\n" +
                                            "insights into conservation and rewilding efforts");
        
        descriptionSecond.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth)));
        descriptionSecond.setWrapText(true);
        descriptionSecond.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px; -fx-text-fill: #82755b;");
        descriptionSecond.setTextAlignment(TextAlignment.LEFT);

        VBox descriptionBox = new VBox(windowHeight * 0.001, descriptionFirst, descriptionSecond);
        descriptionBox.setAlignment(Pos.CENTER_LEFT);

        VBox textBlock = new VBox(windowHeight * 0.02, title, descriptionBox);
        textBlock.setAlignment(Pos.CENTER_LEFT);
        textBlock.setMinWidth(windowWidth / 2 - windowWidth * 0.05);

        Image image = new Image(CommonComponents.class.getResourceAsStream("/images/pictures/Page2.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(windowHeight * 0.7);
        imageView.setPreserveRatio(true);

        // Combine text and image into one section
        HBox content = new HBox(windowWidth * 0.01, textBlock, imageView);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #ffffff;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        content.setMaxWidth(windowWidth - 2 * horizontalGap);
        content.setMinWidth(windowWidth - 2 * horizontalGap);
        content.setMinHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections - topBarHeight);
        content.setMaxHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections- topBarHeight);
        content.setOpacity(0.7);
        return content;
    }

    /**
     * Creates the section about the animals in the Oostvaardersplassen
     * preserve. The section contains three blocks, each describing a
     * different species (deer, cattle, horses) and featuring an image.
     * The section is styled with a specific width, height, and padding,
     * and the text is aligned to the center.
     *
     * @param verticalGapBetweenSections The vertical gap between sections.
     * @param horizontalGap The horizontal gap between elements.
     * @param headerHeight The height of the header.
     * @return An HBox containing the section about the animals.
     */
    private HBox createSectionAboutAnimals(double verticalGapBetweenSections, double horizontalGap, double headerHeight) {
        HBox content = new HBox(windowWidth * 0.03);
        content.setAlignment(Pos.CENTER);

        String[] animalNames = { "Deer", "Cattle", "Horses" };
        HashMap<String, String> animalDescription = new HashMap<>();
        animalDescription.put("Deer", "Deer are key herbivores in the Oostvaardersplassen,\n" +
                                          "contributing to the natural dynamics of the preserve.\n" +
                                          "Their grazing influences vegetation structure, while\n" +
                                          "their movements and behavior enrich the soil and\n" +
                                          "provide essential resources for other organisms in the\n" +
                                          "ecosystem");
        animalDescription.put("Cattle", "The cattle in the Oostvaardersplassen serve as\n" +
                                            "natural landscapers, grazing on grasses and shrubs\n" +
                                            "to prevent overgrowth. Their presence helps maintain\n" +
                                            "open habitats, encouraging the proliferation of plant\n" +
                                            "and insect species and supporting a wide range of\n" +
                                            "wildlife");
        animalDescription.put("Horses", "Wild horses are a cornerstone of the\n" +
                                            "Oostvaardersplassen ecosystem, shaping the\n" +
                                            "environment through their grazing patterns. By\n" +
                                            "selectively feeding on grasses and other vegetation,\n" +
                                            "they promote habitat diversity, enabling the\n" +
                                            "coexistence of multiple plant and animal species");

        HashMap<String, String> animalPictures = new HashMap<>();
        animalPictures.put("Deer", "Deer");
        animalPictures.put("Cattle", "Cattle");
        animalPictures.put("Horses", "Horse");

        for (String animalName : animalNames) {
            Label title = new Label(animalName);
            title.setFont(Util.getBoldFont(Util.getTitleFontSize(windowWidth)));
            title.setTextFill(Color.BLACK);
            title.setStyle("-fx-font-weight: bold; -fx-font-size: " + Util.getTitleFontSize(windowWidth)+ "px;");

            Label description = new Label(animalDescription.get(animalName));
            description.setFont(Util.getRegularFont(Util.getRegularFontSize(windowWidth)));
            description.setWrapText(true);
            description.setTextFill(Color.BLACK);
            description.setStyle("-fx-font-size: " + Util.getRegularFontSize(windowWidth) + "px;");
            description.setTextAlignment(TextAlignment.CENTER);

            String imagePath = "/images/pictures/" + animalPictures.get(animalName) + ".png";
            Image image = new Image(CommonComponents.class.getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(windowHeight * 0.3);
            imageView.setFitWidth(windowHeight * 0.3);

            VBox textBox = new VBox(windowHeight * 0.001, title, description);
            textBox.setAlignment(Pos.CENTER);

            VBox animalBlock = new VBox(windowHeight * 0.009, imageView, textBox);
            animalBlock.setAlignment(Pos.CENTER);

            content.getChildren().add(animalBlock);
        }

        content.setStyle("-fx-background-color: #a8c28c;" +
                         "-fx-border-width: 2;" +
                         "-fx-border-radius: 6;" +
                         "-fx-background-radius: 6");
        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(windowWidth - 2 * horizontalGap);
        content.setMinWidth(windowWidth - 2 * horizontalGap);              
        content.setMinHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections- topBarHeight);
        content.setMaxHeight(windowHeight - headerHeight - 2 * verticalGapBetweenSections - topBarHeight);
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