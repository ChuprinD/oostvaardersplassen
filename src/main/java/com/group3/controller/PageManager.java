package com.group3.controller;

import com.group3.view.Page;
import com.group3.view.MainPage;
import com.group3.view.FirstPage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javafx.scene.layout.Pane;

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
        Page mainPage = new MainPage(navigationController);
        pages.put(mainPage.getPageName(), () -> new MainPage(navigationController).getRoot());
        pages.put(mainPage.getPageName() + ".About", () -> MainPage.createPageAndScroll(navigationController, "About").getRoot());
        pages.put(mainPage.getPageName() + ".Animals", () -> MainPage.createPageAndScroll(navigationController, "Animals").getRoot());

        // Register pages
        Page firstPage = new FirstPage(navigationController);
        pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
        //pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
        //pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
        //pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
        //pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
    }

    /**
     * Gets the page with the given name. If the page does not exist, it throws an
     * IllegalArgumentException.
     *
     * @param pageName the name of the page to retrieve
     * @return the page with the given name
     * @throws IllegalArgumentException if no page with the given name exists
     */
    public Pane getPage(String pageName) {
        if (pages.containsKey(pageName)) {
            return pages.get(pageName).get();
        }
        throw new IllegalArgumentException("Page not found: " + pageName);
    }
    
    /**
     * Registers a page with the given name and supplier. The supplier is used to create
     * a new instance of the page whenever it is requested with getPage().
     *
     * @param pageName the name of the page to register
     * @param pageSupplier a supplier that creates a new instance of the page
     */
    public void addPage(String pageName, Supplier<Pane> pageSupplier) {
        pages.put(pageName, pageSupplier);
    }
}
