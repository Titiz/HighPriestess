package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.Pathfinding.MyPathFindingUtils;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Defence.Movement.NodeMovement.MoveThroughPath;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.SelectMatrixCreator;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2016-08-04.
 */
public class NodeSelectMoveSystem extends BaseSystem {

    ComponentMapper<Selectable> selectableCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Kinematics> kinematicsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentManager componentManager;
    ComponentMapper<MoveThroughPath> moveThroughPathCm;


    CameraSystem cameraSystem;
    GroupManager groupManager;

    int pressTime = 0;
    int timer =0;
    int CELL_SIZE = G.UNIT_SIZE;



    @Override
    protected void processSystem() {
        if (Gdx.input.isButtonPressed(1))
            pressTime += 1;
        else
            pressTime = 0;

        if (pressTime==1) {
            Vector3 mousePos = new Vector3();
            mousePos.add(Gdx.input.getX(), Gdx.input.getY(), 0);
            cameraSystem.camera.unproject(mousePos);
            ImmutableBag<Entity> selectedEntities = groupManager.getEntities("selected");
            if (selectedEntities.size() > 0) {
                int[][] matrix = SelectMatrixCreator.makeMatrix(selectedEntities);
                float finalX;
                float finalY;
                if (matrix.length == 1) {
                    int e = matrix[0][0];
                    finalX = mousePos.x;
                    finalY = mousePos.y ;
                    Bounds bounds = boundsCm.get(e);
                    System.out.println(mousePos.x);
                    System.out.println(mousePos.y);
                    int startingNode = MyPathFindingUtils.getNode(bounds.x, bounds.y, G.defenceGrid);
                    int endingNode = MyPathFindingUtils.getNode(finalX, finalY, G.defenceGrid);
                    createPathTo(e, startingNode, endingNode);
                } else
                    if (matrix.length % 2 == 0) {
                    for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[i].length; j++) {
                            int e = matrix[i][j];
                            if (matrix[i][j] != -1 && selectableCm.get(e).getSelectionLayer() == 0) {
                                finalX = mousePos.x - CELL_SIZE * (matrix.length / 2 - i);
                                finalY = mousePos.y - CELL_SIZE * (matrix.length / 2 - j);
                                Bounds bounds = boundsCm.get(e);
                                int startingNode = MyPathFindingUtils.getNode(bounds.x, bounds.y, G.defenceGrid);
                                int endingNode = MyPathFindingUtils.getNode(finalX, finalY, G.defenceGrid);

                                createPathTo(e, startingNode, endingNode);
                            }
                        }
                    }
                } else {
                    if (matrix.length % 2 == 1) {
                        for (int i = 0; i < matrix.length; i++) {
                            for (int j = 0; j < matrix[i].length; j++) {
                                int e = matrix[i][j];
                                if (matrix[i][j] != -1 && selectableCm.get(e).getSelectionLayer() == 0) {
                                    finalX = mousePos.x - CELL_SIZE * (matrix.length / 2 - j) - CELL_SIZE / 2;
                                    finalY = mousePos.y - CELL_SIZE * (matrix.length / 2 - i) - CELL_SIZE / 2;
                                    Bounds bounds = boundsCm.get(e);
                                    int startingNode = MyPathFindingUtils.getNode(bounds.x, bounds.y, G.defenceGrid);
                                    int endingNode = MyPathFindingUtils.getNode(finalX, finalY, G.defenceGrid);

                                    createPathTo(e, startingNode, endingNode);
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    private void createPathTo(int e, int startingNode, int endingNode) {
        moveThroughPathCm.create(e);
        moveThroughPathCm.get(e).setupNodes(startingNode, endingNode);
    }
}


