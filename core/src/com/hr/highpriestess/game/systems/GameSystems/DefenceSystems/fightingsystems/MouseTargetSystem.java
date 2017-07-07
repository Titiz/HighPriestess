package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Defence.Target;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionUtilSystem;

/**
 * Created by Titas on 2016-08-06.
 */
public class MouseTargetSystem extends BaseSystem{

    String TAG = MouseTargetSystem.class.getName();

    ComponentMapper<Target> targetCm;
    GroupManager groupmanager;
    CameraSystem cameraSystem;
    CollisionUtilSystem collisionUtilSystem;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;

    int timeClicked = 0;



    @Override
    protected void processSystem() {

        ImmutableBag<Entity> entities = groupmanager.getEntities("selected");
        ImmutableBag<Entity> enemies = groupmanager.getEntities("enemies");

        if (Gdx.input.isButtonPressed(1)) {
            timeClicked += 1;

        }
        else
            timeClicked = 0;

        // check if allies are selected:
        if (timeClicked == 1) {
            if (groupmanager.isInGroup(entities.get(0), "allies")) {
                for (Entity entity : entities) {
                    int allyID = entity.getId();
                    for (Entity enemy : enemies) {
                        int enemyID = enemy.getId();
                        if (collisionUtilSystem.collidesWithMouse(enemyID)) {
                            if (targetCm.has(allyID)) {
                                targetCm.get(allyID).setTarget(enemyID);
                            } else {
                                targetCm.create(allyID);
                                targetCm.get(allyID).setTarget(enemyID);
                            }
                            targetCm.get(allyID).mouseTarget = true;
                            Bounds enemyXY = boundsCm.get(enemyID);
                            //moveToDestinationCm.create(allyID);
                            //moveToDestinationCm.get(allyID).setFinalXY(enemyXY.x, enemyXY.y);
                            Gdx.app.debug(TAG, allyID + " has set target " + enemyID);


                        } else if (moveToDestinationCm.has(allyID)){
                            moveToDestinationCm.get(allyID).ignoreSurroundings = true;

                        }
                    }

                }
            }
        }


    }
}
