package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.controller.NavigationController;
import com.group3.mathModels.HerbivoreCompetition;
import com.group3.mathModels.PredatorPreyModel;

public class HerbivorePage extends AbstractPage {

    public HerbivorePage(NavigationController navigationController) {
        super("Herbivore Competition", "Herbivore Competition", navigationController);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Herbivore Competition", new HerbivoreCompetition());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Description Title", "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n");
                                            }
}
