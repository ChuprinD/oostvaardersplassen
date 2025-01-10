package com.group3.view;

import com.group3.controller.NavigationController;

public class PageFactory {
    /**
     * Creates a new page of the given type.
     * 
     * @param pageType the type of page to create, must be one of the following: Herbivore
     * @param navigationController the NavigationController that will be used to navigate from this page.
     * @return the newly created page.
     * @throws IllegalArgumentException if pageType is not one of the above values.
     */
    public static AbstractPage createPage(String pageType, NavigationController navigationController) {
        switch (pageType) {
            case "Herbivore Competition":
                return new HerbivorePage(navigationController);
            case "Predator-Prey Dynamics":
                return new PreyPredator(navigationController);
            case "Total Simulation":
                return new TotalSimulation(navigationController);
            default:
                throw new IllegalArgumentException("Unknown page type: " + pageType);
        }
    }
}
