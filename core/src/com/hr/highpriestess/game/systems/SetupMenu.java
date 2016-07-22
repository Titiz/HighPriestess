package com.hr.highpriestess.game.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.badlogic.gdx.Gdx;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.HoverableText;
import com.hr.highpriestess.game.util.EntityMakerMenu;


public class SetupMenu extends BaseEntitySystem {


    public static final String TAG = SetupMenu.class.getName();

    boolean isSetup = false;

    CameraSystem cameraSystem;

    public SetupMenu() {
        super(Aspect.all());
    }

    private void setup(){
        /**
         * This is where we setup all parts of the Menu.
         */
        float multiplier = cameraSystem.getZOOM();

        EntityMakerMenu.createLabel(G.menuWorld, 0, 0, "HI");
        //EntityMakerMenu.createLabel(G.menuWorld, 40, 200, "bye");
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
