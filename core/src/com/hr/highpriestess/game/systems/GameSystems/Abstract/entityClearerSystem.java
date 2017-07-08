package com.hr.highpriestess.game.systems.GameSystems.Abstract;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.hr.highpriestess.G;

/**
 * Created by Titas on 2016-08-03.
 */
public abstract class EntityClearerSystem extends BaseSystem {

    protected TagManager tagManager;
    protected GroupManager groupManager;


    protected final void deleteEntities(String identifier, GroupManager groupManager){
        ImmutableBag<Entity> entities = groupManager.getEntities(identifier);
        for(Entity entity : entities) {
            removeEntityFromWorld(entity);
            groupManager.remove(entity, identifier);
        }
    }

    protected final void deleteEntities(String identifier, TagManager tagManager){
        Entity entity = tagManager.getEntity(identifier);
        removeEntityFromWorld(entity);
        tagManager.unregister(identifier);

    }

    protected final void removeEntityFromWorld(Entity entity) {
        if (entity != null)
            this.getWorld().delete(entity.getId());
    }


    public abstract void clearEntities();
}
