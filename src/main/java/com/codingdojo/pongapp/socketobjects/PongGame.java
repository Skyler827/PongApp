package com.codingdojo.pongapp.socketobjects;

import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.World;

public class PongGame {
    Bounds bounds = new AxisAlignedBounds(40,20);
    World world = new World(bounds);
    
}
