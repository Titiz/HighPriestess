package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-08-04.
 */
public class SelectMoveSystem extends BaseSystem {

    ComponentMapper<Selectable> selectableCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Kinematics> kinematicsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentManager componentManager;
    CameraSystem cameraSystem;
    GroupManager groupManager;

    int pressTime = 0;
    int timer =0;


    @Override
    protected void processSystem() {
        if (Gdx.input.isButtonPressed(1))
            pressTime += 1;
        else
            pressTime = 0;
        Vector3 mousePos = new Vector3();
        mousePos.add(Gdx.input.getX(), Gdx.input.getY(), 0);
        cameraSystem.camera.unproject(mousePos);
        ImmutableBag<Entity> selectedEntities = groupManager.getEntities("selected");
        if (selectedEntities.size() > 0) {
            int[][] matrix = SelectMatrixCreator.makeMatrix(selectedEntities);
                    if (pressTime == 1) {
                        float finalX;
                        float finalY;
                        if (matrix.length % 2 == 0) {
                            for (int i = 0; i < matrix.length; i++) {
                                for (int j = 0; j < matrix[i].length; j++) {
                                    int e = matrix[i][j];
                                    if (matrix[i][j] != 0 && selectableCm.get(e).getSelectionLayer() == 0 ) {
                                        finalX = mousePos.x - G.CELL_SIZE * (matrix.length / 2 - i) * 2;
                                        finalY = mousePos.y - G.CELL_SIZE * (matrix.length / 2 - j) * 2;
                                        addMoveToDestinationComponent(e, finalX, finalY);
                                    }
                                }
                            }
                        } else {
                            if (matrix.length % 2 == 1) {
                                for (int i = 0; i < matrix.length; i++) {
                                    for (int j = 0; j < matrix[i].length; j++) {
                                        int e = matrix[i][j];
                                        if (matrix[i][j] != 0 && selectableCm.get(e).getSelectionLayer() == 0 ) {
                                            finalX = mousePos.x - G.CELL_SIZE * (matrix.length / 2 - j) * 2 - G.CELL_SIZE;
                                            finalY = mousePos.y - G.CELL_SIZE * (matrix.length / 2 - i) * 2 - G.CELL_SIZE;
                                            addMoveToDestinationComponent(e, finalX, finalY);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
    }


    private void addMoveToDestinationComponent(int e, float finalX, float finalY) {
            moveToDestinationCm.create(e);
            moveToDestinationCm.get(e).setFinalXY(finalX, finalY);
        }
    }


