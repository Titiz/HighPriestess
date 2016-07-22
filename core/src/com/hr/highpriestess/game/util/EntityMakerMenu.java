package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.*;


public class EntityMakerMenu {
    /**
     * Class that holds the creation
     */
    public static Entity createLabel(final World world, final float x, final float y,
                                     final String text, final Animation[] anim) {
        Entity entity =  createNew(world, 10, 10)
                .add(new Text(text))
                .add(new HoverBehavior())
                .add(new AnimationBehind(anim[0], anim[1], anim[2]))
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