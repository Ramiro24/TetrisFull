package com.tetris.controller.Strategy;

public class ButtonLess extends Velocity {

    public ButtonLess( ) {
        velocityInterface = new VelocityDecrease();
        //this.timeline=timeline;

    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
