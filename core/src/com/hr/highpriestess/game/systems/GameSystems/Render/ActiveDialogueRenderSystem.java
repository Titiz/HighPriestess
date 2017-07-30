package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;
import com.hr.highpriestess.game.components.Menu.Bounds;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2017-07-30.
 */
public class ActiveDialogueRenderSystem extends BaseSystem {


    ComponentMapper<Player> playerCm;
    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    ComponentMapper<Dialogue> dialogueCm;
    TagManager tagmanager;

    BitmapFont font;

    AssetSystem assetSystem;
    SpriteBatch batch;



    CameraSystem cameraSystem;
    ComponentMapper<Bounds> boundsCm;


    public ActiveDialogueRenderSystem() {
        font = G.assetSystem.assetManager.get("smallFont.fnt");
        batch = new SpriteBatch();
    }

    private void makeLabelsForDecisions() {

    }

    @Override
    protected void processSystem() {

    }
}
