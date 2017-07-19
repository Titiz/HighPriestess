package com.hr.highpriestess.game.systems.GameSystems.Render;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;
import com.hr.highpriestess.game.systems.MenuSystems.AssetSystem;
import com.hr.highpriestess.game.systems.MenuSystems.CameraSystem;

/**
 * Created by Titas on 2017-07-19.
 */
public class DialogueRenderSystem extends BaseSystem {

    ComponentMapper<Player> playerCm;
    ComponentMapper<DialogueTracker> dialogueTrackerCm;
    ComponentMapper<Dialogue> dialogueCm;
     TagManager tagmanager;

    BitmapFont font;

    AssetSystem assetSystem;
    SpriteBatch batch;

    CameraSystem cameraSystem;

    Label activeLabel;
    boolean newDialogue;
    boolean lastLine;

    String TAG = DialogueRenderSystem.class.toString();


    public DialogueRenderSystem() {
        font = G.assetSystem.assetManager.get("smallFont.fnt");
        batch = new SpriteBatch();
    }

    @Override
    protected void processSystem() {
        Entity tracker = tagmanager.getEntity("tracker");
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);
        if (!dialogueTracker.inDialogue) return;

        if (activeLabel == null) {
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            activeLabel = new Label("", style);
            activeLabel.setFontScale(0.40f);
            activeLabel.setY(300);
            activeLabel.setX(300);
            this.newDialogue = true;
        }

        int dialogueEntity = dialogueTracker.dialogueEntity;
        String text = dialogueCm.get(dialogueEntity).getConversation();


        char[] charText = text.toCharArray();
        String string = "";
        int currentChar = dialogueTracker.dialoguePointer;

        int player = tagmanager.getEntity("player").getId();
        if (playerCm.get(player).isActiveButtonClicked) {
            activeLabel.setText("");
        }

        while(activeLabel.getPrefWidth() < 300 && currentChar < charText.length) {
            string += charText[currentChar];
            activeLabel.setText(string);
            currentChar++;
        }

        dialogueTracker.dialoguePointer = currentChar;




        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        activeLabel.draw(batch, 1.0f);
        batch.end();

        if (currentChar > charText.length && playerCm.get(player).isActiveButtonClicked) {
            activeLabel = null;
            dialogueTracker.inDialogue = false;
            Gdx.app.debug(TAG, "Player is out of the dialogue");
        }

        if (currentChar == charText.length) {
            Gdx.app.debug(TAG, "currentChar is the last Char");
            dialogueTracker.dialoguePointer ++;
        }


    }
}
