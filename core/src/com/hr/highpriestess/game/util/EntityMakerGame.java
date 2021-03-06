package com.hr.highpriestess.game.util;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.*;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Interactibles.Interactible;
import com.hr.highpriestess.game.components.Game.Interactibles.Trigger;
import com.hr.highpriestess.game.components.Game.Tracker.LayerEntityTracker;
import com.hr.highpriestess.game.components.Game.Tracker.*;
import com.hr.highpriestess.game.components.Menu.*;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;

/**
 * Class that stores the entities that can be created in the walking part of the game.
 */
public class EntityMakerGame {


    GroupManager groupManager;
    AssetSystem assetSystem;


    public static Entity createPlayer(final World world, final float x, final float y,
                                      final String... animNames) {
        Entity entity = createNew(world)
                .add(new Kinematics(5, 5))
                .add(new Bounds(x, y, 32, 32))
                .add(new Anim(G.Layer.PLAYER, animNames))
                .add(new Player())
                .getEntity();
        return entity;
    }

    public static Entity createTalkingNPC(final  World world, final float x, final float y,
                                            final String idleAnim)
    {
        Entity entity = createNew(world)
                .add(new Bounds(x, y, 32, 32))
                .add(new Anim(G.Layer.DEFAULT, idleAnim))
                .add(new Dialogue())
                .add(new Interactible())
                .getEntity();
        return entity;
    }


    public static Entity createGate(final World world, final float x, final float y,
                                    String nextMap, String gateName, String arrivalGateName) {
        Entity entity = createNew(world)
                .add(new Bounds(x, y, 32, 32))
                .add(new ChangeMap(nextMap, gateName, arrivalGateName))
                .add (new Trigger())
                .getEntity();

        return entity;
    }



    public static Entity createTracker(final World world) {
        Entity entity = createNew(world)
                .add(new LayerEntityTracker())
                .add(new ShaderHolder())
                .add(new SpawnGateTracker())
                .add(new NeighborMapTracker())
                .add(new AnimationTracker())
                .add(new DialogueTracker())
                .getEntity();
        return entity;
    }


    public static Entity createDoor(final World world, final float x, final float y, String interactLabel,
                                    String nextMap,  String gateName, String arrivalGateName) {
        Entity entity = createNew(world)
                .add(new Bounds(x, y, 32, 32))
                .add(new Interactible(interactLabel))
                .add(new ChangeMap(nextMap, gateName, arrivalGateName))
                .getEntity();
        return entity;
    }


    public static Entity createStaticImageEntity(final World world, final float x, final float y,
                                                 final float width, final float height,
                                                 final String imageName, final G.Layer layer) {
        Entity entity = createNew(world)
                .add(new Image(imageName, layer))
                .add(new Bounds(x, y, width, height))
                .getEntity();
        return entity;
    }

    public static Entity createGlobalBackground(final World world, final float x, final float y,
                                                final float width, final float height,
                                                final String imageName) {
        return createStaticImageEntity(world, x, y, width, height, imageName, G.Layer.BACKGROUND);
    }



    public static Entity createGlobalForeground(final World world, final float x, final float y,
                                                final float width, final float height,
                                                final String imageName) {
        return createStaticImageEntity(world, x, y, width, height, imageName, G.Layer.FOREGROUND);
    }


    private static EntityEdit createNew(final World world) {
        return world.createEntity()
                .edit();
    }
}

