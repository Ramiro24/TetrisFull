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
	public void pushEvent(Timeline timeLine, double dificultad, GuiController guiController) {
		System.out.println("less");
		/*timeline.stop();
		dificultad = 200;
		System.out.println("la dificultad que manejo es: " + dificultad);

		timeline = new Timeline(new KeyFrame(
				Duration.millis(dificultad),
				ae -> guiController.moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
		));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();*/

		if (dificultad <= 500) {
			timeLine.stop();
			dificultad = dificultad + 20;
			timeLine = new Timeline(new KeyFrame(
					Duration.millis(dificultad),
					ae -> guiController.moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
			));
			timeLine.setCycleCount(Timeline.INDEFINITE);
			timeLine.play();
		}

	}
}
