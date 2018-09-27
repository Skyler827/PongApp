package com.codingdojo.pongapp.socketobjects;

public class Ball {
    float x;
    float y;
    float vx;
    float vy;
    float radius;
    Ball() {}
    Ball(float x, float y) {
        this.x = x; this.y = y;
        vx = 0; vy = 0;
    }
    void timeStep(float dt) {
        x += vx * dt;
        y += vy * dt;
    }

    public String getStatus() {
        return "x = "+x+", y = "+y;
    }
}
