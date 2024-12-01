package view;

import javafx.scene.layout.BorderPane;

public interface Page {
    public BorderPane getRoot();
    public String getPageName();
}
