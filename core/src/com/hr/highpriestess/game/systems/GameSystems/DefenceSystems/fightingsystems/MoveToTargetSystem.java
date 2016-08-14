package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.utils.IntBag;
import com.hr.highpriestess.game.components.Game.Defence.Movement.MoveToDestination;
import com.hr.highpriestess.game.components.Game.Defence.Target;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * This System moves a given target to the location of the target
 * This is more than the MoveToDestinationSystem as it offers
 * pathing to a changing location of an object
 */
public class MoveToTargetSystem extends BaseEntitySystem {

    GroupManager groupManager;

    ComponentMapper<Target> targetCm;
    ComponentMapper<MoveToDestination> moveToDestinationCm;
    ComponentMapper<Bounds> boundsCm;


    public MoveToTargetSystem() {
        super(Aspect.all(Target.class));
    }


    @Override
    protected void processSystem() {
        IntBag entities = subscription.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            int e = entities.get(i);
            moveToDestinationCm.create(e);
            if (!moveToDestinationCm.get(e).ignoreSurroundings) {
                int eTarget = targetCm.get(e).getTarget();
                Bounds targetXY = boundsCm.get(eTarget);
                moveToDestinationCm.get(e).setFinalXY(targetXY.x, targetXY.y);
            }
        }


    }
}
