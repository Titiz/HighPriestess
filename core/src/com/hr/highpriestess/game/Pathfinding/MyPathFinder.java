package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.*;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Titas on 2016-08-12.
 */
public class MyPathFinder implements PathFinder{

    Graph graph;

    public MyPathFinder(Graph graph) {
        this.graph = graph;
    }


    @Override
    public boolean searchConnectionPath(Object startNode, Object endNode, Heuristic heuristic, GraphPath outPath) {
        return false;
    }



    private int selectLowestF(int currentNode, Array<Integer> openNodes, Array<Integer> openNodeCosts, Heuristic heuristic) {
        int lowestFCost = 99999999;
        int lowestNode = -1;

        int hCost;
        int gCost;
        int fCost;

        for (int i = 0; i < openNodes.size; i++) {
            hCost = (int) heuristic.estimate(currentNode, openNodes.get(i));
            gCost = openNodeCosts.get(i);
            fCost = hCost + gCost;
            if (fCost < lowestFCost) {
                lowestFCost = fCost;
                lowestNode = openNodes.get(i);
            }
        }
        return lowestNode;
    }

    @Override
    public boolean searchNodePath(Object startNode, Object endNode, Heuristic heuristic, GraphPath outPath) {
        Array<Integer> closedNodes = new Array<Integer>();
        Array<Integer> openNodes = new Array<Integer>();
        Array<Integer> openNodeCosts = new Array<Integer>();
        Array<Integer> closedNodeCosts = new Array<Integer>();
        Array<Integer> path = new Array<Integer>();
        int currentNode;
        Integer n1 = (Integer) startNode;
        Integer n2 = (Integer) endNode;
        openNodes.add(n1);
        openNodeCosts.add((int) heuristic.estimate(n1, n2));
        currentNode = n1;
        while (true) {
            currentNode = selectLowestF(currentNode, openNodes, openNodeCosts, heuristic);
            int currentNodeIndex = openNodes.indexOf(currentNode, true);
            openNodes.removeIndex(currentNodeIndex);
            closedNodes.add(currentNode);
            closedNodeCosts.add(openNodeCosts.get(currentNodeIndex));
            openNodeCosts.removeIndex(currentNodeIndex);



            if (currentNode == n2)
                return true;

            for (Object object : graph.getConnections(currentNode)) {
                Connection connection = (Connection) object;
                // check for inactivity
                int toNode = (Integer) connection.getToNode();
                if (!openNodes.contains(toNode, true) && !closedNodes.contains(toNode, true)) {
                    openNodes.add(toNode);
                    openNodeCosts.add(closedNodeCosts.get(closedNodes.indexOf(n1, true))+ (int)connection.getCost());
                }

            }
        }
    }

    @Override
    public boolean search(PathFinderRequest request, long timeToRun) {
        return false;
    }
}
