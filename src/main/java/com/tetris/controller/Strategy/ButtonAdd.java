package com.tetris.controller.Strategy;

import com.tetris.view.GuiController;
import javafx.animation.Timeline;

public class ButtonAdd extends Buttons {
    public ButtonAdd( GuiController guiController) {
        velocityInterface=new VelocityMore();
        this.guiController=guiController;
    }
    
    @Override
    void display() {
        System.out.println("I'm a real button duck");
    }
}
