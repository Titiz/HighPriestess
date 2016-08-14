package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;

/**
 * Created by Titas on 2016-08-11.
 */
public class MyConnection implements Connection {

    protected int fromNode;
    protected int toNode;
    protected int cost;

    public void setCost(int cost) {
        this.cost = cost;
    }

    public MyConnection(int fromNode, int toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = 10;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Object getFromNode() {
        return fromNode;
    }

    @Override
    public Object getToNode() {
        return toNode;
    }
}
