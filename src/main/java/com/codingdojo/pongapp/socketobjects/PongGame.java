package com.codingdojo.pongapp.socketobjects;

import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Rectangle;
public class PongGame {
    Bounds bounds;
    World world;
    Rectangle r;
    BodyFixture lp_bf;
    Body lp_body;

    BodyFixture rp_bf;
    Body rp_body;
    public PongGame() {
        bounds = new AxisAlignedBounds(40,20);
        world = new World(bounds);
        r = new Rectangle(1,3);

        // left paddle:
        lp_bf = new BodyFixture(r);
        lp_bf.setFriction(1.3);
        lp_body = new Body(1);
        lp_body.addFixture(lp_bf);

        //right paddle:
        rp_bf = new BodyFixture(r);
        lp_bf.setFriction(1.3);
        rp_body = new Body(1);
        rp_body.addFixture(lp_bf);

        world.addBody(lp_body);
        world.addBody(rp_body);

    }
}
