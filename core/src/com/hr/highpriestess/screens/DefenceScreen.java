package com.hr.highpriestess.screens;

/**
 * Created by Titas on 2016-08-02.
 */

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.GameSystems.*;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.*;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.MoveDestinationSystem;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement.CreatePathSystem;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement.MoveThroughPathSystem;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Movement.NodeMovement.NodeSelectMoveSystem;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.Render.RenderSelectionSquare;
import com.hr.highpriestess.game.systems.GameSystems.DefenceSystems.fightingsystems.*;
import com.hr.highpriestess.game.systems.GameSystems.Render.EntityRenderSystem;
import com.hr.highpriestess.game.systems.GameSystems.Render.TilemapRender;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CollisionSystem;



/**
 * @author Titas
 * @version
 */

//Defence mode screen for the defence sequence of the game.

public class DefenceScreen extends AbstractScreen {

    Game game;
    World world;

    public DefenceScreen(Game game) {

        this.game = game;
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        // make the menu
                        new GroupManager(),
                        new TagManager(),

                        new CameraSystem(),
                        new MouseCameraSystem(),

                        G.assetSystem,

                        new DefenceMapSystem(),
                        new WaveMapSystem(),


                        new DefenceEntitySpawnerSystem(),
                        new DefenceEntityClearerSystem(),

                        new SelectSystem(),
                        new MoveDestinationSystem(),
                        new GridSystem(),

                        new CreatePathSystem(),
                        new MoveThroughPathSystem(),
                        new NodeSelectMoveSystem(),




                        new MakeSelectionSquare(),
                        new CollisionSystem(),




                        new EnemyWithinRangeSystem(),
                        new MouseTargetSystem(),
                        new MoveToTargetSystem(),


                        new KinematicsSystem(),


                        new TilemapRender(),
                        new EntityRenderSystem(),
                        new RenderSelectionSquare()
                ).build();
        G.defenceWorld = new World(config);

    }




    @Override
    public void dispose() {
        G.defenceWorld.dispose();
    }





    public void render(float deltaTime) {
        // clear the game
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // process the defenceWorld
        G.defenceWorld.process();

    }
}
