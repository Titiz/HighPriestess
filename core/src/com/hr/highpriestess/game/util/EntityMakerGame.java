package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.hr.highpriestess.game.components.Game.Anim;
import com.hr.highpriestess.game.components.Game.ChangeMap;
import com.hr.highpriestess.game.components.Game.Kinematics;
import com.hr.highpriestess.game.components.Menu.*;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Class that stores the entities that can be created in the walking part of the game.
 */
public class EntityMakerGame {


    GroupManager groupManager;
    AssetSystem assetSystem;


    public static Entity createPlayer(final World world, final float x, final float y
    ) {

        Entity entity = createNew(world)
                .add(new Kinematics(5, 5))
                .add(new Bounds(x, y, 32, 32))
                .add(new Anim(Anim.Layer.PLAYER, "idlePlayer"))
                .getEntity();
        return entity;
    }

    public static Entity createEnemy(final World world, final float x, final float y
    ) {

        Entity entity = createNew(world)
                .add(new Kinematics(5, 5))
                .add(new Bounds(x, y, 32, 32))
                .add(new Anim(Anim.Layer.ENEMY, "menuAnim1Before"))
                .getEntity();
        return entity;
    }

    public static Entity createChanger(final World world, final float x, final float y,
                                       String nextMap) {
        Entity entity = createNew(world)
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

