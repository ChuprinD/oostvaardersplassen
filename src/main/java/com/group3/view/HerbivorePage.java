package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.Formulae.FormulaVariables;
import com.group3.controller.NavigationController;
import com.group3.mathModels.HerbivoreCompetitionModel;

public class HerbivorePage extends AbstractPage {

    public HerbivorePage(NavigationController navigationController, FormulaVariables formulaVariables, int numberOfGraphs) {
        super("Herbivore Competition", "Herbivore Competition", navigationController, formulaVariables, numberOfGraphs);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Herbivore Competition", new HerbivoreCompetitionModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Herbivore Competition", "Purpose:\n" + 
        "This model helps us visualize the dynamics of the interspecific competition\nbetween the herbivores excluding predation\n\n" +
        "Related research question:\n(1) How do herbivores interact with each other regarding competing for food?\n\n"  +
        "Key features:\n" +
        "The y axis is number of animals, and the x axis is time in years\n" +
        "The variables are set based on provided data or other studies, and include metrics\nlike intrinsic growth rate, initial population size, consumption rate");
    }
    @Override
    protected VBox createSectionSecondGraph() {
        return createSectionGraph("Herbivore Competition", new HerbivoreCompetitionModel());
    }
}
