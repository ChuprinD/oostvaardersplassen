package controller;

import javafx.scene.layout.Pane;
import view.MainPage;
import view.GenericPage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PageManager {
    private final NavigationController navigationController;
    private final Map<String, Supplier<Pane>> pages = new HashMap<>();

    public PageManager(NavigationController navigationController) {
        this.navigationController = navigationController;
        initializePages();
    }

    // Initialize and register all pages
    private void initializePages() {
        // Register the main page
        pages.put("Main", () -> new MainPage(navigationController).getRoot());

        // Register pages Q1-Q5
        pages.put("Q1", () -> GenericPage.createPage(navigationController, "Q1", "Graph 1", 3));
        pages.put("Q2", () -> GenericPage.createPage(navigationController, "Q2", "Graph 2", 5));
        pages.put("Q3", () -> GenericPage.createPage(navigationController, "Q3", "Graph 3", 2));
        pages.put("Q4", () -> GenericPage.createPage(navigationController, "Q4", "Graph 4", 4));
        pages.put("Q5", () -> GenericPage.createPage(navigationController, "Q5", "Graph 5", 1));
    }

    public Pane getPage(String pageName) {
        if (pages.containsKey(pageName)) {
            return pages.get(pageName).get();
        }
        throw new IllegalArgumentException("Page not found: " + pageName);
    }

    public void addPage(String pageName, Supplier<Pane> pageSupplier) {
        pages.put(pageName, pageSupplier);
    }
}
