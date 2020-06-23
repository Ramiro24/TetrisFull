package com.tetris.controller.Strategy;

import com.tetris.model.events.EventSource;
import com.tetris.model.events.EventType;
import com.tetris.model.events.MoveEvent;
import com.tetris.view.GuiController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class VelocityDecrease implements VelocityInterface {


    @Override
    public void pushEvent(GuiController guiController) {
        if (guiController.Dificultad <= 500) {
            guiController.timeLine.stop();
            double dificultad = guiController.Dificultad + 20;
            guiController.timeLine = new Timeline(new KeyFrame(
                    Duration.millis(dificultad),
                    ae -> guiController.moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            ));
            guiController.timeLine.setCycleCount(Timeline.INDEFINITE);
            guiController.timeLine.play();
        }

    }
}
