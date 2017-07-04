package com.hr.highpriestess.game.systems.GameSystems.CollisionBasedSystems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;

/**
 * Created by Titas on 2017-07-04.
 */
public class CollideWithInteractiblesSystem extends BaseSystem {

    private String TAG = CollideWithInteractiblesSystem.class.getName();

    CollisionSystem collisionSystem;

    TagManager tagManager;
    GroupManager groupManager;

    protected void processSystem() {
        Entity player = tagManager.getEntity("player");
        ImmutableBag<Entity> Interactibles = groupManager.getEntities("Interactibles");
        for (Entity Interactible : Interactibles) {
            if (collisionSystem.twoEntityCollision(Interactible.getId(), player.getId())) {
                Gdx.app.debug(TAG, " Collision detected of Player with Interactible");
            }
            break;
        }
    }
}