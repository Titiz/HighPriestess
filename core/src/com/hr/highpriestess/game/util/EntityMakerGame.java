package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.components.Game.CharAnimations;
import com.hr.highpriestess.game.components.Game.Controller;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.*;

import java.util.HashMap;

/**
 * Created by Titas on 2016-07-28.
 */
public class EntityMakerGame  {


    GroupManager groupManager;

        //HashMap<String, Animation> anims
    public static Entity createPlayer(final World world, final float x, final float y
                                      ) {
        Entity entity =  createNew(world)
         .add(new Kinematics())
                .add(new Bounds(x, y, 32, 32))
                //.add(new CharAnimations(anims))
                .add(new Controller())
                        .getEntity();
        System.out.println("ASDAS");
        return entity;
    }

    public static Entity createChanger(final World world, final float x, final float y,
                                       String nextMap) {
        Entity entity =  createNew(world)
                .add(new Bounds(x, y, 32, 32))
                .add(new ChangeMap(nextMap))
                .getEntity();

        return entity;
    }

    private static EntityEdit createNew(final World world) {
        return world.createEntity()
                .edit();
    }
}
