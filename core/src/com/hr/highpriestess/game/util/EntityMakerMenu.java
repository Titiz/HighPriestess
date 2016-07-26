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
    public static Entity createHoverTextAnimation(final World world, final float x, final float y,
                                                  final String text, final Animation[] anim, String ID,
                                                  int[] layer) {
        Entity entity =  createNew(world)
                .add(new Text(text))
                .add(new HoverBehavior())
                .add(new AnimationBehind(anim[0], anim[1], anim[2]))
                .add(new Alpha())
                .add(new Bounds(x, y, 100, 100))
                .add(new ClickOpen(ID))
                .add(new Layer(layer[0], layer[1])).
                        getEntity();
        return entity;
    }

    public static Entity createHoverTextAnimationNoAlpha(final World world, final float x, final float y,
                                                               final String text, final Animation[] anim, String ID,
                                                               int[] layer) {
        Entity entity =  createNew(world)
                .add(new Text(text))
                .add(new HoverBehavior())
                .add(new AnimationBehind(anim[0], anim[1], anim[2]))
                .add(new Bounds(x, y, 100, 100))
                .add(new ClickOpen(ID))
                .add(new Layer(layer[0], layer[1])).
                        getEntity();
        return entity;
    }

    public static Entity createTitle(final World world, final float x, final float y,
                                                         final String text, final Animation[] anim,
                                                         int[] layer) {
        Entity entity =  createNew(world)
                .add(new Text(text))
                .add(new AnimationBehind(anim[0], anim[1], anim[2]))
                .add(new Bounds(x, y, 100, 100))
                .add(new Layer(layer[0], layer[0])).
                        getEntity();
        return entity;
    }


    private static EntityEdit createNew(final World world) {
        return world.createEntity()
                .edit();
    }
}