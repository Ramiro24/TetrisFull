package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public abstract class Velocity {

    GuiController guiController;
    Timeline timeline;
    VelocityInterface velocityInterface;

    public Velocity() {

    }

    public void setDisplayBehavior(VelocityInterface fb) {
        this.velocityInterface = fb;
    }

    abstract void display();

    public void changeVelocity(GuiController guiController) {
        velocityInterface.pushEvent(guiController);
    }

}
