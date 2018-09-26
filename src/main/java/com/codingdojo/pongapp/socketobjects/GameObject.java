package com.codingdojo.pongapp.socketobjects;

public abstract class GameObject {
    protected float x;
    protected float y;
    private float vx;
    private float vy;

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }
}
