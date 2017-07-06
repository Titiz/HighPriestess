package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.*;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Game.Tracker.LayerEntityTracker;
import com.hr.highpriestess.game.components.Menu.Bounds;

import java.util.HashMap;


/**
 * Created by Titas on 2017-07-06.
 */



public class EntityLayeringSystem extends BaseEntitySystem {

    String TAG = EntityLayeringSystem.class.getName();
    TagManager tagManager;
    ComponentMapper<Anim> animCm;
    ComponentMapper<LayerEntityTracker> layerEntityTrackercm;

    public EntityLayeringSystem() {super(Aspect.all(Anim.class));}

    protected void processSystem() {

    }

    protected void inserted(int entityId) {
        Gdx.app.debug(TAG, "Inserted Entity with Id: " + entityId);
        Entity tracker = tagManager.getEntity("tracker");
        LayerEntityTracker layerTracker = layerEntityTrackercm.get(tracker);
        layerTracker.add(entityId, animCm.get(entityId).layer);
    }
}
