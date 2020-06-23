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
	public void pushEvent(Timeline timeline, int dificultad, GuiController guiControlle) {
		System.out.println("less");
		timeline.stop();
		dificultad = 200;
		System.out.println("la dificultad que manejo es: " + dificultad);

		timeline = new Timeline(new KeyFrame(
				Duration.millis(dificultad),
				ae -> guiControlle.moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
		));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}
}
