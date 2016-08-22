package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Defence.AttackStats;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Class that stores the entities that can be created in the defence part of the game
 */
public class EntityMakerDefence {

    public static Entity createSelectionSquare(final World world, final float x, final float y
    ) {

        Entity entity =  createNew(world)
                .add(new Bounds(x, y, 0, 0))
                .getEntity();
        return entity;
    }

    private static EntityEdit createNew(final World world) {
        return world.createEntity()
                .edit();
    }


    public static Entity createEnemy(final World world, final float x, final float y
    ) {

        Entity entity = createNew(world)
                .add(new Kinematics(0.5f, 0.5f))
                .add(new Bounds(x, y, G.UNIT_SIZE, G.UNIT_SIZE))
                .add(new Anim("menuAnim1Before", Anim.Layer.ENEMY))
                .add(new Selectable(1))
                .add(new AttackStats(AttackStats.DamageType.PHYSICAL,
                        2, AttackStats.AttackSpeed.SLOW,
                        AttackStats.Range.MELEE,
                        AttackStats.ArmorType.MEDIUM,
                        AttackStats.MagicalArmorType.NONE))
                .getEntity();
        return entity;
    }

    public static Entity createAlly(final World world, final float x, final float y
    ) {

        Entity entity = createNew(world)
                .add(new Kinematics(0.5f, 0.5f))
                .add(new Bounds(x, y, G.UNIT_SIZE, G.UNIT_SIZE))
                .add(new Anim("menuAnim1After", Anim.Layer.DEFAULT))
                .add(new Selectable(0))
                .add(new AttackStats(AttackStats.DamageType.PHYSICAL,
                        2, AttackStats.AttackSpeed.SLOW,
                        AttackStats.Range.MELEE,
                        AttackStats.ArmorType.MEDIUM,
                        AttackStats.MagicalArmorType.NONE))
                .getEntity();
        return entity;
    }
}
