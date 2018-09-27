package com.codingdojo.pongapp.socketobjects;

public abstract class Paddle {
    final float MIN_X;
    final float MAX_X;
    static final float MIN_Y = -5;
    static final float MAX_Y = 5;
    float x_center = 0;
    float y_center = 0;
    float vx = 0;
    float vy = 0;
    boolean downKeyPressed = false;
    boolean upKeyPressed = false;
    float width = 0;
    float height = 0;
    Paddle() {
        MIN_X = -10;
        MAX_X = 10;
    }
    Paddle(float x, float y, float minX,float maxX) {
        this.x_center = x; this.y_center = y;
        MIN_X = minX;
        MAX_X = maxX;
    }
    void timeStep(float dt) {
        if (downKeyPressed) {
            vy -= dt;
        }
        if (upKeyPressed) {
            vy += dt;
        }
        x_center += vx * dt;
        x_center = Math.min(x_center, MAX_X);
        x_center = Math.max(MAX_X, x_center);

        y_center += vy * dt;
        y_center = Math.min(y_center, MAX_Y);
        y_center = Math.max(MIN_Y, y_center);

    }

    public String getStatus() {
        return "x = "+x_center+", y = "+y_center;
    }
}
