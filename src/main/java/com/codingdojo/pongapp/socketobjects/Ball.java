package com.codingdojo.pongapp.socketobjects;

public class Ball {
    public float x;
    public float y;
    public float vx;
    public float vy;
    public float radius;
    Ball() {}
    Ball(float x, float y) {
        this.x = x; this.y = y;
        vx = 3; vy = 300;
    }
    void timeStep(float dt) {
        x += vx * dt;
        y += vy * dt;
    }
    public void move(float dt){
        if(Math.abs(x) > 15){
            vx *= -1;
        }
        x += vx * dt;
        if(Math.abs(y+dt*vy) > 7){
            vy = -Math.abs(vy)*Math.abs(y)/y;
        }
        y += vy * dt;
    }

    public void handleCollision(Paddle p) {
        float horizontalDisplacement = this.x-p.x_center;
        float verticalDisplacement = this.y-p.y_center;
        boolean horizontalCondition = horizontalDisplacement < p.width/2 + this.radius;
        boolean verticalCondition = verticalDisplacement<p.height/2 + this.radius;
        if (horizontalCondition) {
            if (verticalCondition) {
                //collision:
                if (horizontalDisplacement > 0) this.vx = 1;
                else this.vx = -1;
                this.vy = verticalDisplacement;
            }
        }
    }

    public String getStatus() {
        return "x = "+x+", y = "+y;
    }
}
