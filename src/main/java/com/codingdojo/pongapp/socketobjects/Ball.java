package com.codingdojo.pongapp.socketobjects;

public class Ball extends GameObject {
    float radius;
    static final float MIN_X = -10;
    static final float MAX_X = 10;
    static final float MIN_Y = -5;
    static final float MAX_Y = 5;

    public Ball() {
        super(0,0);
        setVx(3);
        setVy(1);
    }
    public Ball(float x, float y) {
        super(x, y);
        setVx(3);
        setVy(1);
    }
    public boolean collideHorizontal(Paddle paddle, float dt) {
        return Math.abs(this.getX()+this.getVx()*dt-(paddle.getX()+paddle.getVx()*dt)) < this.radius + paddle.getWidth();
    }
    public boolean collideVertical(Paddle paddle, float dt) {
        return Math.abs(this.getY()+this.getVy()*dt-(paddle.getY())+paddle.getVy()*dt) < this.radius + paddle.getHeight();
    }
    public void movement(float dt, Paddle left, Paddle right) {
        if (MIN_X < getX() || getX() < MAX_X) {
            setVx(-1*getVx());
        }
        if (MIN_Y < getY() || getY() < MAX_Y) {
            setVy(-1*getVy());
        }
        move(dt);
    }
}
