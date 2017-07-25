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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.hr.highpriestess.G;
import com.hr.highpriestess.game.components.Game.Interactibles.Dialogue;
import com.hr.highpriestess.game.components.Game.Player;
import com.hr.highpriestess.game.components.Game.Tracker.DialogueTracker;
import com.hr.highpriestess.game.components.Menu.Bounds;
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
    ComponentMapper<Bounds> boundsCm;

    Label activeLabel;
    Queue<Label> labelQueue;
    float timer;
    boolean isStringFinished;
    float width = 100;
    float heighOfBubble = 0;
    float baseSecondsPerCharacter = 0.02f;
    float secondsPerCharacter = baseSecondsPerCharacter;
    final int maxDialogueLines = 3;

    int currentlyTalkingEntity;





    Array<String> words;

    String TAG = DialogueRenderSystem.class.toString();


    public DialogueRenderSystem() {
        font = G.assetSystem.assetManager.get("smallFont.fnt");
        batch = new SpriteBatch();
        timer = 0;
        isStringFinished = false;
        labelQueue = new Queue<Label>();
    }


    private void shapeActiveLabel() {
        activeLabel.setFontScale(1.0f);
        float y = boundsCm.get(tagmanager.getEntity("player")).y +
                boundsCm.get(tagmanager.getEntity("player")).height -
                activeLabel.getPrefHeight() * (labelQueue.size - maxDialogueLines );
        float x = boundsCm.get(tagmanager.getEntity("player")).x;
        Gdx.app.debug(TAG, "activeLabelY: " + y);
        activeLabel.setY(y);
        activeLabel.setX(x);
    }



    @Override
    protected void processSystem() {
        Entity tracker = tagmanager.getEntity("tracker");
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);
        if (!dialogueTracker.inDialogue) return;


        if (activeLabel == null) {
            Gdx.app.debug(TAG, "making new label");
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            activeLabel = new Label("", style);
            //Camera stuff
            cameraSystem.setZOOM(0.5f);
            cameraSystem.reset();
            shapeActiveLabel();
        }



        int dialogueEntity = dialogueTracker.dialogueEntity;
        String text = dialogueCm.get(dialogueEntity).getConversation();


        char[] charText = text.toCharArray();
        String string = activeLabel.getText().toString();
        int currentChar = dialogueTracker.dialoguePointer;

        int player = tagmanager.getEntity("player").getId();


        if (playerCm.get(player).isActiveButtonClicked && currentChar != 0) {
            if (!this.isStringFinished){
                secondsPerCharacter = 0;
                Gdx.app.debug(TAG, "finishing sentence");
            } else {
                secondsPerCharacter = baseSecondsPerCharacter;
                Gdx.app.debug(TAG, "going to next sentence");
                isStringFinished = false;
                labelQueue.clear();
                shapeActiveLabel();
                string = "";
                activeLabel.setText(string);
            }
        }

        if (timer > secondsPerCharacter) {
            timer = 0;
            if (!this.isStringFinished && currentChar < charText.length) {
                if (activeLabel.getPrefWidth() > width) {
                    while (charText[currentChar-1] != ' ') {
                        currentChar --;
                        string = string.substring(0, string.length()-1);
                    }
                    activeLabel.setText(string);
                    Gdx.app.debug(TAG, "active label exceeded width, changing active Label");
                    Gdx.app.debug(TAG, "adding the active label to the queue");
                    labelQueue.addFirst(activeLabel);
                    Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
                    activeLabel = new Label("", style);
                    string = "";
                    shapeActiveLabel();
                }
                string += charText[currentChar];
                activeLabel.setText(string);
                if (charText[currentChar] == '.') {
                    Gdx.app.debug(TAG, "string finished naturally");
                    this.isStringFinished = true;
                }
                currentChar++;
                Gdx.app.debug(TAG, "The current character pointer is: " + currentChar);
                dialogueTracker.dialoguePointer = currentChar;
            }
        }

        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        activeLabel.draw(batch, 1);
        for (Label label : labelQueue) {
            label.draw(batch, 1);
        }
        batch.end();


        if (currentChar > charText.length && playerCm.get(player).isActiveButtonClicked) {
            reset(dialogueTracker);
        }

        if (currentChar == charText.length) {
            Gdx.app.debug(TAG, "currentChar is the last Char");
            dialogueTracker.dialoguePointer ++;
            Gdx.app.debug(TAG, "currently one-click away from finishing dialogue");
        }
        timer += Gdx.graphics.getDeltaTime();
    }

    private void reset(DialogueTracker dialogueTracker) {
        Gdx.app.debug(TAG, "Resetting the Dialogue Render System");
        activeLabel = null;
        dialogueTracker.inDialogue = false;
        dialogueTracker.dialoguePointer = 0;
        this.isStringFinished = false;

        //camera stuff
        cameraSystem.setZOOM(1.0f);
        cameraSystem.reset();
        Gdx.app.debug(TAG, "Player is out of the dialogue");
    }
}
