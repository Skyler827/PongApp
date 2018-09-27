package com.codingdojo.pongapp.socketobjects;

public class PongGame {
    private Paddle leftPaddle;
    private Ball ball;
    private Paddle rightPaddle;

    public PongGame() {
        leftPaddle = new Paddle(-10, 0);
        ball = new Ball(0,0);
        rightPaddle = new Paddle(10,0);
    }
    public void simulate(float dt) {
        leftPaddle.move(dt);
        ball.move(dt);
        rightPaddle.move(dt);
    }
    @Override
    public String toString() {
        return "left paddle:" +leftPaddle+", ball: "+ ball+"right paddle: "+rightPaddle;
    }
}
