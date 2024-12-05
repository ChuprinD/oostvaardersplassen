package com.group3.controller;

import com.group3.view.Page;
import com.group3.view.SecondPage;
import com.group3.view.ThirdPage;
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
        Page secondPage = new SecondPage(navigationController);
        Page thirdPage = new ThirdPage(navigationController);
        pages.put(firstPage.getPageName(), () -> new FirstPage(navigationController).getRoot());
        pages.put(secondPage.getPageName(), () -> new SecondPage(navigationController).getRoot());
        pages.put(thirdPage.getPageName(), () -> new ThirdPage(navigationController).getRoot());
        pages.put("Q4", () -> new FirstPage(navigationController).getRoot());
        pages.put("Q5", () -> new FirstPage(navigationController).getRoot());
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
