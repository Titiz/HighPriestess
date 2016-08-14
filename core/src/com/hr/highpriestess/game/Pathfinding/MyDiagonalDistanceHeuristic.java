package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Titas on 2016-08-11.
 */
public class MyDiagonalDistanceHeuristic implements Heuristic {

    int [][] matrix;

    public MyDiagonalDistanceHeuristic(int [][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public float estimate(Object node, Object endNode) {
        int startingNode = (Integer) node;
        int finishNode = (Integer) endNode;
        Vector2 n1 = MyPathFindingUtils.getNodeIndices(startingNode, matrix);
        Vector2 n2 = MyPathFindingUtils.getNodeIndices(finishNode, matrix);
        float dx = Math.abs(n1.x - n2.x);
        float dy = Math.abs(n1.y - n2.y);
        return 10 * (dx + dy) + (14 - 2 * 10) * Math.min(dx, dy);
    }
}
