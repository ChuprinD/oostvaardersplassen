package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.controller.NavigationController;
import com.group3.mathModels.FormulaVariables;
import com.group3.mathModels.HerbivoreCompetitionModel;

public class HerbivorePage extends AbstractPage {

    public HerbivorePage(NavigationController navigationController, FormulaVariables formulaVariables) {
        super("Herbivore Competition", "Herbivore Competition", navigationController, formulaVariables);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Herbivore Competition", new HerbivoreCompetitionModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Description Title", "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n");
                                            }
}
