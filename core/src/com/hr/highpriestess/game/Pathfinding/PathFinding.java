package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.BinaryHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Titas on 2016-08-11.
 */
public class PathFinding {

    public static HashMap<Vector2, Node> allNodes = new HashMap<Vector2, Node>();



    public static void  voidmakeNodeMap(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == -1) {
                    Node newNode = new Node(i, j);
                    allNodes.put(newNode.pos, newNode);
                }
            }
        }
        for (Node node : allNodes.values()) {
            for (Node otherNode : allNodes.values()) {
                if (node != otherNode) {
                    if (node.pos.x+1 == otherNode.pos.x && node.pos.y == otherNode.pos.y ||
                        node.pos.x == otherNode.pos.x - 1 && node.pos.y == otherNode.pos.y ||
                        node.pos.x == otherNode.pos.x && node.pos.y + 1 == otherNode.pos.y ||
                        node.pos.x == otherNode.pos.x && node.pos.y - 1 == otherNode.pos.y) {
                        node.neighbourghs.add(otherNode);
                    }
                }
        }
    }

}

    void makePathUsingNodes(Vector2 startingNodePos, Vector2 endingNodePos) {

        Node startingNode = allNodes.get(startingNodePos);
        Node endNode = allNodes.get(endingNodePos);

        List<Node> openNodes = new ArrayList<Node>();
        List<Node> closedNodes = new ArrayList<Node>();
        openNodes.add(startingNode);
        while (true) {
            break;
        }

    }



}
