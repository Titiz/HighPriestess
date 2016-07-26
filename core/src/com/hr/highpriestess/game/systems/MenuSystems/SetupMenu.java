package com.hr.highpriestess.game.systems.MenuSystems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.util.EntityMakerMenu;


public class SetupMenu extends BaseEntitySystem {


    public static final String TAG = SetupMenu.class.getName();

    boolean isSetup = false;

    CameraSystem cameraSystem;
    AssetSystem assetSystem;



    public SetupMenu() {
        super(Aspect.all());
    }

    private void setup(){
        /**
         * This is where we setup all parts of the Menu.
         */
        float multiplier = cameraSystem.getZOOM();
        Animation[] anims = new Animation[3];
        anims[1] = assetSystem.sprites.get("menuAnim1");
        anims[0] = assetSystem.sprites.get("menuAnim1Before");
        anims[2] = assetSystem.sprites.get("menuAnim1After");
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 100, 100, "Play", anims, "Play", new int[] {0, 2});
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 300, 300, "margin", anims, "margin", new int[] {0, 2});
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 40, 200, "bye", anims, "Options", new int[] {1, 0});
        EntityMakerMenu.createHoverTextAnimationNoAlpha(G.menuWorld, 200, 200, "Continue", anims, "NewGame", new int[] {2, -1});
        EntityMakerMenu.createHoverTextAnimationNoAlpha(G.menuWorld, 200, 100, "New Game", anims, "NewGame", new int[] {2, 0});
        EntityMakerMenu.createTitle(G.menuWorld, 400, 200, "High Priestess", anims, new int [] {0, 0});
    }

    @Override
    protected void processSystem() {
        // setup is called once and then the processing continues by doing nothing
        // TODO: Potentially find a way to call this once in total.
        if (!isSetup) {
            isSetup = true;
            setup();
            Gdx.app.debug(TAG, "SETUP COMPLETED");
        }
    }
}
