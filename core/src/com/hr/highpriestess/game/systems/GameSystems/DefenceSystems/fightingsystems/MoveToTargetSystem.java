package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.ImmutableBag;
import com.hr.highpriestess.game.components.Game.Defence.Target;

/**
 * This System moves a given target to the location of the target
 * This is more than the MoveToDestinationSystem as it offers
 * pathing to a changing location of an object
 */
public class MoveToTargetSystem extends BaseEntitySystem {

    GroupManager groupManager;

    ComponentMapper<Target> targetCm;


    public MoveToTargetSystem() {
        super(Aspect.all(Target.class));
    }


    @Override
    protected void processSystem() {
        ImmutableBag<Entity> entities = groupManager.getEntities("selected");
        ImmutableBag<Entity> enemies = groupManager.getEntities("enemies");

    }
}
