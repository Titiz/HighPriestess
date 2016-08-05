package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-08-04.
 */
public class SelectMoveSystem extends BaseSystem {

    ComponentMapper<Selectable> selectableCm;
    ComponentMapper<Bounds> boundsCm;
    CameraSystem cameraSystem;
    GroupManager groupManager;


    @Override
    protected void processSystem() {
        Vector3 mousePos = new Vector3();
        mousePos.add(Gdx.input.getX(), Gdx.input.getY(), 0);
        cameraSystem.camera.unproject(mousePos);
        ImmutableBag<Entity> selectedEntities = groupManager.getEntities("selected");
        if (selectedEntities.size() > 0) {
            int[][] matrix = SelectMatrixCreator.makeMatrix(selectedEntities);
            for (Entity entity : selectedEntities) {
                int e = entity.getId();
                if (selectableCm.get(e).getSelectionLayer() == 0) {
                    if (Gdx.input.isButtonPressed(1)) {

                        if (matrix.length % 2 == 0) {
                            for (int i = 0; i < matrix.length; i++) {
                                for (int j = 0; j < matrix[i].length; j++) {
                                    if (matrix[i][j] != 0) {
                                        boundsCm.get(matrix[i][j]).setX(mousePos.x - G.CELL_SIZE * (matrix.length / 2 - i) * 2);
                                        boundsCm.get(matrix[i][j]).setY(mousePos.y - G.CELL_SIZE * (matrix.length / 2 - j) * 2);
                                    }
                                }
                            }
                        } else {
                            if (matrix.length % 2 == 1) {
                                for (int i = 0; i < matrix.length; i++) {
                                    for (int j = 0; j < matrix[i].length; j++) {
                                        System.out.println("MATRIX " + matrix[i][j]);
                                        if (matrix[i][j] != 0) {
                                            boundsCm.get(matrix[i][j]).setX(mousePos.x - G.CELL_SIZE * (matrix.length / 2 - j) * 2 - G.CELL_SIZE);
                                            boundsCm.get(matrix[i][j]).setY(mousePos.y - G.CELL_SIZE * (matrix.length / 2 - i) * 2 - G.CELL_SIZE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
