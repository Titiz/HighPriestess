package com.hr.highpriestess.game.systems.GameSystems;


import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Game.Tracker.SpawnGateTracker;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * For now this system relocates the player to the right location
 * after entering through a gate.
 */





public class RelocationSystem extends BaseEntitySystem {

    String TAG = RelocationSystem.class.getName();

    ComponentMapper<ChangeMap> changeMapCm;
    ComponentMapper<SpawnGateTracker> spawnGateCm;
    ComponentMapper<Bounds> boundsCm;
    TagManager tagManager;


    public RelocationSystem() {
        super(Aspect.all(ChangeMap.class));
    }

    protected void processSystem() {

    }

    protected void inserted(int e) {
        Gdx.app.debug(TAG, "Inserted Entity with Id: " + e);
        Entity tracker = tagManager.getEntity("tracker");
        Gdx.app.debug(TAG, "tracker " + tracker);
        if (spawnGateCm.get(tracker).usedGateDestination != null) {
            String usedGate = spawnGateCm.get(tracker).usedGateDestination;
            //TODO: small improvement would be to stop system after the right entity is found.
            if (changeMapCm.get(e).getEnteringGateName().equals(usedGate)) {
                spawnGateCm.get(tracker).usedGateDestination = null;
                boundsCm.get(tagManager.getEntity("player")).setX(boundsCm.get(e).x);
                boundsCm.get(tagManager.getEntity("player")).setY(boundsCm.get(e).y);
            }
        }
    }
}