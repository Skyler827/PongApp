package com.codingdojo.pongapp.socketobjects;

public class Ball {
    public float x;
    public float y;
    public float vx;
    public float vy;
    public float radius;
    public kevinPaddle leftPaddle;
    public kevinPaddle rightPaddle;
    private boolean left = false;
    Ball() {}
    Ball(float x, float y, kevinPaddle left, kevinPaddle right) {
        this.x = x; this.y = y; this.leftPaddle = left; this.rightPaddle = right;
        vx = 3; vy = 3; radius = (float)0.5;
    }
    void timeStep(float dt) {
        x += vx * dt;
        y += vy * dt;
    }
    public void move(float dt){
        if(vx < 0){
            checkCollision(leftPaddle);
        }else{
            checkCollision(rightPaddle);
        }
        if(Math.abs(x) > 20){
            x = 0;
        }
        x += vx * dt;
        if(Math.abs(y+dt*vy) > 7){
            vy = -Math.abs(vy)*Math.abs(y)/y;
        }
        y += vy * dt;
    }

    public void checkCollision(kevinPaddle paddle){
        if(Math.abs(Math.abs(x)-Math.abs(paddle.x_center)) <= (paddle.width + radius)){
            if(Math.abs(y-paddle.y_center) <= (paddle.height + radius)){
                if(x < 0 && left){
                    // System.out.println("CHANGED DIRECTION");
                    left = false;
                    vx *= -1;
                    if(vx < 49){
                        vx*=1.1;
                    }
                    // System.out.println("Ball Speed"+vx);
                }else if(x > 0 && !left){
                    // System.out.println("CHANGED DIRECTION");
                    left = true;
                    vx *= -1;
                    if(vx > -49){
                        vx*=1.1;
                    }
                    // System.out.println("Ball Speed"+vx);
                }
            }
        }
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
