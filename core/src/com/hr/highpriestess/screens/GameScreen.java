package com.hr.highpriestess.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.GameSystems.MapSystem;
import com.hr.highpriestess.game.systems.GameSystems.Render.TilemapRender;
import com.hr.highpriestess.game.systems.MenuSystems.*;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.HoverAlphaSystem;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.HoverAnimationSystem;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.MouseHoverSystem;
import com.hr.highpriestess.game.systems.MenuSystems.Render.RenderMenu;

/**
 * Created by Titas on 2016-07-23.
 */
public class GameScreen extends AbstractScreen {

    Game game;
    World world;

    public GameScreen(Game game) {

        this.game = game;
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(
                        // make the menu
                        new CameraSystem(),
                        new MapSystem(),
                        new TilemapRender()
                ).build();
        G.gameWorld = new World(config);

    }




    @Override
    public void dispose() {
        this.world.dispose();
    }





    public void render(float deltaTime) {
        // clear the game
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // process the menuWorld
        G.gameWorld.process();

    }
}
