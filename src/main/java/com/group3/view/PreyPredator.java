package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.Formulae.FormulaVariables;
import com.group3.controller.NavigationController;
import com.group3.mathModels.PreyPredatorModel;
import com.group3.mathModels.TotalSimulationModel;

public class PreyPredator extends AbstractPage {

    public PreyPredator(NavigationController navigationController, FormulaVariables formulaVariables, int numberOfGraphs) {
        super("Predator-Prey Dynamics", "Predator-Prey Model: Deer-Cattle-Horses-Wolves", navigationController, formulaVariables, numberOfGraphs);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Predator-Prey Dynamics", new PreyPredatorModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Predator-Prey Dynamics", "Purpose:\n" + 
        "This model helps us visualize the dynamics of the interspecific competition\nbetween the herbivores including predation but without grass\n\n" +
        "Related research question:\n(2) How does a predator species, such as grey wolves, interact with the prey species\n(3) How do the three herbivore species’ populations differ in reaction to the predator species?\n\n"  +
        "Key features:\n" +
        "The y axis is number of animals, and the x axis is time in years\n" +
        "The variables are set based on provided data or other studies, and include metrics\nlike i intrinsic growth rate, initial population size, consumption rate, death rate\n\n" +
        "Context:\n" + "We see that Deer are far more affected by wolf predation than the others.\nThis is expected and is based off literature we have found that illustrate that wolves\nhave a preference for deer in the wild when faced with predation choice, and\nthe predation rate value is based off empirical data from wildlife reserves");
    }

    @Override
    protected VBox createSectionSecondGraph() {
        return createSectionSecondGraph("Predator-Prey Dynamics", new PreyPredatorModel());
    }
}
