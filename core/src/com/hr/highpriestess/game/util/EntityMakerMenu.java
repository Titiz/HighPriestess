package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hr.highpriestess.game.components.Menu.*;


/**
 * Class that stores the entities used in the menu
 */

public class EntityMakerMenu {
    /**
     * Class that holds the creations
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

    public static Entity createBackground(final World world, final float x, final float y, String filePath) {
        Entity entity =  createNew(world)
                .add(new Bounds(x, y, 0, 0))
                .add(new ImageComponent(new Texture(filePath)))
                .add(new Layer(100, 100))
                        .getEntity();
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

    public static Entity createTransition(final World world) {
        Entity entity =  createNew(world)
                .add(new Transition())
                .add(new Bounds (0, 0, 0, 0))
                .add(new Layer(100, 100)).
                        getEntity();
        return entity;
    }




    private static EntityEdit createNew(final World world) {
        return world.createEntity()
                .edit();
    }
}