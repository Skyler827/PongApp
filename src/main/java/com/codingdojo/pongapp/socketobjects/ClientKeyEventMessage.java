package com.codingdojo.pongapp.socketobjects;

public class ClientKeyEventMessage {

    private Long gameId;
    private Boolean top;
    private Boolean bottom;


    public ClientKeyEventMessage() {

    }

    public ClientKeyEventMessage(Boolean top, Boolean bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String toString(){
        return "Top: " + this.getTop() + " Bottom: "+ this.getBottom();
    }

    public Long getGameId(){ return this.gameId; }
    public Boolean getTop(){ return this.top; }
    public Boolean getBottom(){ return this.bottom; }

    public void setUsername(Long gameId){ this.gameId = gameId; }
    public void setTop(Boolean value) { this.top = value; }
    public void setBottom(Boolean value) { this.bottom = value; }
}