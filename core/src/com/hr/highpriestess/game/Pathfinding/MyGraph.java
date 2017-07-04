package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;


/**
 * Created by Titas on 2016-08-11.
 */
public class MyGraph implements IndexedGraph {
    int[][] matrix;
    Array<Integer> nodes = new Array<Integer>();
    // Stores the connections of each node.
    Array<Array<Connection>> connections = new Array<Array<Connection>>();


    public MyGraph (int[][] matrix) {
        this.matrix = matrix;
        int numberofNodes = matrix[0].length * matrix.length;
        for (int i = 0; i < numberofNodes; i++) {
            nodes.add(i);
        }
        for (Integer node : nodes) {
            Array<Connection> connections = new Array<Connection>();

            int currentRow = node / matrix.length;
            int currentCol = node % matrix.length;

            int middleValue = currentRow * matrix.length;
            int lowValue = (currentRow - 1) * matrix.length;
            int highValue = (currentRow + 1) * matrix.length;
            int veryHighValue = (currentRow + 2) * matrix.length;

            int diagCost = 14;
            int simpleCost = 10;

            if (isBetween(node + 1, middleValue, highValue))
                makeConnection(node, node + 1, simpleCost, connections);
            if (isBetween(node - 1, middleValue, highValue))
                makeConnection(node, node - 1, simpleCost, connections);


            if (lowValue >= 0) {
                if (isBetween(node - matrix.length - 1, lowValue, middleValue))
                    makeConnection(node, node - matrix.length - 1, diagCost, connections);
                if (isBetween(node - matrix.length, lowValue, middleValue))
                    makeConnection(node, node - matrix.length, simpleCost, connections);
                if (isBetween(node - matrix.length + 1, lowValue, middleValue))
                    makeConnection(node, node - matrix.length + 1, diagCost, connections);
            }
            if (highValue < numberofNodes) {
                if (isBetween(node + matrix.length - 1, highValue, veryHighValue))
                    makeConnection(node, node + matrix.length - 1, diagCost, connections);
                if (isBetween(node + matrix.length, highValue, veryHighValue))
                    makeConnection(node, node + matrix.length, simpleCost, connections);
                if (isBetween(node + matrix.length + 1, highValue, veryHighValue)) {
                    makeConnection(node, node + matrix.length + 1, diagCost, connections);
                }
            }
            this.connections.add(connections);
            //System.out.println("Node: " + node + " connectionLength: " + connections.size);
            //System.out.println("connected to nodes: ");
            //for (Connection connection : connections) {
            //    System.out.print(connection.getToNode() + ", " + "\n");
            //}
            //System.out.print("\n");




        }
    }

    private void makeConnection(int fromNode, int toNode, int cost, Array<Connection> connections) {


            Vector2 toNodeIJ = MyPathFindingUtils.getNodeIndices(toNode, matrix);
            if (matrix[(int) toNodeIJ.y][(int) toNodeIJ.x] == -1) {
                MyConnection connection = new MyConnection(fromNode, toNode);
                connection.setCost(cost);
                connections.add(connection);
            }

    }

    private boolean isBetween(int value, int b1, int b2) {
        if (value >= b1 && value <b2) {
            return true;
        } else {
            return false;
        }
    }


    public void updateMatrix(int [][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public Array<Connection> getConnections(Object fromNode) {
        return this.connections.get((Integer) fromNode);
    }

    @Override
    public int getIndex(Object node) {
        return (Integer) node;
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }
}
