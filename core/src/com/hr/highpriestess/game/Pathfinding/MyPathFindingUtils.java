package com.hr.highpriestess.game.Pathfinding;

import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.math.Vector2;
import com.hr.highpriestess.G;

/**
 * Created by Titas on 2016-08-11.
 */
public class MyPathFindingUtils {

    public static Vector2 getNodeIndices(int node, int [][] matrix) {
        int i = node / matrix.length;
        int j = node % matrix.length;
        //System.out.println("J: " + j + " i :" + i);
        return new Vector2(i, j);
    }

    public static int getNode (float x, float y, int[][] matrix) {
        int i = (int) (x / G.UNIT_SIZE);
        int j = (int) (y / G.UNIT_SIZE);
        return getNode(i, j, matrix);
    }

    public static int getNode (int i, int j, int[][] matrix) {
        return i * matrix.length + j;
    }

    public static Vector2 getCoordsFromNode(int node, int [][] matrix) {
        Vector2 coords = getNodeIndices(node, matrix);
        coords.x = coords.x * G.UNIT_SIZE;
        coords.y = coords.y * G.UNIT_SIZE;
        return coords;
    }

}
