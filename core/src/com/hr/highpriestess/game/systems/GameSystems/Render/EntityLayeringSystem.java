package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.*;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Tracker.LayerEntityTracker;


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
