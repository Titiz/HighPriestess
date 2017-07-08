package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.*;
import com.artemis.annotations.EntityId;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Tracker.LayerEntityTracker;
import com.hr.highpriestess.game.components.Game.Tracker.ShaderHolder;
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;


/**
 * Created by Titas on 2016-07-29.
 */
public class GameEntityClearerSystem extends EntityClearerSystem {


    String TAG = GameEntityClearerSystem.class.getName();

    ComponentMapper<LayerEntityTracker> layCm;
    ComponentMapper<ShaderHolder>  shaderCm;

    public void clearEntities() {
        Gdx.app.debug(TAG, "CLEARING ENTITIES IN WORLD " + this.getWorld().getClass().getName());

        int tracker = tagManager.getEntity("tracker").getId();

        IntBag entities = world.getAspectSubscriptionManager() // We remove all entities that are not the tracker
                .get(Aspect.all())
                .getEntities();

        int[] ids = entities.getData();
        for (int i = 0, s = entities.size(); s > i; i++) {
            if (ids[i] != tracker) world.delete(ids[i]);
        }

        layCm.get(tracker).reset();
        shaderCm.get(tracker).reset();


    }


    @Override
    protected void processSystem() {

    }
}
