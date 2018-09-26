package com.codingdojo.pongapp.socketobjects;

public class Ball extends GameObject {
    float radius;
    public Ball() {
        super(0,0);
    }
    public Ball(float x, float y) {
        super(x, y);
    }


    public void movement(float dt, Paddle left, Paddle right) {

    }
}
