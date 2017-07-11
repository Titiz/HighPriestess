package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.*;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Image;
import com.hr.highpriestess.game.components.Game.Tracker.LayerEntityTracker;


/**
 * Created by Titas on 2017-07-06.
 */



public class EntityLayeringSystem extends BaseEntitySystem {

    String TAG = EntityLayeringSystem.class.getName();
    TagManager tagManager;
    ComponentMapper<Anim> animCm;
    ComponentMapper<Image> imageCm;
    ComponentMapper<LayerEntityTracker> layerEntityTrackercm;

    public EntityLayeringSystem() {super(Aspect.one(Anim.class, Image.class));}

    protected void processSystem() {

    }

    protected void inserted(int e) {
        Gdx.app.debug(TAG, "Inserted Entity with Id: " + e);
        Entity tracker = tagManager.getEntity("tracker");
        Gdx.app.debug(TAG, "tracker " + tracker);
        LayerEntityTracker layerTracker = layerEntityTrackercm.get(tracker);
        if (animCm.has(e)) layerTracker.add(e, animCm.get(e).layer);
        else layerTracker.add(e, imageCm.get(e).layer);
    }
}
