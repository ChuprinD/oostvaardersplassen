package com.group3.view;

import com.group3.controller.NavigationController;
import com.group3.mathModels.FormulaVariables;
import com.group3.mathModels.TotalSimulationModel;

import javafx.scene.layout.VBox;

public class TotalSimulation extends AbstractPage {
    public TotalSimulation(NavigationController navigationController, FormulaVariables formulaVariables) {
        super("Total Simulation", "Total Simulation", navigationController, formulaVariables);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Total Simulation", new TotalSimulationModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Description Title", "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n");
    }
}
