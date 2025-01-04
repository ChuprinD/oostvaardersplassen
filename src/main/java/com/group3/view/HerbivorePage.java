package com.group3.view;

import javafx.scene.layout.VBox;

import com.group3.controller.NavigationController;
import com.group3.mathModels.PredatorPreyModel;

public class HerbivorePage extends AbstractPage {

    public HerbivorePage(NavigationController navigationController) {
        super("Herbivore Competition", "Predator-Prey Model: Deer-Cattle-Horses-Wolves", navigationController);
    }

    @Override
    protected VBox createSectionGraph() {
        return createSectionGraph("Herbivore Competition", new PredatorPreyModel());
    }

    @Override
    protected VBox createSectionDescription() {
        return createSectionDescription("Description Title", "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n"
                                                                 + "Something Something Something Something\n");
                                            }
}
