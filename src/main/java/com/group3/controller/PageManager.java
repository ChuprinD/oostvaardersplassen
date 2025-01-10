package com.group3.controller;

import com.group3.view.Page;
import com.group3.view.PageFactory;
import com.group3.view.MainPage;
import com.group3.mathModels.FormulaVariables;
import com.group3.view.AbstractPage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula;

import javafx.scene.layout.Pane;

public class PageManager {
    private final NavigationController navigationController;
    private final Map<String, Supplier<Pane>> pages = new HashMap<>();
    private FormulaVariables formulaVariables;

    public PageManager(NavigationController navigationController) {
        this.navigationController = navigationController;
        this.formulaVariables = new FormulaVariables();
        initializePages();
    }

    
    /**
     * Initializes the pages of the application. This method is responsible for
     * registering all the pages of the application in the page manager. The
     * pages are registered with a unique name, and a supplier that creates the
     * page when it is requested. The pages are also registered with a supplier
     * for the scrollable version of the page, which is used when the user scrolls
     * to a specific section of the page.
     */
    private void initializePages() {
        // Register the main page
        Page mainPage = new MainPage(navigationController);
        pages.put(mainPage.getPageName(), () -> new MainPage(navigationController).getRoot());
        pages.put(mainPage.getPageName() + ".About", () -> MainPage.createPageAndScroll(navigationController, "About").getRoot());
        pages.put(mainPage.getPageName() + ".Animals", () -> MainPage.createPageAndScroll(navigationController, "Animals").getRoot());

        // Register pages
        AbstractPage firstPage = PageFactory.createPage("Herbivore Competition", navigationController, formulaVariables);
        AbstractPage secondPage = PageFactory.createPage("Predator-Prey Dynamics", navigationController, formulaVariables);
        AbstractPage thirdPage = PageFactory.createPage("Total Simulation", navigationController, formulaVariables);

        pages.put(firstPage.getPageName(), () -> PageFactory.createPage("Herbivore Competition", navigationController, formulaVariables).getRoot());
        pages.put(secondPage.getPageName(), () -> PageFactory.createPage("Predator-Prey Dynamics", navigationController, formulaVariables).getRoot());
        pages.put(thirdPage.getPageName(), () -> PageFactory.createPage("Total Simulation", navigationController, formulaVariables).getRoot());
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
