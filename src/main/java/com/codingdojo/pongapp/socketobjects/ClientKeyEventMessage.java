package com.codingdojo.pongapp.socketobjects;

public class ClientKeyEventMessage {

    private 

    private Boolean top;
    private Boolean bottom;
    private Boolean left;
    private Boolean right;
    

    public ClientKeyEventMessage() {

    }

    // public ClientKeyEventMessage(Boolean top, Boolean bottom, Boolean left, Boolean right) {
    //     this.top = top;
    //     this.bottom = bottom;
    //     this.left = left;
    //     this.right = right;
    // }

    @Override
    public String toString(){
        return "Top: " + this.getTop() + " Bottom: "+ this.getBottom() + " Left: "+this.getLeft() + " Right: "+this.getRight();
    }

    public Boolean getTop(){ return this.top; }
    public Boolean getBottom(){ return this.bottom; }
    public Boolean getLeft(){ return this.left; }
    public Boolean getRight(){ return this.right; }

    public void setTop(Boolean value) { this.top = value; }
    public void setBottom(Boolean value) { this.bottom = value; }
    public void setLeft(Boolean value) { this.left = value; }
    public void setRight(Boolean value) { this.right = value; }
}