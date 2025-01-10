package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.controller.NavigationController;
import com.group3.mathModels.FormulaVariables;
import com.group3.mathModels.PreyPredatorModel;

public class PreyPredator extends AbstractPage {

    public PreyPredator(NavigationController navigationController, FormulaVariables formulaVariables) {
        super("Predator-Prey Dynamics", "Predator-Prey Model: Deer-Cattle-Horses-Wolves", navigationController, formulaVariables);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Predator-Prey Dynamics", new PreyPredatorModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Description Title", "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n");
                                            }
}
