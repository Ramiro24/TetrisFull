package com.tetris.controller.Strategy;

public class ButtonAdd extends Velocity {
    public ButtonAdd( ) {
        velocityInterface=new VelocityMore();

    }
    
    @Override
    public void display() {
        System.out.println("I'm a real button duck");
    }
}
