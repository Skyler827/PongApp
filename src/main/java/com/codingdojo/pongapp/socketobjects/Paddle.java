package com.codingdojo.pongapp.socketobjects;

public class Paddle extends GameObject {
    private float width;
    private float height;
    private float friction;
    public Paddle() {
        super(0,0);
    }
    public Paddle(float x, float y) {
        super(x,y);
        width = 1;
        height = 3;
        friction = 1;
    }

    public void movement(float dt) {

    }
    @Override
    public String toString() {
        return "";
    }
}
