package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public abstract class Velocity {

    public VelocityInterface velocityInterface;

    public Velocity() {

    }

    public void setDisplayBehavior(VelocityInterface fb) {
        this.velocityInterface = fb;
    }

    public abstract void display();

    public void changeVelocity(GuiController guiController) {
        velocityInterface.pushEvent(guiController);
    }

}
