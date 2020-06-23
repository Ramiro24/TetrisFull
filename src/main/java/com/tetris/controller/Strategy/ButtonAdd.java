package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public class ButtonAdd extends Buttons {
    public ButtonAdd( ) {
        velocityInterface=new VelocityMore();

    }
    
    @Override
    void display() {
        System.out.println("I'm a real button duck");
    }
}
