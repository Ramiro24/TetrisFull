package com.tetris.controller.Strategy;

import com.tetris.model.events.EventSource;
import com.tetris.model.events.EventType;
import com.tetris.model.events.MoveEvent;
import com.tetris.view.GuiController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class VelocityMore implements VelocityInterface {


    @Override
    public void pushEvent(Timeline timeline, int dificultad, GuiController guiController) {
        System.out.println("more");
        timeline.stop();
        dificultad = 400;
        System.out.println("la dificultad que manejo es: " + dificultad);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(dificultad),
                ae -> guiController.moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}
