package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.*;


public class EntityMakerMenu {
    /**
     * Class that holds the creation
     */
    public static Entity createLabel(final World world, final float x, final float y,
                                     final String text) {
        Entity entity =  createNew(world, 10, 10)
                .add(new Text(text))
                .add(new HoverBehavior())
                .add(new Alpha())
                .add(new Bounds(x, y, 100, 100))
                .add(new ClickOpen()).
                        getEntity();
        return entity;
    }
    private static EntityEdit createNew(final World world, final float x, final float y) {
        return world.createEntity()
                .edit();
    }
}