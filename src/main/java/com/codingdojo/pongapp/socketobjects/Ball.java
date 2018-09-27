package com.codingdojo.pongapp.socketobjects;

public class Ball extends GameObject {
    float radius;
    public Ball() {
        super(0,0);
    }
    public Ball(float x, float y) {
        super(x, y);
    }
    public boolean collideHorizontal(Paddle paddle, float dt) {
        return Math.abs(this.getX()+this.getVx()*dt-(paddle.getX()+paddle.getVx()*dt)) < this.radius + paddle.getWidth();
    }
    public boolean collideVertical(Paddle paddle, float dt) {
        return Math.abs(this.getY()+this.getVy()*dt-(paddle.getY())+paddle.getVy()*dt) < this.radius + paddle.getHeight();
    }
    public void movement(float dt, Paddle left, Paddle right) {
        // check collision left
        if (collideHorizontal(left, dt)) {
            if (collideVertical(right, dt)) {
                //ball is touching or within paddle
            } else {
                //ball is above or below paddle

            }
        } else {
            // ball is away from paddle
        }

        // check collision right
        if (Math.abs(this.getX()-right.getX()) < this.radius + right.getWidth()) {
            if (Math.abs(this.getY()-right.getY()) < this.radius + right.getHeight()) {
                //ball is touching or within paddle
            } else {
                //ball is above or below paddle

            }
        } else {
            // ball is away from paddle
        }

    }
}
