package com.codingdojo.pongapp.socketobjects;

public class ClientKeyEventMessage {

    // private String userName;

    private Boolean top;
    private Boolean bottom;


    public ClientKeyEventMessage() {

    }

    public ClientKeyEventMessage(Boolean top, Boolean bottom, Boolean left, Boolean right) {
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String toString(){
        return "Top: " + this.getTop() + " Bottom: "+ this.getBottom();
    }

    public Boolean getTop(){ return this.top; }
    public Boolean getBottom(){ return this.bottom; }

    public void setTop(Boolean value) { this.top = value; }
    public void setBottom(Boolean value) { this.bottom = value; }
}