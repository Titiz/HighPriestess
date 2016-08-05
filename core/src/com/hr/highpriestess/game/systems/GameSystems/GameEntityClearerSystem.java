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
import com.hr.highpriestess.game.systems.GameSystems.Abstract.EntityClearerSystem;


/**
 * Created by Titas on 2016-07-29.
 */
public class GameEntityClearerSystem extends EntityClearerSystem {



    public void clearEntities() {

            deleteEntities("player", tagManager);
            deleteEntities("enemy", groupManager);
            deleteEntities("gate", groupManager );



    }


    @Override
    protected void processSystem() {

    }
}
