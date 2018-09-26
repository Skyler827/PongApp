package com.codingdojo.pongapp.socketobjects;

public class PongGameState {
    private Paddle leftPaddle;
    private Ball ball;
    private Paddle rightPaddle;

    public PongGameState() {
        leftPaddle = new Paddle(-10, 0);
        ball = new Ball(0,0);
        rightPaddle = new Paddle(10,0);
    }

    @Override
    public String toString() {
        return "left paddle:" +leftPaddle+", ball: "+ ball+"right paddle: "+rightPaddle;
    }
}
