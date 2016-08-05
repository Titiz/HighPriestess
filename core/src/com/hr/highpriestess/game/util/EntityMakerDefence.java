package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.Defence.Selectable;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.Bounds;

/**
 * Created by Titas on 2016-08-02.
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


    public static Entity create(final World world, final float x, final float y
    ) {

        Entity entity = createNew(world)
                .add(new Kinematics(5, 5))
                .add(new Bounds(x, y, 32, 32))
                .add(new Anim("menuAnim1Before", Anim.Layer.ENEMY))
                .add(new Selectable(0))
                .getEntity();
        return entity;
    }
}
