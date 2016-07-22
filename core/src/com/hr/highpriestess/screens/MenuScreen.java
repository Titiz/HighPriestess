package com.hr.highpriestess.screens;

import com.artemis.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hr.highpriestess.G;
import com.hr.highpriestess.HighPriestessMain;
import com.hr.highpriestess.game.components.HoverableText;
import com.hr.highpriestess.game.systems.*;
import com.hr.highpriestess.game.systems.RenderSystems.RenderMenu;

/**
 * Created by Titas on 2016-07-18.
 */
public class MenuScreen extends AbstractScreen {
    Game game;
    World world;
    public MenuScreen(Game game) {

        this.game = game;
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        // make the menu
                        new SetupMenu(),

                        //setup the necessary Entities
                        new HoverAlpha(),
                        new AssetSystem(),
                        new CollisionSystem(),
                        new CameraSystem(),
                        new OpenOnClick(),
                        //render Methods
                        new RenderMenu()
                ).build();
        G.menuWorld = new World(config);

    }

    @Override
    public void render(float deltaTime) {
        // clear the game
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // process the menuWorld
        G.menuWorld.process();
    }

    @Override
    public void dispose() {
        this.world.dispose();
    }
}
