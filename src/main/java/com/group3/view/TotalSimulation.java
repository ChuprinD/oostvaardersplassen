package com.group3.view;

import com.group3.Formulae.FormulaVariables;
import com.group3.controller.NavigationController;
import com.group3.mathModels.TotalSimulationModel;

import javafx.scene.layout.VBox;

public class TotalSimulation extends AbstractPage {
    public TotalSimulation(NavigationController navigationController, FormulaVariables formulaVariables, int numberOfGraphs) {
        super("Total Simulation", "Total Simulation", navigationController, formulaVariables, numberOfGraphs);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Total Simulation", new TotalSimulationModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Total Simulation", "Purpose:\n" + 
        "This model helps us visualize the dynamics of the Tritrophic competition\nbetween the herbivores, the grass and the wolves by modelling the grass\nas prey, the herbivores as intermediate predator, and the wolf as top predator\n\n" +
        "Key features:\n" +
        "The y axis is number of animals, and the x axis is time in years\n" +
        "The variables are set based on provided data or other studies, and include metrics\nlike i intrinsic growth rate, initial population size, consumption rate, death rate , carrying capacity of grass");
    }
    
    @Override
    protected VBox createSectionSecondGraph() {
        return createSectionSecondGraph("Total Simulation", new TotalSimulationModel());
    }
}
