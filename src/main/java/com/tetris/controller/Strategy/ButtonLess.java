package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public class ButtonLess extends Buttons {

    public ButtonLess( GuiController guiController) {
        velocityInterface = new VelocityDecrease();
        //this.timeline=timeline;
        this.guiController=guiController;
    }

    @Override
    void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
