package com.tetris.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class HighScorePanel extends BorderPane {

    public HighScorePanel() {
        final Label HighScoreLabel = new Label("HighScore");
        HighScoreLabel.getStyleClass().add("gameOverStyle");
        setCenter(HighScoreLabel);
    }

}
