package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.BinaryHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Titas on 2016-08-11.
 */
public class Node {

    public Vector2 pos;
    BinaryHeap.Node node = new BinaryHeap.Node(0);

    public Node(int i, int j) {
        pos.x = i;
        pos.y = j;
    }
    public List<Node> neighbourghs = new ArrayList<Node>();
    public int cost = 10;




}
