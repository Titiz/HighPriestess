package com.hr.highpriestess.screens;

import com.artemis.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.systems.MenuSystems.*;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.HoverAlphaSystem;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.HoverAnimationSystem;
import com.hr.highpriestess.game.systems.MenuSystems.LabelSystem;
import com.hr.highpriestess.game.systems.MenuSystems.HoverSystems.MouseHoverSystem;
import com.hr.highpriestess.game.systems.MenuSystems.Render.RenderMenu;

/**
 * menu screen for the menu part of the game.
 */
public class MenuScreen extends AbstractScreen {
    Game game;
    World world;
    public MenuScreen(Game game) {
        this.game = game;
        if (G.assetSystem == null)
            G.assetSystem = new AssetSystem();
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(

                        G.assetSystem,

                        // make the menu
                        new SetupMenu(),

                        //setup the necessary Entities
                        new LayerManager(),


                        new CollisionUtilSystem(),
                        new CameraSystem(),

                        //mouseSystems

                        new HoverAlphaSystem(),
                        new MouseHoverSystem(),
                        new HoverAnimationSystem(),
                        new OpenOnClick(),
                        new LabelSystem(),

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
        G.menuWorld.dispose();
    }


}
