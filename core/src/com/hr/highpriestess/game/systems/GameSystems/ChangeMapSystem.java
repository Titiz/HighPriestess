package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;


public class ChangeMapSystem extends BaseSystem {



    private String TAG = ChangeMapSystem.class.getName();
    private GroupManager groupManager;
    private TagManager tagManager;


    private GameMapSystem gameMapSystem;
    private CollisionSystem collisionSystem;
    private ComponentMapper<ChangeMap> changeMapCm;
    int playerEntity;
    int [] changeMapEntities;


    @Override
    protected void processSystem() {
         ImmutableBag<Entity> changeMapBoxes = groupManager.getEntities("gate");
         Entity player = tagManager.getEntity("player");

            for (Entity changeMapBox : changeMapBoxes) {
                if (collisionSystem.twoEntityCollision(changeMapBox.getId(), player.getId())) {
                    Gdx.app.debug(TAG, " Collision detected of Player with ChangeBox '" +
                            changeMapCm.get(changeMapBox.getId()).getNextMap() + "'");
                    gameMapSystem.setActiveMap(changeMapCm.get(changeMapBox.getId()).getNextMap());
                    break;
                }


        }



    }




}
