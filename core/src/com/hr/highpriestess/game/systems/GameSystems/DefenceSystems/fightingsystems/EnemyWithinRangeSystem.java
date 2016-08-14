package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.*;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.AttackStats;
import com.hr.highpriestess.game.components.Game.Defence.Health;
import com.hr.highpriestess.game.components.Game.Defence.Target;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.util.Utilities;

/**
 * This is a major system in the game that will determine
 * how the units fight between themselves.
 */
public class EnemyWithinRangeSystem extends BaseSystem {


    String TAG = EnemyWithinRangeSystem.class.getName();

    ComponentMapper<AttackStats> attackStatsCm;
    ComponentMapper<Health> healthCm;
    ComponentMapper<Bounds> boundsCm;
    ComponentMapper<Target> targetCm;
    GroupManager groupManager;

    final int VIEW_RANGE = 5 * G.CELL_SIZE;




    @Override
    protected void processSystem() {
        checkEntityView("allies", "enemies");
        checkEntityView("enemies", "allies");
    }

    private void checkEntityView(String e1Name, String e2Name) {
        //function which checks if an entity is within another's view.
        ImmutableBag<Entity> e1s = groupManager.getEntities(e1Name);
        ImmutableBag<Entity> e2s = groupManager.getEntities(e2Name);
        for (int i = 0; i < e1s.size(); i++) {
            Entity e1 = e1s.get(i);
            if (!targetCm.has(e1.getId()) || Utilities.distance(e1, this.world.getEntity(targetCm.get(e1.getId()).getTarget())) > VIEW_RANGE) {
                targetCm.remove(e1);
                Entity e2 = getClosestEntity(e2s, e1);
                if (Utilities.distance(e1, e2) < VIEW_RANGE) {
                    targetCm.create(e1.getId());
                    targetCm.get(e1.getId()).setTarget(e2.getId());
                    Gdx.app.debug(TAG, e1 + " has set target " + e2);
                }
            } else if (targetCm.get(e1.getId()).mouseTarget);

        }
    }




    private Entity getClosestEntity(ImmutableBag<Entity> entities, Entity e1) {
        float smallestDistance = 99999;
        Entity smallestEntity = null;
        for (Entity e2 : entities) {
            if (Utilities.distance(e1, e2) < smallestDistance) {
                smallestEntity = e2;
                smallestDistance = Utilities.distance(e1, e2);
            }
        }
        return smallestEntity;

    }


}
