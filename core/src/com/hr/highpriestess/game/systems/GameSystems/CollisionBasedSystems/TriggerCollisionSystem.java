package com.hr.highpriestess.game.systems.GameSystems.CollisionBasedSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.hr.highpriestess.game.components.Game.Interactibles.Interactible;
import com.hr.highpriestess.game.components.Game.Interactibles.Trigger;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionUtilSystem;

/**
 * Created by Titas on 2017-07-07.
 */
public class TriggerCollisionSystem extends BaseEntitySystem {


    private String TAG = TriggerCollisionSystem.class.getName();

    public TriggerCollisionSystem() {
        super(Aspect.one(Interactible.class, Trigger.class).all(Bounds.class));
    }

    CollisionUtilSystem collisionUtilSystem;
    int player;

    ComponentMapper<Player> playerCm;
    ComponentMapper<Interactible> interactibleCm;
    ComponentMapper<Interactible> triggerCm;
    TagManager tagManager;
    GroupManager groupManager;

    @Override
    protected void begin(){
        player = tagManager.getEntity("player").getId();
        playerCm.get(player).collidingEntity = null;
    }

    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        for (int i = 0, s = actives.size(); s > i; i++) {
            int e = ids[i];
            if (collisionUtilSystem.twoEntityCollision(e, player)) {
                playerCm.get(player).collidingEntity = this.world.getEntity(e);
                break;
            }
        }

    }


}
