package com.codingdojo.pongapp.socketobjects;

public class PongGame {
    Ball b;
    Paddle leftPaddle;
    Paddle rightPaddle;
    public PongGame() {
        b = new Ball(0,0);
        leftPaddle = new PlayerPaddle(-10, 0, -10,-10);
        rightPaddle = new ComputerPaddle(10, 0, 10,10);
    }
    void move(float dt) {
        handleCollision(b, leftPaddle);
        handleCollision(b, rightPaddle);
        b.timeStep(dt);
        leftPaddle.timeStep(dt);
        rightPaddle.timeStep(dt);
    }
    static void handleCollision(Ball b, Paddle p) {
        float horizontalDisplacement = b.x-p.x_center;
        float verticalDisplacement = b.y-p.y_center;
        boolean horizontalCondition = horizontalDisplacement < p.width/2 + b.radius;
        boolean verticalCondition = verticalDisplacement<p.height/2 + b.radius;
        if (horizontalCondition) {
            if (verticalCondition) {
                //collision:
                if (horizontalDisplacement > 0) b.vx = 1;
                else b.vx = -1;
                b.vy = verticalDisplacement;
            }
        }
    }
    public String getStatus() {
        return "Pong game: Left Paddle: "+leftPaddle.getStatus()
                +", Right Paddle: " + rightPaddle.getStatus()
                +", Ball: "+b.getStatus();
    }
    public void handleKeyEvent(ClientKeyEventMessage kem) {
        leftPaddle.downKeyPressed = kem.getBottom();
        leftPaddle.upKeyPressed = kem.getTop();
    }
}
