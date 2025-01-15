package com.group3.view;

import com.group3.Formulae.FormulaVariables;
import com.group3.controller.NavigationController;

public class PageFactory {
    
    /**
     * Creates an instance of the page with the given type.
     * 
     * @param pageType the type of page to create. Must be one of "Herbivore Competition",
     *                 "Predator-Prey Dynamics", or "Total Simulation".
     * @param navigationController the controller responsible for navigating between pages.
     * @param formulaVariables the formula variables to use for the page.
     * @return an instance of the page with the given type.
     * @throws IllegalArgumentException if the page type is not recognized.
     */
    public static AbstractPage createPage(String pageType, NavigationController navigationController, FormulaVariables formulaVariables) {
        switch (pageType) {
            case "Herbivore Competition":
                return new HerbivorePage(navigationController, formulaVariables, 1);
            case "Predator-Prey Dynamics":
                return new PreyPredator(navigationController, formulaVariables, 2);
            case "Total Simulation":
                return new TotalSimulation(navigationController, formulaVariables, 1);
            default:
                throw new IllegalArgumentException("Unknown page type: " + pageType);
        }
    }
}
