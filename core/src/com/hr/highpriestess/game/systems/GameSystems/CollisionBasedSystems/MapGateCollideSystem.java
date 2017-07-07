package com.hr.highpriestess.game.systems.GameSystems.CollisionBasedSystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.components.Game.Interactibles.Interactible;
import com.hr.highpriestess.game.components.Game.Interactibles.Trigger;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.systems.GameSystems.GameMapSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionUtilSystem;


public class MapGateCollideSystem extends BaseSystem {



    private String TAG = MapGateCollideSystem.class.getName();
    private GroupManager groupManager;
    private TagManager tagManager;


    private GameMapSystem gameMapSystem;
    private CollisionUtilSystem collisionUtilSystem;


    private ComponentMapper<ChangeMap> changeMapCm;
    private ComponentMapper<Trigger> triggerCm;
    private ComponentMapper<Interactible> interaCm;
    private ComponentMapper<Player> playerCm;

    int player;


    public MapGateCollideSystem() {}


    protected void begin() {
        player = tagManager.getEntity("player").getId();
    }

    @Override
    protected void processSystem() {
        Entity collidedEntity = playerCm.get(player).collidingEntity;
        Gdx.app.debug(TAG, playerCm.get(player).isActiveButtonClicked.toString());
        if (collidedEntity!= null && changeMapCm.has(collidedEntity)) {
            if (triggerCm.has(collidedEntity)) {
                gameMapSystem.setActiveMap(changeMapCm.get(collidedEntity).getNextMap());
            } else if (interaCm.has(collidedEntity) && playerCm.get(player).isActiveButtonClicked) {
                gameMapSystem.setActiveMap(changeMapCm.get(collidedEntity).getNextMap());
            }
        }

    }




}




