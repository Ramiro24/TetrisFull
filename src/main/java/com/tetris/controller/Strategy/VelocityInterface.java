package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public interface VelocityInterface {
    void pushEvent(Timeline timeline, GuiController guiController);
}