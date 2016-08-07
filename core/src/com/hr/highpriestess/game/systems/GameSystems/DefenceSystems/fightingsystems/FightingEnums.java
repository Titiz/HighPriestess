package com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems;

import com.artemis.Entity;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Defence.AttackStats;

/**
 * Created by Titas on 2016-08-07.
 */
public class FightingEnums {



    private float getRange(Entity entity) {
        AttackStats stats = entity.getComponent(AttackStats.class);
        float range = 0;
        switch(stats.range) {
            case MELEE:
                range = 10;
                break;
            case RANGED_CLOSE:
                range = G.CELL_SIZE *2;
                break;
            case RANGED_MIDDLE:
                range = G.CELL_SIZE *3;
                break;
            case RANGED_FAR:
                range = G.CELL_SIZE *4;
                break;
        }
        return range;
    }



}
