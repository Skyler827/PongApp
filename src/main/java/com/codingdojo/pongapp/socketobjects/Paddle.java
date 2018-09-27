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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }
}
