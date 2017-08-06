package com.hr.highpriestess.game.systems.GameSystems.Render.Dialogue;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
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
import com.hr.highpriestess.game.util.Nodes.DialogueNode;
import com.hr.highpriestess.game.util.Nodes.TweenNode;

/**
 * Created by Titas on 2017-07-19.
 */
public class PassiveDialogueRenderSystem extends BaseSystem {

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
    final int maxDialogueLines = 2;

    String currentSpeakerName = null;







    Array<String> words;

    String TAG = PassiveDialogueRenderSystem.class.toString();


    public PassiveDialogueRenderSystem() {
        font = G.assetSystem.assetManager.get("smallFont.fnt");
        batch = new SpriteBatch();
        timer = 0;
        isStringFinished = false;
        labelQueue = new Queue<Label>();
    }


    private void shapeActiveLabel() {
        activeLabel.setFontScale(1.0f);

        Entity tracker = tagmanager.getEntity("tracker");
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);
        DialogueNode currentNode = (DialogueNode) dialogueTracker.getCurrentNode();
        currentSpeakerName = currentNode.speaker;
        if (currentSpeakerName == null) currentSpeakerName = "player";
        else currentSpeakerName = currentNode.speaker.toLowerCase();



        Gdx.app.debug(TAG, "current speaker is: " + currentSpeakerName);

        setActiveLabelPosition(currentSpeakerName);
    }

    private void setActiveLabelPosition(String speakerName) {
        float y = boundsCm.get(tagmanager.getEntity(speakerName)).y +
                boundsCm.get(tagmanager.getEntity(speakerName)).height -
                activeLabel.getPrefHeight() * (labelQueue.size - maxDialogueLines);
        float x = boundsCm.get(tagmanager.getEntity(speakerName)).x;
        Gdx.app.debug(TAG, "activeLabelY: " + y);
        activeLabel.setY(y);
        activeLabel.setX(x);
    }

    private void setAllLabelPosition(String speakerName) {
        float y = boundsCm.get(tagmanager.getEntity(speakerName)).y +
                boundsCm.get(tagmanager.getEntity(speakerName)).height;
        float x = boundsCm.get(tagmanager.getEntity(speakerName)).x;
        for (int i = 0; i < labelQueue.size; i++) {
            y += activeLabel.getPrefHeight() * (maxDialogueLines - i);
            labelQueue.get(i).setPosition(x, y);
        }
        setActiveLabelPosition(speakerName);
    }


    private void makeLabelIfNull() {
        if (activeLabel == null) {
            Gdx.app.debug(TAG, "label found to be null");
            Gdx.app.debug(TAG, "making new label");
            Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());
            activeLabel = new Label("", style);
            //Camera stuff
            cameraSystem.setZOOM(0.5f);
            cameraSystem.reset();
            shapeActiveLabel();
        }
    }

    @Override
    protected void processSystem() {
        Entity tracker = tagmanager.getEntity("tracker");
        DialogueTracker dialogueTracker = dialogueTrackerCm.get(tracker);

        if (!dialogueTracker.inDialogue) return;
        if (dialogueTracker.dialogueStopped) return;
        if (dialogueTracker.isMakingDecision) return;

        makeLabelIfNull();

        int dialogueEntity = dialogueTracker.dialogueEntity;
        DialogueNode dialogueNode = (DialogueNode) dialogueTrackerCm.get(tracker).nodes.get(dialogueTrackerCm.get(tracker).currentNode);
        String text =  dialogueNode.text;
        char[] charText = text.toCharArray();
        String string = activeLabel.getText().toString();
        int currentChar = dialogueTracker.dialoguePointer;
        int player = tagmanager.getEntity("player").getId();




        // This here is for passive dialogue, that just goes on.
        updateLabelText(currentChar, charText, string, dialogueTracker);
        trackPlayerClicks(player, currentChar);

        setAllLabelPosition(currentSpeakerName);


        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        activeLabel.draw(batch, 1);
        for (Label label : labelQueue) {
            label.draw(batch, 1);
        }
        batch.end();

        gotoNextNode(currentChar, charText, dialogueTracker, player, dialogueEntity);

        timer += Gdx.graphics.getDeltaTime();
    }



    private void gotoNextNode(int currentChar, char [] charText, DialogueTracker dialogueTracker, int player, int dialogueEntity) {
        if (currentChar > charText.length && playerCm.get(player).isActiveButtonClicked) {
            activeLabel = null;
            int neighborId = dialogueTracker.nodes.get(dialogueTracker.currentNode).neighbors[0];
            if (dialogueTracker.nodes.get(neighborId).getClass() == TweenNode.class) {
                TweenNode tweenNode = (TweenNode) dialogueTracker.nodes.get(neighborId);
                if (tweenNode.stopsDialogue) {
                    dialogueTracker.dialogueStopped = true;
                    Gdx.app.debug(TAG, "Dialogue stopped due to tween");
                }
                return;
            }
            DialogueNode neighborNode = (DialogueNode) dialogueTracker.nodes.get(neighborId);
            String neighborSpeaker = neighborNode.speaker;
            if (neighborSpeaker == null) {
                reset(dialogueTracker);
                return;
            }
            if (neighborSpeaker.equals("Player")) {
                Gdx.app.debug(TAG, "player is making decision");
                dialogueTracker.isMakingDecision = true;
            }
            dialogueTracker.continueConversation(dialogueEntity, neighborId);
        }

        if (currentChar == charText.length) {
            Gdx.app.debug(TAG, "currentChar is the last Char");
            dialogueTracker.dialoguePointer ++;
            isStringFinished = true;
            Gdx.app.debug(TAG, "currently one-click away from going to the next node");
        }
    }

    private void trackPlayerClicks(int player, int currentChar) {
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
                activeLabel.setText("");
            }
        }
    }

    private void updateLabelText(int currentChar, char[] charText, String string, DialogueTracker dialogueTracker) {
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
    }


    private void reset(DialogueTracker dialogueTracker) {
        Gdx.app.debug(TAG, "Resetting the Dialogue Render System");
        activeLabel = null;
        dialogueTracker.inDialogue = false;
        dialogueTracker.dialoguePointer = 0;
        this.isStringFinished = false;
        playerCm.get(tagmanager.getEntity("player")).currentState = Player.States.DEFAULT; // for now we go back to default

        //camera stuff
        cameraSystem.setZOOM(1.0f);
        cameraSystem.reset();
        Gdx.app.debug(TAG, "Player is out of the dialogue");
    }
}
