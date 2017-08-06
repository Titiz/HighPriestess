package com.hr.highpriestess.game.systems.GameSystems.Render.Dialogue;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
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


    private String TAG = ActiveDialogueRenderSystem.class.getName();

    ComponentMapper<Player> playerCm;
    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    ComponentMapper<Dialogue> dialogueCm;
    TagManager tagmanager;

    BitmapFont font;

    AssetSystem assetSystem;
    SpriteBatch batch;



    CameraSystem cameraSystem;
    ComponentMapper<Bounds> boundsCm;
    Array<Label> labels;




    public ActiveDialogueRenderSystem() {
        font = G.assetSystem.assetManager.get("smallFont.fnt");
        batch = new SpriteBatch();
        labels = new Array<Label>();
    }

    private void makeLabelsForDecisions(DialogueTracker dialogueTracker) {
        for (int i = 0; i < dialogueTracker.decisions.length; i++) {
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            Label label = new Label(dialogueTracker.decisions[i], style);
            labels.add(label);
        }
    }

    @Override
    protected void processSystem() {
        int tracker = tagmanager.getEntity("tracker").getId();
        Player player = playerCm.get(tagmanager.getEntity("player").getId());
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);
        if (player.isActiveButtonClicked) labels.clear();
        if (!dialogueTracker.inDialogue) return;
        if (!dialogueTracker.isMakingDecision) return;
        if (dialogueTracker.dialogueStopped) return;
        if (dialogueTracker.decisions == null) return;
        if (labels.size == 0)
            makeLabelsForDecisions(dialogueTracker);
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        labels.get(dialogueTracker.currentDecisionId).draw(batch, 1.0f);
        batch.end();
    }
}
