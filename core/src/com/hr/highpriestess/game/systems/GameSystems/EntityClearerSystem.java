package com.hr.highpriestess.game.systems.GameSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.annotations.EntityId;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.hr.highpriestess.G;


/**
 * Created by Titas on 2016-07-29.
 */
public class EntityClearerSystem extends BaseSystem {


    GroupManager groupManager;
    TagManager tagManager;





    private void deleteEntities(String identifier, GroupManager groupManager){
        ImmutableBag<Entity> entities = groupManager.getEntities(identifier);
        for(Entity entity : entities) {
            removeEntityFromWorld(entity);
            //groupManager.remove(entity, identifier);   not sure if needed
        }
    }

    private void deleteEntities(String identifier, TagManager tagManager){
        Entity entity = tagManager.getEntity(identifier);
        removeEntityFromWorld(entity);
        //agManager.unregister(identifier);  not sure if needed

    }

    public void removeEntityFromWorld(Entity entity) {
        if (entity != null)
            G.gameWorld.delete(entity.getId());
    }


    public void clearEntities() {

            deleteEntities("player", tagManager);
            deleteEntities("enemy", groupManager);
            deleteEntities("gate", groupManager );



    }


    @Override
    protected void processSystem() {

    }
}
