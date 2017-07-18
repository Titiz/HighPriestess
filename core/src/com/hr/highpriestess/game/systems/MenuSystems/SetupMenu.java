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
         * Manual setup of the menu.
         */
        float multiplier = cameraSystem.getZOOM();
        Animation[] anims = new Animation[3];
        anims[1] = assetSystem.animations.get("menuAnim1");
        anims[0] = assetSystem.animations.get("menuAnim1Before");
        anims[2] = assetSystem.animations.get("menuAnim1After");
        EntityMakerMenu.createBackground(G.menuWorld, 0, 0, "background.png");
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 100, 100, "Play", anims, "Play", new int[] {0, 2});
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 300, 300, "margin", anims, "margin", new int[] {0, 2});
        EntityMakerMenu.createHoverTextAnimation(G.menuWorld, 40, 200, "bye", anims, "Options", new int[] {1, 0});
        EntityMakerMenu.createHoverTextAnimationNoAlpha(G.menuWorld, 200, 200, "Continue", anims, "NewGame", new int[] {2, -1});
        EntityMakerMenu.createHoverTextAnimationNoAlpha(G.menuWorld, 200, 100, "New Game", anims, "NewGame", new int[] {2, 0});

    }

    @Override
    protected void processSystem() {
        // setup is called once and then this system is removed.
        if (!isSetup) {
            isSetup = true;
            setup();
            Gdx.app.debug(TAG, "SETUP COMPLETED");
        } else {
            this.dispose();
        }
    }
}
