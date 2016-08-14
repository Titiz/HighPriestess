package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import java.lang.Math.*;
import java.util.Arrays;

import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;

/**
 * Class that will put the entities into a matrix so that they
 * arrange nicely into a square formation when moved.
 */
public class SelectMatrixCreator{



    static String TAG = SelectMatrixCreator.class.getName();

    public static int[][] makeMatrix(ImmutableBag<Entity> entities) {
        int size = entities.size();
        int matrix_size;
        if (size == 1)
            matrix_size = 1;
        else if (Math.log(size)/Math.log(2) % 2 == 0) {
           matrix_size = (int) (Math.log(size)/Math.log(2));
        } else {
            matrix_size = (int) (Math.log(size)/Math.log(2)+1);
        }

        Gdx.app.debug(TAG, "matrix_size: " + matrix_size);




        int [][] matrix = new int [matrix_size] [matrix_size];
        for (int[] col : matrix) {
            Arrays.fill(col, -1);
        }
        if(matrix_size == 1) {
            matrix[0][0] = entities.get(0).getId();
        } else {
            int row = 0;
            int collumn = -1;
            for (Entity e : entities) {
                if (Math.abs(row) % (matrix_size) == 0) {
                    collumn += 1;
                    row = 0;
                }

                matrix[row][collumn] = e.getId();
                row += 1;
            }
        }
        return matrix;
    }




}
